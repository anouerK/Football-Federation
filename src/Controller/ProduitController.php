<?php

namespace App\Controller;
use App\Entity\User;
use ContainerDsHILo7\PaginatorInterface_82dac15;
use DateTime;
use App\Entity\Categorie;
use App\Entity\Produit;
use App\Form\CategorieType;
use App\Form\ProduitType;
use App\Repository\CathegorieRepository;
use App\Repository\MarquesRepository;
use App\Repository\ProduitRepository;
use Doctrine\ORM\EntityManager;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use phpDocumentor\Reflection\Types\Integer;
use Symfony\Component\Form\isSubmitted;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\RequestStack;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Marques;
use App\Form\UpdatePType;

use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\String\Slugger\AsciiSlugger;

use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Validator\Constraints\Json;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use imageServer;



class ProduitController extends AbstractController
{
    public function __construct(RequestStack $requestStack)
    {
        $this->requestStack = $requestStack;
    }
    private $requestStack;


    /**
     * @Route("/afficheimg", name="afficheimg")
     */
    public function afficheimg(ProduitRepository $repository)
    {
        $allowedExts = array("gif", "jpeg", "jpg", "png");
        $temp = explode(".", $_FILES["file"]["name"]);
        $extension = end($temp);

        if ((($_FILES["file"]["type"] == "image/gif") || ($_FILES["file"]["type"] == "image/jpeg") || ($_FILES["file"]["type"] == "image/jpg") || ($_FILES["file"]["type"] == "image/pjpeg") || ($_FILES["file"]["type"] == "image/x-png") || ($_FILES["file"]["type"] == "image/png")) && ($_FILES["file"]["size"] < 5000000) && in_array($extension, $allowedExts)) {
            if ($_FILES["file"]["error"] > 0) {
                $named_array = array("Response" => array(array("Status" => "error")));
                echo json_encode($named_array);
            } else {
                move_uploaded_file($_FILES["file"]["tmp_name"], "images/" . $_FILES["file"]["name"]);

                $Path = $_FILES["file"]["name"];
                $named_array = array("Response" => array(array("Status" => "ok")));
                echo json_encode($named_array);
            }
        } else {
            $named_array = array("Response" => array(array("Status" => "invalid")));
            echo json_encode($named_array);
        }
    }




    /**
     * @Route("/searchp/{searchString}", name="searchp")
     */
    public function searchp( $searchString,NormalizerInterface $serializerInterface ,PaginatorInterface $paginator,Request $request)
    {

        //$serializer=new Serializer([new ObjectNormalizer()]);
        // $serializer = new Serializer([new ObjectNormalizer()]);
        $repository = $this->getDoctrine()->getRepository(Produit::class);
        // $students = $repository->findByid($searchString);
        //$students = $repository->findBy(array('id' => '%'.'2'));

        $students = $repository->findByExampleField($searchString);
        $p=$paginator->paginate(
            $students,
            $request->query->getInt('page',1),
            3
        );
        $data = $serializerInterface->normalize($p,'json',['groups'=>'prod']);
        // $data=$serializer->normalize("");

        return new JsonResponse($data);
    }


    /**
     * @Route("/produit", name="produit")
     */
    public function index(ProduitRepository $repository): Response
    {
       $now= new \DateTime('@'.strtotime('now'));
        $date=$repository->findAll();
        $t=[];
        $x= new DateTime();
        foreach ($date as $test)
        {
           $a= $x->setTimestamp(strtotime($test->getDateAjout()->format('Y-m-d H:i:s')));


           if($a->modify('+10 day') > $now) {


               $t[] = ['product' => $repository->find($test->getId())];
           }

            }




        return $this->render('new.html.twig',['date'=>$t]);
    }

    /**
     * @Route("/ajoutp", name="ajoutp")
     */
    public function ajoutp(Request $request): Response
    {
        $c=new Produit();
        $slugger = new AsciiSlugger();
        $br = 'uploads/img';
        $form=$this->createForm(ProduitType::class,$c);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            /** @var UploadedFile $brochureFile */
            $brochureFile = $form->get('img')->getData();
            // this condition is needed because the 'brochure' field is not required
            // so the PDF file must be processed only when a file is uploaded
            if ($brochureFile) {
                $originalFilename = pathinfo($brochureFile->getClientOriginalName(), PATHINFO_FILENAME);
                // this is needed to safely include the file name as part of the URL
                $safeFilename = $slugger->slug($originalFilename);
                $newFilename = $safeFilename.'-'.uniqid().'.'.$brochureFile->guessExtension();

                // Move the file to the directory where brochures are stored
                try {
                    $brochureFile->move(
                        $br,
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }

                // updates the 'brochureFilename' property to store the PDF file name
                // instead of its contents
                $c->setImg($newFilename);
            }

            $c->setDateAjout(new \DateTime('@'.strtotime('now')));
            $em=$this->getDoctrine()->getManager();
            $em->persist($c);
            $em->flush();
            // return $this->redirectToRoute("affichec");

        }

        return $this->render('produit/ajout_produit.html.twig', [ 'f'=>$form->createView()]);

    }

    /**
     * @Route("/affichep", name="affichep")
     */
    public function affichep(ProduitRepository $repository): Response
    {
        $session = $this->requestStack->getSession();
        $user = $session->get('user');

        if($user == null )
            return $this->redirectToRoute('login');
        $user =  $this->getDoctrine()->getRepository(User::class)->findOneBy(['id'=>$user[0]]);
        if($user == null )
            return $this->redirectToRoute('login');

        if($user->getRole() == 'client' )
        {
            return $this->redirectToRoute('user');
        }
        $r=$this->getDoctrine()->getRepository(Produit::class);
        $p=$r->findAll();
        $search=$repository->searchProduit();
        return $this->render('Produit/index.html.twig',
            ['c'=>$p, 'qte'=>$search
        ]);
    }



    /**
     * @Route("/affichenot", name="affichenot")
     */
    public function affichenot(ProduitRepository $repository): Response
    {
        $session = $this->requestStack->getSession();
        $user = $session->get('user');

        if($user == null )
            return $this->redirectToRoute('login');
        $user =  $this->getDoctrine()->getRepository(User::class)->findOneBy(['id'=>$user[0]]);
        if($user == null )
            return $this->redirectToRoute('login');

        if($user->getRole() == 'client' )
        {
            return $this->redirectToRoute('user');
        }
        $r=$this->getDoctrine()->getRepository(Produit::class);
        $p=$r->findAll();
        $search=$repository->searchProduit();
        return $this->render('Produit/notify.html.twig',
            [ 'qte'=>$search
            ]);
    }



    /**
     * @Route("/modifp/{id}", name="modifp")
     */
    public function modifp(Request $request,$id): Response
    {
        $slugger = new AsciiSlugger();
        $br = 'uploads/img';

        $class=$this->getDoctrine()->getRepository(Produit::class)->find($id);


        $form=$this->createForm(ProduitType::class,$class);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $em=$this->getDoctrine()->getManager();

            /** @var UploadedFile $brochureFile */
            $brochureFile = $form->get('img')->getData();

            // this condition is needed because the 'brochure' field is not required
            // so the PDF file must be processed only when a file is uploaded
            if ($brochureFile) {
                $originalFilename = pathinfo($brochureFile->getClientOriginalName(), PATHINFO_FILENAME);
                // this is needed to safely include the file name as part of the URL
                $safeFilename = $slugger->slug($originalFilename);
                $newFilename = $safeFilename . '-' . uniqid() . '.' . $brochureFile->guessExtension();

                // Move the file to the directory where brochures are stored
                try {
                    $brochureFile->move(
                        $br,
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }
            }
                $class->setImg($newFilename);

            $em->flush();
            return $this->redirectToRoute("affichep");
        }

        return $this->render('Produit/modif_produit.html.twig', [ 'f'=>$form->createView()]);



    }


    /**
     *
     * @Route("/suppp/{id}", name="suppp")
     */
    public function suppp($id): Response
    {
        $class=$this->getDoctrine()->getRepository(Produit::class)->find($id);
        $d=$this->getDoctrine()->getManager();
        $d->remove($class);
        $d->flush();
        return $this->redirectToRoute("affichep");

    }


    /**
     * @Route("/affichef", name="affichef")
     */
    public function affichef(CathegorieRepository $repository,ProduitRepository $pr,Request $request,PaginatorInterface $paginator): Response
    {
        
        $verif=0;
        $cat=$repository->findAll();
        //$r=$this->getDoctrine()->getRepository(Produit::class);
        $donnes=$pr->findAll();
        $p=$paginator->paginate(
            $donnes,
            $request->query->getInt('page',1),
            3
        );

        $count=$pr->counts();





        return $this->render('produit/store.html.twig', ['c'=>$p,'cat'=>$cat,'verif'=>$verif
        ]);
    }

    /**
     * @Route("/find{id}", name="find")
     */

    public function find($id,Request $request,ProduitRepository $repository,CathegorieRepository $repository2,PaginatorInterface $paginator)
{

    $search=$repository->searchProduit_categorie($id);
    $cat=$repository2->findAll();





    if(isset($_GET['prix1']))
    {
        $search=$repository->filter($id);


    }

    if(isset($_GET['prix2']))
    {
        $search=$repository->filter2($id);
    }

    if(isset($_GET['red']))
    {
        $search=$repository->filter3($id,'rouge');
    }

    if(isset($_GET['white']))
    {
        $search=$repository->filter4($id,'blanc');
    }
    if(isset($_GET['black']))
    {
        $search=$repository->filter5($id,'noire');
    }


    if(isset($_GET['white']) && isset($_GET['prix1']) )
    {
        $search=$repository->filter6($id,'blanc');
    }

    if(isset($_GET['white']) && isset($_GET['prix2']) )
    {
        $search=$repository->filter7($id,'blanc');
    }


    if(isset($_GET['black']) && isset($_GET['prix1']) )
    {
        $search=$repository->filter8($id,'noire');
    }

    if(isset($_GET['black']) && isset($_GET['prix2']) )
    {
        $search=$repository->filter9($id,'noire');
    }



    $p=$paginator->paginate(
        $search,
        $request->query->getInt('page',1),
        3
    );


    return $this->render('produit/store.html.twig',['c'=>$p,'cat'=>$cat,'verif'=>$id]);


}

    /**
     * @Route("/product_page{id}", name="product_page")
     */
    public function product_page($id): Response
    {
        $r=$this->getDoctrine()->getRepository(Produit::class);
        $p=$r->find($id);
        return $this->render('product.html.twig', ['c'=>$p
        ]);
    }

    /**
     * @Route("/notification{id}", name="notification")
     */
    public function notification($id,Request $request,ProduitRepository $repository):Response
    {
        $r=$this->getDoctrine()->getRepository(Produit::class);
        $c=$r->find($id);

       $p=$repository->find($id);
        $em=$this->getDoctrine()->getManager();

        $form=$this->createForm(UpdatePType::class);
        $form->handleRequest($request);
        if($form->isSubmitted())
        {
            $x=$form->getData();
            foreach ($x as $qte) {
                $i=(int)$qte;
                $c->setQte($i);
            }
        if($p->getId()==$id) {
            $c->setNomP($p->getNomP());
            $c->setMarquep($p->getMarquep());
            $c->setTaille($p->getTaille());
            $c->setTaille2($p->getTaille2());
            $c->setDescr($p->getDescr());
            $c->setPrix($p->getPrix());
            $c->setImg($p->getImg());
            $c->setDateAjout($p->getDateAjout());
            $c->setCouleur($p->getCouleur());
            $c->setCategorie($p->getCategorie());
        }

            $em->persist($c);
            $em->flush();
        }



        return $this->render('produit/modif_notif.html.twig',
            [ 'f'=>$form->createView(),'id'=>$p->getId() ]);
    }




    /**
     * @Route("/addp", name="addp")
     */

    public function addp(Request $request, NormalizerInterface  $serializer , EntityManagerInterface $em)
    {
        $badge = new Produit();

        $nom = $request->query->get("nomP");
        $taille1 = $request->query->get("taille1");
        $taille2 = $request->query->get("taille2");
        $couleur=  $request->query->get("couleurP");
        $prix=     $request->query->get("prixP");
        $descr=    $request->query->get("descrP");
        $qte=      $request->query->get("quantiteP");
        //$img=      $request->query->get("imageP");
        $date=    new \DateTime('now');
        $categorie=$request->query->get("categorieP");
        $marque=   $request->query->get("marqueP");

        $m=$this->getDoctrine()->getManager()->getRepository(Categorie::class)->find($request->get("categorieP"));
        $m2=$this->getDoctrine()->getManager()->getRepository(Marques::class)->find($request->get("marqueP"));


        $em = $this->getDoctrine()->getManager();


        $slugger = new AsciiSlugger();
        $br = 'uploads/img/';
        $temp = explode(".", $_FILES["file"]["name"]);
        $extension = end($temp);
        /** @var UploadedFile $brochureFile */
        $brochureFile = $_FILES["file"]["tmp_name"];
        $originalFilename = pathinfo($_FILES["file"]["name"], PATHINFO_FILENAME);
        $safeFilename = $slugger->slug($originalFilename);
        $newFilename = $safeFilename.'-'.uniqid().'.'.$extension;
        move_uploaded_file($brochureFile, $br . $newFilename);
        $badge->setImg($newFilename);



        $badge->setNomP($nom);
        $badge->setTaille($taille1);
        $badge->setTaille2($taille2);
        $badge->setCouleur($couleur);
        $badge->setPrix($prix);
        $badge->setDescr($descr);
        $badge->setQte($qte);
        $badge->setDateAjout($date);
        $badge->setCategorie($m);
        $badge->setMarquep($m2);
        $em->persist($badge);
        $em->flush();

        //$serializer = new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($badge,'json',['groups'=>'prod']);
        return new JsonResponse($formatted);
    }
    /**
     * @Route("/produit_date", name="produit_date")
     */
    public function produit_date(ProduitRepository $repository,NormalizerInterface $normalizer): Response
    {
        $now = new \DateTime('@' . strtotime('now'));
        $date = $repository->findAll();
        $t = [];
        $x = new DateTime();
        foreach ($date as $test) {
            $a = $x->setTimestamp(strtotime($test->getDateAjout()->format('Y-m-d H:i:s')));


            if ($a->modify('+10 day') > $now) {


                $t[] = ['product' => $repository->find($test->getId())];
            }

        }

        $json=$normalizer->normalize($t,'json',['groups'=>'prod']);

        return new  Response(json_encode($json));

    }
    /**
     * @Route("/getp", name="getp")
     */
    public function getp(ProduitRepository  $repository,NormalizerInterface $serializer,CathegorieRepository $xx,MarquesRepository $m){
        //$marque=$this->getDoctrine()->getManager()->getRepository(Marques::class)->findAll();
       // $cat=$c->findAll();
       // $mar=$m->findAll();
        $prod=$repository->findAll();

        $rep= $this->getDoctrine()->getRepository(Produit::class);
        $marque=$rep->findAll();

        $json=$serializer->normalize($prod,'json',['groups'=>'prod']);






        return new Response(json_encode($json));



       // $formatted=$serializer->normalize($marque,'json',['groups'=>'prod']);

      //  return new  Response(json_encode($formatted));
    }



    /**
     * @Route("/getptri", name="getptri")
     */
    public function getptri(ProduitRepository  $repository,NormalizerInterface $serializer,CathegorieRepository $c,MarquesRepository $m,Request $request){
        //$marque=$this->getDoctrine()->getManager()->getRepository(Marques::class)->findAll();
        $cat=$c->findAll();
        $mar=$m->findAll();
        $tri = $request->get("tri");
        if($tri=="c") {
            $prod = $repository->filterprixc();
        }
        if($tri=="d") {
            $prod = $repository->filterprixd();
        }

        $rep= $this->getDoctrine()->getRepository(Produit::class);
        $marque=$rep->findAll();

        $json=$serializer->normalize($prod,'json',['groups'=>'prod']);



        for($i = 0 ; $i< count($json);$i++)
        {
            $cat_id =  $prod[0]->getCategorie()->getId();
            $badge = $this->getDoctrine()->getRepository(Categorie::class)->findOneBy(['id' => $cat_id]);
            $badge_j = $serializer->normalize($badge,'json',['groups'=>'cat']);
            $json[$i]["categorie"]=$badge_j;


        }



        for($i = 0 ; $i< count($json);$i++)
        {
            $badge_id =  $prod[0]->getMarquep()->getId();
            $badge = $this->getDoctrine()->getRepository(Marques::class)->findOneBy(['id' => $badge_id]);
            $badge_j = $serializer->normalize($badge,'json',['groups'=>'marque']);
            $json[$i]["marquep"]=$badge_j;


        }



        return new Response(json_encode($json));



        $formatted=$serializer->normalize($marque,'json',['groups'=>'prod']);

        return new  Response(json_encode($formatted));
    }



    /**
     * @Route("/searchprod/{searchString}", name="searchprod")
     */
    public function searchprod($searchString,ProduitRepository  $repository,NormalizerInterface $serializer,CathegorieRepository $c,MarquesRepository $m,Request $request)
    {
        $cat=$c->findAll();
        $mar=$m->findAll();
        //$tri = $request->get("tri");
        $prod = $repository->findByExampleField($searchString);


        $rep= $this->getDoctrine()->getRepository(Produit::class);
        $marque=$rep->findAll();

        $json=$serializer->normalize($prod,'json',['groups'=>'prod']);



        for($i = 0 ; $i< count($json);$i++)
        {
            $cat_id =  $prod[0]->getCategorie()->getId();
            $badge = $this->getDoctrine()->getRepository(Categorie::class)->findOneBy(['id' => $cat_id]);
            $badge_j = $serializer->normalize($badge,'json',['groups'=>'cat']);
            $json[$i]["categorie"]=$badge_j;


        }



        for($i = 0 ; $i< count($json);$i++)
        {
            $badge_id =  $prod[0]->getMarquep()->getId();
            $badge = $this->getDoctrine()->getRepository(Marques::class)->findOneBy(['id' => $badge_id]);
            $badge_j = $serializer->normalize($badge,'json',['groups'=>'marque']);
            $json[$i]["marquep"]=$badge_j;


        }



        return new Response(json_encode($json));



        $formatted=$serializer->normalize($marque,'json',['groups'=>'prod']);

        return new  Response(json_encode($formatted));
    }



    /**
     * @Route("/updatep", name="updatep")
     */
    public function updatep(Request $request,NormalizerInterface $serializer,EntityManagerInterface $em)
    {
        $em=$this->getDoctrine()->getManager();
        $badge=$this->getDoctrine()->getManager()->getRepository(Produit::class)->find($request->get("id"));


        $nom = $request->query->get("nomP");
        $file = $request->query->get("file");
        $taille1 = $request->query->get("taille1");
        $taille2 = $request->query->get("taille2");
        $couleur=  $request->query->get("couleurP");
        $prix=     $request->query->get("prixP");
        $descr=    $request->query->get("descrP");
        $qte=      $request->query->get("quantiteP");
        //$img=      $request->query->get("imageP");
        $date=    new \DateTime('now');
        $categorie=$request->query->get("categorieP");
        $marque=   $request->query->get("marqueP");

        $m=$this->getDoctrine()->getManager()->getRepository(Categorie::class)->find($request->get("categorieP"));
        $m2=$this->getDoctrine()->getManager()->getRepository(Marques::class)->find($request->get("marqueP"));




if($file!="") {
    $slugger = new AsciiSlugger();
    $br = 'uploads/img/';
    $temp = explode(".", $_FILES["file"]["name"]);
    $extension = end($temp);
    /** @var UploadedFile $brochureFile */
    $brochureFile = $_FILES["file"]["tmp_name"];
    $originalFilename = pathinfo($_FILES["file"]["name"], PATHINFO_FILENAME);
    $safeFilename = $slugger->slug($originalFilename);
    $newFilename = $safeFilename . '-' . uniqid() . '.' . $extension;
    move_uploaded_file($brochureFile, $br . $newFilename);
    $badge->setImg($newFilename);
}
else
{
    $badge->setImg($badge->getImg());
}


        $badge->setNomP($nom);
        $badge->setTaille($taille1);
        $badge->setTaille2($taille2);
        $badge->setCouleur($couleur);
        $badge->setPrix($prix);
        $badge->setDescr($descr);
        $badge->setQte($qte);
        $badge->setDateAjout($date);
        $badge->setCategorie($m);
        $badge->setMarquep($m2);
        $em->persist($badge);
        $em->flush();


        //$serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($badge,'json',['groups'=>'prod']);
        return new JsonResponse('Produit updated');
    }

    /**
     * @Route("/deletep", name="deletep")
     */
    public function deletep(Request $request, SerializerInterface  $serializer , EntityManagerInterface $em)
    {
        $id = $request->get("id");
        $em = $this->getDoctrine()->getManager();
        $badge  = $em->getRepository(Produit::class)->find($id);
        if($badge != null)
        {
            $em->remove($badge);
            $em->flush();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize("Produit Deleted ");
            return new JsonResponse($formatted);
        }
        return new JsonResponse("rip");
    }


}

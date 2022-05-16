<?php

namespace App\Controller;

use App\Entity\Badge;
use App\Entity\User;
use App\Repository\BadgeRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\RequestStack;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use App\Form\BadgeType;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Exception\ExceptionInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Polyfill\Intl\Normalizer\Normalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\String\Slugger\AsciiSlugger;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
class BadgeController extends AbstractController
{
    public function __construct(RequestStack $requestStack)
    {
        $this->requestStack = $requestStack;
    }
    private $requestStack;

    /**
     * @Route("/badge", name="badge")
     */
    public function index(): Response
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
        //recuperer le repository pour utiliser la fonction findAll
        $r=$this->getDoctrine()->getRepository(Badge::class);

        //faire appel a la fonction findAll
        $badges=$r->findAll();
        return $this->render('badge/index.html.twig', [
            'b' =>$badges,
        ]);
    }

    /**
     * @Route("/viewb", name="viewb")
     */
    public function viewb(): Response
    {
        $session = $this->requestStack->getSession();
        $user = $session->get('user');

        if( $user == null )
            return $this->redirectToRoute('login');
        $user =  $this->getDoctrine()->getRepository(User::class)->findOneBy(['id'=>$user[0]]);
        if($user == null )
            return $this->redirectToRoute('login');

        if($user->getRole() == 'client' )
        {
            return $this->redirectToRoute('user');
        }
        //recuperer le repository pour utiliser la fonction findAll
        $r=$this->getDoctrine()->getRepository(Badge::class);

        //faire appel a la fonction findAll
        $badges=$r->findAll();
        return $this->render('badge/viewb.html.twig', [
            'b' =>$badges,
        ]);
    }

    /**
     * @Route("/viewbaj", name="viewbaj")
     */
    public function viewbaj( Request $request,NormalizerInterface $Normalizer)
    {
        $repo = $this->getDoctrine()->getRepository(Badge::class);
        $order = $request->get("nb");
        if($order == 1 )
        {
            $badges = $this->getDoctrine()->getRepository(Badge::class)->findBy(
                array(),
                array('nb' => 'ASC')
            );

        }
        if($order == 2 )
        {
            $badges = $this->getDoctrine()->getRepository(Badge::class)->findBy(
                array(),
                array('nb' => 'DESC')
            );
        }
        if($order == 3 )
        {
            $badges = $this->getDoctrine()->getRepository(Badge::class)->findBy(
                array(),
                array('nomB' => 'ASC')
            );
        }
        if($order == 4 )
        {
            $badges = $this->getDoctrine()->getRepository(Badge::class)->findBy(
                array(),
                array('nomB' => 'DESC')
            );
        }
       if($order == 0)
        {
            $badges = $repo->findAll();
        }




        $json=$Normalizer->normalize($badges,'json',['groups'=>'badge']);
        for($i = 0 ; $i< count($json);$i++)
        {
            $badge_id = $json[$i]["id"];
            $badge=$this->getDoctrine()->getRepository(Badge::class)->findOneBy(['id' => $badge_id]);
            $json[$i]['count'] = count($badge->getUsers());
        }





        return new Response(json_encode($json));

        dump($json);

        die;
    }



    /**
     * @Route("/searchbadge", name="searchbadge")
     */
    public function searchbadge(Request  $request , NormalizerInterface $Normalizer)
    {
        $search = $request->get("str");

        $badges = $this->getDoctrine()->getRepository(Badge::class)->findByExampleField($search);

        $json=$Normalizer->normalize($badges,'json',['groups'=>'badge']);


        return new Response(json_encode($json));

        dump($json);

        die;
    }


    /**
     * @Route("/deletebaj", name="deletebaj")
     * @Method ("DELETE")
     */
    public function deletebaj(Request $request, SerializerInterface  $serializer , EntityManagerInterface $em)
    {
       $id = $request->get("id");
       $em = $this->getDoctrine()->getManager();
       $badge  = $em->getRepository(Badge::class)->find($id);
       if($badge != null)
       {
           $em->remove($badge);
           $em->flush();
           $serializer = new Serializer([new ObjectNormalizer()]);
           $formatted = $serializer->normalize("Badge Deleted ");
           return new JsonResponse($formatted);
       }
        return new JsonResponse("rip");
    }
    /**
     * @Route("/addBadgej", name="addBadgej")
     */
    public function addBadgej(Request $request, SerializerInterface  $serializer , EntityManagerInterface $em)
    {
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
        $badge = new Badge();
        $bn = $request->query->get("nomB");
        $nb = $request->query->get("nb");
        $em = $this->getDoctrine()->getManager();
        $badge->setNomB($bn);
        $badge->setLogoB($newFilename);
        $badge->setNb($nb);
        $em->persist($badge);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($badge);
        return new JsonResponse($formatted);
    }
    /**
     * @Route("/updateBadgeja", name="updateBadgeja")
     * @Method("PUT")
     */
    public function updateBadgeja(Request $request, SerializerInterface  $serializer , EntityManagerInterface $em)
    {
        $em= $this->getDoctrine()->getManager();
        $badge = $this->getDoctrine()->getManager()->getRepository(Badge::class)->find($request->get("id"));
        $badge->setNomB($request->get("nomB"));
        $badge->setLogoB($request->get("logoB"));
        $badge->setNb($request->get("nb"));
        $em->persist($badge);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($badge);
        return new JsonResponse("Badge Updated");
    }
    /**
     * @Route("/addBadge", name="addBadge")
     */
    public function addBadge(Request $request): Response
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
        //récupération du formulaire
        $badge = new Badge();
        $slugger = new AsciiSlugger();
        $br = 'uploads/img';
        $form = $this->createForm(BadgeType::class, $badge);
        /*Ajout du boutton dans l'action
        $form->add('Ajouter',SubmitType::class);*/
//récupérer les données saisies dans les champs
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            /** @var UploadedFile $brochureFile */
            $brochureFile = $form->get('logoB')->getData();

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
                $badge->setLogoB($newFilename);
            }
            $em = $this->getDoctrine()->getManager();
            $em->persist($badge);
            $em->flush();

            return $this->redirectToRoute('badge');
        }
        return $this->render("badge/addBadge.html.twig",
            array('fb'=>$form->createView()));
    }
    /**
     * @Route("/deleteBadge/{id}", name="deleteBadge")
     */
    public function supp($id): Response
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
        //recupperer
        $classrooms = $this->getDoctrine()->getRepository(Badge::class)->find($id);
        $er=$this->getDoctrine()->getManager();
        //suppression
        $er->remove($classrooms);
        //effectuer la supp
        $er->flush();
        return $this->redirectToRoute("badge");
    }

    /**
     * @Route("/updateBadge/{id}", name="updateBadge")
     */
    public function updateBadge(Request $request,$id)
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
//je récupère la classe à modifier
        $badge=$this->getDoctrine()
            ->getRepository(Badge::class)->find($id);
//récupération du formulaire
        $form = $this->createForm(BadgeType::class, $badge);
//récupérer les données saisies dans les champs
        $form->handleRequest($request);
        $slugger = new AsciiSlugger();
        $br = 'uploads/img';
        if ($form->isSubmitted() && $form->isValid()) {
            /** @var UploadedFile $brochureFile */
            $brochureFile = $form->get('logoB')->getData();

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
                $badge->setLogoB($newFilename);
            }
            $em = $this->getDoctrine()->getManager();
            $em->persist($badge);
            $em->flush();
            return $this->redirectToRoute('badge');
        }
        return $this->render('badge/updateBadge.html.twig',['fb' => $form->createView()]);
    }




}

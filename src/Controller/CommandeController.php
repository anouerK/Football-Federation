<?php

namespace App\Controller;
use App\Entity\Produit;
use App\Entity\Commande;
use App\Entity\User;
use App\Form\ProduitType;
use App\Repository\CommandeRepository;
use App\Repository\ProduitRepository;
use App\Repository\UserRepository;
use ContainerGC3QVBl\PaginatorInterface_82dac15;
use Knp\Bundle\SnappyBundle\KnpSnappyBundle;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use App\Services\MailerService;
use App\Services\MessageService;
use App\Services\PdfService;
use App\Form\VerifType;

use Symfony\Component\Notifier\Message\SmsMessage;
use Symfony\Component\Notifier\TexterInterface;

use Scheb\TwoFactorBundle\Security\TwoFactor\Provider\Totp\TotpAuthenticatorInterface;
use Scheb\TwoFactorBundle\Security\TwoFactor\QrCode\QrCodeGenerator;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use App\Services\QrcodeService;
use Symfony\Component\HttpFoundation\RequestStack;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;




class CommandeController extends AbstractController
{
    public function __construct(RequestStack $requestStack)
    {
        $this->requestStack = $requestStack;
    }
    private $requestStack;

    /**
     * @Route("/commande", name="commande")
     */
    public function index(): Response
    {
        return $this->render('commande/facture.html.twig', [
            'controller_name' => 'CommandeController',
        ]);
    }

    /**
     * @Route("/commandef", name="commandef")
     */
    public function commandef(Request $request, PaginatorInterface $paginator,CommandeRepository $commande): Response
    {


        $s = $this->requestStack->getSession();
        $user = $s->get("user");

        if($user == null)
        {
            return $this->render('user/login.html.twig');
        }
        else
        {
            $usere = $this->getDoctrine()->getRepository(User::class)->findOneBy(['id' => $user[0]]);

        }

        $donnes=$commande->finduser($usere->getID());

        $p=$paginator->paginate(
            $donnes,
            $request->query->getInt('page',1),
            2
        );
        return $this->render('commande/commandef.html.twig', [
            'items'=>$p
        ]);
    }




    /**
     * @Route("/commande/add_commande", name="add_commande")
     * @param Request $request
     * @param MessageService $messageService
     * @param MailerService $mailService
     */
    public function add_commande(SessionInterface  $session, SessionInterface $session2,
                                 ProduitRepository $produitRepository, Request $request, MessageService $messageService,
                                 MailerService     $mailerService, PdfService $pdfService, SessionInterface $session4,
                                 QrcodeService $service,SessionInterface $session5): Response
    {

        $s = $this->requestStack->getSession();
        $user = $s->get("user");


        if($user == null)
        {
            return $this->render('user/login.html.twig');
        }
        else
        {
            $usere = $this->getDoctrine()->getRepository(User::class)->findOneBy(['id' => $user[0]]);

        }

        $rand1 = random_int(10000, 99999);
        $r = (string)$rand1;
        $session4->set('verify', $rand1);


        if(isset($_GET['qr']))
        {
            $qrCode=$service->qrcode($r);
            $session5->set('qr',$qrCode);
        }

        if(isset($_GET['mail']))
        {
            $session->set('qr',null);
            $mailerService->send(
                "Nouveau Commande",
                "onep1981@gmail.com",
                'yaga77328@gmail.com',
                $r

            );
        }



        return $this->redirectToRoute("verif");


    }

    /**
     * @Route("/afficheco", name="afficheco")
     */
    public function afficheco(): Response
    {
        $r = $this->getDoctrine()->getRepository(Commande::class);
        $p = $r->findAll();
        return $this->render('Commande/index.html.twig', ['c' => $p,
        ]);
    }


    /**
     * @Route("/verif", name="verif")
     */
    public function verif(Request $request, SessionInterface $session4, PdfService $pdfService,
                          SessionInterface  $session, SessionInterface $session2,
                          ProduitRepository $produitRepository, MessageService $messageService,
                          MailerService     $mailerServic,SessionInterface $session5): Response
    {


        $s = $this->requestStack->getSession();
        $user = $s->get("user");
        $usere = $this->getDoctrine()->getRepository(User::class)->findOneBy(['id' => $user[0]]);


        $x=0;
        $panier = $session->get('commande', []);
        $panierWithData = [];
        $get = $session2->get('abc');
        $qr=$session5->get('qr');


        $em = $this->getDoctrine()->getManager();

        foreach ($panier as $id => $quantity) {

            $panierWithData[] = [
                'product' => $produitRepository->find($id),
                'quantity' => $quantity,
                'taille' => $get
            ];
        }
        $total = 0;
        foreach ($panierWithData as $item) {
            if ($item['product'] != null) {
                $totaItem = $item['product']->getprix() * $item['quantity'];
                $total += $totaItem;
            }
        }



        $get = $session4->get('verify');
        $r = $this->getDoctrine()->getRepository(Commande::class);

        $form = $this->createForm(VerifType::class);
        $form->handleRequest($request);

        if ($form->isSubmitted()) {
            $x = $form->getData();
            foreach ($x as $b) {

                if ($b == $get) {

                    foreach ($panierWithData as $item){

                        $commande = new  Commande();
                        if ($item['product'] != null) {

                            $commande->setIdP($item['product']);
                           // dd($item['product']->getid());
                            $commande->setPrixU($item['product']->getprix() * $item['quantity']);
                            $commande->setQte($item['quantity']);
                            $commande->setDate(new \DateTime('@' . strtotime('now')));
                            $commande->setIdU($usere);
                            $commande->setTaille($item['taille']);
                            $commande->setEtat(1);


                            $em->persist($commande);
                            $em->flush();
                        }
                    }
                    $date= new \DateTime('@' . strtotime('now'));



                    $html = $this->renderView('commande/facture.html.twig',['commande'=>$panierWithData,'datec'=>$date,'total'=>$total,'user'=>$usere]);
                    //$html .= '<link type="text/css" media="dompdf" href="C:\Users\Lord\Documents\FirstProject\public\facture.css" rel="stylesheet"/>';
                    $pdf = $pdfService->showPfFile($html);

/*
                    if (!empty($panier))
                    {
                        $panier=  array_diff($panier,$panier);
                    }

                    $session->set('commande',$panier);
*/

                    return $this->redirectToRoute("cart_remove2");
                   
                    

                } else {
                    $x=1;
                    return $this->render('Commande/verification.html.twig', ['f' => $form->createView(),'x'=>$x,'qr'=>$qr]);
                }
            }
        }

        return $this->render('Commande/verification.html.twig', ['f' => $form->createView(),'x'=>$x,'qr'=>$qr]);

    }


    /**
     * @Route("/addcom", name="addcom")
     */
public function  addcom(Request $request, NormalizerInterface $serializer)
{
    $commande= new Commande();

    $prix = $request->query->get("prixc");
    $qte= $request->query->get("qtec");
    $etat = $request->query->get("etatc");
    $taille = $request->query->get("taillec");
    $date=    new \DateTime('now');

    $em = $this->getDoctrine()->getManager();
    $p=$this->getDoctrine()->getManager()->getRepository(Produit::class)->find($request->get("produit"));
    $u=$this->getDoctrine()->getManager()->getRepository(User::class)->find($request->get("user"));

   $commande->setDate($date);
   $commande->setIdP($p);
   $commande->setIdU($u);
   $commande->setQte($qte);
   $commande->setTaille($taille);
   $commande->setEtat($etat);
   $commande->setPrixU($prix);

    $em->persist($commande);
    $em->flush();

    $formatted=$serializer->normalize($commande,'json',['groups'=>'commande']);
    return new JsonResponse($formatted);

}



    /**
     * @Route("/getcom", name="getcom")
     */
    public function getcom(CommandeRepository  $repository,NormalizerInterface $serializer,UserRepository $u,ProduitRepository $p){
        //$marque=$this->getDoctrine()->getManager()->getRepository(Marques::class)->findAll();
       // $prod=$p->findAll();
      //  $user=$u->findAll();
        $commande=$repository->findAll();

        $rep= $this->getDoctrine()->getRepository(Commande::class);
        //$marque=$rep->findAll();

        $json=$serializer->normalize($commande,'json',['groups'=>'commande']);







        return new Response(json_encode($json));


    }


}
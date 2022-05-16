<?php

namespace App\Controller;
use App\Entity\Game;
use App\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\RequestStack;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Reclamation;
use App\Form\ReclamationType;
use App\Repository\ReclamationRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\HttpFoundation\JsonResponse;

class ReclamationController extends AbstractController
{
    public function __construct(RequestStack $requestStack)
    {
        $this->requestStack = $requestStack;
    }
    private $requestStack;
    /**
     * @Route("/reclamation", name="reclamation")
     */
    public function index(): Response
    {
        $reclamation=$this->getDoctrine()->getRepository(Reclamation::class)->findAll();
        return $this->render('reclamation/afficherReclamation.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }


    /**
     * @Route("/mesReclamations", name="mesReclamations")
     */
    public function mesReclamations(): Response
    {
        $session = $this->requestStack->getSession();
        $user = $session->get('user');

        if($user == null )
            return $this->redirectToRoute('login');
      //  $user =  $this->getDoctrine()->getRepository(User::class)->findOneBy(['id'=>$user[0]]);
        $reclamation=$this->getDoctrine()->getRepository(Reclamation::class)->findBy(['user'=>$user[0]]);

        return $this->render('reclamation/mesReclamations.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    /**
     * @param Request $request
     * @return Response
     * @Route("/AjouterReclamation", name="ajouterReclamation")
     */
    public function AjouterReclamation(Request $request): Response
    {
        $r = new Reclamation();
        $session = $this->requestStack->getSession();
        $user = $session->get('user');

        if($user == null )
            return $this->redirectToRoute('login');
        $user =  $this->getDoctrine()->getRepository(User::class)->findOneBy(['id'=>$user[0]]);
        if($user == null )
            return $this->redirectToRoute('login');


        $rr=$this->getDoctrine()->getRepository(Game::class);
        $games=$rr->findAll();
        $form=$this->createForm(ReclamationType::class, $r);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $user = $this->getDoctrine()->getRepository(User::class)->findOneBy(['id' => $user->getId()]);
            $r->setUser($user);
            $em = $this->getDoctrine()->getManager();
            $em->persist($r);
            $em->flush();
            return $this->redirectToRoute('mesReclamations');
        }
        return $this->render('reclamation/ajouterReclamation.html.twig', [
            'formareclamation' => $form->createView(),
            'e' =>$games,

        ]);
    }



    /**
     * @Route("/supprimerReclamation/{idr}", name="supprimerReclamation")
     */
    public function SupprimerReclamation($idr, ReclamationRepository $repository): Response
    {
        $reclamation=$repository->find($idr);
        $em=$this->getDoctrine()->getManager();
        $em->remove($reclamation);
        $em->flush();
        return $this->redirectToRoute('mesReclamations');
    }

    /**
     * @Route("/modifierReclamation{idr}", name="modifierReclamation")
     */
    public function modifierReclamation(ReclamationRepository $repository,Request $request, $idr)
    {
        $reclamation=$repository->find($idr);
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $rr=$this->getDoctrine()->getRepository(Game::class);
        $games=$rr->findAll();
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){

            $em = $this->getDoctrine()->getManager();
            $em->persist($reclamation);
            $em->flush();
            return $this->redirectToRoute('mesReclamations');
        }
        return $this->render('reclamation/modifierReclamation.html.twig', [
            'formareclamation' => $form->createView(),
            'e' =>$games,
        ]);
    }
    /**
     * @Route("/mesReclamationsJson", name="mesReclamationsJson")
     */
    public function mesReclamationsJson(SerializerInterface $serializer)
    {
        $reclamation=$this->getDoctrine()->getRepository(Reclamation::class)->findAll();

        $jsonContent = $serializer->serialize($reclamation, 'json', ['groups' => 'reclamation']);
        $response = new Response($jsonContent);
        $response->headers->set('Content-Type', 'application/json');

        return $response;
    }

    /**
     * @Route("/ajouterReclamationJson", name="ajouterReclamationJson")
     */
    public function ajouterReclamationJson(Request $request)
    {
        $parameters = json_decode($request->getContent(), true);
        $user = $this->getDoctrine()->getRepository(User::class)->find($request->get("user_id"));

        $reclamation = new Reclamation();
        $reclamation->setObjet($request->get("objet"));
        $reclamation->setDescR($request->get("descR"));
        $reclamation->setStatus($request->get("status"));
        $reclamation->setUser($user);
        $em = $this->getDoctrine()->getManager();
        $em->persist($reclamation);
        $em->flush();
        return new JsonResponse("done!");
    }

    /**
     * @Route("/updateReclamationJson", name="updateReclamationJson")
     */
    public function updateReclamationJson(Request $request)
    {
        $parameters = json_decode($request->getContent(), true);
        $user = $this->getDoctrine()->getRepository(User::class)->find($request->get("user_id"));

        $reclamation = $this->getDoctrine()->getRepository(Reclamation::class)->find($request->get("idr"));
        $reclamation->setObjet($request->get("objet"));
        $reclamation->setDescR($request->get("descR"));
        $reclamation->setStatus($request->get("status"));
        $reclamation->setUser($user);
        $em = $this->getDoctrine()->getManager();
        $em->persist($reclamation);
        $em->flush();
        return new JsonResponse("done!");

    }

    /**
     * @Route("/supprimerReclamationJson", name="supprimerReclamationJson")
     */
    public function supprimerReclamationJson(Request $request)
    {
        $parameters = json_decode($request->getContent(), true);
        $reclamation = $this->getDoctrine()->getRepository(Reclamation::class)->find($request->get('idr'));

        $em=$this->getDoctrine()->getManager();
        $em->remove($reclamation);
        $em->flush();
        return new JsonResponse("done!");
    }
}

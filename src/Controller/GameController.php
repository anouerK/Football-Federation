<?php

namespace App\Controller;

use App\Entity\Game;
use App\Entity\Stade;
use App\Entity\Joueur;
use App\Entity\Tournoi;
use App\Entity\Arbitre;
use App\Form\GameType;
use App\Entity\Club;
use App\Form\TournoiType;
use App\Form\StadeType;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\FormTypeInterface;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\FormBuilderInterface;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\HttpFoundation\JsonResponse;


class GameController extends AbstractController
{
    /**
     * @Route("/game", name="game")
     */
    public function index(): Response
    {
        return $this->render('game/index.html.twig', [
            'controller_name' => 'GameController',
        ]);
    }

    /**
     * @Route("/detailG{id}", name="detailG")
     */
    public function detailG($id): Response
    {
        //recuperer le repository pour utiliser la fonction findAll()
        $r=$this->getDoctrine()->getRepository(Game::class);
        $games=$r->findAll();
        $r=$this->getDoctrine()->getRepository(Game::class);
        $arbitres=$r->find($id);
        $result = $this->getDoctrine()->getRepository(tournoi::class);
        $tournois = $result->findall();
        $r=$this->getDoctrine()->getRepository(Joueur::class);
        $joueurs=$r->findAll();
        return $this->render('game/detailG.html.twig', [
            'e' => $arbitres,
            'g' =>$games,
            'c' =>$tournois,
            'jrr' =>$arbitres->getClub1()->getJoueurs(),
            'jr' =>$arbitres->getClub2()->getJoueurs(),
        ]);
    }

    ///methode Ajout
    /**
     * @Route("/ajoutG", name="ajoutG")
     */

    public function ajoutG(Request $request,FlashyNotifier $flashy): Response
    {
        //creation une formulaire
        $g=new Game();
        $form=$this->createForm(GameType::class,$g);

        //recuperer les donnees depuis la requette http
        $form->handleRequest($request);
        if($form->isSubmitted()&&$form->isValid())
        {

            $em=$this->getDoctrine()->getManager();
            $em->persist($g);
            $em->flush();
            $flashy->success('Match ajouté avec succés', 'http://your-awesome-link.com');
            return $this->redirect('afficheG');
        }
        return $this->render('game/ajoutG.html.twig',['g' => $form->createView()]);

    }

    ///methode affichage
    /**
     * @Route("/afficheG", name="afficheG")
     */
    public function afficheG(): Response
    {
        //recuperer le repository pour utiliser la fonction findAll()
        $r=$this->getDoctrine()->getRepository(Game::class);
        $games=$r->findAll();
        return $this->render('game/afficheG.html.twig', [
            'e' => $games,
        ]);
    }
    ///methode affichage
    /**
     * @Route("/afficheGF", name="afficheGF")
     */
    public function afficheGF(): Response
    {
        //recuperer le repository pour utiliser la fonction findAll()
        $r=$this->getDoctrine()->getRepository(Game::class);
        $games=$r->findAll();
        $result = $this->getDoctrine()->getRepository(tournoi::class);
        $tournois = $result->findall();
        $result = $this->getDoctrine()->getRepository(tournoi::class);
        $tournois = $result->findall();
        return $this->render('game/afficheGF.html.twig', [
            'e' => $games,
            'c' =>$tournois,

        ]);
    }

    ///methode affichage
    /**
     * @Route("/afficheGFD{id}", name="afficheGFD")
     */
    public function afficheGFD($id): Response
    {
        //recuperer le repository pour utiliser la fonction findAll()
        $r=$this->getDoctrine()->getRepository(Tournoi::class);
        $arbitres=$r->find($id);
        $r=$this->getDoctrine()->getRepository(Game::class);
        $games=$r->findAll();
        $result = $this->getDoctrine()->getRepository(tournoi::class);
        $tournois = $result->findall();
        return $this->render('game/afficheGFD.html.twig', [
            'e' => $games,
            'art'=>$arbitres->getGame(),
            'c' =>$tournois,
            
        ]);
    } 

    ///methode supprimer
    /**
     * @Route("/SuppG/{id}", name="SuppG")
     */
    public function SuppG($id,FlashyNotifier $flashy): Response
    {
        //recuperer le Classroom a supprimer find($id)
        $s=$this->getDoctrine()->getRepository(Game::class);
        $games=$s->find($id);

        //on passe a la supprision
        $em=$this->getDoctrine()->getManager();
        $em->remove($games);
        $em->flush();
        $flashy->error('Match supprimé !!', 'http://your-awesome-link.com');
        return $this->redirectToroute('afficheG');
        //    return $this->render('classroom/afficheC.html.twig', [
        //    'c' => $classrooms,
        //    ]);
    }

    ///methode modifier
    /**
     * @Route("/modifG{id}", name="modifG")
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function modifG(Request $request,$id,FlashyNotifier $flashy): Response
    {
        //recuperer le Classroom a supprimer find($id)
        // $c=new Classroom();
        $s=$this->getDoctrine()->getRepository(Game::class);
        $games=$s->find($id);

        $form=$this->createForm(GameType::class,$games);
        $checkout=$form->handleRequest($request);
        if($checkout->isSubmitted()&&$checkout->isValid())
        {

            $em=$this->getDoctrine()->getManager();
            $em->flush();
            $flashy->info('Match modifié!', 'http://your-awesome-link.com');
            return $this->redirectToRoute('afficheG');
        }

        return $this->render('game/ajoutG.html.twig',['g' => $form->createView()]);
    }


        /**
     * @Route("/testG", name="testG")
     */
    public function testG(): Response
    {
        $repo = $this->getDoctrine()->getRepository(Game::class);
        $tournois = $repo->findAll();
        return $this->render('game/recent_listG.html.twig', [
            't' => $tournois,
        ]);
    }
    /**
     * @Route("/viewG", name="viewG")
     */
    public function viewG( NormalizerInterface $Normalizer)
    {
        $repo = $this->getDoctrine()->getRepository(Game::class);
        $tournois = $repo->findAll();

        $json=$Normalizer->normalize($tournois,'json',['groups'=>'game']);

        return new Response(json_encode($json));

        dump($json);

        die;

    }

    /**
     * @Route("/addGame", name="addGame")
     */
    public function addGame(Request $request, SerializerInterface  $serializer )
    {

        $id = $request->get("idt");
        $id1 = $request->get("idc1");
        $id2 = $request->get("idc2");
        $id3 = $request->get("idar");
        $id4 = $request->get("ids");


        $t  = $this->getDoctrine()->getRepository(Tournoi::class)->find($id);
        $t1  = $this->getDoctrine()->getRepository(Club::class)->find($id1);
        $t2  = $this->getDoctrine()->getRepository(Club::class)->find($id2);
        $t3  = $this->getDoctrine()->getRepository(Arbitre::class)->find($id3);
        $t4  = $this->getDoctrine()->getRepository(Stade::class)->find($id4);

        $em=$this->getDoctrine()->getManager();
        $tournoi=new Game();

        $tournoi->setTournoi($t);
        $tournoi->setClub1($t1);
        $tournoi->setR1($request->get("r1"));
        $tournoi->setR2($request->get("r2"));
        $tournoi->setClub2($t2);
        $tournoi->setArbitre($t3);
        $tournoi->setStade($t4);
        $tournoi->setDated(date_create_from_format("d-m-Y",$request->get("dated")));

        $em->persist($tournoi);
        $em->flush();
        //$serializer = new Serializer([new ObjectNormalizer()]);
        $json=$serializer->normalize($tournoi,'json',['groups'=>'game']);
        $formatted = $serializer->normalize($json);
        return new JsonResponse($formatted);
    }


    /**
     * @Route("/deleteG", name="deleteG")
     */
    public function deleteG(Request $request, SerializerInterface  $serializer )
    {
        $id = $request->get("id");

        $tournois  = $this->getDoctrine()->getRepository(Game::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        if($tournois != null)
        {
            $em->remove($tournois);
            $em->flush();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize("Game Deleted ");
            return new JsonResponse($formatted);
        }
    }

    /**
     * @Route("/updateGameeJSON/{id}", name="updateGameeJSON")
     */
    public function updateGameeJSON(Request $request, NormalizerInterface $Normalizer,$id){
        $em=$this->getDoctrine()->getManager();
        $event=$em->getRepository(Game::class)->find($id);





        $id = $request->get("idt");
        $id1 = $request->get("idc1");
        $id2 = $request->get("idc2");
        $id3 = $request->get("idar");
        $id4 = $request->get("ids");


        $t  = $this->getDoctrine()->getRepository(Tournoi::class)->find($id);
        $t1  = $this->getDoctrine()->getRepository(Club::class)->find($id1);
        $t2  = $this->getDoctrine()->getRepository(Club::class)->find($id2);
        $t3  = $this->getDoctrine()->getRepository(Arbitre::class)->find($id3);
        $t4  = $this->getDoctrine()->getRepository(Stade::class)->find($id4);

        //   $em=$this->getDoctrine()->getManager();
        //  $tournoi=new Game();

        $event->setTournoi($t);
        $event->setClub1($t1);
        $event->setR1($request->get("r1"));
        $event->setR2($request->get("r2"));
        $event->setClub2($t2);
        $event->setArbitre($t3);
        $event->setStade($t4);
        $event->setDated(date_create_from_format("d-m-Y",$request->get("dated")));
        $em->flush();
        $jsonContent=$Normalizer->normalize($event,'json',['groups'=>'game']);

        return new Response("Informations mises à jour avec succès".json_encode($jsonContent));
    }
}

<?php

namespace App\Entity;

use App\Repository\CommandeRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass=CommandeRepository::class)
 */
class Commande
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     * @Groups("commande")
     */
    private $id;

    /**
     * @ORM\Column(type="float")
     * @Groups("commande")
     */
    private $prixU;

    /**
     * @ORM\Column(type="integer")
     * @Groups("commande")
     */
    private $qte;

    /**
     * @ORM\Column(type="date")
     * @Groups("commande")
     */
    private $date;







    /**
     * @ORM\Column(type="integer")
     * @Groups("commande")
     */
    private $etat;

    /**
     * @ORM\Column(type="string", length=255)
     * @Groups("commande")
     */
    private $taille;

    /**
     * @ORM\ManyToOne(targetEntity=User::class, inversedBy="commandes")
     * @ORM\JoinColumn(nullable=false)
     * @Groups("commande")
     */
    private $idU;

    /**
     * @ORM\ManyToOne(targetEntity=Produit::class, inversedBy="commandes")
     * @ORM\JoinColumn(nullable=false)
     * @Groups("commande")
     */
    private $idP;








    public function getId(): ?int
    {
        return $this->id;
    }

    public function getPrixU(): ?float
    {
        return $this->prixU;
    }

    public function setPrixU(float $prixU): self
    {
        $this->prixU = $prixU;

        return $this;
    }

    public function getQte(): ?int
    {
        return $this->qte;
    }

    public function setQte(int $qte): self
    {
        $this->qte = $qte;

        return $this;
    }

    public function getDate(): ?\DateTimeInterface
    {
        return $this->date;
    }

    public function setDate(\DateTimeInterface $date): self
    {
        $this->date = $date;

        return $this;
    }


    public function getEtat(): ?int
    {
        return $this->etat;
    }

    public function setEtat(int $etat): self
    {
        $this->etat = $etat;

        return $this;
    }

    public function getTaille(): ?string
    {
        return $this->taille;
    }

    public function setTaille(string $taille): self
    {
        $this->taille = $taille;

        return $this;
    }

    public function getIdU(): ?User
    {
        return $this->idU;
    }

    public function setIdU(?User $idU): self
    {
        $this->idU = $idU;

        return $this;
    }

    public function getIdP(): ?produit
    {
        return $this->idP;
    }

    public function setIdP(?produit $produit): self
    {
        $this->idP = $produit;

        return $this;
    }



}

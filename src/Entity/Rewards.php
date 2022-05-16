<?php

namespace App\Entity;

use App\Repository\RewardsRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * @ORM\Entity(repositoryClass=RewardsRepository::class)
 */
class Rewards
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     *  @Groups("s")
     */
    private $idR;

    /**
     * @ORM\Column(type="integer")
     *  @Assert\NotBlank
     * @Groups("s")
     */
    private $rank;

    /**
     * @ORM\Column(type="string", length=255)
     *  @Assert\NotBlank(message="veillez remplir le champ")
     * @Groups("s")
     */
    private $trophe;

    /**
     * @ORM\Column(type="float")
     *  * @Assert\NotBlank(message="veillez entrer un prix")
     * @Groups("s")
     */
    private $prixR;



    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Upload your image")
     * @Groups("s")
     */
    private $img;

    /**
     * @ORM\ManyToOne(targetEntity=Tournoi::class, inversedBy="rewards")
     * @ORM\JoinColumn(nullable=false)
     * @Groups("s")
     */
    private $tournoi;

    public function getIdR(): ?int
    {
        return $this->idR;
    }

    public function getRank(): ?int
    {
        return $this->rank;
    }

    public function setRank(int $rank): self
    {
        $this->rank = $rank;

        return $this;
    }

    public function getTrophe(): ?string
    {
        return $this->trophe;
    }

    public function setTrophe(string $trophe): self
    {
        $this->trophe = $trophe;

        return $this;
    }

    public function getPrixR(): ?float
    {
        return $this->prixR;
    }

    public function setPrixR(float $prixR): self
    {
        $this->prixR = $prixR;

        return $this;
    }



    public function getImg(): ?string
    {
        return $this->img;
    }

    public function setImg(string $img): self
    {
        $this->img = $img;

        return $this;
    }

    public function getTournoi(): ?Tournoi
    {
        return $this->tournoi;
    }

    public function setTournoi(?Tournoi $tournoi): self
    {
        $this->tournoi = $tournoi;

        return $this;
    }
}
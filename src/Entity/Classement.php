<?php

namespace App\Entity;

use App\Repository\ClassementRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ORM\Entity(repositoryClass=ClassementRepository::class)
 */
class Classement
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     *  @Groups("cl")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity=Club::class, inversedBy="classements")
     * @ORM\JoinColumn(nullable=false)
     * @Groups("cl")
     */
    private $club;

    /**
     * @ORM\ManyToOne(targetEntity=Tournoi::class, inversedBy="classements")
     * @ORM\JoinColumn(nullable=false)
     * @Groups("cl")
     */
    private $tournoi;





    public function getId(): ?int
    {
        return $this->id;
    }

    public function getClub(): ?club
    {
        return $this->club;
    }

    public function setClub(?club $club): self
    {
        $this->club = $club;

        return $this;
    }

    public function getTournoi(): ?tournoi
    {
        return $this->tournoi;
    }

    public function setTournoi(?tournoi $tournoi): self
    {
        $this->tournoi = $tournoi;

        return $this;
    }


}
<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220309161907 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE jour (id INT AUTO_INCREMENT NOT NULL, clu_id INT DEFAULT NULL, nom VARCHAR(255) NOT NULL, INDEX IDX_DA17D9C5A6810A97 (clu_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('CREATE TABLE panier (id INT AUTO_INCREMENT NOT NULL, user_id INT NOT NULL, produit_id INT NOT NULL, prix_u DOUBLE PRECISION NOT NULL, qte INT NOT NULL, date DATE NOT NULL, INDEX IDX_24CC0DF2A76ED395 (user_id), INDEX IDX_24CC0DF2F347EFB (produit_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE jour ADD CONSTRAINT FK_DA17D9C5A6810A97 FOREIGN KEY (clu_id) REFERENCES club (id) ON DELETE SET NULL');
        $this->addSql('ALTER TABLE panier ADD CONSTRAINT FK_24CC0DF2A76ED395 FOREIGN KEY (user_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE panier ADD CONSTRAINT FK_24CC0DF2F347EFB FOREIGN KEY (produit_id) REFERENCES produit (id)');
        $this->addSql('ALTER TABLE commande CHANGE id_u id_u_id INT NOT NULL');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67D6F858F92 FOREIGN KEY (id_u_id) REFERENCES user (id)');
        $this->addSql('CREATE INDEX IDX_6EEAA67D6F858F92 ON commande (id_u_id)');
        $this->addSql('ALTER TABLE game DROP FOREIGN KEY FK_232B318C943A5F0');
        $this->addSql('ALTER TABLE game CHANGE arbitre_id arbitre_id INT DEFAULT NULL');
        $this->addSql('ALTER TABLE game ADD CONSTRAINT FK_232B318C943A5F0 FOREIGN KEY (arbitre_id) REFERENCES arbitre (id) ON DELETE SET NULL');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('DROP TABLE jour');
        $this->addSql('DROP TABLE panier');
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67D6F858F92');
        $this->addSql('DROP INDEX IDX_6EEAA67D6F858F92 ON commande');
        $this->addSql('ALTER TABLE commande CHANGE id_u_id id_u INT NOT NULL');
        $this->addSql('ALTER TABLE game DROP FOREIGN KEY FK_232B318C943A5F0');
        $this->addSql('ALTER TABLE game CHANGE arbitre_id arbitre_id INT NOT NULL');
        $this->addSql('ALTER TABLE game ADD CONSTRAINT FK_232B318C943A5F0 FOREIGN KEY (arbitre_id) REFERENCES arbitre (id)');
    }
}

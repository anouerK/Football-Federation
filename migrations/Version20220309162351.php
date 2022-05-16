<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20220309162351 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande CHANGE id_p id_p_id INT NOT NULL');
        $this->addSql('ALTER TABLE commande ADD CONSTRAINT FK_6EEAA67D585B7FA0 FOREIGN KEY (id_p_id) REFERENCES produit (id)');
        $this->addSql('CREATE INDEX IDX_6EEAA67D585B7FA0 ON commande (id_p_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE commande DROP FOREIGN KEY FK_6EEAA67D585B7FA0');
        $this->addSql('DROP INDEX IDX_6EEAA67D585B7FA0 ON commande');
        $this->addSql('ALTER TABLE commande CHANGE id_p_id id_p INT NOT NULL');
    }
}

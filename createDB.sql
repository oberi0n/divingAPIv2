-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema equipment
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `equipment` DEFAULT CHARACTER SET utf8 ;
USE `equipment` ;

-- -----------------------------------------------------
-- Table `equipment`.`Equipment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipment`.`Equipment` (
  `idEquipment` INT(11) NOT NULL AUTO_INCREMENT,
  `libelle` VARCHAR(45) NULL DEFAULT NULL,
  `marque` VARCHAR(45) NULL DEFAULT NULL,
  `taille` VARCHAR(45) NULL DEFAULT NULL,
  `numeroSerie` VARCHAR(250) NULL DEFAULT NULL,
  `tag` VARCHAR(250) NULL DEFAULT NULL,
  `dateAchat` DATE NULL DEFAULT NULL,
  `dateDernierEntretien` DATE NULL DEFAULT NULL,
  `photoB64` MEDIUMTEXT NULL DEFAULT NULL,
  `dateRequalif` DATE NULL DEFAULT NULL,
  `fabriquant` VARCHAR(255) NULL DEFAULT NULL,
  `numeroAffiche` VARCHAR(255) NULL DEFAULT NULL,
  `statutTIV` VARCHAR(3) NULL DEFAULT NULL,
  PRIMARY KEY (`idEquipment`))
ENGINE = InnoDB
AUTO_INCREMENT = 113
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `equipment`.`Utilisateur`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipment`.`Utilisateur` (
  `idUtilisateur` INT(11) NOT NULL AUTO_INCREMENT,
  `nom` VARCHAR(255) NULL DEFAULT NULL,
  `prenom` VARCHAR(250) NULL DEFAULT NULL,
  `tag` VARCHAR(250) NULL DEFAULT NULL,
  `visible` VARCHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`idUtilisateur`))
ENGINE = InnoDB
AUTO_INCREMENT = 60
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `equipment`.`Evenement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipment`.`Evenement` (
  `idEvenement` INT(11) NOT NULL AUTO_INCREMENT,
  `Equipment_idEquipment` INT(11) NOT NULL,
  `Utilisateur_idUtilisateur` INT(11) NOT NULL,
  `dateDebut` DATE NOT NULL,
  `dateFin` DATE NULL DEFAULT NULL,
  PRIMARY KEY (`idEvenement`, `Equipment_idEquipment`, `Utilisateur_idUtilisateur`),
  INDEX `fk_Equipment_has_Utilisateur_Utilisateur1_idx` (`Utilisateur_idUtilisateur` ASC) VISIBLE,
  INDEX `fk_Equipment_has_Utilisateur_Equipment_idx` (`Equipment_idEquipment` ASC) VISIBLE,
  CONSTRAINT `fk_Equipment_has_Utilisateur_Equipment`
    FOREIGN KEY (`Equipment_idEquipment`)
    REFERENCES `equipment`.`Equipment` (`idEquipment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipment_has_Utilisateur_Utilisateur1`
    FOREIGN KEY (`Utilisateur_idUtilisateur`)
    REFERENCES `equipment`.`Utilisateur` (`idUtilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 140
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `equipment`.`NotificationsToken`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `equipment`.`NotificationsToken` (
  `idNotificationsToken` INT(11) NOT NULL AUTO_INCREMENT,
  `token` VARCHAR(45) NULL DEFAULT NULL,
  `idUtilisateur` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idNotificationsToken`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

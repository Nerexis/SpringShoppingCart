-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema spring_shop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema spring_shop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `spring_shop` DEFAULT CHARACTER SET utf8 ;
USE `spring_shop` ;

-- -----------------------------------------------------
-- Table `spring_shop`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spring_shop`.`user` (
  `id_user` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `username` VARCHAR(45) NOT NULL COMMENT '',
  `password` VARCHAR(200) NOT NULL COMMENT '',
  `enabled` INT(11) NOT NULL DEFAULT '1' COMMENT '',
  PRIMARY KEY (`id_user`)  COMMENT '',
  UNIQUE INDEX `username_UNIQUE` (`username` ASC)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `spring_shop`.`authority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spring_shop`.`authority` (
  `id_authority` INT(11) NOT NULL COMMENT '',
  `id_user` INT(11) NULL DEFAULT NULL COMMENT '',
  `role` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id_authority`)  COMMENT '',
  UNIQUE INDEX `idx_username_role` (`id_user` ASC, `role` ASC)  COMMENT '',
  INDEX `fk_username_idx` (`id_user` ASC)  COMMENT '',
  CONSTRAINT `fk_id_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `spring_shop`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `spring_shop`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spring_shop`.`product` (
  `id_product` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `price` DOUBLE NULL DEFAULT '0' COMMENT '',
  `description` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id_product`)  COMMENT '',
  UNIQUE INDEX `idproduct_UNIQUE` (`id_product` ASC)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

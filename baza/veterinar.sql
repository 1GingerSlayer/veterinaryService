/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - veterinarska_ambulanta
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`veterinar` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `veterinar`;

DROP TABLE IF EXISTS `StavkaTermina`;
DROP TABLE IF EXISTS `Termin`;
DROP TABLE IF EXISTS `VeterinarLicenca`;
DROP TABLE IF EXISTS `Veterinar`;
DROP TABLE IF EXISTS `Klijent`;
DROP TABLE IF EXISTS `KategorijaKlijenta`;
DROP TABLE IF EXISTS `Usluga`;
DROP TABLE IF EXISTS `Licenca`;

/* VETERINAR */
CREATE TABLE `Veterinar` (
  `VeterinarID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(50) NOT NULL,
  `Prezime` VARCHAR(50) NOT NULL,
  `Username` VARCHAR(50) NOT NULL,
  `Password` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`VeterinarID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Veterinar` VALUES
(1,'Nikolija','Stankovic','ni','ni'),
(2,'Milica','Jevtic','milica','milica'),
(3,'Stefan','Obradovic','stefan','stefan'),
(4,'Jovana','Radulovic','jovana','jovana');

/* LICENCA */
CREATE TABLE `Licenca` (
  `LicencaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`LicencaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Licenca` VALUES
(1,'Licenca za male zivotinje'),
(2,'Licenca za hirurske intervencije'),
(3,'Licenca za vakcinaciju zivotinja'),
(4,'Licenca za ultrazvucnu dijagnostiku'),
(5,'Licenca za hitnu veterinarsku pomoc');

/* VETERINAR-LICENCA */
CREATE TABLE `VeterinarLicenca` (
  `VeterinarID` BIGINT(10) UNSIGNED NOT NULL,
  `LicencaID` BIGINT(10) UNSIGNED NOT NULL,
  `DatumIzdavanja` DATE NOT NULL,
  PRIMARY KEY (`VeterinarID`,`LicencaID`,`DatumIzdavanja`),
  CONSTRAINT `fk_vl_veterinar` FOREIGN KEY (`VeterinarID`) REFERENCES `Veterinar` (`VeterinarID`),
  CONSTRAINT `fk_vl_licenca` FOREIGN KEY (`LicencaID`) REFERENCES `Licenca` (`LicencaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `VeterinarLicenca` VALUES
(1,1,'2021-04-12'),
(1,3,'2022-08-20'),
(2,2,'2020-11-15'),
(2,4,'2023-03-10'),
(3,5,'2024-01-25'),
(4,1,'2022-06-18'),
(4,4,'2024-05-09');

/* KATEGORIJA KLIJENTA */
CREATE TABLE `KategorijaKlijenta` (
  `KategorijaKlijentaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`KategorijaKlijentaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `KategorijaKlijenta` VALUES
(1,'Osnovni'),
(2,'Srebrni'),
(3,'Zlatni');

/* KLIJENT */
CREATE TABLE `Klijent` (
  `KlijentID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(50) NOT NULL,
  `Prezime` VARCHAR(50) NOT NULL,
  `Email` VARCHAR(100) NOT NULL,
  `Telefon` VARCHAR(40) NOT NULL,
  `KategorijaKlijentaID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`KlijentID`),
  CONSTRAINT `fk_klijent_kategorija` FOREIGN KEY (`KategorijaKlijentaID`) REFERENCES `KategorijaKlijenta` (`KategorijaKlijentaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Klijent` VALUES
(1,'Marko','Nikolic','markonikolic@gmail.com','0601112233',1),
(2,'Tamara','Petrovic','tamarapetrovic@gmail.com','0612223344',2),
(3,'Vladimir','Ilic','vladimirilic@gmail.com','0623334455',3),
(4,'Ana','Stojanovic','anastojanovic@gmail.com','0634445566',1),
(5,'Nemanja','Pavlovic','nemanjapavlovic@gmail.com','0645556677',2),
(6,'Katarina','Milosavljevic','katarinamilosavljevic@gmail.com','0656667788',3);

/* USLUGA */
CREATE TABLE `Usluga` (
  `UslugaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(100) NOT NULL,
  `Opis` VARCHAR(255) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `TrajanjeMin` INT(7) NOT NULL,
  PRIMARY KEY (`UslugaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Usluga` VALUES
(1,'Opsti pregled','Pregled opste zdravstvene slike ljubimca.',2500.00,30),
(2,'Vakcinacija','Aplikacija vakcine i upis u karton.',3000.00,20),
(3,'Ciscenje usiju','Pregled i ciscenje usiju kod pasa i macaka.',1800.00,20),
(4,'Ultrazvuk abdomena','Ultrazvucni pregled abdomena zivotinje.',5000.00,45),
(5,'Hirursko saniranje rane','Obrada, dezinfekcija i saniranje manje rane.',7000.00,60),
(6,'Laboratorijska analiza','Analiza krvi i osnovnih biohemijskih parametara.',4500.00,40),
(7,'Stomatoloski pregled','Pregled zuba i desni kod ljubimca.',2200.00,25),
(8,'Ciscenje zuba','Uklanjanje kamenca i poliranje zuba.',6500.00,70),
(9,'Kontrolni pregled','Kontrola nakon terapije ili intervencije.',1500.00,20),
(10,'Mikrocipovanje','Ugradnja mikrocipa i evidencija podataka.',3500.00,25);

/* TERMIN */
CREATE TABLE `Termin` (
  `TerminID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVremePocetka` DATETIME NOT NULL,
  `IznosBezPopusta` DECIMAL(10,2) NOT NULL,
  `Popust` DECIMAL(5,2) NOT NULL,
  `KonacanIznos` DECIMAL(10,2) NOT NULL,
  `VeterinarID` BIGINT(10) UNSIGNED NOT NULL,
  `KlijentID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`TerminID`),
  CONSTRAINT `fk_termin_veterinar` FOREIGN KEY (`VeterinarID`) REFERENCES `Veterinar` (`VeterinarID`),
  CONSTRAINT `fk_termin_klijent` FOREIGN KEY (`KlijentID`) REFERENCES `Klijent` (`KlijentID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Termin` VALUES
-- Marko: Osnovni = 0%
(1,'2026-08-01 09:00:00',5500.00,0.00,5500.00,1,1),

-- Tamara: Srebrni = 10%
(2,'2026-09-02 11:30:00',9500.00,10.00,8550.00,2,2),

-- Vladimir: Zlatni = 20%
(3,'2026-09-03 13:15:00',11500.00,20.00,9200.00,2,3),

-- Ana: Osnovni = 0%
(4,'2026-09-04 10:45:00',5700.00,0.00,5700.00,3,4),

-- Nemanja: Srebrni = 10%
(5,'2026-09-05 16:00:00',11000.00,10.00,9900.00,4,5),

-- Katarina: Zlatni = 20%
(6,'2026-09-06 18:30:00',8000.00,20.00,6400.00,1,6);

/* STAVKA TERMINA */
CREATE TABLE `StavkaTermina` (
  `TerminID` BIGINT(10) UNSIGNED NOT NULL,
  `Rb` INT(7) NOT NULL,
  `UslugaID` BIGINT(10) UNSIGNED NOT NULL,
  `Napomena` VARCHAR(200) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `Iznos` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`TerminID`,`Rb`),
  CONSTRAINT `fk_st_termin` FOREIGN KEY (`TerminID`) REFERENCES `Termin` (`TerminID`) ON DELETE CASCADE,
  CONSTRAINT `fk_st_usluga` FOREIGN KEY (`UslugaID`) REFERENCES `Usluga` (`UslugaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `StavkaTermina` VALUES
-- Termin 1: 2500 + 3000 = 5500
(1,1,1,'Opsti pregled psa.',2500.00,2500.00),
(1,2,2,'Redovna godisnja vakcinacija.',3000.00,3000.00),

-- Termin 2: 4500 + 5000 = 9500; 10% popust = 8550
(2,1,6,'Krvna slika i osnovna biohemija.',4500.00,4500.00),
(2,2,4,'Ultrazvucni pregled abdomena.',5000.00,5000.00),

-- Termin 3: 7000 + 3000 + 1500 = 11500; 20% popust = 9200
(3,1,5,'Saniranje manje rane na sapi.',7000.00,7000.00),
(3,2,2,'Vakcinacija nakon pregleda.',3000.00,3000.00),
(3,3,9,'Kontrola oporavka.',1500.00,1500.00),

-- Termin 4: 2500 + 1800 + 1400 = 5700
(4,1,1,'Opsti pregled macke.',2500.00,2500.00),
(4,2,3,'Ciscenje usiju.',1800.00,1800.00),
(4,3,9,'Kratka kontrola terapije.',1400.00,1400.00),

-- Termin 5: 6500 + 2200 + 2300 = 11000; 10% popust = 9900
(5,1,8,'Ciscenje zuba ultrazvukom.',6500.00,6500.00),
(5,2,7,'Stomatoloski pregled.',2200.00,2200.00),
(5,3,9,'Kontrolni pregled posle tretmana.',2300.00,2300.00),

-- Termin 6: 2500 + 3500 + 2000 = 8000; 20% popust = 6400
(6,1,1,'Opsti pregled pre mikrocipovanja.',2500.00,2500.00),
(6,2,10,'Ugradnja mikrocipa.',3500.00,3500.00),
(6,3,3,'Ciscenje usiju posle pregleda.',2000.00,2000.00);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*
SQLyog Enterprise - MySQL GUI v8.18 
MySQL - 5.0.32-Debian_7etch11-log : Database - averni
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`averni` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `averni`;

/*Table structure for table `prices` */

DROP TABLE IF EXISTS `prices`;

CREATE TABLE `prices` (
  `id` int(11) NOT NULL auto_increment,
  `symbolId` int(11) NOT NULL,
  `date` date NOT NULL,
  `open` double NOT NULL,
  `high` double NOT NULL,
  `low` double NOT NULL,
  `close` double NOT NULL,
  `volume` double NOT NULL,
  `period` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `Symbol_index` (`symbolId`,`date`),
  CONSTRAINT `FK_prices` FOREIGN KEY (`symbolId`) REFERENCES `symbols` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `settlements` */

DROP TABLE IF EXISTS `settlements`;

CREATE TABLE `settlements` (
  `SPORTS_ID` varchar(255) default NULL,
  `EVENT_ID` varchar(255) default NULL,
  `SETTLED_DATE` varchar(255) default NULL,
  `FULL_DESCRIPTION` varchar(255) default NULL,
  `SCHEDULED_OFF` varchar(255) default NULL,
  `EVENT` varchar(255) default NULL,
  `DT ACTUAL_OFF` varchar(255) default NULL,
  `SELECTION_ID` varchar(255) default NULL,
  `SELECTION` varchar(255) default NULL,
  `ODDS` varchar(255) default NULL,
  `NUMBER_BETS` varchar(255) default NULL,
  `VOLUME_MATCHED` varchar(255) default NULL,
  `LATEST_TAKEN` varchar(255) default NULL,
  `FIRST_TAKEN` varchar(255) default NULL,
  `WIN_FLAG` varchar(255) default NULL,
  `IN_PLAY` varchar(255) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `signals` */

DROP TABLE IF EXISTS `signals`;

CREATE TABLE `signals` (
  `id` int(11) NOT NULL auto_increment,
  `symbolId` int(11) NOT NULL,
  `signalType` enum('Upside Breakout','Momentum') default NULL,
  `buyDate` date NOT NULL,
  `buyPrice` double NOT NULL,
  `sellDate` date default NULL,
  `sellPrice` double default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=509 DEFAULT CHARSET=latin1;

/*Table structure for table `symbols` */

DROP TABLE IF EXISTS `symbols`;

CREATE TABLE `symbols` (
  `id` int(11) NOT NULL auto_increment,
  `symbol` varchar(10) NOT NULL,
  `description` varchar(100) NOT NULL,
  `marketType` varchar(25) NOT NULL,
  `exchange` varchar(25) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `symbol_index` (`symbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

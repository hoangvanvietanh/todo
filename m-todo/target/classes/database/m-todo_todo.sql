-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: m-todo
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `todo`
--

DROP TABLE IF EXISTS `todo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `todo` (
  `idtodo` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `started_at` datetime DEFAULT NULL,
  `ended_at` datetime DEFAULT NULL,
  `iduser` int(11) DEFAULT NULL,
  PRIMARY KEY (`idtodo`),
  KEY `fk_todo_1_idx` (`iduser`),
  CONSTRAINT `fk_todo_1` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `todo`
--

LOCK TABLES `todo` WRITE;
/*!40000 ALTER TABLE `todo` DISABLE KEYS */;
INSERT INTO `todo` VALUES (156,'Messi','s','New2','2018-09-14',NULL,'2018-09-14',NULL,NULL,NULL),(164,'viet1','','Canceled','2018-09-21',NULL,'2018-09-21',NULL,NULL,1),(165,'viet2','','Done','2018-09-21',NULL,'2018-09-22','2018-09-26 18:54:59','2018-09-28 12:36:11',1),(166,'viet3','','Done','2018-09-21',NULL,'2018-09-19','2018-09-21 15:47:56','2018-09-26 18:54:52',1),(167,'viet4','','Done','2018-09-21',NULL,'2018-09-22','2018-09-26 18:55:35','2018-09-28 12:36:26',1),(168,'viet5','','Done','2018-09-21',NULL,'2018-09-22','2018-09-26 18:55:03','2018-09-26 18:55:13',1),(169,'viet6','','Done','2018-09-21',NULL,'2018-09-21','2018-09-21 15:47:58','2018-09-21 15:47:59',1),(171,'viet8','','Done','2018-09-21',NULL,'2018-09-18','2018-09-21 15:48:03','2018-09-28 16:37:05',1),(174,'viet11','','Done','2018-09-21',NULL,'2018-09-22','2018-09-28 16:35:21','2018-09-28 16:37:08',1),(175,'viet12','','Canceled','2018-09-21',NULL,'2018-09-21',NULL,NULL,1),(177,'viet14','','In-progress','2018-09-21',NULL,'2018-09-26','2018-09-28 16:37:13',NULL,1),(178,'viet15','','Canceled','2018-09-21',NULL,'2018-09-22',NULL,NULL,1),(179,'viet16','','In-progress','2018-09-21',NULL,'2018-09-21','2018-09-28 16:37:15',NULL,1),(181,'viet18','','In-progress','2018-09-21',NULL,'2018-09-15','2018-09-28 16:37:17',NULL,1),(183,'viet20','','New2','2018-09-21',NULL,'2018-09-20',NULL,NULL,1);
/*!40000 ALTER TABLE `todo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-29 22:22:55

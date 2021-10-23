-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: speedtest
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `speedtest_cidr`
--

DROP TABLE IF EXISTS `speedtest_cidr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `speedtest_cidr` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cidr` varchar(45) DEFAULT NULL,
  `position` varchar(128) DEFAULT NULL,
  `accessMethod` varchar(45) DEFAULT NULL,
  `isp` varchar(128) DEFAULT NULL,
  `index` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index` (`index`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speedtest_cidr`
--

LOCK TABLES `speedtest_cidr` WRITE;
/*!40000 ALTER TABLE `speedtest_cidr` DISABLE KEYS */;
INSERT INTO `speedtest_cidr` VALUES (1,'::1/128',NULL,NULL,'localhost IPv6 access',0),(2,'fe80::/10',NULL,NULL,'link-local IPv6 access',0),(3,'127.0.0.0/8',NULL,NULL,'localhost IPv4 access',0),(4,'10.0.0.0/8',NULL,NULL,'private IPv4 access',0),(5,'172.16.0.0/12',NULL,NULL,'private IPv4 access',0),(6,'192.168.0.0/16',NULL,NULL,'private IPv4 access',0),(7,'169.254.0.0/16',NULL,NULL,'link-local IPv4 access',0);
/*!40000 ALTER TABLE `speedtest_cidr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speedtest_history`
--

DROP TABLE IF EXISTS `speedtest_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `speedtest_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ip` varchar(64) DEFAULT NULL,
  `ua` varchar(256) DEFAULT NULL,
  `dl` float DEFAULT NULL,
  `ul` float DEFAULT NULL,
  `ping` float DEFAULT NULL,
  `jitter` float DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `testpointid` int DEFAULT NULL,
  `extraAttribute` text,
  PRIMARY KEY (`id`),
  KEY `time_userid_index` (`userid`, `time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speedtest_history`
--

LOCK TABLES `speedtest_history` WRITE;
/*!40000 ALTER TABLE `speedtest_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `speedtest_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speedtest_testpoint`
--

DROP TABLE IF EXISTS `speedtest_testpoint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `speedtest_testpoint` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `server` varchar(256) DEFAULT NULL,
  `port` int DEFAULT '80',
  `status` tinyint DEFAULT '1',
  `type` tinyint DEFAULT '4',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speedtest_testpoint`
--

LOCK TABLES `speedtest_testpoint` WRITE;
/*!40000 ALTER TABLE `speedtest_testpoint` DISABLE KEYS */;
/*!40000 ALTER TABLE `speedtest_testpoint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speedtest_user`
--

DROP TABLE IF EXISTS `speedtest_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `speedtest_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(16) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `extraAttribute` text,
  PRIMARY KEY (`id`),
  KEY `unique_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speedtest_user`
--

LOCK TABLES `speedtest_user` WRITE;
/*!40000 ALTER TABLE `speedtest_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `speedtest_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-13 14:15:43

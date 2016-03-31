-- MySQL dump 10.13  Distrib 5.7.11, for osx10.11 (x86_64)
--
-- Host: localhost    Database: Shopz
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `user` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `address` varchar(255) NOT NULL DEFAULT '',
  `city` varchar(255) NOT NULL DEFAULT '',
  `postal` varchar(255) NOT NULL DEFAULT '',
  `country` varchar(255) NOT NULL DEFAULT '',
  KEY `user` (`user`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`user`) REFERENCES `user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES ('user1','Alice','123 road st','toronto','00','canada');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `itemID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `manufacturer` varchar(255) NOT NULL DEFAULT '',
  `description` varchar(8000) NOT NULL DEFAULT '',
  `category` varchar(255) NOT NULL DEFAULT '',
  `price` float NOT NULL DEFAULT '-1',
  PRIMARY KEY (`itemID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'iphone 6','apple','hype','phone',1000);
INSERT INTO `item` VALUES (2,'nexus 6','nexus','motorolla','phone',600);
INSERT INTO `item` VALUES (3,'itemName','Manufacturer','this is a watch. ','watch',2100.99);
INSERT INTO `item` VALUES (4,'itemName','Manufacturer','its a car. needs new tires...','car',21000);
INSERT INTO `item` VALUES (5,'optima','kia','its a car. needs new tires','car',21000);
INSERT INTO `item` VALUES (6,'Macbook Pro','apple','A really expensive laptop. ','laptop',1500);
INSERT INTO `item` VALUES (7,'XPS 13','Dell','Another Really expensive Laptop','laptop',1500);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentinfo`
--

DROP TABLE IF EXISTS `paymentinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentinfo` (
  `username` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `cardNumber` int(11) NOT NULL DEFAULT '-1',
  `expDate` varchar(255) NOT NULL DEFAULT '',
  `ccv` int(11) NOT NULL DEFAULT '-1',
  `cardType` varchar(255) NOT NULL DEFAULT '',
  KEY `username` (`username`),
  CONSTRAINT `paymentinfo_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentinfo`
--

LOCK TABLES `paymentinfo` WRITE;
/*!40000 ALTER TABLE `paymentinfo` DISABLE KEYS */;
INSERT INTO `paymentinfo` VALUES ('user1','Charlie',12345,'8/16',426,'VISA');
/*!40000 ALTER TABLE `paymentinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `reviewID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '',
  `publishDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `stars` int(11) NOT NULL DEFAULT '-1',
  `description` varchar(8000) NOT NULL DEFAULT '',
  `item` int(11) DEFAULT NULL,
  PRIMARY KEY (`reviewID`),
  KEY `item` (`item`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`item`) REFERENCES `item` (`itemID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,'hkjhkjhlkjhlk','2016-03-30 18:32:49',0,'lkjhlkjhlkjh',1);
INSERT INTO `review` VALUES (2,'The Macbook Pro is a really good laptop','2016-03-30 20:13:28',3,'I really like this laptop',6);
INSERT INTO `review` VALUES (3,'New review','2016-03-30 21:08:21',1,'this is a description ',1);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcart`
--

DROP TABLE IF EXISTS `shoppingcart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingcart` (
  `Username` varchar(255) DEFAULT NULL,
  `Item` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL DEFAULT '-1',
  KEY `Username` (`Username`),
  CONSTRAINT `shoppingcart_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcart`
--

LOCK TABLES `shoppingcart` WRITE;
/*!40000 ALTER TABLE `shoppingcart` DISABLE KEYS */;
INSERT INTO `shoppingcart` VALUES ('user1',6,1);
/*!40000 ALTER TABLE `shoppingcart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `isAdmin` bit(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('user1','Alice','bam','');
INSERT INTO `user` VALUES ('user2','cat','meow','\0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-30 21:19:35

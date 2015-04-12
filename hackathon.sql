CREATE DATABASE  IF NOT EXISTS `hackathon` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hackathon`;
-- MySQL dump 10.13  Distrib 5.6.19, for Win32 (x86)
--
-- Host: localhost    Database: hackathon
-- ------------------------------------------------------
-- Server version	5.6.21-enterprise-commercial-advanced-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_Ncomplete_daresOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES ('Kurt','123123','Kurt Neil','Aquino','kurt.aquino@gmail.com'),('Seaver','123123','Matthew Seaver','Choy','seaver.choy@gmail.com'),('Tristan','123123','Tristan','Milan','tristan.milan@gmail.com');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amazing_race`
--

DROP TABLE IF EXISTS `amazing_race`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amazing_race` (
  `race_id` int(10) unsigned NOT NULL,
  `dare_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`race_id`,`dare_id`),
  KEY `amazingDare_idx` (`dare_id`),
  CONSTRAINT `amazingDare` FOREIGN KEY (`dare_id`) REFERENCES `challenge_dares` (`dare_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `amazingRace` FOREIGN KEY (`race_id`) REFERENCES `challenge_race` (`race_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amazing_race`
--

LOCK TABLES `amazing_race` WRITE;
/*!40000 ALTER TABLE `amazing_race` DISABLE KEYS */;
INSERT INTO `amazing_race` VALUES (1,1);
/*!40000 ALTER TABLE `amazing_race` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge_dares`
--

DROP TABLE IF EXISTS `challenge_dares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `challenge_dares` (
  `dare_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `loc_id` int(10) unsigned NOT NULL,
  `dare_name` varchar(100) NOT NULL,
  `dare_desc` tinytext NOT NULL,
  `date_posted` datetime NOT NULL,
  PRIMARY KEY (`dare_id`,`username`,`loc_id`),
  UNIQUE KEY `dare_id_UNIQUE` (`dare_id`),
  KEY `dareLocation_idx` (`loc_id`),
  KEY `dareUser_idx` (`username`),
  CONSTRAINT `dareLocation` FOREIGN KEY (`loc_id`) REFERENCES `location` (`loc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `dareUser` FOREIGN KEY (`username`) REFERENCES `accounts` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge_dares`
--

LOCK TABLES `challenge_dares` WRITE;
/*!40000 ALTER TABLE `challenge_dares` DISABLE KEYS */;
INSERT INTO `challenge_dares` VALUES (1,'Kurt',5,'Hot Air Balloon Challenge','Ride up! Never fall down!','2015-04-11 14:32:32'),(2,'Seaver',1,'What the Hack Hackathon','Code all day, all night for travelling!','2015-04-11 07:00:00'),(3,'Tristan',4,'Brave the Night!','Can you camp in our safari?','2015-03-23 18:00:00'),(4,'Kurt',3,'Space Shuttle EXTREME','Ride 5 times consecutively!','2015-01-01 12:00:00'),(5,'Tristan',2,'HACKATHING','Code all night! Create an app for us!','2015-04-13 08:00:00'),(10,'Kurt',1,'null','null','2015-04-12 01:33:49'),(11,'Kurt',1,'null','null','2015-04-12 01:34:13');
/*!40000 ALTER TABLE `challenge_dares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge_race`
--

DROP TABLE IF EXISTS `challenge_race`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `challenge_race` (
  `race_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `race_name` varchar(100) NOT NULL,
  `race_desc` tinytext NOT NULL,
  `date_posted` datetime NOT NULL,
  PRIMARY KEY (`race_id`),
  UNIQUE KEY `race_id_UNIQUE` (`race_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge_race`
--

LOCK TABLES `challenge_race` WRITE;
/*!40000 ALTER TABLE `challenge_race` DISABLE KEYS */;
INSERT INTO `challenge_race` VALUES (1,'Tristan','Don\'t sleep; code!','2 straight hackathons','2015-04-15 05:00:00');
/*!40000 ALTER TABLE `challenge_race` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complete_dares`
--

DROP TABLE IF EXISTS `complete_dares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complete_dares` (
  `player_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dare_id` int(10) unsigned NOT NULL,
  `username` varchar(45) NOT NULL,
  `completed` tinyint(1) NOT NULL,
  `approved` tinyint(1),
  `file_name` varchar(100) NOT NULL,
  `date_completed` datetime NOT NULL,
  `date_approved` datetime,
  PRIMARY KEY (`player_id`,`dare_id`,`username`),
  UNIQUE KEY `contestant_ID_UNIQUE` (`player_id`),
  KEY `completeDare_idx` (`dare_id`),
  KEY `completeUser_idx` (`username`),
  CONSTRAINT `completeDare` FOREIGN KEY (`dare_id`) REFERENCES `challenge_dares` (`dare_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `darePlayer` FOREIGN KEY (`username`) REFERENCES `accounts` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complete_dares`
--

LOCK TABLES `complete_dares` WRITE;
/*!40000 ALTER TABLE `complete_dares` DISABLE KEYS */;
/*!40000 ALTER TABLE `complete_dares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complete_races`
--

DROP TABLE IF EXISTS `complete_races`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complete_races` (
  `player_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `race_id` int(10) unsigned NOT NULL,
  `username` varchar(45) NOT NULL,
  `approved_dares` int(11) NOT NULL,
  `date_start` datetime NOT NULL,
  `date_finish` datetime NOT NULL,
  `completed` tinyint(1) NOT NULL,
  `approved` tinyint(1),
  `date_completed` datetime NOT NULL,
  `date_approved` datetime NOT NULL,
  PRIMARY KEY (`player_id`,`race_id`,`username`),
  UNIQUE KEY `player_ID_UNIQUE` (`player_id`),
  KEY `completeRace_idx` (`race_id`),
  KEY `completeRaceUser_idx` (`username`),
  CONSTRAINT `completeRace` FOREIGN KEY (`race_id`) REFERENCES `challenge_race` (`race_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `racePlayer` FOREIGN KEY (`username`) REFERENCES `accounts` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complete_races`
--

LOCK TABLES `complete_races` WRITE;
/*!40000 ALTER TABLE `complete_races` DISABLE KEYS */;
/*!40000 ALTER TABLE `complete_races` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dare_photos`
--

DROP TABLE IF EXISTS `dare_photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dare_photos` (
  `dare_id` int(10) unsigned NOT NULL,
  `file_name` varchar(100) NOT NULL,
  PRIMARY KEY (`dare_id`),
  CONSTRAINT `photoDare` FOREIGN KEY (`dare_id`) REFERENCES `challenge_dares` (`dare_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dare_photos`
--

LOCK TABLES `dare_photos` WRITE;
/*!40000 ALTER TABLE `dare_photos` DISABLE KEYS */;
INSERT INTO `dare_photos` VALUES (10,'Laguna.JPG'),(11,'2X2.jpg');
/*!40000 ALTER TABLE `dare_photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_packs`
--

DROP TABLE IF EXISTS `favorite_packs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite_packs` (
  `username` varchar(45) NOT NULL,
  `pack_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`username`,`pack_id`),
  KEY `list_name_idx` (`pack_id`),
  CONSTRAINT `favoritePack` FOREIGN KEY (`pack_id`) REFERENCES `package` (`pack_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `favoriteUser` FOREIGN KEY (`username`) REFERENCES `accounts` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_packs`
--

LOCK TABLES `favorite_packs` WRITE;
/*!40000 ALTER TABLE `favorite_packs` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorite_packs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `loc_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `loc_name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `loc_desc` tinytext NOT NULL,
  PRIMARY KEY (`loc_id`),
  UNIQUE KEY `list_item_id_UNIQUE` (`loc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'College of St. Benilde','Vito Cruz corner Taft Avenue','The best college!!'),(2,'De La Salle University','Along Taft Avenue','The best college!!'),(3,'Enchanted Kingdom','Sta. Rosa, Laguna','The best amusement park!!'),(4,'Zoobic Safari','Subic, Zambales','Freeport Zone'),(5,'Hot Air Balloon!','Clark, Pampanga','BALLOOOOOONS');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_pack`
--

DROP TABLE IF EXISTS `location_pack`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location_pack` (
  `loc_id` int(10) unsigned NOT NULL,
  `pack_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`loc_id`,`pack_id`),
  KEY `packID_idx` (`pack_id`),
  CONSTRAINT `packID` FOREIGN KEY (`pack_id`) REFERENCES `package` (`pack_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `packLocation` FOREIGN KEY (`loc_id`) REFERENCES `location` (`loc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_pack`
--

LOCK TABLES `location_pack` WRITE;
/*!40000 ALTER TABLE `location_pack` DISABLE KEYS */;
INSERT INTO `location_pack` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `location_pack` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_photos`
--

DROP TABLE IF EXISTS `location_photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location_photos` (
  `loc_id` int(10) unsigned NOT NULL,
  `file_name` varchar(100) NOT NULL,
  PRIMARY KEY (`loc_id`,`file_name`),
  KEY `fk_list_item_photos_list_items1_idx` (`loc_id`),
  CONSTRAINT `photoLocation` FOREIGN KEY (`loc_id`) REFERENCES `location` (`loc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_photos`
--

LOCK TABLES `location_photos` WRITE;
/*!40000 ALTER TABLE `location_photos` DISABLE KEYS */;
/*!40000 ALTER TABLE `location_photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package`
--

DROP TABLE IF EXISTS `package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `package` (
  `pack_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pack_name` varchar(45) NOT NULL,
  `pack_desc` tinytext NOT NULL,
  PRIMARY KEY (`pack_id`),
  UNIQUE KEY `list_id_UNIQUE` (`pack_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package`
--

LOCK TABLES `package` WRITE;
/*!40000 ALTER TABLE `package` DISABLE KEYS */;
INSERT INTO `package` VALUES (1,'Universities in Taft','some colleges around Taft Avenue');
/*!40000 ALTER TABLE `package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `tag_name` varchar(45) NOT NULL,
  PRIMARY KEY (`tag_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES ('adrenaline overload'),('animals'),('beach'),('dangerous'),('fun'),('hack'),('historical'),('leisure'),('overnight'),('recreation'),('relaxation'),('school'),('thriller');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags_dare`
--

DROP TABLE IF EXISTS `tags_dare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags_dare` (
  `dare_id` int(10) unsigned NOT NULL,
  `tag_name` varchar(45) NOT NULL,
  PRIMARY KEY (`dare_id`,`tag_name`),
  KEY `tagName2_idx` (`tag_name`),
  CONSTRAINT `tagDare` FOREIGN KEY (`dare_id`) REFERENCES `challenge_dares` (`dare_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tagName2` FOREIGN KEY (`tag_name`) REFERENCES `tags` (`tag_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags_dare`
--

LOCK TABLES `tags_dare` WRITE;
/*!40000 ALTER TABLE `tags_dare` DISABLE KEYS */;
INSERT INTO `tags_dare` VALUES (4,'adrenaline overload'),(1,'dangerous'),(3,'dangerous'),(2,'overnight'),(3,'overnight'),(5,'overnight'),(1,'thriller'),(3,'thriller');
/*!40000 ALTER TABLE `tags_dare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags_location`
--

DROP TABLE IF EXISTS `tags_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags_location` (
  `loc_id` int(10) unsigned NOT NULL,
  `tag_name` varchar(45) NOT NULL,
  PRIMARY KEY (`loc_id`,`tag_name`),
  KEY `fk_lists_has_tags_tags1_idx` (`tag_name`),
  CONSTRAINT `tagLocation` FOREIGN KEY (`loc_id`) REFERENCES `location` (`loc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tagName1` FOREIGN KEY (`tag_name`) REFERENCES `tags` (`tag_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags_location`
--

LOCK TABLES `tags_location` WRITE;
/*!40000 ALTER TABLE `tags_location` DISABLE KEYS */;
INSERT INTO `tags_location` VALUES (1,'historical'),(2,'historical'),(1,'school'),(2,'school');
/*!40000 ALTER TABLE `tags_location` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-12  6:19:22

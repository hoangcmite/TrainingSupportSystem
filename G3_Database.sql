CREATE DATABASE  IF NOT EXISTS `MyDBG3` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `MyDBG3`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: database-2.clu4n8uk1rfj.us-east-1.rds.amazonaws.com    Database: MyDBG3
-- ------------------------------------------------------
-- Server version	8.0.30

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment` (
  `ass_id` int NOT NULL AUTO_INCREMENT,
  `subject_id` int NOT NULL,
  `title` varchar(150) NOT NULL,
  `ass_boby` varchar(450) NOT NULL,
  `eval_weight` int NOT NULL,
  `is_team_work` bit(1) NOT NULL,
  `is_ongoing` bit(1) NOT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`ass_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (1,3,'ass1','ass_boby2',1,_binary '',_binary '',_binary ''),(2,2,'ass2','ass_boby2',2,_binary '',_binary '',_binary ''),(3,2,'ass1','ass_boby1',1,_binary '\0',_binary '',_binary ''),(4,2,'ass3','ass_boby3',3,_binary '',_binary '\0',_binary '\0'),(5,3,'abc','abc',0,_binary '\0',_binary '\0',_binary ''),(6,4,'hello ass1','hello world1',1,_binary '',_binary '\0',_binary '\0'),(7,2,'learning online ass','learning',0,_binary '\0',_binary '\0',_binary '\0'),(8,6,'clean water ass','clean water',0,_binary '\0',_binary '\0',_binary ''),(9,2,'2','2',0,_binary '',_binary '',_binary ''),(10,5,'1','1',1,_binary '',_binary '',_binary '');
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `aid` int NOT NULL AUTO_INCREMENT,
  `class_id` int DEFAULT NULL,
  `trainee_id` int DEFAULT NULL,
  `schedule_id` int DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`aid`),
  KEY `fk_class_id_idx` (`class_id`),
  KEY `fk_schedule_id_idx` (`schedule_id`),
  KEY `fk_trainee_id_idx` (`trainee_id`),
  CONSTRAINT `fk_class_id` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`),
  CONSTRAINT `fk_schedule_id` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`number`),
  CONSTRAINT `fk_trainee_id` FOREIGN KEY (`trainee_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `class_id` int NOT NULL AUTO_INCREMENT,
  `subject_id` int NOT NULL,
  `class_code` varchar(150) NOT NULL,
  `trainer_id` int NOT NULL,
  `supporter_id` int NOT NULL,
  `term_id` int NOT NULL,
  `status` bit(1) NOT NULL,
  `description` varchar(150) NOT NULL,
  PRIMARY KEY (`class_id`),
  KEY `fk_class_setting_idx` (`term_id`),
  KEY `fk_class_subject_idx` (`subject_id`),
  KEY `fk_class_user_trainer_idx` (`trainer_id`),
  KEY `fk_class_user_support_idx` (`supporter_id`),
  CONSTRAINT `fk_class_setting` FOREIGN KEY (`term_id`) REFERENCES `setting` (`setting_id`),
  CONSTRAINT `fk_class_subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`),
  CONSTRAINT `fk_class_supporteer` FOREIGN KEY (`supporter_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `fk_class_trainer` FOREIGN KEY (`trainer_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,2,'SE1632',60,95,83,_binary '',''),(2,2,'SE1633',60,95,83,_binary '',''),(3,2,'SE1634',60,48,84,_binary '',''),(4,3,'SE1628',60,48,84,_binary '','');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_setting`
--

DROP TABLE IF EXISTS `class_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_setting` (
  `setting_id` int NOT NULL AUTO_INCREMENT,
  `type_id` int NOT NULL,
  `setting_title` varchar(150) NOT NULL,
  `setting_value` varchar(150) DEFAULT NULL,
  `display_order` int NOT NULL,
  `class_id` int NOT NULL,
  `status` tinyint NOT NULL,
  `description` varchar(150) NOT NULL,
  PRIMARY KEY (`setting_id`),
  KEY `fk_class_setting_setting_idx` (`type_id`),
  KEY `fk_class_setting_class_idx` (`class_id`),
  CONSTRAINT `fk_class_setting_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`),
  CONSTRAINT `fk_class_setting_setting` FOREIGN KEY (`type_id`) REFERENCES `setting` (`setting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_setting`
--

LOCK TABLES `class_setting` WRITE;
/*!40000 ALTER TABLE `class_setting` DISABLE KEYS */;
INSERT INTO `class_setting` VALUES (1,30,'Class Setting 1','1',1,1,0,''),(2,29,'Class Setting List 2 ','2',1,1,1,''),(3,29,'Class Setting List 3','3',1,1,0,''),(4,29,'Class Setting List 4','4',1,1,0,''),(5,29,'Class Setting List 5','5',1,1,0,''),(6,29,'Class Setting List 6','6',1,1,1,''),(7,30,'Class Setting Details 1','6',2,2,0,''),(8,30,'Class Setting Details 1','2',2,2,0,''),(9,30,'Class Setting Details 2','3',2,2,1,''),(10,30,'Class Setting Details 3','4',2,2,1,''),(11,30,'Class Setting Details 4','5',2,2,1,''),(12,30,'Mai Thị','6',1,2,0,''),(13,30,'Class Setting Details 6','7',2,2,1,''),(14,30,'Class Setting Details 7','8',2,2,1,'');
/*!40000 ALTER TABLE `class_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_user`
--

DROP TABLE IF EXISTS `class_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_user` (
  `class_id` int NOT NULL,
  `user_id` int NOT NULL,
  `status` bit(1) NOT NULL,
  `note` varchar(300) DEFAULT NULL,
  `dropout_date` date DEFAULT NULL,
  `ongoing_eval` float DEFAULT NULL,
  `final_eval` float DEFAULT NULL,
  `topic_eval` float DEFAULT NULL,
  PRIMARY KEY (`class_id`,`user_id`),
  KEY `fk_class_user_idx` (`user_id`),
  CONSTRAINT `fk_class_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `fk_class_user_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_user`
--

LOCK TABLES `class_user` WRITE;
/*!40000 ALTER TABLE `class_user` DISABLE KEYS */;
INSERT INTO `class_user` VALUES (1,46,_binary '',NULL,NULL,8,10,9),(1,49,_binary '',NULL,NULL,10,7,9),(1,55,_binary '',NULL,NULL,6,7,6),(1,57,_binary '',NULL,NULL,5,6,5),(1,58,_binary '',NULL,NULL,8,8,8),(1,59,_binary '\0','','2022-10-27',6,10,6),(1,60,_binary '',NULL,NULL,10,10,9),(1,106,_binary '',NULL,NULL,NULL,NULL,NULL),(1,107,_binary '',NULL,NULL,NULL,NULL,NULL),(1,108,_binary '',NULL,NULL,NULL,NULL,NULL),(1,109,_binary '',NULL,NULL,NULL,NULL,NULL),(1,110,_binary '',NULL,NULL,NULL,NULL,NULL),(1,111,_binary '',NULL,NULL,NULL,NULL,NULL),(1,112,_binary '',NULL,NULL,NULL,NULL,NULL),(1,113,_binary '',NULL,NULL,NULL,NULL,NULL),(2,58,_binary '',NULL,NULL,7,9,5),(2,60,_binary '',NULL,NULL,9,10,10),(2,93,_binary '',NULL,NULL,7,5,8),(2,96,_binary '',NULL,NULL,NULL,NULL,NULL),(2,97,_binary '',NULL,NULL,NULL,NULL,NULL),(2,98,_binary '',NULL,NULL,NULL,NULL,NULL),(2,99,_binary '',NULL,NULL,NULL,NULL,NULL),(2,100,_binary '',NULL,NULL,NULL,NULL,NULL),(2,101,_binary '',NULL,NULL,NULL,NULL,NULL),(2,102,_binary '',NULL,NULL,NULL,NULL,NULL),(2,103,_binary '',NULL,NULL,NULL,NULL,NULL),(2,104,_binary '',NULL,NULL,NULL,NULL,NULL),(2,105,_binary '',NULL,NULL,NULL,NULL,NULL),(2,114,_binary '',NULL,NULL,NULL,NULL,NULL),(2,115,_binary '',NULL,NULL,NULL,NULL,NULL),(2,116,_binary '',NULL,NULL,NULL,NULL,NULL),(2,117,_binary '',NULL,NULL,NULL,NULL,NULL),(2,118,_binary '',NULL,NULL,NULL,NULL,NULL),(2,119,_binary '',NULL,NULL,NULL,NULL,NULL),(2,120,_binary '',NULL,NULL,NULL,NULL,NULL),(3,58,_binary '',NULL,NULL,8,8,8),(3,60,_binary '\0',NULL,'2022-10-24',10,10,10);
/*!40000 ALTER TABLE `class_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `client_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `email` varchar(150) DEFAULT NULL,
  `mobile` varchar(150) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `position` varchar(150) DEFAULT NULL,
  `company` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  KEY `fk_client_user_idx` (`user_id`),
  CONSTRAINT `fk_client_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,1,NULL,NULL,NULL,NULL,NULL),(34,46,NULL,NULL,NULL,NULL,NULL),(36,48,NULL,NULL,NULL,NULL,NULL),(37,49,NULL,NULL,NULL,NULL,NULL),(43,55,NULL,NULL,NULL,NULL,NULL),(45,57,NULL,NULL,'','',''),(46,58,NULL,NULL,NULL,NULL,NULL),(73,95,NULL,NULL,NULL,NULL,NULL),(86,121,NULL,NULL,NULL,NULL,NULL),(88,122,NULL,NULL,NULL,NULL,NULL),(89,123,NULL,NULL,NULL,NULL,NULL),(90,124,NULL,NULL,NULL,NULL,NULL),(92,126,NULL,NULL,NULL,NULL,NULL),(93,127,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eval_criteria`
--

DROP TABLE IF EXISTS `eval_criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eval_criteria` (
  `criteria_id` int NOT NULL AUTO_INCREMENT,
  `ass_id` int NOT NULL,
  `milestone_id` int NOT NULL,
  `criteria_name` varchar(150) NOT NULL,
  `is_team_eval` bit(1) NOT NULL,
  `eval_weight` varchar(45) NOT NULL,
  `max_loc` int NOT NULL,
  `status` bit(1) NOT NULL,
  `description` varchar(350) NOT NULL,
  PRIMARY KEY (`criteria_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eval_criteria`
--

LOCK TABLES `eval_criteria` WRITE;
/*!40000 ALTER TABLE `eval_criteria` DISABLE KEYS */;
INSERT INTO `eval_criteria` VALUES (5,123,1,'123',_binary '','123',11,_binary '','123'),(6,1,2,'duc',_binary '\0','1000',100,_binary '','fsdas'),(7,1,1,'abc1',_binary '','11',10010,_binary '','adfgbndfagbssadfvb'),(8,2,1,'eval_criteria123',_binary '','213',12,_binary '\0','adfgbndfagbssadfvb'),(9,1,1,'abcd',_binary '','1',1111,_binary '','1'),(10,1,2,'a',_binary '','1',1,_binary '','1'),(11,1,1,'b',_binary '','',1,_binary '','1'),(12,1,1,'c',_binary '','',1,_binary '','1'),(13,1,4,'d',_binary '','',1,_binary '','1'),(14,2,4,'e',_binary '','',2,_binary '','2'),(15,4,1,'f',_binary '','3',3,_binary '','3'),(16,5,4,'g',_binary '','4',4,_binary '\0','4'),(17,1,1,'abc',_binary '','1',100,_binary '','abc'),(18,1,1,'abc1',_binary '','11',1001,_binary '','adfgbndfagbssadfvb'),(19,1,2,'eval_criteria1231',_binary '\0','2311321',13,_binary '','fsdas'),(20,1,2,'eval_criteria1231',_binary '\0','2311321',10000,_binary '','fsdas'),(21,1,2,'eval_criteria1231',_binary '\0','2311321',10000,_binary '','fsdas'),(22,1,2,'eval_criteria1231',_binary '\0','1000',1000,_binary '','fsdas'),(23,1,2,'duc1',_binary '','20',10,_binary '\0','fsdas'),(24,1,2,'duc',_binary '\0','100',10,_binary '','fsdas'),(25,1,2,'duc',_binary '','100',100,_binary '','fsdas');
/*!40000 ALTER TABLE `eval_criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `milestone`
--

DROP TABLE IF EXISTS `milestone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `milestone` (
  `milestone_id` int NOT NULL AUTO_INCREMENT,
  `ass_id` int NOT NULL,
  `class_id` int NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  `title` varchar(550) NOT NULL,
  `ass_body` varchar(450) NOT NULL,
  `description` varchar(350) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`milestone_id`),
  KEY `fk_melis_ass_idx` (`ass_id`),
  KEY `fk_miles_class_idx` (`class_id`),
  CONSTRAINT `fk_miles_ass` FOREIGN KEY (`ass_id`) REFERENCES `assignment` (`ass_id`),
  CONSTRAINT `fk_miles_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `milestone`
--

LOCK TABLES `milestone` WRITE;
/*!40000 ALTER TABLE `milestone` DISABLE KEYS */;
INSERT INTO `milestone` VALUES (1,1,1,'2022-10-10','2022-12-12','ass1','ass_boby1',NULL,_binary ''),(2,2,2,'2022-10-10','2022-12-12','ass2','ass_boby2',NULL,_binary ''),(3,3,1,'2022-10-10','2022-12-12','ass3','ass_boby3',NULL,_binary ''),(4,4,2,'2022-11-11','2022-12-12','ass4','ass_boby4',NULL,_binary '\0'),(5,5,1,'2022-10-12','2022-12-12','ass5','ass_boby5',NULL,_binary '\0');
/*!40000 ALTER TABLE `milestone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `screen_id` int NOT NULL,
  `role_id` int NOT NULL,
  `get_all_data` bit(1) DEFAULT NULL,
  `can_edit` bit(1) DEFAULT NULL,
  `can_add` bit(1) DEFAULT NULL,
  `can_delete` bit(1) DEFAULT NULL,
  PRIMARY KEY (`screen_id`,`role_id`),
  CONSTRAINT `fk_permission_setting` FOREIGN KEY (`screen_id`) REFERENCES `setting` (`setting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` VALUES (21,17,_binary '',_binary '',_binary '',_binary ''),(21,18,_binary '',_binary '\0',_binary '\0',_binary '\0'),(21,44,_binary '',_binary '\0',_binary '\0',_binary '\0'),(21,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(21,46,_binary '',_binary '\0',_binary '\0',_binary '\0'),(22,17,_binary '',_binary '',_binary '',_binary ''),(22,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(22,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(22,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(22,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(23,17,_binary '',_binary '',_binary '',_binary ''),(23,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(23,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(23,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(23,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(24,17,_binary '',_binary '',_binary '',_binary ''),(24,18,_binary '',_binary '',_binary '',_binary ''),(24,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(24,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(24,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(25,17,_binary '',_binary '',_binary '',_binary ''),(25,18,_binary '',_binary '',_binary '',_binary ''),(25,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(25,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(25,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(29,17,_binary '',_binary '',_binary '',_binary ''),(29,18,_binary '',_binary '',_binary '',_binary ''),(29,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(29,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(29,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(30,17,_binary '',_binary '',_binary '',_binary ''),(30,18,_binary '',_binary '',_binary '',_binary ''),(30,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(30,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(30,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(31,17,_binary '',_binary '',_binary '',_binary ''),(31,18,_binary '',_binary '\0',_binary '\0',_binary '\0'),(31,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(31,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(31,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(32,17,_binary '',_binary '',_binary '',_binary ''),(32,18,_binary '',_binary '',_binary '',_binary ''),(32,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(32,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(32,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(33,17,_binary '',_binary '',_binary '',_binary ''),(33,18,_binary '',_binary '\0',_binary '\0',_binary '\0'),(33,44,_binary '',_binary '\0',_binary '\0',_binary '\0'),(33,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(33,46,_binary '',_binary '\0',_binary '\0',_binary '\0'),(34,17,_binary '',_binary '',_binary '',_binary ''),(34,18,_binary '',_binary '\0',_binary '\0',_binary '\0'),(34,44,_binary '',_binary '\0',_binary '\0',_binary '\0'),(34,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(34,46,_binary '',_binary '\0',_binary '\0',_binary '\0'),(35,17,_binary '',_binary '',_binary '',_binary ''),(35,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(35,44,_binary '',_binary '',_binary '',_binary ''),(35,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(35,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(36,17,_binary '',_binary '',_binary '',_binary ''),(36,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(36,44,_binary '',_binary '',_binary '',_binary ''),(36,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(36,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(54,17,_binary '',_binary '',_binary '',_binary ''),(54,18,_binary '',_binary '\0',_binary '\0',_binary '\0'),(54,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(54,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(54,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(55,17,_binary '',_binary '',_binary '',_binary ''),(55,18,_binary '',_binary '\0',_binary '\0',_binary '\0'),(55,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(55,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(55,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(56,17,_binary '',_binary '',_binary '',_binary ''),(56,18,_binary '',_binary '\0',_binary '\0',_binary '\0'),(56,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(56,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(56,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(57,17,_binary '',_binary '',_binary '',_binary ''),(57,18,_binary '',_binary '\0',_binary '\0',_binary '\0'),(57,44,_binary '',_binary '\0',_binary '\0',_binary '\0'),(57,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(57,46,_binary '',_binary '\0',_binary '\0',_binary '\0'),(58,17,_binary '',_binary '',_binary '',_binary ''),(58,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(58,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(58,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(58,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(61,17,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(61,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(61,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(61,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(61,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(62,17,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(62,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(62,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(62,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(62,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(63,17,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(63,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(63,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(63,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(63,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(64,17,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(64,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(64,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(64,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(64,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(65,17,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(65,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(65,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(65,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(65,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(66,17,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(66,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(66,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(66,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(66,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(67,17,_binary '',_binary '',_binary '',_binary ''),(67,18,_binary '',_binary '',_binary '',_binary ''),(67,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(67,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(67,46,_binary '',_binary '\0',_binary '\0',_binary '\0'),(68,17,_binary '',_binary '',_binary '',_binary ''),(68,18,_binary '',_binary '\0',_binary '\0',_binary '\0'),(68,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(68,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(68,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(69,17,_binary '',_binary '',_binary '',_binary ''),(69,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(69,44,_binary '',_binary '',_binary '',_binary '\0'),(69,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(69,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(70,17,_binary '',_binary '',_binary '',_binary ''),(70,18,_binary '',_binary '\0',_binary '\0',_binary '\0'),(70,44,_binary '',_binary '\0',_binary '\0',_binary '\0'),(70,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(70,46,_binary '',_binary '\0',_binary '\0',_binary '\0'),(71,17,_binary '',_binary '',_binary '',_binary ''),(71,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(71,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(71,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(71,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(72,17,_binary '',_binary '',_binary '',_binary ''),(72,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(72,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(72,45,_binary '',_binary '\0',_binary '\0',_binary '\0'),(72,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(76,17,_binary '',_binary '',_binary '',_binary ''),(76,18,_binary '',_binary '',_binary '',_binary ''),(76,44,_binary '',_binary '',_binary '',_binary ''),(76,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(76,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(77,17,_binary '',_binary '',_binary '',_binary ''),(77,18,_binary '',_binary '',_binary '',_binary ''),(77,44,_binary '',_binary '',_binary '',_binary ''),(77,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(77,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(78,17,_binary '',_binary '',_binary '',_binary ''),(78,18,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(78,44,_binary '',_binary '',_binary '',_binary ''),(78,45,_binary '',_binary '',_binary '',_binary ''),(78,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(79,17,_binary '',_binary '',_binary '',_binary ''),(79,18,_binary '',_binary '',_binary '',_binary ''),(79,44,_binary '',_binary '',_binary '',_binary ''),(79,45,_binary '',_binary '',_binary '',_binary ''),(79,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(82,17,_binary '',_binary '',_binary '',_binary ''),(82,18,_binary '',_binary '',_binary '',_binary ''),(82,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(82,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(82,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(91,17,_binary '',_binary '',_binary '',_binary ''),(91,18,_binary '',_binary '',_binary '',_binary ''),(91,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(91,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(91,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(92,17,_binary '',_binary '',_binary '',_binary ''),(92,18,_binary '',_binary '',_binary '\0',_binary ''),(92,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(92,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(92,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(93,17,_binary '',_binary '',_binary '',_binary ''),(93,18,_binary '',_binary '',_binary '\0',_binary ''),(93,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(93,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(93,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(94,17,_binary '',_binary '',_binary '',_binary ''),(94,18,_binary '',_binary '',_binary '\0',_binary ''),(94,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(94,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(94,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(95,17,_binary '',_binary '',_binary '',_binary ''),(95,18,_binary '',_binary '',_binary '\0',_binary ''),(95,44,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(95,45,_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(95,46,_binary '\0',_binary '\0',_binary '\0',_binary '\0');
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule` (
  `number` int NOT NULL AUTO_INCREMENT,
  `slot` int DEFAULT NULL,
  `subjectId` int DEFAULT NULL,
  `classId` int DEFAULT NULL,
  `dayStudy` date DEFAULT NULL,
  `fromTime` varchar(100) DEFAULT NULL,
  `toTime` varchar(100) DEFAULT NULL,
  `room` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`number`),
  KEY `fk_class_idx` (`classId`),
  KEY `fk_subject_idx` (`subjectId`),
  CONSTRAINT `fk_class` FOREIGN KEY (`classId`) REFERENCES `class` (`class_id`),
  CONSTRAINT `fk_subject` FOREIGN KEY (`subjectId`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,10,4,1,'2022-11-11','10:40','11:40','DE-01'),(2,1,2,1,'2022-11-02','03:00 PM','05:00 PM','DE-02'),(3,1,2,1,'2022-11-05','01:00 PM','03:00 PM','DE-03'),(4,6,5,2,'2022-11-11','12:30 PM','02:00 PM','DE-01'),(5,8,8,2,'2022-11-19','07:30 AM','09:00 AM','DE-01'),(6,6,5,1,'2022-11-09','01:30 PM','03:00 PM','DE-03'),(7,7,10,2,'2022-11-12','07:30 AM','09:00 AM','DE-01'),(8,1,4,3,'2022-11-11','07:30 AM','09:00 AM','DE-01');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setting` (
  `setting_id` int NOT NULL AUTO_INCREMENT,
  `type_id` int DEFAULT NULL,
  `setting_title` varchar(150) NOT NULL,
  `setting_value` varchar(150) DEFAULT NULL,
  `display_order` varchar(150) NOT NULL,
  `status` tinyint NOT NULL,
  `description` varchar(150) NOT NULL,
  PRIMARY KEY (`setting_id`),
  UNIQUE KEY `setting_value_UNIQUE` (`setting_value`),
  KEY `fk_setting_setting1_idx` (`type_id`),
  CONSTRAINT `fk_setting_setting` FOREIGN KEY (`type_id`) REFERENCES `setting` (`setting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES (1,NULL,'User Role','User Role','1',1,''),(2,NULL,'Web Contact Category','Web Contact Category','2',0,''),(3,NULL,'Class Setting Type','Class Setting Type','3',0,''),(4,NULL,'Subject Setting Type','Subject Setting Type','4',0,''),(5,NULL,'System Screen','System Screen','5',1,''),(17,1,'Admin','Administator','1',1,' '),(18,1,'Manager','Manager','1',1,' '),(19,2,'Email','11212','2',0,''),(20,2,'Phone','32312312','2',1,''),(21,5,'Dashboard','/WebApplication2/Dashboard','1',2,' '),(22,5,'Setting List','/WebApplication2/admin/setting','2',2,' '),(23,5,'Setting Detail','dfdsfsaaasdasdasdads','3',2,''),(24,5,'User List','/WebApplication2/admin/UserList','4',2,''),(25,5,'User Detail','/WebApplication2/UserDetails','5',2,''),(29,5,'Class Setting List','/WebApplication2/admin/ClassSetting','1',2,''),(30,5,'Class Setting Detail','123','2',2,''),(31,5,'Subject Setting List','/WebApplication2/SSettingList','1',2,''),(32,5,'Subject Setting Detail','/WebApplication2/SubjectSettingDetails','2',2,''),(33,5,'Test','/WebApplication2/view/manager/newjsp.jsp','3',0,''),(34,5,'Permission Screen','/WebApplication2/Permission','3',2,''),(35,5,'WebContact','/WebApplication2/WebContact','3',2,''),(36,5,'WebContact Detail','/WebApplication2/WebContactDetail','3',2,''),(44,1,'Supportter','support','1',1,' '),(45,1,'Trainer','trainer','2',1,' '),(46,1,'Trainee','trainee','1',1,' '),(53,5,'Change password','/WebApplication2/ChangePassword','1',1,' '),(54,5,'Search List','/WebApplication2/Search','2',2,' '),(55,5,'Subject Detail','/WebApplication2/Details','3',2,' '),(56,5,'Subject List','/WebApplication2/SubjectList','4',2,' '),(57,5,'Home page','/WebApplication2/Home.jsp','5',1,' '),(58,5,'Login page','/WebApplication2/Login','5',1,' '),(59,5,'error reset pass page','/WebApplication2/errorresetpass','4',1,''),(60,5,'forgot pass page','/WebApplication2/forgotpass','3',1,''),(61,5,'login true','/WebApplication2/logintrue','2',1,''),(62,5,'mail change pass','/WebApplication2/CheckExpire','1',1,' '),(63,5,'register page','/WebApplication2/register','1',1,' '),(64,5,'temp page','/WebApplication2/mailchangepass','2',1,' '),(65,5,'userprofile ','/WebApplication2/UserProfile','3',1,' '),(66,5,'logout','/WebApplication2/Logout','1',1,' '),(67,5,'Class List','/WebApplication2/ClassList','1',2,' '),(68,5,'Assignment List','/WebApplication2/manager/assignment','1',2,' '),(69,5,'Trainee List','/WebApplication2/supporter/ClassUser','1',2,''),(70,5,'Team List','/WebApplication2/TeamList','1',2,' '),(71,5,'Class Eval-Criteria List','/WebApplication2/Trainer/ClassEvalList','1',2,''),(72,5,'Class Eval-Criteria List Detail','/WebApplication2/Trainer/EvalDetail','1',2,''),(73,5,'Update Eval Status','/WebApplication2/Trainer/UpdateEvalStatus','1',1,''),(74,5,'Edit Eval','/WebApplication2/Trainer/EditEval','1',1,''),(75,5,'Clone Eval','/WebApplication2/Trainer/CloneEval','1',1,''),(76,5,'Schedule List','/WebApplication2/ScheduleList','1',2,' '),(77,5,'Schedule Details','/WebApplication2/ScheduleDetails','1',2,' '),(78,5,'Attendance Tracking','/WebApplication2/AttendanceTracking','1',2,' '),(79,5,'Add Schedule','/WebApplication2/AddSchedule','1',2,' '),(80,4,'Subject Complexity','ewqewqewqe','1',0,''),(81,4,'Subject Quality','ứadasd','1',0,''),(82,5,'Add Subject Setting','/WebApplication2/AddSubjectSetting','1',2,' '),(83,4,'Fall 2022','qưewqeqwesadsa','1',0,''),(84,4,'Spring 2023','qưeqweqeqeqwe','1',0,''),(85,1,'1','1','1',0,'1'),(86,1,'2','6','6',0,''),(89,1,'2','xxxx','1',0,'2'),(90,1,'a','aaaa','1',0,''),(91,5,'Add Class','/WebApplication2/AddClass','1',1,' '),(92,5,'Eval-Criteria List','/WebApplication2/admin/EvalList','1',2,''),(93,5,'Eval-Criteria Details','/WebApplication2/admin/EvalDetail','1',2,''),(94,5,'Edit Eval','/WebApplication2/admin/EditEval','1',1,''),(95,5,'Add Eval','/WebApplication2/admin/AddEval','1',1,'');
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `subject_code` varchar(150) NOT NULL,
  `subject_name` varchar(150) NOT NULL,
  `manager_id` int NOT NULL,
  `expect_id` int NOT NULL,
  `status` bit(1) NOT NULL,
  `body` varchar(150) NOT NULL,
  PRIMARY KEY (`subject_id`),
  KEY `fk_subject_user_idx` (`manager_id`),
  KEY `fk_subject_user2_idx` (`expect_id`),
  CONSTRAINT `fk_subject_user` FOREIGN KEY (`manager_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `fk_subject_user2` FOREIGN KEY (`expect_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (2,'PRJ321','Java web',57,55,_binary '\0','demo'),(3,'SWP','Project team ',60,55,_binary '\0',''),(4,'OJT','on the job trainning',60,58,_binary '\0','demo'),(5,'MAS291','Statistics',49,55,_binary '\0',''),(6,'VOV001','Vovinam',115,119,_binary '','let\'s try some physical'),(7,'VOV002','Vovinam 2',118,119,_binary '\0',''),(8,'JPD113','Japanese',60,93,_binary '',''),(9,'JPD123','Japanese 2',60,93,_binary '\0',''),(10,'JPD133','Japanese 3',60,93,_binary '\0','');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_setting`
--

DROP TABLE IF EXISTS `subject_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_setting` (
  `setting_id` int NOT NULL AUTO_INCREMENT,
  `type_id` int NOT NULL,
  `setting_title` varchar(150) NOT NULL,
  `setting_value` varchar(150) NOT NULL,
  `display_order` varchar(150) NOT NULL,
  `status` bit(1) NOT NULL,
  `description` varchar(300) NOT NULL,
  `subject_id` int NOT NULL,
  PRIMARY KEY (`setting_id`),
  KEY `fk_subject_setting_setting_idx` (`type_id`),
  KEY `fk_csubject_setting_subject_idx` (`subject_id`),
  CONSTRAINT ` fk_subject_setting_setting` FOREIGN KEY (`type_id`) REFERENCES `setting` (`setting_id`),
  CONSTRAINT `fk_csubject_setting_subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_setting`
--

LOCK TABLES `subject_setting` WRITE;
/*!40000 ALTER TABLE `subject_setting` DISABLE KEYS */;
INSERT INTO `subject_setting` VALUES (1,80,'Quality','6','1',_binary '\0','',2),(2,81,'Complexity','9','2',_binary '','',3),(3,81,'Quality','4','3',_binary '','',4),(4,80,'Complexity','5','4',_binary '','',5),(5,80,'Quality','3','7',_binary '\0','',6),(6,81,'Quality','2','6',_binary '\0','',7),(7,80,'Complexity','10ghdf','14fgdhfgh',_binary '\0','',6),(8,80,'Quality','10','demo16',_binary '','nope',10),(9,80,'Quality','demo8','demo16',_binary '\0','',10),(10,80,'Quality','demo9','demo16',_binary '\0','',10);
/*!40000 ALTER TABLE `subject_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submit`
--

DROP TABLE IF EXISTS `submit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `submit` (
  `submit_id` int NOT NULL AUTO_INCREMENT,
  `milestone_id` int DEFAULT NULL,
  `class_id` int DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `submit_file_url` varchar(450) DEFAULT NULL,
  `submit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`submit_id`),
  KEY `fk_submit_team_idx` (`team_id`),
  KEY `fk_submit_class_user_idx` (`class_id`,`user_id`),
  KEY `fk_submit_milestone_idx` (`milestone_id`),
  CONSTRAINT `fk_submit_class_user` FOREIGN KEY (`class_id`, `user_id`) REFERENCES `class_user` (`class_id`, `user_id`),
  CONSTRAINT `fk_submit_milestone` FOREIGN KEY (`milestone_id`) REFERENCES `milestone` (`milestone_id`),
  CONSTRAINT `fk_submit_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submit`
--

LOCK TABLES `submit` WRITE;
/*!40000 ALTER TABLE `submit` DISABLE KEYS */;
INSERT INTO `submit` VALUES (1,1,1,1,NULL,1,NULL,NULL),(2,1,1,2,NULL,1,NULL,NULL),(3,1,1,4,NULL,1,NULL,NULL),(4,1,1,3,NULL,1,NULL,NULL),(5,3,1,9,NULL,1,NULL,NULL),(6,3,1,10,NULL,1,NULL,NULL),(7,5,1,11,NULL,1,NULL,NULL),(8,5,1,12,NULL,1,NULL,NULL),(9,2,2,13,NULL,1,NULL,NULL),(10,2,2,14,NULL,1,NULL,NULL),(11,2,2,15,NULL,1,NULL,NULL),(13,4,2,17,NULL,1,NULL,NULL),(14,4,2,18,NULL,1,NULL,NULL),(15,4,2,19,NULL,1,NULL,NULL);
/*!40000 ALTER TABLE `submit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `team_id` int NOT NULL AUTO_INCREMENT,
  `class_id` int NOT NULL,
  `team_code` varchar(150) NOT NULL,
  `topic_name` varchar(150) NOT NULL,
  `status` bit(1) NOT NULL,
  `description` varchar(150) NOT NULL,
  PRIMARY KEY (`team_id`),
  KEY `fk_team_class_idx` (`class_id`),
  CONSTRAINT `fk_team_class` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,1,'Group1','',_binary '',''),(2,1,'Group2','',_binary '',''),(3,1,'Group3','',_binary '',''),(4,1,'Group4','',_binary '',''),(5,2,'Group1','',_binary '',''),(6,2,'Group2','',_binary '',''),(7,2,'Group3','',_binary '',''),(8,2,'Group4','',_binary '',''),(9,1,'Group1','',_binary '',' '),(10,1,'Group2','',_binary '',' '),(11,1,'Group1','',_binary '',' '),(12,1,'Group2','',_binary '',' '),(13,2,'Group1','',_binary '',' '),(14,2,'Group2','',_binary '',' '),(15,2,'Group3','',_binary '',''),(17,2,'Group1','',_binary '',''),(18,2,'Group2','',_binary '',''),(19,2,'Group3','',_binary '','');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_member`
--

DROP TABLE IF EXISTS `team_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team_member` (
  `team_id` int NOT NULL,
  `user_id` int NOT NULL,
  `is_leader` bit(1) NOT NULL,
  `is_active` bit(1) NOT NULL,
  PRIMARY KEY (`team_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_member`
--

LOCK TABLES `team_member` WRITE;
/*!40000 ALTER TABLE `team_member` DISABLE KEYS */;
INSERT INTO `team_member` VALUES (1,58,_binary '\0',_binary ''),(1,106,_binary '',_binary ''),(1,107,_binary '\0',_binary ''),(2,109,_binary '',_binary ''),(2,110,_binary '\0',_binary ''),(3,46,_binary '',_binary ''),(3,112,_binary '\0',_binary ''),(3,113,_binary '\0',_binary ''),(4,49,_binary '',_binary ''),(4,55,_binary '\0',_binary ''),(4,57,_binary '\0',_binary ''),(5,101,_binary '',_binary ''),(5,102,_binary '\0',_binary ''),(5,103,_binary '\0',_binary ''),(6,104,_binary '\0',_binary ''),(6,105,_binary '\0',_binary ''),(6,114,_binary '',_binary ''),(7,115,_binary '\0',_binary ''),(7,116,_binary '',_binary ''),(7,117,_binary '\0',_binary ''),(8,118,_binary '',_binary ''),(8,119,_binary '\0',_binary ''),(8,120,_binary '\0',_binary ''),(9,46,_binary '',_binary ''),(9,49,_binary '\0',_binary ''),(9,55,_binary '\0',_binary ''),(9,57,_binary '\0',_binary ''),(9,111,_binary '\0',_binary ''),(10,106,_binary '',_binary ''),(10,107,_binary '\0',_binary ''),(10,108,_binary '\0',_binary ''),(10,109,_binary '\0',_binary ''),(10,110,_binary '\0',_binary ''),(10,113,_binary '\0',_binary ''),(11,106,_binary '',_binary ''),(11,107,_binary '\0',_binary ''),(11,108,_binary '\0',_binary ''),(11,109,_binary '\0',_binary ''),(11,110,_binary '\0',_binary ''),(11,111,_binary '\0',_binary ''),(12,46,_binary '',_binary ''),(12,49,_binary '\0',_binary ''),(12,55,_binary '\0',_binary ''),(12,57,_binary '\0',_binary ''),(12,112,_binary '\0',_binary ''),(12,113,_binary '\0',_binary ''),(13,58,_binary '\0',_binary ''),(13,101,_binary '',_binary ''),(13,102,_binary '\0',_binary ''),(13,103,_binary '\0',_binary ''),(14,105,_binary '\0',_binary ''),(14,114,_binary '',_binary ''),(14,115,_binary '\0',_binary ''),(14,116,_binary '\0',_binary ''),(15,100,_binary '\0',_binary ''),(15,117,_binary '',_binary ''),(15,118,_binary '\0',_binary ''),(15,119,_binary '\0',_binary ''),(15,120,_binary '\0',_binary ''),(17,117,_binary '\0',_binary ''),(17,118,_binary '\0',_binary ''),(17,119,_binary '\0',_binary ''),(17,120,_binary '',_binary ''),(18,101,_binary '',_binary ''),(18,114,_binary '\0',_binary ''),(18,115,_binary '\0',_binary ''),(19,102,_binary '',_binary ''),(19,103,_binary '\0',_binary ''),(19,104,_binary '\0',_binary ''),(19,105,_binary '\0',_binary '');
/*!40000 ALTER TABLE `team_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL,
  `mobile` varchar(150) DEFAULT NULL,
  `password` varchar(150) NOT NULL,
  `avatar_url` varchar(450) NOT NULL,
  `status` int NOT NULL,
  `note` varchar(300) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `role` int NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test1','aaa','asdasd','asdasd','asasdad',0,'asdsadas','2011-09-22 19:11:11','11122',3),(46,'Kien dep trai','kt29@gmail.com','0838776396','ogYOqF5pxX','https://www.w3schools.com/html/workplace.jpg',1,'',NULL,NULL,2),(48,'Cao Manh Hoang','hoangcmhe153602@fpt.edu.vn',NULL,'Agnojpj1','https://lh3.googleusercontent.com/a-/ACNPEu9e9tac-4jfeEiE0m74Zxzas785WD4MVe8dVqsMEQ=s96-c',1,NULL,NULL,NULL,2),(49,'Tung','tunghxhe160167@fpt.edu.vn',NULL,'202cb962ac59075b964b07152d234b70','https://lh3.googleusercontent.com/a/default-user=s96-c',1,NULL,'2022-09-27 14:03:37','UYLWMIvrtZ',3),(55,'nguyen trung kien','kiennt@fpt.edu.vn',NULL,'LsrjaEr9ES','https://www.w3schools.com/html/workplace.jpg',1,NULL,NULL,NULL,2),(57,'New User','dacdung0984@gmail.com','','ed2b1f468c5f915f3f1cf75d7068baae','//images//target//57.jpg',1,'null','2022-11-09 09:46:08','ETi0Wh72jZ',1),(58,'New User','dungndhe163971@fpt.edu.vn',NULL,'202cb962ac59075b964b07152d234b70','https://lh3.googleusercontent.com/a/default-user=s96-c',1,NULL,NULL,NULL,2),(59,'admin1','',NULL,'202cb962ac59075b964b07152d234b70','https://www.w3schools.com/html/workplace.jpg',1,NULL,NULL,NULL,1),(60,'admin2','thientm01@gmail.com','','202cb962ac59075b964b07152d234b70','https://www.w3schools.com/html/workplace.jpg',1,'null','2022-10-13 09:28:15','XZkY1Jnl14',1),(93,'New User','thoiloan0984@gmail.com',NULL,'202cb962ac59075b964b07152d234b70','https://lh3.googleusercontent.com/a/default-user=s96-c',1,NULL,NULL,NULL,6),(95,'rolesupporter','hoangcv68@gmail.com',NULL,'c15a10014a379803b293abf7ebbe982a','https://www.w3schools.com/html/workplace.jpg',0,NULL,NULL,'tD5AeXtcYX',2),(96,'user1','user1@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(97,'user2','user2@gmail.com',NULL,'202cb962ac59075b964b07152d234b70','',1,NULL,NULL,NULL,0),(98,'user3','user3@gmail.com',NULL,'202cb962ac59075b964b07152d234b70','',1,NULL,NULL,NULL,0),(99,'user4','user4@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(100,'user5','user5@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(101,'user6','user6@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(102,'user7','user7@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(103,'user8','user8@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(104,'user9','user9@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(105,'user10','user10@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(106,'user11','user11@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(107,'user12','user12@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(108,'user13','user13@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(109,'user14','user14@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(110,'user15','user15@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(111,'user16','user16@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(112,'user17','user17@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(113,'user18','user18@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(114,'user19','user19@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(115,'user20','user20@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(116,'user21','user21@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(117,'user22','user22@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(118,'user23','user23@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(119,'user24','user24@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(120,'user25','user25@gmail.com',NULL,'','',1,NULL,NULL,NULL,0),(121,'New User','testjavamail0985@gmail.com',NULL,'192d9c4a8a03f8ee08cbd1214e034afd','https://lh3.googleusercontent.com/a/default-user=s96-c',1,NULL,NULL,NULL,6),(122,'New User','kientthe160491@fpt.edu.vn',NULL,'d793b45dea21596c992e9f85893fb8d0','https://lh3.googleusercontent.com/a-/ACNPEu9bMG4v8CsHSh_m5A1fmMyJ05UyKGn7nDiL2mn4FQ=s96-c',1,NULL,NULL,NULL,6),(123,'New User','tkien2911@gmail.com',NULL,'e53b1f353d97f666eda1823ca899d563','https://lh3.googleusercontent.com/a-/ACNPEu9MlHLDCpaWIpz8OVLHeuWAhcfL44m9xCf6NM89Cg=s96-c',1,NULL,NULL,NULL,6),(124,'managerRole','nickyoutut682001mm@gmail.com',NULL,'b719d6cb9a7662f879c37a6f979ec928','https://lh3.googleusercontent.com/a-/ACNPEu82plsCHDqqBy57pC7s4dZeRbD8la4jEVs4wwcn=s96-c',1,NULL,NULL,NULL,6),(126,'ladasd','legonic20@gmail.com',NULL,'c15a10014a379803b293abf7ebbe982a','https://www.w3schools.com/html/workplace.jpg',1,NULL,NULL,'VbB1yWvuT0',6),(127,'nguyen long','legonicadassadds@gmail.com',NULL,'c15a10014a379803b293abf7ebbe982a','https://www.w3schools.com/html/workplace.jpg',0,NULL,NULL,'Pheebs4SjW',6);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `setting_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`setting_id`),
  KEY `fk_role_setting_idx` (`setting_id`),
  CONSTRAINT `fk_role_setting` FOREIGN KEY (`setting_id`) REFERENCES `setting` (`setting_id`),
  CONSTRAINT `fk_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (46,17),(48,17),(49,17),(55,17),(57,17),(59,17),(60,17),(122,17),(124,18),(95,44),(96,44),(58,45),(93,45),(97,45),(121,46),(123,46),(126,46),(127,46);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_contact`
--

DROP TABLE IF EXISTS `web_contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_contact` (
  `contact_id` int NOT NULL AUTO_INCREMENT,
  `category_id` int NOT NULL,
  `supporter_id` int NOT NULL,
  `full_name` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL,
  `mobile` varchar(150) NOT NULL,
  `message` varchar(300) NOT NULL,
  `response` varchar(300) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `fk_web_contact_user1_idx` (`supporter_id`),
  KEY `fk_web_contact_setting_idx` (`category_id`),
  CONSTRAINT `fk_web_contact_setting` FOREIGN KEY (`category_id`) REFERENCES `setting` (`setting_id`),
  CONSTRAINT `fk_web_contact_user` FOREIGN KEY (`supporter_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_contact`
--

LOCK TABLES `web_contact` WRITE;
/*!40000 ALTER TABLE `web_contact` DISABLE KEYS */;
INSERT INTO `web_contact` VALUES (1,19,49,'thien','tm@gmail.com','0123456789','ewqewqweqweqewq','ádasd',_binary ''),(2,20,49,'fff','fff','fff','fff','dddddd',_binary ''),(3,20,49,'fff','fff','fff','fff','aaaaaa',_binary ''),(4,19,49,'quay','thiendb@imediatech.com.vn','123213','sadsdasd\r\n                                ','',_binary ''),(5,19,49,'Kien','kien@gmail.com','123123','ádadasdasd','',_binary ''),(7,19,49,'Hoang','hoang@gmail.com','1111111','hello ','',_binary ''),(8,19,49,'Dung','asdad@gmail.com','12121212','i need help','cvcvcv',_binary ''),(9,19,49,'Dung','C@gmail.com','11111112','can anyone support me now','aaaaaa',_binary '');
/*!40000 ALTER TABLE `web_contact` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-10  1:46:33

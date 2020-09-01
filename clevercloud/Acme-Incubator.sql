-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: acme-incubator
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `accounting_record`
--

DROP TABLE IF EXISTS `accounting_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounting_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `draft` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `bookkeeper_id` int(11) NOT NULL,
  `investment_round_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK41jm4vk7runvmg5tderffrele` (`bookkeeper_id`),
  KEY `FKk1pmfnppwk0kav7xloy8u71uq` (`investment_round_id`),
  CONSTRAINT `FK41jm4vk7runvmg5tderffrele` FOREIGN KEY (`bookkeeper_id`) REFERENCES `bookkeeper` (`id`),
  CONSTRAINT `FKk1pmfnppwk0kav7xloy8u71uq` FOREIGN KEY (`investment_round_id`) REFERENCES `investment_round` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounting_record`
--

LOCK TABLES `accounting_record` WRITE;
/*!40000 ALTER TABLE `accounting_record` DISABLE KEYS */;
INSERT INTO `accounting_record` VALUES (121,0,'Aquí escribo sobre mi contabilidad','2020-08-11 21:00:00.000000',_binary '\0','Mi contabilidad',44,100),(122,0,'Llevar una buena contabilidad es importante','2020-08-12 21:00:00.000000',_binary '\0','Mi contabilidad 2',44,100),(123,0,'Con esta contabilidad no tendrás problemas','2020-08-13 21:00:00.000000',_binary '','Mi contabilidad 3',44,100),(124,0,'Con esta contabilidad no tendrás problemas','2020-08-10 20:00:00.000000',_binary '\0','My accounting record',47,102),(125,0,'Necesitas más fondos para poder llevar a cabo tu proyecto','2020-08-31 10:00:00.000000',_binary '\0','My accounting record',47,103);
/*!40000 ALTER TABLE `accounting_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `budget_amount` double DEFAULT NULL,
  `budget_currency` varchar(255) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `investment_round_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1ufotopeofii4jlefyk9c7os5` (`investment_round_id`),
  CONSTRAINT `FK1ufotopeofii4jlefyk9c7os5` FOREIGN KEY (`investment_round_id`) REFERENCES `investment_round` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (104,0,20000,'€','2021-01-01 09:00:00.000000','2020-10-11 10:00:00.000000','Activity 1',100),(105,0,50000,'€','2021-02-02 15:00:00.000000','2020-12-21 10:05:00.000000','Activity 2',100),(106,0,10000,'€','2021-03-03 17:00:00.000000','2020-12-20 12:00:00.000000','Activity 3',100),(107,0,20000,'€','2021-03-21 18:00:00.000000','2020-12-24 23:30:00.000000','Activity 4',101),(108,0,50000,'€','2021-04-05 18:00:00.000000','2020-12-15 13:00:00.000000','Activity 5',101),(109,0,25000,'€','2020-10-03 16:00:00.000000','2020-09-10 11:00:00.000000','First activity',102),(110,0,70000,'€','2020-12-23 18:00:00.000000','2020-10-10 08:30:00.000000','Second activity',102),(111,0,40000,'€','2021-04-05 18:00:00.000000','2021-01-10 13:00:00.000000','Third activity',102);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2a5vcjo3stlfcwadosjfq49l1` (`user_account_id`),
  CONSTRAINT `FK_2a5vcjo3stlfcwadosjfq49l1` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (8,0,7),(11,0,10);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ales_bulletin`
--

DROP TABLE IF EXISTS `ales_bulletin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ales_bulletin` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `lucky_number` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ales_bulletin`
--

LOCK TABLES `ales_bulletin` WRITE;
/*!40000 ALTER TABLE `ales_bulletin` DISABLE KEYS */;
INSERT INTO `ales_bulletin` VALUES (61,0,'Manuel Ales','2','2002-04-20 14:00:00.000000','Hola Mundo'),(62,0,'Master Chief','117','2002-04-21 14:00:00.000000','I love you Cortana'),(63,0,'Spike Spiegel','27','2002-04-22 14:00:00.000000','La vida es como un juego de cartas, unas veces se gana y otras se pierde.');
/*!40000 ALTER TABLE `ales_bulletin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anonymous`
--

DROP TABLE IF EXISTS `anonymous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anonymous` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6lnbc6fo3om54vugoh8icg78m` (`user_account_id`),
  CONSTRAINT `FK_6lnbc6fo3om54vugoh8icg78m` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anonymous`
--

LOCK TABLES `anonymous` WRITE;
/*!40000 ALTER TABLE `anonymous` DISABLE KEYS */;
INSERT INTO `anonymous` VALUES (2,0,1);
/*!40000 ALTER TABLE `anonymous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `justification` varchar(255) DEFAULT NULL,
  `offer_amount` double DEFAULT NULL,
  `offer_currency` varchar(255) DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `investment_round_id` int(11) NOT NULL,
  `investor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ao7wxw7e7mkj6g5q49yq2fw8d` (`ticker`),
  KEY `FKk5ibe41quxsif8im882xv4afo` (`investment_round_id`),
  KEY `FKl4fp0cd8c008ma79n6w58xhk9` (`investor_id`),
  CONSTRAINT `FKk5ibe41quxsif8im882xv4afo` FOREIGN KEY (`investment_round_id`) REFERENCES `investment_round` (`id`),
  CONSTRAINT `FKl4fp0cd8c008ma79n6w58xhk9` FOREIGN KEY (`investor_id`) REFERENCES `investor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (112,0,'2000-12-11 22:59:00.000000','',80000,'€','example 1','accepted','TEC-20-000001',100,35),(113,0,'2000-12-11 22:59:00.000000','Offer too low!',100000,'€','example 2','rejected','SCI-20-000002',100,38),(114,0,'2000-12-11 22:59:00.000000','',25000,'€','example 3','pending','SCI-20-000003',102,38),(115,0,'2000-12-11 22:59:00.000000','It\'s the same as the other one.',25000,'€','example 3','rejected','TEC-20-000003',102,38),(116,0,'2020-08-31 10:59:00.000000','This looks shady',1000000000,'€','Don\'t worry about paying me back','rejected','HEA-20-000223',103,41),(117,0,'2020-08-30 21:59:00.000000','',120000,'€','Contact me if this amount isn\'t enough','accepted','SCI-20-0012343',103,38),(118,0,'2020-08-30 21:59:00.000000','',120000,'€','This should help be enough to ensure your growth','pending','TEC-20-001103',103,35),(119,0,'2020-08-31 11:59:00.000000','Already accepted another application',70000,'€','This should help be enough to ensure your growth','rejected','HEA-20-021103',100,41),(120,0,'2020-08-29 11:59:00.000000','Yes, thank you very much',120000,'€','Is this amount enough?','accepted','HEA-20-057103',102,41);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authenticated`
--

DROP TABLE IF EXISTS `authenticated`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authenticated` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h52w0f3wjoi68b63wv9vwon57` (`user_account_id`),
  CONSTRAINT `FK_h52w0f3wjoi68b63wv9vwon57` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authenticated`
--

LOCK TABLES `authenticated` WRITE;
/*!40000 ALTER TABLE `authenticated` DISABLE KEYS */;
INSERT INTO `authenticated` VALUES (4,0,3),(6,0,5),(9,0,7),(12,0,10),(15,0,14),(18,0,16),(21,0,19),(24,0,22),(27,0,25),(30,0,28),(33,0,31),(36,0,34),(39,0,37),(42,0,40),(45,0,43),(48,0,46);
/*!40000 ALTER TABLE `authenticated` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `credit_card_id` int(11) DEFAULT NULL,
  `patron_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr19baq0bri0akndc7ruwhngy4` (`credit_card_id`),
  KEY `FKdocr1jjfgwx9ef5jbf675l360` (`patron_id`),
  CONSTRAINT `FKdocr1jjfgwx9ef5jbf675l360` FOREIGN KEY (`patron_id`) REFERENCES `patron` (`id`),
  CONSTRAINT `FKr19baq0bri0akndc7ruwhngy4` FOREIGN KEY (`credit_card_id`) REFERENCES `credit_card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (92,0,'https://files.catbox.moe/xu70s5.jpg','Wide Miyako is wide','https://en.wikipedia.org/wiki/Hidamari_Sketch',89,17),(93,0,'https://i.imgur.com/DFqnDyU.jpg','So evil!','http://gabdro.com/',89,17),(94,0,'https://upload.wikimedia.org/wikipedia/commons/f/f0/Vega_-_star_in_Lyra.png','La estrella Vega','https://es.wikipedia.org/',NULL,20),(95,0,'https://shadowverse.com/assets/images/cards_banner_11th.png','Thousand of cards, infinite possibilities','https://shadowverse.com/',91,23),(96,0,'https://portal.symphogear.com/bnr/XVlogo.png','In the distance, that day, when the star became music...','https://www.symphogear-xv.com/',91,23),(97,0,'https://static2.cbrimages.com/wordpress/wp-content/uploads/2020/03/10-Reasons-Why-Shakugan-No-Shana-Is-The-Best-Anime-Series.jpg','Those who rely on “someday” will only grow weaker and lowlier','https://www.shakugan.com/',91,23),(98,0,'https://pbs.twimg.com/media/DqUVDYDUUAAQ0cJ.jpg','Fumo supremacy','https://www.youtube.com/channel/UCVv6OI4dMDrFWPXLxZrONng',89,17),(99,0,'https://files.catbox.moe/fjvb6b.png','The problem of being faster than light is that you can only live in darkness','https://sanic.readthedocs.io/en/latest/',89,17);
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bonacini_bulletin`
--

DROP TABLE IF EXISTS `bonacini_bulletin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bonacini_bulletin` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bonacini_bulletin`
--

LOCK TABLES `bonacini_bulletin` WRITE;
/*!40000 ALTER TABLE `bonacini_bulletin` DISABLE KEYS */;
INSERT INTO `bonacini_bulletin` VALUES (57,0,'Emiya Shirou','Fuyuki','2002-04-20 14:00:00.000000','People die if they\'re killed...'),(58,0,'Luca Bonacini','Planet Usamin','2014-04-02 12:00:00.000000','I am eternally 17 (◕‿◕✿)'),(59,0,'Suzumiya Haruhi','Tokyo','2007-10-23 20:37:00.000000','All ships, open fire! Go out there and burn the enemy leader with all the fires of hell!'),(60,0,'Sasasegawa Sasami','Keyverse','2011-05-13 10:45:00.000000','My name is Sasasesa... Sasasegawa Sasami!');
/*!40000 ALTER TABLE `bonacini_bulletin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookkeeper`
--

DROP TABLE IF EXISTS `bookkeeper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookkeeper` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `firm_name` varchar(255) DEFAULT NULL,
  `responsibility_statement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_krvjp9eaqyapewl2igugbo9o8` (`user_account_id`),
  CONSTRAINT `FK_krvjp9eaqyapewl2igugbo9o8` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookkeeper`
--

LOCK TABLES `bookkeeper` WRITE;
/*!40000 ALTER TABLE `bookkeeper` DISABLE KEYS */;
INSERT INTO `bookkeeper` VALUES (44,0,43,'Microsoft','This is a highly confidential responsibility statement'),(47,0,46,'Monsanto','We won\'t accept Bayer\'s offer');
/*!40000 ALTER TABLE `bookkeeper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookkeeper_request`
--

DROP TABLE IF EXISTS `bookkeeper_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookkeeper_request` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `accepted` bit(1) NOT NULL,
  `firm_name` varchar(255) DEFAULT NULL,
  `responsibility_statement` varchar(255) DEFAULT NULL,
  `authenticated_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qvxp9h7at8vjbwgpi9q5s3fpl` (`authenticated_id`),
  CONSTRAINT `FKhdducua8c58xhfrls8oiih3j0` FOREIGN KEY (`authenticated_id`) REFERENCES `authenticated` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookkeeper_request`
--

LOCK TABLES `bookkeeper_request` WRITE;
/*!40000 ALTER TABLE `bookkeeper_request` DISABLE KEYS */;
INSERT INTO `bookkeeper_request` VALUES (49,0,_binary '\0','Patata Inc.','Las patatas están ricas',15),(50,0,_binary '\0','Microsoft','This is a highly confidential responsibility statement',45),(51,0,_binary '\0','Monsanto','We won\'t accept Bayer\'s offer',48);
/*!40000 ALTER TABLE `bookkeeper_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge`
--

DROP TABLE IF EXISTS `challenge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenge` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `average_goal` varchar(255) DEFAULT NULL,
  `average_reward_amount` double DEFAULT NULL,
  `average_reward_currency` varchar(255) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `expert_goal` varchar(255) DEFAULT NULL,
  `expert_reward_amount` double DEFAULT NULL,
  `expert_reward_currency` varchar(255) DEFAULT NULL,
  `rookie_goal` varchar(255) DEFAULT NULL,
  `rookie_reward_amount` double DEFAULT NULL,
  `rookie_reward_currency` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge`
--

LOCK TABLES `challenge` WRITE;
/*!40000 ALTER TABLE `challenge` DISABLE KEYS */;
INSERT INTO `challenge` VALUES (82,0,'Achieve a podium finish!',25000,'€','2020-10-04 21:59:00.000000','The first to finish wins!','Finish in first place!',50000,'€','Finish the challenge!',10000,'€','Challenge 1'),(83,0,'Jump at least 1 metre!',10000,'€','2020-01-09 22:59:00.000000','Jump high and win!','Break the world record!',70000,'€','Finish the challenge!',25000,'€','Challenge 2'),(84,0,'Finish in less than 30 minutes!',70000,'€','2021-11-24 20:47:00.000000','The fastest will win!','Finish in less than 1 minute!',100000,'€','Finish the challenge!',50000,'€','Challenge 3');
/*!40000 ALTER TABLE `challenge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `activity_sectors` varchar(255) DEFAULT NULL,
  `inv_round_kinds` varchar(255) DEFAULT NULL,
  `spam_words` varchar(255) DEFAULT NULL,
  `threshold` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (13,0,'Technology; Science; Arts; Business; Health','SEED; ANGEL; SERIES-A; SERIES-B; SERIES-C; BRIDGE','sex, hard core, viagra, cialis, Nigeria, you\'ve won, million dolar ',2.5);
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumer`
--

DROP TABLE IF EXISTS `consumer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6cyha9f1wpj0dpbxrrjddrqed` (`user_account_id`),
  CONSTRAINT `FK_6cyha9f1wpj0dpbxrrjddrqed` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumer`
--

LOCK TABLES `consumer` WRITE;
/*!40000 ALTER TABLE `consumer` DISABLE KEYS */;
/*!40000 ALTER TABLE `consumer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `credit_card` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  `expiration_date` datetime(6) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `patron_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mre2b1pfmmoe2f6n3c60tom5q` (`patron_id`),
  CONSTRAINT `FK31e9eqi896koc93q7yjs5yoox` FOREIGN KEY (`patron_id`) REFERENCES `patron` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
INSERT INTO `credit_card` VALUES (89,0,'Visa','000','2025-05-31 22:00:00.000000','Alguien Uno','5500000000000004',17),(90,0,'Master Card','123','2024-05-31 22:00:00.000000','Alguien Dos','6011000000000004',20),(91,0,'Visa','999','2022-04-30 22:00:00.000000','Alguien Tres','3088000000000009',23);
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrepreneur`
--

DROP TABLE IF EXISTS `entrepreneur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrepreneur` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `activity_sector` varchar(255) DEFAULT NULL,
  `qualifications` varchar(255) DEFAULT NULL,
  `skills` varchar(255) DEFAULT NULL,
  `start_up_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r6tqltqvrlh1cyy8rsj5pev1q` (`user_account_id`),
  CONSTRAINT `FK_r6tqltqvrlh1cyy8rsj5pev1q` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrepreneur`
--

LOCK TABLES `entrepreneur` WRITE;
/*!40000 ALTER TABLE `entrepreneur` DISABLE KEYS */;
INSERT INTO `entrepreneur` VALUES (26,0,25,'Technology','Qualifications Entrepreneur 1','Skills Entrepreneur 1','Entrepreneur 1'),(29,0,28,'Science','Qualifications Entrepreneur 2','Skills Entrepreneur 2','Entrepreneur 2'),(32,0,31,'Business','Qualifications Entrepreneur 3','Skills Entrepreneur 3','Entrepreneur 3');
/*!40000 ALTER TABLE `entrepreneur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fernandez_bulletin`
--

DROP TABLE IF EXISTS `fernandez_bulletin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fernandez_bulletin` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `favourite_song` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fernandez_bulletin`
--

LOCK TABLES `fernandez_bulletin` WRITE;
/*!40000 ALTER TABLE `fernandez_bulletin` DISABLE KEYS */;
INSERT INTO `fernandez_bulletin` VALUES (55,0,'Espartaco','POP SMOKE - DIOR','2020-05-05 13:45:00.000000','It\'s so good!'),(56,0,'Lagarto Juancho','JACKBOYS, Pop Smoke, Travis Scott - GATTI','2020-06-05 15:40:00.000000','Good song!');
/*!40000 ALTER TABLE `fernandez_bulletin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum`
--

DROP TABLE IF EXISTS `forum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forum` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `investment_round_id` int(11) DEFAULT NULL,
  `owner_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq8ggcjgl5by5gf6l5bji632hu` (`investment_round_id`),
  KEY `FKohunr24m4s1n5mgs3eoqtscsv` (`owner_id`),
  CONSTRAINT `FKohunr24m4s1n5mgs3eoqtscsv` FOREIGN KEY (`owner_id`) REFERENCES `authenticated` (`id`),
  CONSTRAINT `FKq8ggcjgl5by5gf6l5bji632hu` FOREIGN KEY (`investment_round_id`) REFERENCES `investment_round` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum`
--

LOCK TABLES `forum` WRITE;
/*!40000 ALTER TABLE `forum` DISABLE KEYS */;
INSERT INTO `forum` VALUES (126,0,'2020-05-01 10:30:00.000000','Investment round 1\'s discussion forum',100,27),(127,0,'2020-02-21 13:10:00.000000','Investment round 3\'s discussion forum',102,30),(128,0,'2020-06-21 12:10:00.000000','Investment round 3\'s discussion forum',103,33);
/*!40000 ALTER TABLE `forum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum_authenticated`
--

DROP TABLE IF EXISTS `forum_authenticated`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forum_authenticated` (
  `forum_id` int(11) NOT NULL,
  `authenticated_id` int(11) NOT NULL,
  PRIMARY KEY (`forum_id`,`authenticated_id`),
  KEY `FK2tvw03uoosrx2bloqkex8ssgr` (`authenticated_id`),
  CONSTRAINT `FK2tvw03uoosrx2bloqkex8ssgr` FOREIGN KEY (`authenticated_id`) REFERENCES `authenticated` (`id`),
  CONSTRAINT `FKd1ebx6x0cql1bxphvu15qxh2x` FOREIGN KEY (`forum_id`) REFERENCES `forum` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forum_authenticated`
--

LOCK TABLES `forum_authenticated` WRITE;
/*!40000 ALTER TABLE `forum_authenticated` DISABLE KEYS */;
INSERT INTO `forum_authenticated` VALUES (127,39),(128,39),(126,45);
/*!40000 ALTER TABLE `forum_authenticated` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (135);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry`
--

DROP TABLE IF EXISTS `inquiry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `max_money_amount` double DEFAULT NULL,
  `max_money_currency` varchar(255) DEFAULT NULL,
  `min_money_amount` double DEFAULT NULL,
  `min_money_currency` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry`
--

LOCK TABLES `inquiry` WRITE;
/*!40000 ALTER TABLE `inquiry` DISABLE KEYS */;
INSERT INTO `inquiry` VALUES (76,0,'contactEmail@gmail.com','2019-06-20 22:30:00.000000','2021-06-21 22:00:00.000000','This is a description!',50000,'€',20000,'€','Inquiry 1'),(77,0,'contactEmail@gmail.com','2019-05-13 15:30:00.000000','2019-07-19 20:30:00.000000','This is a description!',25000,'€',10000,'€','Inquiry 2'),(78,0,'contactEmail@gmail.com','2020-01-15 14:35:00.000000','2020-10-11 15:05:00.000000','This is a description!',70000,'€',25000,'€','Inquiry 3');
/*!40000 ALTER TABLE `inquiry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investment_round`
--

DROP TABLE IF EXISTS `investment_round`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `investment_round` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `amount_amount` double DEFAULT NULL,
  `amount_currency` varchar(255) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `final_mode` bit(1) NOT NULL,
  `kind` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `entrepreneur_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_408l1ohatdkkut5bkt0eu6ifs` (`ticker`),
  KEY `FKkj1l8c2ftn9c65y061me6t37j` (`entrepreneur_id`),
  CONSTRAINT `FKkj1l8c2ftn9c65y061me6t37j` FOREIGN KEY (`entrepreneur_id`) REFERENCES `entrepreneur` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investment_round`
--

LOCK TABLES `investment_round` WRITE;
/*!40000 ALTER TABLE `investment_round` DISABLE KEYS */;
INSERT INTO `investment_round` VALUES (100,0,80000,'€','2020-05-01 10:30:00.000000','Description Investment round 1',_binary '','SEED','https://www.google.com/','TEC-20-014683','Investment round 1',26),(101,0,70000,'€','2020-04-12 09:35:00.000000','Description InvestmentRound 2',_binary '\0','ANGEL','https://www.google.com/','SCI-20-652963','Investment round 2',29),(102,0,100000,'€','2020-02-21 13:10:00.000000','Description InvestmentRound 3',_binary '','SERIES-A','https://www.google.com/','TEC-20-967332','Investment round 3',29),(103,0,135000,'€','2020-06-21 12:10:00.000000','Description InvestmentRound 4',_binary '','ANGEL','https://www.google.com/','BUS-20-900032','Investment round 4',32);
/*!40000 ALTER TABLE `investment_round` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investor`
--

DROP TABLE IF EXISTS `investor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `investor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `activity_sector` varchar(255) DEFAULT NULL,
  `firm_name` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dcek5rr514s3rww0yy57vvnpq` (`user_account_id`),
  CONSTRAINT `FK_dcek5rr514s3rww0yy57vvnpq` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investor`
--

LOCK TABLES `investor` WRITE;
/*!40000 ALTER TABLE `investor` DISABLE KEYS */;
INSERT INTO `investor` VALUES (35,0,34,'Technology','Firm Name Investor 1','Profile Investor 1'),(38,0,37,'Science','Firm Name Investor 2','Profile Investor 2'),(41,0,40,'Health','Firm Name Investor 3','Profile Investor 3');
/*!40000 ALTER TABLE `investor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `forum_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfwwpivgx5j4vw4594dgrw884q` (`forum_id`),
  KEY `FKni5vovfjiadoisvjqpvfx703d` (`sender_id`),
  CONSTRAINT `FKfwwpivgx5j4vw4594dgrw884q` FOREIGN KEY (`forum_id`) REFERENCES `forum` (`id`),
  CONSTRAINT `FKni5vovfjiadoisvjqpvfx703d` FOREIGN KEY (`sender_id`) REFERENCES `authenticated` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (129,0,'I don\'t know if I have to change some settings','2020-05-02 09:35:00.000000','','Can you all see this forum?',126,27),(130,0,'Don\'t worry, everyone involved in it can see it by default.','2020-05-04 19:35:00.000000','help','It\'s fine',126,45),(131,0,'Yes, I can confirm I could see it right after having my application accepted.','2020-05-10 19:00:00.000000','','Don\'t worry',126,36),(132,0,'You should update your skills record.','2020-04-15 19:00:00.000000','','Skills?',127,39),(133,0,'Sorry, I chose the default ones. I will change them as soon as I have one.','2020-04-17 19:00:00.000000','','Skills?',127,30),(134,0,'Thanks for your application','2020-08-31 12:00:00.000000','','Thanks',127,33);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `header` varchar(255) DEFAULT NULL,
  `links` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (85,0,'This is a body!','2020-07-27 22:30:00.000000','2021-07-27 22:00:00.000000','https://upload.wikimedia.org/wikipedia/commons/4/4f/Black_hole_-_Messier_87_crop_max_res.jpg','https://anilist.co/ ; https://es.wikipedia.org/','Notice 1'),(86,0,'This is a body 2!','2020-06-27 22:30:00.000000','2020-07-27 22:00:00.000000','https://upload.wikimedia.org/wikipedia/commons/d/d1/Artist%E2%80%99s_impression_of_the_quasar_3C_279.jpg',NULL,'Notice 2'),(87,0,'This is a body 3!','2020-06-27 22:30:00.000000','2021-07-27 22:00:00.000000','https://upload.wikimedia.org/wikipedia/commons/f/f0/Vega_-_star_in_Lyra.png','https://anilist.co/','Notice 3'),(88,0,'This is a body 3!','2020-06-27 22:30:00.000000','2021-07-27 22:00:00.000000','https://www.jpl.nasa.gov/spaceimages/images/largesize/PIA04204_hires.jpg','https://es.wikipedia.org/','Notice 4');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `overture`
--

DROP TABLE IF EXISTS `overture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `overture` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `max_money_amount` double DEFAULT NULL,
  `max_money_currency` varchar(255) DEFAULT NULL,
  `min_money_amount` double DEFAULT NULL,
  `min_money_currency` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `overture`
--

LOCK TABLES `overture` WRITE;
/*!40000 ALTER TABLE `overture` DISABLE KEYS */;
INSERT INTO `overture` VALUES (79,0,'contactEmail@gmail.com','2018-03-11 09:30:00.000000','2021-01-11 23:00:00.000000','This is a description!',50000,'€',20000,'€','Overture 1'),(80,0,'contactEmail@gmail.com','2017-05-13 11:30:00.000000','2022-09-12 09:30:00.000000','This is a description!',25000,'€',10000,'€','Overture 2'),(81,0,'contactEmail@gmail.com','2020-01-25 08:45:00.000000','2020-06-28 05:55:00.000000','This is a description!',70000,'€',25000,'€','Overture 3');
/*!40000 ALTER TABLE `overture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patron`
--

DROP TABLE IF EXISTS `patron`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patron` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `activity_sector` varchar(255) DEFAULT NULL,
  `organisation` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `credit_card_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpj4cod0bcxwxg4nqv4f2xkikg` (`credit_card_id`),
  KEY `FK_8xx5nujhuio3advxc2freyu65` (`user_account_id`),
  CONSTRAINT `FK_8xx5nujhuio3advxc2freyu65` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FKpj4cod0bcxwxg4nqv4f2xkikg` FOREIGN KEY (`credit_card_id`) REFERENCES `credit_card` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patron`
--

LOCK TABLES `patron` WRITE;
/*!40000 ALTER TABLE `patron` DISABLE KEYS */;
INSERT INTO `patron` VALUES (17,1,16,'Technology','Patron Organisation 1','Profile Patron 1',89),(20,1,19,'Business','Patron Organisation 2','Profile Patron 2',90),(23,1,22,'Health','Patron Organisation 3','Profile Patron 3',91);
/*!40000 ALTER TABLE `patron` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provider` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b1gwnjqm6ggy9yuiqm0o4rlmd` (`user_account_id`),
  CONSTRAINT `FK_b1gwnjqm6ggy9yuiqm0o4rlmd` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technology_record`
--

DROP TABLE IF EXISTS `technology_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `technology_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `activity_sector` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `inventor` varchar(255) DEFAULT NULL,
  `open_source` bit(1) NOT NULL,
  `stars` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `web_site` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technology_record`
--

LOCK TABLES `technology_record` WRITE;
/*!40000 ALTER TABLE `technology_record` DISABLE KEYS */;
INSERT INTO `technology_record` VALUES (64,0,'Science','Sample description','mail1@example.com','Dr. Robotnik',_binary '\0',3,'Technology 1','http://example1.com'),(65,0,'Technology','Sample description','mail2@example.com','Beakman',_binary '',1,'Technology 2','http://example2.com'),(66,0,'Technology','Sample description','mail3@example.com','Dr. Willy',_binary '\0',-3,'Technology 3','http://example3.com'),(67,0,'Business','Sample description','mail4@example.com','Dr. Emmett Brown (Doc)',_binary '',5,'Technology 4','http://example4.com'),(68,0,'Health','Sample description','mail5@example.com','Morty Sánchez',_binary '\0',-5,'Technology 5','http://example5.com'),(69,0,'Science','Sample description','mail5@example.com','Rintaro Okabe',_binary '\0',-5,'Technology 6','http://steinsgate.jp/');
/*!40000 ALTER TABLE `technology_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `toledo_bulletin`
--

DROP TABLE IF EXISTS `toledo_bulletin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `toledo_bulletin` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `toledo_bulletin`
--

LOCK TABLES `toledo_bulletin` WRITE;
/*!40000 ALTER TABLE `toledo_bulletin` DISABLE KEYS */;
INSERT INTO `toledo_bulletin` VALUES (52,0,'Alberto Toledo Mayorga','email@prueba1.com','2020-07-22 15:46:00.000000','Estoy feliz, prueba 1'),(53,0,'Alberto Toledo Mayorga','email@prueba2.com','2020-07-22 15:47:00.000000','Estoy muy feliz, prueba 2'),(54,0,'Alberto Toledo Mayorga','email@prueba3.com','2020-07-22 15:48:00.000000','Estoy muy, pero que muy feliz, prueba 3');
/*!40000 ALTER TABLE `toledo_bulletin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tool_record`
--

DROP TABLE IF EXISTS `tool_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tool_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `activity_sector` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `inventor` varchar(255) DEFAULT NULL,
  `open_source` bit(1) NOT NULL,
  `stars` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `web_site` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tool_record`
--

LOCK TABLES `tool_record` WRITE;
/*!40000 ALTER TABLE `tool_record` DISABLE KEYS */;
INSERT INTO `tool_record` VALUES (70,0,'Technology','Sample description','mail1@example.com','Dr. Pepper',_binary '\0',1,'Tool 1','http://example1.com'),(71,0,'Science','Sample description','mail2@example.com','Marcus Fenix',_binary '',-2,'Tool 2','http://example2.com'),(72,0,'Technology','Sample description','mail3@example.com','Megaman',_binary '\0',0,'Tool 3','http://example3.com'),(73,0,'Science','Sample description','mail4@example.com','Marty McFly',_binary '',-4,'Tool 4','http://example4.com'),(74,0,'Technology','Sample description','mail5@example.com','Al Pacino',_binary '\0',5,'Tool 5','http://example5.com'),(75,0,'Science','Sample description','mail5@example.com','Natasha',_binary '\0',5,'Tool 6','https://www.sekaiseifuku-zzz.com/');
/*!40000 ALTER TABLE `tool_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `identity_email` varchar(255) DEFAULT NULL,
  `identity_name` varchar(255) DEFAULT NULL,
  `identity_surname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,0,_binary '\0','john.doe@acme.com','John','Doe','$2a$05$hrb5j98RJaZaZhuCQjzebevM9maLDYP/WwjEvfh3yYb1M71IZqpxy','anonymous'),(3,0,_binary '','hoshimiya.kate@acme.com','Kate','Hoshimiya','$2a$05$mTjFl/2vyTcATgU2U7j3rO/1d9Z1TWzBV8o.mhA6voSA1zTv5LDdi','venera'),(5,0,_binary '','usada.pekora@acme.com','Pekora','Usada','$2a$05$WFqm/5lHHPAfJB3jPf9.B.WrPv.4tff7hgjpAB.u/kHRy1G/0ltUW','usuario'),(7,0,_binary '','administrator@acme.com','Administrator','Acme.com','$2a$05$SpSHlULUJ5T0k7cl9z7j3uhx20q7M5QOO6ugKxCnYBecSxIshmIGe','administrator'),(10,0,_binary '','admin@acme.com','Admin','Acme.com','$2a$05$.p21oFYYPDp2m8dTkXMA0OTFh8AfQrHeZPxcFVz3qwzA3gd66xyLu','admin'),(14,0,_binary '','patata.potato@acme.com','Patata','Potato','$2a$05$b7WF6vVgiMQEEpQZufosV.vD8OTMnp6iHNRPDLNsB.O8xcfpM7f.K','patata'),(16,0,_binary '','patron1@gmail.com','Patron','One','$2a$05$Wh.w9THz8TLE00dCz5jO6OotIKvtEZmuxczubuIZMPfvI8XtJ/MPC','patron1'),(19,0,_binary '','patron2@gmail.com','Patron','Two','$2a$05$7WQ8rpvalUjf0Kc5dXcILOHCLgxjHxnJCavk1tuKpAM43zFp42a7K','patron2'),(22,0,_binary '','patron3@gmail.com','Patron','Three','$2a$05$WuxQYCjpnIR9uB3Rlj2tXuvyiauLfandHkvJav1dz7j897DjSPZPu','patron3'),(25,0,_binary '','entrepreneur1@gmail.com','Entrepreneur','One','$2a$05$AFjm2m7Y090/c9Y3qWcWqObwAH2XppHsJO/co5tVGAN.8j6t7LFem','entrepreneur1'),(28,0,_binary '','entrepreneur2@gmail.com','Entrepreneur','Two','$2a$05$9.YgijZMAO7Rdkcx5GG3RexZZgJaGrmCNpyDLDez683toYjwl64My','entrepreneur2'),(31,0,_binary '','entrepreneur3@gmail.com','Entrepreneur','Three','$2a$05$edkUF/JFeHy2XRZOLVw10OG5qggTSxbhiImiDCVVKnqpp2wR7HxLC','entrepreneur3'),(34,0,_binary '','investor1@acme.com','Investor','One','$2a$05$Lu43uN29y0EKu7W7xfWNROX22h05nEDveos.6TAIEFp1e4qIxgx42','investor1'),(37,0,_binary '','investor2@acme.com','Investor','Two','$2a$05$O3ra5y.jEVEa0eTGFYMKW.yPVYQoSPdtfcKiMh5JjgO5qtqstWlnG','investor2'),(40,0,_binary '','investor2@acme.com','Investor','Three','$2a$05$leXOc7C.Au4uQP1N438GquyKOW8xob0LxF6Jiu9/FIYC1EmfHm0wu','investor3'),(43,0,_binary '','bookkeeper1@microsoft.com','Bookkeeper','One','$2a$05$mENszPwOlC3mXkOMeptWtuzFpN6RAJVxG6oPtljSiY4QVJ5qE/aVC','bookkeeper1'),(46,0,_binary '','bookkeeper2@monsanto.com','Bookkeeper','Two','$2a$05$cDbT1D3qQE66sYdn70wACOmXliFNC1L21aRHTb0T1ktkzf5kvmJzG','bookkeeper2');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-01  2:44:42

-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: shoppingcart-ver2
-- ------------------------------------------------------
-- Server version	8.0.33

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(38,2) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `UK_o61fmio5yukmmiqgnxf8pnavn` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


INSERT INTO `products` VALUES (1,'Pears baby soap for Kids','Soap',35.75,100),(2,'Signal Tooth Brushes Size in (L, M, S)','Tooth Brush',34.50,245),(3,'Casual Shirt imported from France','Shirt',1500.00,350),(4,'Leather bag imported from USA','Office Bag',1000.00,320),(5,'Hot Water Bottles','Bottle',450.45,69),(6,'Imported wrist watches from swiss','Wrist Watch',2500.00,800),(7,'3G/4G capability','Mobile Phone',45000.00,697),(8,'Head and Shoulders Shampoo','Shampoo',300.00,500),(9,'Imported Leather Wallets from AUS','Leather Wallets',500.00,983),(10,'Imported Canon camera from USA','Camera',85000.00,100);


--
-- Table structure for table `roles`
--


CREATE TABLE `roles` (
  `role_id` bigint NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_g50w4r0ru3g9uf6i6fr4kpro8` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `roles` (`role_id`, `role`) VALUES (2, 'ADMIN'), (4, 'GUEST'), (1, 'OWNER'), (3, 'SALES');

-- INSERT INTO `roles` (`role_id`, `role`) VALUES (1, 'ROLE_OWNER'),(2, 'ROLE_ADMIN'), (3, 'ROLE_SALES'), (4, 'ROLE_GUEST');


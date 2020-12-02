create database test6;
use test6;
DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `username` varchar(40) NOT NULL,
  `password` varchar(250) NOT NULL,
  `name` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `enabled` int(11) DEFAULT '1',
  `illegalaccesscnt` int(11) DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `USERS_ROLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS_ROLES` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(20) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`),
  KEY `user` (`user`),
  CONSTRAINT `USERS_ROLES_ibfk_1` FOREIGN KEY (`user`) REFERENCES `USERS` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `CUSTOMER`;
CREATE TABLE `CUSTOMER` (
  `custid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `doornum` varchar(10) DEFAULT NULL,
  `streetname` varchar(50) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  `uname` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `imid` int(11) DEFAULT '4',
  PRIMARY KEY (`custid`),
  KEY `FK_CUST_REF_USER` (`uname`),
  -- KEY `FK_CUSTOMER_IMAGE` (`imid`),
  CONSTRAINT `CUSTOMER_ibfk_1` FOREIGN KEY (`uname`) REFERENCES `USERS` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
  -- CONSTRAINT `FK_CUSTOMER_IMAGE` FOREIGN KEY (`imid`) REFERENCES `IMAGE` (`imgid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `PRODUCT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRODUCT` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `qtyinstock` int(11) DEFAULT NULL,
  `mrp` double DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `imname` varchar(1000) DEFAULT NULL,
  `imid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
  -- KEY `FK_PRODUCT_IMAGE` (`imid`),
  -- CONSTRAINT `FK_PRODUCT_IMAGE` FOREIGN KEY (`imid`) REFERENCES `IMAGE` (`imgid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

INSERT INTO PRODUCT(name,qtyinstock,mrp,description,brand,imname) VALUES("Nizoral Shampoo",100,250,"Ketoconazole is an antifungal medication that fights dandruff effectively. This powerful medicated shampoo kills the fungus that causes dandruff and simultaneously imparts natural proteins to your hair to keep it strong and healthy. It contains a blend of wheat proteins and wheat germ oil that keeps your hair soft and shiny. ","Nizoral","images/product_01.png");
INSERT INTO PRODUCT(name,qtyinstock,mrp,description,brand,imname) VALUES(" Neutrogena T/Gel Therapeutic Shampoo",110,150,"This dermatologist-recommended shampoo treats all types of scalp conditions. It contains salicylic acid that breaks down the flakes on your scalp and prevents new flakes from appearing. It eliminates stubborn dandruff and soothes your scalp. It clears the dead skin built up on your scalp and keeps it clean and hydrated. ","Neutrogina","images/product_02.png");
INSERT INTO PRODUCT(name,qtyinstock,mrp,description,brand,imname) VALUES("Denorex Extra Strength Dandruff  Shampoo",100,400,"Ketoconazole is an antifungal medication that fights dandruff effectively. This powerful medicated shampoo kills the fungus that causes dandruff and simultaneously imparts natural proteins to your hair to keep it strong and healthy. It contains a blend of wheat proteins and wheat germ oil that keeps your hair soft and shiny. ","Nizoral","images/product_03.png");
INSERT INTO PRODUCT(name,qtyinstock,mrp,description,brand,imname) VALUES("Selsun Blue Dandruff Shampoo",60,375,"This highly acclaimed medicated shampoo contains an active ingredient called selenium sulfide that treats dandruff and scalp infection instantly. This shampoo is formulated with menthol that treats inflammation and prevents redness. It creates a rich lather that softens your hair. ","Selsun","images/product_04.png");
INSERT INTO PRODUCT(name,qtyinstock,mrp,description,brand,imname) VALUES("Sulfur8 Deep Cleaning Syrup",100,550,"Sulfur8 Deep Cleaning Shampoo is for those people who suffer from mild seborrheic dermatitis and lice infestation. It is a rich lathering shampoo that is suitable for thorough deep cleansing. It leaves your hair soft and manageable while clearing the dead skin off your scalp.","Sulfur8 ","images/product_05.png");
INSERT INTO PRODUCT(name,qtyinstock,mrp,description,brand,imname) VALUES("Bioderma",60,375,"This highly acclaimed medicated shampoo contains an active ingredient called selenium sulfide that treats dandruff and scalp infection instantly. This shampoo is formulated with menthol that treats inflammation and prevents redness. It creates a rich lather that softens your hair. ","Selsun","images/product_01.png");

DROP TABLE IF EXISTS `CART`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CART` (
  `custid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `qty` int(11) DEFAULT '0',
  PRIMARY KEY (`custid`,`productid`),
  KEY `FK_CART_PRODUCT` (`productid`),
  CONSTRAINT `FK_CART_CUST` FOREIGN KEY (`custid`) REFERENCES `CUSTOMER` (`custid`),
  CONSTRAINT `FK_CART_PRODUCT` FOREIGN KEY (`productid`) REFERENCES `PRODUCT` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `ADMININFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ADMININFO` (
  `adminid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `doornum` varchar(10) DEFAULT NULL,
  `streetname` varchar(50) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  `uname` varchar(20) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `imid` int(11) DEFAULT '4',
  PRIMARY KEY (`adminid`),
  KEY `FK_ADMIN_REF_USER` (`uname`),
  -- KEY `FK_ADMININFO_IMAGE` (`imid`),
  CONSTRAINT `ADMININFO_ibfk_1` FOREIGN KEY (`uname`) REFERENCES `USERS` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
  -- CONSTRAINT `FK_ADMININFO_IMAGE` FOREIGN KEY (`imid`) REFERENCES `IMAGE` (`imgid`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


use test6;

DROP TABLE IF EXISTS `SHOPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SHOPS` (
  `shopid` int(11) NOT NULL AUTO_INCREMENT,
  `shopdescription` varchar(50) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `doornum` varchar(10) DEFAULT NULL,
  `streetname` varchar(50) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`shopid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `EMPLOYEE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMPLOYEE` (
  `employeeid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `doornum` varchar(10),
  `streetname` varchar(50),
  `city` varchar(30) ,
  `state` varchar(30) ,
  `pincode` varchar(10) ,
  `shopid` int(11) NOT NULL,
  `uname` varchar(20) NOT NULL,
  `salary` double DEFAULT '0',
  `hiringdate` date DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `imid` int(11) DEFAULT '4',
  PRIMARY KEY (`employeeid`),
  KEY `FK_EMPLOYEE_SHOPS` (`shopid`),
  KEY `FK_EMPLOYEE_USERS` (`uname`),
  -- KEY `FK_EMPLOYEE_IMAGE` (`imid`),
  -- CONSTRAINT `FK_EMPLOYEE_IMAGE` FOREIGN KEY (`imid`) REFERENCES `IMAGE` (`imgid`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_EMPLOYEE_SHOPS` FOREIGN KEY (`shopid`) REFERENCES `SHOPS` (`shopid`)  ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_EMPLOYEE_USERS` FOREIGN KEY (`uname`) REFERENCES `USERS` (`username`)  ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `ORDERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ORDERS` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `custid` int(11) NOT NULL,
  `assignedemployeeid` int(11) DEFAULT NULL,
  `assignedemployeestatus` varchar(30) DEFAULT 'Not Yet Assigned.',
  `paymentmode` varchar(20) NOT NULL,
  `totalamount` double NOT NULL,
  `orderstatus` varchar(30) DEFAULT NULL,
  `deliverydoornum` varchar(10) NOT NULL,
  `deliverystreetname` varchar(50) NOT NULL,
  `deliverycity` varchar(30) NOT NULL,
  `deliverystate` varchar(30) NOT NULL,
  `deliverypincode` varchar(10) NOT NULL,
  `deliveryphone` varchar(10) NOT NULL,
  `deliveringto` varchar(20) NOT NULL,
  `paymentstatus` varchar(20) DEFAULT 'Pending',
  `orderdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderid`),
  KEY `FK_ORDERS_CUSTOMER` (`custid`),
  KEY `FK_ORDERS_EMPLOYEE` (`assignedemployeeid`),
  CONSTRAINT `FK_ORDERS_CUSTOMER` FOREIGN KEY (`custid`) REFERENCES `CUSTOMER` (`custid`),
  CONSTRAINT `FK_ORDERS_EMPLOYEE` FOREIGN KEY (`assignedemployeeid`) REFERENCES `EMPLOYEE` (`employeeid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `ORDERPRODUCTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ORDERPRODUCTS` (
  `orderid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  `qty` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`orderid`,`productid`),
  KEY `FK_ORDERPRODUCTS_PRODUCT` (`productid`),
  CONSTRAINT `FK_ORDERPRODUCTS_ORDERS` FOREIGN KEY (`orderid`) REFERENCES `ORDERS` (`orderid`),
  CONSTRAINT `FK_ORDERPRODUCTS_PRODUCT` FOREIGN KEY (`productid`) REFERENCES `PRODUCT` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `SUPPLIERINFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SUPPLIERINFO` (
  `supplierid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `doornum` varchar(10) DEFAULT NULL,
  `streetname` varchar(50) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`supplierid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `SUPPLY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SUPPLY` (
  `supplierid` int(11) NOT NULL,
  `productid` int(11) NOT NULL,
  PRIMARY KEY (`supplierid`,`productid`),
  KEY `FK_SUPPLY_PRODUCT` (`productid`),
  CONSTRAINT `FK_SUPPLY_PRODUCT` FOREIGN KEY (`productid`) REFERENCES `PRODUCT` (`id`),
  CONSTRAINT `FK_SUPPLY_SUPPLIER` FOREIGN KEY (`supplierid`) REFERENCES `SUPPLIERINFO` (`supplierid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `FEEDBACK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FEEDBACK` (
  `feedbackid` int(11) NOT NULL AUTO_INCREMENT,
  `websiterating` int(11) DEFAULT NULL,
  `employeerating` int(11) DEFAULT NULL,
  `feedbacktext` varchar(300) DEFAULT NULL,
  `orderid` int(11) NOT NULL,
  `custid` int(11) NOT NULL,
  PRIMARY KEY (`feedbackid`),
  CONSTRAINT `FK_FEEDBACK_CUSTOMER` FOREIGN KEY (`custid`) REFERENCES `CUSTOMER` (`custid`),
  CONSTRAINT `FK_FEEDBACK_PRODUCT` FOREIGN KEY (`orderid`) REFERENCES `ORDERS` (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
SET FOREIGN_KEY_CHECKS=0;DROP TABLE IF EXISTS GS_VENDOR_MASTER;SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE GS_VENDOR_MASTER(
VENDOR_ID BIGINT(20) NOT NULL AUTO_INCREMENT,
user_id int(10) NOT NULL
parent_org_id int(11) NOT NULL,
org_id int(11) NOT NULL,
gs_vertical_id int(11) NOT NULL,
gs_business_unit_id int(11) NOT NULL,
ORGANIZATION VARCHAR(50),
COMPANY_NAME VARCHAR(20),
COMPANY_WEBSITE VARCHAR(100),
FIRST_NAME VARCHAR(20),
LAST_NAME VARCHAR(20),
DESIGNATION VARCHAR(20),
CONTACT VARCHAR(15),
EMAIL VARCHAR(40),
COMPANY_PAN_NO VARCHAR(10),
GST_NO VARCHAR(15),
SERVICE_TAX VARCHAR(10),
TAN_NO VARCHAR(20),
STATUS_OF_PERSON VARCHAR(10),
REGISTRATION_NO VARCHAR(20),
REGISTERING_AUTHORITY VARCHAR(20),
REGISTRATION_DATE VARCHAR(20),
PLACE_OF_REGISTRATION VARCHAR(20),
EXPERIENCE VARCHAR(3),
TURNOVER VARCHAR(20),
BANK_AC_NO VARCHAR(15),
BANK_NAME VARCHAR(15),
BANK_IFSC_CODE VARCHAR(15),
BANK_BRANCH VARCHAR(15),
BANK_ADDRESS VARCHAR(300),
ATTACHEMNT VARCHAR(50),
created_at timestamp NOT NULL,
created_by varchar(45) NOT NULL,
updated_at timestamp NOT NULL,
update_by varchar(45) NOT NULL,
PRIMARY KEY(VENDOR_ID),
CONSTRAINT `VENDOR_ORG` FOREIGN KEY (`org_id`) REFERENCES `t_gs_organaization` (`parent_org_id`),
CONSTRAINT `VENDOR_VERTICAL` FOREIGN KEY (`gs_vertical_id`) REFERENCES `t_gs_vertical` (`gs_vertical_id`),
CONSTRAINT `VENDOR_BUSINESSUNIT` FOREIGN KEY (`gs_business_unit_id`) REFERENCES `t_gs_business_unit` (`gs_business_unit_id`)
CONSTRAINT `VENDOR_USER` FOREIGN KEY (`user_id`) REFERENCES `t_gs_user` (`t_gs_user`)
);

CREATE TABLE GS_VENDOR_DIRECTOR(
ID  BIGINT(20) NOT NULL AUTO_INCREMENT,
VENDOR_ID BIGINT(20),
FIRST_NAME VARCHAR(20),
LAST_NAME VARCHAR(20),
DESIGNATION VARCHAR(15),
CONTACT_NO VARCHAR(15),
EMAIL_ID VARCHAR(40),
PAN_NO VARCHAR(10),
ADDRESS VARCHAR(300),
PINCODE VARCHAR(10),
PRIMARY KEY(ID),
FOREIGN KEY(VENDOR_ID) REFERENCES GS_VENDOR_MASTER(VENDOR_ID)
);


CREATE TABLE automate_globalsettings.GS_TAX_GROUP(
ID  BIGINT(20) NOT NULL AUTO_INCREMENT,
ORG_NAME VARCHAR(30),
TAX_GROUP VARCHAR(20),
STATUS VARCHAR(20),
PRIMARY KEY(ID)
);

CREATE TABLE automate_globalsettings.GS_TAX_SECTION(
ID  BIGINT(20) NOT NULL AUTO_INCREMENT,
TAX_SECTION_NAME VARCHAR(30),
STATUS VARCHAR(20),
PRIMARY KEY(ID)
);

CREATE TABLE automate_globalsettings.GS_TAX_ACCOUNT_NATURE(
ID  BIGINT(20) NOT NULL AUTO_INCREMENT,
TAX_NATURE VARCHAR(20),
TAX_GROUP VARCHAR(30),
TAX_ACCOUNT VARCHAR(20),
TAX_ACCOUNT_TYPE VARCHAR(20),
STATUS VARCHAR(20),
PRIMARY KEY(ID)
);

CREATE TABLE automate_globalsettings.GS_TAX_GROUP(
ID  BIGINT(20) NOT NULL AUTO_INCREMENT,
ORG_NAME VARCHAR(30),
TAX_GROUP VARCHAR(20),
STATUS VARCHAR(20),
PRIMARY KEY(ID)
);

CREATE TABLE automate_globalsettings.GS_TAX_MAPPING(
ID  BIGINT(20) NOT NULL AUTO_INCREMENT,
SERVICE_TYPE VARCHAR(30),
VENDOR_TYPE VARCHAR(20),
TAX_GROUP VARCHAR(20),
SUPPLY_TYPE VARCHAR(20),
TAX_ACCOUNT VARCHAR(20),
TAX_SECTION VARCHAR(20),
TAX_RATE_PERC VARCHAR(10),
EFFECTIVE_FROM VARCHAR(20),
EFFECTIVE_TO VARCHAR(20),
PRIMARY KEY(ID)
);


DROP TABLE IF EXISTS GS_GL_ACCOUNT;

CREATE TABLE automate_globalsettings.GS_GL_ACCOUNT(
ID  BIGINT(20) NOT NULL AUTO_INCREMENT,
GL_ACCOUNT_NAME VARCHAR(30),
GL_GROUP_NAME VARCHAR(20),
GL_SUB_GROUP_NAME VARCHAR(20),
GL_VISIBLE_TO_VENDOR VARCHAR(5),
GL_STATUS VARCHAR(10),
created_at timestamp NOT NULL,
created_by varchar(45) NOT NULL,
updated_at timestamp NOT NULL,
updated_by varchar(45) NOT NULL,
PRIMARY KEY(ID)
);

CREATE TABLE automate_globalsettings.GS_TAX_ACCOUNT(
ID  BIGINT(20) NOT NULL AUTO_INCREMENT,
TAX_ACCOUNT_NAME VARCHAR(30),
STATUS VARCHAR(20),
PRIMARY KEY(ID)
);

CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `line_no1` varchar(45) DEFAULT NULL,
  `line_no2` varchar(45) DEFAULT NULL,
  `line_no3` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `pincode` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `mandal` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_gs_contact_details` (
  `contact_id` int NOT NULL AUTO_INCREMENT,
  `contact_number` varchar(45) DEFAULT NULL,
  `pan_no` varchar(45) DEFAULT NULL,
  `gst_no` varchar(45) DEFAULT NULL,
  `primary_contact_person` varchar(45) DEFAULT NULL,
  `primary_contact_no` varchar(45) DEFAULT NULL,
  `primary_contact_email_id` varchar(45) DEFAULT NULL,
  `secondary_contact_email_id` varchar(45) DEFAULT NULL,
  `secondary_contact_person` varchar(45) DEFAULT NULL,
  `secondary_contact_no` varchar(45) DEFAULT NULL,
  `web_site_url` varchar(45) DEFAULT NULL,
  `google_location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_gs_organaization` (
  `parent_org_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `org_name` varchar(45) DEFAULT NULL,
  `org_desc` varchar(45) DEFAULT NULL,
  `org_logo` int DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  `contact_id` int DEFAULT NULL,
  PRIMARY KEY (`parent_org_id`),
  KEY `fk_user_id_idx` (`user_id`),
  KEY `fk_address_id_idx` (`address_id`),
  KEY `fk_contact_id_idx` (`contact_id`),
  CONSTRAINT `fk_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `fk_contact_id` FOREIGN KEY (`contact_id`) REFERENCES `t_gs_contact_details` (`contact_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_gs_user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_gs_user` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `t_gs_vertical` (
  `gs_vertical_id` int NOT NULL AUTO_INCREMENT,
  `org_name` varchar(45) DEFAULT NULL,
  `org_desc` varchar(45) DEFAULT NULL,
  `parent_org_id` int DEFAULT NULL,
  `org_logo` int DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  `address_id` int DEFAULT NULL,
  `contact_id` int DEFAULT NULL,
  PRIMARY KEY (`gs_vertical_id`),
  KEY `parent_org_id` (`parent_org_id`),
  KEY `fk_addess_id_idx` (`address_id`),
  KEY `fk_contact_id_idx` (`contact_id`),
  CONSTRAINT `t_gs_vertical_ibfk_1` FOREIGN KEY (`parent_org_id`) REFERENCES `t_gs_organaization` (`parent_org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



drop table if exists t_gs_dynamic_wf_approvers;
CREATE TABLE automate_globalsettings.t_gs_dynamic_wf_approvers(
id  BIGINT(20) NOT NULL AUTO_INCREMENT,
gs_dynamic_workflow_id int(11),
approver_id VARCHAR(20),
approver_name VARCHAR(15),
approver_type VARCHAR(15),
PRIMARY KEY(id),
FOREIGN KEY(gs_dynamic_workflow_id) REFERENCES t_gs_dynamic_workflow(gs_dynamic_workflow_id)
);

ALTER TABLE t_gs_sub_module
ADD COLUMN module_id int(11) DEFAULT NULL AFTER gs_sub_module_id;
ALTER TABLE t_gs_dynamic_workflow
ADD COLUMN sub_module_id int(11) DEFAULT NULL AFTER module_id 
ALTER TABLE t_gs_dynamic_workflow ADD
FOREIGN KEY (module_id) REFERENCES t_gs_module(gs_module_id)

ALTER TABLE t_gs_organaization
ADD COLUMN contact_id int; 


DROP TABLE IF EXISTS t_gs_dynamic_workflow;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE t_gs_dynamic_workflow (
   gs_dynamic_workflow_id int(11) NOT NULL AUTO_INCREMENT,
   parent_org_id int(11) NULL,
   gs_vertical_id int(11) NULL,
   gs_business_unit_id` int(11) NULL,
   module_id int(11),
   sub_module_id int(11),
  created_at timestamp NOT NULL,
  created_by varchar(45) NOT NULL,
  updated_at timestamp NOT NULL,
  update_by varchar(45) NOT NULL,
  PRIMARY KEY (gs_dynamic_workflow_id),
  CONSTRAINT t_gs_dynamic_workflow_ibfk_1 FOREIGN KEY (parent_org_id) REFERENCES t_gs_organaization (parent_org_id),
  CONSTRAINT t_gs_dynamic_workflow_ibfk_2 FOREIGN KEY (module_id) REFERENCES t_gs_module (gs_module_id),
  CONSTRAINT t_gs_dynamic_workflow_ibfk_3 FOREIGN KEY (sub_module_id) REFERENCES t_gs_sub_module (gs_sub_module_id),
  CONSTRAINT t_gs_dynamic_workflow_ibfk_4 FOREIGN KEY (gs_vertical_id) REFERENCES t_gs_vertical (gs_vertical_id),
  CONSTRAINT t_gs_dynamic_workflow_ibfk_5 FOREIGN KEY (gs_business_unit_id) REFERENCES t_gs_business_unit (gs_business_unit_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `t_gs_tax_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_gs_tax_group` (
  `gs_tax_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_org_id` int(11) DEFAULT NULL,
  `gs_vertical_id` int(11) NULL,
   `gs_business_unit_id` int(11) NULL,
  `select_org` varchar(45) DEFAULT NULL,
  `group_name` varchar(45) DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  PRIMARY KEY (`gs_tax_group_id`),
  KEY `parent_org_id` (`parent_org_id`),
  CONSTRAINT `t_gs_tax_group_ibfk_1` FOREIGN KEY (`parent_org_id`) REFERENCES `t_gs_organaization` (`parent_org_id`),
    CONSTRAINT `t_gs_tax_group_ibfk_2` FOREIGN KEY (`gs_vertical_id`) REFERENCES `t_gs_vertical` (`gs_vertical_id`),
  CONSTRAINT `t_gs_tax_group_ibfk_3` FOREIGN KEY (`gs_business_unit_id`) REFERENCES `t_gs_business_unit` (`gs_business_unit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


ALTER TABLE module_mappings
ADD COLUMN calendar_year_id int(11) ;

ALTER TABLE module_mappings
ADD COLUMN year_id int(11) ;

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `line_no1` varchar(45) DEFAULT NULL,
  `line_no2` varchar(45) DEFAULT NULL,
  `line_no3` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `pincode` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `mandal` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Bharat Hyundai','gachibowli','gachibowli','gachibowli','Hyderabad','Telangana','500507','India','Gowlidoddi','Financial District','2021-04-19 18:30:00','sindu','2021-04-19 18:30:00','sindu');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendar_year_mapping`
--

DROP TABLE IF EXISTS `calendar_year_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendar_year_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calendar_year_id` int(11) DEFAULT NULL,
  `month` varchar(45) DEFAULT NULL,
  `posting_period` int(11) DEFAULT NULL,
  `calendar_period` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `calendar_year_mapping_fk_idx` (`calendar_year_id`),
  CONSTRAINT `calendar_year_mapping_fk` FOREIGN KEY (`calendar_year_id`) REFERENCES `t_gs_calendar_year` (`gs_calendar_year_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar_year_mapping`
--

LOCK TABLES `calendar_year_mapping` WRITE;
/*!40000 ALTER TABLE `calendar_year_mapping` DISABLE KEYS */;
INSERT INTO `calendar_year_mapping` VALUES (1,1,'2020',1,'Q2'),(2,NULL,'February2021',1,'Q2'),(3,NULL,'March2021',2,'Q3'),(4,NULL,'April2021',3,'Q4'),(5,NULL,'May2021',4,'Q4'),(6,NULL,'June2021',5,'Q4'),(7,NULL,'July2021',6,'Q4'),(8,NULL,'August2021',7,'Q4'),(9,NULL,'September2021',8,'Q4'),(10,NULL,'October2021',9,'Q4'),(11,NULL,'November2021',10,'Q4'),(12,NULL,'December2021',11,'Q4'),(13,NULL,'2021',12,'Q4'),(14,12,'February2021',1,'Q2'),(15,12,'March2021',2,'Q3'),(16,12,'April2021',3,'Q4'),(17,12,'May2021',4,'Q4'),(18,12,'June2021',5,'Q4'),(19,12,'July2021',6,'Q4'),(20,12,'August2021',7,'Q4'),(21,12,'September2021',8,'Q4'),(22,12,'October2021',9,'Q4'),(23,12,'November2021',10,'Q4'),(24,12,'December2021',11,'Q4'),(25,12,'2021',12,'Q4');
/*!40000 ALTER TABLE `calendar_year_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country_location`
--

DROP TABLE IF EXISTS `country_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country_location` (
  `country_location_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_id` int(11) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `mandal` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `pincode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`country_location_id`),
  KEY `country_location_fk_idx` (`country_id`),
  CONSTRAINT `country_location_fk` FOREIGN KEY (`country_id`) REFERENCES `t_gs_country` (`gs_country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_location`
--

LOCK TABLES `country_location` WRITE;
/*!40000 ALTER TABLE `country_location` DISABLE KEYS */;
INSERT INTO `country_location` VALUES (1,1,'Hyderabad','State','Gowlododdi','Gachibowli','500075'),(2,NULL,'Hyderabad','Telangana','gowlidoddi','gachibowli','500037'),(8,NULL,'Hyderabad','Telangana','gowlidoddi','gachibowli','500037');
/*!40000 ALTER TABLE `country_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `financial_year_mapping`
--
LOCK TABLES `t_gs_financial_year` WRITE;
drop table if exists t_gs_financial_year;

LOCK TABLES `financial_year_mapping` WRITE;
drop table if exists financial_year_mapping;



DROP TABLE IF EXISTS `t_gs_financial_year`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_gs_financial_year` (
  `gs_financial_year_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_org_id` int(11) DEFAULT NULL,
  `financial_year` varchar(45) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`gs_financial_year_id`),
  KEY `t_gs_financial_year_fk_idx` (`parent_org_id`),
  CONSTRAINT `t_gs_financial_year_fk` FOREIGN KEY (`parent_org_id`) REFERENCES `t_gs_organaization` (`parent_org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `financial_year_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `financial_year_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gs_financial_year_id` int(11) DEFAULT NULL,
  `month` varchar(45) DEFAULT NULL,
  `posting_period` int(11) DEFAULT NULL,
  `calendar_period` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `financial_year_mapping_fk_idx` (`gs_financial_year_id`),
  KEY `financial_year_mapping_fk_idx2` (`gs_financial_year_id`),
  KEY `fk_fincicnalyear_idx` (`gs_financial_year_id`),
  KEY `fk_fincicnalyear_idx2` (`gs_financial_year_id`),
  CONSTRAINT `fk_fincicnalyear` FOREIGN KEY (`gs_financial_year_id`) REFERENCES `t_gs_financial_year` (`gs_financial_year_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping data for table `financial_year_mapping`
--

LOCK TABLES `financial_year_mapping` WRITE;
/*!40000 ALTER TABLE `financial_year_mapping` DISABLE KEYS */;
INSERT INTO `financial_year_mapping` VALUES (37,6,'February2021',1,'Q1'),(38,6,'March2022',2,'Q1'),(39,6,'April2022',3,'Q1'),(40,6,'May2022',4,'Q2'),(41,6,'June2022',5,'Q2'),(42,6,'July2022',6,'Q2'),(43,6,'August2022',7,'Q3'),(44,6,'September2022',8,'Q3'),(45,6,'October2022',9,'Q3'),(46,6,'November2022',10,'Q4'),(47,6,'December2022',11,'Q4');
/*!40000 ALTER TABLE `financial_year_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module_mappings`
--

DROP TABLE IF EXISTS `module_mappings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `module_mappings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) DEFAULT NULL,
  `sub_module_id` int(11) DEFAULT NULL,
  `parent_org_id` int(11) DEFAULT NULL,
  `relation_id` int(11) DEFAULT NULL,
  `relation_name` varchar(45) DEFAULT NULL,
  `created_at` varchar(45) NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` varchar(45) NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_org_id` (`parent_org_id`),
  KEY `module_map_submodue_fk_idx` (`sub_module_id`),
  KEY `module_mapping_module_fk_idx` (`module_id`),
  CONSTRAINT `module_map_submodue_fk` FOREIGN KEY (`sub_module_id`) REFERENCES `t_gs_sub_module` (`gs_sub_module_id`),
  CONSTRAINT `module_mapping_module_fk` FOREIGN KEY (`module_id`) REFERENCES `t_gs_module` (`gs_module_id`),
  CONSTRAINT `module_mappings_ibfk_1` FOREIGN KEY (`parent_org_id`) REFERENCES `t_gs_organaization` (`parent_org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module_mappings`
--

LOCK TABLES `module_mappings` WRITE;
/*!40000 ALTER TABLE `module_mappings` DISABLE KEYS */;
/*!40000 ALTER TABLE `module_mappings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_mappings`
--

DROP TABLE IF EXISTS `org_mappings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `org_mappings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `parent_org_id` int(11) DEFAULT NULL,
  `vertical_id` int(11) DEFAULT NULL,
  `business_unit_id` int(11) DEFAULT NULL,
  `relation_id` int(11) DEFAULT NULL,
  `relation_name` varchar(45) DEFAULT NULL,
  `module_mapping_id` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_org_id` (`parent_org_id`),
  KEY `org_mappings_fk_idx` (`user_id`),
  CONSTRAINT `org_mappings_fk` FOREIGN KEY (`user_id`) REFERENCES `t_gs_user` (`userid`),
  CONSTRAINT `org_mappings_ibfk_1` FOREIGN KEY (`parent_org_id`) REFERENCES `t_gs_organaization` (`parent_org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_mappings`
--

LOCK TABLES `org_mappings` WRITE;
/*!40000 ALTER TABLE `org_mappings` DISABLE KEYS */;
INSERT INTO `org_mappings` VALUES (1,1,1,1,1,1,'Financial year',1,'2021-04-20 18:30:00','sindu','2021-04-20 18:30:00','sindu');
/*!40000 ALTER TABLE `org_mappings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_gs_asset_group`
--


DROP TABLE IF EXISTS `t_gs_asset_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_gs_asset_group` (
  `gs_asset_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_org_id` int(11) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `asset_class_name` varchar(45) DEFAULT NULL,
  `asset_nature` varchar(45) DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  PRIMARY KEY (`gs_asset_group_id`),
  KEY `parent_org_id` (`parent_org_id`),
  CONSTRAINT `t_gs_asset_group_ibfk_1` FOREIGN KEY (`parent_org_id`) REFERENCES `t_gs_organaization` (`parent_org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_gs_asset_group`
--

LOCK TABLES `t_gs_asset_group` WRITE;
/*!40000 ALTER TABLE `t_gs_asset_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_gs_asset_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_gs_asset_nature`
--

DROP TABLE IF EXISTS `t_gs_asset_nature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_gs_asset_nature` (
  `gs_asset_nature_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_org_id` int(11) DEFAULT NULL,
  `org_name` varchar(45) DEFAULT NULL,
  `asset_nature_name` varchar(45) DEFAULT NULL,
  `asset_group` varchar(45) DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  PRIMARY KEY (`gs_asset_nature_id`),
  KEY `parent_org_id` (`parent_org_id`),
  CONSTRAINT `t_gs_asset_nature_ibfk_1` FOREIGN KEY (`parent_org_id`) REFERENCES `t_gs_organaization` (`parent_org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_gs_asset_nature`
--

LOCK TABLES `t_gs_asset_nature` WRITE;
/*!40000 ALTER TABLE `t_gs_asset_nature` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_gs_asset_nature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_gs_bank_details`
--

DROP TABLE IF EXISTS `t_gs_bank_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_gs_bank_details` (
  `gs_bank_details_id` int(11) NOT NULL AUTO_INCREMENT,
  `account_no` varchar(45) DEFAULT NULL,
  `bank_name` varchar(45) DEFAULT NULL,
  `ifsc_code` varchar(45) DEFAULT NULL,
  `branch` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `upload` varchar(45) DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  PRIMARY KEY (`gs_bank_details_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_gs_bank_details`
--

LOCK TABLES `t_gs_bank_details` WRITE;
/*!40000 ALTER TABLE `t_gs_bank_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_gs_bank_details` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `t_gs_calendar_year`;

DROP TABLE IF EXISTS `calendar_year_mapping`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_gs_calendar_year` (
  `gs_calendar_year_id` int(11) NOT NULL AUTO_INCREMENT,
  `calendar_year` varchar(45) DEFAULT NULL,
  `parent_org_id` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`gs_calendar_year_id`),
  KEY `t_gs_calendar_year_fk_idx` (`parent_org_id`),
  CONSTRAINT `t_gs_calendar_year_fk` FOREIGN KEY (`parent_org_id`) REFERENCES `t_gs_organaization` (`parent_org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



CREATE TABLE `calendar_year_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calendar_year_id` int(11) DEFAULT NULL,
  `month` varchar(45) DEFAULT NULL,
  `posting_period` int(11) DEFAULT NULL,
  `calendar_period` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `calendar_year_mapping_fk_idx` (`calendar_year_id`),
  CONSTRAINT `calendar_year_mapping_fk` FOREIGN KEY (`calendar_year_id`) REFERENCES `t_gs_calendar_year` (`gs_calendar_year_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `t_gs_business_unit`
--

DROP TABLE IF EXISTS `calendar_year_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendar_year_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `calendar_year_id` int(11) DEFAULT NULL,
  `month` varchar(45) DEFAULT NULL,
  `posting_period` int(11) DEFAULT NULL,
  `calendar_period` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `calendar_year_mapping_fk_idx` (`calendar_year_id`),
  CONSTRAINT `calendar_year_mapping_fk` FOREIGN KEY (`calendar_year_id`) REFERENCES `t_gs_calendar_year` (`gs_calendar_year_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


ALTER TABLE module_mappings
ADD COLUMN calendar_year_id int(11) ;

ALTER TABLE module_mappings
ADD COLUMN year_id int(11) ;

DROP TABLE IF EXISTS `t_gs_currency`;


CREATE TABLE `t_gs_currency` (
  `gs_currency_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_org_id` int(11) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  PRIMARY KEY (`gs_currency_id`),
  KEY `t_gs_currency_fk_idx` (`parent_org_id`),
  CONSTRAINT `t_gs_currency_fk` FOREIGN KEY (`parent_org_id`) REFERENCES `t_gs_organaization` (`parent_org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_gs_currency`
--


LOCK TABLES `t_gs_currency` WRITE;
/*!40000 ALTER TABLE `t_gs_currency` DISABLE KEYS */;
INSERT INTO `t_gs_currency` VALUES (1,1,'India','Indian Rupee','2021-04-15 18:30:00','sindu','2021-04-15 18:30:00','sindu');
/*!40000 ALTER TABLE `t_gs_currency` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `country_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country_location` (
  `country_location_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_id` int(11) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `mandal` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `pincode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`country_location_id`),
  KEY `country_location_fk_idx` (`country_id`),
  CONSTRAINT `country_location_fk` FOREIGN KEY (`country_id`) REFERENCES `t_gs_country` (`gs_country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_location`
--

LOCK TABLES `country_location` WRITE;
/*!40000 ALTER TABLE `country_location` DISABLE KEYS */;
INSERT INTO `country_location` VALUES (1,1,'Hyderabad','State','Gowlododdi','Gachibowli','500075'),(2,NULL,'Hyderabad','Telangana','gowlidoddi','gachibowli','500037'),(8,NULL,'Hyderabad','Telangana','gowlidoddi','gachibowli','500037');
/*!40000 ALTER TABLE `country_location` ENABLE KEYS */;
UNLOCK TABLES;

created_at timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  PRIMARY KEY (`gs_currency_id`),


ALTER TABLE country_location 
ADD COLUMN created_at timestamp;




ALTER TABLE country_location 
ADD COLUMN created_by varchar(45);

ALTER TABLE country_location 
ADD COLUMN updated_at timestamp;

ALTER TABLE country_location 
ADD COLUMN updated_by varchar(45);



ALTER TABLE t_gs_rule_configuration
ADD COLUMN status varchar(45);






----

SELECT  * FROM t_gs_organaization
SET FOREIGN_KEY_CHECKS=0; DROP TABLE IF EXISTS `t_gs_organaization`; SET FOREIGN_KEY_CHECKS=1;
commit;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_gs_organaization` (
  `parent_org_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `org_name` varchar(45) DEFAULT NULL,
  `org_desc` varchar(45) DEFAULT NULL,
  `org_logo` int DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  `contact_id` int DEFAULT NULL,
  PRIMARY KEY (`parent_org_id`),
  KEY `fk_user_id_idx` (`user_id`),
  KEY `fk_address_id_idx` (`address_id`),
  KEY `fk_contact_id_idx` (`contact_id`),
  CONSTRAINT `fk_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `fk_contact_id` FOREIGN KEY (`contact_id`) REFERENCES `t_gs_contact_details` (`contact_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_gs_user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


SET FOREIGN_KEY_CHECKS=0;DROP TABLE IF EXISTS `t_gs_user`;SET FOREIGN_KEY_CHECKS=1;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_gs_user` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `t_gs_user` WRITE;
/*!40000 ALTER TABLE `t_gs_user` DISABLE KEYS */;
INSERT INTO `t_gs_user` VALUES (1,'string','string','2021-04-18 08:06:46','string','2021-04-18 08:06:46','string'),(3,'test','string','2021-04-18 08:06:46','string','2021-04-18 08:06:46','string'),(5,'test1','string','2021-04-18 08:06:46','string','2021-04-18 08:06:46','string'),(6,'test5','string','2021-04-18 08:06:46','string','2021-04-18 08:06:46','string');
/*!40000 ALTER TABLE `t_gs_user` ENABLE KEYS */;
UNLOCK TABLES;
LOCK TABLES `t_gs_user` WRITE;




SET FOREIGN_KEY_CHECKS=0;DROP TABLE IF EXISTS `address`;SET FOREIGN_KEY_CHECKS=1;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  `line_no1` varchar(45) DEFAULT NULL,
  `line_no2` varchar(45) DEFAULT NULL,
  `line_no3` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `pincode` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `mandal` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'string','2021-04-18 08:06:46','string','2021-04-18 08:06:46','string','string','string','string','string','string','string','string','string','string'),(2,'string','2021-04-18 08:06:46','string','2021-04-18 08:06:46','string','string','string','string','string','string','string','string','string','string');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;


SET FOREIGN_KEY_CHECKS=0;DROP TABLE IF EXISTS `t_gs_contact_details`;SET FOREIGN_KEY_CHECKS=1;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_gs_contact_details` (
  `contact_id` int NOT NULL AUTO_INCREMENT,
  `contact_number` varchar(45) DEFAULT NULL,
  `pan_no` varchar(45) DEFAULT NULL,
  `gst_no` varchar(45) DEFAULT NULL,
  `primary_contact_person` varchar(45) DEFAULT NULL,
  `primary_contact_no` varchar(45) DEFAULT NULL,
  `primary_contact_email_id` varchar(45) DEFAULT NULL,
  `secondary_contact_email_id1` varchar(45) DEFAULT NULL,
  `secondary_contact_person1` varchar(45) DEFAULT NULL,
  `secondary_contact_no1` varchar(45) DEFAULT NULL,
  `web_site_url` varchar(45) DEFAULT NULL,
  `google_location` varchar(45) DEFAULT NULL,
  `secondary_contact_email_id2` varchar(45) DEFAULT NULL,
  `secondary_contact_person2` varchar(45) DEFAULT NULL,
  `secondary_contact_no2` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_gs_contact_details`
--

LOCK TABLES `t_gs_contact_details` WRITE;
/*!40000 ALTER TABLE `t_gs_contact_details` DISABLE KEYS */;
INSERT INTO `t_gs_contact_details` VALUES (2,'string','string','string','string','string','string','string','string','string','string','string',NULL,NULL,NULL),(3,'string','string','string','string','string','string','string','string','string','string','string',NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_gs_contact_details` ENABLE KEYS */;
UNLOCK TABLES;





LOCK TABLES `t_gs_organaization` WRITE;
/*!40000 ALTER TABLE `t_gs_organaization` DISABLE KEYS */;
INSERT INTO `t_gs_organaization` VALUES (1,1,'string','string',0,NULL,'2021-04-18 08:06:46','string','2021-04-18 08:06:46','string',NULL),(4,3,'string','string',0,NULL,'2021-04-18 08:06:46','string','2021-04-18 08:06:46','string',NULL),(5,5,'string','string',0,NULL,'2021-04-18 08:06:46','string','2021-04-18 08:06:46','string',2),(6,6,'string','string',0,NULL,'2021-04-18 08:06:46','string','2021-04-18 08:06:46','string',3);
/*!40000 ALTER TABLE `t_gs_organaization` ENABLE KEYS */;
UNLOCK TABLES;



SET FOREIGN_KEY_CHECKS=0;DROP TABLE IF EXISTS `t_gs_vertical`;SET FOREIGN_KEY_CHECKS=1;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_gs_vertical` (
  `gs_vertical_id` int NOT NULL AUTO_INCREMENT,
  `org_name` varchar(45) DEFAULT NULL,
  `org_desc` varchar(45) DEFAULT NULL,
  `parent_org_id` int DEFAULT NULL,
  `org_logo` int DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  `address_id` int DEFAULT NULL,
  `contact_id` int DEFAULT NULL,
  PRIMARY KEY (`gs_vertical_id`),
  KEY `parent_org_id` (`parent_org_id`),
  KEY `fk_addess_id_idx` (`address_id`),
  KEY `fk_contact_id_idx` (`contact_id`),
  CONSTRAINT `t_gs_vertical_ibfk_1` FOREIGN KEY (`parent_org_id`) REFERENCES `t_gs_organaization` (`parent_org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


LOCK TABLES `t_gs_vertical` WRITE;
/*!40000 ALTER TABLE `t_gs_vertical` DISABLE KEYS */;
INSERT INTO `t_gs_vertical` VALUES (1,'string','string',NULL,0,'2021-04-18 08:06:46','string','2021-04-18 08:06:46','string',NULL,NULL),(2,'string','string',NULL,0,'2021-04-18 08:06:46','string','2021-04-18 08:06:46','string',NULL,NULL);
/*!40000 ALTER TABLE `t_gs_vertical` ENABLE KEYS */;
UNLOCK TABLES;


SET FOREIGN_KEY_CHECKS=0;DROP TABLE IF EXISTS `t_gs_business_unit`;SET FOREIGN_KEY_CHECKS=1;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_gs_business_unit` (
  `gs_business_unit_id` int NOT NULL AUTO_INCREMENT,
  `business_unit_name` varchar(45) DEFAULT NULL,
  `business_unit_desc` varchar(45) DEFAULT NULL,
  `business_unit_logo` int DEFAULT NULL,
  `oem_type` varchar(45) DEFAULT NULL,
  `dealer_code` varchar(45) DEFAULT NULL,
  `sub_dealer_name` varchar(45) DEFAULT NULL,
  `sub_dealer_code` varchar(45) DEFAULT NULL,
  `website_url` varchar(45) DEFAULT NULL,
  `google_location` varchar(45) DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  `address_id` int DEFAULT NULL,
  `contact_id` int DEFAULT NULL,
  PRIMARY KEY (`gs_business_unit_id`),
  KEY `fk_address_id_idx` (`address_id`),
  KEY `fk_contact_id_idx` (`contact_id`),
  CONSTRAINT `fk_address_idx` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `fk_contact_idx` FOREIGN KEY (`contact_id`) REFERENCES `t_gs_contact_details` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_gs_business_unit`
--
drop table if exists t_gs_organaization_addresses;
CREATE TABLE t_gs_organaization_addresses(
tgs_organaization_parent_org_id int,
addresses_address_id int,
CONSTRAINT fk_address_id1 FOREIGN KEY (tgs_organaization_parent_org_id) REFERENCES t_gs_organaization(parent_org_id),
CONSTRAINT fk_contact_id2 FOREIGN KEY (addresses_address_id) REFERENCES address (address_id)
);

drop table if exists automate_globalsettings.t_gs_vertical_addresses;
CREATE TABLE automate_globalsettings.t_gs_vertical_addresses(
tgs_vertical_gs_vertical_id int,
addresses_address_id int,
CONSTRAINT fk_address_id3 FOREIGN KEY (tgs_vertical_gs_vertical_id) REFERENCES t_gs_vertical(gs_vertical_id),
CONSTRAINT fk_contact_id4 FOREIGN KEY (addresses_address_id) REFERENCES address (address_id)
);


drop table if exists automate_globalsettings.t_gs_business_unit_addresses;
CREATE TABLE automate_globalsettings.t_gs_business_unit_addresses(
tgs_business_unit_gs_business_unit_id int,
addresses_address_id int,
CONSTRAINT fk_address_id5 FOREIGN KEY (tgs_business_unit_gs_business_unit_id) REFERENCES t_gs_business_unit(gs_business_unit_id),
CONSTRAINT fk_contact_id6 FOREIGN KEY (addresses_address_id) REFERENCES address (address_id)
);



LOCK TABLES `t_gs_business_unit` WRITE;
/*!40000 ALTER TABLE `t_gs_business_unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_gs_business_unit` ENABLE KEYS */;
UNLOCK TABLES;




ALTER TABLE t_gs_organaization  ADD COLUMN status varchar(10);
ALTER TABLE t_gs_vertical  ADD COLUMN status varchar(10);
ALTER TABLE t_gs_business_unit  ADD COLUMN status varchar(10);



ALTER TABLE t_gs_business_unit  MODIFY COLUMN business_unit_logo varchar(200);
ALTER TABLE t_gs_vertical  MODIFY org_logo varchar(200);
ALTER TABLE t_gs_organaization  MODIFY COLUMN org_logo  varchar(200);

ALTER TABLE t_gs_business_unit  ADD COLUMN parent_org_id int;
ALTER TABLE t_gs_business_unit  ADD COLUMN gs_vertical_id int;

ALTER TABLE t_gs_module ADD COLUMN status varchar(10);
ALTER TABLE t_gs_calendar_year  ADD COLUMN status  varchar(10);


LOCK TABLES t_gs_tax_account WRITE;
LOCK TABLES t_gs_tax_mapping WRITE;
LOCK TABLES GS_TAX_SECTION WRITE;

ALTER TABLE t_gs_tax_account ADD COLUMN status varchar(10);
ALTER TABLE t_gs_tax_mapping ADD COLUMN status varchar(10);

ALTER TABLE t_gs_tax_account ADD COLUMN gs_vertical_id int;
ALTER TABLE t_gs_tax_account ADD COLUMN gs_business_unit_id int;
ALTER TABLE t_gs_tax_mapping ADD COLUMN gs_vertical_id int;
ALTER TABLE t_gs_tax_mapping ADD COLUMN gs_business_unit_id int;

ALTER TABLE t_gs_tax_section ADD COLUMN status varchar(10);
ALTER TABLE t_gs_tax_section ADD COLUMN gs_vertical_id int;
ALTER TABLE t_gs_tax_section ADD COLUMN gs_business_unit_id int;


ALTER TABLE GS_TAX_SECTION ADD COLUMN parent_org_id int;
ALTER TABLE GS_TAX_SECTION ADD COLUMN gs_vertical_id int;
ALTER TABLE GS_TAX_SECTION ADD COLUMN gs_business_unit_id int;



SET FOREIGN_KEY_CHECKS=0;DROP TABLE IF EXISTS `t_gs_tax_group`;SET FOREIGN_KEY_CHECKS=0;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_gs_tax_group` (
  `gs_tax_group_id` int NOT NULL AUTO_INCREMENT,
  `parent_org_id` int DEFAULT NULL,
  `gs_vertical_id` int DEFAULT NULL,
  `gs_business_unit_id` int DEFAULT NULL,
  `select_org` varchar(45) DEFAULT NULL,
  `group_name` varchar(45) DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  PRIMARY KEY (`gs_tax_group_id`),
  KEY `parent_org_id` (`parent_org_id`),
  KEY `t_gs_tax_group_ibfk_2` (`gs_vertical_id`),
  KEY `t_gs_tax_group_ibfk_3` (`gs_business_unit_id`),
  CONSTRAINT `t_gs_tax_group_ibfk_1` FOREIGN KEY (`parent_org_id`) REFERENCES `t_gs_organaization` (`parent_org_id`),
  CONSTRAINT `t_gs_tax_group_ibfk_2` FOREIGN KEY (`gs_vertical_id`) REFERENCES `t_gs_vertical` (`gs_vertical_id`),
  CONSTRAINT `t_gs_tax_group_ibfk_3` FOREIGN KEY (`gs_business_unit_id`) REFERENCES `t_gs_business_unit` (`gs_business_unit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


SET FOREIGN_KEY_CHECKS=0;DROP TABLE IF EXISTS t_gs_user;SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `t_gs_user` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `created_by` varchar(45) DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
SET FOREIGN_KEY_CHECKS=0;DROP TABLE IF EXISTS t_gs_organaization;SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `t_gs_organaization` (
  `parent_org_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `org_name` varchar(45) DEFAULT NULL,
  `org_desc` varchar(45) DEFAULT NULL,
  `org_logo` varchar(200) DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  `created_at` timestamp NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `updated_at` timestamp NOT NULL,
  `updated_by` varchar(45) NOT NULL,
  `contact_id` int DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`parent_org_id`),
  KEY `fk_user_id_idx` (`user_id`),
  KEY `fk_address_id_idx` (`address_id`),
  KEY `fk_contact_id_idx` (`contact_id`),
  CONSTRAINT `fk_address_id` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `fk_contact_id` FOREIGN KEY (`contact_id`) REFERENCES `t_gs_contact_details` (`contact_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `t_gs_user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


ALTER TABLE t_gs_income_gl_head ADD COLUMN status varchar(10);

ALTER TABLE t_gs_organaization ADD COLUMN gs_vertical_id int;


ALTER TABLE t_gs_income_gl_head ADD COLUMN status varchar(10);

ALTER TABLE t_gs_profit_center ADD COLUMN status varchar(10);
ALTER TABLE t_gs_asset_nature ADD COLUMN status varchar(10);
ALTER TABLE t_gs_asset_group ADD COLUMN status varchar(10);
ALTER TABLE t_gs_liability_gl_subhead ADD COLUMN status varchar(10);

ALTER TABLE t_gs_liability_gl_head ADD COLUMN status varchar(10);


address_id


ALTER TABLE t_gs_business_unit ADD COLUMN address_id int;

ALTER TABLE address ADD COLUMN c_line_no1 varchar(100);
ALTER TABLE address ADD COLUMN c_line_no2 varchar(100);
ALTER TABLE address ADD COLUMN c_line_no3varchar(100);
ALTER TABLE address ADD COLUMN c_checkbox varchar(30);


ALTER TABLE address ADD COLUMN c_line_no1 varchar(100);
ALTER TABLE address ADD COLUMN c_line_no2 varchar(100);
ALTER TABLE address ADD COLUMN c_line_no3varchar(100);
ALTER TABLE address ADD COLUMN c_checkbox varchar(30);


Create table t_gs_subdealer(
gs_subdealer_id INT,
subDealerName VARCHAR(10),
subDealerCode VARCHAR(10),
subDealerAddress VARCHAR(10)
);


CREATE TABLE `highschool` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `gender` varchar(25) NOT NULL,
  `ip_address` varchar(45) NOT NULL,
  `cm_height` int NOT NULL,
  `age` int NOT NULL,
  `has_car` tinyint NOT NULL DEFAULT '0',
  `car_color` varchar(20) DEFAULT 'didnt choose',
  `grade` int NOT NULL,
  `grade_avg` float NOT NULL,
  `identification` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `identification_UNIQUE` (`identification`),
  KEY `idx_sima_table_id` (`id`)
)
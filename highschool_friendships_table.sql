CREATE TABLE `highschool_friendships` (
  `id` int NOT NULL AUTO_INCREMENT,
  `friend_id` int DEFAULT NULL,
  `other_friend_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`friend_id`,`other_friend_id`),
  KEY `id_idx1` (`other_friend_id`),
  CONSTRAINT `id` FOREIGN KEY (`other_friend_id`) REFERENCES `highschool` (`id`)
)
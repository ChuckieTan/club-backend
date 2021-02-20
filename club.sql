-- MariaDB dump 10.18  Distrib 10.5.8-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: club
-- ------------------------------------------------------
-- Server version	10.5.8-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `club`
--

DROP TABLE IF EXISTS `club`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club`
(
    `club_id`              int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '社团ID',
    `name`                 varchar(16)      NOT NULL COMMENT '社团名字',
    `avatar_id`            int(10) unsigned DEFAULT NULL COMMENT '头像ID',
    `introduction`         text             DEFAULT NULL COMMENT '社团介绍',
    `type`                 int(11)          DEFAULT NULL COMMENT '社团类型',
    `consultant_name`      varchar(8)       DEFAULT NULL COMMENT '指导教师姓名',
    `consultant_tel`       varchar(16)      DEFAULT NULL COMMENT '指导教师电话',
    `initiator_id`         int(10) unsigned DEFAULT NULL COMMENT '发起人用户ID',
    `core_values`          text             DEFAULT NULL COMMENT '社团宗旨',
    `plans`                text             DEFAULT NULL COMMENT '主要活动计划',
    `practically_analysis` text             DEFAULT NULL COMMENT '可行性分析',
    `progress`             int(11)          DEFAULT NULL COMMENT '申请进度',
    `apply_time`           datetime         DEFAULT NULL,
    PRIMARY KEY (`club_id`) USING BTREE,
    KEY `fk_club_avatar_picture_path` (`avatar_id`) USING BTREE,
    KEY `fk_club_initiator_user` (`initiator_id`) USING BTREE,
    CONSTRAINT `fk_club_avatar_path` FOREIGN KEY (`avatar_id`) REFERENCES `image_path` (`image_id`),
    CONSTRAINT `fk_club_initiator_user` FOREIGN KEY (`initiator_id`) REFERENCES `user` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `club_member`
--

DROP TABLE IF EXISTS `club_member`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `club_member`
(
    `club_id`      int(10) unsigned NOT NULL COMMENT '社团ID',
    `user_id`      int(10) unsigned NOT NULL COMMENT '用户ID',
    `role`         int(11)          NOT NULL COMMENT '用户角色',
    `introduction` text             DEFAULT NULL COMMENT '个人简介',
    `is_agree`     tinyint(1)       DEFAULT NULL COMMENT '是否同意',
    `apply_time`   datetime         DEFAULT NULL COMMENT '申请时间',
    `grade`        int(10) unsigned DEFAULT NULL COMMENT '申请年级',
    PRIMARY KEY (`club_id`, `user_id`) USING BTREE,
    KEY `fk_member_user` (`user_id`) USING BTREE,
    CONSTRAINT `fk_member_club` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`),
    CONSTRAINT `fk_member_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `image_path`
--

DROP TABLE IF EXISTS `image_path`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image_path`
(
    `image_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '图片ID',
    `path`     varchar(255)     NOT NULL COMMENT '图片路径',
    PRIMARY KEY (`image_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `login_data`
--

DROP TABLE IF EXISTS `login_data`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_data`
(
    `login_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '登录ID',
    `time`     datetime    DEFAULT NULL COMMENT '登录时间',
    `ip`       varchar(16) DEFAULT NULL COMMENT '登录IP地址',
    `user_id`  int(10) unsigned NOT NULL COMMENT '登录用户ID',
    `device`   varchar(16) DEFAULT NULL COMMENT '登录设备',
    PRIMARY KEY (`login_id`) USING BTREE,
    KEY `fk_login_data_user` (`user_id`) USING BTREE,
    CONSTRAINT `fk_login_data_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message`
(
    `message_id`   int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '消息ID',
    `release_time` datetime                  DEFAULT NULL COMMENT '消息发布时间',
    `title`        text             NOT NULL COMMENT '消息标题',
    `content`      text                      DEFAULT NULL COMMENT '消息内容',
    `club_id`      int(11) unsigned          DEFAULT NULL COMMENT '消息对应社团ID',
    `draft_mark`   tinyint(1)       NOT NULL DEFAULT 1 COMMENT '是否草稿',
    `type`         int(11)                   DEFAULT NULL COMMENT '消息类型',
    `author_id`    int(10) unsigned NOT NULL COMMENT '作者',
    PRIMARY KEY (`message_id`) USING BTREE,
    KEY `fk_message_club` (`club_id`) USING BTREE,
    CONSTRAINT `fk_message_club` FOREIGN KEY (`club_id`) REFERENCES `club` (`club_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message_read`
--

DROP TABLE IF EXISTS `message_read`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_read`
(
    `user_id`    int(10) unsigned NOT NULL COMMENT '用户ID',
    `message_id` int(10) unsigned NOT NULL COMMENT '消息ID',
    `read_time`  datetime DEFAULT NULL COMMENT '阅读时间',
    PRIMARY KEY (`user_id`, `message_id`) USING BTREE,
    KEY `fk_read_message` (`message_id`) USING BTREE,
    CONSTRAINT `fk_read_message` FOREIGN KEY (`message_id`) REFERENCES `message` (`message_id`),
    CONSTRAINT `fk_read_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user`
(
    `user_id`         int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `number`          varchar(16)               DEFAULT NULL COMMENT '用户学号',
    `password`        varchar(16)      NOT NULL DEFAULT '123456' COMMENT '用户密码',
    `nickname`        varchar(16)               DEFAULT NULL COMMENT '用户昵称',
    `realname`        varchar(8)                DEFAULT NULL COMMENT '用户真实姓名',
    `sex`             char(1)                   DEFAULT NULL COMMENT '用户性别',
    `enrollment_date` datetime                  DEFAULT NULL COMMENT '入学时间',
    `graduation_date` datetime                  DEFAULT NULL COMMENT '毕业时间',
    `introduction`    text                      DEFAULT NULL COMMENT '自我介绍',
    `avatar_id`       int(10) unsigned          DEFAULT NULL COMMENT '头像ID',
    `profession`      int(11)                   DEFAULT NULL COMMENT '专业代号',
    `email`           varchar(32)               DEFAULT NULL COMMENT 'Email',
    `tel`             varchar(16)               DEFAULT NULL COMMENT '电话号码',
    `qq`              varchar(16)               DEFAULT NULL COMMENT 'QQ号',
    PRIMARY KEY (`user_id`) USING BTREE,
    KEY `fk_user_picture_path` (`avatar_id`) USING BTREE,
    CONSTRAINT `fk_user_avatar_path` FOREIGN KEY (`avatar_id`) REFERENCES `image_path` (`image_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2021-02-20 19:17:21

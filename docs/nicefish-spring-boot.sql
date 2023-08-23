/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.5.9-MariaDB : Database - nicefish-spring-boot-test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='MySQL 没有 sequence 机制，用表来模拟。';

/*Data for the table `hibernate_sequence` */

/*Table structure for table `nicefish_cms_comment` */

DROP TABLE IF EXISTS `nicefish_cms_comment`;

CREATE TABLE `nicefish_cms_comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `content` text DEFAULT NULL,
  `comment_time` datetime NOT NULL DEFAULT current_timestamp(),
  `comment_ip` varchar(64) DEFAULT NULL COMMENT '评论者的IP地址',
  `p_id` int(11) DEFAULT -1 COMMENT '父层评论的ID，用来实现评论盖楼效果',
  `user_id` int(11) NOT NULL,
  `user_name` varchar(64) DEFAULT NULL,
  `nick_name` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `status` int(11) DEFAULT 1 COMMENT '评论状态：0：已删除；1：已发布；2:优质评论；',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

/*Data for the table `nicefish_cms_comment` */

insert  into `nicefish_cms_comment`(`comment_id`,`post_id`,`content`,`comment_time`,`comment_ip`,`p_id`,`user_id`,`user_name`,`nick_name`,`email`,`status`) values 
(19,42,'bbbbbbbbbbbbbbbb','2023-08-08 15:24:47',NULL,NULL,2,'admin@126.com','admin',NULL,NULL),
(20,43,'测试评论测试评论测试评论测试评论测试评论测试评论测试评论测试评论测试评论','2023-08-08 18:26:18',NULL,NULL,2,'admin@126.com','admin',NULL,NULL),
(21,46,'a','2023-08-12 13:39:50',NULL,NULL,2,'admin@126.com','admin',NULL,NULL),
(22,46,'b','2023-08-13 22:09:36',NULL,NULL,2,'admin@126.com','admin',NULL,NULL),
(23,46,'c','2023-08-13 22:10:00',NULL,NULL,2,'admin@126.com','admin',NULL,NULL),
(24,46,'d','2023-08-13 22:16:08',NULL,NULL,2,'admin@126.com','admin',NULL,NULL),
(25,46,'e','2023-08-13 22:43:56',NULL,NULL,2,'admin@126.com','admin',NULL,NULL),
(26,46,'f','2023-08-13 22:48:42',NULL,NULL,2,'admin@126.com','admin',NULL,NULL),
(27,47,'a','2023-08-14 13:36:57',NULL,NULL,2,'admin@126.com','admin',NULL,NULL),
(28,47,'a','2023-08-14 13:37:01',NULL,NULL,2,'admin@126.com','admin',NULL,NULL),
(29,47,'b','2023-08-14 13:37:04',NULL,NULL,2,'admin@126.com','admin',NULL,NULL),
(30,47,'c','2023-08-14 13:37:08',NULL,NULL,2,'admin@126.com','admin',NULL,NULL),
(31,47,'e','2023-08-14 13:37:16',NULL,NULL,2,'admin@126.com','admin',NULL,NULL);

/*Table structure for table `nicefish_cms_file_upload` */

DROP TABLE IF EXISTS `nicefish_cms_file_upload`;

CREATE TABLE `nicefish_cms_file_upload` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `file_name` varchar(255) NOT NULL COMMENT '文件名，实际存储此文件的名称',
  `display_name` varchar(255) NOT NULL COMMENT '文件的展示名称，不带后缀',
  `file_suffix` varchar(32) DEFAULT NULL COMMENT '文件的后缀',
  `file_size` bigint(20) DEFAULT 0,
  `path` varchar(255) NOT NULL COMMENT '文件的存储路径',
  `url` varchar(255) DEFAULT NULL COMMENT '访问此文件的路径，可以指向系统外部的 URL 。',
  `file_desc` varchar(255) DEFAULT NULL,
  `display_order` int(11) NOT NULL DEFAULT 1 COMMENT '显示顺序',
  `up_time` datetime NOT NULL DEFAULT current_timestamp(),
  `user_id` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=352 DEFAULT CHARSET=utf8mb4 COMMENT='上传文件的记录。';

/*Data for the table `nicefish_cms_file_upload` */

insert  into `nicefish_cms_file_upload`(`id`,`file_name`,`display_name`,`file_suffix`,`file_size`,`path`,`url`,`file_desc`,`display_order`,`up_time`,`user_id`) values 
(316,'1690612766124.jpg','brotherhood-at-sunset-1-1244631','jpg',501147,'C:\\nicefish\\upload\\1690612766124.jpg',NULL,NULL,1,'2023-07-29 14:39:26',NULL),
(317,'1690612779212.jpg','door-1192256','jpg',2515675,'C:\\nicefish\\upload\\1690612779212.jpg',NULL,NULL,1,'2023-07-29 14:39:39',NULL),
(318,'1690612792114.jpg','door-1552484','jpg',1022575,'C:\\nicefish\\upload\\1690612792114.jpg',NULL,NULL,1,'2023-07-29 14:39:52',NULL),
(319,'1690612806614.jpg','jellyfish-1507290','jpg',1461094,'C:\\nicefish\\upload\\1690612806614.jpg',NULL,NULL,1,'2023-07-29 14:40:06',NULL),
(320,'1690612819222.jpg','people-1057167','jpg',1254042,'C:\\nicefish\\upload\\1690612819222.jpg',NULL,NULL,1,'2023-07-29 14:40:19',NULL),
(321,'1690612833275.jpg','peppers-falling-into-water-1142701','jpg',1465019,'C:\\nicefish\\upload\\1690612833275.jpg',NULL,NULL,1,'2023-07-29 14:40:33',NULL),
(322,'1690612846330.jpg','prague-conference-center-1056491','jpg',2556197,'C:\\nicefish\\upload\\1690612846330.jpg',NULL,NULL,1,'2023-07-29 14:40:46',NULL),
(323,'1690612953697.jpg','imaad-whd-YEiqu8XitRI-unsplash','jpg',1506525,'C:\\nicefish\\upload\\1690612953697.jpg',NULL,NULL,1,'2023-07-29 14:42:33',NULL),
(324,'1690612972485.jpg','brian-gomes-n_qeUPl4QCM-unsplash','jpg',1166755,'C:\\nicefish\\upload\\1690612972485.jpg',NULL,NULL,1,'2023-07-29 14:42:52',NULL),
(325,'1690613015458.jpg','peppers-falling-into-water-1142701','jpg',1465019,'C:\\nicefish\\upload\\1690613015458.jpg',NULL,NULL,1,'2023-07-29 14:43:35',NULL),
(326,'1690643250922.jpg','1690612819222','jpg',1254042,'C:\\nicefish\\upload\\1690643250922.jpg',NULL,NULL,1,'2023-07-29 23:07:30',NULL),
(327,'1690643251080.jpg','1690612833275','jpg',1465019,'C:\\nicefish\\upload\\1690643251080.jpg',NULL,NULL,1,'2023-07-29 23:07:31',NULL),
(328,'1690643251086.jpg','1690612846330','jpg',2556197,'C:\\nicefish\\upload\\1690643251086.jpg',NULL,NULL,1,'2023-07-29 23:07:31',NULL),
(329,'1690643251089.jpg','1690612953697','jpg',1506525,'C:\\nicefish\\upload\\1690643251089.jpg',NULL,NULL,1,'2023-07-29 23:07:31',NULL),
(330,'1690643251093.jpg','1690612972485','jpg',1166755,'C:\\nicefish\\upload\\1690643251093.jpg',NULL,NULL,1,'2023-07-29 23:07:31',NULL),
(331,'1690643251096.jpg','1690613015458','jpg',1465019,'C:\\nicefish\\upload\\1690643251096.jpg',NULL,NULL,1,'2023-07-29 23:07:31',NULL),
(332,'51b6d23616ce9f36cf1824777477dd4958937f1654eed0ae5adb64a26fc7230b.png','1690554964422','png',9099,'C:\\nicefish\\upload\\51b6d23616ce9f36cf1824777477dd4958937f1654eed0ae5adb64a26fc7230b.png',NULL,NULL,1,'2023-08-08 18:25:53',NULL),
(333,'cf016e1f407bea6c73a7918012d4e2c48743fb1541beda7c739f15c350b30370.png','1690554964542','png',11864,'C:\\nicefish\\upload\\cf016e1f407bea6c73a7918012d4e2c48743fb1541beda7c739f15c350b30370.png',NULL,NULL,1,'2023-08-08 18:25:57',NULL),
(334,'11b38a23f584480d6fbc584248e3bac0473e491d450f65df42604d3ae9d4815c.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\11b38a23f584480d6fbc584248e3bac0473e491d450f65df42604d3ae9d4815c.mp4',NULL,NULL,1,'2023-08-09 19:00:47',NULL),
(335,'00afaa21ff0ad6e8590a6bacf725ae1b9876b20b49b05516e9d3b538bfe591da.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\00afaa21ff0ad6e8590a6bacf725ae1b9876b20b49b05516e9d3b538bfe591da.mp4',NULL,NULL,1,'2023-08-09 19:07:27',NULL),
(336,'eeec96abb3a120c4465de4d45992fceac8f2b91d7d37afb12a5ddf9a2e4d18e6.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\eeec96abb3a120c4465de4d45992fceac8f2b91d7d37afb12a5ddf9a2e4d18e6.mp4',NULL,NULL,1,'2023-08-09 19:09:12',NULL),
(337,'368b7ac3aff0615409696729d4fa094b1bea581adebe7cc0a2ce10657e64f043.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\368b7ac3aff0615409696729d4fa094b1bea581adebe7cc0a2ce10657e64f043.mp4',NULL,NULL,1,'2023-08-09 19:09:24',NULL),
(338,'93006b275f1a9b7ef3198cbb7a9259fbd2318786f89f3100f4a4335f9abd0293.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\93006b275f1a9b7ef3198cbb7a9259fbd2318786f89f3100f4a4335f9abd0293.mp4',NULL,NULL,1,'2023-08-09 19:11:42',NULL),
(339,'2e5bfcb07ab8cd3e0df302671c90ab681e0438368aa471cde87eaf40deae4fb8.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\2e5bfcb07ab8cd3e0df302671c90ab681e0438368aa471cde87eaf40deae4fb8.mp4',NULL,NULL,1,'2023-08-09 19:15:15',NULL),
(340,'8ccc7be23fc7cb2847cf5fbe32110d8280bd8802d51be13d4a699c88566fcb85.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\8ccc7be23fc7cb2847cf5fbe32110d8280bd8802d51be13d4a699c88566fcb85.mp4',NULL,NULL,1,'2023-08-09 19:17:51',NULL),
(341,'dd32214b87c7a56ddd9f4459bf29aea8561b30b23c94840862b92f188fb7a497.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\dd32214b87c7a56ddd9f4459bf29aea8561b30b23c94840862b92f188fb7a497.mp4',NULL,NULL,1,'2023-08-09 19:17:55',NULL),
(342,'3d811bff0bc062c15f11d0dc23cfed46474651bcacb39a3eb6009631b1dfc7ed.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\3d811bff0bc062c15f11d0dc23cfed46474651bcacb39a3eb6009631b1dfc7ed.mp4',NULL,NULL,1,'2023-08-09 19:18:00',NULL),
(343,'db567198949c227225eef9e1b0ba956dacbe329fece9ca100b14353c0fe1e3ab.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\db567198949c227225eef9e1b0ba956dacbe329fece9ca100b14353c0fe1e3ab.mp4',NULL,NULL,1,'2023-08-09 19:18:18',NULL),
(344,'bad30b4a2c6856683ae15446c9a8536ec13060d9f5f3b7816476aa7a8f004eef.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\bad30b4a2c6856683ae15446c9a8536ec13060d9f5f3b7816476aa7a8f004eef.mp4',NULL,NULL,1,'2023-08-09 19:18:44',NULL),
(345,'f4e40210a40f6c2d29f5ec1651351105b17b9780567c10b80d5b7bca6a2e2467.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\f4e40210a40f6c2d29f5ec1651351105b17b9780567c10b80d5b7bca6a2e2467.mp4',NULL,NULL,1,'2023-08-09 19:47:54',NULL),
(346,'6534212b8279f085b0452511c3ca638c86eddf01b2d62e7e7c4f502f59f43a45.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\6534212b8279f085b0452511c3ca638c86eddf01b2d62e7e7c4f502f59f43a45.mp4',NULL,NULL,1,'2023-08-12 13:32:04',NULL),
(347,'8b80cac1545a56a74fe9114a8ddbb6678aa45e03f6620dd063ac75e58b03b46a.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\8b80cac1545a56a74fe9114a8ddbb6678aa45e03f6620dd063ac75e58b03b46a.mp4',NULL,NULL,1,'2023-08-14 12:27:15',NULL),
(348,'3cb556c93216916c8ea3e086e1b7fbedddc5351768b0dd3c416303873a148eab.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\3cb556c93216916c8ea3e086e1b7fbedddc5351768b0dd3c416303873a148eab.mp4',NULL,NULL,1,'2023-08-21 12:13:04',NULL),
(349,'9ae352a634a948740dc5957794f6719b5ab8414514303e47c09eff29ca657c43.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\9ae352a634a948740dc5957794f6719b5ab8414514303e47c09eff29ca657c43.mp4',NULL,NULL,1,'2023-08-21 18:21:24',NULL),
(350,'bb9820c35a0af97dff45c78e5c5b29670b6d462d3eaab83ec9d34a60134b9ee5.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\bb9820c35a0af97dff45c78e5c5b29670b6d462d3eaab83ec9d34a60134b9ee5.mp4',NULL,NULL,1,'2023-08-21 18:21:29',NULL),
(351,'54d728a9909de0de64a7c32d54f439f37418d2399c606cb6b96d075057d99e4f.mp4','flower','mp4',1128375,'C:\\nicefish\\upload\\54d728a9909de0de64a7c32d54f439f37418d2399c606cb6b96d075057d99e4f.mp4',NULL,NULL,1,'2023-08-23 19:42:36',NULL);

/*Table structure for table `nicefish_cms_post` */

DROP TABLE IF EXISTS `nicefish_cms_post`;

CREATE TABLE `nicefish_cms_post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_title` varchar(128) DEFAULT NULL,
  `post_summary` varchar(1024) DEFAULT NULL COMMENT '摘要，文章列表页需要使用',
  `post_content` text DEFAULT NULL COMMENT '内容',
  `original_url` varchar(512) DEFAULT NULL COMMENT '原文链接，如果有这个字段，说明是翻译文章',
  `thumb_img_url` varchar(256) DEFAULT NULL COMMENT '缩略图链接',
  `header_img_url` varchar(256) DEFAULT NULL,
  `post_time` datetime NOT NULL DEFAULT current_timestamp(),
  `update_time` datetime DEFAULT current_timestamp(),
  `post_type` varchar(32) NOT NULL DEFAULT '0' COMMENT '文章的类型，0原创1翻译',
  `read_times` int(11) NOT NULL DEFAULT 1 COMMENT '阅读数',
  `liked_times` int(11) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `comment_times` int(11) NOT NULL DEFAULT 0 COMMENT '评论数',
  `user_id` int(11) NOT NULL,
  `email` varchar(64) DEFAULT NULL,
  `nick_name` varchar(64) DEFAULT NULL COMMENT '冗余字段，对应 nicefish_rbac_user 表中的 NICK_NAME 字段，用来避免表关联。',
  `enable_comment` varchar(32) NOT NULL DEFAULT '1' COMMENT '是否可评论\n            0不可\n            1可',
  `status` int(11) NOT NULL DEFAULT 4 COMMENT '状态：\n            1、已删除\n            2、已归档，已归档的内容禁止评论，文章不可删除\n            3、草稿\n            4、已发布\n            5、精华-->精华文章不可删除\n            6、已推至首页\n            ',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

/*Data for the table `nicefish_cms_post` */

insert  into `nicefish_cms_post`(`post_id`,`post_title`,`post_summary`,`post_content`,`original_url`,`thumb_img_url`,`header_img_url`,`post_time`,`update_time`,`post_type`,`read_times`,`liked_times`,`comment_times`,`user_id`,`email`,`nick_name`,`enable_comment`,`status`) values 
(33,'',NULL,'测试内容1测试内容1测试内容1测试内容1测试内容1测试内容1测试内容1测试内容1测试内容1测试内容1测试内容1测试内容1测试内容1测试内容1测试内容1测试内容1测试内容1测试内容1','',NULL,NULL,'2023-07-29 14:39:33','2023-07-29 14:39:33','0',1,0,0,2,'admin@126.com','admin','Y',4),
(34,'',NULL,'测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2测试内容2','',NULL,NULL,'2023-07-29 14:39:45','2023-07-29 14:39:45','0',1,0,0,2,'admin@126.com','admin','Y',4),
(35,'',NULL,'测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3测试内容3','',NULL,NULL,'2023-07-29 14:40:00','2023-07-29 14:40:00','0',1,0,0,2,'admin@126.com','admin','Y',4),
(36,'',NULL,'测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4测试内容4','',NULL,NULL,'2023-07-29 14:40:13','2023-07-29 14:40:13','0',1,0,0,2,'admin@126.com','admin','Y',4),
(37,'',NULL,'测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6测试内容6','',NULL,NULL,'2023-07-29 14:40:26','2023-07-29 14:40:26','0',1,0,0,2,'admin@126.com','admin','Y',4),
(38,'',NULL,'测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7测试内容7','',NULL,NULL,'2023-07-29 14:40:39','2023-07-29 14:40:39','0',1,0,0,2,'admin@126.com','admin','Y',4),
(39,'',NULL,'测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9测试内容9','',NULL,NULL,'2023-07-29 14:40:54','2023-07-29 14:40:54','0',1,0,0,2,'admin@126.com','admin','Y',4),
(40,'',NULL,'测试内容10测试内容10测试内容10测试内容10测试内容10测试内容10测试内容10测试内容10测试内容10测试内容10','',NULL,NULL,'2023-07-29 14:42:41','2023-07-29 14:42:41','0',1,0,0,2,'admin@126.com','admin','Y',4),
(41,'',NULL,'测试内容11测试内容11测试内容11测试内容11测试内容11测试内容11测试内容11测试内容11测试内容11测试内容11测试内容11测试内容11测试内容11测试内容11','',NULL,NULL,'2023-07-29 14:42:59','2023-07-29 14:42:59','0',1,0,0,2,'admin@126.com','admin','Y',4),
(42,'',NULL,'测试图片轮播测试图片轮播测试图片轮播','',NULL,NULL,'2023-07-29 23:07:41','2023-07-29 23:07:41','0',1,0,0,2,'admin@126.com','admin','Y',4),
(43,'',NULL,'测试发图','',NULL,NULL,'2023-08-08 18:26:03','2023-08-08 18:26:03','0',1,0,0,2,'admin@126.com','admin','Y',4),
(44,'',NULL,'测试发布视频内容测试发布视频内容测试发布视频内容测试发布视频内容测试发布视频内容','',NULL,NULL,'2023-08-09 19:18:58','2023-08-09 19:18:58','0',1,0,0,2,'admin@126.com','admin','Y',4),
(45,'',NULL,'测试视频内容测试视频内容测试视频内容测试视频内容测试视频内容测试视频内容测试视频内容测试视频内容','',NULL,NULL,'2023-08-09 19:48:04','2023-08-09 19:48:04','0',1,0,0,2,'admin@126.com','admin','Y',4),
(46,'',NULL,'测试表单校验','',NULL,NULL,'2023-08-12 13:32:17','2023-08-12 13:32:17','0',1,0,0,2,'admin@126.com','admin','Y',4),
(47,'',NULL,'啊','',NULL,NULL,'2023-08-14 12:27:17','2023-08-14 12:27:17','0',1,0,0,2,'admin@126.com','admin','Y',4),
(48,'',NULL,'testtesttesttesttesttesttesttesttesttesttest','',NULL,NULL,'2023-08-21 12:13:13','2023-08-21 12:13:13','0',1,0,0,4,'user1@126.com','测试用户-1','Y',4),
(49,'\"\"',NULL,'logstash, logstash,logstash,logstash,logstash,logstash,logstash,logstash,logstash,logstash,logstash,',NULL,NULL,NULL,'2023-08-22 19:45:24','2023-08-22 19:45:24','0',1,0,0,2,'admin@126.com','admin','1',4),
(50,'\"\"',NULL,'logstash',NULL,NULL,NULL,'2023-08-22 20:14:16','2023-08-22 20:14:16','0',1,0,0,2,'admin@126.com','admin','1',4),
(51,'',NULL,'測試 SpringBoot3.1.2','',NULL,NULL,'2023-08-23 19:42:56','2023-08-23 19:42:56','0',1,0,0,2,'admin@126.com','admin','Y',4);

/*Table structure for table `nicefish_cms_post_file_upload` */

DROP TABLE IF EXISTS `nicefish_cms_post_file_upload`;

CREATE TABLE `nicefish_cms_post_file_upload` (
  `post_id` int(11) NOT NULL,
  `up_id` int(11) NOT NULL,
  PRIMARY KEY (`post_id`,`up_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章与上传的图片、视频中间的映射关系';

/*Data for the table `nicefish_cms_post_file_upload` */

insert  into `nicefish_cms_post_file_upload`(`post_id`,`up_id`) values 
(33,316),
(34,317),
(35,318),
(36,319),
(37,320),
(38,321),
(39,322),
(40,323),
(41,324),
(42,326),
(42,327),
(42,328),
(42,329),
(42,330),
(42,331),
(43,332),
(43,333),
(44,344),
(45,345),
(46,346),
(47,347),
(48,348),
(51,351);

/*Table structure for table `nicefish_cms_sys_params` */

DROP TABLE IF EXISTS `nicefish_cms_sys_params`;

CREATE TABLE `nicefish_cms_sys_params` (
  `param_key` varchar(128) NOT NULL,
  `param_value` varchar(2048) NOT NULL,
  `param_desc` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`param_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统参数配置表';

/*Data for the table `nicefish_cms_sys_params` */

/*Table structure for table `nicefish_cms_user_follow` */

DROP TABLE IF EXISTS `nicefish_cms_user_follow`;

CREATE TABLE `nicefish_cms_user_follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` int(11) DEFAULT NULL,
  `to_id` int(11) DEFAULT NULL,
  `follow_time` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

/*Data for the table `nicefish_cms_user_follow` */

insert  into `nicefish_cms_user_follow`(`id`,`from_id`,`to_id`,`follow_time`) values 
(1,2,4,'2023-08-20 15:46:32'),
(2,2,5,'2023-08-20 15:46:37'),
(3,2,6,'2023-08-20 15:46:43'),
(4,2,7,'2023-08-20 15:46:46'),
(16,4,2,'2023-08-21 12:12:42'),
(18,4,2,'2023-08-21 12:12:54'),
(22,2,4,'2023-08-22 12:47:31'),
(24,2,4,'2023-08-22 12:47:34'),
(26,2,4,'2023-08-22 12:47:37'),
(28,2,4,'2023-08-22 12:47:44'),
(29,2,2,'2023-08-23 19:42:13');

/*Table structure for table `nicefish_cms_user_post_relation` */

DROP TABLE IF EXISTS `nicefish_cms_user_post_relation`;

CREATE TABLE `nicefish_cms_user_post_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `relation_type` int(11) DEFAULT NULL COMMENT '1 表示喜欢；2 表示收藏',
  `time` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

/*Data for the table `nicefish_cms_user_post_relation` */

insert  into `nicefish_cms_user_post_relation`(`id`,`user_id`,`post_id`,`relation_type`,`time`) values 
(4,7,33,1,'2023-08-20 19:23:23'),
(9,2,46,2,'2023-08-20 23:01:32'),
(11,2,47,2,'2023-08-20 23:01:48'),
(12,2,45,1,'2023-08-20 23:01:52'),
(15,2,44,1,'2023-08-20 23:05:09'),
(16,2,40,2,'2023-08-20 23:05:11'),
(18,2,47,1,'2023-08-21 10:14:39'),
(21,2,42,2,'2023-08-21 14:41:52'),
(22,2,33,1,'2023-08-22 12:22:55'),
(23,2,33,2,'2023-08-22 12:22:56'),
(24,2,43,1,'2023-08-23 19:42:13');

/*Table structure for table `nicefish_rbac_api` */

DROP TABLE IF EXISTS `nicefish_rbac_api`;

CREATE TABLE `nicefish_rbac_api` (
  `api_id` int(11) NOT NULL AUTO_INCREMENT,
  `api_name` varchar(64) NOT NULL,
  `url` varchar(64) DEFAULT NULL COMMENT 'URL 的匹配模式和 @RequestMapping 中的定义模式完全相同。',
  `permission` varchar(64) NOT NULL DEFAULT '*' COMMENT '权限定义，按照 Apache Shiro 的权限定义规则进行定义。\r\n            为了避免重复和歧义，权限字符串必须是不同的。',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`api_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='用来定义服务端 API 接口的权限。';

/*Data for the table `nicefish_rbac_api` */

insert  into `nicefish_rbac_api`(`api_id`,`api_name`,`url`,`permission`,`create_time`,`update_time`,`remark`) values 
(5,'系统管理',NULL,'*','2023-07-18 08:59:45','2023-07-19 13:54:36','系统管理权限是最高权限，拥有此权限可以对系统进行任意操作，删除此权限会导致系统管理员无法对系统进行维护。根据 Apache Shiro 的权限规则，此权限代码拥有最高优先级，将会覆盖其它所有权限代码。'),
(6,'关注用户','','user:follow','2023-07-18 14:30:04','2023-07-19 13:51:20','拥有此权限可以关注用户，否则不可以。【这是一条测试数据，无意义】'),
(7,'管理用户','','sys:manage:user','2023-07-19 13:48:36','2023-07-19 13:48:36','管理用户'),
(8,'管理角色','','sys:manage:role','2023-07-19 13:51:50','2023-07-19 13:51:50','管理角色'),
(9,'管理后端接口权限','','sys:manage:api-permission','2023-07-19 13:53:00','2023-07-19 13:53:00','拥有此权限代码的角色，可以维护后端接口权限。'),
(10,'管理前端页面权限','','sys:manage:component-permission','2023-07-19 13:53:43','2023-07-19 13:53:43','拥有此权限代码的角色可以管理前端页面权限。'),
(12,'测试数据-没有用','','*','2023-07-22 16:55:50','2023-07-26 16:38:11','【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】'),
(14,'测试111','','*','2023-08-13 13:15:27','2023-08-13 13:15:37','测试接口用，请勿配置此项目。测试接口用，请勿配置此项目。测试接口用，请勿配置此项目。');

/*Table structure for table `nicefish_rbac_component` */

DROP TABLE IF EXISTS `nicefish_rbac_component`;

CREATE TABLE `nicefish_rbac_component` (
  `component_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_id` int(11) DEFAULT NULL COMMENT '用来构建 Tree 形菜单，P_ID=-1 表示顶级菜单',
  `component_name` varchar(64) NOT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `url` varchar(64) DEFAULT NULL COMMENT '组件对应的 URL 路径，可以定义成系统外部的链接 URL',
  `display_order` int(11) NOT NULL DEFAULT 1 COMMENT '组件在前端屏幕上的显示顺序，按数值从小到达排列，数值越小越靠屏幕顶部。\r\n            在构建树形菜单时，可以利用此列控制显示的顺序。',
  `permission` varchar(64) NOT NULL DEFAULT '*' COMMENT '权限定义，按照 Apache Shiro 的权限定义规则进行定义。\r\n            为了避免重复和歧义，权限字符串必须是不同的。',
  `create_time` datetime NOT NULL DEFAULT current_timestamp(),
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `visiable` int(11) NOT NULL DEFAULT 1 COMMENT '菜单是否可见，1 可见，0 不可见',
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`component_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COMMENT='用来定义前端页面上的组件权限，\r\ncomponent 可以是菜单、按钮，甚至可以细致到一个 HTML 元素。\r\n';

/*Data for the table `nicefish_rbac_component` */

insert  into `nicefish_rbac_component`(`component_id`,`p_id`,`component_name`,`icon`,`url`,`display_order`,`permission`,`create_time`,`update_time`,`visiable`,`remark`) values 
(42,NULL,'用户管理',NULL,'user-table/page/1',1,'menu:view:user-management','2023-07-19 14:13:59','2023-07-19 15:10:57',1,'拥有此权限代码的角色可以看到管理后台右侧边栏的【用户管理】菜单项。\n删除此项将会导致菜单入口消失。'),
(43,NULL,'角色管理',NULL,'role-table/page/1',2,'menu:view:role-management','2023-07-19 14:14:18','2023-07-19 14:31:58',1,'拥有此权限代码的角色可以看到管理后台右侧边栏的【角色管理】菜单项。'),
(44,NULL,'后端接口权限',NULL,'api-permission-table/page/1',3,'menu:view:api-permission-management','2023-07-19 14:14:54','2023-07-19 14:32:12',1,'拥有此权限代码的角色可以看到管理后台右侧边栏的【后端接口权限】菜单项。'),
(45,NULL,'前端页面权限',NULL,'component-permission-table/page/1',4,'menu:view:component-permission-management','2023-07-19 14:15:18','2023-07-19 14:32:26',1,'拥有此权限代码的角色可以看到管理后台右侧边栏的【前端页面权限】菜单项。'),
(46,NULL,'系统设置',NULL,'sys-settings',5,'menu:view:sys-settings','2023-07-19 14:15:48','2023-07-19 14:32:38',1,'拥有此权限代码的角色可以看到管理后台右侧边栏的【系统设置】菜单项。'),
(49,NULL,'测试数据-没有用',NULL,'/test/test/test/test',100,'*','2023-07-22 16:56:02','2023-07-26 17:57:59',1,'【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】【测试数据-没有用】'),
(54,49,'测试子节点和展开状态','','',1002,'*','2023-08-18 14:51:48','2023-08-18 15:29:20',0,'测试数据，请不要配置。');

/*Table structure for table `nicefish_rbac_role` */

DROP TABLE IF EXISTS `nicefish_rbac_role`;

CREATE TABLE `nicefish_rbac_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '-1 特权角色，不能删除 0正常 1停用 2删除',
  `remark` varchar(500) DEFAULT '',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

/*Data for the table `nicefish_rbac_role` */

insert  into `nicefish_rbac_role`(`role_id`,`role_name`,`status`,`remark`) values 
(1,'系统管理员',1,'【系统管理员角色拥有系统最高权限，删除或者禁用此角色将会导致管理员无法登录系统。】'),
(10,'测试角色-1',1,'此角色用来测试，上面没有配置权限点。'),
(12,'普通用户默认角色11111',1,'普通用户拥有的默认角色，删除此角色将导致普通用户失去对应的接口和菜单权限。【普通用户默认角色11111】'),
(13,'测试角色2222',1,'测试角色2222测试角色2222测试角色2222测试角色2222'),
(14,'测试角色3333',1,'测试角色3333测试角色3333测试角色3333测试角色3333测试角色3333测试角色3333测试角色3333测试角色3333');

/*Table structure for table `nicefish_rbac_role_api` */

DROP TABLE IF EXISTS `nicefish_rbac_role_api`;

CREATE TABLE `nicefish_rbac_role_api` (
  `role_id` int(11) NOT NULL,
  `api_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色与 API  接口之间的关联关系。';

/*Data for the table `nicefish_rbac_role_api` */

insert  into `nicefish_rbac_role_api`(`role_id`,`api_id`) values 
(1,5),
(10,7),
(12,12),
(14,5);

/*Table structure for table `nicefish_rbac_role_component` */

DROP TABLE IF EXISTS `nicefish_rbac_role_component`;

CREATE TABLE `nicefish_rbac_role_component` (
  `role_id` int(11) NOT NULL,
  `component_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`component_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色与菜单的关联关系';

/*Data for the table `nicefish_rbac_role_component` */

insert  into `nicefish_rbac_role_component`(`role_id`,`component_id`) values 
(1,42),
(1,43),
(1,44),
(1,45),
(1,46),
(10,42);

/*Table structure for table `nicefish_rbac_session` */

DROP TABLE IF EXISTS `nicefish_rbac_session`;

CREATE TABLE `nicefish_rbac_session` (
  `session_id` varchar(64) NOT NULL DEFAULT '',
  `app_name` varchar(64) DEFAULT NULL COMMENT '应用名称',
  `user_id` int(11) DEFAULT NULL COMMENT '如果用户没有登录，此列可为空',
  `user_name` varchar(64) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `expiry_time` datetime DEFAULT NULL,
  `last_access_time` datetime DEFAULT NULL,
  `max_inactive_interval` int(11) DEFAULT NULL,
  `timeout` bigint(20) DEFAULT NULL COMMENT '过期时间',
  `expired` tinyint(1) DEFAULT 0 COMMENT 'Session 是否已经过期',
  `host` varchar(64) DEFAULT '' COMMENT 'IP地址',
  `os` varchar(64) DEFAULT '',
  `browser` varchar(64) DEFAULT '',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '浏览器发送过来的 UserAgent 字符串',
  `session_data` text DEFAULT NULL COMMENT 'Session 中的所有 Attribute ，格式是 JSON 。',
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用来持久化 Session ，应用端可以利用此表实现 SSO 。\r\n此表中的 SESSION_DATA 是 J';

/*Data for the table `nicefish_rbac_session` */

insert  into `nicefish_rbac_session`(`session_id`,`app_name`,`user_id`,`user_name`,`creation_time`,`expiry_time`,`last_access_time`,`max_inactive_interval`,`timeout`,`expired`,`host`,`os`,`browser`,`user_agent`,`session_data`) values 
('2821dac2-ef06-4a73-af39-d5aa4e9c3dab',NULL,NULL,NULL,'2023-08-20 15:27:30',NULL,'2023-08-20 15:50:47',NULL,259200000,1,'0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0',NULL),
('2f002575-00c8-4e35-8071-d8a3a4f72a15',NULL,NULL,NULL,'2023-08-14 13:36:13',NULL,'2023-08-14 14:18:24',NULL,259200000,1,'0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0',NULL),
('3d0c3c21-a70b-4dcc-a29c-ec21aaf0a479',NULL,2,'admin@126.com','2023-08-23 19:41:48',NULL,'2023-08-23 19:43:02',NULL,259200000,0,'0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0',NULL),
('40cf9d7b-e605-4b3e-b94d-c5a3dd710f18',NULL,2,'admin@126.com','2023-08-18 20:59:36',NULL,'2023-08-19 14:53:53',NULL,259200000,0,'0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0',NULL),
('5d9d9697-4701-41ed-8803-cd0dffc84663',NULL,2,'admin@126.com','2023-08-13 21:54:36',NULL,'2023-08-13 21:59:23',NULL,259200000,0,'0:0:0:0:0:0:0:1','Windows 10','Chrome 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36',NULL),
('78a6bc73-464f-440d-9b07-dc5676e34489',NULL,NULL,NULL,'2023-08-14 14:37:19',NULL,'2023-08-14 15:08:07',NULL,259200000,1,'0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0',NULL),
('9c3ab919-ff8a-4eac-b060-ce3a4b3d99bc',NULL,NULL,NULL,'2023-08-18 13:03:00',NULL,'2023-08-18 20:59:36',NULL,259200000,1,'0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0',NULL),
('af1216eb-6c9a-4c57-bfbf-85a6f97840b2',NULL,2,'admin@126.com','2023-08-20 15:50:50',NULL,'2023-08-21 12:11:34',NULL,259200000,1,'0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0',NULL),
('bd29d2d2-74e0-46c5-9cad-ecc1cdc84a8c',NULL,NULL,NULL,'2023-08-13 21:54:36',NULL,'2023-08-13 21:54:36',NULL,259200000,0,'0:0:0:0:0:0:0:1','Windows 10','Chrome 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36',NULL),
('d5872822-8eec-43f8-a723-ae5f6e3a4e6d',NULL,NULL,NULL,'2023-08-13 14:25:10',NULL,'2023-08-14 13:36:12',NULL,259200000,1,'0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0',NULL),
('ec621aa3-fdf1-4b0c-a4a7-89173500def1',NULL,4,'user1@126.com','2023-08-21 12:11:53',NULL,'2023-08-21 12:13:30',NULL,259200000,1,'0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0',NULL),
('f220742d-14b1-4adb-9679-88d1f19cb9cb',NULL,2,'admin@126.com','2023-08-14 14:18:26',NULL,'2023-08-14 14:37:18',NULL,259200000,1,'0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0',NULL),
('fcf2c2c3-8686-472e-809f-6bfe16795c5d',NULL,2,'admin@126.com','2023-08-21 12:14:14',NULL,'2023-08-23 19:39:26',NULL,259200000,1,'0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0',NULL),
('fe1cb959-7924-41c6-b1fb-5cb3a9e70b21',NULL,NULL,NULL,'2023-08-23 19:41:48',NULL,'2023-08-23 19:41:48',NULL,259200000,0,'0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0',NULL);

/*Table structure for table `nicefish_rbac_user` */

DROP TABLE IF EXISTS `nicefish_rbac_user`;

CREATE TABLE `nicefish_rbac_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL,
  `nick_name` varchar(64) NOT NULL,
  `password` varchar(64) DEFAULT '',
  `email` varchar(64) DEFAULT '',
  `cellphone` varchar(32) DEFAULT '',
  `gender` int(11) DEFAULT 0 COMMENT '0男 1女 2未知',
  `city` varchar(128) DEFAULT NULL,
  `education` varchar(128) DEFAULT NULL,
  `avatar_url` varchar(64) DEFAULT '' COMMENT '用户头像 URL',
  `salt` varchar(32) DEFAULT '',
  `create_time` datetime DEFAULT current_timestamp(),
  `status` int(11) DEFAULT 1 COMMENT '-1 特权用户不能删除 0正常 1禁用 2删除',
  `remark` varchar(500) DEFAULT '',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

/*Data for the table `nicefish_rbac_user` */

insert  into `nicefish_rbac_user`(`user_id`,`user_name`,`nick_name`,`password`,`email`,`cellphone`,`gender`,`city`,`education`,`avatar_url`,`salt`,`create_time`,`status`,`remark`) values 
(2,'admin@126.com','admin','7f9091af32f1f34b430998540d9b8cfc','admin@126.com','13813838338',2,NULL,NULL,'','850bf8','2023-07-17 23:02:37',1,'adminadminadminadminadminadminadminadminadminadminadminadminadminadminadmin'),
(4,'user1@126.com','测试用户-1','5f077f98795ddfe4d353e4c2a0acbdd0','user1@126.com','',1,NULL,NULL,'','596a69','2023-07-18 13:58:04',1,''),
(5,'user2@126.com','user2','4f9c512e8ca73e1285a5617fb843e609','user2@126.com','',0,NULL,NULL,'','558d9a','2023-07-18 15:10:22',1,''),
(6,'user3@126.com','user3','ae2a2f18656334fab7d3ea71c6bc55ce','user3@126.com','',0,NULL,NULL,'','ec764e','2023-07-18 16:20:58',1,''),
(7,'user4@126.com','user4','9ee2e0077263167a2c1f62fa7f993da1','user4@126.com','',2,NULL,NULL,'','3cb61c','2023-07-18 20:24:12',1,''),
(10,'user5@126.com','user5','2513aac029d2863024ad16133ced58c1','','',1,NULL,NULL,'','7ce10d','2023-07-26 15:18:53',0,'user5'),
(11,'user6@126.com','user6','773d107cf9d07154d954a9630bcc9002','user6@126.com','',0,NULL,NULL,'','29322b','2023-07-27 13:14:13',1,'【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】【user6】'),
(12,'test22@126.com','','e0f543b23b37a0c9ace53eed201f915f','test22@126.com','',0,NULL,NULL,'','483c0e','2023-08-12 00:11:03',1,''),
(13,'test3333@126.com','test3333','85e162e858e8a8493e37bf1b29907a72','test3333@126.com','',0,NULL,NULL,'','1ea4a8','2023-08-13 14:12:13',1,'test3333test3333test3333test3333test3333test3333test3333test3333test3333test3333test3333test3333test3333test3333test3333');

/*Table structure for table `nicefish_rbac_user_role` */

DROP TABLE IF EXISTS `nicefish_rbac_user_role`;

CREATE TABLE `nicefish_rbac_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `nicefish_rbac_user_role` */

insert  into `nicefish_rbac_user_role`(`user_id`,`role_id`) values 
(2,1),
(2,10),
(2,12),
(4,10),
(7,10);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

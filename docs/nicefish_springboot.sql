/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 10.1.8-MariaDB-log : Database - nicefish_springboot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nicefish_springboot` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `nicefish_springboot`;

/*Table structure for table `cms_comment` */

DROP TABLE IF EXISTS `cms_comment`;

CREATE TABLE `cms_comment` (
  `COMMENT_ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `POST_ID` bigint(11) NOT NULL,
  `CONTENT` text,
  `COMMENT_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `COMMENT_IP` varchar(64) DEFAULT NULL COMMENT '评论者的IP地址',
  `P_ID` bigint(11) DEFAULT '-1' COMMENT '父层评论的ID，用来实现评论盖楼效果',
  `USER_ID` bigint(11) NOT NULL,
  `USER_NAME` varchar(64) DEFAULT NULL,
  `NICK_NAME` varchar(64) DEFAULT NULL,
  `EMAIL` varchar(64) DEFAULT NULL,
  `STATUS` int(11) DEFAULT '1' COMMENT '评论状态：0：已删除；1：已发布；2:优质评论；',
  PRIMARY KEY (`COMMENT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=utf8;

/*Data for the table `cms_comment` */

insert  into `cms_comment`(`COMMENT_ID`,`POST_ID`,`CONTENT`,`COMMENT_TIME`,`COMMENT_IP`,`P_ID`,`USER_ID`,`USER_NAME`,`NICK_NAME`,`EMAIL`,`STATUS`) values (266,120,'这里什么也没有，测试业务流程而已\n','2019-08-27 08:49:10',NULL,NULL,1,'admin','admin',NULL,NULL),(267,120,'这里什么也没有，测试业务流程而已\n','2019-08-27 08:49:18',NULL,NULL,1,'admin','admin',NULL,NULL),(268,120,'这里什么也没有，测试业务流程而已\n','2019-08-27 08:49:21',NULL,NULL,1,'admin','admin',NULL,NULL),(269,120,'这里什么也没有，测试业务流程而已\n','2019-08-27 08:49:28',NULL,NULL,1,'admin','admin',NULL,NULL),(278,134,'测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口','2019-08-28 15:45:46',NULL,NULL,22,'damoqiongqiu@126.com','大漠穷秋',NULL,NULL),(279,134,'测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口','2019-08-28 15:45:50',NULL,NULL,22,'damoqiongqiu@126.com','大漠穷秋',NULL,NULL),(280,134,'测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口','2019-08-29 11:08:53',NULL,NULL,22,'damoqiongqiu@126.com','大漠穷秋',NULL,NULL),(281,135,'测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试','2019-08-29 12:12:02',NULL,NULL,22,'damoqiongqiu@126.com','大漠穷秋',NULL,NULL),(282,135,'测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试','2019-08-29 12:12:08',NULL,NULL,22,'damoqiongqiu@126.com','大漠穷秋',NULL,NULL);

/*Table structure for table `cms_params` */

DROP TABLE IF EXISTS `cms_params`;

CREATE TABLE `cms_params` (
  `PARAM_KEY` varchar(128) NOT NULL,
  `PARAM_VALUE` varchar(2048) NOT NULL,
  `PARAM_DESC` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`PARAM_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统参数表。';

/*Data for the table `cms_params` */

insert  into `cms_params`(`PARAM_KEY`,`PARAM_VALUE`,`PARAM_DESC`) values ('COMMENT_MAX_LEN','140','评论最大长度，字符数'),('COMMENT_MIN_LEN','5','评论最小长度'),('COMMENT_PAGE_SIZE','10','文章评论每页显示的条数'),('COMMENT_TIME_INTERVAL','3','?分钟内不允许多次发表评论'),('CONTENT_LEN_SHORT','140','评论缩略长度'),('POST_ATTACH_NUM_MAX','10','每篇文章允许的附件数量'),('POST_ATTACH_SIZE_MAX','50','文章附件单个文件最大尺寸（M）'),('POST_CONTENT_MAX_LEN','10000','文章内容最大长度'),('POST_CONTENT_MIN_LEN','10','文章内容最小长度'),('POST_IMG_SIZE_MAX','1','文章内部图片最大文件尺寸（M）'),('POST_MAX_FILE_SIZE','10','写文章上传附件时文件的最大尺寸（M）'),('POST_MAX_PAGE_SIZE','10','文章列表最大显示的页码数'),('POST_NUM_EACH_DAY','10','每天允许发布的文章数量'),('POST_PAGE_SIZE','20','文章列表每页显示文章的数量'),('POST_TITLE_MAX_LEN','32','文章标题最大长度'),('POST_TITLE_MIN_LEN','8','文章标题最小长度'),('POST_UPLOAD_FILE_DIR','TOMCAT','上传文件存放的目录，默认保存在项目部署的根目录下'),('USER_AUTO_MUTE_TIME','60','新用户自动禁言时间'),('USER_AVATAR_SIZE','1','用户头像最大尺寸（M）'),('USER_DESC_MAX_LEN','140','用户简介最大长度'),('USER_NAME_MAX_LEN','64','用户名最大长度'),('USER_NAME_MIN_LEN','4','用户名最小长度'),('USER_PWD_MAX_LEN','16','密码最大长度'),('USER_PWD_MIN_LEN','8','密码最小长度');

/*Table structure for table `cms_post` */

DROP TABLE IF EXISTS `cms_post`;

CREATE TABLE `cms_post` (
  `POST_ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `BG_IMG` varchar(256) DEFAULT NULL COMMENT '缩略图链接',
  `POST_TITLE` varchar(128) NOT NULL,
  `POST_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `POST_SUMMARY` varchar(1024) DEFAULT NULL COMMENT '摘要，文章列表页需要使用',
  `POST_CONTENT` text COMMENT '内容',
  `ORIGINAL_URL` varchar(512) DEFAULT NULL COMMENT '原文链接，如果有这个字段，说明是翻译文章',
  `POST_TYPE` int(11) NOT NULL DEFAULT '0' COMMENT '文章的类型，0原创1翻译',
  `LAST_MODIFY_TIME` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `READ_TIMES` bigint(11) NOT NULL DEFAULT '1' COMMENT '阅读数',
  `LIKED_TIMES` bigint(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `COMMENT_TIMES` bigint(11) NOT NULL DEFAULT '0' COMMENT '评论数',
  `USER_ID` bigint(11) DEFAULT NULL,
  `EMAIL` varchar(64) DEFAULT NULL,
  `NICK_NAME` varchar(64) DEFAULT NULL,
  `ENABLE_COMMENT` int(11) NOT NULL DEFAULT '1' COMMENT '是否可评论\n            0不可\n            1可',
  `STATUS` int(11) NOT NULL DEFAULT '4' COMMENT '状态：\n            1、已删除\n            2、已归档，已归档的内容禁止评论，文章不可删除\n            3、草稿\n            4、已发布\n            5、精华-->精华文章不可删除\n            6、已推至首页\n            ',
  PRIMARY KEY (`POST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;

/*Data for the table `cms_post` */

insert  into `cms_post`(`POST_ID`,`BG_IMG`,`POST_TITLE`,`POST_TIME`,`POST_SUMMARY`,`POST_CONTENT`,`ORIGINAL_URL`,`POST_TYPE`,`LAST_MODIFY_TIME`,`READ_TIMES`,`LIKED_TIMES`,`COMMENT_TIMES`,`USER_ID`,`EMAIL`,`NICK_NAME`,`ENABLE_COMMENT`,`STATUS`) values (120,NULL,'这里什么也没有，测试业务流程而已','2019-08-27 08:48:53',NULL,'<p>这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已</p>','',0,'2019-08-27 08:48:52',1,0,0,1,'admin@126.com','admin',1,4),(121,NULL,'这里什么也没有，测试业务流程而已','2019-08-27 08:50:47',NULL,'<p>这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已</p>','',0,'2019-08-27 08:50:47',1,0,0,1,'admin@126.com','admin',1,4),(122,NULL,'这里什么也没有，测试业务流程而已','2019-08-27 08:51:25',NULL,'<p>这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已</p>','',0,'2019-08-27 08:51:25',1,0,0,1,'admin@126.com','admin',1,4),(123,NULL,'这里什么也没有，测试业务流程而已','2019-08-27 08:51:38',NULL,'<p>这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已</p>','',0,'2019-08-27 08:51:38',1,0,0,1,'admin@126.com','admin',1,4),(124,NULL,'这里什么也没有，测试业务流程而已','2019-08-27 08:51:45',NULL,'<p>这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已这里什么也没有，测试业务流程而已</p>','',0,'2019-08-27 08:51:45',1,0,0,1,'admin@126.com','admin',1,4),(134,NULL,'测试API接口','2019-08-28 15:45:33',NULL,'<p>测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口测试API接口</p>','',0,'2019-08-28 15:45:33',1,0,0,22,'damoqiongqiu@126.com','大漠穷秋',1,4),(135,NULL,'测试啊测试','2019-08-29 12:11:43',NULL,'<p>测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试测试啊测试</p>','',0,'2019-08-29 12:11:43',1,0,0,22,'damoqiongqiu@126.com','大漠穷秋',1,4);

/*Table structure for table `cms_upload` */

DROP TABLE IF EXISTS `cms_upload`;

CREATE TABLE `cms_upload` (
  `UP_ID` bigint(64) NOT NULL,
  `UP_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `FILE_NAME` varchar(128) DEFAULT NULL COMMENT '与物理保存的文件名一致',
  `FILE_TYPE` varchar(16) DEFAULT '1' COMMENT '1、图片；\n            2、附件；',
  `FILE_WIDTH` int(11) DEFAULT '0',
  `FILE_HEIGHT` int(11) DEFAULT '0',
  `FILE_SIZE` float DEFAULT '0',
  `DISPLAY_ORDER` int(11) DEFAULT '0',
  `USER_ID` bigint(64) DEFAULT '0',
  `FILE_MODULE` int(11) DEFAULT '1' COMMENT '1、metro相关的图片\n            2、文章相关的图片\n            3、图书相关的图片\n            4、小图标\n            5、用户头像\n            ',
  `FILE_DESC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上传的文件，会员头像、用户头像、门店图片介绍、项目图片介绍等，所有上传的文件都记录在这张表。';

/*Data for the table `cms_upload` */

/*Table structure for table `fish_auth_permission` */

DROP TABLE IF EXISTS `fish_auth_permission`;

CREATE TABLE `fish_auth_permission` (
  `PERMISSION_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `PERMISSION_STR` varchar(64) NOT NULL DEFAULT '' COMMENT 'Shiro风格的权限通配符',
  `STATUS` int(1) NOT NULL DEFAULT '0' COMMENT '-1 特权角色，不能删除 0正常 1停用 2删除',
  `REMARK` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`PERMISSION_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `fish_auth_permission` */

insert  into `fish_auth_permission`(`PERMISSION_ID`,`PERMISSION_STR`,`STATUS`,`REMARK`) values (1,'*:*:*',-1,'所有资源*所有实例*所有属性'),(4,'test1:test1:test1',0,'test1:test1:test1');

/*Table structure for table `fish_auth_role` */

DROP TABLE IF EXISTS `fish_auth_role`;

CREATE TABLE `fish_auth_role` (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(30) NOT NULL,
  `ROLE_KEY` varchar(100) NOT NULL,
  `STATUS` int(1) NOT NULL DEFAULT '0' COMMENT '-1 特权角色，不能删除 0正常 1停用 2删除',
  `REMARK` varchar(500) DEFAULT '',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色信息表';

/*Data for the table `fish_auth_role` */

insert  into `fish_auth_role`(`ROLE_ID`,`ROLE_NAME`,`ROLE_KEY`,`STATUS`,`REMARK`) values (1,'管理员','admin',-1,'管理员'),(2,'普通用户','common',-1,'普通角色');

/*Table structure for table `fish_auth_role_permission` */

DROP TABLE IF EXISTS `fish_auth_role_permission`;

CREATE TABLE `fish_auth_role_permission` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) NOT NULL,
  `PERMISSION_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `fish_auth_role_permission` */

insert  into `fish_auth_role_permission`(`ID`,`ROLE_ID`,`PERMISSION_ID`) values (1,1,1);

/*Table structure for table `fish_auth_user` */

DROP TABLE IF EXISTS `fish_auth_user`;

CREATE TABLE `fish_auth_user` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(30) NOT NULL,
  `NICK_NAME` varchar(30) NOT NULL,
  `PASSWORD` varchar(50) DEFAULT '',
  `EMAIL` varchar(50) DEFAULT '',
  `CELLPHONE` varchar(11) DEFAULT '',
  `GENDER` int(8) DEFAULT '0' COMMENT '0男 1女 2未知',
  `AVATAR` varchar(100) DEFAULT '',
  `SALT` varchar(20) DEFAULT '',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `STATUS` int(1) DEFAULT '0' COMMENT '-1 特权用户不能删除 0正常 1禁用 2删除',
  `REMARK` varchar(500) DEFAULT '',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `fish_auth_user` */

insert  into `fish_auth_user`(`USER_ID`,`USER_NAME`,`NICK_NAME`,`PASSWORD`,`EMAIL`,`CELLPHONE`,`GENDER`,`AVATAR`,`SALT`,`CREATE_TIME`,`STATUS`,`REMARK`) values (1,'admin','admin','29c67a30398638269fe600f73a054934','admin@126.com','',1,'','111111','2019-08-27 12:40:18',-1,'管理员'),(12,'253445528@qq.com','小漠漠','123159e713693930e22e4c1c8ce546f4','253445528@qq.com',NULL,NULL,NULL,'b8f4f1','2019-08-27 12:40:18',-1,'管理员'),(22,'damoqiongqiu@126.com','大漠穷秋','53c4eea9f0d6797ccfaefdd525a8e8f3','damoqiongqiu@126.com','',0,'','db22e1','2019-08-28 13:41:49',-1,'管理员');

/*Table structure for table `fish_auth_user_role` */

DROP TABLE IF EXISTS `fish_auth_user_role`;

CREATE TABLE `fish_auth_user_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NOT NULL,
  `ROLE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

/*Data for the table `fish_auth_user_role` */

insert  into `fish_auth_user_role`(`ID`,`USER_ID`,`ROLE_ID`) values (1,1,1),(5,12,2),(15,22,2);

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values (1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

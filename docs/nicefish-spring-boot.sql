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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

/*Data for the table `nicefish_cms_comment` */

insert  into `nicefish_cms_comment`(`comment_id`,`post_id`,`content`,`comment_time`,`comment_ip`,`p_id`,`user_id`,`user_name`,`nick_name`,`email`,`status`) values 
(1,1,'1111111111111111111111111','2023-07-12 21:09:43',NULL,NULL,1,'admin@126.com','系统管理员',NULL,NULL),
(2,1,'1111111111111111111111111','2023-07-12 21:09:47',NULL,NULL,1,'admin@126.com','系统管理员',NULL,NULL),
(3,1,'1111111111111111111111111','2023-07-12 21:09:49',NULL,NULL,1,'admin@126.com','系统管理员',NULL,NULL);

/*Table structure for table `nicefish_cms_file_upload` */

DROP TABLE IF EXISTS `nicefish_cms_file_upload`;

CREATE TABLE `nicefish_cms_file_upload` (
  `up_id` int(11) NOT NULL,
  `up_time` datetime DEFAULT current_timestamp(),
  `file_name` varchar(128) DEFAULT NULL COMMENT '与物理保存的文件名一致',
  `file_type` varchar(32) DEFAULT '1' COMMENT '1、图片；\n            2、附件；',
  `file_size` float DEFAULT 0,
  `file_width` int(11) DEFAULT 0,
  `file_height` int(11) DEFAULT 0,
  `display_order` int(11) DEFAULT 0,
  `user_id` int(11) DEFAULT 0,
  `url` varchar(128) DEFAULT NULL COMMENT '访问此文件的路径，可以指向系统外部的 URL 。',
  `file_module` int(11) DEFAULT 1 COMMENT '1、metro相关的图片\n            2、文章相关的图片\n            3、图书相关的图片\n            4、小图标\n            5、用户头像\n            ',
  `file_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`up_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='上传文件的记录。';

/*Data for the table `nicefish_cms_file_upload` */

/*Table structure for table `nicefish_cms_post` */

DROP TABLE IF EXISTS `nicefish_cms_post`;

CREATE TABLE `nicefish_cms_post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_title` varchar(128) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

/*Data for the table `nicefish_cms_post` */

insert  into `nicefish_cms_post`(`post_id`,`post_title`,`post_summary`,`post_content`,`original_url`,`thumb_img_url`,`header_img_url`,`post_time`,`update_time`,`post_type`,`read_times`,`liked_times`,`comment_times`,`user_id`,`email`,`nick_name`,`enable_comment`,`status`) values 
(1,'1111111111111111111111111',NULL,'<p>11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111</p>','',NULL,NULL,'2023-07-12 21:09:20','2023-07-12 21:09:20','0',1,0,0,1,'admin@126.com','系统管理员','Y',4),
(2,'222222222222222222222222222222',NULL,'<p>222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222</p>','',NULL,NULL,'2023-07-12 21:09:33','2023-07-12 21:09:33','0',1,0,0,1,'admin@126.com','系统管理员','Y',4);

/*Table structure for table `nicefish_cms_sys_params` */

DROP TABLE IF EXISTS `nicefish_cms_sys_params`;

CREATE TABLE `nicefish_cms_sys_params` (
  `param_key` varchar(128) NOT NULL,
  `param_value` varchar(2048) NOT NULL,
  `param_desc` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`param_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统参数配置表';

/*Data for the table `nicefish_cms_sys_params` */

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='用来定义服务端 API 接口的权限。';

/*Data for the table `nicefish_rbac_api` */

insert  into `nicefish_rbac_api`(`api_id`,`api_name`,`url`,`permission`,`create_time`,`update_time`,`remark`) values 
(1,'角色管理','','*','2023-07-16 13:30:38','2023-07-16 13:30:38',''),
(2,'用户管理','','****','2023-07-16 13:30:49','2023-07-16 13:31:04',''),
(3,'测试555','','*','2023-07-16 21:01:08','2023-07-16 21:01:08','测试555');

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COMMENT='用来定义前端页面上的组件权限，\r\ncomponent 可以是菜单、按钮，甚至可以细致到一个 HTML 元素。\r\n';

/*Data for the table `nicefish_rbac_component` */

insert  into `nicefish_rbac_component`(`component_id`,`p_id`,`component_name`,`icon`,`url`,`display_order`,`permission`,`create_time`,`update_time`,`visiable`,`remark`) values 
(1,NULL,'根节点1',NULL,NULL,2,'*','2023-07-14 11:37:22','2023-07-14 12:53:53',1,NULL),
(2,1,'子节点1-1',NULL,NULL,1,'*','2023-07-14 11:37:30','2023-07-14 11:53:32',1,NULL),
(3,2,'子节点1-1-1',NULL,NULL,1,'*','2023-07-14 11:57:34','2023-07-14 11:57:34',1,NULL),
(10,NULL,'測試111111',NULL,NULL,100,'***','2023-07-15 23:23:08','2023-07-15 23:26:46',0,NULL),
(13,NULL,'测试跟节点2222',NULL,NULL,1,'*','2023-07-16 11:27:23','2023-07-16 11:27:23',1,NULL),
(14,10,'测试444444',NULL,NULL,1,'*','2023-07-16 11:29:37','2023-07-16 11:29:37',1,'测试444444测试444444测试444444测试444444测试444444测试444444测试444444测试444444测试444444测试444444测试444444测试444444');

/*Table structure for table `nicefish_rbac_role` */

DROP TABLE IF EXISTS `nicefish_rbac_role`;

CREATE TABLE `nicefish_rbac_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '-1 特权角色，不能删除 0正常 1停用 2删除',
  `remark` varchar(500) DEFAULT '',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `nicefish_rbac_role` */

insert  into `nicefish_rbac_role`(`role_id`,`role_name`,`status`,`remark`) values 
(1,'系统管理员',1,'删除此角色将会导致整个系统不可用。'),
(2,'测试角色1111',1,''),
(3,'测试角色2',0,''),
(4,'测试3',0,''),
(5,'测试3333',1,''),
(6,'测试777777777777',1,'测试777777777777');

/*Table structure for table `nicefish_rbac_role_api` */

DROP TABLE IF EXISTS `nicefish_rbac_role_api`;

CREATE TABLE `nicefish_rbac_role_api` (
  `role_id` int(11) NOT NULL,
  `api_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色与 API  接口之间的关联关系。';

/*Data for the table `nicefish_rbac_role_api` */

insert  into `nicefish_rbac_role_api`(`role_id`,`api_id`) values 
(1,1),
(2,1),
(2,2),
(2,3),
(6,1),
(6,2),
(6,3);

/*Table structure for table `nicefish_rbac_role_component` */

DROP TABLE IF EXISTS `nicefish_rbac_role_component`;

CREATE TABLE `nicefish_rbac_role_component` (
  `role_id` int(11) NOT NULL,
  `component_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`component_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色与菜单的关联关系';

/*Data for the table `nicefish_rbac_role_component` */

insert  into `nicefish_rbac_role_component`(`role_id`,`component_id`) values 
(1,1),
(6,1),
(6,2),
(6,3),
(6,10),
(6,13),
(6,14);

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
  `expired` varchar(32) DEFAULT 'N' COMMENT 'Session 是否已经过期，N 表示未过期，Y 表示已过期。',
  `host` varchar(64) DEFAULT '' COMMENT 'IP地址',
  `os` varchar(64) DEFAULT '',
  `browser` varchar(64) DEFAULT '',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '浏览器发送过来的 UserAgent 字符串',
  `session_data` text DEFAULT NULL COMMENT 'Session 中的所有 Attribute ，格式是 JSON 。',
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用来持久化 Session ，应用端可以利用此表实现 SSO 。\n此表中的 SESSION_DATA 是 J';

/*Data for the table `nicefish_rbac_session` */

insert  into `nicefish_rbac_session`(`session_id`,`app_name`,`user_id`,`user_name`,`creation_time`,`expiry_time`,`last_access_time`,`max_inactive_interval`,`timeout`,`expired`,`host`,`os`,`browser`,`user_agent`,`session_data`) values 
('03682154-d7ae-4769-9991-b4197bcb50ca',NULL,NULL,NULL,'2023-07-14 10:34:30',NULL,'2023-07-14 10:34:30',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('05110ad6-3563-4817-b77b-c67b466d5142',NULL,NULL,NULL,'2023-07-15 23:06:07',NULL,'2023-07-15 23:06:12',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('087fed88-4dc0-44f1-b952-c4249cf4cecf',NULL,NULL,NULL,'2023-07-16 20:59:01',NULL,'2023-07-16 21:09:18',NULL,300000,'N','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('0a66fad1-283a-4944-a28c-fea734109477',NULL,NULL,NULL,'2023-07-17 14:45:38',NULL,'2023-07-17 14:48:04',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('0e484c8a-2a42-4349-a836-41994134788a',NULL,NULL,NULL,'2023-07-14 13:06:08',NULL,'2023-07-14 13:09:25',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('0e4a60cf-5c1f-4c84-aa18-d8b1171a74ce',NULL,NULL,NULL,'2023-07-13 08:53:00',NULL,'2023-07-13 08:58:18',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('16ee4496-5b86-4c4c-bc5a-660228fb6039',NULL,NULL,NULL,'2023-07-16 10:21:53',NULL,'2023-07-16 10:32:15',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('1795788b-cec3-47b0-a83e-2c471bfc34cb',NULL,NULL,NULL,'2023-07-13 16:53:10',NULL,'2023-07-13 16:54:31',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('1f0b9b60-2f59-4012-90cd-cc2ed539de55',NULL,NULL,NULL,'2023-07-17 14:54:41',NULL,'2023-07-17 14:58:10',NULL,300000,'N','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('240e48db-16c0-4bef-be49-2e02c7af107b',NULL,NULL,NULL,'2023-07-13 08:37:59',NULL,'2023-07-13 08:41:37',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('2be93aa4-c256-4a4f-97ea-2b2e4fd83e48',NULL,NULL,NULL,'2023-07-16 20:16:10',NULL,'2023-07-16 20:20:25',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('2c214251-64c1-4919-860d-2351846a3021',NULL,NULL,NULL,'2023-07-17 13:29:34',NULL,'2023-07-17 13:29:34',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('2e66c8ee-c22e-4cae-bf95-7d0059ae1c09',NULL,NULL,NULL,'2023-07-12 21:20:01',NULL,'2023-07-12 21:24:15',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('30ce80ac-3ee1-4f99-9fc0-53c96604be07',NULL,NULL,NULL,'2023-07-16 15:27:43',NULL,'2023-07-16 15:38:35',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('31e9af73-06d9-4a1b-9054-26986745ea87',NULL,NULL,NULL,'2023-07-17 12:47:56',NULL,'2023-07-17 12:48:27',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('3843aba7-2806-4415-a4e7-b5a9cb360cf9',NULL,NULL,NULL,'2023-07-17 13:29:34',NULL,'2023-07-17 13:35:45',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('39bd01d1-1e70-4c4f-92bd-76b1c9a68631',NULL,NULL,NULL,'2023-07-16 15:12:27',NULL,'2023-07-16 15:17:21',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('3b8d54ba-8781-42a9-9dac-26be47d2c550',NULL,NULL,NULL,'2023-07-16 15:27:43',NULL,'2023-07-16 15:27:43',NULL,300000,'N','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('3eaf7355-3f0c-49d2-902f-f99fe16cb659',NULL,NULL,NULL,'2023-07-16 09:18:58',NULL,'2023-07-16 09:21:57',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('3f6da1ce-cb4a-4882-811c-f56518c8110a',NULL,NULL,NULL,'2023-07-13 11:22:38',NULL,'2023-07-13 11:29:07',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('3fabe6d1-e8c7-4e64-8adc-4491d837abbb',NULL,NULL,NULL,'2023-07-13 10:20:22',NULL,'2023-07-13 10:20:29',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('4341d6a3-83b1-4983-af10-56468dbf6cc4',NULL,NULL,NULL,'2023-07-12 20:55:19',NULL,'2023-07-12 20:55:19',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('459670a0-e3db-4b74-aa7b-ae022848af3f',NULL,NULL,NULL,'2023-07-12 20:55:19',NULL,'2023-07-12 21:10:05',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('5dd9c115-556a-40d6-9b27-a4bd144c90ce',NULL,NULL,NULL,'2023-07-13 22:16:27',NULL,'2023-07-13 22:17:44',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('5fcd8f77-9e18-4f77-82e5-f84980147668',NULL,NULL,NULL,'2023-07-13 12:24:33',NULL,'2023-07-13 12:43:39',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('66b51829-2ebd-43fa-bb4f-a18cff20e7b3',NULL,NULL,NULL,'2023-07-15 18:28:30',NULL,'2023-07-15 18:29:41',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('6bdd8ea3-8ca7-4a51-b6d9-1f615d699bfc',NULL,NULL,NULL,'2023-07-13 12:49:08',NULL,'2023-07-13 12:49:12',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('6eeb047b-b9be-4cf0-89de-01a610769b7c',NULL,NULL,NULL,'2023-07-13 11:54:09',NULL,'2023-07-13 12:00:07',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('740c8227-30af-457f-8428-d4180b27ec03',NULL,NULL,NULL,'2023-07-17 13:29:34',NULL,'2023-07-17 13:29:34',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('7503aeb5-982d-489c-8d6b-f2fd275527c9',NULL,NULL,NULL,'2023-07-14 12:40:57',NULL,'2023-07-14 12:40:57',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('769ccd1e-7d72-436e-b7b7-06282af19238',NULL,NULL,NULL,'2023-07-16 19:46:34',NULL,'2023-07-16 19:47:24',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('76edde70-0867-4d40-821a-9415b21ce29b',NULL,NULL,NULL,'2023-07-16 18:03:41',NULL,'2023-07-16 18:11:27',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('774f9239-229e-4da6-85a5-a450ecbe03bd',NULL,NULL,NULL,'2023-07-16 20:49:51',NULL,'2023-07-16 20:50:14',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('7781ee1f-a476-4db8-8e60-06ba12879d55',NULL,NULL,NULL,'2023-07-13 16:53:10',NULL,'2023-07-13 16:53:10',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('77b810b0-4c50-4d19-b6a7-6470e12cda6c',NULL,NULL,NULL,'2023-07-14 11:53:10',NULL,'2023-07-14 12:04:46',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('7926aac3-e913-4b60-9881-3756c949df40',NULL,NULL,NULL,'2023-07-16 09:33:39',NULL,'2023-07-16 09:45:45',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('7e3e50c3-9ec8-4209-908d-2a6f081309ab',NULL,NULL,NULL,'2023-07-13 09:48:29',NULL,'2023-07-13 10:06:36',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('8491b2d5-77db-4755-9cda-971c40b17c9d',NULL,NULL,NULL,'2023-07-13 10:35:14',NULL,'2023-07-13 10:35:21',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('855653d3-e98b-4ece-8236-a77d750372c9',NULL,NULL,NULL,'2023-07-13 12:06:16',NULL,'2023-07-13 12:09:47',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('8780caae-e05c-40a3-b8e5-347e17285265',NULL,NULL,NULL,'2023-07-16 20:07:19',NULL,'2023-07-16 20:09:30',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('887d47e5-472b-4757-a0db-f4dbce77fd3b',NULL,NULL,NULL,'2023-07-13 22:17:50',NULL,'2023-07-13 22:20:57',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('8c264714-235d-4d51-a055-e465a58b647a',NULL,NULL,NULL,'2023-07-17 13:29:34',NULL,'2023-07-17 13:29:34',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('8edf2c63-e310-47ab-a326-853524e3e643',NULL,NULL,NULL,'2023-07-13 12:50:24',NULL,'2023-07-13 12:50:24',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('97b9b80e-00be-4d18-8373-352b8be26fbc',NULL,NULL,NULL,'2023-07-16 18:25:52',NULL,'2023-07-16 18:28:47',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('98956b0d-288c-457f-ac62-8c38c703be83',NULL,NULL,NULL,'2023-07-13 11:40:32',NULL,'2023-07-13 11:40:44',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('99da90a7-53b9-4040-a6a0-d5e26d27d295',NULL,NULL,NULL,'2023-07-17 13:29:34',NULL,'2023-07-17 13:29:34',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('9a5679e6-b474-4697-97e4-8be69886cfa8',NULL,NULL,NULL,'2023-07-14 11:19:47',NULL,'2023-07-14 11:38:58',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('9e8b10f6-d07d-4e18-a333-1dc17b49bdcb',NULL,NULL,NULL,'2023-07-16 18:03:41',NULL,'2023-07-16 18:03:42',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('9f3c95c2-4edc-4b3c-8657-b5baffeb8280',NULL,NULL,NULL,'2023-07-16 10:55:36',NULL,'2023-07-16 11:33:31',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('a0243616-d19e-4d57-b646-f489ab29ba40',NULL,NULL,NULL,'2023-07-13 09:13:42',NULL,'2023-07-13 09:23:10',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('a0c5e14e-b759-47a8-8120-e5b6d05ce1c7',NULL,NULL,NULL,'2023-07-16 14:56:31',NULL,'2023-07-16 15:04:50',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('a5e62e8f-eaf5-413f-b2ed-31883cd4fd86',NULL,NULL,NULL,'2023-07-14 12:54:11',NULL,'2023-07-14 12:56:14',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('ad8f5354-6242-44d5-bcfc-0977cdc409bf',NULL,NULL,NULL,'2023-07-16 13:18:59',NULL,'2023-07-16 13:42:58',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('adab2985-3272-45eb-bda2-189b8c22e086',NULL,NULL,NULL,'2023-07-14 09:43:59',NULL,'2023-07-14 09:43:59',NULL,300000,'N','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('ae04e584-cae1-42a4-a26a-0855ebb32be7',NULL,NULL,NULL,'2023-07-14 10:02:57',NULL,'2023-07-14 10:15:46',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('af764e6a-678f-4bd0-ae23-dbf317f6cb81',NULL,NULL,NULL,'2023-07-17 14:00:19',NULL,'2023-07-17 14:00:19',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('b882b3fd-957b-4d15-81eb-fa92b545e6d4',NULL,NULL,NULL,'2023-07-13 09:06:25',NULL,'2023-07-13 09:06:25',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('c43c3855-fa3f-4c67-bd7e-7fe97fa9392d',NULL,NULL,NULL,'2023-07-13 13:10:25',NULL,'2023-07-13 13:16:29',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('c4fb25db-bdbf-4314-ba2c-3fbd4402fe55',NULL,NULL,NULL,'2023-07-16 15:27:33',NULL,'2023-07-16 15:27:34',NULL,300000,'N','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('cbefcb0e-480e-43a1-a71b-372195eff7cf',NULL,NULL,NULL,'2023-07-16 12:02:47',NULL,'2023-07-16 12:02:48',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('cd55cece-798d-47f1-a5a2-94b43ea2f24b',NULL,NULL,NULL,'2023-07-16 19:56:10',NULL,'2023-07-16 19:56:10',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('d0e54984-254a-4c79-bf6b-41de68c4aeff',NULL,NULL,NULL,'2023-07-16 14:48:02',NULL,'2023-07-16 14:48:54',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('d0ec9e27-428b-4391-b601-10609e0b9d81',NULL,NULL,NULL,'2023-07-17 12:34:21',NULL,'2023-07-17 12:34:41',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('d495ab9d-cb6f-47a2-b705-9fd46fbdc6a8',NULL,NULL,NULL,'2023-07-15 23:34:59',NULL,'2023-07-15 23:44:17',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('d7ce5113-c362-4714-967e-02b7623aa281',NULL,NULL,NULL,'2023-07-17 13:23:49',NULL,'2023-07-17 13:23:51',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('d7d1d642-8c52-4853-afaf-d0fe2b1e9036',NULL,NULL,NULL,'2023-07-15 18:11:01',NULL,'2023-07-15 18:23:03',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('d9be7a40-1a42-49f5-a52a-db708130280a',NULL,NULL,NULL,'2023-07-14 08:49:09',NULL,'2023-07-14 08:50:33',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('e2c686a4-e11a-487c-94c0-eeed1d7a8250',NULL,NULL,NULL,'2023-07-14 09:44:00',NULL,'2023-07-14 09:53:36',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('e5dafc10-1d23-4000-9106-872516790c2b',NULL,NULL,NULL,'2023-07-14 11:01:59',NULL,'2023-07-14 11:11:54',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('e83fdcc0-8d57-4700-8c39-6406db72a335',NULL,NULL,NULL,'2023-07-13 10:46:04',NULL,'2023-07-13 10:46:56',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('e874199f-6879-4b2a-8b64-4b2ddb03e70f',NULL,NULL,NULL,'2023-07-15 23:14:45',NULL,'2023-07-15 23:16:37',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('e9c5e5d0-eed9-47e9-b0c1-2f9cb34a8719',NULL,NULL,NULL,'2023-07-13 14:40:55',NULL,'2023-07-13 14:41:33',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('e9dfd39b-7626-4b0c-868a-5c5f1a522c00',NULL,NULL,NULL,'2023-07-14 13:18:06',NULL,'2023-07-14 13:25:24',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('eab133f1-6d8b-45e6-abeb-cbfd5d0d880e',NULL,NULL,NULL,'2023-07-17 14:16:14',NULL,'2023-07-17 14:35:42',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('eb45b618-ca9a-4448-8ca1-57aaff227b38',NULL,NULL,NULL,'2023-07-15 22:36:29',NULL,'2023-07-15 22:51:16',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('f05f1cd1-48e1-4f76-87dc-9f4812f7f623',NULL,NULL,NULL,'2023-07-13 13:34:08',NULL,'2023-07-13 13:38:39',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('f0ec5067-a528-4e96-8c0b-f4b815d8b2eb',NULL,NULL,NULL,'2023-07-16 18:03:41',NULL,'2023-07-16 18:03:42',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('f97c0463-74f0-4062-a6d6-47b1f8367355',NULL,NULL,NULL,'2023-07-16 14:30:45',NULL,'2023-07-16 14:38:55',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('fa2a3e6e-7d72-4cb3-a84c-c9ce4f54420f',NULL,NULL,NULL,'2023-07-15 23:22:04',NULL,'2023-07-15 23:28:58',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('fa318ee6-d7de-4ca5-8eed-3e7f8b2c3f0f',NULL,NULL,NULL,'2023-07-13 09:42:44',NULL,'2023-07-13 09:42:44',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL),
('fef099b7-6288-4ea3-9b63-6227e9805e9d',NULL,NULL,NULL,'2023-07-13 11:30:23',NULL,'2023-07-13 11:30:23',NULL,300000,'Y','0:0:0:0:0:0:0:1','Windows 10','Firefox 11','Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/115.0',NULL);

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
  `avatar_url` varchar(64) DEFAULT '' COMMENT '用户头像 URL',
  `salt` varchar(32) DEFAULT '',
  `create_time` datetime DEFAULT current_timestamp(),
  `status` int(11) DEFAULT 1 COMMENT '-1 特权用户不能删除 0正常 1禁用 2删除',
  `remark` varchar(500) DEFAULT '',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `nicefish_rbac_user` */

insert  into `nicefish_rbac_user`(`user_id`,`user_name`,`nick_name`,`password`,`email`,`cellphone`,`gender`,`avatar_url`,`salt`,`create_time`,`status`,`remark`) values 
(1,'admin@126.com','系统管理员','93c1ec603e93996a9cb18dc4b8f6c468','admin@126.com','',0,'','570a31','2023-07-12 20:55:53',1,'');

/*Table structure for table `nicefish_rbac_user_role` */

DROP TABLE IF EXISTS `nicefish_rbac_user_role`;

CREATE TABLE `nicefish_rbac_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `nicefish_rbac_user_role` */

insert  into `nicefish_rbac_user_role`(`user_id`,`role_id`) values 
(1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

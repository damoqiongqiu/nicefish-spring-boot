/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2023/7/8 22:23:22                            */
/*==============================================================*/


drop table if exists nicefish_cms_comment;

drop table if exists nicefish_cms_file_upload;

drop table if exists nicefish_cms_post;

drop table if exists nicefish_cms_sys_params;

drop table if exists nicefish_rbac_api;

drop table if exists nicefish_rbac_component;

drop table if exists nicefish_rbac_role;

drop table if exists nicefish_rbac_role_api;

drop table if exists nicefish_rbac_role_component;

drop table if exists nicefish_rbac_session;

drop table if exists nicefish_rbac_user;

drop table if exists nicefish_rbac_user_role;

drop table if exists hibernate_sequence;

/*==============================================================*/
/* Table: nicefish_cms_comment                                  */
/*==============================================================*/
create table nicefish_cms_comment
(
   comment_id           int(11) not null auto_increment,
   post_id              int(11) not null,
   content              text,
   comment_time         datetime not null default current_timestamp,
   comment_ip           varchar(64) default null comment '评论者的IP地址',
   p_id                 int(11) default -1 comment '父层评论的ID，用来实现评论盖楼效果',
   user_id              int(11) not null,
   user_name            varchar(64) default null,
   nick_name            varchar(64) default null,
   email                varchar(64) default null,
   status               int(11) default 1 comment '评论状态：0：已删除；1：已发布；2:优质评论；',
   primary key (comment_id)
);

alter table nicefish_cms_comment comment '评论表';

/*==============================================================*/
/* Table: nicefish_cms_file_upload                              */
/*==============================================================*/
create table nicefish_cms_file_upload
(
   up_id                int(11) not null,
   up_time              datetime default current_timestamp,
   file_name            varchar(128) default null comment '与物理保存的文件名一致',
   file_type            varchar(32) default '1' comment '1、图片；\n            2、附件；',
   file_size            float default 0,
   file_width           int(11) default 0,
   file_height          int(11) default 0,
   display_order        int(11) default 0,
   user_id              int(11) default 0,
   url                  varchar(128) comment '访问此文件的路径，可以指向系统外部的 URL 。',
   file_module          int(11) default 1 comment '1、metro相关的图片\n            2、文章相关的图片\n            3、图书相关的图片\n            4、小图标\n            5、用户头像\n            ',
   file_desc            varchar(255) default null,
   primary key (up_id)
);

alter table nicefish_cms_file_upload comment '上传文件的记录。';

/*==============================================================*/
/* Table: nicefish_cms_post                                     */
/*==============================================================*/
create table nicefish_cms_post
(
   post_id              int(11) not null auto_increment,
   post_title           varchar(128) not null,
   post_summary         varchar(1024) default null comment '摘要，文章列表页需要使用',
   post_content         text comment '内容',
   original_url         varchar(512) default null comment '原文链接，如果有这个字段，说明是翻译文章',
   thumb_img_url        varchar(256) default null comment '缩略图链接',
   header_img_url       varchar(256),
   post_time            datetime not null default current_timestamp,
   udpate_time          datetime default current_timestamp,
   post_type            char(1) not null default '0' comment '文章的类型，0原创1翻译',
   read_times           int(11) not null default 1 comment '阅读数',
   liked_times          int(11) not null default 0 comment '点赞数',
   comment_times        int(11) not null default 0 comment '评论数',
   user_id              int(11) not null,
   email                varchar(64) default null,
   nick_name            varchar(64) default null comment '冗余字段，对应 nicefish_rbac_user 表中的 NICK_NAME 字段，用来避免表关联。',
   enable_comment       char(1) not null default '1' comment '是否可评论\n            0不可\n            1可',
   status               int(11) not null default 4 comment '状态：\n            1、已删除\n            2、已归档，已归档的内容禁止评论，文章不可删除\n            3、草稿\n            4、已发布\n            5、精华-->精华文章不可删除\n            6、已推至首页\n            ',
   primary key (post_id)
);

alter table nicefish_cms_post comment '文章表';

/*==============================================================*/
/* Table: nicefish_cms_sys_params                               */
/*==============================================================*/
create table nicefish_cms_sys_params
(
   param_key            varchar(128) not null,
   param_value          varchar(2048) not null,
   param_desc           varchar(512) default null,
   primary key (param_key)
);

alter table nicefish_cms_sys_params comment '系统参数配置表';

/*==============================================================*/
/* Table: nicefish_rbac_api                                     */
/*==============================================================*/
create table nicefish_rbac_api
(
   api_id               int(11) not null auto_increment,
   api_name             varchar(64) not null,
   url                  varchar(64) comment 'URL 的匹配模式和 @RequestMapping 中的定义模式完全相同。',
   permission           varchar(64) not null default '*' comment '权限定义，按照 Apache Shiro 的权限定义规则进行定义。
            为了避免重复和歧义，权限字符串必须是不同的。',
   create_time          datetime,
   update_time          datetime,
   remark               varchar(500),
   primary key (api_id)
);

alter table nicefish_rbac_api comment '用来定义并保护服务端 API 接口的权限。';

/*==============================================================*/
/* Table: nicefish_rbac_component                               */
/*==============================================================*/
create table nicefish_rbac_component
(
   component_id         int(11) not null auto_increment,
   p_id                 int(11),
   component_name       varchar(64) not null,
   icon                 varchar(64),
   url                  varchar(64) comment '组件对应的 URL 路径，可以定义成系统外部的链接 URL',
   display_order        int(11) not null default 1 comment '组件在前端屏幕上的显示顺序，按数值从小到达排列，数值越小越靠屏幕顶部。',
   permission           varchar(64) not null default '*' comment '权限定义，按照 Apache Shiro 的权限定义规则进行定义。
            为了避免重复和歧义，权限字符串必须是不同的。',
   create_time          datetime default current_timestamp,
   update_time          datetime,
   visiable             char(1) not null default 'Y' comment '菜单是否可见，只能是 N 和 Y 两个取值，字母大写。',
   remark               varchar(500),
   primary key (component_id)
);

alter table nicefish_rbac_component comment '用来定义前端页面上的组件权限，
component 可以是菜单、按钮，甚至可以细致到一个 HTML 元素。
';

/*==============================================================*/
/* Table: nicefish_rbac_role                                    */
/*==============================================================*/
create table nicefish_rbac_role
(
   role_id              int(11) not null auto_increment,
   role_name            varchar(64) not null,
   role_key             varchar(100) not null,
   status               int(1) not null default 0 comment '-1 特权角色，不能删除 0正常 1停用 2删除',
   remark               varchar(500) default '',
   primary key (role_id)
);

/*==============================================================*/
/* Table: nicefish_rbac_role_api                                */
/*==============================================================*/
create table nicefish_rbac_role_api
(
   role_id              int(11) not null,
   api_id               int(11) not null,
   primary key (role_id, api_id)
);

alter table nicefish_rbac_role_api comment '角色与 API  接口之间的关联关系。';

/*==============================================================*/
/* Table: nicefish_rbac_role_component                          */
/*==============================================================*/
create table nicefish_rbac_role_component
(
   role_id              int(11) not null,
   menu_id              int(11) not null,
   primary key (role_id, menu_id)
);

alter table nicefish_rbac_role_component comment '角色与菜单的关联关系';

/*==============================================================*/
/* Table: nicefish_rbac_session                                 */
/*==============================================================*/
create table nicefish_rbac_session
(
   session_id           varchar(64) not null default '',
   user_id              int(11) comment '如果用户没有登录，此列可为空',
   user_name            varchar(64),
   start_time           datetime,
   stop_time            datetime,
   last_access_time     datetime,
   timeout              int(11) comment '过期时间',
   expired              char(1) comment '是否已经过期',
   host                 varchar(64) default '' comment 'IP地址',
   os                   varchar(64) default '',
   browser              varchar(64) default '',
   user_agent           varchar(255) comment '浏览器发送过来的 UserAgent 字符串',
   primary key (session_id)
);

alter table nicefish_rbac_session comment '用来持久化 Session ，应用端可以利用此表实现 SSO 。';

/*==============================================================*/
/* Table: nicefish_rbac_user                                    */
/*==============================================================*/
create table nicefish_rbac_user
(
   user_id              int(11) not null auto_increment,
   user_name            varchar(64) not null,
   nick_name            varchar(64) not null,
   password             varchar(64) default '',
   email                varchar(64) default '',
   cellphone            varchar(32) default '',
   gender               char(1) default '0' comment '0男 1女 2未知',
   avatar_url           varchar(64) default '' comment '用户头像 URL',
   salt                 varchar(32) default '',
   create_time          datetime not null default current_timestamp,
   status               char(1) default '0' comment '-1 特权用户不能删除 0正常 1禁用 2删除',
   remark               varchar(500) default '',
   primary key (user_id)
);

/*==============================================================*/
/* Table: nicefish_rbac_user_role                               */
/*==============================================================*/
create table nicefish_rbac_user_role
(
   user_id              int(11) not null,
   role_id              int(11) not null,
   primary key (user_id, role_id)
);

/*==============================================================*/
/* Table: hibernate_sequence                                    */
/*==============================================================*/
create table hibernate_sequence
(
   next_val             bigint(20) default null
);

alter table hibernate_sequence comment 'MySQL 没有 sequence 机制，用表来模拟。';


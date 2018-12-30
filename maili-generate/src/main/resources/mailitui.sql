/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.23-log : Database - mailitui
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mailitui` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mailitui`;

/*Table structure for table `contact` */

DROP TABLE IF EXISTS `contact`;

CREATE TABLE `contact` (
  `id` varchar(32) NOT NULL COMMENT '唯一id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `name` varchar(64) DEFAULT NULL COMMENT '联系人',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `region` varchar(150) DEFAULT NULL COMMENT '地区',
  `address` varchar(500) DEFAULT NULL COMMENT '详细地址',
  `postal_code` varchar(16) DEFAULT NULL COMMENT '邮政编码',
  `is_default` char(1) DEFAULT NULL COMMENT '默认地址(0:不默认,1:默认)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `disabled` char(1) DEFAULT '0' COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `contact` */

insert  into `contact`(`id`,`user_id`,`name`,`mobile`,`region`,`address`,`postal_code`,`is_default`,`create_date`,`disabled`) values ('1039218373741813760','75bcaa15158a445f926b05ac779332e1','张三','17602150641','宝安区','西乡坪洲','518101','1','2018-09-11 02:25:43','0'),('1042812848176533504','38d7e705ccdd46f5a3315377883344c7','刘鹏迪','13380848826','陆丰市','东海镇玉印小区三巷九号','516500','1','2018-09-21 00:28:52','0');

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `id` varchar(16) NOT NULL COMMENT '唯一id',
  `column_id` varchar(16) DEFAULT NULL COMMENT '栏目id',
  `title` varchar(32) DEFAULT NULL COMMENT '新闻标题',
  `keyword` varchar(150) DEFAULT NULL COMMENT '关键字',
  `description` varchar(250) DEFAULT NULL COMMENT '描述说明',
  `source` varchar(16) DEFAULT NULL COMMENT '资讯来源',
  `status` char(1) DEFAULT NULL COMMENT '资讯状态(0:未发布,1:已发布,2:已取消)',
  `create_by` varchar(16) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `publish_by` varchar(32) DEFAULT NULL COMMENT '发布人',
  `publish_date` datetime DEFAULT NULL COMMENT '发布时间',
  `content` mediumtext COMMENT '资讯内容',
  `disabled` char(1) DEFAULT NULL COMMENT '删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `news` */

/*Table structure for table `sys_column` */

DROP TABLE IF EXISTS `sys_column`;

CREATE TABLE `sys_column` (
  `id` varchar(32) NOT NULL COMMENT '自增id',
  `name` varchar(16) DEFAULT NULL COMMENT '栏目名称',
  `parent_id` varchar(16) DEFAULT NULL COMMENT '父id',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_column` */

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` varchar(32) NOT NULL COMMENT '唯一id',
  `type` varchar(32) DEFAULT NULL COMMENT '字典类型',
  `label` varchar(64) DEFAULT NULL COMMENT '字典标签',
  `value` varchar(32) DEFAULT NULL COMMENT '字典值',
  `sort` int(10) DEFAULT NULL COMMENT '排序权重',
  `disabled` char(1) DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_dict` */

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '操作用户',
  `type` char(1) DEFAULT NULL COMMENT '日志类型(0:登录登出,1:操作日志,2:异常日志)',
  `operation` varchar(64) DEFAULT NULL COMMENT '操作名称',
  `method` varchar(128) DEFAULT NULL COMMENT '请求方法',
  `request_url` varchar(128) DEFAULT NULL COMMENT '请求地址',
  `request_ip` varchar(16) DEFAULT NULL COMMENT '请求ip',
  `equip_ment` varchar(32) DEFAULT NULL COMMENT '设备名称',
  `operation_system` varchar(32) DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(16) DEFAULT NULL COMMENT '浏览器名',
  `resp_time` bigint(16) DEFAULT NULL COMMENT '响应时间',
  `create_date` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`user_id`,`type`,`operation`,`method`,`request_url`,`request_ip`,`equip_ment`,`operation_system`,`browser`,`resp_time`,`create_date`) values ('1069960301877886978','1','1','更新角色','com.mall.plus.web.controller.sys.SysRoleController.update()','/sys/role/update','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',152,'2018-12-04 22:23:09'),('1069960346413006850','1','1','更新角色','com.mall.plus.web.controller.sys.SysRoleController.update()','/sys/role/update','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',42,'2018-12-04 22:23:20'),('1077404890242969602','1','1','新增菜单','com.zhou.web.controller.sys.SysMenuController.save()','/sys/menu/save','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',115,'2018-12-25 11:25:18'),('1077404918575493122','1','1','更新菜单','com.zhou.web.controller.sys.SysMenuController.update()','/sys/menu/update','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',18,'2018-12-25 11:25:24'),('1077405290119524354','1','1','新增菜单','com.zhou.web.controller.sys.SysMenuController.save()','/sys/menu/save','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',21,'2018-12-25 11:26:53'),('1077405466427092993','1','1','新增菜单','com.zhou.web.controller.sys.SysMenuController.save()','/sys/menu/save','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',11,'2018-12-25 11:27:35'),('1077405648992563202','1','1','新增菜单','com.zhou.web.controller.sys.SysMenuController.save()','/sys/menu/save','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',10,'2018-12-25 11:28:19'),('1077405806497067009','1','1','新增菜单','com.zhou.web.controller.sys.SysMenuController.save()','/sys/menu/save','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',9,'2018-12-25 11:28:56'),('1077407681439035393','1','1','更新菜单','com.zhou.web.controller.sys.SysMenuController.update()','/sys/menu/update','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',10,'2018-12-25 11:36:23'),('1077407755216842754','1','1','更新菜单','com.zhou.web.controller.sys.SysMenuController.update()','/sys/menu/update','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',13,'2018-12-25 11:36:41'),('1077407825903448065','1','1','更新菜单','com.zhou.web.controller.sys.SysMenuController.update()','/sys/menu/update','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',11,'2018-12-25 11:36:58'),('1077407905733636098','1','1','更新菜单','com.zhou.web.controller.sys.SysMenuController.update()','/sys/menu/update','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',11,'2018-12-25 11:37:17'),('1077408157626757121','1','1','更新菜单','com.zhou.web.controller.sys.SysMenuController.update()','/sys/menu/update','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',10,'2018-12-25 11:38:17'),('1077408176903778305','1','1','更新菜单','com.zhou.web.controller.sys.SysMenuController.update()','/sys/menu/update','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',9,'2018-12-25 11:38:21'),('1077494417280159745','1','1','更新菜单','com.zhou.web.controller.sys.SysMenuController.update()','/sys/menu/update','0:0:0:0:0:0:0:1','PC','Windows 10','Chrome',132,'2018-12-25 17:21:03');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` varchar(32) NOT NULL,
  `parent_id` varchar(32) NOT NULL,
  `type` char(1) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `sort` int(10) DEFAULT NULL,
  `href` varchar(2000) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `permission` varchar(150) DEFAULT NULL COMMENT '权限标识',
  `disabled` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`parent_id`,`type`,`name`,`sort`,`href`,`icon`,`permission`,`disabled`) values ('1','0','0','根目录',0,'','sy-icon-home',NULL,'0'),('1042031579413340160','5','1','用户管理',20200,'sys/user/list','sy-icon-user','','0'),('1042383848677269504','5','1','角色管理',20300,'sys/role/list','sy-icon-role','','0'),('1042386053910679552','5','1','字典管理',20400,'sys/dict/list','sy-icon-dict','','0'),('1065183574316216322','1042031579413340160','2','查看',20201,' ','','sys:user:view','0'),('1065225462100455426','1042031579413340160','2','添加',20202,'','sy-icon-friendadd','sys:user:add','0'),('1065226013957615617','1042031579413340160','2','编辑',20203,'','sy-icon-friendadd','sys:user:update','0'),('1065226223723147265','1042031579413340160','2','删除',20204,'','el-icon-delete','sys:user:remove','0'),('1065226393709899778','1042383848677269504','2','查看',20301,'','el-icon-view','sys:role:view','0'),('1065226496071888898','1042383848677269504','2','添加',20302,'','','sys:role:save','0'),('1065226739454767106','1042383848677269504','2','编辑',20303,'','','sys:role:update','0'),('1065226908464246786','1042383848677269504','2','删除',20304,'','','sys:role:remove','0'),('1065239900409004033','6','2','查看',20101,'','el-icon-success','sys:menu:view','0'),('1065240014502461442','6','2','添加',20102,'','','sys:menu:save','0'),('1065240103400734721','6','2','编辑',20103,'','','sys:menu:update','0'),('1065240238654455809','6','2','删除',20104,'','','sys:menu:remove','0'),('1065240337757470722','1042386053910679552','2','查看',20401,'','','sys:dict:view','0'),('1065240435077906433','1042386053910679552','2','添加',20402,'','','sys:dict:save','0'),('1065240596319535106','1042386053910679552','2','编辑',20403,'','','sys:dict:update','0'),('1065240778570432514','1042386053910679552','2','删除',20404,'','','sys:dict:remove','0'),('1069514016653713410','5','1','日志管理',20500,'sys/log/list','el-icon-document','','0'),('1069514187978448898','1069514016653713410','2','查看',20501,'','','sys:log:view','0'),('1069818391452368898','5','1','接口管理',20600,'swagger-ui.html','sy-icon-api','','0'),('1069819655133249537','1069818391452368898','2','查看',20601,'','el-icon-tickets',' ','0'),('1077404890075197442','1','0','资讯管理',10000,'','sy-icon-text','','0'),('1077405290077581314','1077404890075197442','1','栏目列表',10100,'column/list','sy-icon-sortlight','','0'),('1077405466372567042','1077404890075197442','1','资讯列表',10200,'news/list','el-icon-document','','0'),('1077405648950620162','1077405290077581314','2','查看',10101,'','el-icon-view','sys:column:view','0'),('1077405806450929666','1077405466372567042','2','查看',10201,'','el-icon-view','sys:news:view','0'),('5','1','0','系统管理',20000,'','sy-icon-settings','','0'),('6','5','1','菜单管理',20100,'sys/menu/list','el-icon-menu','','0');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(32) NOT NULL COMMENT '唯一id',
  `code` varchar(16) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(16) DEFAULT NULL COMMENT '角色名称',
  `disabled` char(1) DEFAULT '0' COMMENT '删除状态',
  `menu_ids` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`code`,`name`,`disabled`,`menu_ids`) values ('1042391793391194112','0000','系统管理员','0',NULL),('1042648514293751808','1000','客户','0','6,1065239900409004033,1065240014502461442,1065240103400734721,1065240238654455809,1065183574316216322,1065226393709899778,1042386053910679552,1065240337757470722,1065240435077906433,1065240596319535106,1065240778570432514');

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values ('1042648514293751808','6'),('1042648514293751808','1065239900409004033'),('1042648514293751808','1065240014502461442'),('1042648514293751808','1065240103400734721'),('1042648514293751808','1065240238654455809'),('1042648514293751808','1065183574316216322'),('1042648514293751808','1065226393709899778'),('1042648514293751808','1042386053910679552'),('1042648514293751808','1065240337757470722'),('1042648514293751808','1065240435077906433'),('1042648514293751808','1065240596319535106'),('1042648514293751808','1065240778570432514'),('1042648514293751808','1'),('1042648514293751808','5'),('1042648514293751808','1042031579413340160'),('1042648514293751808','1042383848677269504');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT '唯一id',
  `username` varchar(64) DEFAULT NULL COMMENT '登录名称',
  `password` varchar(128) DEFAULT NULL COMMENT '登录密码',
  `name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `disabled` char(1) DEFAULT '0' COMMENT '删除标志(0:否,1:是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`name`,`disabled`) values ('1','admin','ba2fd76fd243ec1cc44774b0167232fa1e3a3e714f362abcb78d2304aa456a01','管理员','0'),('1043198757636530176','slem321','ba2fd76fd243ec1cc44774b0167232fa1e3a3e714f362abcb78d2304aa456a01','sssss','0'),('1069532620258496513','123','a9a309307b1ddd8613d37ae3ff874b23d81a6d7418f144773f44748e80dc6784','12312','0');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values ('1','1042391793391194112'),('1042648710838837248','1042648514293751808'),('1043198757636530176','1042648514293751808'),('1069530528731058177','1042648514293751808'),('1069532620258496513','1042391793391194112');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

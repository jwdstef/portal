/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : firstsystem

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-05-07 22:02:02
*/

create database firstsystem default charset=utf8;

use firstsystem;

-- ----------------------------
-- Table structure for `s_data_dict`
-- ----------------------------
DROP TABLE IF EXISTS `s_data_dict`;
CREATE TABLE `s_data_dict` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `parent_id` int(18) DEFAULT NULL,
  `dict_type` varchar(128) NOT NULL,
  `dict_code` varchar(128) NOT NULL,
  `dict_value` varchar(256) NOT NULL,
  `orader_value` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_data_dict
-- ----------------------------

-- ----------------------------
-- Table structure for `s_menu`
-- ----------------------------
DROP TABLE IF EXISTS `s_menu`;
CREATE TABLE `s_menu` (
  `id` int(18) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `text` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `url` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `iconCls` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT '菜单图标',
  `parent_id` int(18) DEFAULT NULL COMMENT '父类ID',
  `order_value` int(10) DEFAULT NULL COMMENT '排序值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of s_menu
-- ----------------------------
INSERT INTO `s_menu` VALUES ('1', '快速开发系统', null, null, null, '0');
INSERT INTO `s_menu` VALUES ('10', '系统管理', null, null, '1', '1');
INSERT INTO `s_menu` VALUES ('11', '用户管理', 'user/', null, '10', '11');
INSERT INTO `s_menu` VALUES ('12', '菜单管理', 'http://www.baidu.com', null, '10', '12');
INSERT INTO `s_menu` VALUES ('13', '权限管理', 'http://www.baidu.com', null, '10', '13');

-- ----------------------------
-- Table structure for `s_resources`
-- ----------------------------
DROP TABLE IF EXISTS `s_resources`;
CREATE TABLE `s_resources` (
  `resources_id` int(18) NOT NULL AUTO_INCREMENT,
  `resources_name` varchar(50) DEFAULT NULL,
  `resources_code` varchar(200) DEFAULT NULL,
  `resources_remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`resources_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_resources
-- ----------------------------
INSERT INTO `s_resources` VALUES ('1', '所有权限', '*:*', '超级管理员的权限，‘无解了，所有的权限都对其开放');

-- ----------------------------
-- Table structure for `s_role`
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `role_id` int(18) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  `role_remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('1', '超级管理员', '超级用户，拥有所有权限');

-- ----------------------------
-- Table structure for `s_role_resources`
-- ----------------------------
DROP TABLE IF EXISTS `s_role_resources`;
CREATE TABLE `s_role_resources` (
  `role_id` int(18) DEFAULT NULL,
  `resources_id` int(18) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_role_resources
-- ----------------------------
INSERT INTO `s_role_resources` VALUES ('1', '1');

-- ----------------------------
-- Table structure for `s_user`
-- ----------------------------
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS  `s_user`;
CREATE TABLE `s_user` (
  `user_id` int(18) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(200) NOT NULL,
  `salt` varchar(50) NOT NULL,
  `locked` int(1) NOT NULL,
  `real_name` varchar(200) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `create_time` datetime DEFAULT null COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of s_user
-- ----------------------------
insert into `s_user`(`user_id`,`username`,`password`,`salt`,`locked`,`real_name`,`email`,`phone`,`create_time`,`last_login_time`) values
('1','admin','ef94fdb7001fce2c97bf8020899a6b65','md5','0',null,null,null,'2015-05-25 21:11:15',null),
('2','sysadmin','3fdfb9751eda287ddde9e3726b893f86','sysadmin855e4bf810581f256b8a60b98f2aadb0','0',null,null,null,'2015-05-25 21:11:15',null);
SET FOREIGN_KEY_CHECKS = 1;
-- ----------------------------
-- Table structure for `s_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `s_user_role`;
CREATE TABLE `s_user_role` (
  `user_id` int(18) DEFAULT NULL,
  `role_id` int(18) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user_role
-- ----------------------------
INSERT INTO `s_user_role` VALUES ('1', '1');

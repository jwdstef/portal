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
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `user_id` int(18) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(200) NOT NULL,
  `salt` varchar(50) DEFAULT NULL,
  `locked` int(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('1', 'admin', 'admin', 'md5', '0');
INSERT INTO `s_user` VALUES ('2', 'sysadmin', '3fdfb9751eda287ddde9e3726b893f86', 'sysadmin855e4bf810581f256b8a60b98f2aadb0', '0');
-- ----------------------------
-- Table structure for `s_user_detail`
-- ----------------------------
DROP TABLE IF EXISTS `s_user_detail`;
CREATE TABLE `s_user_detail` (
  `user_id` int(18) NOT NULL COMMENT '用户id与s_user.user_id关联',
  `real_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '真实名字',
  `email` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `phone` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '电话号码',
  `create_time` datatime  COMMENT '创建时间',
  `last_login_time` datatime  COMMENT '最后登录时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of s_user_detail
-- ----------------------------
INSERT INTO `s_user_detail` VALUES ('1', 'king', 'pwpw1218@126.com', '18171204445', '2015-03-21 22:10:38', null);
INSERT INTO `s_user_detail` VALUES ('2', '1111111111', '24@1.c', '11223333', '2015-04-27 23:28:56', null);

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

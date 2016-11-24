/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2016-04-01 14:49:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for testtabel
-- ----------------------------
DROP TABLE IF EXISTS `testtabel`;
CREATE TABLE `testtabel` (
  `tid` varchar(36) CHARACTER SET utf8 NOT NULL,
  `tname` varchar(36) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of testtabel
-- ----------------------------
INSERT INTO `testtabel` VALUES ('24486201880739841', '聂伟');
INSERT INTO `testtabel` VALUES ('24486201880739842', '章科');
INSERT INTO `testtabel` VALUES ('24486201880739843', '郭亚芳');
INSERT INTO `testtabel` VALUES ('24486201880739844', '田进');

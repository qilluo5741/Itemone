/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : sharebo

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2016-05-16 10:17:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for advertising
-- ----------------------------
DROP TABLE IF EXISTS `advertising`;
CREATE TABLE `advertising` (
  `advid` varbinary(36) NOT NULL COMMENT '广告主键',
  `adname` varchar(30) DEFAULT NULL,
  `adv_img_url` varchar(50) NOT NULL COMMENT '广告图片路径',
  PRIMARY KEY (`advid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告表Advertising';

-- ----------------------------
-- Records of advertising
-- ----------------------------
INSERT INTO `advertising` VALUES (0x32, '223', '13245');

-- ----------------------------
-- Table structure for certification
-- ----------------------------
DROP TABLE IF EXISTS `certification`;
CREATE TABLE `certification` (
  `certificationId` varchar(36) NOT NULL COMMENT '车辆认证表主键',
  `userid` varchar(36) NOT NULL COMMENT '用户ID(外键)',
  `vehicleid` varchar(36) NOT NULL COMMENT '车辆ID(外键)',
  `driver_license_positive` varchar(70) DEFAULT NULL COMMENT '驾驶证（正面）',
  `driver_license_reverse` varchar(70) DEFAULT NULL COMMENT '驾驶证（反面）',
  `vehicle_license_positive` varchar(70) DEFAULT NULL COMMENT '行驶证（正面）',
  `vehicle_license_reverse` varchar(70) DEFAULT NULL COMMENT '行驶证（背面）',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `certification_status` int(1) DEFAULT NULL COMMENT '认证状态（0：未认证，1：已认证，2：拒绝认证）',
  `thistime` datetime DEFAULT NULL COMMENT '认证时间',
  `audittime` datetime DEFAULT NULL,
  PRIMARY KEY (`certificationId`),
  KEY `FK_Certification_User` (`userid`),
  KEY `FK_Certification_Vehicle` (`vehicleid`),
  CONSTRAINT `FK_Certification_User` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  CONSTRAINT `FK_Certification_Vehicle` FOREIGN KEY (`vehicleid`) REFERENCES `vehicle` (`vehicleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车主认证表';

-- ----------------------------
-- Records of certification
-- ----------------------------

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `collectid` varchar(36) NOT NULL COMMENT '车位收藏表Id',
  `userid` varchar(36) NOT NULL COMMENT '用户表（外键）',
  `parkid` varchar(36) NOT NULL COMMENT '车位表（外键）',
  `collecttime` datetime NOT NULL COMMENT '标记时间',
  PRIMARY KEY (`collectid`),
  KEY `FK_Collect_Parkingspace` (`parkid`),
  KEY `FK_Collect_User` (`userid`),
  CONSTRAINT `FK_Collect_Parkingspace` FOREIGN KEY (`parkid`) REFERENCES `parkingspace` (`parkid`),
  CONSTRAINT `FK_Collect_User` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车位收藏表';

-- ----------------------------
-- Records of collect
-- ----------------------------

-- ----------------------------
-- Table structure for community
-- ----------------------------
DROP TABLE IF EXISTS `community`;
CREATE TABLE `community` (
  `communityId` varchar(36) NOT NULL COMMENT '小区表主键ID',
  `community_name` varchar(40) NOT NULL COMMENT '小区名字',
  `community_address` varchar(200) NOT NULL COMMENT '小区地址',
  `administrative` varchar(50) DEFAULT NULL COMMENT '行政区',
  `addtime` datetime NOT NULL COMMENT '录入时间',
  `isaudit` int(1) NOT NULL COMMENT '是否审核（0：未审核，1,：已审核）',
  PRIMARY KEY (`communityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='小区表';

-- ----------------------------
-- Records of community
-- ----------------------------
INSERT INTO `community` VALUES ('1', '滨江国际广场', '江浦路1058弄小区', null, '2016-05-11 20:12:15', '1');

-- ----------------------------
-- Table structure for dayrules
-- ----------------------------
DROP TABLE IF EXISTS `dayrules`;
CREATE TABLE `dayrules` (
  `dayid` varchar(36) NOT NULL,
  `thisDay` date DEFAULT NULL COMMENT '日期',
  `parkid` varchar(36) DEFAULT NULL COMMENT '车位外键',
  `hours_01` int(1) DEFAULT NULL,
  `hours_23` int(1) DEFAULT NULL,
  `hours_02` int(1) DEFAULT NULL,
  `hours_03` int(1) DEFAULT NULL,
  `hours_04` int(1) DEFAULT NULL,
  `hours_05` int(1) DEFAULT NULL,
  `hours_06` int(1) DEFAULT NULL,
  `hours_07` int(1) DEFAULT NULL,
  `hours_08` int(1) DEFAULT NULL,
  `hours_09` int(1) DEFAULT NULL,
  `hours_10` int(1) DEFAULT NULL,
  `hours_11` int(1) DEFAULT NULL,
  `hours_12` int(1) DEFAULT NULL,
  `hours_13` int(1) DEFAULT NULL,
  `hours_14` int(1) DEFAULT NULL,
  `hours_15` int(1) DEFAULT NULL,
  `hours_16` int(1) DEFAULT NULL,
  `hours_17` int(1) DEFAULT NULL,
  `hours_18` int(1) DEFAULT NULL,
  `hours_19` int(1) DEFAULT NULL,
  `hours_20` int(1) DEFAULT NULL,
  `hours_21` int(1) DEFAULT NULL,
  `hours_22` int(1) DEFAULT NULL,
  `hours_24` int(1) DEFAULT NULL,
  PRIMARY KEY (`dayid`),
  KEY `FK_Dayrules_ParkInfo` (`parkid`),
  CONSTRAINT `FK_Dayrules_ParkInfo` FOREIGN KEY (`parkid`) REFERENCES `parkingspace` (`parkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日期规则表';

-- ----------------------------
-- Records of dayrules
-- ----------------------------

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `feedbackid` varchar(36) NOT NULL COMMENT '用户反馈主键',
  `feedback_content` varchar(300) NOT NULL COMMENT '用户反馈内容',
  `feedback_img` varchar(200) DEFAULT NULL COMMENT '反馈图片（只支持一张图）',
  `userid` varchar(36) NOT NULL COMMENT '反馈的用户',
  PRIMARY KEY (`feedbackid`),
  KEY `FK_Feedback_User` (`userid`),
  CONSTRAINT `FK_Feedback_User` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户反馈表';

-- ----------------------------
-- Records of feedback
-- ----------------------------

-- ----------------------------
-- Table structure for invitedrecord
-- ----------------------------
DROP TABLE IF EXISTS `invitedrecord`;
CREATE TABLE `invitedrecord` (
  `invId` varchar(36) NOT NULL COMMENT '邀请表主键ID',
  `to_phone` varchar(11) NOT NULL COMMENT '被邀请人电话',
  `inviteTime` datetime DEFAULT NULL COMMENT '邀请时间',
  `inviteState` int(1) DEFAULT NULL COMMENT '邀请状态（0：已发送，1：已注册）',
  `userid` varchar(36) NOT NULL COMMENT '用户ID（外键）',
  `parkid` varchar(20) NOT NULL,
  PRIMARY KEY (`invId`),
  KEY `FK_InvitedRecord_User` (`userid`),
  CONSTRAINT `FK_InvitedRecord_User` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邀请记录表';

-- ----------------------------
-- Records of invitedrecord
-- ----------------------------

-- ----------------------------
-- Table structure for menuinfo
-- ----------------------------
DROP TABLE IF EXISTS `menuinfo`;
CREATE TABLE `menuinfo` (
  `menuId` varchar(36) NOT NULL COMMENT '菜单表主键ID',
  `menuName` varchar(30) NOT NULL COMMENT '菜单名字',
  `href` varchar(300) NOT NULL COMMENT '路径',
  `target` varchar(100) DEFAULT NULL COMMENT '其他',
  `menuTypeId` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menuinfo
-- ----------------------------
INSERT INTO `menuinfo` VALUES ('1', '用户管理测试1', '路径1111', 'rightFrame', '1');
INSERT INTO `menuinfo` VALUES ('2', '用户管理测试2', '路径1111', 'rightFrame', '1');
INSERT INTO `menuinfo` VALUES ('3', 'BATP管理', '路径1111', 'rightFrame', '2');
INSERT INTO `menuinfo` VALUES ('4', '享泊管理', '路径1111', 'rightFrame', '3');
INSERT INTO `menuinfo` VALUES ('5', '享泊管理2', '路径1111', 'rightFrame', '3');
INSERT INTO `menuinfo` VALUES ('6', '享泊管理6', '路径1111', 'rightFrame', '3');
INSERT INTO `menuinfo` VALUES ('7', '享泊管理7', '路径1111', 'rightFrame', '3');

-- ----------------------------
-- Table structure for menuroleinfo
-- ----------------------------
DROP TABLE IF EXISTS `menuroleinfo`;
CREATE TABLE `menuroleinfo` (
  `rmid` varchar(36) NOT NULL COMMENT '菜单管理表主键ID',
  `menuId` varchar(36) NOT NULL COMMENT '菜单外键',
  `roleId` varchar(36) NOT NULL COMMENT '角色外键',
  PRIMARY KEY (`rmid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menuroleinfo
-- ----------------------------
INSERT INTO `menuroleinfo` VALUES ('1', '1', '1');
INSERT INTO `menuroleinfo` VALUES ('2', '2', '1');
INSERT INTO `menuroleinfo` VALUES ('3', '3', '1');
INSERT INTO `menuroleinfo` VALUES ('4', '4', '1');
INSERT INTO `menuroleinfo` VALUES ('5', '5', '1');
INSERT INTO `menuroleinfo` VALUES ('6', '6', '2');
INSERT INTO `menuroleinfo` VALUES ('7', '7', '2');

-- ----------------------------
-- Table structure for menutype
-- ----------------------------
DROP TABLE IF EXISTS `menutype`;
CREATE TABLE `menutype` (
  `menuTypeId` varchar(36) NOT NULL COMMENT '菜单类型表主键ID',
  `menuTypeName` varchar(60) NOT NULL COMMENT '菜单类型名字',
  `menuTypeicon` varchar(60) NOT NULL COMMENT '菜单类型图标',
  `remark` varchar(100) DEFAULT NULL COMMENT '菜单类型说明',
  PRIMARY KEY (`menuTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menutype
-- ----------------------------
INSERT INTO `menutype` VALUES ('1', '用户管理', 'aa', '权限管理');
INSERT INTO `menutype` VALUES ('2', 'BATP管理', 'aa', 'BATP管理');
INSERT INTO `menutype` VALUES ('3', '享泊管理', 'aa', '享泊管理');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `msgid` varchar(36) NOT NULL,
  `msgType` int(1) NOT NULL COMMENT '消息类型（0：判断消息，1，文字消息）',
  `msgContent` varchar(200) NOT NULL,
  `userid` varchar(36) NOT NULL COMMENT '用户id',
  `orderid` varchar(36) DEFAULT NULL COMMENT '订单id(查看详情的订单号) 可以为空（为空也可以代表是文字消息）',
  PRIMARY KEY (`msgid`),
  KEY `FK_Message_Order` (`orderid`),
  CONSTRAINT `FK_Message_Order` FOREIGN KEY (`orderid`) REFERENCES `orderinfo` (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息记录表';

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `orderid` varchar(36) NOT NULL,
  `ordernum` varchar(18) NOT NULL COMMENT '订单号码（规则：年月日时分秒毫秒（13位）+随机数（5））',
  `parkid` varchar(36) NOT NULL COMMENT '车位ID（外键）',
  `placeorde_time` datetime NOT NULL COMMENT '下订单时间（生成时间）',
  `supplierconfirm_time` datetime DEFAULT NULL COMMENT '供方确认时间',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `payType` int(1) DEFAULT NULL COMMENT '支付类型（0“余额支付，1：支付宝，2：微信支付）',
  `order_state` int(1) NOT NULL COMMENT '订单状态（0：未支付，1：已支付，2：已取消，3：已完成，4.已拒绝',
  `vehicleid` varchar(40) NOT NULL COMMENT '车牌号',
  `money` double(10,2) NOT NULL COMMENT '订单金额',
  `from_userid` varchar(36) NOT NULL COMMENT '用户外键（需方）',
  `chargetype` int(1) DEFAULT NULL COMMENT '收费类型（0：按次收费 1：按小时收费）',
  `typemoney` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  KEY `FK_OrderInfo_Park` (`parkid`),
  KEY `FK_Order_FromUser` (`from_userid`),
  CONSTRAINT `FK_OrderInfo_Park` FOREIGN KEY (`parkid`) REFERENCES `parkingspace` (`parkid`),
  CONSTRAINT `FK_Order_FromUser` FOREIGN KEY (`from_userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
INSERT INTO `orderinfo` VALUES ('66a744cd-7f11-4f7d-8098-d46660a1173e', '14630459809077789', '111111', '2016-05-12 17:39:40', null, null, null, '2', '沪A45678', '245.52', '11111111111', '1', '10.23');
INSERT INTO `orderinfo` VALUES ('d4cafa00-3945-4a2a-ad79-c8a6f47ede66', '14629731206745988', '111111', '2016-05-11 21:25:20', null, null, null, '0', '沪A45678', '245.52', '11111111111', '1', '10.23');

-- ----------------------------
-- Table structure for ordertime
-- ----------------------------
DROP TABLE IF EXISTS `ordertime`;
CREATE TABLE `ordertime` (
  `otId` varchar(36) NOT NULL COMMENT '订单时间表主键',
  `begin_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `thisDate` date DEFAULT NULL COMMENT '当前日期',
  `orderid` varchar(36) DEFAULT NULL COMMENT '订单外键（此条数据对应的是哪个订单）',
  PRIMARY KEY (`otId`),
  KEY `FK_OrderTime_OrderInfo` (`orderid`),
  CONSTRAINT `FK_OrderTime_OrderInfo` FOREIGN KEY (`orderid`) REFERENCES `orderinfo` (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ordertime
-- ----------------------------
INSERT INTO `ordertime` VALUES ('2a1915e1-45ac-4acf-b05d-95bb581b2c5e', '09:00:00', '20:00:00', '2016-05-20', '66a744cd-7f11-4f7d-8098-d46660a1173e');
INSERT INTO `ordertime` VALUES ('70bd32e9-54fc-4e6e-a738-4a6b02d97cfb', '09:00:00', '20:00:00', '2016-05-13', 'd4cafa00-3945-4a2a-ad79-c8a6f47ede66');
INSERT INTO `ordertime` VALUES ('9663fe54-4de0-489a-9e75-b209eae25d62', '07:00:00', '20:00:00', '2016-05-19', '66a744cd-7f11-4f7d-8098-d46660a1173e');
INSERT INTO `ordertime` VALUES ('ebac4b7c-c93e-4db9-8875-a6f0dfabb316', '07:00:00', '20:00:00', '2016-05-12', 'd4cafa00-3945-4a2a-ad79-c8a6f47ede66');

-- ----------------------------
-- Table structure for parkingspace
-- ----------------------------
DROP TABLE IF EXISTS `parkingspace`;
CREATE TABLE `parkingspace` (
  `parkid` varchar(36) NOT NULL COMMENT '车位主键ID',
  `parkNo` varchar(40) NOT NULL COMMENT '车位编号',
  `park_instructions` varchar(300) DEFAULT NULL COMMENT '车位具体说明',
  `releasedate` datetime NOT NULL COMMENT '发布时间',
  `update_time` datetime DEFAULT NULL COMMENT '车位更新时间',
  `parkstate` int(1) DEFAULT NULL COMMENT '车位状态（缺省）',
  `money` double(10,2) NOT NULL COMMENT '停车费用',
  `chargetype` int(1) NOT NULL COMMENT '收费类型（0：按次收费 1：按小时收费）',
  `userid` varchar(36) DEFAULT NULL,
  `communityId` varchar(36) DEFAULT NULL COMMENT '小区外键',
  `entrance` varchar(300) DEFAULT NULL COMMENT '入口',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除（0：未删除 1：已经删除）',
  `parkType` varchar(30) DEFAULT NULL COMMENT '车位类型',
  PRIMARY KEY (`parkid`),
  KEY `FK_Parkingspace_Community` (`communityId`),
  KEY `FK_Parkingspace_User` (`userid`),
  CONSTRAINT `FK_Parkingspace_Community` FOREIGN KEY (`communityId`) REFERENCES `community` (`communityId`),
  CONSTRAINT `FK_Parkingspace_User` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车位表';

-- ----------------------------
-- Records of parkingspace
-- ----------------------------
INSERT INTO `parkingspace` VALUES ('111111', '#A12345', '按时间来！', '2016-05-11 20:13:30', '2016-05-11 20:13:34', null, '10.23', '1', '11111111111', '1', '我是入口', '0', '地面车位');

-- ----------------------------
-- Table structure for purse
-- ----------------------------
DROP TABLE IF EXISTS `purse`;
CREATE TABLE `purse` (
  `purseid` varchar(36) NOT NULL COMMENT '钱包表主键ID',
  `balance` double(10,2) NOT NULL COMMENT '余额',
  `blocked_balances` double(10,2) NOT NULL COMMENT '冻结余额',
  `userid` varchar(36) NOT NULL COMMENT '用户表外键ID',
  PRIMARY KEY (`purseid`),
  UNIQUE KEY `userid` (`userid`),
  CONSTRAINT `FK_Purse_User` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='钱包表';

-- ----------------------------
-- Records of purse
-- ----------------------------

-- ----------------------------
-- Table structure for roleinfo
-- ----------------------------
DROP TABLE IF EXISTS `roleinfo`;
CREATE TABLE `roleinfo` (
  `roleId` varchar(36) NOT NULL COMMENT '角色ID主键',
  `roleName` varchar(100) NOT NULL COMMENT '角色名字',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roleinfo
-- ----------------------------
INSERT INTO `roleinfo` VALUES ('0', '新用户');
INSERT INTO `roleinfo` VALUES ('1', '超级管理员');
INSERT INTO `roleinfo` VALUES ('2', '运营管理');
INSERT INTO `roleinfo` VALUES ('3', '市场管理');
INSERT INTO `roleinfo` VALUES ('4', '新用户');

-- ----------------------------
-- Table structure for touchbalance
-- ----------------------------
DROP TABLE IF EXISTS `touchbalance`;
CREATE TABLE `touchbalance` (
  `tbid` varchar(36) NOT NULL COMMENT '余额明细表Id',
  `money` double(10,2) NOT NULL COMMENT '金额（正数或者负数；支出或者收入）',
  `operation_time` datetime NOT NULL COMMENT '时间',
  `tradtype` int(1) DEFAULT NULL,
  `purseid` varchar(36) DEFAULT NULL COMMENT '钱包外键',
  PRIMARY KEY (`tbid`),
  KEY `FK_TouchBalance_Purse` (`purseid`),
  CONSTRAINT `FK_TouchBalance_Purse` FOREIGN KEY (`purseid`) REFERENCES `purse` (`purseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='余额明细表';

-- ----------------------------
-- Records of touchbalance
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` varchar(36) NOT NULL COMMENT '用户主键',
  `userName` varchar(20) DEFAULT NULL COMMENT '用户姓名',
  `mobile` varchar(11) NOT NULL COMMENT '手机号码',
  `password` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `sex` int(1) DEFAULT NULL COMMENT '性别(0:未填写， 1：男    2  女 )',
  `age` int(2) DEFAULT NULL COMMENT '年龄',
  `headportrait` varchar(40) DEFAULT NULL COMMENT '头像路径',
  `invitecode` varchar(10) DEFAULT NULL COMMENT '邀请码',
  `regid` varchar(30) DEFAULT NULL COMMENT '消息推送，Id点对点',
  `token` varchar(20) DEFAULT NULL COMMENT '安全验证，为了防止多方登录',
  `is_guard` int(11) NOT NULL COMMENT '是否为保安（0：不是，1：是保安）',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号码（保安）',
  `date_created` datetime DEFAULT NULL COMMENT '注册时间，创建时间',
  `date_updated` datetime NOT NULL COMMENT '最后更新时间（手机号）',
  `smscode` varchar(6) NOT NULL COMMENT '短信验证码',
  `terminal` varchar(300) DEFAULT NULL COMMENT '设备id（拼接）',
  `bank_open_name` varchar(40) DEFAULT NULL COMMENT '银行卡开户姓名',
  `bank_open_no` varchar(18) DEFAULT NULL COMMENT '银行卡卡号',
  `bank_type` varchar(20) DEFAULT NULL COMMENT '银行名字：（比如 招商银行）',
  `payname` varchar(30) DEFAULT NULL COMMENT '支付宝姓名',
  `payno` varchar(30) DEFAULT NULL COMMENT '支付宝号码',
  `communityName` varchar(50) DEFAULT NULL COMMENT '停车场/小区名字',
  `Community_address` varchar(100) DEFAULT NULL COMMENT '小区地址（认证保安/代理）',
  `entrance` varchar(100) DEFAULT NULL COMMENT '小区入口（认证保安）',
  PRIMARY KEY (`userid`,`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('11111111111', 'niewei', '18272163455', '123456', null, null, null, null, null, null, '0', null, null, '2016-04-28 00:00:00', '2833', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userId` varchar(36) NOT NULL COMMENT '用户ID；用户表主键',
  `userName` varchar(20) NOT NULL COMMENT '用户姓名',
  `userAccount` varchar(20) NOT NULL COMMENT '用户账号',
  `userPwd` varchar(100) NOT NULL COMMENT '用户密码',
  `userStatus` int(11) NOT NULL COMMENT '用户状态：0 审核中  1 成功  2失败',
  `userTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `userRemark` varchar(500) NOT NULL COMMENT '用户备注',
  `roleId` varchar(36) NOT NULL COMMENT '用户外键/角色',
  PRIMARY KEY (`userId`),
  KEY `fk_UserInfo_roleId` (`roleId`),
  CONSTRAINT `fk_UserInfo_roleId` FOREIGN KEY (`roleId`) REFERENCES `roleinfo` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('25ae948c-1bc9-4cfa-b32f-9fb543b14340', '聂伟', 'niewei', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2016-05-13 17:58:26', '我是牛逼人物！我是风云人物~', '1');
INSERT INTO `userinfo` VALUES ('28c5c8a1-4109-4fa4-aae1-aa2d5c9e5686', '聂伟', 'nieiwei2', 'E10ADC3949BA59ABBE56E057F20F883E', '0', '2016-05-13 17:59:48', '我是牛逼人物！我是风云人物~', '0');
INSERT INTO `userinfo` VALUES ('84103a56-c17d-4070-aba4-c22c88699f38', '小伟哥', 'niewei2', 'E10ADC3949BA59ABBE56E057F20F883E', '0', '2016-05-13 18:08:31', '我是聂伟！！', '0');

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `vehicleid` varchar(36) NOT NULL COMMENT '车辆主键id',
  `vehicleno` varchar(10) NOT NULL COMMENT '车牌号',
  `userid` varchar(36) NOT NULL,
  PRIMARY KEY (`vehicleid`),
  KEY `FK_Vehice_User` (`userid`),
  CONSTRAINT `FK_Vehice_User` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆表';

-- ----------------------------
-- Records of vehicle
-- ----------------------------

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `versionid` varchar(36) NOT NULL COMMENT '主键ID',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `url` varchar(70) DEFAULT NULL COMMENT '路劲',
  `os_version` varchar(30) DEFAULT NULL COMMENT '操作系统版本号',
  PRIMARY KEY (`versionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版本信息';

-- ----------------------------
-- Records of version
-- ----------------------------

-- ----------------------------
-- Table structure for weekrules
-- ----------------------------
DROP TABLE IF EXISTS `weekrules`;
CREATE TABLE `weekrules` (
  `weekid` varchar(36) NOT NULL,
  `monday_begin` time NOT NULL COMMENT '星期一（开始）',
  `monday_end` time NOT NULL COMMENT '星期一（结束）',
  `tuesday_begin` time NOT NULL COMMENT '星期二（开始）',
  `tuesday_end` time NOT NULL COMMENT '星期二（结束）',
  `wednesday_begin` time NOT NULL COMMENT '星期三（开始）',
  `wednesday_end` time NOT NULL COMMENT '星期三（结束）',
  `thursday_begin` time NOT NULL COMMENT '星期四（开始）',
  `thursday_end` time NOT NULL COMMENT '星期四（结束）',
  `friday_begin` time NOT NULL COMMENT '星期五（开始）',
  `friday_end` time NOT NULL COMMENT '星期五（结束）',
  `saturday_begin` time NOT NULL COMMENT '星期六（开始）',
  `saturday_end` time NOT NULL COMMENT '星期六（结束）',
  `sunday_begin` time NOT NULL COMMENT '星期日（开始）',
  `sunday_end` time NOT NULL COMMENT '星期日（结束）',
  `update_time` datetime NOT NULL,
  `parkid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`weekid`),
  KEY `FK_Weekrules_Parkingspace` (`parkid`),
  CONSTRAINT `FK_Weekrules_Parkingspace` FOREIGN KEY (`parkid`) REFERENCES `parkingspace` (`parkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='星期规则表';

-- ----------------------------
-- Records of weekrules
-- ----------------------------
INSERT INTO `weekrules` VALUES ('1211111', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '07:00:00', '20:00:00', '07:00:00', '20:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '2016-05-11 20:19:14', '111111');

-- ----------------------------
-- Table structure for withdrawalrecord
-- ----------------------------
DROP TABLE IF EXISTS `withdrawalrecord`;
CREATE TABLE `withdrawalrecord` (
  `wrid` varchar(36) NOT NULL COMMENT '提现ID',
  `withdrawal_money` double(10,2) DEFAULT NULL COMMENT '提现金额',
  `withdrawal_date` datetime DEFAULT NULL COMMENT '提现时间',
  `userid` varchar(36) NOT NULL,
  `withdrawal_state` int(1) DEFAULT NULL COMMENT '提现状态（0：申请中，1：已提现 2:其他）',
  `successfultime` datetime DEFAULT NULL COMMENT '提现审核时间。',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除（0：未删除  1：已删除）',
  `payName` varchar(30) DEFAULT NULL,
  `payNo` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`wrid`),
  KEY `FK_WithdrawalRecord_User` (`userid`),
  CONSTRAINT `FK_WithdrawalRecord_User` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提现记录表';

-- ----------------------------
-- Records of withdrawalrecord
-- ----------------------------

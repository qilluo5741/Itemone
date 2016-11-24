/*
Navicat MySQL Data Transfer

Source Server         : Linux服务器
Source Server Version : 50173
Source Host           : 218.83.192.100:3306
Source Database       : sharebo

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-05-26 17:26:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for advertising
-- ----------------------------
DROP TABLE IF EXISTS `advertising`;
CREATE TABLE `advertising` (
  `advid` varbinary(36) NOT NULL COMMENT '广告主键',
  `adname` varchar(30) DEFAULT NULL,
  `adv_img_url` varchar(240) NOT NULL COMMENT '广告图片路径',
  `adv_href` varchar(240) DEFAULT NULL COMMENT '路径跳转',
  PRIMARY KEY (`advid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='广告表Advertising';

-- ----------------------------
-- Records of advertising
-- ----------------------------
INSERT INTO `advertising` VALUES (0x3234353334303837373436313934313937, null, 'http://xtc.oss-cn-hangzhou.aliyuncs.com/dev/image/4bc8de3e-bbe0-4250-9245-2fe2d0585114', null);
INSERT INTO `advertising` VALUES (0x3234353334303837373436313934313938, null, 'http://xtc.oss-cn-hangzhou.aliyuncs.com/dev/image/93044998-4fc1-4a4e-bd31-ac0eb9918f3d', null);
INSERT INTO `advertising` VALUES (0x3234353334303837373436313934313939, null, 'http://xtc.oss-cn-hangzhou.aliyuncs.com/dev/image/e1885dc0-920d-40f6-8a95-6dfe186cae3d', null);
INSERT INTO `advertising` VALUES (0x3234353334303837373436313934323030, null, 'http://xtc.oss-cn-hangzhou.aliyuncs.com/dev/image/5e515b9d-6bcb-4b3e-85a1-ad2b7ceb39e1', null);

-- ----------------------------
-- Table structure for certification
-- ----------------------------
DROP TABLE IF EXISTS `certification`;
CREATE TABLE `certification` (
  `certificationId` varchar(36) NOT NULL COMMENT '车辆认证表主键',
  `userid` varchar(36) NOT NULL COMMENT '用户ID(外键)',
  `vehicleid` varchar(36) NOT NULL COMMENT '车辆ID(外键)',
  `driver_license_positive` varchar(240) DEFAULT NULL COMMENT '驾驶证（正面）',
  `driver_license_reverse` varchar(240) DEFAULT NULL COMMENT '驾驶证（反面）',
  `vehicle_license_positive` varchar(240) DEFAULT NULL COMMENT '行驶证（正面）',
  `vehicle_license_reverse` varchar(240) DEFAULT NULL COMMENT '行驶证（背面）',
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
INSERT INTO `community` VALUES ('1', '滨江国际广场', '江浦路1058弄小区', null, '2016-05-11 20:12:15', '0');
INSERT INTO `community` VALUES ('24534087746194206', '月浦一村', '月浦一村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194207', '月浦十村', '月浦十村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194208', '乐业小区', '德都路165弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194209', '祥腾菁英公馆', '长江南路955弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194210', '集贤路501弄', '金星绿苑', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194211', '集贤路500弄', '金星绿苑', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194212', '金星绿园二期', '东至抚远路、南至练祁河、西至集贤路、北至祁北路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194213', '宝纳文化源', '祁连山路南大路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194214', '祥腾生活广场', '淞发路901弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194215', '四元路360弄', '四元路360弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194216', '绿地公园壹品', '顾村沙浦路（沙浦路205号）', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194217', '经纬城市绿洲五期学府涵青（经纬城市绿洲二期学府涵青）', '纬地路99弄（涵青路398弄1～79号）', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194218', '华馨苑', '蕴川路1438弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194219', '经纬祥泰苑', '沪太路3888号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194220', '万科四季花城一期', '江杨北路1185号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194221', '美岸栖庭一二三期', '（高逸路201弄 国权北路828弄 何家湾路111弄）', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194222', '成亿宝盛', '水产西路858、859弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194223', '长江路406弄410弄', '长江路406弄410弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194224', '馨佳园十街坊邵山路', '邵山路 348 弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194225', '古北菊翔苑', '菊联路232弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194226', '保利叶上海二、三期', '菊联路419弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194227', '保利叶语', '宝菊路1号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194228', '宝通家园一期', '潘新路118弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194229', '宝通家园二期', '潘新路118弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194230', '宝通家园三期', '潘新路118弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194231', '四季绿城A', '新二路999弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194232', '四季绿城B', '新二路999弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194233', '四季绿城C', '新二路999弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194234', '圣卡洛.铂庭小区', '宝安公路1188号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194235', '世纪长江苑', '电台路568弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194236', '保利叶上海', '菊联路889弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194237', '顾村馨佳苑1区', '韶山路419弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194238', '成事高邸', '上大路178弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194239', '九英里城', '长临路1318弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194240', '四季绿城一期', '新二路999号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194241', '三花现代城丹桂苑', '上海市宝山区云西路168号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194242', '三花现代城香梅苑', '上海市宝山区云西路219号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194243', '杰圣家苑', '殷高西路331弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194244', '丽景翠亭', '盘古路2618弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194245', '禄德佳苑', '竹韵路258弄、259弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194246', '新月时代家园', '德都路28号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194247', '嘉诚国际', '德都路50弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194248', '海淞苑', '淞宝路250弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194249', '万临家园', '淞发路500号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194250', '新城尚景', '淞肇路333弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194251', '宝莲湖景苑', '绥化路260弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194252', '新月明月园', '鹤林路58弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194253', '新月明星园', '塔源路58弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194254', '新月翡翠苑', '德都路35弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194255', '同盛嘉园', '月城路299弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194256', '宝启公寓', '蕴川路1625弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194257', '宝启花园', '宝杨路3288弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194258', '福地苑-海尚名城一期', '友谊路1869号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194259', '福地苑-海尚名城二期', '友谊路1869号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194260', '新月桃园', '塔源路158弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194261', '宝山七村南块', '海江路宝东路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194262', '友谊家园二期', '铁峰路2001弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194263', '共和新苑', '共和新路5308弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194264', '西城区老年活动中心', '盘古路1911弄（江杨北路）', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194265', '友谊家园一期', '铁峰路2000弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194266', '朗诗绿岛', '罗芬路1199弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194267', '沪太路纬地路口', '沪太路纬地路口', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194268', '聚丰景都AG1', '聚丰园路628弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194269', '上大聚丰园AG1', '上大路1288弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194270', '经纬三期', '纬地路358弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194271', '经纬一期', '纬地路88弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194272', '经纬二期', '涵青路398弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194273', '共康五村', '共康路138弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194274', '共康七村', '长江西路2320号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194275', '共康八村', '长临路880弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194276', '共康六村', '三泉路1600弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194277', '海尚明城', '友谊路1869弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194278', '宝林二村', '宝杨路13号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194279', '宝林一村', '宝杨路853号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194280', '宝林六村', '宝杨路宝东路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194281', '宝虹水岸景苑', '梅林路865弄866弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194282', '杨泰一村', '杨泰路328弄338弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194283', '杨泰二村', '杨鑫路靠近杨泰路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194284', '杨泰春城', '镇泰路338弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194285', '宝杨路3184弄小区', '宝杨路3184弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194286', '淞南新村', '长江南路156号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194287', '淞南一村', '通南路3号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194288', '淞南二村', '淞良路201弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194289', '淞南三村', '淞南路301号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194290', '淞南四村', '淞南路4弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194291', '保德路1238弄', '保德路1238弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194292', '高境二村', '殷高西路58号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194293', '高境一村', '殷高西路101弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194294', '昌鑫时代绿园', '虎林路99弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194295', '三门路489弄', '三门路489弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194296', '吉浦路615弄', '吉浦路615弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194297', '海尚菊园', '陆翔路698弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194298', '万里城四街坊', '新村路1881弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194299', '呼玛二村', '通河路呼玛路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194300', '泗塘五村', '虎林路共江路东北', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194301', '宝莲湖景园', '绥化路260弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194302', '共和二村', '阳曲路共康东路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194303', '共和三村', '岭南路1050弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194304', '文华苑', '华灵路1180弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194305', '和欣国际', '场北路39弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194306', '中环国际一期', '场北路551弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194307', '宝宸共和家园', '三泉路1858弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194308', '宝宸怡景园', '三泉路1859弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194309', '华能城市花园', '牡丹江路1298弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194310', '宝林七村', '友谊路43号/东林路84号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194311', '宝山八村', '永清路699弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194312', '宝林八村', '密山东路东林路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194313', '飘鹰锦和东区', '红林路58弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194314', '飘鹰锦和西区', '红林路158弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194315', '飘鹰锦和中区', '依安路39弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194316', '杨泰公寓', '梅林路666弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194317', '葑润华庭', '祁华路58弄/218弄/219弄/华秋路187~189弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194318', '祥泰苑', '沪太路3611弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194319', '和泰苑', '沪太路4099弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194320', '学林苑', '聚丰园路188弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194321', '宝景苑EG1', '通南路55弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194322', '新逸仙公寓EG1', '逸仙路3458弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194323', '逸居苑', '淞南路111弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194324', '长宏新苑', '淞南路211弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194325', '淞南十村', '新二路1398弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194326', '馨良苑', '新二路183弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194327', '盛达家园', '一二八纪念路55弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194328', '逸兴家园', '逸仙路3456弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194329', '逸兴公寓', '逸仙路3508弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194330', '佳翔苑', '陆翔路3489弄（育才路陶浜路）', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194331', '宝欣苑', '兴农路联杨路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194332', '美罗家园C4地块美丰苑', '罗和路855弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194333', '美罗家园C2地块美丰苑', '罗和路935弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194334', '美罗家园润苑', '罗和路陶浜路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194335', '美罗家园吉翔苑', '年吉路358弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194336', '罗宁雅苑', '罗宁路2999弄（罗宁路潘新路）', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194337', '海江二村', '双城路淞宝路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194338', '海江新村', '塘后路1-23号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194339', '永清新村', '双城路永清路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194340', '永清二村', '永清路水产路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194341', '海滨新村', '同泰北路112号（同济路同济支路）', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194342', '大华二村', '华灵路510弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194343', '大华三村', '大华路华灵路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194344', '梧桐城邦一期', '真华路1800弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194345', '梧桐城邦二期', '真华路1801弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194346', '康泰新城', '真金璐1039弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194347', '何家湾路111弄', '何家湾路111弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194348', '罗宁路3030弄', '罗宁路3030弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194349', '真华路926弄', '真华路926弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194350', '水产西路777弄', '水产西路777弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194351', '菊泉街39弄', '菊泉街39弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194352', '宝菊路133弄', '宝菊路133弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194353', '丹霞山路257弄', '丹霞山路257弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194354', '盛桥一村', '盛桥一村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194355', '盛桥二村', '盛桥二村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194356', '盛桥三村', '盛桥三村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194357', '盛桥四村', '盛桥四村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194358', '陆翔路698弄', '陆翔路698弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194359', '陆翔路678弄', '陆翔路678弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194360', '陆翔路358弄', '陆翔路358弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194361', '菊联路233弄', '菊联路233弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194362', '菊联路232弄', '菊联路232弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194363', '菊联路68弄', '菊联路68弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194364', '水产西路489弄', '水产西路489弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194365', '水产西路729弄', '水产西路729弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194366', '教育路555弄', '教育路555弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194367', '电台路599弄', '电台路599弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194368', '顾陈路231弄', '顾陈路231弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194369', '沙浦路128弄', '沙浦路128弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194370', '宝绿路99弄', '宝绿路99弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194371', '富联路128弄', '富联路128弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194372', '顾北路689弄', '顾北路689弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194373', '水产西路858弄', '水产西路858弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194374', '水产西路859弄', '水产西路859弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194375', '沪太路3717弄', '沪太路3717弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194376', '泗塘六村', '泗塘六村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194377', '泗塘三村', '泗塘三村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194378', '泗塘一村', '泗塘一村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194379', '泰和西路3527弄', '泰和西路3527弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194380', '泗塘八村', '泗塘八村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194381', '泗塘二村', '泗塘二村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194382', '泗塘七村', '泗塘七村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194383', '华灵路411弄', '华灵路411弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194384', '联牧路1弄44支弄', '联牧路1弄44支弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194385', '顾荻路161弄', '顾荻路161弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194386', '宝山九村', '宝山九村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194387', '宝山十村', '宝山十村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194388', '宝城三村', '宝城三村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194389', '宝林四村', '宝林四村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194390', '宝林三村', '宝林三村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194391', '宝林五村', '宝林五村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194392', '宝钢八村', '宝钢八村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194393', '宏宝公寓', '宝杨路3410弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194394', '柏丽华庭', '蕴川路1623弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194395', '世华佳苑', '杨鑫路451弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194396', '杨泰阳光苑', '杨泰路268弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194397', '杨泰苑', '杨桃路185弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194398', '影园路小区', '杨泰路杨桃路东北侧', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194399', '杨泰康苑', '松兰路328弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194400', '环镇北路300弄', '环镇北路300弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194401', '环镇北路323弄', '环镇北路323弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194402', '环镇北路400弄', '环镇北路400弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194403', '环镇北路417弄', '环镇北路417弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194404', '环镇北路500弄', '环镇北路500弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194405', '环镇北路509弄', '环镇北路509弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194406', '环镇北路600弄', '环镇北路600弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194407', '环镇北路655弄', '环镇北路655弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194408', '环镇北路699弄', '环镇北路699弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194409', '环镇北路700弄', '环镇北路700弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194410', '共富三村', '共富路348号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194411', '共富四村', '共富路351弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194412', '鑫鑫花园', '联谊路501弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194413', '富隆苑', '共富路476弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194414', '富都苑', '联泰路158弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194415', '富弘苑', '共富路518弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194416', '艺康苑', '共康路88弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194417', '馨康苑', '共康路169弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194418', '通河一村', '通河一村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194419', '通河二村', '通河二村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194420', '通河三村', '通河三村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194421', '通河四村', '通河四村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194422', '通河六村', '通河六村', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194423', '长江路20弄', '长江路20弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194424', '长江路28弄', '长江路28弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194425', '长江路68弄', '长江路68弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194426', '祁连欣苑', '聚丰园路500弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194427', '锦龙苑', '聚丰园路388弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194428', '祁连家园', '祁连山路2555弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194429', '祁连一村', '祁连山路2500弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194430', '富华苑', '联泰路518弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194431', '共康前进公寓', '三泉路1015弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194432', '共康二村', '共康路400弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194433', '民主新苑', '共和新路4719弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194434', '宝山六村', '永乐路115弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194435', '密山二村', '牡丹江路1258号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194436', '宝钢一村', '友谊路114号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194437', '长江路780弄', '长江路780弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194438', '长江路88弄', '长江路88弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194439', '华浜新村', '长江路军工路西南侧', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194440', '华浜二村', '张发路张华路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194441', '康德公寓', '共和新路4828弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194442', '民主花园', '共和新路4703弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194443', '民主墩发', '共和新路4739弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194444', '民主紫竹苑', '共和新路4699弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194445', '共康雅苑一期', '花园宅路52弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194446', '共康雅苑A', '花园宅路190弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194447', '共富一村', '共富路183弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194448', '宝钢六村', '友谊路牡丹江路交叉口', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194449', '宝钢三村', '宝山区友谊路密山路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194450', '宝钢二村', '友谊路密山路', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194451', '宝钢十一村', '宝山区团结路173号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194452', '宝山三村', '永乐路422号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194453', '长江路440弄', '长江路441弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194454', '长江路860弄', '长江路861弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194455', '淞南路459弄', '淞南路460弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194456', '淞南路549弄', '淞南路550弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194457', '新二路183弄', '新二路184弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194458', '长江路410弄', '长江路411弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194459', '淞发路25弄', '淞发路26弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194460', '洛场路103号', '洛场路103号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194461', '洛场路105号', '洛场路105号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194462', '洛场路107号', '洛场路107号', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194463', '洛场路250弄', '洛场路250弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194464', '洛场路300弄', '洛场路300弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194465', '南陈路278弄', '南陈路278弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194466', '南陈路279弄', '南陈路279弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194467', '南大路106弄', '南大路106弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194468', '南大路10弄', '南大路10弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194469', '南大路118弄', '南大路118弄', '宝山', '2016-04-26 12:51:20', '1');
INSERT INTO `community` VALUES ('24534087746194470', '南大路126弄', '南大路126弄', '宝山', '2016-04-26 12:51:20', '1');

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
  `msgContent` varchar(200) DEFAULT NULL,
  `userid` varchar(36) NOT NULL COMMENT '用户id',
  `orderid` varchar(36) DEFAULT NULL COMMENT '订单id(查看详情的订单号) 可以为空（为空也可以代表是文字消息）',
  `messageTime` datetime NOT NULL,
  PRIMARY KEY (`msgid`),
  KEY `FK_Message_Order` (`orderid`),
  CONSTRAINT `FK_Message_Order` FOREIGN KEY (`orderid`) REFERENCES `orderinfo` (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息记录表';

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('05d1adc110e941c5a55c9c63c9a2bbcd', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:55');
INSERT INTO `message` VALUES ('16d8f80e5e8542dfba84fac06eeb5596', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:52');
INSERT INTO `message` VALUES ('183cbeb98dd04a2d841d5f329c2f39e7', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:47');
INSERT INTO `message` VALUES ('186ffd059e8c44e4b85ca50a52fe1511', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:53');
INSERT INTO `message` VALUES ('1a005eae27154dbdb1b0a05c46e35c09', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:20:51');
INSERT INTO `message` VALUES ('24534087746194203', '0', null, '24535443009699849', '24534087746194188', '2016-05-25 11:18:11');
INSERT INTO `message` VALUES ('24534087746194204', '0', null, '24535443009699849', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 11:18:11');
INSERT INTO `message` VALUES ('24534087746194205', '0', null, '24535443009699849', 'd4cafa00-3945-4a2a-ad79-c8a6f47ede66', '2016-05-25 11:18:11');
INSERT INTO `message` VALUES ('24534087746194471', '0', '测试数据', '24535443009699849', null, '2016-05-25 11:18:11');
INSERT INTO `message` VALUES ('24534087746194472', '1', '测试数据', '24535443009699849', null, '2016-05-25 11:18:11');
INSERT INTO `message` VALUES ('24534087746194473', '1', '测试数据', '24535443009699849', null, '2016-05-25 11:18:11');
INSERT INTO `message` VALUES ('24534087746194474', '1', '测试数据', '24535443009699849', null, '2016-05-25 11:18:11');
INSERT INTO `message` VALUES ('24534087746194475', '1', '测试数据', '24535443009699849', null, '2016-05-25 11:18:11');
INSERT INTO `message` VALUES ('24534087746194476', '1', '测试数据', '24535443009699849', null, '2016-05-25 11:18:11');
INSERT INTO `message` VALUES ('26951daf623b4aa9a8f6bb4ca85fa62a', '1', '测试数据', '11111111111', null, '2016-05-25 16:36:18');
INSERT INTO `message` VALUES ('33ce998496c04827902cd977126b2a97', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:55');
INSERT INTO `message` VALUES ('3f4214f08b6f40139a8b8b476962fbf3', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:44');
INSERT INTO `message` VALUES ('4eb43107831142c79f6dc16d009a9c66', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:09:46');
INSERT INTO `message` VALUES ('5629ee9a31504a999744f2446e48a5a5', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:49');
INSERT INTO `message` VALUES ('582d06dabe624962bf09148644666f0b', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:55');
INSERT INTO `message` VALUES ('6228257cb067487d9d60595c06bfbc7c', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:36:18');
INSERT INTO `message` VALUES ('6b309daca94144c49ccf08ea92afdfb7', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:53');
INSERT INTO `message` VALUES ('6ec171b527e840f2af6ac932d62ef279', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:50');
INSERT INTO `message` VALUES ('7195e41c53ca4a74957db903e5b734e6', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:54');
INSERT INTO `message` VALUES ('7be2a3ee206e466bab8cea6ab568b0cc', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:54');
INSERT INTO `message` VALUES ('7fb595927b4e4502bc06d994c215677f', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:54');
INSERT INTO `message` VALUES ('83500cd7800a4ea1b456cf61a1935b4e', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:19:53');
INSERT INTO `message` VALUES ('95f21fd6fb854275a31fb470f6631505', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:55');
INSERT INTO `message` VALUES ('9ad0c8914b0d4508ba5bd2150b5f9280', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:51');
INSERT INTO `message` VALUES ('9d36e1d492f0485896d24ab23cc05558', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:54');
INSERT INTO `message` VALUES ('a81dc097b0f34e8a949a916abfd9336c', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:49');
INSERT INTO `message` VALUES ('a96638d2bf52491aac8f76208228f056', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:48');
INSERT INTO `message` VALUES ('a992e2d1f05c4cc0854a98b4ac94d5d5', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:19');
INSERT INTO `message` VALUES ('b6b1fa79b24647ea95745334b56fb773', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:53');
INSERT INTO `message` VALUES ('b798c73f5e464086baabfddddd331547', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:55');
INSERT INTO `message` VALUES ('b8cfaec2da284d52b415d124d1f2a183', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:52');
INSERT INTO `message` VALUES ('c624bae378094e4f86faf6db7640bbac', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:52');
INSERT INTO `message` VALUES ('d32af99607d7497fbebaf34af1d5fb29', '1', null, '11111111111', '66a744cd-7f11-4f7d-8098-d46660a1173e', '2016-05-25 16:37:46');

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
  `order_state` int(1) NOT NULL COMMENT '订单状态（0：未支付，1：已支付，2：已取消，3：已接受，4.已拒绝',
  `vehicleid` varchar(40) NOT NULL COMMENT '车牌号',
  `money` double(10,2) NOT NULL COMMENT '订单金额',
  `from_userid` varchar(36) NOT NULL COMMENT '用户外键（需方）',
  `chargetype` int(1) DEFAULT NULL COMMENT '收费类型（0：按次收费 1：按小时收费）',
  `typemoney` double(10,2) DEFAULT NULL COMMENT '单价（停车位的单价）',
  PRIMARY KEY (`orderid`),
  UNIQUE KEY `UN_Order_Num` (`ordernum`),
  KEY `FK_OrderInfo_Park` (`parkid`),
  KEY `FK_Order_FromUser` (`from_userid`),
  CONSTRAINT `FK_OrderInfo_Park` FOREIGN KEY (`parkid`) REFERENCES `parkingspace` (`parkid`),
  CONSTRAINT `FK_Order_FromUser` FOREIGN KEY (`from_userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
INSERT INTO `orderinfo` VALUES ('11111222233333', '111122', '2', '2016-05-26 14:38:05', '2016-05-04 14:38:11', null, null, '2', '54434', '0.00', '11111111111', '1', '1.00');
INSERT INTO `orderinfo` VALUES ('24534087746194188', '14615255854158141', '24534087746194199', '2016-05-25 10:51:48', '2016-05-25 10:51:57', null, null, '1', '15521', '10.00', '24535443009699849', '1', '1.00');
INSERT INTO `orderinfo` VALUES ('66a744cd-7f11-4f7d-8098-d46660a1173e', '14630459809077789', '2', '2016-05-12 17:39:40', null, '2016-05-25 13:57:37', '0', '4', 'NB756215', '20.11', '11111111111', '1', '10.23');
INSERT INTO `orderinfo` VALUES ('d4cafa00-3945-4a2a-ad79-c8a6f47ede66', '14629731206745988', '4', '2016-05-11 21:25:20', null, null, null, '1', '646554', '245.52', '11111111111', '1', '10.23');

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
INSERT INTO `ordertime` VALUES ('2a1915e1-45ac-4acf-b05d-95bb581b2c5e', '09:00:00', '20:00:00', '2016-05-20', '11111222233333');
INSERT INTO `ordertime` VALUES ('70bd32e9-54fc-4e6e-a738-4a6b02d97cfb', '09:00:00', '20:00:00', '2016-05-13', 'd4cafa00-3945-4a2a-ad79-c8a6f47ede66');
INSERT INTO `ordertime` VALUES ('9663fe54-4de0-489a-9e75-b209eae25d62', '07:00:00', '20:00:00', '2016-05-19', '66a744cd-7f11-4f7d-8098-d46660a1173e');
INSERT INTO `ordertime` VALUES ('aaaaaaaaaaaaaa', '09:32:23', '14:32:20', '2016-05-26', '24534087746194188');
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
INSERT INTO `parkingspace` VALUES ('2', '2', null, '0000-00-00 00:00:00', '2016-04-27 12:12:08', null, '0.00', '0', '11111111111', '1', null, null, null);
INSERT INTO `parkingspace` VALUES ('24534087746194199', 'JH74110', null, '2016-05-25 10:50:12', '2016-05-25 10:50:15', null, '1.00', '0', '24535443009699849', '1', '1', '0', '地面车位');
INSERT INTO `parkingspace` VALUES ('3', '', null, '0000-00-00 00:00:00', '2016-05-24 12:12:11', null, '0.00', '0', '11111111111', '1', null, null, null);
INSERT INTO `parkingspace` VALUES ('4', '', null, '0000-00-00 00:00:00', '2016-05-20 12:12:14', null, '0.00', '0', '547b0c88-760e-41b1-bff2-94c81b72dc3a', '1', null, null, null);

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
INSERT INTO `purse` VALUES ('087b4b0a-5d4f-4b2d-9a60-50914a8c5070', '10.00', '0.00', '547b0c88-760e-41b1-bff2-94c81b72dc3a');
INSERT INTO `purse` VALUES ('1', '1056.68', '1.00', '11111111111');
INSERT INTO `purse` VALUES ('2', '2.00', '2.00', '33408e78-ce82-4427-8925-59fda4b3796e');
INSERT INTO `purse` VALUES ('24534087746194188', '1000.00', '10.00', '24535443009699849');

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
  `tradtype` int(1) DEFAULT NULL COMMENT '0支付宝1微信2余额3退款',
  `purseid` varchar(36) DEFAULT NULL COMMENT '钱包外键',
  PRIMARY KEY (`tbid`),
  KEY `FK_TouchBalance_Purse` (`purseid`),
  CONSTRAINT `FK_TouchBalance_Purse` FOREIGN KEY (`purseid`) REFERENCES `purse` (`purseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='余额明细表';

-- ----------------------------
-- Records of touchbalance
-- ----------------------------
INSERT INTO `touchbalance` VALUES ('01121d931ac6446f9530c087dc009c6b', '20.11', '2016-05-25 16:19:53', '3', '1');
INSERT INTO `touchbalance` VALUES ('07f6e594cf9d46978efc9d3eb03a844b', '20.11', '2016-05-25 16:37:53', '3', '1');
INSERT INTO `touchbalance` VALUES ('0846dd6f96524d07aaa55bb27f7008d0', '20.11', '2016-05-25 16:37:54', '3', '1');
INSERT INTO `touchbalance` VALUES ('17ec5425d41a41cfbc76b544a50229e6', '20.11', '2016-05-25 16:37:53', '3', '1');
INSERT INTO `touchbalance` VALUES ('201d54e5b9c34397adeac8d8c0eae8ad', '20.11', '2016-05-25 16:37:53', '3', '1');
INSERT INTO `touchbalance` VALUES ('32fe13a3203e414ea17daf8ecff7c0ac', '20.11', '2016-05-25 16:37:49', '3', '1');
INSERT INTO `touchbalance` VALUES ('34165930ce6d4ff0b03ec0cf68024b9c', '20.11', '2016-05-25 16:37:52', '3', '1');
INSERT INTO `touchbalance` VALUES ('3ed7a66e0d8643229723ee0c0c197e9f', '20.11', '2016-05-25 16:37:55', '3', '1');
INSERT INTO `touchbalance` VALUES ('65340b2d3ef1487eadff4beab0121523', '20.11', '2016-05-25 16:37:46', '3', '1');
INSERT INTO `touchbalance` VALUES ('6910654b68074fc08c47e5ed34628a3b', '20.11', '2016-05-25 13:57:37', '0', '1');
INSERT INTO `touchbalance` VALUES ('7235d84bba714ee4b2e4829784b48e7d', '20.11', '2016-05-25 16:37:51', '3', '1');
INSERT INTO `touchbalance` VALUES ('7456c9e2d2bc48ddaf89dc4a3024565e', '20.11', '2016-05-25 16:37:25', '3', '1');
INSERT INTO `touchbalance` VALUES ('81edf091711340bcb757cfcd666f6cc3', '20.11', '2016-05-25 16:37:55', '3', '1');
INSERT INTO `touchbalance` VALUES ('89911cc475f2484a8f7057493e18c79e', '20.11', '2016-05-25 16:37:55', '3', '1');
INSERT INTO `touchbalance` VALUES ('8b01f050cedf47feaa31799be29d6939', '20.11', '2016-05-25 16:37:48', '3', '1');
INSERT INTO `touchbalance` VALUES ('8b242296676747f89f75af95be023a59', '20.11', '2016-05-25 16:37:54', '3', '1');
INSERT INTO `touchbalance` VALUES ('92aea0b2d20a449f8132afb3eb7937cf', '20.11', '2016-05-25 16:36:18', '3', '1');
INSERT INTO `touchbalance` VALUES ('98c9b6a07dba45f1be1cda77d7b36d77', '20.11', '2016-05-25 16:37:55', '3', '1');
INSERT INTO `touchbalance` VALUES ('a29064c6b1274be48dd643bd0dce405f', '20.11', '2016-05-25 16:36:18', '3', '1');
INSERT INTO `touchbalance` VALUES ('a61b173eb07b4354b722e52745511886', '20.11', '2016-05-25 16:37:52', '3', '1');
INSERT INTO `touchbalance` VALUES ('aa5a483769b14e9ab52be6f03ae219f5', '20.11', '2016-05-25 16:37:49', '3', '1');
INSERT INTO `touchbalance` VALUES ('b5973dd1e6d0481a921da95305b61624', '20.11', '2016-05-25 16:37:54', '3', '1');
INSERT INTO `touchbalance` VALUES ('bb86f0590f4a4b7fb8b7fe810b3e5586', '20.11', '2016-05-25 16:20:51', '3', '1');
INSERT INTO `touchbalance` VALUES ('c4def74b58a54777812d0a77eacaa950', '20.11', '2016-05-25 16:37:47', '3', '1');
INSERT INTO `touchbalance` VALUES ('cb659699fa4c411fa03be37a8bc9106a', '20.11', '2016-05-25 16:37:52', '3', '1');
INSERT INTO `touchbalance` VALUES ('ea648bb8c5bf4848a7524f16cf5b60bf', '20.11', '2016-05-25 16:37:54', '3', '1');
INSERT INTO `touchbalance` VALUES ('ebb18e009ba3420e9298b3eefb5376a4', '20.11', '2016-05-25 16:37:50', '3', '1');
INSERT INTO `touchbalance` VALUES ('ee7ae6842f0c4c0c9d49ca8f8201f779', '20.11', '2016-05-25 16:37:55', '3', '1');
INSERT INTO `touchbalance` VALUES ('fee357bcf31f4f3597a9f6eb1ea887ee', '20.11', '2016-05-25 16:37:44', '3', '1');

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
  `headportrait` varchar(240) DEFAULT NULL COMMENT '头像路径',
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
INSERT INTO `user` VALUES ('11111111111', 'niewei', '18272163455', '123456', null, null, null, null, null, null, '0', null, '2016-04-28 00:00:00', '2016-04-28 00:00:00', '2833', null, null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('24534087746194190', 'admin', '13101089314', 'e10adc3949ba59abbe56e057f20f883e', '1', '18', 'http://xtc.oss-cn-hangzhou.aliyuncs.com/dev/image/0a2a948f-ed61-4548-81e6-aad7e8b45de4', 'J86GPB', '18071adc030e329ec80', '1461923663979580', '0', null, null, '2016-04-29 13:25:37', '026042', null, null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('24534087746194196', 'admin', '13101089314', 'e10adc3949ba59abbe56e057f20f883e', '1', '18', 'http://xtc.oss-cn-hangzhou.aliyuncs.com/dev/image/0a2a948f-ed61-4548-81e6-aad7e8b45de4', 'J86GPB', '18071adc030e329ec80', '1461923663979580', '0', null, null, '2016-04-29 13:25:37', '026042', null, null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('24535443009699849', '旧梦丶毁佳人', '13101089314', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, null, null, null, '0', null, '2016-05-25 10:48:02', '2016-05-25 10:48:06', '520520', null, null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('33408e78-ce82-4427-8925-59fda4b3796e', null, '18581343206', null, null, null, null, null, null, null, '0', null, '2016-05-19 09:42:19', '2016-05-26 15:43:12', '169590', null, null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('50f3603e-205c-41c5-a8d0-3bdfe85e5dd7', null, '15903697128', null, null, null, null, null, null, null, '0', null, null, '2016-05-26 12:11:26', '992239', null, null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('547b0c88-760e-41b1-bff2-94c81b72dc3a', '111', '18530911214', 'FCEA920F7412B5DA7BE0CF42B8C93759', null, null, null, null, '', '1464156957719345', '1', null, '2016-05-24 15:28:43', '2016-05-25 13:43:22', '', null, '111', '1111 1', null, null, null, '2222', '111', null);
INSERT INTO `user` VALUES ('556c3113-b168-4030-9d6d-eb7d3dc14f13', null, '13818413839', null, null, null, null, null, null, null, '0', null, null, '2016-05-26 12:10:02', '195671', null, null, null, null, null, null, null, null, null);

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
INSERT INTO `userinfo` VALUES ('14ce93c0-a8a3-4c45-99ce-cbf9bcdaed17', '将军令', '123123123123', '4297F44B13955235245B2497399D7A93', '1', '2016-05-17 11:01:25', '诚智汇达java', '0');
INSERT INTO `userinfo` VALUES ('24535443009699788', '旧梦丶毁佳人', '旧梦丶毁佳人', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2016-05-25 17:38:30', '旧梦丶毁佳人', '1');
INSERT INTO `userinfo` VALUES ('25ae948c-1bc9-4cfa-b32f-9fb543b14340', '聂伟', 'niewei', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2016-05-13 17:58:26', '我是牛逼人物！我是风云人物~', '1');
INSERT INTO `userinfo` VALUES ('28c5c8a1-4109-4fa4-aae1-aa2d5c9e5686', '聂伟', 'nieiwei2', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2016-05-13 17:59:48', '我是牛逼人物！我是风云人物~', '0');
INSERT INTO `userinfo` VALUES ('443fb960-9de1-49a8-a5f7-1949683afdf4', '梦梦', '5201314', '723D505516E0C197E42A6BE3C0AF910E', '1', '2016-05-17 11:00:49', 'java', '0');
INSERT INTO `userinfo` VALUES ('6afc7e40-fca8-4026-a4e3-5e7d8b2aa4f7', 'sssssss', 'niewei4', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2016-05-17 11:02:29', 'aff', '0');
INSERT INTO `userinfo` VALUES ('75248991-8453-4c83-b8b1-d57fef1c76de', '聂伟', 'niewei3', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2016-05-16 13:43:58', '我是你仙人。。', '0');
INSERT INTO `userinfo` VALUES ('84103a56-c17d-4070-aba4-c22c88699f38', '小伟哥', 'niewei2', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2016-05-13 18:08:31', '我是聂伟！！', '0');
INSERT INTO `userinfo` VALUES ('b7df0f2d-bc91-4d9a-a74c-f3ee60bd1f4f', '小伟', 'niewei1', 'E10ADC3949BA59ABBE56E057F20F883E', '2', '2016-05-17 11:00:15', 'dcdfdf', '0');
INSERT INTO `userinfo` VALUES ('b8bccb5c-84cc-4c95-ba68-8a8d9bf77c65', '小伟子', 'xiaoweizi', 'E807F1FCF82D132F9BB018CA6738A19F', '1', '2016-05-18 14:54:01', '架构师', '0');
INSERT INTO `userinfo` VALUES ('d898862b-0d99-453a-a1e0-02bf67efbaea', '小布', 'xbd8023', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2016-05-17 13:08:28', '学生', '0');
INSERT INTO `userinfo` VALUES ('f133b0bd-00a4-4d8f-b2d8-365f7369348d', '彭安琳', '15900758337', 'A0669BCF2AFCEA589A56AF2F9BF8FC8F', '1', '2016-05-17 11:01:18', 'java工程师', '0');
INSERT INTO `userinfo` VALUES ('f278ce8d-766a-4292-a7db-e1ba1092660f', 'nn', 'niewei!', 'E10ADC3949BA59ABBE56E057F20F883E', '1', '2016-05-16 13:45:05', '我是你仙人。。', '0');

-- ----------------------------
-- Table structure for vehicle
-- ----------------------------
DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `vehicleid` varchar(36) NOT NULL COMMENT '车辆主键id',
  `vehicleno` varchar(10) NOT NULL COMMENT '车牌号',
  `userid` varchar(36) NOT NULL,
  PRIMARY KEY (`vehicleid`),
  UNIQUE KEY `UN_Vehice_vehice` (`vehicleno`),
  KEY `FK_Vehice_User` (`userid`),
  CONSTRAINT `FK_Vehice_User` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆表';

-- ----------------------------
-- Records of vehicle
-- ----------------------------
INSERT INTO `vehicle` VALUES ('24534087746194176', 'NB74110', '11111111111');
INSERT INTO `vehicle` VALUES ('24534087746194177', 'NB74111', '11111111111');
INSERT INTO `vehicle` VALUES ('24534087746194180', 'NB74112', '11111111111');

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
INSERT INTO `withdrawalrecord` VALUES ('1', '1.00', '2016-05-26 10:44:24', '33408e78-ce82-4427-8925-59fda4b3796e', '1', '2016-05-27 10:44:34', '1', '1', '1');
INSERT INTO `withdrawalrecord` VALUES ('2', '2.00', '2016-05-11 10:44:03', '11111111111', '2', '2016-05-17 10:44:12', '2', '2', '2');

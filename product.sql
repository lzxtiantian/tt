/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2019-10-21 11:00:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `type` varchar(255) DEFAULT '上架',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('41', '电脑', '1.png', '2322.10', '上架');
INSERT INTO `product` VALUES ('43', '键盘4', '1.png', '32.00', '下架');
INSERT INTO `product` VALUES ('45', '键盘6', '1.png', '78.00', '上架');
INSERT INTO `product` VALUES ('46', '键盘7', '1.png', '55.00', '下架');
INSERT INTO `product` VALUES ('47', '键盘8', '1.png', '45.00', '上架');
INSERT INTO `product` VALUES ('48', '键盘9', '1.png', '88.00', '上架');
INSERT INTO `product` VALUES ('49', '键盘0', '1.png', '45.00', '上架');
INSERT INTO `product` VALUES ('50', '键盘11', '1.png', '33.00', '下架');
INSERT INTO `product` VALUES ('51', '键盘12', '1.png', '22.00', '上架');
INSERT INTO `product` VALUES ('52', '键盘13', '1.png', '23.00', '上架');
INSERT INTO `product` VALUES ('53', '键盘114', '1.png', '111.00', '下架');
INSERT INTO `product` VALUES ('54', '键盘132', '1.png', '2.00', '下架');
INSERT INTO `product` VALUES ('55', '11111111111', null, '1.00', '下架');
INSERT INTO `product` VALUES ('56', '222', null, '222.00', '下架');
INSERT INTO `product` VALUES ('57', '显示屏', null, '1000.00', '下架');

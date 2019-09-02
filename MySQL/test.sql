/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : test

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 09/02/2019 15:50:34 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `SN` int(11) NOT NULL AUTO_INCREMENT,
  `STU_NUM` char(11) NOT NULL,
  `STU_NAME` varchar(10) NOT NULL,
  `STU_SEX` char(1) DEFAULT NULL,
  `STU_MAIL` varchar(100) DEFAULT NULL,
  `STU_TEL` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`SN`),
  UNIQUE KEY `123` (`STU_NUM`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `student`
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES ('48', '16310121102', '33', 'F', '', ''), ('53', '16310121197', 'qwe', 'M', '222222', '113221'), ('54', '16310121104', 'eee', 'F', '343434', ''), ('56', '16310121188', 'qwe', '', '', ''), ('57', '16310121187', 'www', 'F', '', '1616666'), ('58', '16310121166', 'qwe', 'M', '123123123', '222222'), ('62', '16310121103', 'eee', 'M', '5555', '444444'), ('64', '16310121105', 'www', 'M', '', ''), ('65', '16310121107', '???', 'M', 'qweqwe@123123.com', '163222321'), ('66', '16310121106', 'tom', 'M', '123456', '232323232323'), ('67', '16310121108', 'qwe', 'F', '123', '22222'), ('68', '16310121109', 'bop', 'F', '2323123wefawef', '666'), ('71', '16310121101', 'qqqw', '', '', '11'), ('72', '16310121110', 'eee', 'M', '123456', ''), ('76', '16310121114', 'eee', 'M', 'eeee', '444'), ('77', '16310121111', 'Lili', 'F', 'abc@163.com', '13000000000'), ('78', '123456', '121', 'F', '1231312313123123', '123123'), ('79', '123123', '123', '', '', '');
COMMIT;

-- ----------------------------
--  Table structure for `student_score`
-- ----------------------------
DROP TABLE IF EXISTS `student_score`;
CREATE TABLE `student_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `STU_NUM` char(11) NOT NULL,
  `STU_MATH` varchar(3) DEFAULT NULL,
  `STU_CHINESE` varchar(3) DEFAULT NULL,
  `STU_ENGLISH` varchar(3) DEFAULT NULL,
  `STU_SUM` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `student_score`
-- ----------------------------
BEGIN;
INSERT INTO `student_score` VALUES ('5', '16310121102', '22', '44', '33', '99'), ('10', '16310121197', '22', '33', '44', null), ('11', '16310121104', '22', '', '', null), ('13', '16310121188', null, null, null, null), ('14', '16310121187', '23', '33', '', null), ('15', '16310121166', '66', '77', '88', null), ('19', '16310121103', '0', '0', '0', '0'), ('21', '16310121105', '22', '0', '0', '22'), ('22', '16310121107', null, null, null, null), ('23', '16310121101', '223', '22', '11', null), ('24', '16310121106', null, null, null, null), ('25', '16310121108', '22', '66', '0', '88'), ('26', '16310121109', '22', '0', '0', '22'), ('29', '16310121101', '223', '22', '11', null), ('30', '16310121110', '22', '33', '33', '88'), ('34', '16310121114', '55', '66', '77', '198'), ('35', '16310121111', '99', '98', '97', '294'), ('36', '123456', '123', '123', '123', '369'), ('37', '123123', null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` blob NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('6', 'admin', 0xe602cdb9900c), ('7', 'root', 0xe602cdb9900c), ('8', 'ROOT1', 0xe602cdb990), ('9', 'root3', 0xe602cdb9900c), ('10', 'root5', 0xe602cdb9900c), ('11', 'ad', 0xe602cdb9900c), ('12', '123', 0xe602dc);
COMMIT;

-- ----------------------------
--  Table structure for `visitor_user`
-- ----------------------------
DROP TABLE IF EXISTS `visitor_user`;
CREATE TABLE `visitor_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` blob NOT NULL,
  `STU_NUM` char(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `visitor_user`
-- ----------------------------
BEGIN;
INSERT INTO `visitor_user` VALUES ('44', 'TTT', 0xe602cdb9900c, '16310121102'), ('45', 'yyy', 0xe602cdb9900c, '16310121108'), ('46', '123456', 0xe602cdb990, '16310121109'), ('48', 'PPP', 0xe602cdb9900c, '16310121110'), ('49', 'iii', 0xe602a854, '16310121104'), ('50', 'asd', 0xe602a854, '16310121111'), ('51', 'rrrr', 0xe602a854, '16310121112'), ('52', 'www', 0xe602cdb990, '16310121113'), ('53', 'zzz', 0xe602cdb990, '16310121133'), ('54', 'qweasd', 0xe602cdb9900c, '16310121199'), ('55', '111', 0xe602cd22bd85, '123456'), ('56', '123123', 0xe602cd22bd85, '123123');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

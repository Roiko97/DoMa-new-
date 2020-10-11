DROP DATABASE IF EXISTS `doma`;
CREATE DATABASE `doma`;
USE `doma`;
CREATE TABLE `docinfo` (
  `user_id` char(10) DEFAULT NULL COMMENT '发布用户id',
  `doc_id` char(10) DEFAULT NULL COMMENT '文档id',
  `pub_time` datetime DEFAULT NULL COMMENT '发布时间',
  `doc_name` varchar(50) DEFAULT NULL COMMENT '文档名',
  `recommend` int DEFAULT NULL COMMENT '是否推荐 1 - 0 ',
  `style` varchar(30) DEFAULT NULL COMMENT '分类',
  `address` text COMMENT '文件地址',
  `exist_ann` int DEFAULT NULL COMMENT '是否注解',
  `content` text COMMENT '注解内容'
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名称',
   `user_id` char(10) DEFAULT NULL COMMENT '用户id',
   `password` varchar(20) not null COMMENT '用户密码',
   `account` varchar(20) not null COMMENT '用户账号'
);

DROP TABLE IF EXISTS `teamMember`;
CREATE TABLE `teaminfo`(
	`team_id` char(10) DEFAULT NULL COMMENT '团队编号',
	`team_name` varchar(30) DEFAULT NULL COMMENT '团队名称',
    `user_name` varchar(20) DEFAULT NULL COMMENT '用户名称',
    `user_id` char(10) DEFAULT NULL COMMENT '用户id'
);

DROP TABLE IF EXISTS `teams`;
CREATE TABLE `teams`(
	`team_id` char(10) DEFAULT NULL COMMENT '团队编号',
	`team_name` varchar(30) DEFAULT NULL COMMENT  '团队名称'
);


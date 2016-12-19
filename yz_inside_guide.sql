/*
Navicat MySQL Data Transfer

Source Server         : yz_26_1(yz_inside_guide)
Source Server Version : 50544
Source Host           : 172.16.100.26:3306
Source Database       : yz_inside_guide

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2016-12-19 11:36:50
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `guideitem`
-- ----------------------------
DROP TABLE IF EXISTS `guideitem`;
CREATE TABLE `guideitem` (
  `id` varchar(64) NOT NULL,
  `guide_type` varchar(64) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `des` varchar(128) DEFAULT NULL,
  `inner_url` varchar(128) DEFAULT NULL,
  `outer_url` varchar(128) DEFAULT NULL,
  `download_url_forios` varchar(128) DEFAULT NULL,
  `download_url_forandroid` varchar(128) DEFAULT NULL,
  `download_url_forpc` varchar(128) DEFAULT NULL,
  `sort_order` double NOT NULL,
  `creator` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modifier` varchar(128) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  `upload_picture` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `guide_Type` (`guide_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of guideitem
-- ----------------------------
INSERT INTO `guideitem` VALUES ('01bf55aa-9d8c-4d32-bd58-3cde0d39ace4', 'sys', '网三', 'http://w.com', 'http://www.12.com', 'http://w.com', 'http://www.12.com', 'http://w.com', 'http://w.com', '46', 'sys', '2016-11-01 13:33:26', 'sys', '2016-11-02 17:09:17', null);
INSERT INTO `guideitem` VALUES ('0702c332-1dc4-4743-ba51-861bdb02c25f', 'web', '赵老六', '', 'http://ww.12.com', '', '', '', '', '50', 'sys', '2016-10-31 14:52:09', 'sys', '2016-11-02 14:45:03', null);
INSERT INTO `guideitem` VALUES ('0f08b5d9-5f5c-4883-a4c8-51c200e07f6f', 'sys', '裘千尺', '', 'http://ww.12.com', 'http://w.com', 'http://www.12.com', '', 'http://www.com', '26', 'sys', '2016-10-31 14:53:06', 'sys', '2016-11-02 16:59:36', null);
INSERT INTO `guideitem` VALUES ('1', 'web', '邮件', '内部文件管理系统', 'http://mail.yzhtech.com', 'http://mail.yzhtech.com', '', '', '', '2', 'sys', '2016-10-26 14:23:21', 'sys', '2016-11-28 18:11:31', 'JPG 文件\\6786f643-6456-462d-9510-dead0cfadf4f.jpg');
INSERT INTO `guideitem` VALUES ('10', 'sys', '考勤管理系统', '内部文件管理系统', 'http://172.16.100.7', 'http://172.16.100.7', 'http://www.waiqin365.com/p-download-142.html', 'http://www.waiqin365.com/p-download-142.html', 'http://172.16.100.7', '10', 'sys', '2016-10-26 14:21:26', 'sys', '2016-11-28 13:49:08', null);
INSERT INTO `guideitem` VALUES ('11', 'sys', '云集客', '云集客后台客户端', 'http://mail.yzhtech.com', '', 'http://www.hao123.com', '', '', '1', 'sys', '2016-10-26 14:23:11', 'sys', '2016-11-28 18:12:02', 'JPG 文件\\f81bef49-845d-46fe-95b8-1f6255820d55.jpg');
INSERT INTO `guideitem` VALUES ('12', 'sys', '地铁生活圈', '地铁生活圈后台客户端', 'http://172.16.100.8:8090', 'http://jira.yzht', 'http://jira.yzht', 'http://jira.yzht', '', '8', 'sys', '2016-10-24 17:58:19', 'sys', '2016-11-28 16:47:18', null);
INSERT INTO `guideitem` VALUES ('13', 'web', 'JIRA', 'BUG管理器', 'http://jira.yzht', 'http://jira.yzht', '', '', '', '5', 'sys', '2016-10-24 17:47:50', 'sys', '2016-11-28 17:03:25', 'JPG 文件\\d6a9c7ca-2dc2-4e99-9d3c-703f975796de.jpg');
INSERT INTO `guideitem` VALUES ('14', 'web', 'GitLab', '代码同步服务', 'http://172.16.100.com', 'http://172.16.100.7', '', '', '', '6', 'sys', '2016-10-26 09:40:23', 'sys', '2016-11-28 17:04:07', 'JPG 文件\\7d89c30b-9745-42ec-a487-5e7adbdc84e9.jpg');
INSERT INTO `guideitem` VALUES ('14514f9c-0813-4a70-9c28-b3ae285fde69', 'web', '有多少快乐有你和我一起享受', '左半边翅膀', 'http://baidu.com', '', '', '', '', '27', 'sys', '2016-11-22 14:32:17', 'sys', '2016-11-22 15:43:23', null);
INSERT INTO `guideitem` VALUES ('15', 'sys', 'WunderList', '内部文件管理系统', 'http://172.16.100.7', 'http://172.16.100.7', 'https://www.wunderlist.com/zh/download/', 'https://www.wunderlist.com/zh/download/', 'http://172.16.100.8:8090', '7', 'sys', '2016-10-18 10:04:30', 'sys', '2016-11-28 17:03:51', 'JPG 文件\\ec085d3e-0f6c-44ae-ab6c-a6869251492d.jpg');
INSERT INTO `guideitem` VALUES ('16', 'web', 'FTP', '内部文件管理系统', 'http://172.16.100.16', 'http://172.16.100.16', '', '', '', '15', 'sys', '2016-10-20 15:40:01', 'sys', '2016-11-28 13:49:46', null);
INSERT INTO `guideitem` VALUES ('1bcf77ea-36b9-465d-ba44-1e68b299cdfc', 'web', '王世仁11', '', 'http://172.com', '', '', '', '', '41', 'sys', '2016-10-31 15:20:48', 'sys', '2016-11-22 15:42:22', null);
INSERT INTO `guideitem` VALUES ('2', 'doc', '运维技术文档', 'ibacon配置,command', 'http://172.16.100.8:8090/pages/viewpage.action?pageId=2490507', 'http://222.128.2.58:9997/pages/viewpage.action?pageId=2490507', '', '', '', '16', 'sys', '2016-10-26 17:38:21', 'sys', '2016-11-28 13:47:45', null);
INSERT INTO `guideitem` VALUES ('210ccf93-57a8-4a0d-90db-cae4289917b6', 'web', '难不成', '', 'http://www.qcom', '', '', '', '', '40', 'sys', '2016-10-31 15:24:13', 'sys', '2016-11-02 16:48:13', null);
INSERT INTO `guideitem` VALUES ('231e3dc6-f599-4cc8-98b0-7a6e66d2bc16', 'web', '天上有头牛', '', 'http://22.com', 'http://22.com', '', '', '', '29', 'sys', '2016-11-02 12:25:49', 'sys', '2016-11-23 10:57:02', null);
INSERT INTO `guideitem` VALUES ('2745c80f-4627-49a5-872e-142b0ff65895', 'web', '卿本佳人，奈何为贼', '以五十步笑百步', 'http://www.youku.com', '', '', '', '', '40.5', 'sys', '2016-11-23 12:19:47', 'sys', '2016-11-29 09:51:24', null);
INSERT INTO `guideitem` VALUES ('2afd5228-95e5-4604-af41-d9274b0f64fe', 'sys', '赵本山', '赵本山坑你点com', 'http://zhbsh.com', 'http://zhbsh.com', 'http://zhbsh.com', '', '', '28', 'sys', '2016-11-07 15:52:00', 'sys', '2016-11-25 14:53:09', null);
INSERT INTO `guideitem` VALUES ('3', 'sys', '绩效考核系统', '绩效考核', 'http://172.16.100.7', 'http://172.16.100.7', 'http://172.16.100.7', 'http://172.16.100.7', 'http://172.16.100.7', '3', 'sys', '2016-10-26 17:37:16', 'sys', '2016-11-28 16:52:23', 'JPG 文件\\8c17213e-b8e2-4c74-8dc3-3ad7f405f732.jpg');
INSERT INTO `guideitem` VALUES ('300a8f47-a2e8-4ca2-bdae-1d935d277b3f', 'web', '暖春', '', 'http://w.com', 'http://w.com', '', '', '', '28', 'sys', '2016-11-01 13:37:53', 'sys', '2016-11-02 16:48:39', null);
INSERT INTO `guideitem` VALUES ('30b35747-5686-4037-99db-0c6a44f5b4c5', 'web', '123456781234567', '1234567812345678123456781234567812345678123456780', 'http://22.dd.dd', 'http://bb.ee', '', '', '', '33', 'sys', '2016-11-22 14:13:12', 'sys', '2016-11-25 14:07:45', null);
INSERT INTO `guideitem` VALUES ('30f9ecaa-0ae7-4075-a869-d8957bce8386', 'doc', '唱给妈妈的歌', '机器人时代', 'http://kugou.com', 'http://www.robot-china.com', '', '', '', '17', 'sys', '2016-11-24 14:40:21', 'sys', '2016-11-28 11:12:31', null);
INSERT INTO `guideitem` VALUES ('4', 'doc', '英智规章制度', '员工手册,考核制度等', 'http://www.172.com', 'http://172.16.100.8:8090', '', '', '', '14', 'sys', '2016-10-26 17:38:35', 'sys', '2016-11-28 13:47:53', null);
INSERT INTO `guideitem` VALUES ('43f065e9-0a18-4fb5-9e94-ca7a0462be39', 'web', '全智贤', '我的野蛮女友', 'http://www.987.com', 'http://www.987.com', '', '', '', '54', 'sys', '2016-11-28 16:32:01', null, null, null);
INSERT INTO `guideitem` VALUES ('452d17b7-3862-4d30-9242-bb4e04098428', 'web', '喧闹的孤独', 'erwrwer', 'http://www.b.com', 'http://www.b.com', '', '', '', '20', 'sys', '2016-10-27 14:51:34', 'sys', '2016-11-28 11:37:19', null);
INSERT INTO `guideitem` VALUES ('4a645d6e-de29-4ad0-8a72-2875a1b817fb', 'web', '我看见一座座山，一座座山川', 'http://w.com', 'http://w.com', 'http://w.com', '', '', '', '37', 'sys', '2016-11-01 13:35:43', 'sys', '2016-11-02 16:49:09', null);
INSERT INTO `guideitem` VALUES ('5', 'doc', 'IT制度', '电脑安全设定准则', 'http://172.16.100.8:8090/pages/viewpage.action?pageId=1835123', 'http://222.128.2.58:9997/pages/viewpage.action?pageId=1835123', '', '', '', '12', 'sys', '2016-10-26 17:38:55', 'sys', '2016-11-28 13:47:32', null);
INSERT INTO `guideitem` VALUES ('504e2b06-9d68-4c6f-b727-fd84de4478e3', 'doc', '伍佰', '', 'http://www.qq.com', '', '', '', '', '53', 'sys', '2016-11-01 14:45:53', 'sys', '2016-11-28 14:05:39', null);
INSERT INTO `guideitem` VALUES ('5ba83b43-a8d6-425b-8e9b-c29a0118cbd9', 'doc', '大王派我来巡山呀', '', 'http://www.iqiyi.com', '', '', '', '', '50', 'sys', '2016-11-01 13:36:55', 'sys', '2016-11-28 15:33:44', null);
INSERT INTO `guideitem` VALUES ('6', 'doc', '公司通讯录', ' \r\n\r\n公司内部人员通讯录\r\n\r\n', 'http://172.16.100.8:8090/pages/viewpage.action?pageId=5080848', 'http://222.128.2.58:9997/pages/viewpage.action?pageId=5080848', '', '', '', '13', 'sys', '2016-10-26 17:38:10', 'sys', '2016-11-28 16:10:27', null);
INSERT INTO `guideitem` VALUES ('658aceb8-ec21-497f-8372-f7ece9f315e7', 'doc', '天高任鸟飞', '海阔凭鱼跃', 'http://360.com', 'http://360.com', '', '', '', '60', 'sys', '2016-11-22 17:03:33', 'sys', '2016-11-28 15:32:07', null);
INSERT INTO `guideitem` VALUES ('663334e8-2fe8-404a-88ee-55b71f90eb2f', 'doc', '熙熙攘攘的人群里', '我飞呀飞呀', 'http://youku.com', '', '', '', '', '57', 'sys', '2016-11-22 14:39:19', 'sys', '2016-11-28 12:26:03', null);
INSERT INTO `guideitem` VALUES ('689a5ebb-40be-4a59-be1d-8841a68f311e', 'doc', '11', '', 'http://www.11.com', '', '', '', '', '6', 'sys', '2016-11-29 09:50:47', null, null, 'PNG 文件\\f1928d39-f0e2-4a8e-9328-f85fd68397cd.png');
INSERT INTO `guideitem` VALUES ('6d5d0c4c-79f3-47e9-b138-5c854c864858', 'sys', '清风徐来', 'http://www.11.com', 'http://www.11.com', 'http://www.123.com', 'http://www.11.com', '', '', '55', 'sys', '2016-11-09 10:14:35', 'sys', '2016-11-23 10:37:24', null);
INSERT INTO `guideitem` VALUES ('6ee594d7-b54c-4938-b3c2-1a719d5a8f05', 'web', '奇葩说', 'qipa', 'http://www.qb.com', 'http://www.qb.com', '', '', '', '21', 'sys', '2016-11-25 13:46:04', 'sys', '2016-11-25 14:39:05', null);
INSERT INTO `guideitem` VALUES ('7', 'web', '文件服务器', '内部文件管理系统', 'http://172.16.100.2', 'http://172.16.100.2', '', '', '', '4', 'sys', '2016-10-26 17:39:53', 'sys', '2016-11-28 17:03:15', 'JPG 文件\\afbd7866-3b04-4073-b6af-85fe2b4effe1.jpg');
INSERT INTO `guideitem` VALUES ('75c80a88-3f50-486f-9714-b5e89bb5723f', 'sys', '变美这件小事，爱咋咋地', '我是一个粉刷匠，粉刷本领强，我要把那新房子刷的干又亮，刷了房顶又刷墙', 'http://www.kdi.com', '', 'http://douban.com', 'http://douban.com', '', '38', 'sys', '2016-10-27 12:29:31', 'sys', '2016-11-10 12:27:54', null);
INSERT INTO `guideitem` VALUES ('7842b819-12a0-4c1c-8a26-901a4b481281', 'doc', '今天天气好冷', '', 'http://22.com', 'http://22.com', '', '', '', '42.5', 'sys', '2016-11-02 12:25:22', 'sys', '2016-11-29 09:49:47', null);
INSERT INTO `guideitem` VALUES ('7b7c75a1-cea0-40ff-9048-4ff2f14fb654', 'web', '欢迎光临', '', 'http://2.12.dd', '', '', '', '', '49', 'sys', '2016-10-27 15:18:50', 'sys', '2016-11-02 16:49:37', null);
INSERT INTO `guideitem` VALUES ('7f680339-42f3-47b9-bc2f-5197a8850f6d', 'sys', '是谁啊', '', 'http://www.123.com', '', 'http://www.123.com', '', '', '58', 'sys', '2016-10-31 11:45:39', 'sys', '2016-11-02 16:14:37', null);
INSERT INTO `guideitem` VALUES ('8', 'sys', '合同管理系统', '合同管理系统软件', 'http://172.16.100.7', 'http://172.16.100.7', 'http://www.youku.com', 'http://172.16.100.8:8090', 'http://172.16.100.8:8090', '9', 'sys', '2016-10-26 14:34:05', 'sys', '2016-11-28 13:49:17', null);
INSERT INTO `guideitem` VALUES ('88036413-5472-4b37-bcf4-cbc5f991963f', 'doc', '一见容止误终生', '', 'http://22.com', 'http://22.com', '', '', '', '49', 'sys', '2016-11-02 12:27:05', 'sys', '2016-11-02 12:33:08', null);
INSERT INTO `guideitem` VALUES ('8f2ceb88-81a8-422b-b45b-ee2db33b919d', 'doc', '时间都去哪了', 'kougou', 'http://www.kugou.com', 'http://www.kugou.com', '', '', '', '18', 'sys', '2016-11-28 12:16:29', null, null, null);
INSERT INTO `guideitem` VALUES ('9', 'web', 'confluence', '内部文件管理系统', 'http://172.16.100.8:8090', 'http://hub.yzhtech.com', '', '', '', '19', 'sys', '2016-10-24 17:32:57', 'sys', '2016-11-28 13:50:34', null);
INSERT INTO `guideitem` VALUES ('902f20f0-719d-438b-95ab-f8f111acfb77', 'sys', '我来自偶然', '', 'http://163.com', 'http://douban.com', 'ftp://172.16.100.16', 'http://zhihu.com', 'http://weibo.com', '40', 'sys', '2016-11-22 14:15:49', 'sys', '2016-11-23 11:52:36', null);
INSERT INTO `guideitem` VALUES ('94c5a9c3-9e05-4eba-9a2a-58c082c4f3e4', 'sys', '杉菜', '', 'http://172.16.100.198:8080/yz-inside-guide-web', '', 'http://172.16.100.198:8080/yz-inside-guide-web', '', '', '62', 'sys', '2016-10-31 15:41:46', 'sys', '2016-11-28 12:26:32', null);
INSERT INTO `guideitem` VALUES ('9b674717-d8c7-47fe-9cb0-a8fce6ac2ece', 'doc', '千与千寻', '千寻', 'http://gong.com', 'http://gong.com', '', '', '', '35', 'sys', '2016-11-25 15:08:56', 'sys', '2016-11-28 16:49:20', null);
INSERT INTO `guideitem` VALUES ('9d420fb5-f579-493f-9fa0-002fb3201be2', 'sys', '飞天猪', '', 'http://22.com', 'http://22.com', 'http://22.com', '', '', '37', 'sys', '2016-11-02 12:26:17', 'sys', '2016-11-02 16:33:32', null);
INSERT INTO `guideitem` VALUES ('9da57e9a-0e2f-4c62-a954-456962f5bed8', 'sys', 'V型秀', 'http://w.com', 'http://w.com', 'http://w.com', 'http://w.com', 'http://w.com', 'http://w.com', '39', 'sys', '2016-11-01 13:34:10', 'sys', '2016-11-03 09:40:42', null);
INSERT INTO `guideitem` VALUES ('a0c6edb5-c349-4f86-b5be-d93942382132', 'doc', '奥巴马', '奥巴马', 'http://image.baidu.com', 'http://image.baidu.com', '', '', '', '36', 'sys', '2016-11-28 12:36:28', 'sys', '2016-11-28 16:50:10', null);
INSERT INTO `guideitem` VALUES ('a30192e5-7045-4fa5-8216-5b8ef9177582', 'sys', '奇葩说', '', 'http://22.com', '', 'http://56.com', '', '', '11', 'sys', '2016-11-02 12:24:47', 'sys', '2016-11-28 12:27:08', null);
INSERT INTO `guideitem` VALUES ('a440f38f-c27a-4dfb-bb0f-d79ec4717688', 'sys', '鸟山明', '', 'http://172.16.100.198:8080/yz-inside-guide-web', '', '', '', '', '30', 'sys', '2016-10-31 15:33:57', 'sys', '2016-11-25 14:01:32', null);
INSERT INTO `guideitem` VALUES ('a69a0989-f180-40df-a24c-7a414b422fc9', 'doc', '王二麻子', '王二麻子的小叔子', 'http://www.baidu.com', 'http://www.baidu.com', '', '', '', '59.5', 'sys', '2016-11-25 15:09:38', 'sys', '2016-11-29 09:25:44', null);
INSERT INTO `guideitem` VALUES ('acdab81f-697b-441f-890d-3b12b7201683', 'doc', '文档2', '', 'http://www.11.com', '', '', '', '', '31', 'sys', '2016-10-31 13:53:47', 'sys', '2016-11-22 14:48:58', null);
INSERT INTO `guideitem` VALUES ('adb55b7a-a934-47e7-a5e8-7efed967d6a0', 'web', '归来', '巩俐', 'http://123.com', 'http://123.com', '', '', '', '56', 'sys', '2016-11-28 12:22:13', null, null, null);
INSERT INTO `guideitem` VALUES ('b204e18e-7734-42cd-8d5a-197ea0e6fbf4', 'sys', '继续下一个梦', '也为我保留，心的寄托\r\n过去种种抵挡我的手掌 \r\n不管那有多困难重重', 'http://163.com', '', 'http://172.16.100.115', '', '', '34', 'sys', '2016-11-22 14:29:50', 'sys', '2016-11-22 17:11:25', null);
INSERT INTO `guideitem` VALUES ('be12c637-5348-40cc-97c8-a1456ed1f962', 'sys', '青青河上草', '', 'http://66.com', 'http://66.com', 'http://66.com', '', '', '25', 'sys', '2016-11-02 17:02:07', 'sys', '2016-11-28 10:45:30', null);
INSERT INTO `guideitem` VALUES ('bee69a8f-9d91-46d8-b3fb-7e2b84fbfee1', 'doc', '凉州铁骑踏京都', '', 'http://www.123.com', '', '', '', '', '52', 'sys', '2016-11-02 09:40:40', 'sys', '2016-11-28 12:06:12', null);
INSERT INTO `guideitem` VALUES ('bf45b2f0-5060-4609-ab96-67022a48e903', 'sys', '于谦', '', 'http://22.com', 'http://22.com', 'http://22.com', '', '', '22', 'sys', '2016-11-02 12:29:11', 'sys', '2016-11-02 16:42:04', null);
INSERT INTO `guideitem` VALUES ('c3fc6f75-e49c-4578-8c6d-b6980d1225bd', 'doc', '秋千画', '', 'http://q.com', '', '', '', '', '39.25', 'sys', '2016-10-31 15:15:07', 'sys', '2016-11-29 09:49:58', null);
INSERT INTO `guideitem` VALUES ('c615d27b-4398-461c-8591-2be6c4b3f7fa', 'doc', '怒放的生命', '王菲', 'http://www.123.com', 'http://www.iqiyi.com', '', '', '', '43', 'sys', '2016-11-28 12:19:46', 'sys', '2016-11-28 16:05:06', null);
INSERT INTO `guideitem` VALUES ('c8421264-ed92-49fb-bb64-4c1c8edb055f', 'doc', '只有上帝是万能的', '尽力做好自己该做的事，好运自然来', 'http://22.com', 'http://22.com', '', '', '', '51', 'sys', '2016-11-02 12:28:20', 'sys', '2016-11-28 16:05:18', null);
INSERT INTO `guideitem` VALUES ('c8939aaf-77d0-407c-8ec1-7871fcd676db', 'web', '悟空', '戴荃', 'http://www.987.com', 'http://www.789.com', '', '', '', '23', 'sys', '2016-11-28 12:21:07', 'sys', '2016-11-28 16:49:41', null);
INSERT INTO `guideitem` VALUES ('c8fdc2a5-fc9e-45d0-9284-82ec420d587b', 'sys', 'Les ann passen', '1234567812345678123456781234567812345678123456781234567812345678', 'http://baidu.com', '', 'http://douban.com', '', '', '32', 'sys', '2016-11-14 14:33:49', 'sys', '2016-11-22 15:43:05', null);
INSERT INTO `guideitem` VALUES ('e5229d42-b832-47bd-b319-fce6e62ef09e', 'sys', '国色天香', '倩女幽魂', 'http://www.qq.com', 'http://www.qq.com', 'http://www.qq.com', 'http://www.qq.com', 'http://www.qq.com', '61', 'sys', '2016-11-23 17:35:51', 'sys', '2016-11-28 16:50:49', null);
INSERT INTO `guideitem` VALUES ('ee94ff30-16f1-411f-9efa-5b70d9a3ae2e', 'sys', '李三旗', '', 'http://ww.qom', 'http://w.com', 'http://ww.qom', 'http://w.com', 'http://w.com', '24', 'sys', '2016-10-31 15:21:54', 'sys', '2016-11-02 16:43:20', null);

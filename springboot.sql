/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 26/12/2021 15:56:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `price` double NULL DEFAULT NULL COMMENT '价格',
  `useful` tinyint(1) NULL DEFAULT NULL COMMENT '是否可用 0- 不可用 1-可用',
  `create_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-mm-DD HH:MM:SS',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '书籍表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('07454fee016c2799466d890f0dfc5ee6', '第一人称单数', 19.99, 1, '2021-11-09 10:30:34');
INSERT INTO `book` VALUES ('106772e78b2a126464117bcc2acb8359', '围城', 15.3, 1, '2021-11-09 10:36:42');
INSERT INTO `book` VALUES ('123qwe', '浮木', 26.03, 1, '2021-11-04 15:47:32');
INSERT INTO `book` VALUES ('2f6ea4693e3dbdfa9ae777e1c23f2da2', '房思琪的初恋乐园', 9.99, 1, '2021-12-20 15:37:25');
INSERT INTO `book` VALUES ('48372d3fbe1fdd5eb91e8294f4e7da19', '南京大屠杀', 20, 1, '2021-12-20 20:42:00');
INSERT INTO `book` VALUES ('8849fdb0afe01611be779289d1a0ecdd', '献给阿尔吉侬的花束', 10.8, 1, '2021-12-20 15:40:10');
INSERT INTO `book` VALUES ('d3750781750b4266c3f5315d1cf8e694', '猫城记', 4.99, 1, '2021-11-04 15:47:54');
INSERT INTO `book` VALUES ('f513521248156bba662aec050c7759a0', '在细雨中呼喊', 11.99, 1, '2021-11-09 10:38:23');

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Author` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_user` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES ('123', '07454fee016c2799466d890f0dfc5ee6', '村上春树', '2021-11-09 10:30:39', '0855a674f56de4ef02292613c6d8948c', '暌违六年！村上春树2021全新短篇小说集！\n继小说集《没有女人的男人们》6年之后！继长篇小说《刺杀骑士团长》3年之后村上春树正式小说作品！\n同名篇章 《第一人称单数》，在春夜满月里的酒吧中发生了一段质疑自我的邂逅，故事结束了却余韵未了，彷佛跌入晦暗的酒吧空间，以小说开启一个不眠的微醺之夜。', 'db37e0c3-9f24-4f19-ae7a-b7062dd8a314.jpg');
INSERT INTO `book_info` VALUES ('46aea0638bf4df7bec34517145d3ca6e', '48372d3fbe1fdd5eb91e8294f4e7da19', '3123131', '2021-12-20 20:42:48', '1234qwerqwqqeqeqeqe3213213', '1212121', 'e5a91290-8799-41f7-9736-5e30816fc451.jpg');
INSERT INTO `book_info` VALUES ('5201f6c5c6d170a843630432061b06ad', '106772e78b2a126464117bcc2acb8359', '钱钟书', '2021-12-20 15:19:15', '1234qwerqwqqeqeqeqe3213213', '《围城》是钱钟书“锱铢积累”而成的唯一的长篇小说，全书以讽刺为基调，以主人公方鸿渐的生活道路为主线，反映了那个时代某些知识分子（主要是部分欧美留学生、大学教授，等等）生活和心理的变迁沉浮。小说可以分为三个部分：第一部分 写在上海的情景，以方鸿渐和苏文纨的“爱情”纠葛为主要内容，那些吃饭斗嘴、争风吃醋的社交场合，充满了浓厚的调侃意味；第二部分 主要写三闾大学的明争暗斗，各种各样的学者文士粉墨登场，演出了一幕幕可悲可耻的讽刺喜剧，辛辣的讽刺味愈加浓烈；第三部分 写方鸿渐和孙柔嘉婚后又回到上海，往日的朋友或冤家都已星散，夫妇俩为琐碎之事终日争吵，更多的是谋生的艰难了。在整个讽刺基调不变的前提下，各个部分的侧重点略有不同。', 'af262291-f779-4cd8-8f94-689ed57239be.jpg');
INSERT INTO `book_info` VALUES ('68365eba6aea62eb9212c6b4624b37e8', '8849fdb0afe01611be779289d1a0ecdd', '丹尼尔·凯斯', '2021-12-20 15:40:51', '1234qwerqwqqeqeqeqe3213213', '很多人都笑我。但他们是我的朋友我们都很快乐。\n声称能改造智能的科学实验在白老鼠阿尔吉侬身上获得了突破性的进展，下一步急需进行人体实验。个性和善、学习态度积极的心智障碍者查理·高登成为最佳人选。手术成功后，查理的智商从68跃升为185，然而那些从未有过的情绪和记忆也逐渐浮现。', '3fb3d143-55c8-487c-b182-cf04ffa64c8d.jpg');
INSERT INTO `book_info` VALUES ('6b9c443e59e55079d8649eeddb7dd1ac', '2f6ea4693e3dbdfa9ae777e1c23f2da2', '林奕含', '2021-12-20 15:38:23', '1234qwerqwqqeqeqeqe3213213', '令人心碎却无能为力的真实故事。\n痛苦的际遇是如此难以分享，好险这个世界还有文学。\n思琪的初恋是李老师。因为李老师把她翻面，把他的东西塞进去。那年的教师节思琪才十三岁，这个世界和她原本认识的不一样。 如果这是爱情，为什么觉得暴力？为什么觉得被折断？为什么老师要一个女学生换过一个女学生？如果这不是爱情，那满口学问的李老师怎么能做了以后，还这么自信、无疑、无愧于心？\n故事必须重新讲过，与房思琪情同双胞的刘怡婷，接到警局通知，去带回神志不清，被判定疯了的房思琪。透过思琪的日记，怡婷得知思琪五年中的所见所思……', '2425ff26-659b-488c-b941-65d6b4738fad.jpg');
INSERT INTO `book_info` VALUES ('722e2b191847d7e131d06dac8279b4b5', '123qwe', '杨本芬', '2021-12-20 15:34:19', '1234qwerqwqqeqeqeqe3213213', '《浮木》是一部根据真实事件改写的作品，是《秋园》的续集。一位八旬奶奶讲述她和妈妈，以及中南腹地那些家人、亲戚、乡亲们的故事，在那样的年代里，人们像水中的浮木般起起伏伏、随波逐流、挣扎求生，他们的命运在大时代中载浮载沉、漂泊无向，有的从此破碎，有的尚有一线生机。文字中涉及的人物，其生命多数不复存在，恰如泡影破灭于水面，闪电消失于天空。本文通过回忆，讲述了中国人民生生不息的坚韧与美好，这一生，像是一颗露珠的记忆，微小，短暂。但在露珠破灭之前，那也是闪耀着晶亮光芒的，是一个完整的宇宙。', '50cff23d-9c7d-4841-9f13-f580320467ac.jpg');
INSERT INTO `book_info` VALUES ('7aee27944fd4c637e818807fba72944e', 'f513521248156bba662aec050c7759a0', '余华', '2021-12-20 15:36:58', '1234qwerqwqqeqeqeqe3213213', '《在细雨中呼喊》是二十世纪九十年代的经典文本，是余华的第一部长篇力作。小说描述了一位江南少年的成长经历和心灵历程。《在细雨中呼喊》的结构来自于对时间的感受，确切地说是对记忆中的时间的感受，叙述者 天马行空地在过去、现在和将来这三个时间维度里自由穿行，将忆记的碎片 穿插、结集、拼嵌完整。作者因本书荣获法国文学和艺术骑士勋章等奖。', '1940e300-e16d-4485-b1a6-57f7f0ac1bfb.jpg');
INSERT INTO `book_info` VALUES ('b6023907ee0fb6ea4dfa778cf8aa9952', 'd3750781750b4266c3f5315d1cf8e694', '老舍', '2021-12-20 15:35:19', '1234qwerqwqqeqeqeqe3213213', '飞机坠毁在火星，“我”误入火星上最古的国“猫城”，结识了形形色色的猫人：身兼大地主、政客、诗人、军官的大蝎，世事洞明而敷衍的小蝎，只抢迷叶与妇女的猫兵，守着八个小妾的公使太太，杀人不犯法的外国人，打老师的学生，卖文物的学者，起哄为业的党棍，抢着投降的军阀……一声炮响，繁华落尽。', 'bdebd467-4cb1-4a6c-a9ff-71750cb3d83b.jpg');

-- ----------------------------
-- Table structure for book_sale
-- ----------------------------
DROP TABLE IF EXISTS `book_sale`;
CREATE TABLE `book_sale`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `discount` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_sale
-- ----------------------------

-- ----------------------------
-- Table structure for dic
-- ----------------------------
DROP TABLE IF EXISTS `dic`;
CREATE TABLE `dic`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid主键',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典name',
  `value` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典value',
  `dictype_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属字典类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dic
-- ----------------------------
INSERT INTO `dic` VALUES ('0293fd31d80f343713be831e28b8b6fb', '新增', 'add', '53e0863faf5d42cb6fe2e9d96e57c170');
INSERT INTO `dic` VALUES ('057989f3041d26f751b29bbc1bbb2f7e', '未支付', '0', '4ad20dc7139cc0ad8d41d15999fd9140');
INSERT INTO `dic` VALUES ('0a7e8d2c8a65d2803ca728acfa6fa7df', '已支付', '1', '4ad20dc7139cc0ad8d41d15999fd9140');
INSERT INTO `dic` VALUES ('35def631f8aa64570e800a5c88ba7c85', '保密', '2', '743afcde11a54141583006b469a3e7ac');
INSERT INTO `dic` VALUES ('5a6fd0eaf6d54eff69e843ab2cf9060d', '是否可用', 'useful', '53e0863faf5d42cb6fe2e9d96e57c170');
INSERT INTO `dic` VALUES ('7ffcb09eee6dd07bc852738a0af0466d', '可用', '1', '743afcde11a54141583006b469a3e7ac');
INSERT INTO `dic` VALUES ('fe88d781754b3524ca172b73dc8b5918', '不可用', '0', '743afcde11a54141583006b469a3e7ac');

-- ----------------------------
-- Table structure for dictype
-- ----------------------------
DROP TABLE IF EXISTS `dictype`;
CREATE TABLE `dictype`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid主键',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型name',
  `value` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型value',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictype
-- ----------------------------
INSERT INTO `dictype` VALUES ('4ad20dc7139cc0ad8d41d15999fd9140', '订单状态', 'status');
INSERT INTO `dictype` VALUES ('53e0863faf5d42cb6fe2e9d96e57c170', '按钮菜单', 'button');
INSERT INTO `dictype` VALUES ('743afcde11a54141583006b469a3e7ac', '状态', 'useful');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单链接，父菜单则为空',
  `parent_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父菜单id，没有父菜单则为-1',
  `sort` int NULL DEFAULT NULL COMMENT '菜单序号',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单描述',
  `level` int NULL DEFAULT NULL COMMENT '层级，1-顶级菜单  2-子菜单  3-CRUD操作',
  `button_type` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联的按钮类型，就是dictype中button对应的dic的id',
  `component` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面组件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('01f03e4a0a97d68897d382a27d055cb8', '用户角色菜单控制', '', '-1', 4, '用户角色菜单控制', 1, '', 'manage_view/layout/basic');
INSERT INTO `menu` VALUES ('05f4a792ca2bee89b5e3fd64e05b74e7', '录入信息', '/bookInfo', '4e70db6f0d792e06f2f439f0af082c30', 2, '录入书籍信息', 2, '', 'manage_view/urm/bookInfo/BookInfo');
INSERT INTO `menu` VALUES ('1efdf598f811717b6255c81ffdcfbce7', '收藏', '', '-1', 1, '收藏', 1, '', 'store_view/StoreIndex');
INSERT INTO `menu` VALUES ('2179d98eb28fbd00551444608ce46ffe', '基础信息管理', '', '-1', 2, '基础信息管理', 1, '', 'manage_view/layout/basic');
INSERT INTO `menu` VALUES ('2d2441c776570ff66a704c4a2ef974c0', '字典类型管理', '/myDic', '2179d98eb28fbd00551444608ce46ffe', 1, '字典类型管理', 2, '', 'manage_view/urm/dic/MyDic');
INSERT INTO `menu` VALUES ('35562286c08fefd7aa41099714b21b4e', '首页', '/Store', 'f4c4340282993b7e721b30eb06297bc7', 0, '首页', 2, '', 'store_view/basic/firstPage');
INSERT INTO `menu` VALUES ('4e70db6f0d792e06f2f439f0af082c30', '书籍管理', '', '-1', 3, '书籍管理', 1, '', 'manage_view/layout/basic');
INSERT INTO `menu` VALUES ('51625cdd8c3db579982fbefef9949228', '角色管理', '/MyRole', '01f03e4a0a97d68897d382a27d055cb8', 2, '角色管理', 2, '', 'manage_view/urm/role/MyRole');
INSERT INTO `menu` VALUES ('5608c0235ea8eaf4b5747c1a2e4819b2', 'Home', '', '-1', 1, '后台管理首页', 1, '', 'manage_view/layout/basic');
INSERT INTO `menu` VALUES ('5c98c27448f96f0f02c77c0a6895e10a', '特价设置', '/mySale', '4e70db6f0d792e06f2f439f0af082c30', 3, '特价设置', 2, '', 'manage_view/urm/book/MySale');
INSERT INTO `menu` VALUES ('9cd5a82daf7c8e66a34048d0b2e1e601', '关于我们', '/home', '5608c0235ea8eaf4b5747c1a2e4819b2', 1, 'home', 2, '', 'manage_view/Home');
INSERT INTO `menu` VALUES ('af5f3b1b32f87334ddf7f52a028bc410', '是否可用', '', 'e362753994e920fc1b07335d02f433de', 2, '', 3, 'myuseful', '');
INSERT INTO `menu` VALUES ('c29d1437b0bcd989748f609bbfb6d21e', 'Home', '', '3694d0c785e7614da98a4b7cfdcf69f0', 0, 'home', 2, '', '');
INSERT INTO `menu` VALUES ('c6dfb569322e6e160f0261247c9e8e53', '书籍设置', '/myBook', '4e70db6f0d792e06f2f439f0af082c30', 1, '书籍管理', 2, '', 'manage_view/urm/book/MyBook');
INSERT INTO `menu` VALUES ('d62ce3021c90196bad461423e356de40', '我的收藏', '/collect', '1efdf598f811717b6255c81ffdcfbce7', 0, '我的收藏', 2, '', 'store_view/collect/MyCollect');
INSERT INTO `menu` VALUES ('dcd63a46d5b5f36aaf64353d50066c40', '增加', '', 'e362753994e920fc1b07335d02f433de', 1, '', 3, 'add', '');
INSERT INTO `menu` VALUES ('e362753994e920fc1b07335d02f433de', '用户管理', '/myUser', '01f03e4a0a97d68897d382a27d055cb8', 1, '用户管理', 2, '', 'manage_view/urm/user/MyUser');
INSERT INTO `menu` VALUES ('e83c60010566c8d5ff95711595df12e5', '菜单管理', '/MyMenu', '01f03e4a0a97d68897d382a27d055cb8', 3, '菜单管理', 2, '', 'manage_view/urm/menu/MyMenu');
INSERT INTO `menu` VALUES ('f4c4340282993b7e721b30eb06297bc7', 'Store', '/Store', '-1', 0, '商店页面', 1, '', 'store_view/StoreIndex');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid主键',
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `create_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `useful` tinyint(1) NULL DEFAULT NULL COMMENT '是否可用 0- 不可用 1-可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('47edd60516484c433ac2ba1ce5d1524c', '普通管理员', '2021-11-09 15:47:21', 1);
INSERT INTO `role` VALUES ('55192eb8d4544561b875af8940155220', '普通顾客', '2021-12-14 11:11:55', 1);
INSERT INTO `role` VALUES ('b5b78b3b52b92f4761c037e7d3aafdce', '超级管理员', '2021-11-09 15:47:12', 1);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid主键',
  `menu_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属menu',
  `role_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属role',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('032fb63f8ee5a1cb09d30f879c1b4d24', '01f03e4a0a97d68897d382a27d055cb8', '47edd60516484c433ac2ba1ce5d1524c');
INSERT INTO `role_menu` VALUES ('0657adcd069990baafff3897187408e3', 'dcd63a46d5b5f36aaf64353d50066c40', '47edd60516484c433ac2ba1ce5d1524c');
INSERT INTO `role_menu` VALUES ('07dbab6e053675fec36fdbfe0e93c776', '35562286c08fefd7aa41099714b21b4e', '55192eb8d4544561b875af8940155220');
INSERT INTO `role_menu` VALUES ('0f0bdea2eccda9fe13e85df29c2aac3b', 'f4c4340282993b7e721b30eb06297bc7', '55192eb8d4544561b875af8940155220');
INSERT INTO `role_menu` VALUES ('16febfed96872ceb570c301db371f26e', 'af5f3b1b32f87334ddf7f52a028bc410', '47edd60516484c433ac2ba1ce5d1524c');
INSERT INTO `role_menu` VALUES ('17912e2320675aabce02c34f2c157617', 'e362753994e920fc1b07335d02f433de', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `role_menu` VALUES ('1ae89e9d7be3b64fc8874447fa24e766', '4e70db6f0d792e06f2f439f0af082c30', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `role_menu` VALUES ('2097f98e424c2c1a4e1d49d104a8dcd3', '05f4a792ca2bee89b5e3fd64e05b74e7', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `role_menu` VALUES ('23118bee94e107c12d4d26e0374582f1', '2d2441c776570ff66a704c4a2ef974c0', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `role_menu` VALUES ('30c4a61a5a44687fde93539bd2fd3af9', 'e83c60010566c8d5ff95711595df12e5', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `role_menu` VALUES ('6374b60e0276a1f7b35ab50342b063f3', '5c98c27448f96f0f02c77c0a6895e10a', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `role_menu` VALUES ('6812a9f11031a8931a4c1926d81b6a8f', 'c6dfb569322e6e160f0261247c9e8e53', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `role_menu` VALUES ('69bb76fc1af0ce389917a444a8f1a9d7', 'af5f3b1b32f87334ddf7f52a028bc410', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `role_menu` VALUES ('6a65a7f5c73bfebaae47256898d559b9', 'e362753994e920fc1b07335d02f433de', '47edd60516484c433ac2ba1ce5d1524c');
INSERT INTO `role_menu` VALUES ('746ad713ea170f605809dfb58b5f449c', 'dcd63a46d5b5f36aaf64353d50066c40', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `role_menu` VALUES ('82f9ffb0491629959cd7a524a5a3ef92', '9cd5a82daf7c8e66a34048d0b2e1e601', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `role_menu` VALUES ('879f6200b3ffca106ecc7e22f5282499', '4e70db6f0d792e06f2f439f0af082c30', '47edd60516484c433ac2ba1ce5d1524c');
INSERT INTO `role_menu` VALUES ('90ef2e8c410db5f881c238aff228c625', 'c6dfb569322e6e160f0261247c9e8e53', '47edd60516484c433ac2ba1ce5d1524c');
INSERT INTO `role_menu` VALUES ('9476040dd58182eb97d0a7d5ed8ff94a', '01f03e4a0a97d68897d382a27d055cb8', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `role_menu` VALUES ('d34b8f00abe185bf8d24b7418ca62aca', '5608c0235ea8eaf4b5747c1a2e4819b2', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `role_menu` VALUES ('da046e6439ea1afef5525ce394aa0a10', '2179d98eb28fbd00551444608ce46ffe', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `role_menu` VALUES ('e1c217ae484203df533bf5efef5b97e7', '05f4a792ca2bee89b5e3fd64e05b74e7', '47edd60516484c433ac2ba1ce5d1524c');
INSERT INTO `role_menu` VALUES ('e20a39f0090bef7ee64e1c8b35106682', '5c98c27448f96f0f02c77c0a6895e10a', '47edd60516484c433ac2ba1ce5d1524c');
INSERT INTO `role_menu` VALUES ('e32458a6f517125eae1abc99a19bb182', '1efdf598f811717b6255c81ffdcfbce7', '55192eb8d4544561b875af8940155220');
INSERT INTO `role_menu` VALUES ('e63117ed0e1c484f2d337e714e54f2fc', 'd62ce3021c90196bad461423e356de40', '55192eb8d4544561b875af8940155220');
INSERT INTO `role_menu` VALUES ('e7ee86e246228ee341e76b2d7a6acfb4', '51625cdd8c3db579982fbefef9949228', 'b5b78b3b52b92f4761c037e7d3aafdce');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid主键',
  `username` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `nickname` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `useful` tinyint(1) NULL DEFAULT NULL COMMENT '是否可用 0- 不可用 1-可用',
  `create_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_username_uindex`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0855a674f56de4ef02292613c6d8948c', '916803397', '64e0e5f45c5df8592e19cc155bb0c434', '15624245-603d-4d84-b2f2-062e0bad6577', 'NoFat', 1, '2021-12-14 10:53:37', NULL);
INSERT INTO `user` VALUES ('1234qwerqwqqeqeqeqe3213213', 'admin', 'b251d195daad7862043843d25052e2a8', 'df361755-3068-4ba5-ab22-12cc2ab7c88e', NULL, 1, '2021-10-27 10:44:20', NULL);
INSERT INTO `user` VALUES ('1325a8963fef8f66cd8c1720233aad6b', 'nofat', '667740e80227bfb81f54c01a1fcfdb73', '1cad6c3c-4805-4f7a-9644-f1932b79736b', 'nofat', 1, '2021-12-14 20:24:34', NULL);
INSERT INTO `user` VALUES ('a3428b41a2fef857a2c85c7f55a9e15d', 'lmc', '1d5adf9b3f1f50d5e63dcfcb1fb51b40', 'cba47a76-8d80-418a-98be-70b52248c9da', 'lmc', 1, '2021-12-14 20:40:28', NULL);

-- ----------------------------
-- Table structure for user_avatar
-- ----------------------------
DROP TABLE IF EXISTS `user_avatar`;
CREATE TABLE `user_avatar`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imgUrl` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_avatar
-- ----------------------------

-- ----------------------------
-- Table structure for user_collect
-- ----------------------------
DROP TABLE IF EXISTS `user_collect`;
CREATE TABLE `user_collect`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `book_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_collect
-- ----------------------------
INSERT INTO `user_collect` VALUES ('0a26dc8f60b3a806ec0c4bc818fc228b', 'a3428b41a2fef857a2c85c7f55a9e15d', '106772e78b2a126464117bcc2acb8359');
INSERT INTO `user_collect` VALUES ('d07d77ee0c54ec478240e3c907167d38', 'a3428b41a2fef857a2c85c7f55a9e15d', 'd3750781750b4266c3f5315d1cf8e694');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid主键',
  `user_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属user',
  `role_id` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属role',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('441a52aa2cb5287a9f0beec99bce61f3', '03a36779f5e79702dd5c10cc7084dc73', '47edd60516484c433ac2ba1ce5d1524c');
INSERT INTO `user_role` VALUES ('c0d1dd79cbfb26107d124952d7db3b42', 'a3428b41a2fef857a2c85c7f55a9e15d', '55192eb8d4544561b875af8940155220');
INSERT INTO `user_role` VALUES ('eeb1efe09c896c56cae5c56d4e4bd023', '1234qwerqwqqeqeqeqe3213213', 'b5b78b3b52b92f4761c037e7d3aafdce');
INSERT INTO `user_role` VALUES ('f2826f9b67421a91dea518b8ffffc67c', 'a63a77ed96839dfbd9af3f4b5b435803', '47edd60516484c433ac2ba1ce5d1524c');
INSERT INTO `user_role` VALUES ('f89f659a72d696706f6953d0f7da01ce', '0855a674f56de4ef02292613c6d8948c', '47edd60516484c433ac2ba1ce5d1524c');
INSERT INTO `user_role` VALUES ('fcac345b4d6dd63748544eb7e4221c51', '1325a8963fef8f66cd8c1720233aad6b', '55192eb8d4544561b875af8940155220');

SET FOREIGN_KEY_CHECKS = 1;

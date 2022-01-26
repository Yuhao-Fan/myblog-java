
CREATE DATABASE `myblog`;

USE `myblog`;



DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `content` LONGTEXT,
  `created` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `likenum` INT(8) NOT NULL DEFAULT '0',
  `dislikenum` INT(8) NOT NULL DEFAULT '0',
  `collectnum` INT(8) NOT NULL DEFAULT '0',
  `sharenum` INT(8) NOT NULL DEFAULT '0',
  `status` TINYINT(4) DEFAULT NULL,
  `last_edit` DATETIME DEFAULT NULL,
  `tags` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



INSERT  INTO `blog`(`id`,`user_id`,`title`,`description`,`content`,`created`,`likenum`,`dislikenum`,`collectnum`,`sharenum`,`status`,`last_edit`,`tags`) VALUES 
(0,2,'阿萨德','1111231111',' #Sample blog post #### April 1, 2020 by [Olivier](/)This blog post shows a few different types of content that are supported and styled with','2021-03-28 16:37:49',0,0,0,0,0,'2021-04-02 14:09:28',NULL),
(2,1,'最值得学习的博客项目eblog','eblog是一个基于Springboot2.1.2开发的博客学习项目，为了让项目融合更多的知识点，达到学习目的，编写了详细的从0到1开发文档。主要学习包括：自定义Freemarker标签，使用shiro+redis完成了会话共享，redis的zset结构完成本周热议排行榜，t-io+websocket完成即时消息通知和群聊，rabbitmq+elasticsearch完成博客内容搜索引擎等。值得学习的地方很多！','**推荐阅读：**\r\n\r\n[分享一套SpringBoot开发博客系统源码，以及完整开发文档！速度保存！](https://mp.weixin.qq.com/s/jz6e977xP-OyaAKNjNca8w)\r\n\r\n[Github上最值得学习的100个Java开源项目，涵盖各种技术栈！](https://mp.weixin.qq.com/s/N-U0TaEUXnBFfBsmt_OESQ)\r\n\r\n[2020年最新的常问企业面试题大全以及答案](https://mp.weixin.qq.com/s/lR5LC5GnD2Gs59ecV5R0XA)','2021-04-02 14:04:49',7,10,0,0,1,'2021-04-02 14:04:48',NULL),
(7,1,'你真的会写单例模式吗?','单例模式可能是代码最少的模式了，但是少不一定意味着简单，想要用好、用对单例模式，还真得费一番脑筋。本文对 Java 中常见的单例模式写法做了一个总结，如有错漏之处，恳请读者指正。','> 作者：吃桔子的攻城狮 来源：http://www.tekbroaden.com/singleton-java.html\n\n\n单例模式可能是代码最少的模式了，但是少不一定意味着简单，想要用好、用对单例模式，还真得费一番脑筋。本文对 Java 中常见的单例模式写法做了一个总结，如有错漏之处，恳请读者指正。\n\n饿汉法\n===\n\n顾名思义，饿汉法就是在第一次引用该类的时候就创建对象实例，而不管实际是否需要创建。代码如下：\n\n```\npublic class Singleton {  \n    private static Singleton = new Singleton();\n    private Singleton() {}\n    public static getSignleton(){\n        return singleton;\n    }\n}\n\n```\n\n这样做的好处是编写简单，但是无法做到延迟创建对象。但是我们很多时候都希望对象可以尽可能地延迟加载，从而减小负载，所以就需要下面的懒汉法：\n','2021-04-02 14:04:52',-1,0,0,0,0,'2021-04-02 14:04:50',NULL),
(9,1,'真正理解Mysql的四种隔离级别@','事务是应用程序中一系列严密的操作，所有操作必须成功完成，否则在每个操作中所作的所有更改都会被撤消。也就是事务具有原子性，一个事务中的一系列的操作要么全部成功，要么一个都不做。\n\n事务的结束有两种，当事务中的所以步骤全部成功执行时，事务提交。如果其中一个步骤失败，将发生回滚操作，撤消撤消之前到事务开始时的所以操作。','### 什么是事务  \n\n> 事务是应用程序中一系列严密的操作，所有操作必须成功完成，否则在每个操作中所作的所有更改都会被撤消。也就是事务具有原子性，一个事务中的一系列的操作要么全部成功，要么一个都不做。\n> \n> 事务的结束有两种，当事务中的所以步骤全部成功执行时，事务提交。如果其中一个步骤失败，将发生回滚操作，撤消撤消之前到事务开始时的所以操作。\n\n**事务的 ACID**\n\n事务具有四个特征：原子性（ Atomicity ）、一致性（ Consistency ）、隔离性（ Isolation ）和持续性（ Durability ）。这四个特性简称为 ACID 特性。\n\n> 1 、原子性。事务是数据库的逻辑工作单位，事务中包含的各操作要么都做，要么都不做\n> \n> 2 、一致性。事 务执行的结果必须是使数据库从一个一致性状态变到另一个一致性状态。因此当数据库只包含成功事务提交的结果时，就说数据库处于一致性状态。如果数据库系统 运行中发生故障，有些事务尚未完成就被迫中断，这些未完成事务对数据库所做的修改有一部分已写入物理数据库，这时数据库就处于一种不正确的状态，或者说是 不一致的状态。','2021-04-02 14:04:54',-1,1,0,0,0,'2021-04-02 14:04:53',NULL),
(22,1,'title','description','```\n# Sample blog post\n\n#### April 1, 2020 by [Olivier](/)\n\nThis blog post shows a few different types of content that are supported and styled with\nMaterial styles. Basic typography, images, and code are all supported.\nYou can extend these by modifying `Markdown.js`.\n\nCum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.\nAenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum.\nSed posuere consectetur est at lobortis. Cras mattis consectetur purus sit amet fermentum.\n\nCurabitur blandit tempus porttitor. **Nullam quis risus eget urna mollis** ornare vel eu leo.\nNullam id dolor id nibh ultricies vehicula ut id elit.\n\nEtiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum.\nAenean lacinia bibendum nulla sed consectetur.\n\n## Heading\n\nVivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor.\nDuis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.\nMorbi leo risus, porta ac consectetur ac, vestibulum at eros.\n\n### Sub-heading\n\nCum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.\n\n### Sub-heading\n\nCum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.\nAenean lacinia bibendum nulla sed consectetur. Etiam porta sem malesuada magna mollis euismod.\nFusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo\nsit amet risus.\n\n- Praesent commodo cursus magna, vel scelerisque nisl consectetur et.\n- Donec id elit non mi porta gravida at eget metus.\n- Nulla vitae elit libero, a pharetra augue.\n\nDonec ullamcorper nulla non metus auctor fringilla. Nulla vitae elit libero, a pharetra augue.\n\n1.  Vestibulum id ligula porta felis euismod semper.\n2.  Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.\n3.  Maecenas sed diam eget risus varius blandit sit amet non magna.\n\nCras mattis consectetur purus sit amet fermentum. Sed posuere consectetur est at lobortis.\n\n```\n','2021-04-02 14:04:56',0,0,0,0,0,'2021-04-02 14:04:55',NULL),
(23,1,'title','description','This is a success message!\n','2021-04-02 14:05:00',0,0,0,0,0,'2021-04-02 14:04:59',NULL),
(43,2,'达到','asfafgasg','Hello, Vditor + React!\n','2021-04-02 14:04:58',0,0,0,0,0,'2021-04-02 14:04:57',NULL),
(44,0,'重要','学习曲线','1. 看了ReactNative的慕课网视频\n2. 使用RN写了一个网约车应用\n3. 写了一遍React官方文档的小游戏教程\n4. 修改metrial ui 的模板\n5. 网络上查找react的写法\n','2021-04-02 14:09:28',0,0,0,0,0,'2021-04-02 13:52:03',NULL),
(50,2,'福娃第三方第三方','1111231111',' #Sample blog post #### April 1, 2020 by [Olivier](/)This blog post shows a few different types of content that are supported and styled with','2021-04-02 14:05:02',0,0,0,0,0,'2021-04-02 14:05:01',NULL),
(52,2,'阿萨德','1111231111',' #Sample blog post #### April 1, 2020 by [Olivier](/)This blog post shows a few different types of content that are supported and styled with','2021-04-02 14:05:04',0,0,0,0,0,'2021-04-02 14:05:03',NULL),
(53,2,'阿斯顿发','1111231111',' #Sample blog post #### April 1, 2020 by [Olivier](/)This blog post shows a few different types of content that are supported and styled with','2021-04-02 14:05:06',0,0,0,0,0,'2021-04-02 14:05:05',NULL),
(54,2,'富士达','1111231111',' #Sample blog post #### April 1, 2020 by [Olivier](/)This blog post shows a few different types of content that are supported and styled with','2021-04-02 14:05:10',0,0,0,0,0,'2021-04-02 14:05:07',NULL),
(80,0,'异或','leetcode136','[https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/er-cha-shu-de-zui-da-shen-du-by-leetcode-solution/](https://)\n\n---\n\n```int\nif (nums.length > 1) {\n   for (int i = 1; i < nums.length; i++) {\n      ans = ans ^ nums[i];\n   }\n }\n return ans;\n```\n\n| col1 | col2 | col3 |\n| ---- | ---- | ---- |\n|      |      |      |\n|      |      |      |\r\n\r\n\n','2021-04-02 14:02:40',0,0,0,0,0,'2021-04-02 14:01:30',NULL),
(81,0,'多值属性','二进制转十进制','https://blog.csdn.net/weixin_28957683/article/details/113213095\n\n目前据我所知单个字段无法储存数组\n\n1、二进制转十进制\n\n如果这个多值非常多，例如说有一万个tag，那么这个十进制将会变得非常巨大。只适用于非常少的时候\n\n2、可以使用String来储存，stringbuffer来构造\n\n写成“1，2，3，5，10”这个样子\n\n3、或者添加一张表用来记录blogId好tagId的对应关系\n','2021-04-02 15:18:05',-1,0,0,0,0,'2021-04-02 15:18:05',NULL);



DROP TABLE IF EXISTS `blog_action`;

CREATE TABLE `blog_action` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL,
  `blog_id` BIGINT(20) NOT NULL,
  `action_mode` INT(8) NOT NULL DEFAULT '0',
  `is_like` TINYINT(1) NOT NULL DEFAULT '0',
  `is_dislike` TINYINT(1) NOT NULL DEFAULT '0',
  `is_collection` TINYINT(1) NOT NULL DEFAULT '0',
  `is_share` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



INSERT  INTO `blog_action`(`id`,`user_id`,`blog_id`,`action_mode`,`is_like`,`is_dislike`,`is_collection`,`is_share`) VALUES 
(1,2,71,0,0,0,0,0),
(2,2,3,0,0,0,0,0),
(3,2,7,0,1,0,0,0),
(4,2,2,0,0,0,0,0),
(5,2,68,0,0,0,0,0),
(6,2,69,0,0,0,0,0),
(7,3,9,0,1,0,0,0),
(8,3,7,0,1,0,0,0),
(9,3,69,0,0,0,0,0),
(10,2,0,0,0,0,0,0),
(11,2,79,0,1,0,1,1),
(12,0,81,0,1,0,0,0);



DROP TABLE IF EXISTS `blog_tag`;

CREATE TABLE `blog_tag` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `avatar` VARCHAR(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `color` VARCHAR(16) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



DROP TABLE IF EXISTS `blogid_tagid`;

CREATE TABLE `blogid_tagid` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `blog_id` BIGINT(20) DEFAULT NULL,
  `tag_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `blog_id` BIGINT(20) NOT NULL,
  `user_id` BIGINT(20) NOT NULL,
  `content` LONGTEXT,
  `created` DATETIME NOT NULL,
  `status` TINYINT(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT  INTO `comment`(`id`,`blog_id`,`user_id`,`content`,`created`,`status`) VALUES 
(2,50,1,'**推荐阅读：**\r\n\r\n[分享一套SpringBoot开发博客系统源码，以及完整开发文档！速度保存！](https://mp.weixin.qq.com/s/jz6e977xP-OyaAKNjNca8w)\r\n\r\n[Github上最值得学习的100个Java开源项目，涵盖各种技术栈！](https://mp.weixin.qq.com/s/N-U0TaEUXnBFfBsmt_OESQ)\r\n\r\n[2020年最新的常问企业面试题大全以及答案](https://mp.weixin.qq.com/s/lR5LC5GnD2Gs59ecV5R0XA)','2020-05-28 09:36:38',0),
(3,50,1,'### 工具获取\r\n\r\n* xshell 6 绿色破解版：关注公众号：JavaCat，回复 xshell 获取\r\n* Navicat 11 简体中文版：关注公众号：JavaCat，回复 navicat 获取\r\n\r\n公众号二维码：\r\n\r\n![JavaCat](//image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20201020/7fa16a1f957f4cfebe7be1f6675f6f36.png \"JavaCat\")\r\n\r\n直接扫码回复对应关键字\r\n\r\n**推荐阅读：**\r\n\r\n[B站86K播放量，SpringBoot+Vue前后端分离完整入门教程！](https://mp.weixin.qq.com/s/jGEkHTf2X8l-wUenc-PpEw)\r\n\r\n[分享一套SpringBoot开发博客系统源码，以及完整开发文档！速度保存！](https://mp.weixin.qq.com/s/jz6e977xP-OyaAKNjNca8w)\r\n\r\n[Github上最值得学习的100个Java开源项目，涵盖各种技术栈！](https://mp.weixin.qq.com/s/N-U0TaEUXnBFfBsmt_OESQ)\r\n\r\n[2020年最新的常问企业面试题大全以及答案](https://mp.weixin.qq.com/s/lR5LC5GnD2Gs59ecV5R0XA)','2020-10-20 05:05:31',0),
(7,50,1,'> 作者：吃桔子的攻城狮 来源：http://www.tekbroaden.com/singleton-java.html\n\n\n单例模式可能是代码最少的模式了，但是少不一定意味着简单，想要用好、用对单例模式，还真得费一番脑筋。本文对 Java 中常见的单例模式写法做了一个总结，如有错漏之处，恳请读者指正。\n\n饿汉法\n===\n\n顾名思义，饿汉法就是在第一次引用该类的时候就创建对象实例，而不管实际是否需要创建。代码如下：\n\n```\npublic class Singleton {  \n    private static Singleton = new Singleton();\n    private Singleton() {}\n    public static getSignleton(){\n        return singleton;\n    }\n}\n\n```\n\n这样做的好处是编写简单，但是无法做到延迟创建对象。但是我们很多时候都希望对象可以尽可能地延迟加载，从而减小负载，所以就需要下面的懒汉法：\n','2020-05-22 00:42:44',0),
(9,50,1,'### 什么是事务  \n\n> 事务是应用程序中一系列严密的操作，所有操作必须成功完成，否则在每个操作中所作的所有更改都会被撤消。也就是事务具有原子性，一个事务中的一系列的操作要么全部成功，要么一个都不做。\n> \n> 事务的结束有两种，当事务中的所以步骤全部成功执行时，事务提交。如果其中一个步骤失败，将发生回滚操作，撤消撤消之前到事务开始时的所以操作。\n\n**事务的 ACID**\n\n事务具有四个特征：原子性（ Atomicity ）、一致性（ Consistency ）、隔离性（ Isolation ）和持续性（ Durability ）。这四个特性简称为 ACID 特性。\n\n> 1 、原子性。事务是数据库的逻辑工作单位，事务中包含的各操作要么都做，要么都不做\n> \n> 2 、一致性。事 务执行的结果必须是使数据库从一个一致性状态变到另一个一致性状态。因此当数据库只包含成功事务提交的结果时，就说数据库处于一致性状态。如果数据库系统 运行中发生故障，有些事务尚未完成就被迫中断，这些未完成事务对数据库所做的修改有一部分已写入物理数据库，这时数据库就处于一种不正确的状态，或者说是 不一致的状态。','2020-05-22 22:04:46',0),
(10,50,1,'ok，再回到我们的eblog项目，源码、文档、视频我都开源出来了。来些基本操作：github上给个star，B站视频给个三连支持咧。\n\neblog源码：https://github.com/MarkerHub/eblog\n\n点击这里：[10+篇完整开发文档](https://mp.weixin.qq.com/mp/homepage?__biz=MzIwODkzOTc1MQ==&hid=1&sn=8e512316c3dfe140e636d0c996951166)\n\n![](//image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20200508/c290d945b7d24c79b172759bdb5b94e0.png)\n\n视频讲解：（记得关注我噢！）\n\nhttps://www.bilibili.com/video/BV1ri4y1x71A\n\n![](//image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/20200508/983b5abc1c934360a1a1362347a275f7.png)\n\n项目其实还很多bug的，哈哈，我还需要进行二次迭代，到时候再发迭代文档出来。\n\n关注下我的B站，作为一个自媒体的自由职业者，没有什么比涨粉更让我开心的了，嘻嘻。\n\n近期即将推出的视频教程：\n\n1. 搭建脚手架，前后端分离首秀\n2. Shiro入门到精通教程\n3. SpringBoot2.2.6最新入门教程','2020-05-22 22:05:49',0),
(15,50,1,'content-233333','2021-02-05 21:40:33',0),
(16,49,1,'1','2021-02-23 21:36:28',0),
(19,49,1,'asdasd\n','2021-02-24 22:57:09',0),
(20,49,1,'# jhmkgh\n\n~~wqeqwe~~\n','2021-02-24 23:02:43',0),
(21,48,1,'# jhmkgh\n\n~~wqeqwe~~\n','2021-02-24 23:02:43',0),
(22,48,1,'```\n# Sample blog post\n\n#### April 1, 2020 by [Olivier](/)\n\nThis blog post shows a few different types of content that are supported and styled with\nMaterial styles. Basic typography, images, and code are all supported.\nYou can extend these by modifying `Markdown.js`.\n\nCum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.\nAenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum.\nSed posuere consectetur est at lobortis. Cras mattis consectetur purus sit amet fermentum.\n\nCurabitur blandit tempus porttitor. **Nullam quis risus eget urna mollis** ornare vel eu leo.\nNullam id dolor id nibh ultricies vehicula ut id elit.\n\nEtiam porta sem malesuada magna mollis euismod. Cras mattis consectetur purus sit amet fermentum.\nAenean lacinia bibendum nulla sed consectetur.\n\n## Heading\n\nVivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor.\nDuis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.\nMorbi leo risus, porta ac consectetur ac, vestibulum at eros.\n\n### Sub-heading\n\nCum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.\n\n### Sub-heading\n\nCum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.\nAenean lacinia bibendum nulla sed consectetur. Etiam porta sem malesuada magna mollis euismod.\nFusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo\nsit amet risus.\n\n- Praesent commodo cursus magna, vel scelerisque nisl consectetur et.\n- Donec id elit non mi porta gravida at eget metus.\n- Nulla vitae elit libero, a pharetra augue.\n\nDonec ullamcorper nulla non metus auctor fringilla. Nulla vitae elit libero, a pharetra augue.\n\n1.  Vestibulum id ligula porta felis euismod semper.\n2.  Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.\n3.  Maecenas sed diam eget risus varius blandit sit amet non magna.\n\nCras mattis consectetur purus sit amet fermentum. Sed posuere consectetur est at lobortis.\n\n```\n','2021-02-24 23:04:28',0),
(23,48,1,'This is a success message!\n','2021-02-24 23:42:08',0),
(24,0,1,'驱蚊器翁\n','2021-02-25 00:02:44',0),
(25,0,1,'为\n','2021-02-25 00:03:36',0),
(26,0,1,'啊\n','2021-02-25 00:04:05',0),
(27,0,1,'# 啊实打实的\n\n1. 沙发沙发上\n\n1. dasadada\n2. 啊飒飒的\n\n* [ ] asdasd\n  啊实打实\n\n> 引用\n\n[链接](https://baidu.com)\n\n*斜体*\n\n?️\n\n---\n\n```\negsg\n```\n','2021-02-25 09:25:38',0),
(28,0,1,'1\n','2021-02-25 16:00:33',0),
(29,0,1,'w\n','2021-02-25 16:35:44',0),
(30,0,1,'1\n','2021-02-25 16:56:09',0),
(36,0,2,'按时发大水发撒缺乏\n','2021-03-04 13:37:41',0),
(37,0,2,'按时发大水发撒缺乏\n','2021-03-04 13:37:44',0),
(38,0,2,'按时发大水发撒缺乏\n','2021-03-04 13:37:44',0),
(39,0,2,'按时发大水发撒缺乏\n','2021-03-04 13:37:45',0),
(40,0,2,'按时发大水发撒缺乏\n','2021-03-04 13:37:45',0),
(42,0,2,'瓦斯的打算打三个方式打给对方说过的话·\n','2021-03-04 13:38:16',0),
(43,0,2,'Hello, Vditor + React!\n','2021-03-04 14:27:41',0),
(44,0,2,'1. 看了ReactNative的慕课网视频\n2. 使用RN写了一个网约车应用\n3. 写了一遍React官方文档的小游戏教程\n4. 修改metrial ui 的模板\n5. 网络上查找react的写法\n','2021-03-04 15:02:36',0),
(45,0,2,'test\n','2021-03-04 15:04:01',0),
(46,0,2,'deqsd\n','2021-03-04 15:04:44',0),
(47,0,2,'123\n\n4124\n','2021-03-04 15:06:02',0),
(48,0,2,'1323\n','2021-03-04 15:06:13',0),
(49,0,2,'qweqwe\n','2021-03-04 15:07:05',0),
(50,0,2,'asdas\n','2021-03-04 15:07:29',0),
(51,1,2,' a','2021-03-26 21:38:19',0),
(52,36,3,'asd','2021-03-26 22:10:44',0),
(53,36,3,'asd','2021-03-26 22:11:37',0),
(54,36,3,'asdasdasd','2021-03-26 22:11:42',0),
(55,69,3,'vdv','2021-03-27 13:40:28',0),
(56,69,3,'vdv','2021-03-27 13:43:57',0),
(57,69,3,'dfasdfgads','2021-03-27 13:44:03',0),
(58,69,3,'fgfhjdgfj','2021-03-27 13:44:33',0),
(59,69,3,'asdgf','2021-03-27 13:45:39',0),
(60,69,3,'爱上岗的','2021-03-27 13:46:25',0),
(61,69,3,'人','2021-03-27 14:01:30',0),
(62,69,3,'波士顿讽德诵功','2021-03-27 14:07:58',0),
(63,69,3,'波士顿讽德诵功','2021-03-27 14:08:01',0),
(64,69,3,'爱的速递','2021-03-27 14:08:05',0),
(65,79,2,'重复打造','2021-03-29 18:27:56',0);



DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `created` DATETIME DEFAULT NULL,
  `content` LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(64) DEFAULT NULL,
  `avatar` VARCHAR(255) DEFAULT NULL,
  `email` VARCHAR(64) DEFAULT NULL,
  `password` VARCHAR(64) DEFAULT NULL,
  `status` INT(5) NOT NULL,
  `created` DATETIME DEFAULT NULL,
  `last_login` DATETIME DEFAULT NULL,
  `role` VARCHAR(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_USERNAME` (`username`) USING BTREE
) ENGINE=INNODB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;


INSERT  INTO `user`(`id`,`username`,`avatar`,`email`,`password`,`status`,`created`,`last_login`,`role`) VALUES 
(0,'super','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1438365440,2412899555&fm=26&gp=0.jpg',NULL,'c4ca4238a0b923820dcc509a6f75849b',0,'2021-04-02 13:47:34',NULL,NULL),
(1,'fanyuhao','https://pics3.baidu.com/feed/d833c895d143ad4b435398fc3097e7a9a40f062d.jpeg?token=812abdddd2056f8a2413e604c4dfc813',NULL,'96e79218965eb72c92a549dd5a330112',0,'2020-04-20 10:44:01',NULL,NULL),
(2,'fan','https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1219821606,4235258507&fm=26&gp=0.jpg',NULL,'c4ca4238a0b923820dcc509a6f75849b',0,'2021-02-26 21:09:03',NULL,'admin'),
(3,'f','https://img0.baidu.com/it/u=1936713713,2821891562&fm=26&fmt=auto&gp=0.jpg',NULL,'c4ca4238a0b923820dcc509a6f75849b',0,'2021-03-04 21:52:44',NULL,NULL),
(4,'fan2',NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',0,'2021-03-05 14:07:01',NULL,NULL),
(6,'ff',NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',0,'2021-03-05 14:10:58',NULL,NULL),
(10,'fan1321',NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',0,'2021-03-05 14:33:42',NULL,NULL),
(11,'fan1231',NULL,NULL,'c4ca4238a0b923820dcc509a6f75849b',0,'2021-03-05 14:34:46',NULL,NULL);


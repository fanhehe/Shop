
create DATABASE IF NOT EXISTS account;
create DATABASE IF NOT EXISTS message;

use account;

-- DROP TABLE IF EXISTS `bind`;
CREATE TABLE IF NOT EXISTS `bind` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `uid` int(4) NOT NULL COMMENT '用户ID',
  `type` varchar(128) NOT NULL COMMENT '绑定信息类型',
  `openid` varchar(128) DEFAULT '0' COMMENT '第三方ID',
  `credential` varchar(32) DEFAULT '0' COMMENT '凭证',
  `createdAt` int(4) DEFAULT '0' COMMENT '创建记录时间',
  `updatedAt` int(4) DEFAULT '0' COMMENT '更新记录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_uid` (`uid`,`type`),
  KEY `udx_openid_type` (`openid`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户绑定信息表';

-- DROP TABLE IF EXISTS `bind_history`;
CREATE TABLE IF NOT EXISTS `bind_history` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `uid` int(4) NOT NULL COMMENT '用户ID',
  `type` varchar(128) NOT NULL COMMENT '绑定信息类型',
  `openid` varchar(128) DEFAULT '0' COMMENT '第三方ID',
  `credential` varchar(32) DEFAULT '0' COMMENT '凭证',
  `createdAt` int(4) DEFAULT '0' COMMENT '创建记录时间',
  `updatedAt` int(4) DEFAULT '0' COMMENT '更新记录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户绑定信息表';

-- DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `uid` int(4) NOT NULL COMMENT '用户ID',
  `nick` varchar(128) NOT NULL COMMENT '用户昵称',
  `avatar` varchar(128) DEFAULT '0' COMMENT '用户头像',
  `salt` varchar(32) DEFAULT '',
  `password` varchar(64) DEFAULT '0' COMMENT '用户密码',
  `createdAt` int(4) DEFAULT '0' COMMENT '创建记录时间',
  `updatedAt` int(4) DEFAULT '0' COMMENT '更新记录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';

use message;

-- DROP TABLE IF EXISTS `captcha_code`;
CREATE TABLE `captcha_code` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `uid` int(4) NOT NULL DEFAULT '0' COMMENT '牛牛号',
  `target` varchar(40) DEFAULT '' COMMENT '发给谁',
  `app` varchar(40) DEFAULT '' COMMENT '类别',
  `order_id` varchar(40) NOT NULL COMMENT '最终使用的订单号',
  `overtime` int(4) DEFAULT '300' COMMENT '邮件验证码的有效时间，单位秒, 写于发消息时的配置',
  `code` varchar(40) NOT NULL COMMENT '发送的消息验证码信息',
  `status` tinyint(1) DEFAULT '1' COMMENT '发送状态1:初始状态, 2: 发送中，3: 发送完成,3 or其他均为失败',
  `created_at` int(4) DEFAULT '0' COMMENT '创建记录时间',
  `updated_at` int(4) DEFAULT '0' COMMENT '更新记录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_order_app` (`order_id`,`app`),
  KEY `idx_target_app` (`target`,`app`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='验证码 验证码表';


-- DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `app` varchar(50) NOT NULL COMMENT '服务权限配置系统分配给应用的 app',
  `category` tinyint(1) NOT NULL COMMENT '消息种类: 0-系统消息, 1-短信, 2-邮件',
  `receiver` varchar(80) DEFAULT '' COMMENT '真实发送的地方, 用户ID, 邮箱地址, 手机号',
  `receiverType` tinyint(1) NOT NULL COMMENT 'receiver的类型 ',
  `uid` int(4) DEFAULT '0' COMMENT '消息接收者牛牛号',
  `status` tinyint(4) DEFAULT '1' COMMENT '消息发送状态（1-初始化，2-失败，3-成功, 4-过期）',
  `title` varchar(50) DEFAULT '' COMMENT '标题',
  `content` mediumtext COMMENT '消息内容, 加密存储，Impush 的内容包含 desc 等字段，json 序列化后再加密存储',
  `messagePayload` mediumtext COMMENT '消息的附带信息',
  `attachments` text COMMENT '附件信息，json 数组格式',
  `orderId` varchar(80) NOT NULL COMMENT '订单id',
  `duration` int(10) DEFAULT '86400' COMMENT '消息有效时间',
  `createdAt` int(4) DEFAULT NULL COMMENT '流水创建时间',
  `updatedAt` int(4) DEFAULT NULL COMMENT '流水更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_app_orderId` (`orderId`,`app`),
  KEY `idx_app` (`app`),
  KEY `idx_uid` (`uid`),
  KEY `idx_receiver` (`receiver`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



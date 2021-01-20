CREATE TABLE `users` (
  `id` int(10)  NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `mobile` char(11) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `realname` varchar(32) DEFAULT NULL,
  `nickname` varchar(32) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `age` int(3)  DEFAULT 0,
  `gender` ENUM('male', 'female') DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `source_app_id` VARCHAR(32) DEFAULT NULL COMMENT '用户来源（业务系统）',
  `type` varchar(32) DEFAULT NULL COMMENT '用户类型',
  `deleted` bit(1) DEFAULT b'0',
  `enabled` bit(1) DEFAULT b'1',
  `reg_ip` varchar(15) DEFAULT NULL COMMENT '注册ip',
  `reg_at` datetime DEFAULT NULL,
  `last_login_ip` varchar(15) DEFAULT NULL COMMENT '最后登录ip',
  `last_login_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
   UNIQUE INDEX `username_uq_index` (`username`),
   UNIQUE INDEX `email_uq_index` (`email`),
   UNIQUE INDEX `mobile_uq_index` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE `open_oauth2_config` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `open_type` ENUM('weixin', 'weibo','qq','taobao','alipay') NOT NULL,
  `app_type` ENUM('mp','miniapp') DEFAULT NULL,
  `app_id` varchar(32) DEFAULT NULL,
  `app_secret` varchar(64) DEFAULT NULL,
  `bind_client_id` varchar(50) DEFAULT NULL,
  `enabled` TINYINT(1) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `open_account_binding` (
  `id` int(10)  NOT NULL AUTO_INCREMENT,
  `user_id` int(10)  NOT NULL,
  `open_type` ENUM('weixin', 'weibo','qq','taobao','alipay') NOT NULL,
  `app_type` ENUM('mp','miniapp') DEFAULT NULL,
  `union_id` varchar(32) DEFAULT NULL,
  `open_id` varchar(32) DEFAULT NULL,
  `source_client_id` VARCHAR(32) DEFAULT NULL COMMENT '用户来源（业务系统）',
  `enabled` bit(1) DEFAULT b'1',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
   UNIQUE INDEX `ao_uq_index` (`user_id`,`open_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='第三方账号绑定';

CREATE TABLE `client_config` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `client_id` varchar(32) DEFAULT NULL,
  `client_secret` varchar(64) DEFAULT NULL,
  `enabled` TINYINT(1) DEFAULT NULL,
  `is_inner_app` TINYINT(1) DEFAULT 0,
  `callback_uri` varchar(50) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `client_id_uq_index` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





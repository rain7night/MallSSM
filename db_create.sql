CREATE TABLE `tb_admin_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `password` VARCHAR(32) NOT NULL COMMENT '密码，加密存储',
  `phone` VARCHAR(20) DEFAULT NULL COMMENT '注册手机号',
  `email` VARCHAR(50) DEFAULT NULL COMMENT '注册邮箱',
  `created` DATETIME NOT NULL,
  `updated` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='管理员表';

CREATE TABLE `tb_content` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `category_id` BIGINT(30),
  `title` VARCHAR(30),
  `sub_title` VARCHAR(30) DEFAULT NULL,
  `title_desc` VARCHAR(50) DEFAULT NULL,
  `url` VARCHAR(30) DEFAULT NULL,
  `pic` VARCHAR(500) DEFAULT NULL,
  `pic2` VARCHAR(500) DEFAULT NULL,
  `created` DATETIME NOT NULL,
  `updated` DATETIME NOT NULL,
  `content` VARCHAR(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_content_category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `parent_id` BIGINT(30),
  `name` VARCHAR(30),
  `status` INTEGER(10) DEFAULT NULL,
  `sort_order` INTEGER(10) DEFAULT NULL,
  `is_parent` TINYINT(1) DEFAULT 0,
  `created` DATETIME NOT NULL,
  `updated` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_item` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(30),
  `sell_point` VARCHAR(30),
  `price` BIGINT(20),
  `num` INTEGER(20),
  `barcode` VARCHAR(20),
  `image` VARCHAR(500),
  `cid` BIGINT(20),
  `status` TINYINT(1),
  `created` DATETIME NOT NULL,
  `updated` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_item_cat` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `parent_id` BIGINT(30),
  `name` VARCHAR(30),
  `status` INTEGER(10),
  `sort_order` INTEGER(10) DEFAULT NULL,
  `is_parent` TINYINT(1) DEFAULT 0,
  `created` DATETIME NOT NULL,
  `updated` DATETIME NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_item_desc` (
  `item_id` BIGINT(20) NOT NULL,
  `created` DATETIME,
  `updated` DATETIME,
  `item_desc` VARCHAR(100),
  PRIMARY KEY (`item_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_item_param` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `item_cat_id` BIGINT(20) NOT NULL,
  `created` DATETIME,
  `updated` DATETIME,
  `param_data` VARCHAR(100),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_item_param_item` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `item_id` BIGINT(20) NOT NULL,
  `created` DATETIME,
  `updated` DATETIME,
  `param_data` VARCHAR(100),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_order` (
  `order_id` VARCHAR(20) NOT NULL,
  `payment` VARCHAR(30),
  `payment_type` INTEGER(10),
  `post_fee` VARCHAR(20),
  `status` INTEGER(10),
  `create_time` DATETIME,
  `update_time` DATETIME,
  `payment_time` DATETIME,
  `consign_time` DATETIME,
  `end_time` DATETIME,
  `close_time` DATETIME,
  `shipping_name` VARCHAR(30),
  `shipping_code` VARCHAR(20),
  `user_id` BIGINT(10),
  `buyer_message` VARCHAR(50),
  `buyer_nick` VARCHAR(30),
  `buyer_rate` INTEGER(10),
  PRIMARY KEY (`order_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_order_item` (
  `id` VARCHAR(20) NOT NULL,
  `item_id` VARCHAR(30),
  `order_id` VARCHAR(20),
  `num` INTEGER(20),
  `title` VARCHAR(10),
  `price` BIGINT(10),
  `total_fee` BIGINT(20),
  `pic_path` VARCHAR(20),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_order_shipping` (
  `order_id` VARCHAR(20) NOT NULL,
  `receiver_name` VARCHAR(20),
  `receiver_phone` VARCHAR(20),
  `receiver_mobile` VARCHAR(20),
  `receiver_state` VARCHAR(20),
  `receiver_city` VARCHAR(20),
  `receiver_district` VARCHAR(30),
  `receiver_address` VARCHAR(30),
  `receiver_zip` VARCHAR(20),
  `created` DATETIME,
  `updated` DATETIME,
  PRIMARY KEY (`order_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50),
  `password` VARCHAR(32),
  `phone` VARCHAR(20),
  `email` VARCHAR(50),
  `created` DATETIME,
  `updated` DATETIME,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

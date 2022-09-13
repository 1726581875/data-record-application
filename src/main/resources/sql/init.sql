
-- 系统用户表
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
	`tenantId` varchar(36) NOT NULL COMMENT '租户id',
  `name` varchar(40) NOT NULL COMMENT '用户名称',
  `account` varchar(20) NOT NULL COMMENT '登录账号',
  `password` varchar(1048) NOT NULL COMMENT '登录密码',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '用户状态| 1正常，2禁用，3已删除',
  `login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


INSERT INTO `bin_log_record`.`sys_user`(`id`, `tenantId`,`name`, `account`, `password`, `status`, `login_time`, `create_time`, `update_time`) VALUES (1,'123456789','admin', 'root', 'root', 1, NULL, '2022-09-09 14:38:52', '2022-09-09 14:38:52');

CREATE TABLE `sys_data_source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenantId` varchar(36) NOT NULL COMMENT '租户id',
  `name` varchar(40) NOT NULL COMMENT '数据源名称',
  `hostname` varchar(40) NOT NULL COMMENT '数据库主机名',
  `server_port` varchar(40) NOT NULL COMMENT '数据库端口',
  `schema_name` varchar(36) NOT NULL COMMENT '数据库名称',
  `username` varchar(20) NOT NULL COMMENT '数据库用户',
  `password` varchar(1048) NOT NULL COMMENT '数据库密码',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '数据源状态| 1正常，2已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='数据源表';


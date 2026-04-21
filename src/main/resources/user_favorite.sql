-- 创建收藏表
CREATE TABLE IF NOT EXISTS `user_favorite` (
  `favorite_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `goods_id` bigint(20) NOT NULL COMMENT '商品id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`favorite_id`),
  UNIQUE KEY `uk_user_goods` (`user_id`, `goods_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_goods_id` (`goods_id`),
  CONSTRAINT `fk_user_favorite_user` FOREIGN KEY (`user_id`) REFERENCES `tb_newbee_mall_user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user_favorite_goods` FOREIGN KEY (`goods_id`) REFERENCES `tb_newbee_mall_goods_info` (`goods_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='用户收藏表';
-- 绿松石商品数据
INSERT INTO `tb_newbee_mall_goods_info` (`goods_name`, `goods_intro`, `goods_category_id`, `goods_cover_img`, `goods_carousel`, `goods_detail_content`, `original_price`, `selling_price`, `stock_num`, `tag`, `goods_sell_status`, `create_user`, `create_time`, `update_user`, `update_time`)
VALUES
	('乌兰花 · 龙纹扳指', '高瓷高蓝乌兰花绿松石，精雕龙纹图案，工艺精湛', 91, '/images/placeholder/turquoise1.jpg', '/images/placeholder/turquoise1.jpg', '<p>商品介绍加载中...</p>', 2800, 2500, 50, '乌兰花', 0, 0, NOW(), 0, NOW()),
	('高瓷蓝绿松石手串', '天然高瓷蓝绿松石，108颗佛珠手串，色泽均匀', 91, '/images/placeholder/turquoise2.jpg', '/images/placeholder/turquoise2.jpg', '<p>商品介绍加载中...</p>', 1200, 1080, 100, '高瓷蓝', 0, 0, NOW(), 0, NOW()),
	('菜籽黄绿松石戒面', '天然菜籽黄绿松石戒面，质地细腻，色泽温润', 91, '/images/placeholder/turquoise3.jpg', '/images/placeholder/turquoise3.jpg', '<p>商品介绍加载中...</p>', 800, 720, 80, '菜籽黄', 0, 0, NOW(), 0, NOW()),
	('唐三彩绿松石雕刻件', '唐三彩绿松石雕刻件，精雕花鸟图案，寓意吉祥', 91, '/images/placeholder/turquoise4.jpg', '/images/placeholder/turquoise4.jpg', '<p>商品介绍加载中...</p>', 1800, 1620, 30, '唐三彩', 0, 0, NOW(), 0, NOW()),
	('绿松石平安扣', '天然绿松石平安扣，寓意平安吉祥，佩戴保平安', 91, '/images/placeholder/turquoise5.jpg', '/images/placeholder/turquoise5.jpg', '<p>商品介绍加载中...</p>', 600, 540, 120, '平安扣', 0, 0, NOW(), 0, NOW()),
	('绿松石耳坠', '天然绿松石耳坠，优雅大方，适合日常佩戴', 91, '/images/placeholder/turquoise6.jpg', '/images/placeholder/turquoise6.jpg', '<p>商品介绍加载中...</p>', 900, 810, 60, '耳坠', 0, 0, NOW(), 0, NOW()),
	('绿松石项链', '天然绿松石项链，搭配925银链，时尚百搭', 91, '/images/placeholder/turquoise7.jpg', '/images/placeholder/turquoise7.jpg', '<p>商品介绍加载中...</p>', 1500, 1350, 40, '项链', 0, 0, NOW(), 0, NOW()),
	('绿松石手把件', '天然绿松石手把件，适合把玩，包浆后更漂亮', 91, '/images/placeholder/turquoise8.jpg', '/images/placeholder/turquoise8.jpg', '<p>商品介绍加载中...</p>', 1300, 1170, 35, '手把件', 0, 0, NOW(), 0, NOW());

-- 首页配置数据
-- 热销商品
INSERT INTO `tb_newbee_mall_index_config` (`config_name`, `config_type`, `goods_id`, `redirect_url`, `config_rank`, `is_deleted`, `create_time`, `create_user`, `update_time`, `update_user`)
VALUES
	('热销商品 乌兰花龙纹扳指', 3, (SELECT goods_id FROM tb_newbee_mall_goods_info WHERE goods_name = '乌兰花 · 龙纹扳指'), '##', 100, 0, NOW(), 0, NOW(), 0),
	('热销商品 高瓷蓝绿松石手串', 3, (SELECT goods_id FROM tb_newbee_mall_goods_info WHERE goods_name = '高瓷蓝绿松石手串'), '##', 99, 0, NOW(), 0, NOW(), 0);

-- 新品上线
INSERT INTO `tb_newbee_mall_index_config` (`config_name`, `config_type`, `goods_id`, `redirect_url`, `config_rank`, `is_deleted`, `create_time`, `create_user`, `update_time`, `update_user`)
VALUES
	('新品上线 唐三彩绿松石雕刻件', 4, (SELECT goods_id FROM tb_newbee_mall_goods_info WHERE goods_name = '唐三彩绿松石雕刻件'), '##', 100, 0, NOW(), 0, NOW(), 0),
	('新品上线 绿松石平安扣', 4, (SELECT goods_id FROM tb_newbee_mall_goods_info WHERE goods_name = '绿松石平安扣'), '##', 99, 0, NOW(), 0, NOW(), 0);

-- 为你推荐
INSERT INTO `tb_newbee_mall_index_config` (`config_name`, `config_type`, `goods_id`, `redirect_url`, `config_rank`, `is_deleted`, `create_time`, `create_user`, `update_time`, `update_user`)
VALUES
	('为你推荐 绿松石项链', 5, (SELECT goods_id FROM tb_newbee_mall_goods_info WHERE goods_name = '绿松石项链'), '##', 100, 0, NOW(), 0, NOW(), 0);

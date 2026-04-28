-- 商品测试数据
INSERT INTO newbee_mall_goods (
    goods_name, 
    goods_intro, 
    goods_category_id, 
    goods_cover_img, 
    goods_carousel, 
    goods_detail_content, 
    original_price, 
    selling_price, 
    stock_num, 
    tag, 
    goods_sell_status, 
    create_time, 
    update_time
) VALUES 
('云盖寺·高瓷蓝水滴吊坠', '精选云盖寺高瓷蓝绿松石，色泽浓郁，质地细腻，水滴造型优雅大方', 1, '/images/turquoise/goods/yungaisi1.jpg', '/images/turquoise/goods/yungaisi1.jpg,/images/turquoise/goods/yungaisi2.jpg', '<p>精选云盖寺高瓷蓝绿松石</p><p>色泽浓郁，质地细腻</p>', 15800.00, 12800.00, 10, '云盖寺,高瓷蓝', 1, NOW(), NOW()),
('丫角山·乌兰花手串', '丫角山乌兰花绿松石手串，花纹独特，佩戴舒适', 2, '/images/turquoise/goods/yajiaoshan1.jpg', '/images/turquoise/goods/yajiaoshan1.jpg,/images/turquoise/goods/yajiaoshan2.jpg', '<p>丫角山乌兰花绿松石手串</p><p>花纹独特，佩戴舒适</p>', 9800.00, 8500.00, 15, '丫角山,乌兰花', 1, NOW(), NOW()),
('秦古·菜籽黄挂件', '秦古菜籽黄绿松石挂件，色泽温润，雕工精细', 1, '/images/turquoise/goods/qingu1.jpg', '/images/turquoise/goods/qingu1.jpg,/images/turquoise/goods/qingu2.jpg', '<p>秦古菜籽黄绿松石挂件</p><p>色泽温润，雕工精细</p>', 6800.00, 5600.00, 20, '秦古,菜籽黄', 1, NOW(), NOW()),
('竹山·高瓷绿圆珠', '竹山高瓷绿绿松石圆珠，颗颗饱满，光泽度高', 2, '/images/turquoise/goods/zhushan1.jpg', '/images/turquoise/goods/zhushan1.jpg,/images/turquoise/goods/zhushan2.jpg', '<p>竹山高瓷绿绿松石圆珠</p><p>颗颗饱满，光泽度高</p>', 7800.00, 6800.00, 12, '竹山,高瓷绿', 0, NOW(), NOW()),
('喇叭山·蓝绿料手串', '喇叭山蓝绿料绿松石手串，颜色独特，佩戴效果好', 2, '/images/turquoise/goods/labasishan1.jpg', '/images/turquoise/goods/labasishan1.jpg,/images/turquoise/goods/labasishan2.jpg', '<p>喇叭山蓝绿料绿松石手串</p><p>颜色独特，佩戴效果好</p>', 8800.00, 7500.00, 8, '喇叭山,蓝绿料', 1, NOW(), NOW());

-- 商品分类测试数据
INSERT INTO newbee_mall_goods_category (
    category_level, 
    parent_id, 
    category_name, 
    category_rank, 
    is_deleted, 
    create_time
) VALUES 
(1, 0, '吊坠', 1, 0, NOW()),
(1, 0, '手串', 2, 0, NOW()),
(1, 0, '挂件', 3, 0, NOW()),
(1, 0, '圆珠', 4, 0, NOW());
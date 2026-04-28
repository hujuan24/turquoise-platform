-- 初始化绿松石属性字典数据

-- 矿口属性
INSERT INTO `tb_turquoise_attr_dict` (`attr_type`, `attr_name`, `attr_code`, `sort_order`, `is_deleted`, `create_time`, `update_time`)
VALUES
    ('origin', '云盖寺', 'YGS', 1, 0, NOW(), NOW()),
    ('origin', '秦古', 'QG', 2, 0, NOW(), NOW()),
    ('origin', '丫角山', 'YJS', 3, 0, NOW(), NOW()),
    ('origin', '竹山', 'ZS', 4, 0, NOW(), NOW());

-- 颜色属性
INSERT INTO `tb_turquoise_attr_dict` (`attr_type`, `attr_name`, `attr_code`, `sort_order`, `is_deleted`, `create_time`, `update_time`)
VALUES
    ('color', '高瓷蓝', 'GCBL', 1, 0, NOW(), NOW()),
    ('color', '乌兰花', 'WHL', 2, 0, NOW(), NOW()),
    ('color', '菜籽黄', 'CZH', 3, 0, NOW(), NOW()),
    ('color', '军绿', 'JL', 4, 0, NOW(), NOW()),
    ('color', '蓝绿', 'LL', 5, 0, NOW(), NOW());

-- 瓷度属性
INSERT INTO `tb_turquoise_attr_dict` (`attr_type`, `attr_name`, `attr_code`, `sort_order`, `is_deleted`, `create_time`, `update_time`)
VALUES
    ('grade', '高瓷', 'GC', 1, 0, NOW(), NOW()),
    ('grade', '瓷松', 'CS', 2, 0, NOW(), NOW()),
    ('grade', '面松', 'MS', 3, 0, NOW(), NOW()),
    ('grade', '泡松', 'PS', 4, 0, NOW(), NOW());
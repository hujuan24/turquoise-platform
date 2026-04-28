package ltd.newbee.mall.mapper;

import ltd.newbee.mall.entity.TurquoiseAttrDict;
import java.util.List;

public interface TurquoiseAttrDictMapper {
    /**
     * 根据类型获取属性列表
     * @param attrType 属性类型
     * @return 属性列表
     */
    List<TurquoiseAttrDict> selectListByType(String attrType);

    /**
     * 插入属性
     * @param record 属性实体
     * @return 影响行数
     */
    int insert(TurquoiseAttrDict record);

    /**
     * 根据主键删除属性（逻辑删除）
     * @param attrId 属性ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Long attrId);

    /**
     * 根据主键查询属性
     * @param attrId 属性ID
     * @return 属性实体
     */
    TurquoiseAttrDict selectByPrimaryKey(Long attrId);

    /**
     * 更新属性
     * @param record 属性实体
     * @return 影响行数
     */
    int updateByPrimaryKey(TurquoiseAttrDict record);
}

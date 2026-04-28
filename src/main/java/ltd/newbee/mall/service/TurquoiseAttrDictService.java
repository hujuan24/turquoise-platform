package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.TurquoiseAttrDict;
import java.util.List;

public interface TurquoiseAttrDictService {
    /**
     * 根据类型获取属性列表
     * @param attrType 属性类型
     * @return 属性列表
     */
    List<TurquoiseAttrDict> getAttrListByType(String attrType);

    /**
     * 保存属性
     * @param attrDict 属性实体
     * @return 是否保存成功
     */
    boolean saveAttr(TurquoiseAttrDict attrDict);

    /**
     * 删除属性
     * @param attrId 属性ID
     * @return 是否删除成功
     */
    boolean deleteAttr(Long attrId);
}

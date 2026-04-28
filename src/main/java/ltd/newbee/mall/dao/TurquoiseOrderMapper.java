package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.TurquoiseOrder;

import java.util.List;
import java.util.Map;

public interface TurquoiseOrderMapper {
    int deleteByPrimaryKey(Long orderId);
    int insert(TurquoiseOrder record);
    int insertSelective(TurquoiseOrder record);
    TurquoiseOrder selectByPrimaryKey(Long orderId);
    int updateByPrimaryKeySelective(TurquoiseOrder record);
    int updateByPrimaryKey(TurquoiseOrder record);
    List<TurquoiseOrder> findOrderList(Map<String, Object> params);
    int getTotalOrders(Map<String, Object> params);
}

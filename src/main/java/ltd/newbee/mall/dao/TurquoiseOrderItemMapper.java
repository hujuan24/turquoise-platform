package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.TurquoiseOrderItem;

import java.util.List;

public interface TurquoiseOrderItemMapper {
    int deleteByPrimaryKey(Long orderItemId);
    int insert(TurquoiseOrderItem record);
    int insertSelective(TurquoiseOrderItem record);
    TurquoiseOrderItem selectByPrimaryKey(Long orderItemId);
    int updateByPrimaryKeySelective(TurquoiseOrderItem record);
    int updateByPrimaryKey(TurquoiseOrderItem record);
    List<TurquoiseOrderItem> findOrderItemsByOrderId(Long orderId);
}

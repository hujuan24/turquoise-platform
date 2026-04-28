package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.TurquoiseOrderItem;

import java.util.List;

public interface TurquoiseOrderItemService {
    List<TurquoiseOrderItem> getOrderItemsByOrderId(Long orderId);
}

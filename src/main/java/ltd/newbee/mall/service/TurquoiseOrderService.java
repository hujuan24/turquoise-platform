package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.TurquoiseOrder;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

public interface TurquoiseOrderService {
    PageResult getTurquoiseOrdersPage(PageQueryUtil pageUtil);
    TurquoiseOrder getOrderById(Long orderId);
    boolean updateOrderStatus(Long orderId, Integer orderStatus);
}

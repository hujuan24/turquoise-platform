package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.dao.TurquoiseOrderMapper;
import ltd.newbee.mall.entity.TurquoiseOrder;
import ltd.newbee.mall.service.TurquoiseOrderService;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.PageQueryUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TurquoiseOrderServiceImpl implements TurquoiseOrderService {

    @Resource
    private TurquoiseOrderMapper turquoiseOrderMapper;

    @Override
    public PageResult getTurquoiseOrdersPage(PageQueryUtil pageUtil) {
        List<TurquoiseOrder> orderList = turquoiseOrderMapper.findOrderList(pageUtil);
        int total = turquoiseOrderMapper.getTotalOrders(pageUtil);
        return new PageResult(orderList, total, pageUtil.getLimit(), pageUtil.getPage());
    }

    @Override
    public TurquoiseOrder getOrderById(Long orderId) {
        return turquoiseOrderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public boolean updateOrderStatus(Long orderId, Integer orderStatus) {
        TurquoiseOrder order = turquoiseOrderMapper.selectByPrimaryKey(orderId);
        if (order != null) {
            order.setOrderStatus(orderStatus.byteValue());
            return turquoiseOrderMapper.updateByPrimaryKeySelective(order) > 0;
        }
        return false;
    }
}

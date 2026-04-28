package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.dao.TurquoiseOrderItemMapper;
import ltd.newbee.mall.entity.TurquoiseOrderItem;
import ltd.newbee.mall.service.TurquoiseOrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TurquoiseOrderItemServiceImpl implements TurquoiseOrderItemService {

    @Resource
    private TurquoiseOrderItemMapper turquoiseOrderItemMapper;

    @Override
    public List<TurquoiseOrderItem> getOrderItemsByOrderId(Long orderId) {
        return turquoiseOrderItemMapper.findOrderItemsByOrderId(orderId);
    }
}

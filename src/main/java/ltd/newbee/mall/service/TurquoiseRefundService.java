package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.TurquoiseRefund;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.PageQueryUtil;

public interface TurquoiseRefundService {
    PageResult getRefundsPage(PageQueryUtil pageUtil);
    boolean updateRefundStatus(Long refundId, Integer refundStatus);
    TurquoiseRefund getRefundById(Long refundId);
}

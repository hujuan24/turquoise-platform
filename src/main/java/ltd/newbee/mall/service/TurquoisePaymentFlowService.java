package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.TurquoisePaymentFlow;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.PageQueryUtil;

public interface TurquoisePaymentFlowService {
    PageResult getPaymentsPage(PageQueryUtil pageUtil);
    TurquoisePaymentFlow getPaymentById(Long flowId);
    Integer getTodayTotalAmount();
    Integer getTotalCount();
}

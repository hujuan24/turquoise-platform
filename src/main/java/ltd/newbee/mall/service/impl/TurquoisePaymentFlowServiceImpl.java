package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.dao.TurquoisePaymentFlowMapper;
import ltd.newbee.mall.entity.TurquoisePaymentFlow;
import ltd.newbee.mall.service.TurquoisePaymentFlowService;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.PageQueryUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TurquoisePaymentFlowServiceImpl implements TurquoisePaymentFlowService {

    @Resource
    private TurquoisePaymentFlowMapper turquoisePaymentFlowMapper;

    @Override
    public PageResult getPaymentsPage(PageQueryUtil pageUtil) {
        List<TurquoisePaymentFlow> paymentList = turquoisePaymentFlowMapper.findPaymentList(pageUtil);
        int total = turquoisePaymentFlowMapper.getTotalPayments(pageUtil);
        return new PageResult(paymentList, total, pageUtil.getLimit(), pageUtil.getPage());
    }

    @Override
    public TurquoisePaymentFlow getPaymentById(Long flowId) {
        return turquoisePaymentFlowMapper.selectByPrimaryKey(flowId);
    }

    @Override
    public Integer getTodayTotalAmount() {
        return turquoisePaymentFlowMapper.getTodayTotalAmount();
    }

    @Override
    public Integer getTotalCount() {
        return turquoisePaymentFlowMapper.getTotalCount();
    }
}

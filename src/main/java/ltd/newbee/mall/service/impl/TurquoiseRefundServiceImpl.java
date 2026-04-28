package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.dao.TurquoiseRefundMapper;
import ltd.newbee.mall.entity.TurquoiseRefund;
import ltd.newbee.mall.service.TurquoiseRefundService;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.PageQueryUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TurquoiseRefundServiceImpl implements TurquoiseRefundService {

    @Resource
    private TurquoiseRefundMapper turquoiseRefundMapper;

    @Override
    public PageResult getRefundsPage(PageQueryUtil pageUtil) {
        List<TurquoiseRefund> refundList = turquoiseRefundMapper.findRefundList(pageUtil);
        int total = turquoiseRefundMapper.getTotalRefunds(pageUtil);
        return new PageResult(refundList, total, pageUtil.getLimit(), pageUtil.getPage());
    }

    @Override
    public boolean updateRefundStatus(Long refundId, Integer refundStatus) {
        TurquoiseRefund refund = turquoiseRefundMapper.selectByPrimaryKey(refundId);
        if (refund != null) {
            refund.setRefundStatus(refundStatus.byteValue());
            return turquoiseRefundMapper.updateByPrimaryKeySelective(refund) > 0;
        }
        return false;
    }

    @Override
    public TurquoiseRefund getRefundById(Long refundId) {
        return turquoiseRefundMapper.selectByPrimaryKey(refundId);
    }
}

package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.service.TurquoiseAppraisalService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.springframework.stereotype.Service;

@Service
public class TurquoiseAppraisalServiceImpl implements TurquoiseAppraisalService {

    @Override
    public PageResult getAppraisalPage(PageQueryUtil pageUtil) {
        // 实现鉴定申请列表查询
        return null;
    }

    @Override
    public Object getAppraisalById(Long appraisalId) {
        // 实现根据ID获取鉴定申请
        return null;
    }

    @Override
    public boolean processAppraisal(Object appraisal) {
        // 实现处理鉴定申请
        return false;
    }
}

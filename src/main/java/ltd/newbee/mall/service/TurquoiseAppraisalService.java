package ltd.newbee.mall.service;

import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

public interface TurquoiseAppraisalService {
    /**
     * 获取鉴定申请列表
     * @param pageUtil
     * @return
     */
    PageResult getAppraisalPage(PageQueryUtil pageUtil);

    /**
     * 根据ID获取鉴定申请
     * @param appraisalId
     * @return
     */
    Object getAppraisalById(Long appraisalId);

    /**
     * 处理鉴定申请
     * @param appraisal
     * @return
     */
    boolean processAppraisal(Object appraisal);
}

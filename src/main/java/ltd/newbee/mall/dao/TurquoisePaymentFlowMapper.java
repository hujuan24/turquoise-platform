package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.TurquoisePaymentFlow;

import java.util.List;
import java.util.Map;

public interface TurquoisePaymentFlowMapper {
    int deleteByPrimaryKey(Long flowId);
    int insert(TurquoisePaymentFlow record);
    int insertSelective(TurquoisePaymentFlow record);
    TurquoisePaymentFlow selectByPrimaryKey(Long flowId);
    int updateByPrimaryKeySelective(TurquoisePaymentFlow record);
    int updateByPrimaryKey(TurquoisePaymentFlow record);
    List<TurquoisePaymentFlow> findPaymentList(Map<String, Object> params);
    int getTotalPayments(Map<String, Object> params);
    Integer getTodayTotalAmount();
    Integer getTotalCount();
}

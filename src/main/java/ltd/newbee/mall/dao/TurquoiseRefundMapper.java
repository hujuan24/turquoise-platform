package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.TurquoiseRefund;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TurquoiseRefundMapper {
    int deleteByPrimaryKey(Long refundId);
    int insert(TurquoiseRefund record);
    int insertSelective(TurquoiseRefund record);
    TurquoiseRefund selectByPrimaryKey(Long refundId);
    int updateByPrimaryKeySelective(TurquoiseRefund record);
    int updateByPrimaryKey(TurquoiseRefund record);
    List<TurquoiseRefund> findRefundList(Map<String, Object> params);
    int getTotalRefunds(Map<String, Object> params);
}

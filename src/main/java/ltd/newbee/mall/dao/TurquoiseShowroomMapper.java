package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.TurquoiseShowroom;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 展厅管理Mapper接口
 */
public interface TurquoiseShowroomMapper {

    /**
     * 分页查询展厅列表
     */
    List<TurquoiseShowroom> findShowroomList(@Param("params") Map<String, Object> params);

    /**
     * 统计展厅数量
     */
    int getTotalShowrooms(@Param("params") Map<String, Object> params);

    /**
     * 根据ID查询展厅
     */
    TurquoiseShowroom selectByPrimaryKey(Long showroomId);

    /**
     * 新增展厅
     */
    int insert(TurquoiseShowroom record);

    /**
     * 更新展厅
     */
    int updateByPrimaryKey(TurquoiseShowroom record);

    /**
     * 删除展厅（软删除）
     */
    int deleteByPrimaryKey(Long showroomId);

    /**
     * 批量删除展厅
     */
    int batchDelete(@Param("ids") List<Long> ids);

    /**
     * 更新状态
     */
    int updateStatus(@Param("showroomId") Long showroomId, @Param("status") Byte status);
}
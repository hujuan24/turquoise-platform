package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.TurquoiseShowroom;

import java.util.List;
import java.util.Map;

/**
 * 展厅管理Service接口
 */
public interface TurquoiseShowroomService {

    /**
     * 分页查询展厅列表
     */
    Map<String, Object> getShowroomList(Integer page, Integer limit, String showroomName);

    /**
     * 根据ID查询展厅
     */
    TurquoiseShowroom getShowroomById(Long showroomId);

    /**
     * 新增展厅
     */
    boolean addShowroom(TurquoiseShowroom showroom);

    /**
     * 更新展厅
     */
    boolean updateShowroom(TurquoiseShowroom showroom);

    /**
     * 删除展厅
     */
    boolean deleteShowroom(Long showroomId);

    /**
     * 批量删除展厅
     */
    boolean batchDeleteShowroom(List<Long> ids);

    /**
     * 更新状态
     */
    boolean updateShowroomStatus(Long showroomId, Byte status);
}
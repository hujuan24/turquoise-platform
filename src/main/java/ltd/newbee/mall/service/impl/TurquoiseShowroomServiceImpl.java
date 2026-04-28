package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.dao.TurquoiseShowroomMapper;
import ltd.newbee.mall.entity.TurquoiseShowroom;
import ltd.newbee.mall.service.TurquoiseShowroomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 展厅管理Service实现类
 */
@Service
public class TurquoiseShowroomServiceImpl implements TurquoiseShowroomService {

    @Resource
    private TurquoiseShowroomMapper turquoiseShowroomMapper;

    @Override
    public Map<String, Object> getShowroomList(Integer page, Integer limit, String showroomName) {
        Map<String, Object> params = new HashMap<>();
        params.put("showroomName", showroomName);
        params.put("start", (page - 1) * limit);
        params.put("limit", limit);

        List<TurquoiseShowroom> showroomList = turquoiseShowroomMapper.findShowroomList(params);
        int total = turquoiseShowroomMapper.getTotalShowrooms(params);

        Map<String, Object> result = new HashMap<>();
        result.put("list", showroomList);
        result.put("total", total);
        result.put("page", page);
        result.put("limit", limit);

        return result;
    }

    @Override
    public TurquoiseShowroom getShowroomById(Long showroomId) {
        return turquoiseShowroomMapper.selectByPrimaryKey(showroomId);
    }

    @Override
    @Transactional
    public boolean addShowroom(TurquoiseShowroom showroom) {
        if (showroom.getStatus() == null) {
            showroom.setStatus((byte) 1);
        }
        if (showroom.getSort() == null) {
            showroom.setSort(0);
        }
        return turquoiseShowroomMapper.insert(showroom) > 0;
    }

    @Override
    @Transactional
    public boolean updateShowroom(TurquoiseShowroom showroom) {
        return turquoiseShowroomMapper.updateByPrimaryKey(showroom) > 0;
    }

    @Override
    @Transactional
    public boolean deleteShowroom(Long showroomId) {
        return turquoiseShowroomMapper.deleteByPrimaryKey(showroomId) > 0;
    }

    @Override
    @Transactional
    public boolean batchDeleteShowroom(List<Long> ids) {
        return turquoiseShowroomMapper.batchDelete(ids) > 0;
    }

    @Override
    @Transactional
    public boolean updateShowroomStatus(Long showroomId, Byte status) {
        return turquoiseShowroomMapper.updateStatus(showroomId, status) > 0;
    }
}
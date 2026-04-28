package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.dao.TurquoiseUserCollectMapper;
import ltd.newbee.mall.service.TurquoiseUserCollectService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户收藏服务实现类
 */
@Service
public class TurquoiseUserCollectServiceImpl implements TurquoiseUserCollectService {

    @Resource
    private TurquoiseUserCollectMapper turquoiseUserCollectMapper;

    @Override
    public Map<String, Object> getCollectStatistics() {
        Map<String, Object> statistics = new HashMap<>();
        
        // 收藏总次数
        Integer totalCollects = turquoiseUserCollectMapper.getTotalCollects();
        statistics.put("totalCollects", totalCollects != null ? totalCollects : 0);
        
        // 今日新增收藏
        Integer todayCollects = turquoiseUserCollectMapper.getTodayCollects();
        statistics.put("todayCollects", todayCollects != null ? todayCollects : 0);
        
        // 有收藏行为的用户数
        Integer activeCollectUsers = turquoiseUserCollectMapper.getActiveCollectUsers();
        statistics.put("activeCollectUsers", activeCollectUsers != null ? activeCollectUsers : 0);
        
        // 人均收藏数（保留1位小数）
        double avgCollects = 0;
        if (activeCollectUsers != null && activeCollectUsers > 0 && totalCollects != null) {
            avgCollects = Math.round((double) totalCollects / activeCollectUsers * 10) / 10.0;
        }
        statistics.put("avgCollectsPerUser", avgCollects);
        
        return statistics;
    }

    @Override
    public List<Map<String, Object>> getHotProducts() {
        return turquoiseUserCollectMapper.getHotProducts();
    }

    @Override
    public PageResult getCollectList(PageQueryUtil pageQueryUtil) {
        List<Map<String, Object>> collectList = turquoiseUserCollectMapper.getCollectList(pageQueryUtil);
        int total = turquoiseUserCollectMapper.getCollectListCount(pageQueryUtil);
        return new PageResult(collectList, total, pageQueryUtil.getLimit(), pageQueryUtil.getPage());
    }

    @Override
    public boolean cancelCollect(Long collectId) {
        return turquoiseUserCollectMapper.cancelCollect(collectId) > 0;
    }
}
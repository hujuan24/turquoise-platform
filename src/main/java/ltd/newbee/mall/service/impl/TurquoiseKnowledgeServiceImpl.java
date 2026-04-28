package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.dao.TurquoiseKnowledgeMapper;
import ltd.newbee.mall.entity.TurquoiseKnowledge;
import ltd.newbee.mall.service.TurquoiseKnowledgeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 知识库Service实现类
 */
@Service
public class TurquoiseKnowledgeServiceImpl implements TurquoiseKnowledgeService {

    @Resource
    private TurquoiseKnowledgeMapper turquoiseKnowledgeMapper;

    @Override
    public Map<String, Object> getKnowledgeList(Integer page, Integer limit, String title, String category) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        params.put("category", category);
        params.put("start", (page - 1) * limit);
        params.put("limit", limit);

        List<TurquoiseKnowledge> knowledgeList = turquoiseKnowledgeMapper.findKnowledgeList(params);
        int total = turquoiseKnowledgeMapper.getTotalKnowledges(params);

        Map<String, Object> result = new HashMap<>();
        result.put("list", knowledgeList);
        result.put("total", total);
        result.put("page", page);
        result.put("limit", limit);

        return result;
    }

    @Override
    public TurquoiseKnowledge getKnowledgeById(Long knowledgeId) {
        return turquoiseKnowledgeMapper.selectByPrimaryKey(knowledgeId);
    }

    @Override
    @Transactional
    public boolean addKnowledge(TurquoiseKnowledge knowledge) {
        if (knowledge.getStatus() == null) {
            knowledge.setStatus((byte) 0);
        }
        if (knowledge.getViewCount() == null) {
            knowledge.setViewCount(0);
        }
        return turquoiseKnowledgeMapper.insert(knowledge) > 0;
    }

    @Override
    @Transactional
    public boolean updateKnowledge(TurquoiseKnowledge knowledge) {
        return turquoiseKnowledgeMapper.updateByPrimaryKey(knowledge) > 0;
    }

    @Override
    @Transactional
    public boolean deleteKnowledge(Long knowledgeId) {
        return turquoiseKnowledgeMapper.deleteByPrimaryKey(knowledgeId) > 0;
    }

    @Override
    @Transactional
    public boolean batchDeleteKnowledge(List<Long> ids) {
        return turquoiseKnowledgeMapper.batchDelete(ids) > 0;
    }

    @Override
    @Transactional
    public boolean updateKnowledgeStatus(Long knowledgeId, Byte status) {
        return turquoiseKnowledgeMapper.updateStatus(knowledgeId, status) > 0;
    }

    @Override
    public List<String> getAllCategories() {
        return turquoiseKnowledgeMapper.getAllCategories();
    }
}
package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.TurquoiseKnowledge;

import java.util.List;
import java.util.Map;

/**
 * 知识库Service接口
 */
public interface TurquoiseKnowledgeService {

    /**
     * 分页查询知识库列表
     */
    Map<String, Object> getKnowledgeList(Integer page, Integer limit, String title, String category);

    /**
     * 根据ID查询知识
     */
    TurquoiseKnowledge getKnowledgeById(Long knowledgeId);

    /**
     * 新增知识
     */
    boolean addKnowledge(TurquoiseKnowledge knowledge);

    /**
     * 更新知识
     */
    boolean updateKnowledge(TurquoiseKnowledge knowledge);

    /**
     * 删除知识
     */
    boolean deleteKnowledge(Long knowledgeId);

    /**
     * 批量删除知识
     */
    boolean batchDeleteKnowledge(List<Long> ids);

    /**
     * 更新状态
     */
    boolean updateKnowledgeStatus(Long knowledgeId, Byte status);

    /**
     * 获取所有分类
     */
    List<String> getAllCategories();
}
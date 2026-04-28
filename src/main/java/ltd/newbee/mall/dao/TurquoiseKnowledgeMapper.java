package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.TurquoiseKnowledge;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 知识库Mapper接口
 */
public interface TurquoiseKnowledgeMapper {

    /**
     * 分页查询知识库列表
     */
    List<TurquoiseKnowledge> findKnowledgeList(@Param("params") Map<String, Object> params);

    /**
     * 统计知识库数量
     */
    int getTotalKnowledges(@Param("params") Map<String, Object> params);

    /**
     * 根据ID查询知识
     */
    TurquoiseKnowledge selectByPrimaryKey(Long knowledgeId);

    /**
     * 新增知识
     */
    int insert(TurquoiseKnowledge record);

    /**
     * 更新知识
     */
    int updateByPrimaryKey(TurquoiseKnowledge record);

    /**
     * 删除知识（软删除）
     */
    int deleteByPrimaryKey(Long knowledgeId);

    /**
     * 批量删除知识
     */
    int batchDelete(@Param("ids") List<Long> ids);

    /**
     * 更新状态
     */
    int updateStatus(@Param("knowledgeId") Long knowledgeId, @Param("status") Byte status);

    /**
     * 更新浏览量
     */
    int updateViewCount(@Param("knowledgeId") Long knowledgeId);

    /**
     * 获取所有分类
     */
    List<String> getAllCategories();
}
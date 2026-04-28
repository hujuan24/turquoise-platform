package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.TurquoiseForumPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 论坛帖子Mapper接口
 */
public interface TurquoiseForumPostMapper {

    /**
     * 分页查询帖子列表
     */
    List<TurquoiseForumPost> findPostList(@Param("params") Map<String, Object> params);

    /**
     * 统计帖子数量
     */
    int getTotalPosts(@Param("params") Map<String, Object> params);

    /**
     * 根据ID查询帖子
     */
    TurquoiseForumPost selectByPrimaryKey(Long postId);

    /**
     * 更新状态
     */
    int updateStatus(@Param("postId") Long postId, @Param("status") Byte status);

    /**
     * 删除帖子（软删除）
     */
    int deleteByPrimaryKey(Long postId);

    /**
     * 批量删除帖子
     */
    int batchDelete(@Param("ids") List<Long> ids);

    /**
     * 更新浏览量
     */
    int updateViewCount(@Param("postId") Long postId);
}
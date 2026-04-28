package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.TurquoiseForumPost;

import java.util.List;
import java.util.Map;

/**
 * 论坛帖子Service接口
 */
public interface TurquoiseForumPostService {

    /**
     * 分页查询帖子列表
     */
    Map<String, Object> getPostList(Integer page, Integer limit, String title, Integer status);

    /**
     * 根据ID查询帖子
     */
    TurquoiseForumPost getPostById(Long postId);

    /**
     * 删除帖子
     */
    boolean deletePost(Long postId);

    /**
     * 批量删除帖子
     */
    boolean batchDeletePost(List<Long> ids);

    /**
     * 更新帖子状态
     */
    boolean updatePostStatus(Long postId, Byte status);
}
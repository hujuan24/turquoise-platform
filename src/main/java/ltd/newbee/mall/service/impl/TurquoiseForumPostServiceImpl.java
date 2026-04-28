package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.dao.TurquoiseForumPostMapper;
import ltd.newbee.mall.entity.TurquoiseForumPost;
import ltd.newbee.mall.service.TurquoiseForumPostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 论坛帖子Service实现类
 */
@Service
public class TurquoiseForumPostServiceImpl implements TurquoiseForumPostService {

    @Resource
    private TurquoiseForumPostMapper turquoiseForumPostMapper;

    @Override
    public Map<String, Object> getPostList(Integer page, Integer limit, String title, Integer status) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", title);
        if (status != null && status >= 0) {
            params.put("status", status);
        }
        params.put("start", (page - 1) * limit);
        params.put("limit", limit);

        List<TurquoiseForumPost> postList = turquoiseForumPostMapper.findPostList(params);
        int total = turquoiseForumPostMapper.getTotalPosts(params);

        Map<String, Object> result = new HashMap<>();
        result.put("list", postList);
        result.put("total", total);
        result.put("page", page);
        result.put("limit", limit);

        return result;
    }

    @Override
    public TurquoiseForumPost getPostById(Long postId) {
        return turquoiseForumPostMapper.selectByPrimaryKey(postId);
    }

    @Override
    @Transactional
    public boolean deletePost(Long postId) {
        return turquoiseForumPostMapper.deleteByPrimaryKey(postId) > 0;
    }

    @Override
    @Transactional
    public boolean batchDeletePost(List<Long> ids) {
        return turquoiseForumPostMapper.batchDelete(ids) > 0;
    }

    @Override
    @Transactional
    public boolean updatePostStatus(Long postId, Byte status) {
        return turquoiseForumPostMapper.updateStatus(postId, status) > 0;
    }
}
package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.entity.TurquoiseGoodsReview;
import ltd.newbee.mall.mapper.TurquoiseGoodsReviewMapper;
import ltd.newbee.mall.service.TurquoiseReviewService;
import ltd.newbee.mall.util.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 绿松石商品评价服务实现类
 */
@Service
public class TurquoiseReviewServiceImpl implements TurquoiseReviewService {

    @Resource
    private TurquoiseGoodsReviewMapper turquoiseGoodsReviewMapper;

    @Override
    public String addReview(Long goodsId, Long userId, String username, Integer rating, String content) {
        // 检查用户是否已评价该商品
        if (checkReviewed(goodsId, userId)) {
            return "您已经评价过该商品";
        }

        // 创建评价实体
        TurquoiseGoodsReview review = new TurquoiseGoodsReview();
        review.setGoodsId(goodsId);
        review.setUserId(userId);
        review.setUsername(username);
        review.setRating(rating);
        review.setContent(content);

        // 插入评价
        int result = turquoiseGoodsReviewMapper.insert(review);
        if (result > 0) {
            return "success";
        } else {
            return "评价失败";
        }
    }

    @Override
    public PageResult getReviewList(Long goodsId, String type, Integer page, Integer limit) {
        int start = (page - 1) * limit;
        List<TurquoiseGoodsReview> reviews = turquoiseGoodsReviewMapper.selectByGoodsId(goodsId, type, start, limit);
        int total = turquoiseGoodsReviewMapper.countByGoodsId(goodsId, type);
        return new PageResult(reviews, total, page, limit);
    }

    @Override
    public Map<String, Object> getReviewStats(Long goodsId) {
        Map<String, Object> stats = new HashMap<>();

        // 获取平均评分
        Double averageScore = turquoiseGoodsReviewMapper.selectAverageRating(goodsId);
        stats.put("averageScore", averageScore != null ? averageScore : 0.0);

        // 获取评价总数
        int totalCount = turquoiseGoodsReviewMapper.countByGoodsId(goodsId, "all");
        stats.put("totalCount", totalCount);

        // 获取各星级评价数量
        int fiveStarCount = turquoiseGoodsReviewMapper.countByGoodsId(goodsId, "5");
        int fourStarCount = turquoiseGoodsReviewMapper.countByGoodsId(goodsId, "4");
        int threeStarCount = turquoiseGoodsReviewMapper.countByGoodsId(goodsId, "3");
        int twoStarCount = turquoiseGoodsReviewMapper.countByGoodsId(goodsId, "2");
        int oneStarCount = turquoiseGoodsReviewMapper.countByGoodsId(goodsId, "1");

        stats.put("fiveStarCount", fiveStarCount);
        stats.put("fourStarCount", fourStarCount);
        stats.put("threeStarCount", threeStarCount);
        stats.put("twoStarCount", twoStarCount);
        stats.put("oneStarCount", oneStarCount);

        // 计算各星级评价占比
        if (totalCount > 0) {
            stats.put("fiveStarPercent", Math.round((double) fiveStarCount / totalCount * 100));
            stats.put("fourStarPercent", Math.round((double) fourStarCount / totalCount * 100));
            stats.put("threeStarPercent", Math.round((double) threeStarCount / totalCount * 100));
            stats.put("twoStarPercent", Math.round((double) twoStarCount / totalCount * 100));
            stats.put("oneStarPercent", Math.round((double) oneStarCount / totalCount * 100));
        } else {
            stats.put("fiveStarPercent", 0);
            stats.put("fourStarPercent", 0);
            stats.put("threeStarPercent", 0);
            stats.put("twoStarPercent", 0);
            stats.put("oneStarPercent", 0);
        }

        return stats;
    }

    @Override
    public boolean checkReviewed(Long goodsId, Long userId) {
        TurquoiseGoodsReview review = turquoiseGoodsReviewMapper.selectByGoodsIdAndUserId(goodsId, userId);
        return review != null;
    }
}

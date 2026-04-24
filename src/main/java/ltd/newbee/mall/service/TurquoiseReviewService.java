package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.TurquoiseGoodsReview;
import ltd.newbee.mall.util.PageResult;

import java.util.Map;

/**
 * 绿松石商品评价服务接口
 */
public interface TurquoiseReviewService {
    /**
     * 添加评价
     * @param goodsId 商品ID
     * @param userId 用户ID
     * @param username 用户名
     * @param rating 评分
     * @param content 评价内容
     * @return 操作结果
     */
    String addReview(Long goodsId, Long userId, String username, Integer rating, String content);

    /**
     * 分页查询评价列表
     * @param goodsId 商品ID
     * @param type 评价类型：all-全部，5-好评，3-中评，1-差评
     * @param page 页码
     * @param limit 每页数量
     * @return 分页结果
     */
    PageResult getReviewList(Long goodsId, String type, Integer page, Integer limit);

    /**
     * 获取评价统计信息
     * @param goodsId 商品ID
     * @return 评价统计信息
     */
    Map<String, Object> getReviewStats(Long goodsId);

    /**
     * 检查用户是否已评价该商品
     * @param goodsId 商品ID
     * @param userId 用户ID
     * @return 是否已评价
     */
    boolean checkReviewed(Long goodsId, Long userId);
}

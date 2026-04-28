package ltd.newbee.mall.service;

import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

import java.util.Map;

/**
 * 用户收藏服务接口
 */
public interface TurquoiseUserCollectService {

    /**
     * 获取收藏统计数据
     * @return Map包含统计信息
     */
    Map<String, Object> getCollectStatistics();

    /**
     * 获取热门收藏商品排行（Top 10）
     * @return 商品列表
     */
    java.util.List<Map<String, Object>> getHotProducts();

    /**
     * 获取收藏记录分页列表
     * @param pageQueryUtil 分页参数
     * @return 分页结果
     */
    PageResult getCollectList(PageQueryUtil pageQueryUtil);

    /**
     * 取消收藏
     * @param collectId 收藏ID
     * @return 是否成功
     */
    boolean cancelCollect(Long collectId);
}
package ltd.newbee.mall.dao;

import ltd.newbee.mall.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户收藏Mapper接口
 */
public interface TurquoiseUserCollectMapper {

    /**
     * 获取收藏总次数
     * @return 总次数
     */
    Integer getTotalCollects();

    /**
     * 获取今日新增收藏
     * @return 今日收藏数
     */
    Integer getTodayCollects();

    /**
     * 获取有收藏行为的用户数
     * @return 用户数
     */
    Integer getActiveCollectUsers();

    /**
     * 获取热门收藏商品排行（Top 10）
     * @return 商品列表
     */
    List<Map<String, Object>> getHotProducts();

    /**
     * 获取收藏记录分页列表
     * @param pageQueryUtil 分页参数
     * @return 收藏列表
     */
    List<Map<String, Object>> getCollectList(PageQueryUtil pageQueryUtil);

    /**
     * 获取收藏记录总数
     * @param pageQueryUtil 分页参数
     * @return 总数
     */
    int getCollectListCount(PageQueryUtil pageQueryUtil);

    /**
     * 取消收藏（软删除）
     * @param collectId 收藏ID
     * @return 影响行数
     */
    int cancelCollect(@Param("collectId") Long collectId);
}
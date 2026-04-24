package ltd.newbee.mall.mapper;

import ltd.newbee.mall.entity.TurquoiseGoodsReview;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 绿松石商品评价数据访问接口
 */
public interface TurquoiseGoodsReviewMapper {
    /**
     * 添加评价
     * @param review 评价实体
     * @return 影响行数
     */
    int insert(TurquoiseGoodsReview review);

    /**
     * 根据商品ID和用户ID查询评价
     * @param goodsId 商品ID
     * @param userId 用户ID
     * @return 评价实体
     */
    TurquoiseGoodsReview selectByGoodsIdAndUserId(@Param("goodsId") Long goodsId, @Param("userId") Long userId);

    /**
     * 根据商品ID查询评价列表
     * @param goodsId 商品ID
     * @param type 评价类型：all-全部，5-好评，3-中评，1-差评
     * @param start 起始位置
     * @param limit 限制数量
     * @return 评价列表
     */
    List<TurquoiseGoodsReview> selectByGoodsId(@Param("goodsId") Long goodsId, @Param("type") String type, @Param("start") int start, @Param("limit") int limit);

    /**
     * 根据商品ID查询评价总数
     * @param goodsId 商品ID
     * @param type 评价类型：all-全部，5-好评，4-四星，3-中评，2-二星，1-差评
     * @return 评价总数
     */
    int countByGoodsId(@Param("goodsId") Long goodsId, @Param("type") String type);

    /**
     * 根据商品ID查询平均评分
     * @param goodsId 商品ID
     * @return 平均评分
     */
    Double selectAverageRating(@Param("goodsId") Long goodsId);
}

package ltd.newbee.mall.mapper;

import ltd.newbee.mall.entity.UserCollect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户收藏Mapper接口
 */
public interface UserCollectMapper {
    /**
     * 添加收藏
     * @param userCollect 收藏信息
     * @return 影响行数
     */
    int insert(UserCollect userCollect);

    /**
     * 删除收藏
     * @param userId 用户ID
     * @param productId 商品ID
     * @return 影响行数
     */
    int deleteByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    /**
     * 检查是否已收藏
     * @param userId 用户ID
     * @param productId 商品ID
     * @return 收藏记录
     */
    UserCollect selectByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    /**
     * 获取用户收藏列表
     * @param userId 用户ID
     * @return 收藏列表
     */
    List<UserCollect> selectByUserId(Long userId);
}
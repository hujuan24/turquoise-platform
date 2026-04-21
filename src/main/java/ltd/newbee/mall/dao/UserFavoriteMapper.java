package ltd.newbee.mall.dao;

import ltd.newbee.mall.entity.UserFavorite;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserFavoriteMapper {
    int insert(UserFavorite record);
    int deleteByUserIdAndGoodsId(@Param("userId") Long userId, @Param("goodsId") Long goodsId);
    UserFavorite selectByUserIdAndGoodsId(@Param("userId") Long userId, @Param("goodsId") Long goodsId);
    List<UserFavorite> selectByUserId(@Param("userId") Long userId);
    int countByUserId(@Param("userId") Long userId);
}
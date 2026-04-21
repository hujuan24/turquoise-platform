package ltd.newbee.mall.service;

import ltd.newbee.mall.controller.vo.NewBeeMallGoodsDetailVO;
import java.util.List;

public interface UserFavoriteService {
    boolean addFavorite(Long userId, Long goodsId);
    boolean deleteFavorite(Long userId, Long goodsId);
    boolean isFavorite(Long userId, Long goodsId);
    List<NewBeeMallGoodsDetailVO> getFavoriteGoodsList(Long userId);
}
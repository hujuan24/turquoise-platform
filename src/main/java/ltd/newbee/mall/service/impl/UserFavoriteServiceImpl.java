package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.controller.vo.NewBeeMallGoodsDetailVO;
import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.dao.UserFavoriteMapper;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.UserFavorite;
import ltd.newbee.mall.service.UserFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserFavoriteServiceImpl implements UserFavoriteService {

    @Autowired
    private UserFavoriteMapper userFavoriteMapper;
    @Autowired
    private NewBeeMallGoodsMapper goodsMapper;

    @Override
    public boolean addFavorite(Long userId, Long goodsId) {
        UserFavorite exist = userFavoriteMapper.selectByUserIdAndGoodsId(userId, goodsId);
        if (exist != null) return false;
        UserFavorite record = new UserFavorite();
        record.setUserId(userId);
        record.setGoodsId(goodsId);
        return userFavoriteMapper.insert(record) > 0;
    }

    @Override
    public boolean deleteFavorite(Long userId, Long goodsId) {
        return userFavoriteMapper.deleteByUserIdAndGoodsId(userId, goodsId) > 0;
    }

    @Override
    public boolean isFavorite(Long userId, Long goodsId) {
        return userFavoriteMapper.selectByUserIdAndGoodsId(userId, goodsId) != null;
    }

    @Override
    public List<NewBeeMallGoodsDetailVO> getFavoriteGoodsList(Long userId) {
        List<UserFavorite> favorites = userFavoriteMapper.selectByUserId(userId);
        if (CollectionUtils.isEmpty(favorites)) return new ArrayList<>();
        List<NewBeeMallGoodsDetailVO> goodsList = new ArrayList<>();
        for (UserFavorite fav : favorites) {
            NewBeeMallGoods goods = goodsMapper.selectByPrimaryKey(fav.getGoodsId());
            if (goods != null && goods.getGoodsSellStatus() == 1) {
                NewBeeMallGoodsDetailVO vo = new NewBeeMallGoodsDetailVO();
                vo.setGoodsId(goods.getGoodsId());
                vo.setGoodsName(goods.getGoodsName());
                vo.setGoodsCoverImg(goods.getGoodsCoverImg());
                vo.setSellingPrice(goods.getSellingPrice());
                goodsList.add(vo);
            }
        }
        return goodsList;
    }
}
package ltd.newbee.mall.entity;

import java.util.Date;

public class UserFavorite {
    private Long favoriteId;
    private Long userId;
    private Long goodsId;
    private Date createTime;

    public Long getFavoriteId() { return favoriteId; }
    public void setFavoriteId(Long favoriteId) { this.favoriteId = favoriteId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getGoodsId() { return goodsId; }
    public void setGoodsId(Long goodsId) { this.goodsId = goodsId; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
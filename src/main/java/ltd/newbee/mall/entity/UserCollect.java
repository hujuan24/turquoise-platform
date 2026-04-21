package ltd.newbee.mall.entity;

import java.util.Date;

/**
 * 用户收藏实体类
 */
public class UserCollect {
    private Long id;
    private Long userId;
    private Long productId;
    private Date collectTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    @Override
    public String toString() {
        return "UserCollect{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", collectTime=" + collectTime +
                '}';
    }
}
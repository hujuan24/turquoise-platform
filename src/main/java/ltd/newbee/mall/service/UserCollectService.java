package ltd.newbee.mall.service;

import ltd.newbee.mall.common.ServiceResultEnum;

/**
 * 用户收藏Service接口
 */
public interface UserCollectService {
    /**
     * 添加收藏
     * @param userId 用户ID
     * @param productId 商品ID
     * @return 操作结果
     */
    ServiceResultEnum addCollect(Long userId, Long productId);

    /**
     * 取消收藏
     * @param userId 用户ID
     * @param productId 商品ID
     * @return 操作结果
     */
    ServiceResultEnum removeCollect(Long userId, Long productId);

    /**
     * 检查收藏状态
     * @param userId 用户ID
     * @param productId 商品ID
     * @return 是否已收藏
     */
    boolean checkCollectStatus(Long userId, Long productId);
}
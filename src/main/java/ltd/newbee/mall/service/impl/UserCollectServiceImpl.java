package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.entity.UserCollect;
import ltd.newbee.mall.mapper.UserCollectMapper;
import ltd.newbee.mall.service.UserCollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 用户收藏Service实现类
 */
@Service
public class UserCollectServiceImpl implements UserCollectService {

    @Resource
    private UserCollectMapper userCollectMapper;

    @Override
    public ServiceResultEnum addCollect(Long userId, Long productId) {
        // 检查是否已经收藏
        UserCollect existingCollect = userCollectMapper.selectByUserIdAndProductId(userId, productId);
        if (existingCollect != null) {
            return ServiceResultEnum.SUCCESS;
        }

        // 创建收藏记录
        UserCollect userCollect = new UserCollect();
        userCollect.setUserId(userId);
        userCollect.setProductId(productId);
        userCollect.setCollectTime(new Date());

        int result = userCollectMapper.insert(userCollect);
        if (result > 0) {
            return ServiceResultEnum.SUCCESS;
        } else {
            return ServiceResultEnum.DB_ERROR;
        }
    }

    @Override
    public ServiceResultEnum removeCollect(Long userId, Long productId) {
        int result = userCollectMapper.deleteByUserIdAndProductId(userId, productId);
        if (result > 0) {
            return ServiceResultEnum.SUCCESS;
        } else {
            return ServiceResultEnum.DB_ERROR;
        }
    }

    @Override
    public boolean checkCollectStatus(Long userId, Long productId) {
        UserCollect userCollect = userCollectMapper.selectByUserIdAndProductId(userId, productId);
        return userCollect != null;
    }
}
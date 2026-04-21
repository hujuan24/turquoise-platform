package ltd.newbee.mall.controller.mall;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.NewBeeMallUserVO;
import ltd.newbee.mall.service.UserCollectService;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 用户收藏Controller
 */
@Controller
@RequestMapping("/collect")
public class CollectController {

    @Resource
    private UserCollectService userCollectService;

    /**
     * 添加收藏
     * @param productId 商品ID
     * @param session 会话
     * @return 操作结果
     */
    @PostMapping("/add")
    @ResponseBody
    public Result addCollect(@RequestParam("productId") Long productId, HttpSession session) {
        // 检查登录状态
        NewBeeMallUserVO user = (NewBeeMallUserVO) session.getAttribute(Constants.MALL_USER_SESSION_KEY);
        if (user == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.NOT_LOGIN.getResult());
        }

        // 执行收藏操作
        Long userId = user.getUserId();
        ServiceResultEnum resultEnum = userCollectService.addCollect(userId, productId);
        if (resultEnum.equals(ServiceResultEnum.SUCCESS)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(resultEnum.getResult());
        }
    }

    /**
     * 取消收藏
     * @param productId 商品ID
     * @param session 会话
     * @return 操作结果
     */
    @PostMapping("/remove")
    @ResponseBody
    public Result removeCollect(@RequestParam("productId") Long productId, HttpSession session) {
        // 检查登录状态
        NewBeeMallUserVO user = (NewBeeMallUserVO) session.getAttribute(Constants.MALL_USER_SESSION_KEY);
        if (user == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.NOT_LOGIN.getResult());
        }

        // 执行取消收藏操作
        Long userId = user.getUserId();
        ServiceResultEnum resultEnum = userCollectService.removeCollect(userId, productId);
        if (resultEnum.equals(ServiceResultEnum.SUCCESS)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(resultEnum.getResult());
        }
    }

    /**
     * 检查收藏状态
     * @param productId 商品ID
     * @param session 会话
     * @return 收藏状态
     */
    @GetMapping("/check")
    @ResponseBody
    public Result checkCollectStatus(@RequestParam("productId") Long productId, HttpSession session) {
        // 检查登录状态
        NewBeeMallUserVO user = (NewBeeMallUserVO) session.getAttribute(Constants.MALL_USER_SESSION_KEY);
        if (user == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.NOT_LOGIN.getResult());
        }

        // 检查收藏状态
        Long userId = user.getUserId();
        boolean isCollected = userCollectService.checkCollectStatus(userId, productId);
        return ResultGenerator.genSuccessResult(isCollected);
    }
}
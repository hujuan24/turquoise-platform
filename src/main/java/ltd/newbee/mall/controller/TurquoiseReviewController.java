package ltd.newbee.mall.controller;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.controller.vo.NewBeeMallUserVO;
import ltd.newbee.mall.service.TurquoiseReviewService;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 绿松石商品评价控制器
 */
@Controller
@RequestMapping("/turquoise/review")
public class TurquoiseReviewController {

    @Resource
    private TurquoiseReviewService turquoiseReviewService;

    /**
     * 提交评价
     * @param goodsId 商品ID
     * @param rating 评分
     * @param content 评价内容
     * @param session 会话
     * @return 响应结果
     */
    @PostMapping("/add")
    @ResponseBody
    public Result addReview(@RequestParam("goodsId") Long goodsId, 
                           @RequestParam("rating") Integer rating, 
                           @RequestParam("content") String content, 
                           HttpSession session) {
        // 验证用户登录状态
        NewBeeMallUserVO user = (NewBeeMallUserVO) session.getAttribute(Constants.MALL_USER_SESSION_KEY);
        if (user == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.NOT_LOGIN.getResult());
        }

        // 验证参数
        if (goodsId == null || rating == null || rating < 1 || rating > 5 || !StringUtils.hasText(content)) {
            return ResultGenerator.genFailResult("参数错误");
        }

        // 调用服务添加评价
        String result = turquoiseReviewService.addReview(goodsId, user.getUserId(), user.getNickName(), rating, content);
        if ("success".equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    /**
     * 获取评价列表
     * @param goodsId 商品ID
     * @param type 评价类型：all-全部，5-好评，3-中评，1-差评
     * @param page 页码
     * @param limit 每页数量
     * @return 响应结果
     */
    @GetMapping("/list")
    @ResponseBody
    public Result getReviewList(@RequestParam("goodsId") Long goodsId, 
                              @RequestParam(value = "type", defaultValue = "all") String type, 
                              @RequestParam(value = "page", defaultValue = "1") Integer page, 
                              @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        // 验证参数
        if (goodsId == null) {
            return ResultGenerator.genFailResult("参数错误");
        }

        // 调用服务获取评价列表
        PageResult pageResult = turquoiseReviewService.getReviewList(goodsId, type, page, limit);
        return ResultGenerator.genSuccessResult(pageResult);
    }

    /**
     * 获取评价统计信息
     * @param goodsId 商品ID
     * @return 响应结果
     */
    @GetMapping("/stats/{goodsId}")
    @ResponseBody
    public Result getReviewStats(@PathVariable("goodsId") Long goodsId) {
        // 验证参数
        if (goodsId == null) {
            return ResultGenerator.genFailResult("参数错误");
        }

        // 调用服务获取评价统计信息
        return ResultGenerator.genSuccessResult(turquoiseReviewService.getReviewStats(goodsId));
    }
}

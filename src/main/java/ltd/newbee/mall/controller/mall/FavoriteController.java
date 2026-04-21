package ltd.newbee.mall.controller.mall;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.entity.MallUser;
import ltd.newbee.mall.service.UserFavoriteService;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {

    @Resource
    private UserFavoriteService userFavoriteService;

    @PostMapping("/add")
    @ResponseBody
    public Result addFavorite(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MallUser user = (MallUser) session.getAttribute(Constants.MALL_USER_SESSION_KEY);
        if (user == null) {
            return ResultGenerator.genFailResult("请先登录");
        }
        
        // 直接从请求中获取参数
        String goodsIdStr = request.getParameter("goodsId");
        if (goodsIdStr == null || goodsIdStr.isEmpty()) {
            return ResultGenerator.genFailResult("缺少goodsId参数");
        }
        
        Long goodsId = Long.parseLong(goodsIdStr);
        boolean result = userFavoriteService.addFavorite(user.getUserId(), goodsId);
        if (result) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("收藏失败或已收藏");
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result deleteFavorite(HttpServletRequest request, @RequestParam("goodsId") Long goodsId) {
        HttpSession session = request.getSession();
        MallUser user = (MallUser) session.getAttribute(Constants.MALL_USER_SESSION_KEY);
        if (user == null) {
            return ResultGenerator.genFailResult("请先登录");
        }
        boolean result = userFavoriteService.deleteFavorite(user.getUserId(), goodsId);
        if (result) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("取消收藏失败");
        }
    }

    @PostMapping("/check")
    @ResponseBody
    public Result checkFavorite(HttpServletRequest request, @RequestParam("goodsId") Long goodsId) {
        HttpSession session = request.getSession();
        MallUser user = (MallUser) session.getAttribute(Constants.MALL_USER_SESSION_KEY);
        if (user == null) {
            return ResultGenerator.genSuccessResult(false);
        }
        boolean result = userFavoriteService.isFavorite(user.getUserId(), goodsId);
        return ResultGenerator.genSuccessResult(result);
    }

    @GetMapping("/list")
    public String favoriteList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        MallUser user = (MallUser) session.getAttribute(Constants.MALL_USER_SESSION_KEY);
        if (user == null) {
            return "redirect:/login";
        }
        request.setAttribute("favoriteGoodsList", userFavoriteService.getFavoriteGoodsList(user.getUserId()));
        return "mall/favorite";
    }
}

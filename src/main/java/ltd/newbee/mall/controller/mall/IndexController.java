/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2019-2020 十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.newbee.mall.controller.mall;

import ltd.newbee.mall.common.Constants;
import ltd.newbee.mall.common.IndexConfigTypeEnum;
import ltd.newbee.mall.common.NewBeeMallException;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexCarouselVO;
import ltd.newbee.mall.controller.vo.NewBeeMallIndexCategoryVO;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.service.NewBeeMallCarouselService;
import ltd.newbee.mall.service.NewBeeMallIndexConfigService;
import ltd.newbee.mall.service.NewBeeMallCategoryService;
import ltd.newbee.mall.service.TurquoiseGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import java.util.ArrayList;



@Controller
public class IndexController {

    @Resource
    private NewBeeMallCarouselService newBeeMallCarouselService;

    @Resource
    private NewBeeMallIndexConfigService newBeeMallIndexConfigService;

    @Resource
    private NewBeeMallCategoryService newBeeMallCategoryService;
    
    @Resource
    private TurquoiseGoodsService turquoiseGoodsService;

    @GetMapping({"/index", "/", "/index.html"})
    public String indexPage(HttpServletRequest request) {
        List<NewBeeMallIndexCategoryVO> categories = newBeeMallCategoryService.getCategoriesForIndex();




//        if (CollectionUtils.isEmpty(categories)) {
//            NewBeeMallException.fail("分类数据不完善");
//        }
        if (CollectionUtils.isEmpty(categories)) {
            // 前端已使用静态菜单，不抛出异常
            categories = new ArrayList<>();
        }






        List<NewBeeMallIndexCarouselVO> carousels = newBeeMallCarouselService.getCarouselsForIndex(Constants.INDEX_CAROUSEL_NUMBER);
        // 直接从商品表获取上架商品
        List<NewBeeMallGoods> hotGoodses = turquoiseGoodsService.getOnSaleGoods(Constants.INDEX_GOODS_HOT_NUMBER);
        List<NewBeeMallGoods> newGoodses = turquoiseGoodsService.getOnSaleGoods(Constants.INDEX_GOODS_NEW_NUMBER);
        List<NewBeeMallGoods> recommendGoodses = turquoiseGoodsService.getOnSaleGoods(3);
        System.out.println("热销商品数量：" + hotGoodses.size());
        System.out.println("新品数量：" + newGoodses.size());
        System.out.println("馆藏精选数量：" + recommendGoodses.size());
        request.setAttribute("categories", categories);//分类数据
        request.setAttribute("carousels", carousels);//轮播图
        request.setAttribute("hotGoodses", hotGoodses);//热销商品
        request.setAttribute("newGoodses", newGoodses);//新品
        request.setAttribute("recommendGoodses", recommendGoodses);//推荐商品
        return "mall/index";
    }

    @GetMapping("/treasure")
    public String treasurePage(HttpServletRequest request) {
        List<NewBeeMallGoods> goodsList = turquoiseGoodsService.getOnSaleGoods(20);
        request.setAttribute("goodsList", goodsList);
        return "mall/treasure";
    }

    @GetMapping("/forum")
    public String forumPage() {
        return "mall/forum";
    }

    @GetMapping("/exhibition")
    public String exhibitionPage() {
        return "mall/exhibition";
    }

    @GetMapping("/appraisal")
    public String appraisalPage() {
        return "mall/appraisal";
    }
}

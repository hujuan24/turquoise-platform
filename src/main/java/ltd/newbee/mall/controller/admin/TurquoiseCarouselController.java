package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.entity.Carousel;
import ltd.newbee.mall.service.NewBeeMallCarouselService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class TurquoiseCarouselController {

    @Resource
    private NewBeeMallCarouselService carouselService;

    @GetMapping("/carousels")
    public String carouselPage(HttpServletRequest request) {
        request.setAttribute("path", "newbee_mall_carousel");
        return "admin/newbee_mall_carousel";
    }

    @RequestMapping(value = "/carousels/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(carouselService.getCarouselPage(pageUtil));
    }

    @GetMapping("/carousels/info/{carouselId}")
    @ResponseBody
    public Result info(@PathVariable Integer carouselId) {
        Carousel carousel = carouselService.getCarouselById(carouselId);
        if (carousel == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.DATA_NOT_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult(carousel);
    }

    @PostMapping("/carousels/save")
    @ResponseBody
    public Result save(@RequestBody Carousel carousel) {
        if (StringUtils.isEmpty(carousel.getCarouselUrl()) || StringUtils.isEmpty(carousel.getRedirectUrl())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        String result = carouselService.saveCarousel(carousel);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    @PostMapping("/carousels/update")
    @ResponseBody
    public Result update(@RequestBody Carousel carousel) {
        if (StringUtils.isEmpty(carousel.getCarouselUrl()) || StringUtils.isEmpty(carousel.getRedirectUrl()) || carousel.getCarouselId() == null) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        String result = carouselService.updateCarousel(carousel);
        if (ServiceResultEnum.SUCCESS.getResult().equals(result)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult(result);
        }
    }

    @PostMapping("/carousels/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (carouselService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}

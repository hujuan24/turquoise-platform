package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.entity.TurquoiseRefund;
import ltd.newbee.mall.service.TurquoiseRefundService;
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
public class TurquoiseRefundController {

    @Resource
    private TurquoiseRefundService turquoiseRefundService;

    @GetMapping("/refunds")
    public String refundsPage(HttpServletRequest request) {
        request.setAttribute("path", "refunds");
        return "admin/turquoise-admin-refunds";
    }

    @RequestMapping(value = "/refunds/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(turquoiseRefundService.getRefundsPage(pageUtil));
    }

    @PostMapping("/refunds/update-status")
    @ResponseBody
    public Result updateRefundStatus(@RequestParam("refundId") Long refundId, @RequestParam("refundStatus") Integer refundStatus) {
        if (refundId == null || refundStatus == null) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (turquoiseRefundService.updateRefundStatus(refundId, refundStatus)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败！");
        }
    }
}

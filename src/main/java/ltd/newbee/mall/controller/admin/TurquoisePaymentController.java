package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.entity.TurquoisePaymentFlow;
import ltd.newbee.mall.service.TurquoisePaymentFlowService;
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
public class TurquoisePaymentController {

    @Resource
    private TurquoisePaymentFlowService turquoisePaymentFlowService;

    @GetMapping("/payments")
    public String paymentsPage(HttpServletRequest request) {
        request.setAttribute("path", "payments");
        request.setAttribute("todayTotalAmount", turquoisePaymentFlowService.getTodayTotalAmount());
        request.setAttribute("totalCount", turquoisePaymentFlowService.getTotalCount());
        return "admin/turquoise-admin-payments";
    }

    @RequestMapping(value = "/payments/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(turquoisePaymentFlowService.getPaymentsPage(pageUtil));
    }

    @GetMapping("/payments/detail/{flowId}")
    @ResponseBody
    public Result getPaymentDetail(@PathVariable Long flowId) {
        TurquoisePaymentFlow payment = turquoisePaymentFlowService.getPaymentById(flowId);
        if (payment == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.PAYMENT_NOT_EXIST.getResult());
        }
        return ResultGenerator.genSuccessResult(payment);
    }
}

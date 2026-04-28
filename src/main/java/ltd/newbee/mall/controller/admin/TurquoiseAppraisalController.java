package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.entity.TurquoiseAppraisal;
import ltd.newbee.mall.service.TurquoiseAppraisalService;
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
public class TurquoiseAppraisalController {

    @Resource
    private TurquoiseAppraisalService turquoiseAppraisalService;

    @GetMapping("/appraisal")
    public String appraisalPage(HttpServletRequest request) {
        request.setAttribute("path", "appraisal");
        return "admin/turquoise-admin-appraisals";
    }

    @RequestMapping(value = "/appraisal/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(turquoiseAppraisalService.getAppraisalPage(pageUtil));
    }

    @GetMapping("/appraisal/detail/{appraisalId}")
    public String appraisalDetailPage(HttpServletRequest request, @PathVariable Long appraisalId) {
        Object appraisal = turquoiseAppraisalService.getAppraisalById(appraisalId);
        if (appraisal == null) {
            return "error/error_404";
        }
        request.setAttribute("appraisal", appraisal);
        request.setAttribute("path", "appraisal");
        return "admin/turquoise-admin-appraisal-detail";
    }

    @PostMapping("/appraisal/process")
    @ResponseBody
    public Result processAppraisal(@RequestBody TurquoiseAppraisal appraisal) {
        if (appraisal.getAppraisalId() == null || StringUtils.isEmpty(appraisal.getResult())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (turquoiseAppraisalService.processAppraisal(appraisal)) {
            return ResultGenerator.genSuccessResult("处理成功");
        } else {
            return ResultGenerator.genFailResult("处理失败");
        }
    }
}

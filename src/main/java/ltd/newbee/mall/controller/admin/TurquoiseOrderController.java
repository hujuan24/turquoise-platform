package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.entity.TurquoiseOrder;
import ltd.newbee.mall.entity.TurquoiseOrderItem;
import ltd.newbee.mall.service.TurquoiseOrderItemService;
import ltd.newbee.mall.service.TurquoiseOrderService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class TurquoiseOrderController {

    @Resource
    private TurquoiseOrderService turquoiseOrderService;
    @Resource
    private TurquoiseOrderItemService turquoiseOrderItemService;

    @GetMapping("/orders")
    public String ordersPage(HttpServletRequest request) {
        request.setAttribute("path", "orders");
        return "admin/turquoise-admin-orders";
    }

    @RequestMapping(value = "/orders/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(turquoiseOrderService.getTurquoiseOrdersPage(pageUtil));
    }

    @GetMapping("/orders/detail/{orderId}")
    @ResponseBody
    public Result getOrderDetail(@PathVariable Long orderId) {
        TurquoiseOrder order = turquoiseOrderService.getOrderById(orderId);
        if (order == null) {
            return ResultGenerator.genFailResult(ServiceResultEnum.ORDER_NOT_EXIST.getResult());
        }
        List<TurquoiseOrderItem> orderItems = turquoiseOrderItemService.getOrderItemsByOrderId(orderId);
        order.setOrderItems(orderItems);
        return ResultGenerator.genSuccessResult(order);
    }

    @PostMapping("/orders/update-status")
    @ResponseBody
    public Result updateOrderStatus(@RequestParam("orderId") Long orderId, @RequestParam("orderStatus") Integer orderStatus) {
        if (orderId == null || orderStatus == null) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (turquoiseOrderService.updateOrderStatus(orderId, orderStatus)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败！");
        }
    }
}

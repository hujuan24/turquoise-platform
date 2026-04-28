package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.entity.TurquoiseShowroom;
import ltd.newbee.mall.service.TurquoiseShowroomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 展厅管理Controller
 */
@Controller
@RequestMapping("/admin/content/showroom")
public class TurquoiseShowroomController {

    @Resource
    private TurquoiseShowroomService turquoiseShowroomService;

    /**
     * 展厅管理列表页
     */
    @GetMapping
    public String showroomList(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer limit,
                               @RequestParam(required = false) String showroomName,
                               Model model) {
        Map<String, Object> result = turquoiseShowroomService.getShowroomList(page, limit, showroomName);
        model.addAttribute("showroomList", result.get("list"));
        model.addAttribute("total", result.get("total"));
        model.addAttribute("page", result.get("page"));
        model.addAttribute("limit", result.get("limit"));
        model.addAttribute("showroomName", showroomName);
        return "admin/turquoise-admin-showroom";
    }

    /**
     * 新增展厅
     */
    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> addShowroom(@RequestBody TurquoiseShowroom showroom) {
        boolean success = turquoiseShowroomService.addShowroom(showroom);
        return Map.of("success", success, "message", success ? "新增成功" : "新增失败");
    }

    /**
     * 根据ID获取展厅信息
     */
    @GetMapping("/get/{id}")
    @ResponseBody
    public TurquoiseShowroom getShowroom(@PathVariable Long id) {
        return turquoiseShowroomService.getShowroomById(id);
    }

    /**
     * 更新展厅
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> updateShowroom(@RequestBody TurquoiseShowroom showroom) {
        boolean success = turquoiseShowroomService.updateShowroom(showroom);
        return Map.of("success", success, "message", success ? "更新成功" : "更新失败");
    }

    /**
     * 删除展厅
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> deleteShowroom(@PathVariable Long id) {
        boolean success = turquoiseShowroomService.deleteShowroom(id);
        return Map.of("success", success, "message", success ? "删除成功" : "删除失败");
    }

    /**
     * 更新展厅状态
     */
    @PostMapping("/status/{id}")
    @ResponseBody
    public Map<String, Object> updateStatus(@PathVariable Long id, @RequestParam Byte status) {
        boolean success = turquoiseShowroomService.updateShowroomStatus(id, status);
        return Map.of("success", success, "message", success ? "状态更新成功" : "状态更新失败");
    }
}
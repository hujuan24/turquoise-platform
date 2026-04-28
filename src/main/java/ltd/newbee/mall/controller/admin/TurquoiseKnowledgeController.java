package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.entity.TurquoiseKnowledge;
import ltd.newbee.mall.service.TurquoiseKnowledgeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 知识库Controller
 */
@Controller
@RequestMapping("/admin/content/knowledge")
public class TurquoiseKnowledgeController {

    @Resource
    private TurquoiseKnowledgeService turquoiseKnowledgeService;

    /**
     * 知识库列表页
     */
    @GetMapping
    public String knowledgeList(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer limit,
                                @RequestParam(required = false) String title,
                                @RequestParam(required = false) String category,
                                Model model) {
        Map<String, Object> result = turquoiseKnowledgeService.getKnowledgeList(page, limit, title, category);
        List<String> categories = turquoiseKnowledgeService.getAllCategories();
        
        model.addAttribute("knowledgeList", result.get("list"));
        model.addAttribute("total", result.get("total"));
        model.addAttribute("page", result.get("page"));
        model.addAttribute("limit", result.get("limit"));
        model.addAttribute("title", title);
        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        
        return "admin/turquoise-admin-knowledge";
    }

    /**
     * 新增知识
     */
    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> addKnowledge(@RequestBody TurquoiseKnowledge knowledge) {
        boolean success = turquoiseKnowledgeService.addKnowledge(knowledge);
        return Map.of("success", success, "message", success ? "新增成功" : "新增失败");
    }

    /**
     * 根据ID获取知识信息
     */
    @GetMapping("/get/{id}")
    @ResponseBody
    public TurquoiseKnowledge getKnowledge(@PathVariable Long id) {
        return turquoiseKnowledgeService.getKnowledgeById(id);
    }

    /**
     * 更新知识
     */
    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> updateKnowledge(@RequestBody TurquoiseKnowledge knowledge) {
        boolean success = turquoiseKnowledgeService.updateKnowledge(knowledge);
        return Map.of("success", success, "message", success ? "更新成功" : "更新失败");
    }

    /**
     * 删除知识
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> deleteKnowledge(@PathVariable Long id) {
        boolean success = turquoiseKnowledgeService.deleteKnowledge(id);
        return Map.of("success", success, "message", success ? "删除成功" : "删除失败");
    }

    /**
     * 更新知识状态
     */
    @PostMapping("/status/{id}")
    @ResponseBody
    public Map<String, Object> updateStatus(@PathVariable Long id, @RequestParam Byte status) {
        boolean success = turquoiseKnowledgeService.updateKnowledgeStatus(id, status);
        return Map.of("success", success, "message", success ? "状态更新成功" : "状态更新失败");
    }

    /**
     * 获取所有分类
     */
    @GetMapping("/categories")
    @ResponseBody
    public List<String> getAllCategories() {
        return turquoiseKnowledgeService.getAllCategories();
    }
}
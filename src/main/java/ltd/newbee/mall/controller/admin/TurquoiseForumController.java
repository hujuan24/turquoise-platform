package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.entity.TurquoiseForumPost;
import ltd.newbee.mall.service.TurquoiseForumPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 论坛管理Controller
 */
@Controller
@RequestMapping("/admin/content/forum")
public class TurquoiseForumController {

    @Resource
    private TurquoiseForumPostService turquoiseForumPostService;

    /**
     * 论坛管理列表页
     */
    @GetMapping
    public String forumList(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer limit,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) Integer status,
                           Model model) {
        Map<String, Object> result = turquoiseForumPostService.getPostList(page, limit, title, status);
        model.addAttribute("postList", result.get("list"));
        model.addAttribute("total", result.get("total"));
        model.addAttribute("page", result.get("page"));
        model.addAttribute("limit", result.get("limit"));
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        return "admin/turquoise-admin-forum";
    }

    /**
     * 根据ID获取帖子详情
     */
    @GetMapping("/detail/{id}")
    @ResponseBody
    public TurquoiseForumPost getPostDetail(@PathVariable Long id) {
        return turquoiseForumPostService.getPostById(id);
    }

    /**
     * 删除帖子
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> deletePost(@PathVariable Long id) {
        boolean success = turquoiseForumPostService.deletePost(id);
        return Map.of("success", success, "message", success ? "删除成功" : "删除失败");
    }

    /**
     * 更新帖子状态
     */
    @PostMapping("/status/{id}")
    @ResponseBody
    public Map<String, Object> updateStatus(@PathVariable Long id, @RequestParam Byte status) {
        boolean success = turquoiseForumPostService.updatePostStatus(id, status);
        return Map.of("success", success, "message", success ? "状态更新成功" : "状态更新失败");
    }
}
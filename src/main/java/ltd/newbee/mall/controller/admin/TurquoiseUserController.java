package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.entity.MallUser;
import ltd.newbee.mall.entity.UserCollect;
import ltd.newbee.mall.service.NewBeeMallUserService;
import ltd.newbee.mall.service.UserCollectService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class TurquoiseUserController {

    @Resource
    private NewBeeMallUserService newBeeMallUserService;

    @GetMapping("/users")
    public String users(Model model, @RequestParam(required = false) String keyword, 
                       @RequestParam(required = false, defaultValue = "1") Integer pageNumber) {
        Map params = new HashMap();
        params.put("page", pageNumber);
        params.put("limit", 10);
        if (!StringUtils.isEmpty(keyword)) {
            params.put("keyword", keyword);
        }
        
        PageResult pageResult = newBeeMallUserService.getNewBeeMallUsersPage(new PageQueryUtil(params));
        model.addAttribute("users", pageResult.getList());
        model.addAttribute("pageResult", pageResult);
        model.addAttribute("keyword", keyword);
        return "admin/turquoise-admin-users";
    }

    @GetMapping("/users/detail/{userId}")
    public String userDetail(Model model, @PathVariable Long userId) {
        MallUser user = newBeeMallUserService.getUserById(userId);
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "admin/turquoise-admin-user-detail";
    }

    @PostMapping("/users/lock")
    @ResponseBody
    public Map<String, Object> lockUser(@RequestParam Long userId, @RequestParam Integer lockStatus) {
        Map<String, Object> result = new HashMap<>();
        Integer[] ids = {userId.intValue()};
        Boolean success = newBeeMallUserService.lockUsers(ids, lockStatus);
        if (success) {
            result.put("resultCode", 200);
            result.put("message", lockStatus == 1 ? "禁用成功" : "启用成功");
        } else {
            result.put("resultCode", 500);
            result.put("message", "操作失败");
        }
        return result;
    }

    @GetMapping("/users/favorite")
    public String favorites(Model model, @RequestParam(required = false) String keyword,
                           @RequestParam(required = false, defaultValue = "1") Integer pageNumber) {
        Map params = new HashMap();
        params.put("page", pageNumber);
        params.put("limit", 10);
        if (!StringUtils.isEmpty(keyword)) {
            params.put("keyword", keyword);
        }
        
        PageResult pageResult = newBeeMallUserService.getCollectList(new PageQueryUtil(params));
        model.addAttribute("favorites", pageResult.getList());
        model.addAttribute("pageResult", pageResult);
        model.addAttribute("keyword", keyword);
        return "admin/turquoise-admin-favorites";
    }
}
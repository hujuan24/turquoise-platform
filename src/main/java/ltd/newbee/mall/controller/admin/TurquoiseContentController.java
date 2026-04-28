package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.common.ServiceResultEnum;
import ltd.newbee.mall.entity.TurquoiseContent;
import ltd.newbee.mall.service.TurquoiseContentService;
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
public class TurquoiseContentController {

    @Resource
    private TurquoiseContentService turquoiseContentService;

    @GetMapping("/content")
    public String contentPage(HttpServletRequest request) {
        request.setAttribute("path", "content");
        return "admin/turquoise-admin-content";
    }

    @RequestMapping(value = "/content/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(turquoiseContentService.getContentPage(pageUtil));
    }

    @GetMapping("/content/add")
    public String addPage(HttpServletRequest request) {
        request.setAttribute("path", "content-add");
        return "admin/turquoise-admin-content-add";
    }

    @GetMapping("/content/edit/{contentId}")
    public String editPage(HttpServletRequest request, @PathVariable Long contentId) {
        Object content = turquoiseContentService.getContentById(contentId);
        if (content == null) {
            return "error/error_404";
        }
        request.setAttribute("content", content);
        request.setAttribute("path", "content-edit");
        return "admin/turquoise-admin-content-edit";
    }

    @PostMapping("/content/save")
    @ResponseBody
    public Result save(@RequestBody TurquoiseContent content) {
        if (StringUtils.isEmpty(content.getTitle()) || StringUtils.isEmpty(content.getContent())) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (turquoiseContentService.saveContent(content)) {
            return ResultGenerator.genSuccessResult("添加成功");
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @PostMapping("/content/update")
    @ResponseBody
    public Result update(@RequestBody TurquoiseContent content) {
        if (StringUtils.isEmpty(content.getTitle()) || StringUtils.isEmpty(content.getContent()) || content.getContentId() == null) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (turquoiseContentService.updateContent(content)) {
            return ResultGenerator.genSuccessResult("修改成功");
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }

    @PostMapping("/content/delete/{contentId}")
    @ResponseBody
    public Result delete(@PathVariable Long contentId) {
        if (contentId == null) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (turquoiseContentService.deleteContent(contentId)) {
            return ResultGenerator.genSuccessResult("删除成功");
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }

    @PostMapping("/content/publish/{contentId}")
    @ResponseBody
    public Result publish(@PathVariable Long contentId) {
        if (contentId == null) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (turquoiseContentService.updateContentStatus(contentId, 1)) {
            return ResultGenerator.genSuccessResult("发布成功");
        } else {
            return ResultGenerator.genFailResult("发布失败");
        }
    }

    @PostMapping("/content/offline/{contentId}")
    @ResponseBody
    public Result offline(@PathVariable Long contentId) {
        if (contentId == null) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (turquoiseContentService.updateContentStatus(contentId, 0)) {
            return ResultGenerator.genSuccessResult("下线成功");
        } else {
            return ResultGenerator.genFailResult("下线失败");
        }
    }
}

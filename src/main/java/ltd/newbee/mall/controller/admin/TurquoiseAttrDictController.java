package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.entity.TurquoiseAttrDict;
import ltd.newbee.mall.mapper.TurquoiseAttrDictMapper;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TurquoiseAttrDictController {
    
    @Resource
    private TurquoiseAttrDictMapper turquoiseAttrDictMapper;
    
    // 属性字典管理页面
    @GetMapping("/attr-dict")
    public String attrDictPage(Model model) {
        try {
            // 查询所有属性，按类型分组
            List<TurquoiseAttrDict> originList = turquoiseAttrDictMapper.selectListByType("origin");
            List<TurquoiseAttrDict> colorList = turquoiseAttrDictMapper.selectListByType("color");
            List<TurquoiseAttrDict> gradeList = turquoiseAttrDictMapper.selectListByType("grade");
            
            // 确保列表不为null
            if (originList == null) originList = new ArrayList<>();
            if (colorList == null) colorList = new ArrayList<>();
            if (gradeList == null) gradeList = new ArrayList<>();
            
            model.addAttribute("originList", originList);
            model.addAttribute("colorList", colorList);
            model.addAttribute("gradeList", gradeList);
        } catch (Exception e) {
            System.out.println("查询属性字典失败：" + e.getMessage());
            e.printStackTrace();
        }
        return "admin/turquoise-admin-attr-dict";
    }
    
    // 新增属性
    @PostMapping("/attr-dict/save")
    @ResponseBody
    public Result saveAttrDict(@RequestBody TurquoiseAttrDict attrDict) {
        try {
            int result = turquoiseAttrDictMapper.insert(attrDict);
            if (result > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("保存失败");
            }
        } catch (Exception e) {
            System.out.println("保存属性失败：" + e.getMessage());
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }
    
    // 更新属性
    @PostMapping("/attr-dict/update")
    @ResponseBody
    public Result updateAttrDict(@RequestBody TurquoiseAttrDict attrDict) {
        try {
            int result = turquoiseAttrDictMapper.updateByPrimaryKey(attrDict);
            if (result > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("更新失败");
            }
        } catch (Exception e) {
            System.out.println("更新属性失败：" + e.getMessage());
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }
    
    // 删除属性（软删除）
    @PostMapping("/attr-dict/delete")
    @ResponseBody
    public Result deleteAttrDict(@RequestBody TurquoiseAttrDict attrDict) {
        try {
            int result = turquoiseAttrDictMapper.deleteByPrimaryKey(attrDict.getAttrId());
            if (result > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("删除失败");
            }
        } catch (Exception e) {
            System.out.println("删除属性失败：" + e.getMessage());
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }
    
    // 根据ID查询属性
    @GetMapping("/attr-dict/{id}")
    @ResponseBody
    public Result getAttrDictById(@PathVariable Long id) {
        try {
            TurquoiseAttrDict attrDict = turquoiseAttrDictMapper.selectByPrimaryKey(id);
            if (attrDict != null) {
                return ResultGenerator.genSuccessResult(attrDict);
            } else {
                return ResultGenerator.genFailResult("属性不存在");
            }
        } catch (Exception e) {
            System.out.println("查询属性失败：" + e.getMessage());
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }
}
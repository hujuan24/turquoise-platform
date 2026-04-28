package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.entity.TurquoiseAttrDict;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.service.TurquoiseAttrDictService;
import ltd.newbee.mall.dao.GoodsCategoryMapper;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TurquoiseCategoryController {

    @Resource
    private TurquoiseAttrDictService turquoiseAttrDictService;

    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;

    @GetMapping("/categories")
    public String categories(Model model) {
        List<TurquoiseAttrDict> originList = turquoiseAttrDictService.getAttrListByType("origin");
        List<GoodsCategory> typeList = goodsCategoryMapper.selectAll();
        List<TurquoiseAttrDict> colorList = turquoiseAttrDictService.getAttrListByType("color");
        List<TurquoiseAttrDict> densityList = turquoiseAttrDictService.getAttrListByType("grade");

        model.addAttribute("originList", originList);
        model.addAttribute("typeList", typeList);
        model.addAttribute("colorList", colorList);
        model.addAttribute("densityList", densityList);

        return "admin/turquoise-admin-categories";
    }

    @GetMapping("/categories/list")
    @ResponseBody
    public Result listCategories(@RequestParam String type) {
        try {
            if ("type".equals(type)) {
                List<GoodsCategory> typeList = goodsCategoryMapper.selectAll();
                return ResultGenerator.genSuccessResult(typeList);
            } else {
                List<TurquoiseAttrDict> list = turquoiseAttrDictService.getAttrListByType(type);
                return ResultGenerator.genSuccessResult(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("查询失败");
        }
    }

    @PostMapping("/categories/save")
    @ResponseBody
    public Result saveAttr(@RequestParam String attrType, @RequestParam String attrName) {
        try {
            if ("type".equals(attrType)) {
                GoodsCategory category = new GoodsCategory();
                category.setCategoryName(attrName);
                category.setCategoryLevel((byte) 3);
                category.setParentId(0L);
                category.setCategoryRank(0);
                category.setIsDeleted((byte) 0);
                int result = goodsCategoryMapper.insertSelective(category);
                if (result > 0) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("保存失败");
                }
            } else {
                TurquoiseAttrDict attrDict = new TurquoiseAttrDict();
                attrDict.setAttrType(attrType);
                attrDict.setAttrName(attrName);
                attrDict.setAttrCode(attrName);
                attrDict.setSortOrder(0);
                attrDict.setIsDeleted(0);

                boolean result = turquoiseAttrDictService.saveAttr(attrDict);
                if (result) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("保存失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }

    @PostMapping("/categories/delete")
    @ResponseBody
    public Result deleteAttr(@RequestParam Long attrId) {
        try {
            boolean result = turquoiseAttrDictService.deleteAttr(attrId);
            if (result) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }

    @PostMapping("/category/delete")
    @ResponseBody
    public Result deleteCategory(@RequestParam Long categoryId) {
        try {
            int result = goodsCategoryMapper.deleteByPrimaryKey(categoryId);
            if (result > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }

    @PostMapping("/category/update")
    @ResponseBody
    public Result updateCategory(@RequestParam Long categoryId, @RequestParam String categoryName) {
        try {
            GoodsCategory category = new GoodsCategory();
            category.setCategoryId(categoryId);
            category.setCategoryName(categoryName);
            int result = goodsCategoryMapper.updateByPrimaryKeySelective(category);
            if (result > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }

    @GetMapping("/category/list")
    @ResponseBody
    public Result listAllCategories() {
        try {
            List<GoodsCategory> typeList = goodsCategoryMapper.selectAll();
            return ResultGenerator.genSuccessResult(typeList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("查询失败");
        }
    }

    @PostMapping("/category/save")
    @ResponseBody
    public Result saveCategory(@RequestParam String categoryName) {
        try {
            GoodsCategory category = new GoodsCategory();
            category.setCategoryName(categoryName);
            category.setCategoryLevel((byte) 3);
            category.setParentId(0L);
            category.setCategoryRank(0);
            category.setIsDeleted((byte) 0);
            int result = goodsCategoryMapper.insertSelective(category);
            if (result > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }
}
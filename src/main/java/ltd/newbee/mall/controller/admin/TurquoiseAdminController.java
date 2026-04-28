package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.common.Constants;

import ltd.newbee.mall.dao.MallUserMapper;
import ltd.newbee.mall.dao.NewBeeMallGoodsMapper;
import ltd.newbee.mall.dao.GoodsCategoryMapper;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.entity.MallUser;

import ltd.newbee.mall.service.AdminUserService;
import ltd.newbee.mall.service.TurquoiseUserCollectService;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import java.util.Collections;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ltd.newbee.mall.entity.TurquoiseAttrDict;
import ltd.newbee.mall.mapper.TurquoiseAttrDictMapper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class TurquoiseAdminController {
    
    @Resource
    private AdminUserService adminUserService;
    

    
    @Resource
    private MallUserMapper userMapper;
    
    @Resource
    private NewBeeMallGoodsMapper goodsMapper;
    
    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;
    
    @Resource
    private TurquoiseAttrDictMapper turquoiseAttrDictMapper;
    
    @Resource
    private TurquoiseUserCollectService turquoiseUserCollectService;
    
    // 数据驾驶舱
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        try {
            // 今日销售额 - 临时设置默认值
            Integer todaySales = 0;
            model.addAttribute("todaySales", todaySales);
            
            // 今日订单数 - 临时设置默认值
            Integer todayOrders = 0;
            model.addAttribute("todayOrders", todayOrders);
            
            // 今日新增会员 - 临时设置默认值
            Integer todayNewMembers = 0;
            model.addAttribute("todayNewMembers", todayNewMembers);
            
            // 商品总数 - 临时设置默认值
            Integer totalGoods = 0;
            model.addAttribute("totalGoods", totalGoods);
            
            // 库存预警 - 临时设置默认值
            Integer stockAlert = 0;
            model.addAttribute("stockAlert", stockAlert);
            
            // 待处理鉴定 - 临时设置默认值
            Integer pendingAppraisals = 0;
            model.addAttribute("pendingAppraisals", pendingAppraisals);
            
        } catch (Exception e) {
            System.out.println("查询仪表盘数据失败：" + e.getMessage());
            e.printStackTrace();
        }
        return "admin/turquoise-admin-dashboard";
    }
    
    // 商品管理
    @GetMapping("/products")
    public String productsPage(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit,
                            @RequestParam(required = false) String keyword,
                            @RequestParam(required = false) Long categoryId,
                            @RequestParam(required = false) Integer status,
                            Model model) {
        System.out.println("进入 productsPage 方法");
        System.out.println("查询参数：page=" + page + ", limit=" + limit + ", keyword=" + keyword + ", categoryId=" + categoryId + ", status=" + status);
        try {
            // 构建查询参数
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("page", page);
            params.put("limit", limit);
            params.put("keyword", keyword);
            params.put("goodsCategoryId", categoryId);
            params.put("goodsSellStatus", status);
            
            // 创建分页查询对象
            PageQueryUtil pageUtil = new PageQueryUtil(params);
            
            // 打印分页参数
            System.out.println("分页参数：" + pageUtil.toString());
            System.out.println("start：" + pageUtil.get("start"));
            
            // 查询商品列表
            List<NewBeeMallGoods> goodsList = goodsMapper.findNewBeeMallGoodsListBySearch(pageUtil);
            int total = goodsMapper.getTotalNewBeeMallGoodsBySearch(pageUtil);
            
            System.out.println("查询到商品数量：" + goodsList.size());
            System.out.println("总商品数量：" + total);
            System.out.println("商品列表: " + goodsList);
            
            // 打印商品详情
            for (NewBeeMallGoods goods : goodsList) {
                System.out.println("商品ID：" + goods.getGoodsId() + "，商品名称：" + goods.getGoodsName() + "，状态：" + goods.getGoodsSellStatus() + "，分类ID：" + goods.getGoodsCategoryId());
            }
            
            // 计算总页数
            int totalPages = (total + limit - 1) / limit;
            
            // 从数据库查询分类列表，只保留 5 个核心品类
            List<GoodsCategory> categoryList = new ArrayList<>();
            GoodsCategory category1 = new GoodsCategory();
            category1.setCategoryId(1L);
            category1.setCategoryName("高瓷蓝");
            categoryList.add(category1);
            
            GoodsCategory category2 = new GoodsCategory();
            category2.setCategoryId(2L);
            category2.setCategoryName("乌兰花");
            categoryList.add(category2);
            
            GoodsCategory category3 = new GoodsCategory();
            category3.setCategoryId(3L);
            category3.setCategoryName("菜籽黄");
            categoryList.add(category3);
            
            GoodsCategory category4 = new GoodsCategory();
            category4.setCategoryId(4L);
            category4.setCategoryName("玉化料");
            categoryList.add(category4);
            
            GoodsCategory category5 = new GoodsCategory();
            category5.setCategoryId(5L);
            category5.setCategoryName("铁线松");
            categoryList.add(category5);
            
            Map<Long, String> categoryMap = new HashMap<>();
            for (GoodsCategory category : categoryList) {
                categoryMap.put(category.getCategoryId(), category.getCategoryName());
            }
            
            // 从数据库查询属性字典数据
            List<TurquoiseAttrDict> originList = turquoiseAttrDictMapper.selectListByType("origin");
            List<TurquoiseAttrDict> gradeList = turquoiseAttrDictMapper.selectListByType("grade");
            List<TurquoiseAttrDict> colorList = turquoiseAttrDictMapper.selectListByType("color");
            
            // 确保列表不为null
            if (originList == null) originList = new ArrayList<>();
            if (gradeList == null) gradeList = new ArrayList<>();
            if (colorList == null) colorList = new ArrayList<>();
            
            // 将数据添加到模型
            model.addAttribute("page", page);
            model.addAttribute("limit", limit);
            model.addAttribute("keyword", keyword);
            model.addAttribute("categoryId", categoryId);
            model.addAttribute("status", status);
            model.addAttribute("total", total);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("goodsList", goodsList);
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("categoryMap", categoryMap);
            model.addAttribute("originList", originList);
            model.addAttribute("gradeList", gradeList);
            model.addAttribute("colorList", colorList);
            
        } catch (Exception e) {
            System.out.println("查询商品列表失败：" + e.getMessage());
            e.printStackTrace();
            // 发生异常时设置默认值
            model.addAttribute("page", page);
            model.addAttribute("limit", limit);
            model.addAttribute("keyword", keyword);
            model.addAttribute("status", status);
            model.addAttribute("total", 0);
            model.addAttribute("totalPages", 0);
            model.addAttribute("goodsList", null);
        }
        return "admin/turquoise-admin-products";
    }
    
    // 添加商品 - 现在通过模态框实现，此方法重定向到商品列表页面
    @GetMapping("/products/add")
    public String addProduct() {
        System.out.println("进入 addProduct 方法，重定向到商品列表页面");
        return "redirect:/admin/products";
    }
    
    // 保存商品
    @PostMapping("/products/save")
    public String saveProduct(NewBeeMallGoods goods) {
        System.out.println("进入 saveProduct 方法，商品名称：" + goods.getGoodsName());
        try {
            // 插入商品信息
            int result = goodsMapper.insertSelective(goods);
            System.out.println("保存商品结果：" + result);
        } catch (Exception e) {
            System.out.println("保存商品失败：" + e.getMessage());
            e.printStackTrace();
        }
        // 重定向回商品列表页面
        return "redirect:/admin/products";
    }
    
    // 编辑商品
    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        System.out.println("进入 editProduct 方法，商品ID：" + id);
        try {
            // 根据商品ID查询商品详情
            NewBeeMallGoods goods = goodsMapper.selectByPrimaryKey(id);
            System.out.println("查询到商品：" + (goods == null ? "null" : goods.getGoodsName()));
            
            if (goods != null) {
                // 将商品数据添加到模型
                model.addAttribute("goods", goods);
            } else {
                System.out.println("商品不存在：" + id);
                // 商品不存在时添加空对象，避免页面报错
                model.addAttribute("goods", new NewBeeMallGoods());
            }
            
            // 只保留 5 个核心品类
            List<GoodsCategory> categoryList = new ArrayList<>();
            GoodsCategory category1 = new GoodsCategory();
            category1.setCategoryId(1L);
            category1.setCategoryName("高瓷蓝");
            categoryList.add(category1);
            
            GoodsCategory category2 = new GoodsCategory();
            category2.setCategoryId(2L);
            category2.setCategoryName("乌兰花");
            categoryList.add(category2);
            
            GoodsCategory category3 = new GoodsCategory();
            category3.setCategoryId(3L);
            category3.setCategoryName("菜籽黄");
            categoryList.add(category3);
            
            GoodsCategory category4 = new GoodsCategory();
            category4.setCategoryId(4L);
            category4.setCategoryName("玉化料");
            categoryList.add(category4);
            
            GoodsCategory category5 = new GoodsCategory();
            category5.setCategoryId(5L);
            category5.setCategoryName("铁线松");
            categoryList.add(category5);
            
            // 从数据库查询属性字典数据
            List<TurquoiseAttrDict> originList = turquoiseAttrDictMapper.selectListByType("origin");
            List<TurquoiseAttrDict> gradeList = turquoiseAttrDictMapper.selectListByType("grade");
            List<TurquoiseAttrDict> colorList = turquoiseAttrDictMapper.selectListByType("color");
            
            // 确保列表不为null
            if (originList == null) originList = new ArrayList<>();
            if (gradeList == null) gradeList = new ArrayList<>();
            if (colorList == null) colorList = new ArrayList<>();
            
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("originList", originList);
            model.addAttribute("gradeList", gradeList);
            model.addAttribute("colorList", colorList);
        } catch (Exception e) {
            System.out.println("查询商品详情失败：" + e.getMessage());
            e.printStackTrace();
            // 发生异常时添加空对象，避免页面报错
            model.addAttribute("goods", new NewBeeMallGoods());
        }
        return "admin/turquoise-admin-product-edit";
    }
    
    // 更新商品
    @PostMapping("/products/update")
    public String updateProduct(NewBeeMallGoods goods) {
        System.out.println("进入 updateProduct 方法，商品ID：" + goods.getGoodsId());
        try {
            // 更新商品信息
            int result = goodsMapper.updateByPrimaryKeySelective(goods);
            System.out.println("更新商品结果：" + result);
        } catch (Exception e) {
            System.out.println("更新商品失败：" + e.getMessage());
            e.printStackTrace();
        }
        // 重定向回商品列表页面
        return "redirect:/admin/products";
    }
    

    
    // 用户中心
    @GetMapping("/members")
    public String membersPage(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer limit,
                              @RequestParam(required = false) String keyword,
                              @RequestParam(required = false) Integer status,
                              Model model) {
        System.out.println("进入 membersPage 方法");
        try {
            // 构建查询参数
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("page", page);
            params.put("limit", limit);
            params.put("keyword", keyword);
            params.put("status", status);
            
            // 创建分页查询对象
            PageQueryUtil pageUtil = new PageQueryUtil(params);
            
            // 从数据库读取用户列表
            List<MallUser> userList = userMapper.findMallUserList(pageUtil);
            int total = userMapper.getTotalMallUsers(pageUtil);
            
            System.out.println("查询到用户数量：" + userList.size());
            System.out.println("总用户数量：" + total);
            
            model.addAttribute("userList", userList);
            model.addAttribute("page", page);
            model.addAttribute("limit", limit);
            model.addAttribute("total", total);
            model.addAttribute("keyword", keyword);
            model.addAttribute("status", status);
            model.addAttribute("path", "members");
        } catch (Exception e) {
            System.out.println("查询用户列表失败：" + e.getMessage());
            e.printStackTrace();
            model.addAttribute("userList", new ArrayList<>());
            model.addAttribute("page", page);
            model.addAttribute("limit", limit);
            model.addAttribute("total", 0);
            model.addAttribute("keyword", keyword);
            model.addAttribute("status", status);
        }
        return "admin/turquoise-admin-members";
    }
    

    
    // 鉴定中心
    @GetMapping("/appraisals")
    public String appraisals() {
        return "admin/turquoise-admin-appraisals";
    }
    
    // 系统设置
    @GetMapping("/settings")
    public String settings() {
        return "admin/turquoise-admin-settings";
    }
    

    
    // 用户详情
    @GetMapping("/members/detail/{id}")
    public String memberDetail(Model model) {
        model.addAttribute("path", "members");
        return "admin/turquoise-admin-member-detail";
    }
    

    

    
    // 收藏分析
    @GetMapping("/collections")
    public String collections(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @RequestParam(required = false) String keyword,
                             @RequestParam(required = false) String timeRange,
                             Model model) {
        try {
            // 获取统计数据
            Map<String, Object> statistics = turquoiseUserCollectService.getCollectStatistics();
            model.addAttribute("totalCollects", statistics.get("totalCollects"));
            model.addAttribute("todayCollects", statistics.get("todayCollects"));
            model.addAttribute("avgCollectsPerUser", statistics.get("avgCollectsPerUser"));
            model.addAttribute("activeCollectUsers", statistics.get("activeCollectUsers"));
            
            // 获取热门商品排行
            List<Map<String, Object>> hotProducts = turquoiseUserCollectService.getHotProducts();
            if (hotProducts == null) {
                hotProducts = new ArrayList<>();
            }
            model.addAttribute("hotProducts", hotProducts);
            
            // 获取收藏记录列表
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("page", page);
            params.put("limit", limit);
            params.put("keyword", keyword);
            params.put("timeRange", timeRange);
            
            PageQueryUtil pageUtil = new PageQueryUtil(params);
            PageResult pageResult = turquoiseUserCollectService.getCollectList(pageUtil);
            
            List<Map<String, Object>> collectList = new ArrayList<>();
            if (pageResult != null && pageResult.getList() != null) {
                collectList = (List<Map<String, Object>>) pageResult.getList();
            }
            model.addAttribute("collectList", collectList);
            model.addAttribute("page", page);
            model.addAttribute("limit", limit);
            model.addAttribute("total", pageResult.getTotalCount());
            model.addAttribute("keyword", keyword);
            model.addAttribute("timeRange", timeRange);
            model.addAttribute("path", "collections");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("totalCollects", 0);
            model.addAttribute("todayCollects", 0);
            model.addAttribute("avgCollectsPerUser", 0);
            model.addAttribute("activeCollectUsers", 0);
            model.addAttribute("hotProducts", new ArrayList<>());
            model.addAttribute("collectList", new ArrayList<>());
            model.addAttribute("page", page);
            model.addAttribute("limit", limit);
            model.addAttribute("total", 0);
            model.addAttribute("keyword", keyword);
        }
        return "admin/turquoise-admin-collections";
    }
    
    // 取消收藏
    @PostMapping("/collections/cancel/{collectId}")
    @ResponseBody
    public Result cancelCollect(@PathVariable Long collectId) {
        if (turquoiseUserCollectService.cancelCollect(collectId)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("取消收藏失败");
    }
    
    // 展厅管理
    @GetMapping("/exhibition")
    public String exhibition() {
        return "admin/turquoise-admin-exhibition";
    }
    
    // 知识库
    @GetMapping("/knowledge")
    public String knowledge() {
        return "admin/turquoise-admin-knowledge";
    }
    
    // 鉴定师管理
    @GetMapping("/appraisers")
    public String appraisers() {
        return "admin/turquoise-admin-appraisers";
    }
    
    // 管理员账户
    @GetMapping("/admin-accounts")
    public String adminAccounts() {
        return "admin/turquoise-admin-accounts";
    }
    
    // 角色权限
    @GetMapping("/roles")
    public String roles() {
        return "admin/turquoise-admin-roles";
    }
    
    // 操作日志
    @GetMapping("/logs")
    public String logs() {
        return "admin/turquoise-admin-logs";
    }
    
    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.ADMIN_SESSION_KEY);
        return "redirect:/admin/login";
    }
    
    // 登录页
    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }
    
    // 登录提交
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam("role") String role,
                       HttpSession session) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            // 重定向回登录页，显示错误信息
            return "redirect:/admin/login?error=用户名或密码不能为空";
        }
        
        // 临时调试：使用adminUserService查询用户
        System.out.println("登录用户名：" + username);
        System.out.println("登录密码：" + password);
        
        try {
            // 调用adminUserService进行登录验证
            ltd.newbee.mall.entity.AdminUser adminUser = adminUserService.login(username, password);
            System.out.println("查到的用户：" + (adminUser == null ? "null" : adminUser.getLoginUserName()));
            
            if (adminUser != null) {
                session.setAttribute(Constants.ADMIN_SESSION_KEY, adminUser);
                return "redirect:/admin/dashboard";
            } else {
                return "redirect:/admin/login?error=登录失败，请检查用户名和密码";
            }
        } catch (Exception e) {
            System.out.println("登录异常：" + e.getMessage());
            e.printStackTrace();
            return "redirect:/admin/login?error=登录失败，请检查系统日志";
        }
    }
    
    // 更新商品状态
    @PostMapping("/products/status")
    @ResponseBody
    public Result updateStatus(@RequestParam Long goodsId, @RequestParam Integer status) {
        System.out.println("进入 updateStatus 方法");
        try {
            System.out.println("更新商品状态：goodsId=" + goodsId + ", status=" + status);
            
            // 更新商品状态
            NewBeeMallGoods goods = new NewBeeMallGoods();
            goods.setGoodsId(goodsId);
            goods.setGoodsSellStatus(status.byteValue());
            int result = goodsMapper.updateByPrimaryKeySelective(goods);
            
            if (result > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("更新失败");
            }
        } catch (Exception e) {
            System.out.println("更新商品状态失败：" + e.getMessage());
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }
    
    // 删除商品
    @PostMapping("/products/delete")
    @ResponseBody
    public Result deleteProduct(@RequestParam Long goodsId) {
        System.out.println("进入 deleteProduct 方法");
        try {
            System.out.println("删除商品：goodsId=" + goodsId);
            
            // 删除商品
            int result = goodsMapper.deleteByPrimaryKey(goodsId);
            
            if (result > 0) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("删除失败");
            }
        } catch (Exception e) {
            System.out.println("删除商品失败：" + e.getMessage());
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }
}
package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.entity.TurquoiseCertificate;
import ltd.newbee.mall.service.TurquoiseCertificateService;
import ltd.newbee.mall.service.NewBeeMallGoodsService;
import ltd.newbee.mall.entity.NewBeeMallGoods;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TurquoiseCertificateController {

    @Resource
    private TurquoiseCertificateService turquoiseCertificateService;

    @Resource
    private NewBeeMallGoodsService newBeeMallGoodsService;

    @GetMapping("/certificates")
    public String certificates(Model model) {
        // 查询证书列表
        List<TurquoiseCertificate> certificateList = turquoiseCertificateService.getCertificateList();
        // 查询商品列表（用于下拉框）
        List<NewBeeMallGoods> goodsList = newBeeMallGoodsService.getOnSaleGoods(1000); // 限制1000个商品

        model.addAttribute("certificateList", certificateList);
        model.addAttribute("goodsList", goodsList);

        return "admin/turquoise-admin-certificates";
    }

    @PostMapping("/certificates/generate-no")
    @ResponseBody
    public Result generateCertificateNo() {
        try {
            String certificateNo = turquoiseCertificateService.generateCertificateNo();
            return ResultGenerator.genSuccessResult(certificateNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("生成证书编号失败");
        }
    }

    @PostMapping("/certificates/save")
    @ResponseBody
    public Result saveCertificate(@RequestParam Long goodsId, @RequestParam String certificateOrg, @RequestParam String certificateResult, @RequestParam String certificateDate) {
        try {
            // 自动生成证书编号
            String certificateNo = turquoiseCertificateService.generateCertificateNo();
            
            TurquoiseCertificate certificate = new TurquoiseCertificate();
            certificate.setGoodsId(goodsId);
            certificate.setCertificateNo(certificateNo);
            certificate.setCertificateOrg(certificateOrg);
            certificate.setCertificateResult(certificateResult);
            certificate.setCertificateDate(certificateDate);

            boolean result = turquoiseCertificateService.saveCertificate(certificate);
            if (result) {
                return ResultGenerator.genSuccessResult();
            } else {
                return ResultGenerator.genFailResult("保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }

    @PostMapping("/certificates/delete")
    @ResponseBody
    public Result deleteCertificate(@RequestParam Long certificateId) {
        try {
            boolean result = turquoiseCertificateService.deleteCertificate(certificateId);
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
    
    @GetMapping("/certificates/detail/{certificateId}")
    @ResponseBody
    public Result getCertificateDetail(@PathVariable Long certificateId) {
        try {
            TurquoiseCertificate certificate = turquoiseCertificateService.getCertificateById(certificateId);
            if (certificate != null) {
                return ResultGenerator.genSuccessResult(certificate);
            } else {
                return ResultGenerator.genFailResult("证书不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }
    
    @PostMapping("/certificates/update")
    @ResponseBody
    public Result updateCertificate(@RequestParam Long certificateId, @RequestParam String certificateOrg, @RequestParam String certificateResult, @RequestParam String certificateDate, @RequestParam(required = false) String certificateImg) {
        try {
            TurquoiseCertificate certificate = turquoiseCertificateService.getCertificateById(certificateId);
            if (certificate != null) {
                certificate.setCertificateOrg(certificateOrg);
                certificate.setCertificateResult(certificateResult);
                certificate.setCertificateDate(certificateDate);
                if (certificateImg != null && !certificateImg.isEmpty()) {
                    certificate.setCertificateImg(certificateImg);
                }
                
                boolean result = turquoiseCertificateService.updateCertificate(certificate);
                if (result) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("更新失败");
                }
            } else {
                return ResultGenerator.genFailResult("证书不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("操作失败");
        }
    }
}

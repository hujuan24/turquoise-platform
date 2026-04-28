package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.entity.TurquoiseCertificate;
import ltd.newbee.mall.mapper.TurquoiseCertificateMapper;
import ltd.newbee.mall.service.TurquoiseCertificateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TurquoiseCertificateServiceImpl implements TurquoiseCertificateService {

    @Resource
    private TurquoiseCertificateMapper turquoiseCertificateMapper;

    @Override
    public List<TurquoiseCertificate> getCertificateList() {
        return turquoiseCertificateMapper.selectList();
    }

    @Override
    public boolean saveCertificate(TurquoiseCertificate certificate) {
        return turquoiseCertificateMapper.insert(certificate) > 0;
    }

    @Override
    public boolean deleteCertificate(Long certificateId) {
        return turquoiseCertificateMapper.deleteByPrimaryKey(certificateId) > 0;
    }

    @Override
    public String generateCertificateNo() {
        try {
            // 获取当前日期，格式化为yyyyMMdd
            String dateStr = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
            // 查询当天已有证书数量
            int count = turquoiseCertificateMapper.countByDate(dateStr);
            // 生成证书编号：NG + 日期 + 4位序号
            return "NG" + dateStr + String.format("%04d", count + 1);
        } catch (Exception e) {
            e.printStackTrace();
            // 发生异常时返回默认编号
            return "NG" + new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        }
    }

    @Override
    public TurquoiseCertificate getCertificateById(Long certificateId) {
        return turquoiseCertificateMapper.selectByPrimaryKey(certificateId);
    }

    @Override
    public boolean updateCertificate(TurquoiseCertificate certificate) {
        return turquoiseCertificateMapper.updateByPrimaryKey(certificate) > 0;
    }
}

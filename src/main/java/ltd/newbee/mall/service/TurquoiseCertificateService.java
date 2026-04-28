package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.TurquoiseCertificate;
import java.util.List;

public interface TurquoiseCertificateService {
    /**
     * 获取证书列表
     * @return 证书列表
     */
    List<TurquoiseCertificate> getCertificateList();

    /**
     * 保存证书
     * @param certificate 证书实体
     * @return 是否保存成功
     */
    boolean saveCertificate(TurquoiseCertificate certificate);

    /**
     * 删除证书
     * @param certificateId 证书ID
     * @return 是否删除成功
     */
    boolean deleteCertificate(Long certificateId);
    
    /**
     * 生成证书编号
     * @return 证书编号
     */
    String generateCertificateNo();
    
    /**
     * 根据ID获取证书详情
     * @param certificateId 证书ID
     * @return 证书详情
     */
    TurquoiseCertificate getCertificateById(Long certificateId);
    
    /**
     * 更新证书
     * @param certificate 证书实体
     * @return 是否更新成功
     */
    boolean updateCertificate(TurquoiseCertificate certificate);
}

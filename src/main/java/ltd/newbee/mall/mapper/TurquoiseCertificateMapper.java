package ltd.newbee.mall.mapper;

import ltd.newbee.mall.entity.TurquoiseCertificate;
import java.util.List;

public interface TurquoiseCertificateMapper {
    /**
     * 获取证书列表
     * @return 证书列表
     */
    List<TurquoiseCertificate> selectList();

    /**
     * 插入证书
     * @param record 证书实体
     * @return 影响行数
     */
    int insert(TurquoiseCertificate record);

    /**
     * 根据主键删除证书
     * @param certificateId 证书ID
     * @return 影响行数
     */
    int deleteByPrimaryKey(Long certificateId);

    /**
     * 根据主键查询证书
     * @param certificateId 证书ID
     * @return 证书实体
     */
    TurquoiseCertificate selectByPrimaryKey(Long certificateId);

    /**
     * 更新证书
     * @param record 证书实体
     * @return 影响行数
     */
    int updateByPrimaryKey(TurquoiseCertificate record);
    
    /**
     * 根据日期查询证书数量
     * @param dateStr 日期字符串，格式为yyyyMMdd
     * @return 证书数量
     */
    int countByDate(String dateStr);
}

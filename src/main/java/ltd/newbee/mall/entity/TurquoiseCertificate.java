package ltd.newbee.mall.entity;

import java.util.Date;

public class TurquoiseCertificate {
    private Long certificateId;
    private Long goodsId;
    private String certificateNo;
    private String certificateOrg;
    private String certificateResult;
    private String certificateImg;
    private String certificateDate;
    private Integer isDeleted;
    private Date createTime;
    private Date updateTime;
    private String goodsName; // 非数据库字段，用于显示商品名称

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getCertificateOrg() {
        return certificateOrg;
    }

    public void setCertificateOrg(String certificateOrg) {
        this.certificateOrg = certificateOrg;
    }

    public String getCertificateResult() {
        return certificateResult;
    }

    public void setCertificateResult(String certificateResult) {
        this.certificateResult = certificateResult;
    }

    public String getCertificateImg() {
        return certificateImg;
    }

    public void setCertificateImg(String certificateImg) {
        this.certificateImg = certificateImg;
    }

    public String getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(String certificateDate) {
        this.certificateDate = certificateDate;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}

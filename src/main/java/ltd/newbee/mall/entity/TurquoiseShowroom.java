package ltd.newbee.mall.entity;

import java.util.Date;

/**
 * 展厅实体类
 */
public class TurquoiseShowroom {

    private Long showroomId;
    private String showroomName;
    private String showroomDesc;
    private String coverImg;
    private Integer sort;
    private Byte status;
    private Date createTime;
    private Date updateTime;
    private Byte isDeleted;

    public TurquoiseShowroom() {
    }

    public Long getShowroomId() {
        return showroomId;
    }

    public void setShowroomId(Long showroomId) {
        this.showroomId = showroomId;
    }

    public String getShowroomName() {
        return showroomName;
    }

    public void setShowroomName(String showroomName) {
        this.showroomName = showroomName;
    }

    public String getShowroomDesc() {
        return showroomDesc;
    }

    public void setShowroomDesc(String showroomDesc) {
        this.showroomDesc = showroomDesc;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "TurquoiseShowroom{" +
                "showroomId=" + showroomId +
                ", showroomName='" + showroomName + '\'' +
                ", showroomDesc='" + showroomDesc + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", sort=" + sort +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
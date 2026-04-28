package ltd.newbee.mall.entity;

import java.util.Date;

/**
 * 知识库实体类
 */
public class TurquoiseKnowledge {

    private Long knowledgeId;
    private String title;
    private String category;
    private String content;
    private String coverImg;
    private String author;
    private Integer viewCount;
    private Byte status;
    private Date createTime;
    private Date updateTime;
    private Byte isDeleted;

    public TurquoiseKnowledge() {
    }

    public Long getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Long knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
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
        return "TurquoiseKnowledge{" +
                "knowledgeId=" + knowledgeId +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", author='" + author + '\'' +
                ", viewCount=" + viewCount +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
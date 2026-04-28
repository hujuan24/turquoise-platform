package ltd.newbee.mall.entity;

import java.util.Date;

public class TurquoisePaymentFlow {
    private Long flowId;
    private Long orderId;
    private Integer payAmount;
    private Byte payType;
    private Byte payStatus;
    private Date payTime;
    private String transactionNo;
    private Date createTime;

    public Long getFlowId() {
        return flowId;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TurquoisePaymentFlow{" +
                "flowId=" + flowId +
                ", orderId=" + orderId +
                ", payAmount=" + payAmount +
                ", payType=" + payType +
                ", payStatus=" + payStatus +
                ", payTime=" + payTime +
                ", transactionNo='" + transactionNo + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

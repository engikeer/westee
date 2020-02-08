package com.mfun.pojo;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    public static final int STATUS_UNFILLED = 0;
    public static final int STATUS_FILLED = 1;
    public static final int STATUS_COMPLETED = 2;
    private String orderId;  // 订单号，有意义的唯一标识，不用于计算。简单实现：时间戳 + 用户 id
    private Date createDate;
    private double totalPrice;
    // TODO: 将 status 改为 OrderStatus，通过重写映射函数在数据库中保存整数，对象中保存枚举值
    // 在 BaseDaoImpl 中重写 RowProcessor 并传给 Handler 的构造函数
    private int status;
    private int userId;

    public Order() {}

    public Order(String orderId, int status) {
        setOrderId(orderId);
        setStatus(status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createDate=" + createDate +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

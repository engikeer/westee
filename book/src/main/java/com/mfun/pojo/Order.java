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

    public String getStatusDesc() {
        OrderStatus orderStatus = OrderStatus.getOrderStatus(status);
        return orderStatus == null ? "未知状态" : orderStatus.getDesc();
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


    private enum OrderStatus {
        UNFILLED(0, "未发货"),
        FILLED(1, "已发货"),
        COMPLETED(2, "订单完成");

        private int value;
        private String desc;

        OrderStatus(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public static OrderStatus getOrderStatus(int i) {
            for (OrderStatus status : OrderStatus.values()) {
                if (i == status.value) {
                    return status;
                }
            }
            return null;
        }

        public int getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }
    }
}

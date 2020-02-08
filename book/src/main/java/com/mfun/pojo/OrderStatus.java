package com.mfun.pojo;

public enum OrderStatus {
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
        for (OrderStatus status : values()) {
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

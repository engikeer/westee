package com.mfun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItem implements Serializable {
    private int id;
    // 订单应保存购买当时的全部信息
    // 不能只保存物品 id，根据 id 查询得到的数据可能与购买时不同
    private int bookId; // 用户获取图书的当前信息
    private String title;
    // 单价
    private double price;
    private int count;
//    private String imgPath; // 暂不实现
    private String orderId;  // 所属的订单

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", bookTitle='" + title + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", orderId='" + orderId + '\'' +
                '}';
    }

    /**
     * 获取总价
     * @return 总价
     */
    public double getTotalPrice() {
        BigDecimal price = new BigDecimal(String.valueOf(getPrice()));
        BigDecimal count = new BigDecimal(getCount());
        return price.multiply(count).doubleValue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

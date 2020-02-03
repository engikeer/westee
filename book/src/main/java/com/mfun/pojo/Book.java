package com.mfun.pojo;

import java.io.Serializable;

public class Book implements Serializable {

    private static final String defaultImgPath = "static/img/default.jpg";

    private Integer id;
    private String title;
    private String author;
    private double price;
    private int sales;
    private int stock;
    /**
     * 封面图片存储路径
     * 从项目根目录开始的相对路径
     */
    private String imgPath;

    public Book() {}

    public Book(Integer id, String title, String author,
                double price, int sales, int stock, String imgPath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + getImgPath() + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        // 数据库可以保存空值，但获取时返回当前的默认图片路径
        // 避免因默认图片路径修改而需要修改数据库
        return imgPath != null ? imgPath : defaultImgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}

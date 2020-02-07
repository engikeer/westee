package com.mfun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {
    private Book book;
    private int count;

    public CartItem() {}

    public CartItem(Book book, int count) {
        this.book = book;
        this.count = count;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "bookID=" + book.getId() +
                ", bookTitle=" + book.getTitle() +
                ", count=" + count +
                '}';
    }

    /**
     * 计算金额
     * @return 本购物项的金额
     */
    public double getPrice() {
        BigDecimal price = new BigDecimal(String.valueOf(book.getPrice()));
        BigDecimal count = new BigDecimal(getCount());
        return price.multiply(count).doubleValue();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

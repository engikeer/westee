package com.mfun.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

public class Cart implements Serializable {
    // 为了便于查找和删除购物车项，将其放在 map 中，bookId 为 key
    private Map<Integer, CartItem> items = new LinkedHashMap<>();
//    private int totalCount;  // 商品总数，计算得到
//    private double totalPrice;  // 总金额，计算得到

    /**
     * 获取总商品数
     * @return 购物车中所有商品总数目
     */
    public int getTotalCount() {
        int count = 0;
        for (CartItem item : items.values()) {
            count += item.getCount();
        }
        return count;
    }

    /**
     * 获取总金额
     * @return 购物车中所有商品的总金额
     */
    public double getTotalPrice() {
        BigDecimal price = new BigDecimal(0);
        for (CartItem item : items.values()) {
            price = price.add(new BigDecimal(String.valueOf(item.getPrice())));
        }
        return price.doubleValue();
    }

    public void addCartItem(CartItem item) {
        items.put(item.getBook().getId(), item);
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }
}

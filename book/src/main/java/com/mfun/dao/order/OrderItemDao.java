package com.mfun.dao.order;

import com.mfun.pojo.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDao {
    List<OrderItem> getListForOrder(String orderId) throws SQLException;
    int save(OrderItem item) throws SQLException;
}

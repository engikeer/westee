package com.mfun.dao.order;

import com.mfun.pojo.Order;
import com.mfun.pojo.OrderItem;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    int save(Order order) throws SQLException;
    int updateStatus(Order order) throws SQLException;
    List<Order> getList() throws SQLException;  // 获取所有订单，用于管理
    List<Order> getListForUser(int userId) throws SQLException;
}

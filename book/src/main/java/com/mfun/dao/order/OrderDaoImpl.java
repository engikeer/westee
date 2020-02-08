package com.mfun.dao.order;

import com.mfun.dao.BaseDaoImpl;
import com.mfun.pojo.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public int save(Order order) throws SQLException {
        String sql = "INSERT INTO bs_order VALUES (?, ?, ?, ?, ?)";
        return update(sql, order.getOrderId(), order.getCreateDate(),
                order.getTotalPrice(), order.getStatus(), order.getUserId());
    }

    @Override
    public int updateStatus(Order order) throws SQLException {
        String sql = "UPDATE bs_order SET status=? WHERE order_id = ?";
        return update(sql, order.getStatus(), order.getOrderId());
    }

    @Override
    public List<Order> getList() throws SQLException {
        String sql = "SELECT order_id orderId, create_time createDate," +
                " total_price totalPrice, status, user_id userId FROM bs_order";
        return getBeanList(sql);
    }

    @Override
    public List<Order> getListForUser(int userId) throws SQLException {
        String sql = "SELECT order_id orderId, create_time createDate," +
                " total_price totalPrice, status, user_id userId" +
                " FROM bs_order WHERE user_id=?";
        return getBeanList(sql, userId);
    }
}

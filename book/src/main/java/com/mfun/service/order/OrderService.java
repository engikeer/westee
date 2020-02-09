package com.mfun.service.order;

import com.mfun.pojo.Cart;
import com.mfun.pojo.Order;
import com.mfun.pojo.OrderItem;
import com.mfun.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {

    /**
     * 订单结算
     * @param cart 要结算的购物车
     * @return 订单 ID
     */
    String checkout(Cart cart, User user) throws SQLException;
    boolean updateStatus(String orderId, int status);
    List<Order> getAllOrders() throws SQLException;
    List<Order> getOrderForUser(int userId) throws SQLException;

    List<OrderItem> getOrderItems(String orderId) throws SQLException;
    boolean saveItems(OrderItem item);

}

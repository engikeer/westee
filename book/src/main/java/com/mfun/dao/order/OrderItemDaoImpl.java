package com.mfun.dao.order;

import com.mfun.dao.BaseDaoImpl;
import com.mfun.pojo.OrderItem;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDaoImpl extends BaseDaoImpl<OrderItem> implements OrderItemDao {

    public OrderItemDaoImpl() {
        super(OrderItem.class);
    }

    @Override
    public List<OrderItem> getListForOrder(String orderId) throws SQLException {
        String sql = "SELECT id, book_id bookId, title, price, count, order_id orderID" +
                " FROM bs_order_item WHERE order_id=?";
        return getBeanList(sql, orderId);
    }

    @Override
    public int save(OrderItem item) throws SQLException {
        String sql = "INSERT INTO bs_order_item VALUES (null, ?, ?, ?, ?, ?)";
        return update(sql, item.getBookId(), item.getTitle(),
                item.getPrice(), item.getCount(), item.getOrderId());
    }
}

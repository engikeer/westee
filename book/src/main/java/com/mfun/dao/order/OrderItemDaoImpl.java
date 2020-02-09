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

    @Override
    public int[] batchSave(List<OrderItem> items) throws SQLException {
        String sql = "INSERT INTO bs_order_item VALUES (null, ?, ?, ?, ?, ?)";
        int n = items.size();
        // params 第一维是执行几次，第二维是参数数量，所以，param[i] 就是一条语句所需的所有参数
        Object[][] params = new Object[n][5];
        for (int i = 0; i < n; i++) {
            OrderItem item = items.get(i);
            params[i] = new Object[] {item.getBookId(), item.getTitle(),
                    item.getPrice(), item.getCount(), item.getOrderId()};
        }

        return batchUpdate(sql, params);
    }
}

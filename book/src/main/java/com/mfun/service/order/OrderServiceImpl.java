package com.mfun.service.order;

import com.mfun.dao.book.BookDao;
import com.mfun.dao.book.BookDaoImpl;
import com.mfun.dao.order.OrderDao;
import com.mfun.dao.order.OrderDaoImpl;
import com.mfun.dao.order.OrderItemDao;
import com.mfun.dao.order.OrderItemDaoImpl;
import com.mfun.pojo.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    OrderItemDao itemDao = new OrderItemDaoImpl();
    OrderDao orderDao = new OrderDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    @Override
    public String checkout(Cart cart, User user) throws SQLException {
        // TODO 是否会出错，会出错就需要原子性和回滚
        // 1. 创建订单对象并生成订单 ID
        Order order = new Order();
        long millis = System.currentTimeMillis();
        String orderId = String.valueOf(millis) + user.getId();
        order.setOrderId(orderId);
        Date date = new Date();
        order.setCreateDate(date);

        // 2. 封装订单对象
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus(Order.STATUS_UNFILLED);
        order.setUserId(user.getId());
        // 3. 保存订单对象
        orderDao.save(order);

        // 4. 封装订单项对象
        for (CartItem item : cart.getItems().values()) {
            OrderItem orderItem = new OrderItem();
            int bookId = item.getBook().getId();
            orderItem.setBookId(bookId);
            orderItem.setTitle(item.getBook().getTitle());
            orderItem.setPrice(item.getBook().getPrice());
            int count = item.getCount();
            orderItem.setCount(count);
            orderItem.setOrderId(orderId);
            // 5. 保存订单项
            itemDao.save(orderItem);
            // 6. 修改图书库存
            Book book = bookDao.getBookById(new Book(bookId));
            book.setSales(book.getSales() + count);
            book.setStock(book.getStock() - count);
            bookDao.updateBook(book);
        }
        return orderId;
    }

    @Override
    public boolean updateStatus(String orderId, int status) {
        try {
            orderDao.updateStatus(new Order(orderId, status));
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Order> getAllOrder() throws SQLException {
        return orderDao.getList();
    }

    @Override
    public List<Order> getOrderForUser(int userId) throws SQLException {
        return orderDao.getListForUser(userId);
    }

    @Override
    public List<OrderItem> getOrderItems(String orderId) throws SQLException {
        return itemDao.getListForOrder(orderId);
    }

    @Override
    public boolean saveItems(OrderItem item) {
        try {
            return itemDao.save(item) > 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

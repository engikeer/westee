package com.mfun.dao.order;

import com.mfun.dao.BaseDaoImpl;
import com.mfun.pojo.Order;
import com.mfun.utils.ConnectionUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

//    @Override
//    public Order getBean(String sql, Object... params) throws SQLException {
//        QueryRunner runner = ConnectionUtils.getRunner();
//        BeanHandler<Order> handler = new BeanHandler<>(Order.class, new BasicRowProcessor(new MyBeanProcessor()));
//        return runner.query(sql, handler, params);
//    }
//
//    @Override
//    public List<Order> getBeanList(String sql, Object... params) throws SQLException {
//        QueryRunner runner = ConnectionUtils.getRunner();
//        BeanListHandler<Order> handler = new BeanListHandler<>(Order.class, new BasicRowProcessor(new MyBeanProcessor()));
//        return runner.query(sql, handler, params);
//    }

    // 通过索引表实现，不再需要转换
//    private class MyBeanProcessor extends BeanProcessor {
//        @Override
//        public <T> T toBean(ResultSet rs, Class<? extends T> type) throws SQLException {
//            return super.toBean(rs, type);
//        }
//
//        @Override
//        public <T> List<T> toBeanList(ResultSet rs, Class<? extends T> type) throws SQLException {
//            return super.toBeanList(rs, type);
//        }
//
//        @Override
//        protected Object processColumn(ResultSet rs, int index, Class<?> propType) throws SQLException {
//            return super.processColumn(rs, index, propType);
//        }
//    }
}

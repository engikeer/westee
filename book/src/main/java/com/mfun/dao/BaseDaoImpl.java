package com.mfun.dao;

import com.mfun.utils.ConnectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {
    private final Class<T> type;

//    /**
//     * 通过反射捕获实际的类型参数，必须实例化子类对象时调用，不能直接实例化该类的对象
//     * 两次未检查的类型转换：由于直接实例化本类或者类型参数不是类对象都会抛出异常，所以不采用这种方式
//     */
//    public BaseDaoImpl() {
//        // 获取泛型类的类型
//        // 必须在子类中调用该构造方法，不能直接实例化父类
//        // 因为父类的父类是 Object 类，无法转为参数化类型
//        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
//        // 获取参数的类型，T 必须是原始类型
//        type = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
//    }

    /**
     * 用于实现子类的无参构造器
     * 直接传入类型参数，更安全
     * @param type 类型参数的类对象
     */
    protected BaseDaoImpl(Class<T> type) {
        this.type = type;
    }

    @Override
    public T getBean(String sql, Object... params)
            throws SQLException {
        QueryRunner runner = ConnectionUtils.getRunner();
        return runner.query(sql, new BeanHandler<>(type), params);
    }

    @Override
    public List<T> getBeanList(String sql, Object... params) throws SQLException {
        QueryRunner runner = ConnectionUtils.getRunner();
        return runner.query(sql, new BeanListHandler<>(type), params);
    }

    @Override
    public <U> U getScalar(String sql, Object... params) throws SQLException {
        QueryRunner runner = ConnectionUtils.getRunner();
        return runner.query(sql, new ScalarHandler<>(), params);
    }

    // TODO: 之后实现
    @Override
    public boolean updateBean(T bean) {
        return false;
    }

    // TODO: 之后实现
    @Override
    public boolean deleteBean(T bean) {
        return false;
    }

    @Override
    public int update(String sql, Object... params) throws SQLException {
        QueryRunner runner = ConnectionUtils.getRunner();
        return runner.update(sql, params);
    }
}

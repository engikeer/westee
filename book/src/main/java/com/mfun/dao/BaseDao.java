package com.mfun.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T> {

    T getBean(String sql, Object... params) throws SQLException;

    List<T> getBeanList(String sql, Object... params) throws SQLException;

    boolean updateBean(T bean);

    boolean deleteBean(T bean);

    /**
     * 执行通用更新操作，包括插入、删除、修改。
     * @param sql 更新语句
     * @param params 参数的值
     * @return 影响的函数
     */
    int update(String sql, Object... params) throws SQLException;
}

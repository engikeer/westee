package com.mfun.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T> {

    T getBean(String sql, Object... params) throws SQLException;

    List<T> getBeanList(String sql, Object... params) throws SQLException;

    <U> U getScalar(String sql, Object... params) throws SQLException;

    boolean updateBean(T bean);

    boolean deleteBean(T bean);

    /**
     * 执行通用更新操作，包括插入、删除、修改。
     * @param sql 更新语句
     * @param params 参数的值
     * @return 影响的函数
     */
    int update(String sql, Object... params) throws SQLException;

    /**
     * 执行批量更新，一次执行多条更新语句
     * @param sql 更新语句
     * @param params 第一维是执行几次，第二维是参数数量，所以，param[i] 就是一条语句所需的所有参数
     * @return 所有语句影响的行数
     * @throws SQLException 查询异常
     */
    int[] batchUpdate(String sql, Object[][] params) throws SQLException;
}

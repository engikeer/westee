package com.mfun.dao.user;

import com.mfun.pojo.User;

import java.sql.SQLException;

public interface UserDao {
    /**
     * 获取要登陆的用户
     * @param userCode 用户名
     * @param password 密码
     * @return 查询到的用户可能为空
     * @throws SQLException SQL 查询异常
     */
    public User getLoginUser(String userCode, String password) throws SQLException;

    /**
     * 更新当前用户的密码
     * @param id 用户 id
     * @param password 密码
     * @return 数据库受影响的记录数
     * @throws SQLException SQL 查询异常
     */
    public int updatePassword(long id, String password) throws SQLException;

}

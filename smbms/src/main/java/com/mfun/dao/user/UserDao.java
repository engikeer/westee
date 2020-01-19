package com.mfun.dao.user;

import com.mfun.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    /**
     * 获取要登陆的用户
     * @param userCode 用户名
     * @param password 密码
     * @return 查询到的用户可能为空
     * @throws SQLException SQL 异常
     */
    User getLoginUser(String userCode, String password) throws SQLException;

    /**
     * 更新当前用户的密码
     * @param id 用户 id
     * @param password 密码
     * @return 数据库受影响的记录数
     * @throws SQLException SQL 异常
     */
    int updatePassword(long id, String password) throws SQLException;

    /**
     * 根据名称和角色查询符合的用户数量
     * @param username 名称
     * @param userRole 角色
     * @return 记录数
     * @throws SQLException SQL 异常
     */
    int getUserCount(String username, int userRole) throws SQLException;

    /**
     * 根据名称和角色查询符合的用户列表
     * @param username 名称
     * @param userRole 角色
     * @param currentPageNo 当前页码
     * @param pageSize 页面大小
     * @return 用户列表
     * @throws SQLException SQL 异常
     */
    List<User> getUserList(
            String username, int userRole, int currentPageNo, int pageSize)
            throws SQLException;

}

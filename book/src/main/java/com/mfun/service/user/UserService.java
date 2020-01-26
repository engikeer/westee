package com.mfun.service.user;

import com.mfun.pojo.User;

import java.sql.SQLException;

public interface UserService {

    /**
     * 登陆
     * @return 匹配的用户
     * @throws SQLException 查询出错
     */
    User login(User user) throws SQLException;

    /**
     * 注册
     * @return 是否成功
     * @throws SQLException 由于用户名重复，新增用户失败
     */
    boolean logon(User user) throws SQLException;
}

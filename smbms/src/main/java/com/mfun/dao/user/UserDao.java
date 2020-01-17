package com.mfun.dao.user;

import com.mfun.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    public User getLoginUser(String userCode, String password) throws SQLException;
}

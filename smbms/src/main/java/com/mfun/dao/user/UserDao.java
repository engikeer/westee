package com.mfun.dao.user;

import com.mfun.pojo.User;

import java.sql.Connection;

public interface UserDao {
    public User getLoginUser(String userCode);
}

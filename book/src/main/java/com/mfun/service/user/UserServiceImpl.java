package com.mfun.service.user;

import com.mfun.dao.user.UserDao;
import com.mfun.dao.user.UserDaoImpl;
import com.mfun.pojo.User;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User user) throws SQLException {
        return userDao.getUserByNameAndPassword(user);
    }

    @Override
    public boolean logon(User user) throws SQLException {
        return userDao.insertUser(user);
    }
}

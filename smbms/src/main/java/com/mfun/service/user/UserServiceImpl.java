package com.mfun.service.user;

import com.mfun.dao.user.UserDao;
import com.mfun.dao.user.UserDaoImpl;
import com.mfun.pojo.User;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userCode, String password) throws SQLException {
        return userDao.getLoginUser(userCode, password);
    }

    @Override
    public boolean updatePassword(long id, String password) throws SQLException {
        int i = userDao.updatePassword(id, password);
        return i == 1;
    }
}

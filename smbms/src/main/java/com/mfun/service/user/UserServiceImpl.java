package com.mfun.service.user;

import com.mfun.dao.user.UserDao;
import com.mfun.dao.user.UserDaoImpl;
import com.mfun.pojo.User;

import java.sql.SQLException;
import java.util.List;

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

    @Override
    public int getUserCount(String username, int userRole) throws SQLException {
        return userDao.getUserCount(username, userRole);
    }

    @Override
    public List<User> getUserList(String username, int userRole, int currentPageNo, int pageSize) throws SQLException {
        return userDao.getUserList(username, userRole, currentPageNo, pageSize);
    }
}

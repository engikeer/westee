package com.mfun.dao;

import com.mfun.dao.user.UserDao;
import com.mfun.dao.user.UserDaoImpl;
import com.mfun.pojo.User;
import com.mfun.utils.ConnectionUtils;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {

    @Test
    public void getUserTest() throws Exception {
        User user = new User(null, "庄颜", "logic", null);
        UserDao userDao = new UserDaoImpl();
        User data = userDao.getUserByNameAndPassword(user);
        assertNotNull(data);
        user = new User(null, "庄颜", "revenge", null);
        data = userDao.getUserByNameAndPassword(user);
        assertNull(data);

    }

    @Test
    public void insertUserTest() throws Exception {
        User user = new User(null, "测试", "test", "test@test.com");
        UserDao userDao = new UserDaoImpl();
        if (userDao.insertUser(user)) {
            assertEquals("test@test.com", userDao.getUserByNameAndPassword(user).getEmail());
            if (!userDao.deleteUserByNameAndPassword(user)) {
                throw new AssertionFailedError("移除测试记录失败");
            }
        } else {
            throw new AssertionFailedError("执行插入失败");
        }

    }

}

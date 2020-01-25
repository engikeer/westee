package com.mfun.dao;

import com.mfun.pojo.User;
import com.mfun.utils.ConnectionUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoTest {

    @Test
    public void getBeanTest() throws Exception{
        UserDao userDao = new UserDao();
        User user = userDao.getBean("SELECT * FROM bs_user");
        assertEquals("庄颜", user.getUsername());
        ConnectionUtils.closeDataSource();
    }
}

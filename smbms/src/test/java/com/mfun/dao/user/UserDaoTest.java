package com.mfun.dao.user;

import com.mfun.pojo.User;
import com.mfun.util.ConnectionUtil;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    @Test
    public void getLoginUserTest() throws SQLException {
        ConnectionUtil.connect();
        UserDao dao = new UserDaoImpl();
        User user = dao.getLoginUser("zhaoyan", "0000000");
        assertEquals(8, user.getId());
        ConnectionUtil.disconnect();
    }

    @Test
    public void getUserCountTest() throws SQLException {
        ConnectionUtil.connect();
        UserDao dao = new UserDaoImpl();
        assertEquals(2, dao.getUserCount("孙",3));
        assertEquals(0, dao.getUserCount("李明", 3));
        assertEquals(3, dao.getUserCount(null, 2));
        assertEquals(2, dao.getUserCount("赵", 0));
        ConnectionUtil.disconnect();
        System.out.println("结束");
    }

    @Test
    public void queryTest() throws Exception {
        ConnectionUtil.connect();
        List<Map<String, Object>> rows = ConnectionUtil.query(
                "SELECT u.*, r.roleName FROM smbms_user u, smbms_role r " +
                        "WHERE u.userName IN(?, ?) AND u.userRole = r.id " +
                        "ORDER BY u.userRole DESC",
                "李明", "赵燕");

        Class<UserDaoImpl> cls = UserDaoImpl.class;
        Method rowToUser = cls.getDeclaredMethod("rowToUser", Map.class);
        rowToUser.setAccessible(true);

        List<String> names = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            User user = (User) rowToUser.invoke(null, row);
            names.add(user.getUserName());
        }
        assertArrayEquals(new String[] {"赵燕", "李明"}, names.toArray());
        ConnectionUtil.disconnect();
    }

    @Test
    public void updateTest() throws Exception {
        ConnectionUtil.connect();
        int count = ConnectionUtil.update(
                "UPDATE smbms_user SET gender = ? WHERE userName = ?",
                1, "张晨");
        assertEquals(1, count);
        ConnectionUtil.disconnect();
    }

    @Test
    public void getUserListTest() throws Exception {
        ConnectionUtil.connect();
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> users = userDao.getUserList(null, 3, 1, 3);
        String[] names = users.stream().map(User::getUserName).toArray(String[]::new);
        assertArrayEquals(new String[] {"孙兴", "张晨", "邓超"}, names);

        users = userDao.getUserList("李明", 3, 1, 3);
        assertNull(users);

        ConnectionUtil.disconnect();
    }
}

package com.mfun.dao.user;

import com.mfun.pojo.User;
import com.mfun.util.ConnectionUtil;
import com.mfun.util.Gender;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.sql.Timestamp;

public class UserDaoImpl implements UserDao {
    @Override
    public User getLoginUser(String userCode) throws SQLException {
        String sql = "SELECT * FROM smbms_user WHERE userCode = ?";
        List<Map<String, Object>> results = ConnectionUtil.query(sql, userCode);
        if (results.size() != 0) {
            return rowToUser(results.get(0));
        }
        return null;
    }

    public User rowToUser(Map<String, Object> row) {
        User user = new User();
        user.setId((long) row.get("id"));
        user.setUserCode((String) row.get("userCode"));
        user.setUserName((String) row.get("userName"));
        user.setUserPassword((String) row.get("userPassword"));
        user.setGender(Gender.getGender((int) row.get("gender")));
        user.setBirthday(new java.util.Date(((Date) row.get("birthday")).getTime()));
        user.setPhone((String) row.get("phone"));
        user.setAddress((String) row.get("address"));
        user.setUserRole((long) row.get("userRole"));
        user.setCreatedBy((long) row.get("createdBy"));
        user.setCreationDate(new java.util.Date(((Timestamp) row.get("creationDate")).getTime()));
        user.setModifyBy((Long) row.get("modifyBy"));
        if (row.get("modifyDate") != null) {
            user.setModifyDate(new java.util.Date(((Timestamp) row.get("modifyDate")).getTime()));
        }
        return user;
    }
}

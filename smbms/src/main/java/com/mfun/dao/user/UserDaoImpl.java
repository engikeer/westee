package com.mfun.dao.user;

import com.mfun.pojo.User;
import com.mfun.util.ConnectionUtil;
import com.mfun.util.Gender;
import com.mysql.cj.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.sql.Timestamp;

public class UserDaoImpl implements UserDao {
    @Override
    public User getLoginUser(String userCode, String password) throws SQLException {
        String sql = "SELECT * FROM smbms_user WHERE userCode = ? AND userPassword = ?";
        List<Map<String, Object>> results = ConnectionUtil.query(sql, userCode, password);
        if (results.size() != 0) {
            return rowToUser(results.get(0));
        }
        return null;
    }

    @Override
    public int updatePassword(long id, String password) throws SQLException {
        String sql = "UPDATE smbms_user SET userPassword = ? WHERE id = ?";
        return ConnectionUtil.update(sql, password, id);
    }

    @Override
    public int getUserCount(String username, int userRole) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) as count FROM smbms_user WHERE 1");
        List<Object> param  = new ArrayList<>();
        if (!StringUtils.isNullOrEmpty(username)) {
            sql.append(" AND userName Like ?");
            param.add("%" + username + "%");
        }

        if (userRole > 0) {
            sql.append(" AND userRole = ?");
            param.add(userRole);
        }
        List<Map<String, Object>> query = ConnectionUtil.query(sql.toString(), param.toArray());
        if (query.size() > 0) {
            Long count = (Long) query.get(0).get("count");
            return count.intValue();
        } else {
            return 0;
        }
    }

    @Override
    public List<User> getUserList(String username, int userRole, int currentPageNo, int pageSize) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT u.*, r.roleName FROM smbms_user u, smbms_role r WHERE u.userRole = r.id");
        List<Object> param = new ArrayList<>();
        if (!StringUtils.isNullOrEmpty(username)) {
            sql.append(" AND u.userName LIKE ?");
            param.add("%" + username + "%");
        }
        if (userRole > 0) {
            sql.append(" AND u.userRole = ?");
            param.add(userRole);
        }

        // 排序并限制获取记录的条数，LIMIT 的参数为：开始的位置，记录的条数
        sql.append(" ORDER BY u.creationDate DESC LIMIT ?, ?");
        int startIndex = (currentPageNo - 1) * pageSize;
        param.add(startIndex);
        param.add(pageSize);

        List<Map<String, Object>> rows = ConnectionUtil.query(sql.toString(), param.toArray());
        List<User> users = new ArrayList<>();
        if (rows.size() > 0) {
            for (Map<String, Object> row : rows) {
                User user = rowToUser(row);
                users.add(user);
            }
            return users;
        } else {
            return null;
        }
    }

    private static User rowToUser(Map<String, Object> row) {
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
        user.setRoleName((String) row.get("roleName"));
        user.setCreatedBy((long) row.get("createdBy"));
        user.setCreationDate(new java.util.Date(((Timestamp) row.get("creationDate")).getTime()));
        user.setModifyBy((Long) row.get("modifyBy"));
        if (row.get("modifyDate") != null) {
            user.setModifyDate(new java.util.Date(((Timestamp) row.get("modifyDate")).getTime()));
        }
        return user;
    }
}

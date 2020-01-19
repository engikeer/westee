package com.mfun.dao.role;

import com.mfun.pojo.Role;
import com.mfun.util.ConnectionUtil;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoleDaoImpl implements RoleDao {
    @Override
    public List<Role> getRoleList() throws SQLException {
        String sql = "SELECT * FROM smbms_role";
        List<Map<String, Object>> rows = ConnectionUtil.query(sql);
        if (rows.size() > 0) {
            // 如果 rows.size 为 0，collect 会返回 空列表，而不是 null
            return rows.stream().map(RoleDaoImpl::rowToRole)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    private static Role rowToRole(Map<String, Object> row) {
        Role role = new Role();
        role.setId((long) row.get("id"));
        role.setRoleCode((String) row.get("roleCode"));
        role.setRoleName((String) row.get("roleName"));
        role.setCreateBy((long) row.get("createdBy"));
        role.setCreationDate(new java.util.Date(((Timestamp) row.get("creationDate")).getTime()));
        if (row.get("modifyDate") != null) {
            role.setModifyDate(new java.util.Date(((Timestamp) row.get("modifyDate")).getTime()));
        }
        role.setModifyBy((Long) row.get("modifyBy"));

        return role;
    }
}

package com.mfun.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.mfun.dao.user.UserDaoImpl;
import com.mfun.pojo.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ConnectionUtilTest {
    @Test
    public void queryTest() throws Exception {
        ConnectionUtil.connect();
        List<Map<String, Object>> results = ConnectionUtil.query(
                "SELECT * FROM smbms_user WHERE userName IN(?, ?)",
                "李明", "赵燕");
        int count = 0;
        for (Map<String, Object> row : results) {
            String userCode = (String) row.get("userCode");
            if ("liming".equals(userCode) || "zhaoyan".equals(userCode)) {
                count++;
            }
        }
        assertEquals(2, count);
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
}

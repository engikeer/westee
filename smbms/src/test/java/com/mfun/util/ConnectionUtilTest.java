package com.mfun.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ConnectionUtilTest {

//    @Test
//    public void mainTest() throws Exception {
//        Object one = Integer.valueOf(1);
//        int i = (int) one;
//        System.out.println(i);
//    }

    @Test
    public void queryTest() throws Exception {
        ConnectionUtil.connectDatabase();
        List<Map<String, Object>> results = ConnectionUtil.query(
                "SELECT * FROM smbms_user WHERE userName IN(?, ?)",
                "李明", "赵燕");
        for (Map<String, Object> row : results) {
            String message = String.format(
                    "id: %s, code: %s, name: %s",
                    row.get("id"),
                    row.get("userCode"),
                    row.get("userName"));
            System.out.println(message);
        }
        ConnectionUtil.closeConnection();
    }

    @Test
    public void updateTest() throws Exception {
        ConnectionUtil.connectDatabase();
        int count = ConnectionUtil.update(
                "UPDATE smbms_user SET gender = ? WHERE userName = ?",
                1, "张晨");
        assertEquals(1, count);
        ConnectionUtil.closeConnection();
    }
}

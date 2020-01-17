package com.mfun.util;

import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionUtilTest {

    @Test
    public void queryTest() throws Exception {
        try (PreparedStatement statement = ConnectionUtil.getConnection().prepareStatement("SELECT * FROM smbms_user")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(3));
            }
        }

    }
}

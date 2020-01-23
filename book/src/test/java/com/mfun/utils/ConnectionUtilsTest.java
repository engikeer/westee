package com.mfun.utils;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionUtilsTest {

    @Test
    public void getConnectionTest() throws SQLException {
        try (Connection connection = ConnectionUtils.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM bs_user");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                assertEquals("庄颜", username);
            }
        }
    }
}

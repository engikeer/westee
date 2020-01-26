package com.mfun.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

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

    @Test
    public void getRunnerTest() throws SQLException {
        QueryRunner runner = ConnectionUtils.getRunner();
        MapHandler mapHandler = new MapHandler();
        Map<String, Object> result = runner.query("SELECT * FROM bs_user LIMIT 1", mapHandler);
        assertEquals("庄颜", result.get("username"));

    }
}

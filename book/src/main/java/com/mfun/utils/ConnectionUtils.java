package com.mfun.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {
    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        InputStream in = ConnectionUtils.class.getResourceAsStream("/db.properties");
        try {
            properties.load(in);
        } catch (IOException | NullPointerException e) {
            System.out.println("初始化连接工具类失败");
            e.printStackTrace();
        }
        HikariConfig config = new HikariConfig(properties);
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

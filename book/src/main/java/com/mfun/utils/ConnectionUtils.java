package com.mfun.utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {
    private static HikariDataSource dataSource;
    private static QueryRunner runner;

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
        runner = new QueryRunner(dataSource);
    }

    public static QueryRunner getRunner() {
        return runner;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}

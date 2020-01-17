package com.mfun.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionUtil {

    public static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            System.out.println("初始化数据库连接");
            Properties properties = new Properties();
            // 通过类加载器加载资源
            InputStream in = ConnectionUtil.class.getClassLoader().getResourceAsStream("database.properties");
            try {
                properties.load(in);
                String url = properties.getProperty("jdbc.url");
                String username = properties.getProperty("jdbc.username");
                String password = properties.getProperty("jdbc.password");
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("数据库连接初始化失败");
            }
        }
        return connection;
    }

    /**
     * 公共查询方法
     * @param sql 可带有参数的 sql 语句
     * @param params 参数的值
     * @return 结果集
     * @throws SQLException 查询中抛出异常
     */
    public static ResultSet query(String sql, Object... params)
            throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                // 占位符序号从 1 开始
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeQuery();
        }
    }

    /**
     * 公共更新方法
     * @param sql 可带有参数的 sql 语句
     * @param params 参数的值
     * @return 受影响行数
     * @throws SQLException 查询中抛出异常
     */
    public static int update(String sql, Object... params)
            throws SQLException{
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                // 占位符序号从 1 开始
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeUpdate();
        }
    }

    /**
     * 关闭连接
     * @throws SQLException 关闭时抛出异常
     */
    public static void closeConnection() throws SQLException {
        connection.close();
    }
}

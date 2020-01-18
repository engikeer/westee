package com.mfun.util;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class ConnectionUtil {

    private static Connection connection;


    public static void connect() {
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
                connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("数据库连接初始化失败");
            }
        }
    }

    /**
     * 公共查询方法
     * @param sql 可带有参数的 sql 语句
     * @param params 参数的值
     * @return 结果集
     * @throws SQLException 查询中抛出异常
     */
    public static List<Map<String, Object>> query(String sql, Object... params)
            throws SQLException {
        if (connection == null) {
            throw new SQLException("未连接到数据库");
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                // 占位符序号从 1 开始
                preparedStatement.setObject(i + 1, params[i]);
            }
            // 不能直接返回 ResultSet，关闭语句时会先关闭相关的结果集
            // 将结果保存在一个 List 中返回
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<Map<String, Object>> results = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
                results.add(rowData);
            }
            return results;
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
        if (connection == null) {
            throw new SQLException("未连接到数据库");
        }
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
    public static void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("数据库连接关闭成功");
        } else {
            System.out.println("连接已关闭");
        }

    }
}

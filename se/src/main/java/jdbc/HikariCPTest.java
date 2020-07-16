package jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class HikariCPTest {
    public static void main(String[] args) throws Exception {
//        HikariConfig config = new HikariConfig("se/conf/db.properties");
        Properties properties = new Properties();
        // InputStream in = this.getClass().getResourceAsStream("/db.properties")
        try (InputStream in = new FileInputStream("se/conf/db.properties")) {
            properties.load(in);
        }
        HikariConfig config = new HikariConfig(properties);
        HikariDataSource ds = new HikariDataSource(config);
        try (Connection connection = ds.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT COUNT(1) FROM Books");
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
            }
        }
        // 关闭数据源
        ds.close();
    }

    public static void test() throws Exception {
        // 属性中包含中文要使用带编码的 Reader
        Properties properties = new Properties();
        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream("se/conf/db.properties"), StandardCharsets.UTF_8)) {
            properties.load(reader);
        }
        String s = properties.getProperty("user");
        System.out.println("s：" + s);
    }
}

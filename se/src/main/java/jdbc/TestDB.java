package jdbc;

import java.io.*;
import java.sql.*;

public class TestDB {
    public static void main(String[] args) throws Exception {
        runTest();
//        saveBlob();
//        readBlob();
    }

    public static void runTest() throws Exception  {
        try (Connection conn = ExecSQL.getConnection();
             Statement stat = conn.createStatement()) {

//            stat.execute("CREATE TABLE Hello (Message CHAR(20))");
//            stat.execute("INSERT INTO Hello VALUE ('Hello World!')");
            try (ResultSet result = stat.executeQuery("SELECT * FROM Hello")) {
                if (result.next())
                    System.out.println(result.getString(1));
            }

//            stat.executeUpdate("DROP TABLE Hello");
        }
    }

    private static void saveBlob() throws Exception {
        try (Connection conn = ExecSQL.getConnection()) {
//            Blob blob = conn.createBlob();
//            int offset = 0;
            FileInputStream fin = new FileInputStream("img/cache.jpg");
//            BufferedImage image = ImageIO.read(new File("img/cache.jpg"));
//            try (OutputStream out = blob.setBinaryStream(offset)) {
//                ImageIO.write(image, "JPG", out);
//            }
            PreparedStatement stat = conn.prepareStatement("INSERT INTO Greeting VALUES (?)");
            stat.setBlob(1, fin);
            int i = stat.executeUpdate();
            System.out.println(i);
        }
    }

    private static void readBlob() throws Exception {
        try (Connection conn = ExecSQL.getConnection();
             Statement stat = conn.createStatement()) {
            ResultSet rs = stat.executeQuery("SELECT * FROM Greeting");
            int i = 1;
            while (rs.next()) {
                Blob blob = rs.getBlob(1);
                try (InputStream in = blob.getBinaryStream();
                OutputStream out = new FileOutputStream("se/cache/" + i + ".jpg")) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    System.out.println("读取图片：" + i + ".jpg");
                    i++;
                }

            }


        }
    }

//    public static Connection getConnection() throws Exception {
////        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
////        return DriverManager.getConnection(
////                "jdbc:mysql://localhost:3306/corejava?" +
////                "user=user&password=pw");
//        return DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/corejava",
//                "user",
//                "");
//    }
}

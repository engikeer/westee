package com.mfun.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadConnection {
    private static Map<Long, Connection> map = new ConcurrentHashMap<>();
    public static Connection getCurrentConnection() throws SQLException {
        long id = Thread.currentThread().getId();
        Connection conn = map.get(id);
        if (conn == null) {
            conn = ConnectionUtils.getConnection();
            map.put(id, conn);

        }
        return conn;
    }

    public static void removeCurrentConnection() {
        long id = Thread.currentThread().getId();
        map.remove(id);
    }
}

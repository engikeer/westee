package com.mfun.listener;

import com.mfun.util.ConnectionUtil;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;
import java.util.Enumeration;

public class CloseConnectionListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=======应用初始化=======");
        ConnectionUtil.connect();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("======= 应用销毁 =======");
        try {
            ConnectionUtil.disconnect();
        } catch (SQLException e) {
            System.out.println("关闭数据库连接时发生异常");
            e.printStackTrace();
        }
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d = null;
        try {
            while (drivers.hasMoreElements()) {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
                System.out.println("清除数据库连接驱动：" + d);
            }
        } catch (SQLException e) {
            System.out.println("清除数据库驱动时发生异常");
            e.printStackTrace();
        }
        AbandonedConnectionCleanupThread.checkedShutdown();
        System.out.println("关闭 abandoned-connection-cleanup 线程");
    }
}

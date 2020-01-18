package com.mfun.listener;

import com.mfun.util.ConnectionUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

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
    }
}

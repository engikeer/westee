package com.mfun.listener;

import com.mfun.utils.ConnectionUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionUtils.closeDataSource();
    }
}

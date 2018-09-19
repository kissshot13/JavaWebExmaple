package com.kissshot.servletDemo.listener;

import com.kissshot.servletDemo.service.DBConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener("MyContextListener")
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContextListener start!");
        ServletContext ctx = sce.getServletContext();
        String url = ctx.getInitParameter("DBURL");
        String u = ctx.getInitParameter("DBUSER");
        String p = ctx.getInitParameter("DBPWS");
        System.out.println("url: "+url +",user: "+u+",psw: "+p);
        DBConnectionManager dbConnectionManager = new DBConnectionManager(url, u, p);
        ctx.setAttribute("DBManager", dbConnectionManager);
        System.out.println("Database connection initialized for Application.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContextListener destroy!");
        ServletContext ctx = sce.getServletContext();
        DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
        dbManager.closeConnection();
        System.out.println("Database connection closed for Application.");
    }
}

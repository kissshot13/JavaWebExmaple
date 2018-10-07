package com.kissshot.servletDemo.servlet;

import com.kissshot.servletDemo.listener.AppAsyncListener;
import com.kissshot.servletDemo.request.AsyncRequestProcessor;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  异步servlet
 */
@WebServlet(asyncSupported = true, urlPatterns = "/async",name ="AsyncServlet" )
public class AsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        System.out.println("AsyncLongRunningServlet Start::Name="
                + Thread.currentThread().getName() + "::ID="
                + Thread.currentThread().getId());
        req.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        int sec = 10000;
//        异步context
        AsyncContext asynContext = req.startAsync();
        asynContext.addListener(new AppAsyncListener());
        asynContext.setTimeout(200000);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) req.getServletContext().getAttribute("executor");
        executor.execute(new AsyncRequestProcessor(asynContext,sec));
        long endTime = System.currentTimeMillis();
        System.out.println("AsyncLongRunningServlet End::Name=" + Thread.currentThread().getName() + "::ID=" +
                                + Thread.currentThread().getId() + "::Time Taken=\"\n" +
                                + (endTime - startTime) + " ms.");

    }
}

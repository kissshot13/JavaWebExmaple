package com.kissshot.servletDemo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 同步servlet
 */
@WebServlet(name ="syncServlet" ,urlPatterns = "/sync")
public class SyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();
        System.out.println("LongRunningServlet Start:: Name="
        + Thread.currentThread().getName() + "::ID=" + Thread.currentThread().getId());
//        睡眠10秒
        int milliseconds = 10000;
        longProcessing(milliseconds);
        PrintWriter out = resp.getWriter();
        long endTime = System.currentTimeMillis();
        out.write("Processing done for " + milliseconds + " milliseconds!!");
        System.out.println("LongRunningServlet Start::Name="
                + Thread.currentThread().getName() + "::ID="
                + Thread.currentThread().getId() + "::Time Taken="
                + (endTime - startTime) + " ms.");
        out.flush();
        out.close();

    }

    private void longProcessing(int secs) {
        try {
            Thread.sleep(secs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.kissshot.servletDemo.servlet;

import javax.servlet.ReadListener;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("com.kissshot.servletDemo.servlet.token","123");
        String token =(String)req.getAttribute("com.kissshot.servletDemo.servlet.token");
        System.out.println(token);
        ServletContext context = req.getServletContext();
        context.setAttribute("User", "kissshot13");
        String user = (String) context.getAttribute("User");
        context.removeAttribute("User");
        HttpSession session = req.getSession();
        session.invalidate();


        PrintWriter out = resp.getWriter();
        out.write("Hi" + user);

//        out.flush();  // 如果先flush() 在forward 会报错
//        out.close();
//        req.getRequestDispatcher("/").forward(req,resp);
          req.getRequestDispatcher("/session").include(req,resp);
//          resp.sendRedirect("http://www.baidu.com");
    }
}

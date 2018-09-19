package com.kissshot.servletDemo.filter;

import javax.servlet.*;
import java.io.IOException;

public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化！");
        String site = filterConfig.getInitParameter("Site");
        System.out.println("网站名称:" +  site);
    }

    /**
     *  每次请求都执行过滤链
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("站点网址：http://www.baidu.com");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁!");
        /* 在 Filter 实例被 Web 容器从服务移除之前调用 */
    }
}

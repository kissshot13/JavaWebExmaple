package com.kissshot.servletDemo.servlet;

import javax.net.ssl.ManagerFactoryParameters;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<GarbageCollectorMXBean> g = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean bean :
                g) {
            System.out.println(bean.getName());
        }
    }
}

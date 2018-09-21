package com.kissshot.myspringmvc.servlet;

import com.kissshot.myspringmvc.annotations.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    //定义一个
    private List<String> clzList = new ArrayList<>();
    // bean Map
    private Map<String, Object> beanMap = new HashMap<>();

    private Map<String, Method> methodMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }

    @Override
    public void init() throws ServletException {
        // 扫描包
        scanPackage("com.kissshot.myspringmvc");
        // 装配这些bean
        try {
            doInstance();
            //ioc
            ioc();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // ioc

        // 建立映射关系
        setMapping();
    }

    private void setMapping() {
        if (!beanMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
                Class<?> aClass = entry.getValue().getClass();
                if (aClass.isAnnotationPresent(Controller.class)) {
                    Field[] fields = aClass.getFields();
                    for (Field field : fields) {
                        if (field.isAnnotationPresent(RequestMapping.class)) {
//                            methodMap.put("",field.)
                        }
                    }
                }

            }
        }
    }

    private void ioc() throws IllegalAccessException {
        if (!beanMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
                // 获取实例对象中所有属性
                Field[] fields = entry.getValue().getClass().getFields();
                for (Field field:fields) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(AutoWrite.class)) {
                        Qualifier annotation = field.getAnnotation(Qualifier.class);
                        field.set(entry.getValue(),beanMap.get(annotation.value()));
                    }
                }

            }
        }
    }

    private void doInstance() throws Exception{
        if (!clzList.isEmpty()) {
            for (String clz:clzList){
                Class<?> aClass = Class.forName(clz);
                if (aClass.isAnnotationPresent(Controller.class)) {
                    RequestMapping annotation = aClass.getAnnotation(RequestMapping.class);
                    beanMap.put(annotation.value(), aClass.newInstance());
                    //代表这是一个controller
                }else if(aClass.isAnnotationPresent(Service.class)){
                    Service annotation = aClass.getAnnotation(Service.class);
                    beanMap.put(annotation.value(), aClass.newInstance());
                }
            }
        }
    }

    /**
     *  扫描包名，把里面所有类都加入
     * @param packageName
     */
    private void scanPackage(String packageName) {
        // 获取包名对应的物理目录  /F:/kissshot/idea_workspace/JavaWebExmaple/MySpringMVC/target/MySpringMVC/WEB-INF/classes/com/kissshot/myspringmvc/
        String realPath = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/")).getFile();
        File scanfile = new File(realPath);
        String[] fileNameList = scanfile.list();
        for (String fileName: fileNameList) {
            File file = new File(realPath, fileName);
            if (file.isDirectory()) {
                scanPackage(packageName + "." + file.getName());
            } else {
                // 如果是一个文件 放入一个list
                if(fileName.endsWith(".class")){
                    clzList.add(packageName + "." + fileName.replace(".class", ""));
                }
            }
        }
    }


}

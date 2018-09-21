package com.kissshot.myspringmvc.service.Impl;

import com.kissshot.myspringmvc.annotations.Service;
import com.kissshot.myspringmvc.service.HelloService;

@Service("HelloServiceImpl")
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("hello world!");
    }
}

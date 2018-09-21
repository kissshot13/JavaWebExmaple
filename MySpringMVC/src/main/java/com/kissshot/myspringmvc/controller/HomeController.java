package com.kissshot.myspringmvc.controller;

import com.kissshot.myspringmvc.annotations.AutoWrite;
import com.kissshot.myspringmvc.annotations.Controller;
import com.kissshot.myspringmvc.annotations.Qualifier;
import com.kissshot.myspringmvc.annotations.RequestMapping;
import com.kissshot.myspringmvc.service.HelloService;

@Controller
@RequestMapping("/")
public class HomeController {

    @AutoWrite
    @Qualifier("HelloServiceImpl")
    private HelloService service;

    @RequestMapping("/hello")
    public void  home(){
        service.sayHello();
    }
}

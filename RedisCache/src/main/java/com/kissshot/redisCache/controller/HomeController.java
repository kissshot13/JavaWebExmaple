package com.kissshot.redisCache.controller;

import com.kissshot.redisCache.dto.RequestBase;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping(value = "/message")
    @Cacheable(value = "coco")
     public @ResponseBody String getMessage(String id){
         return "hi,"+id ;
     }

    @RequestMapping(value = "/mix")
    @Cacheable(value = "mix")
    public @ResponseBody String getMix(RequestBase req){
       return "hi," + req.getName();
    }
}

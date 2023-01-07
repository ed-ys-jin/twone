package com.shinjin.twone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemController {

    @RequestMapping("/login")
    public String viewLogin(){
        return "/login/login";
    }

}

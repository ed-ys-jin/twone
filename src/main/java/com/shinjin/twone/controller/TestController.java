package com.shinjin.twone.controller;

import com.shinjin.twone.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/")
    public String viewIndex(Model model) {

        model.addAttribute("test", testService.selectList());

        return "index";
    }

}

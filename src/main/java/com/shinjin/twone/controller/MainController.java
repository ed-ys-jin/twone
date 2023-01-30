package com.shinjin.twone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("mainController")
public class MainController {
    @RequestMapping(value= "/")
    public ModelAndView home() throws Exception{
        return new ModelAndView("redirect:/project");
    }
}
package com.shinjin.twone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping(value= "/", method = RequestMethod.GET)
    public ModelAndView home() throws Exception{
        ModelAndView mav = new ModelAndView();

        mav.setViewName("index");

        return mav;
//        return "index";
    }
}
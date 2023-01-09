package com.shinjin.twone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

    @RequestMapping("project/board")
    public String viewBoard(){
        return "project/board";
    }

}

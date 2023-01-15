package com.shinjin.twone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

    @RequestMapping("project/board")
    public String viewBoard(Model model)
    {
        model.addAttribute("navType","board");
        return "project/board";
    }

}

package com.shinjin.twone.controller;

import com.shinjin.twone.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    private DAO dao;

    // DB데이터를 불러오는 controller
    @RequestMapping("/")
    public String viewIndex(Model model) {

        model.addAttribute("test", dao.selectList());

        return "index";
    }

}

package com.shinjin.twone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IssueController {

    @RequestMapping("/issue")
    public String viewIssue(){
        return "issue/issue";
    }

}
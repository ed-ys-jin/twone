package com.shinjin.twone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectController {

    @RequestMapping("/project")
    public String viewProjectList(){
        return "project/project";
    }

    @RequestMapping("/project/setting")
    public String viewSettingDetail(){
        return "project/setting";
    }

}

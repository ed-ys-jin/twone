package com.shinjin.twone.controller;

import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.ProjectDTO;
import com.shinjin.twone.service.MemService;
import com.shinjin.twone.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ProjectController {

    @Autowired
    ProjectService projectService;
    @Autowired
    MemService memService;

    @RequestMapping("/project/setting")
    public String viewSettingDetail(){
        return "project/setting";
    }

    @RequestMapping("/project")
    public String viewProjectList(){
        return "project/project";
    }

    @RequestMapping("/project/createForm")
    public String createProjectForm(HttpServletRequest request, Model model) {
//        int mem_seq = (int) request.getSession().getAttribute("login");
//        MemDTO memDTO = memService.selectOne(mem_seq);
//        model.addAttribute("memDTO", memDTO);

        return "project/createForm";
    }

    @RequestMapping("/project/create")
    public String createProject(HttpServletRequest request, ProjectDTO projectDto) {
//        int mem_seq = (int) request.getSession().getAttribute("login");
//        MemDTO memDTO = memService.selectOne(mem_seq);
//        model.addAttribute("memDTO", memDTO);
//        projectDto.setMemSeq(mem_seq);
        int num = projectService.createProject(projectDto);

        return "project/createForm";
    }
}

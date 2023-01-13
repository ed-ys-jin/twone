package com.shinjin.twone.controller;

import com.shinjin.twone.dto.ProjectDTO;
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

    @RequestMapping("/project/setting")
    public String viewSettingDetail(){
        return "project/setting";
    }

    @RequestMapping("/project")
    public String viewProjectList(){
        return "project/project";
    }

    @RequestMapping("/project/createForm")
    public String createProjectForm(/*HttpServletRequest request, */Model model) {
        //int mem_seq = (int) request.getSession().getAttribute("user");
        model.addAttribute("memSeq", 1);

        return "project/createForm";
    }

    @RequestMapping("/project/create")
    public String createProject(HttpServletRequest request, ProjectDTO projectDto) {
        projectDto.setMemSeq(Integer.parseInt(request.getParameter("memSeq")));
        System.out.println(projectDto.getProjectName());
        System.out.println(projectDto.getProjectKey());
        System.out.println(projectDto.getMemSeq());

        int num = projectService.createProject(projectDto);

        return "project/createForm";
    }
}

package com.shinjin.twone.controller;

import com.shinjin.twone.dao.ProjectDAO;
import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.ProjectDTO;
import com.shinjin.twone.service.MemService;
import com.shinjin.twone.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BoardController {

    @Autowired
    private MemService memService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping("project/board")
    public String viewBoard(HttpServletRequest request, Model model){
        int memSeq = (int) request.getSession().getAttribute("login");
        MemDTO memDTO = memService.getDto(memSeq);
        model.addAttribute("memDTO", memDTO);

        int projectSeq = Integer.parseInt(request.getParameter("projectSeq"));
        ProjectDTO pdto = projectService.selectOne(projectSeq);
        model.addAttribute("pdto", pdto);

        // project 상세페이지 넘어감
        model.addAttribute("navType", "project");

        return "project/board";
    }

}

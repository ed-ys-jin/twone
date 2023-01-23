package com.shinjin.twone.controller;

import com.shinjin.twone.common.commonMethod;
import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.ProjectDTO;
import com.shinjin.twone.dto.TeamDTO;

import com.shinjin.twone.service.ProjectService;
import com.shinjin.twone.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class TeamController {

  @Autowired
  TeamService teamService;
  @Autowired
  ProjectService projectService;

// 사용자 페이지로 이동
  @RequestMapping("/project/team")
  public String teamView(Model model, HttpServletRequest request) throws Exception{

    int pSeq = Integer.parseInt(request.getParameter("projectSeq"));
    int leader = teamService.leaderSeq(pSeq);
    ProjectDTO pdto = projectService.selectOne(pSeq);
    List<HashMap<String,Object>> teamList = teamService.selectTeamList(pSeq);

    model.addAttribute("pdto", pdto);

    model.addAttribute("teamList",teamList);
    model.addAttribute("leader",leader);
    model.addAttribute("navType","team");
    return "/team/team";
  }

//  권한 변경
  @RequestMapping("/project/changeAllow")
  public String chageAllow(HttpServletRequest request){
    int allow = Integer.parseInt(request.getParameter("allow"));
    int memSeq = Integer.parseInt(request.getParameter("memSeq"));

    TeamDTO dto = new TeamDTO();
    dto.setTeamAllow(allow);
    dto.setMemSeq(memSeq);

    int check = teamService.changeAllow(dto);
    if(check == -1 ){
      commonMethod.setAttribute(request,"/project/team","변경을 다시 시도해주세요.");
    }else{
      commonMethod.setAttribute(request, "/project/team","변경이 완료되었습니다.");
    }
      return "/common/alert";
  }

  //사용자 추가
  @RequestMapping("/project/memberAdd")
  public String memberAdd(HttpServletRequest request){
    String email = request.getParameter("email");
    int projectSeq = 21;
//    int projectSeq = Integer.parseInt(request.getParameter("projectSeq"));


    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("email", email);
    map.put("pSeq",projectSeq);
    int check = teamService.memberAdd(map);
    System.out.println(check);

    if(check == -1 ){
      commonMethod.setAttribute(request,"/project/team","사용자를 다시 확인해주세요.");
    }else{
      commonMethod.setAttribute(request, "/project/team","사용자를 추가하었습니다.");
    }
    return "/common/alert";
  }

}

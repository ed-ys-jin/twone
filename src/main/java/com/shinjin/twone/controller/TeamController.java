package com.shinjin.twone.controller;

import com.shinjin.twone.common.commonMethod;
import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.TeamDTO;

import com.shinjin.twone.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class TeamController {

  @Autowired
  TeamService teamService;
  HttpSession session;

// 사용자 페이지로 이동
  @RequestMapping("/project/team")
  public String teamView(HttpServletRequest request) throws Exception{
    int pSeq = Integer.parseInt(request.getParameter("projectSeq"));
    List<MemDTO> teamList = teamService.selectTeamList(pSeq);

    //로그인 세션의 정보 가져오기
    session = request.getSession();
    int login = (int)session.getAttribute("login");

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("mSeq", login);
    map.put("pSeq",pSeq);
    TeamDTO dto = teamService.selectOne(map);
    System.out.println(dto.getTeamAllow() +"세션 권한");

    List<Integer> allowList = new ArrayList<>();

    for(MemDTO mem : teamList){
      if(mem.getMemSeq() != login){
        allowList.add(mem.getTeamAllow());
      }
    }
    for(int a : allowList){
      System.out.println(a);
    }

    request.setAttribute("teamList",teamList);
    request.setAttribute("dto",dto);
    request.setAttribute("allowList",allowList);
    request.setAttribute("navType","team");
    return "/team/team";
  }

//  권한 변경
  @RequestMapping("/project/changeAllow")
  public String chageAllow(HttpServletRequest request){
    int allow = Integer.parseInt(request.getParameter("allow"));
    int memSeq = Integer.parseInt(request.getParameter("memSeq"));
    int projectSeq = Integer.parseInt(request.getParameter("projectSeq"));

    TeamDTO dto = new TeamDTO();
    dto.setTeamAllow(allow);
    dto.setMemSeq(memSeq);
    dto.setProjectSeq(projectSeq);
    System.out.println(dto.getProjectSeq() +"project");
    System.out.println(dto.getMemSeq() +"memSeq");
    System.out.println(dto.getTeamAllow() +"allow");

    int check = teamService.changeAllow(dto);
    if(check == -1 ){
      commonMethod.setAttribute(request,"/project/team?projectSeq="+projectSeq,"변경을 다시 시도해주세요.");
    }else{
      commonMethod.setAttribute(request, "/project/team?projectSeq="+projectSeq,"변경이 완료되었습니다.");
    }
      return "/common/alert";
  }

  //사용자 추가
  @RequestMapping("/project/memberAdd")
  public String memberAdd(HttpServletRequest request) {
    String email = request.getParameter("email");
    int projectSeq = Integer.parseInt(request.getParameter("projectSeq"));

    //사용자 존재여부
    Integer checkMem = teamService.checkMember(email);
    if (checkMem == null) { //사용자가 존재하지 않음
      commonMethod.setAttribute(request, "/project/team?projectSeq=" + projectSeq, "사용자를 다시 확인해주세요.");
      return "/common/alert";

    } else { // 사용자 존재
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("mSeq", checkMem);
      map.put("pSeq", projectSeq);

      // 팀 추가시 사용자 중복 확인
      TeamDTO dto = teamService.selectOne(map);

      if (dto != null) { // 중복
        commonMethod.setAttribute(request, "/project/team?projectSeq=" + projectSeq, "사용자가 이미 존재합니다.");
        return "/common/alert";
      }
      int checkAdd = teamService.memberAdd(map);
      if (checkAdd != 0) {
        commonMethod.setAttribute(request, "/project/team?projectSeq=" + projectSeq, "사용자를 추가하었습니다.");
      } else {
        commonMethod.setAttribute(request, "/project/team?projectSeq=" + projectSeq, "사용자를 다시 확인해주세요.");
      }
      return "/common/alert";
    }
  }

    @RequestMapping(value ={"/project/deleteMember", "/project/Withdrawal"})
    public String deleteMember(HttpServletRequest request){
      int mSeq = Integer.parseInt(request.getParameter("memberSeq"));
      int pSeq = Integer.parseInt(request.getParameter("projectSeq"));

      TeamDTO dto = new TeamDTO();
      dto.setProjectSeq(pSeq);
      dto.setMemSeq(mSeq);

      int check = teamService.deleteMember(dto);
      if(check != 0){
        commonMethod.setAttribute(request,"/project/team?projectSeq=" + pSeq);
        if(request.getServletPath().equals("/project/Withdrawal")){
          System.out.println("본인탈퇴");
          commonMethod.setAttribute(request,"/project");
        }
        return "/common/noalert";
      }
      commonMethod.setAttribute(request,"/project/team?projectSeq=" + pSeq, "다시 시도하여주십시오.");
      return "/common/alert";
    }



  }



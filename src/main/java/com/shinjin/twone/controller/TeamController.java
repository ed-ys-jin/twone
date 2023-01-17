package com.shinjin.twone.controller;

import com.shinjin.twone.common.commonMethod;
import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.ProjectDTO;
import com.shinjin.twone.dto.TeamDTO;

import com.shinjin.twone.service.ProjectService;
import com.shinjin.twone.service.TeamService;
import org.apache.tomcat.util.buf.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.ImageProducer;
import java.util.*;


@Controller
public class TeamController {

  @Autowired
  TeamService teamService;
  @Autowired
  ProjectService projectService;
  HttpSession session;

// 사용자 페이지로 이동
  @RequestMapping("/project/team")
  public String teamView(HttpServletRequest request) throws Exception{

    int pSeq = Integer.parseInt(request.getParameter("projectSeq"));
    int leader = teamService.leaderSeq(pSeq);
    ProjectDTO pdto = projectService.selectOne(pSeq);
//    List<MemDTO> teamList = teamService.selectTeamList(pSeq);
    List<HashMap<String,Object>> teamList = teamService.selectTeamList(pSeq);

    Set<String> keys = teamList.get(0).keySet();
    List<HashMap<String,Object>> list = new ArrayList<>();

    for(HashMap<String,Object> m : teamList){ //{mem_cert=0, team_allow=2}
        HashMap<String,Object> map = new HashMap<>();
      for(String k : keys){ //mem_cert, team_allow
        if(m.get(k).equals("")){
          map.put(("\"" + k + "\"" ),null);
        }else if((m.get(k)+"").matches("[+-]?\\d*(\\.\\d+)?")){
          map.put( ("\"" + k + "\"" ),m.get(k));
        } else if (!(m.get(k)+"").matches("[+-]?\\d*(\\.\\d+)?")) {
          map.put( ("\"" + k + "\"" ), ("\"" + m.get(k) +"\""));
        }
      }
      list.add(map);
    }

    List<Integer> allowList = new ArrayList<>();

    JSONParser parser = new JSONParser();
    JSONArray teamlist = new JSONArray();
//    int idx = 0;
//    String text ="[";
    for(HashMap<String,Object> map:list){
      allowList.add((Integer) map.get("team_allow"));
      String str = map.toString().replaceAll("=",":") ;
      JSONObject json = (JSONObject)parser.parse(str);
      teamlist.add(json);
//      if(idx < 1){
//        text += ",";
//      }
    }
//    text += "]";

//    System.out.println(text +"text");

    //로그인 세션의 정보 가져오기
    session = request.getSession();
    int login = (int)session.getAttribute("login");

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("mSeq", login);
    map.put("pSeq",pSeq);
//    TeamDTO tdto = teamService.selectOne(map);
    HashMap<String,Object> tdto = teamService.selectOne(map);
    Set<String> key = tdto.keySet();

    HashMap<String,Object> dto = new HashMap<String,Object>();
    for(String k : key){ //mem_cert, team_allow
      if(tdto.get(k).equals("")){
        dto.put(("\"" + k + "\"" ),null);
      }else if((tdto.get(k)+"").matches("[+-]?\\d*(\\.\\d+)?")){
        dto.put( ("\"" + k + "\"" ),tdto.get(k));
      } else if (!(tdto.get(k)+"").matches("[+-]?\\d*(\\.\\d+)?")) {
        dto.put( ("\"" + k + "\"" ), ("\"" + tdto.get(k) +"\""));
      }
    }
    String sdto = dto.toString().replaceAll("=",":") ;
    JSONObject json = (JSONObject)parser.parse(sdto);
    System.out.println(json);




    request.setAttribute("teamList",teamlist);
//    request.setAttribute("text",text);
    request.setAttribute("dto",json);
    request.setAttribute("allowList",allowList);
    request.setAttribute("leader",leader);
    request.setAttribute("pdto", pdto);
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
      TeamDTO dto = teamService.checkOne(map);

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



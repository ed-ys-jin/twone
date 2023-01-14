package com.shinjin.twone.controller;

import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.TestDTO;
import com.shinjin.twone.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TeamController {

  @Autowired
  private TeamService teamService;
  @RequestMapping("/project/team")
  public String teamView(Model model){

    List<MemDTO> teamList = teamService.selectTeamList();
    int leader = teamService.leaderSeq();

    model.addAttribute("teamList",teamList);
    model.addAttribute("leader",leader);
    model.addAttribute("navType", "team");
//    TestDTO teamList1 = teamService.selectList();
//    model.addAttribute("test",teamList1);

    return "/team/member";
  }
}

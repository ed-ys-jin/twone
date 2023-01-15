package com.shinjin.twone.service;

import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.TeamDTO;
import com.shinjin.twone.dto.TestDTO;

import java.util.List;

public interface TeamService {

  public List<MemDTO> selectTeamList();

  public int changeAllow(TeamDTO dto);

  public int leaderSeq();

  public int memberAdd(String eamil);
}

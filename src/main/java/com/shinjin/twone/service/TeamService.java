package com.shinjin.twone.service;

import com.shinjin.twone.dao.TeamDAO;
import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
  @Autowired
  private TeamDAO teamDao;

  public TestDTO selectList(){
    return teamDao.selectList();
  }


  public List<MemDTO> selectTeamList() {
    return teamDao.selectTeamList();
  }

  public int leaderSeq() {
    return teamDao.leaderSeq();
  }
}

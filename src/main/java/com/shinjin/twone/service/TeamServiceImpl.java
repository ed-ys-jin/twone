package com.shinjin.twone.service;

import com.shinjin.twone.dao.TeamDAO;
import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.TeamDTO;
import com.shinjin.twone.dto.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

  @Autowired
  private TeamDAO teamDao;

  @Override
  public List<MemDTO> selectTeamList() {
    return teamDao.selectTeamList();
  }

  @Override
  public int changeAllow(TeamDTO dto) {
    return teamDao.changeAllow(dto);
  }

  @Override
  public int leaderSeq(){
    return teamDao.leaderSeq();
  }

  @Override
  public int memberAdd(String email) {
    return teamDao.memberAdd(email);
  }

}

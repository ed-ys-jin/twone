package com.shinjin.twone.dao;

import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.TeamDTO;

import java.util.HashMap;
import java.util.List;

public class TeamDAOImpl implements TeamDAO{


  @Override
  public List<HashMap<String,Object>> selectTeamList(int seq) {
    return selectTeamList(seq);
  }

//  @Override
//  public List<MemDTO> selectTeamList(int seq) {
//    return selectTeamList(seq);
//  }

  @Override
  public int leaderSeq(int seq) {
    return leaderSeq(seq);
  }

  @Override
  public int changeAllow(TeamDTO dto) {
    return changeAllow(dto);
  }

  @Override
  public int memberAdd(HashMap<String, Object> map) {
    return memberAdd(map);
  }

  @Override
  public Integer checkMember(String email) {
    return checkMember(email);
  }

  @Override
  public TeamDTO checkOne(HashMap<String, Object> map) {
    return checkOne(map);
  }
  @Override
  public HashMap<String, Object> selectOne(HashMap<String, Object> map) {
    return selectOne(map);
  }

  @Override
  public int deleteMember(TeamDTO dto) {
    return deleteMember(dto);
  }


}

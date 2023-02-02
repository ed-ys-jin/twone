package com.shinjin.twone.service;

import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.TeamDTO;

import java.util.HashMap;
import java.util.List;

public interface TeamService {

//  public List<MemDTO> selectTeamList(int seq);
  public List<HashMap<String,Object>> selectTeamList(int seq);

  public int changeAllow(TeamDTO dto);

  public int leaderSeq(int seq) throws Exception;

  public int memberAdd(HashMap<String, Object> map);

  public MemDTO checkMember(String email);

  public TeamDTO checkOne(HashMap<String, Object> map);

  public HashMap<String, Object> selectOne(HashMap<String, Object> map);

  public int deleteMember(TeamDTO dto);

  public TeamDTO getTeamDTO(TeamDTO teamDTO); // TeamDTO 불러오기
}

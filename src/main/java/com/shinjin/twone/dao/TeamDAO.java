package com.shinjin.twone.dao;

import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.TeamDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TeamDAO {

//  public List<MemDTO> selectTeamList(int seq);
  public List<HashMap<String,Object>> selectTeamList(int seq);

  public int leaderSeq(int seq) throws Exception;

  public int changeAllow(TeamDTO dto);

  public int memberAdd(HashMap<String, Object> map);

  public MemDTO checkMember(String email);

  public TeamDTO checkOne(HashMap<String, Object> map);
  public HashMap<String, Object> selectOne(HashMap<String, Object> map);

  public int deleteMember(TeamDTO dto);
}
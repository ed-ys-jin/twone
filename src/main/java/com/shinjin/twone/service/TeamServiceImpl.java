package com.shinjin.twone.service;

import com.shinjin.twone.dao.TeamDAO;
import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

  @Autowired
  private TeamDAO teamDao;

//  @Override
//  public List<MemDTO> selectTeamList(int seq) {
//    return teamDao.selectTeamList(seq);
//  }
  @Override
  public List<HashMap<String,Object>> selectTeamList(int seq) {
    return teamDao.selectTeamList(seq);
  }

  @Override
  public int changeAllow(TeamDTO dto) {
    return teamDao.changeAllow(dto);
  }

  @Override
  public int leaderSeq(int seq) throws Exception{
    int result = -1;
    try{
      result = teamDao.leaderSeq(seq);
    }catch (Exception e){
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public int memberAdd(HashMap<String, Object> map) {
    return teamDao.memberAdd(map);
  }

  @Override
  public MemDTO checkMember(String email) {
    return teamDao.checkMember(email);
  }

  @Override
  public TeamDTO checkOne(HashMap<String, Object> map) {
    return teamDao.checkOne(map);
  }
  @Override
  public HashMap<String, Object> selectOne(HashMap<String, Object> map) {
    return teamDao.selectOne(map);
  }

  @Override
  public int deleteMember(TeamDTO dto) {
    return teamDao.deleteMember(dto);
  }

  /* TeamDTO 불러오기 */
  @Override
  public TeamDTO getTeamDTO(TeamDTO teamDTO) {
    return teamDao.getTeamDTO(teamDTO);
  }


}

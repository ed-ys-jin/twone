package com.shinjin.twone.dao;

import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.TestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeamDAO {
  public TestDTO selectList();

  public List<MemDTO> selectTeamList();

  public int leaderSeq();
}
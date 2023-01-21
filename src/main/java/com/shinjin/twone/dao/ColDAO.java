package com.shinjin.twone.dao;

import com.shinjin.twone.dto.ColDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ColDAO {

  public int createSampleColumn(ColDTO colDTO); // 샘플 컬럼 생성

  public int addDoneColumn(ColDTO colDTO); // Done 컬럼 생성

  public int addColumn(ColDTO colDTO); // 컬럼 생성

  public int updateColName(ColDTO colDTO); // 컬럼명 변경

  public ColDTO getColDTO(int colSeq); // ColDTO 불러오기

  public List<ColDTO> getColList(int boardSeq); // 컬럼 리스트 불러오기

  public int deleteColumn(int colSeq); // 컬럼 삭제 by colSeq

  public int deleteColumnByBoardSeq(int boardSeq); // 컬럼 삭제 by boardSeq

  public int deleteColumnByProjectSeq(int projectSeq); // 컬럼 삭제 by projectSeq

}

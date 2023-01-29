package com.shinjin.twone.dao;

import com.shinjin.twone.dto.FormsSimDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FormsSimDAO {

  public int createSimSeq(); // simSeq 생성
  public int addFormsSim(FormsSimDTO simDTO); // 날짜 이슈폼 생성
  public int updateSimTitle(FormsSimDTO simDTO); // simTitle 변경
  public int updateSimValue(FormsSimDTO simDTO); // simValue 변경
  public FormsSimDTO getSimDTO(String simSeq); // FormsSimDTO 불러오기

}

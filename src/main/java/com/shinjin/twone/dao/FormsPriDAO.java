package com.shinjin.twone.dao;

import com.shinjin.twone.dto.FormsPriDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FormsPriDAO {

  public int addFormsPri(FormsPriDTO priDTO); // 우선순위 이슈폼 생성
  public int updatePriTitle(FormsPriDTO priDTO); // priTitle 변경
  public int updatePriValue(FormsPriDTO priDTO); // priValue 변경
  public FormsPriDTO getPriDTO(String formsSeq); // priDTO 불러오기

}

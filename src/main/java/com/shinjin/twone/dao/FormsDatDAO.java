package com.shinjin.twone.dao;

import com.shinjin.twone.dto.FormsDatDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FormsDatDAO {

  public int addFormsDat(FormsDatDTO datDTO); // 날짜 이슈폼 생성
  public int updateDatTitle(FormsDatDTO datDTO); // datTitle 변경
  public int updateDatValue(FormsDatDTO datDTO); // datValue 변경
  public FormsDatDTO getDatDTO(String datSeq); // FormsDatDTO 불러오기

}

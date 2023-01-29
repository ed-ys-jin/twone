package com.shinjin.twone.service;

import com.shinjin.twone.dto.FormsDatDTO;

public interface FormsDatService {

  public String createDatSeq(); // datSeq 생성
  public int addFormsDat(FormsDatDTO datDTO); // 날짜 이슈폼 생성
  public int updateDatTitle(FormsDatDTO datDTO); // datTitle 변경
  public int updateDatValue(FormsDatDTO datDTO); // datValue 변경
  public FormsDatDTO getDatDTO(String datSeq); // FormsDatDTO 불러오기

}

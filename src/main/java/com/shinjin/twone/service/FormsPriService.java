package com.shinjin.twone.service;

import com.shinjin.twone.dto.FormsPriDTO;

public interface FormsPriService {

  public String createPriSeq(); // priSeq 생성
  public int addFormsPri(FormsPriDTO priDTO); // 우선순위 이슈폼 생성
  public int updatePriTitle(FormsPriDTO priDTO); // priTitle 변경
  public int updatePriValue(FormsPriDTO priDTO); // priValue 변경
  public FormsPriDTO getPriDTO(String formsSeq); // priDTO 불러오기

}

package com.shinjin.twone.service;

import com.shinjin.twone.dto.FormsParDTO;

public interface FormsParService {

  public String createParSeq(); // parSeq 생성
  public int addFormsPar(FormsParDTO parDTO); // 단락 이슈폼 생성
  public int updateParTitle(FormsParDTO parDTO); // parTitle 변경
  public int updateParValue(FormsParDTO parDTO); // parValue 변경
  public FormsParDTO getParDTO(String parSeq); // FormsParDTO 불러오기

}

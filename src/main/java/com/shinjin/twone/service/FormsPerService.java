package com.shinjin.twone.service;

import com.shinjin.twone.dto.FormsPerDTO;

public interface FormsPerService {

  public String createPerSeq(); // perSeq 생성
  public int addFormsPer(FormsPerDTO perDTO); // 담당자 이슈폼 생성
  public int updatePerTitle(FormsPerDTO perDTO); // perTitle 변경
  public int updateMemSeq(FormsPerDTO perDTO); // memSeq 변경
  public FormsPerDTO getPerDTO(String perSeq); // FormsPerDTO 불러오기

}

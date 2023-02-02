package com.shinjin.twone.service;

import com.shinjin.twone.dto.FormsDatDTO;
import com.shinjin.twone.dto.FormsSimDTO;

public interface FormsSimService {

  public String createSimSeq(); // simSeq 생성
  public int addFormsSim(FormsSimDTO simDTO); // 날짜 이슈폼 생성
  public int updateSimTitle(FormsSimDTO simDTO); // simTitle 변경
  public int updateSimValue(FormsSimDTO simDTO); // simValue 변경
  public FormsSimDTO getSimDTO(String simSeq); // FormsSimDTO 불러오기

}

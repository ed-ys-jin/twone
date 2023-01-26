package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsPerDAO;
import com.shinjin.twone.dto.FormsPerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsPerServiceImpl implements FormsPerService {

  @Autowired
  private FormsPerDAO formsPerDAO;

  /* 담당자 이슈폼 생성 */
  @Override
  public String addFormsPer(FormsPerDTO perDTO) {
    String result = null;
    try {
      formsPerDAO.addFormsPer(perDTO);
      // selectkey 를 활용하여 인서트 한 per_seq 바로 가져오기
      result = "per-" + perDTO.getPerSeq();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /* perTitle 변경 */
  @Override
  public int updatePerTitle(FormsPerDTO perDTO) {
    return formsPerDAO.updatePerTitle(perDTO);
  }

  /* memSeq 변경 */
  @Override
  public int updateMemSeq(FormsPerDTO perDTO) {
    return formsPerDAO.updateMemSeq(perDTO);
  }

  /* FormsPerDTO 불러오기 */
  @Override
  public FormsPerDTO getPerDTO(String perSeq) {
    return formsPerDAO.getPerDTO(perSeq);
  }
}

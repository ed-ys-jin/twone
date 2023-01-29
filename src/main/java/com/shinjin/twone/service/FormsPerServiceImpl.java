package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsPerDAO;
import com.shinjin.twone.dto.FormsPerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsPerServiceImpl implements FormsPerService {

  @Autowired
  private FormsPerDAO formsPerDAO;

  /* perSeq 생성 */
  @Override
  public String createPerSeq() {
    String perSeq = "per-" + formsPerDAO.createPerSeq();
    return perSeq;
  }

  /* 담당자 이슈폼 생성 */
  @Override
  public int addFormsPer(FormsPerDTO perDTO) {
    return formsPerDAO.addFormsPer(perDTO);
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

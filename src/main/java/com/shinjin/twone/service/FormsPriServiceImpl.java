package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsPriDAO;
import com.shinjin.twone.dto.FormsPriDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsPriServiceImpl implements FormsPriService {

  @Autowired
  private FormsPriDAO formsPriDAO;

  @Override
  public String createPriSeq() {
    String priSeq = "pri-" + formsPriDAO.createPriSeq();
    return priSeq;
  }

  /* 우선순위 이슈폼 생성 */
  @Override
  public int addFormsPri(FormsPriDTO priDTO) {
    return formsPriDAO.addFormsPri(priDTO);
  }

  /* priTitle 변경 */
  @Override
  public int updatePriTitle(FormsPriDTO priDTO) {
    return formsPriDAO.updatePriTitle(priDTO);
  }

  /* priValue 변경 */
  @Override
  public int updatePriValue(FormsPriDTO priDTO) {
    return formsPriDAO.updatePriValue(priDTO);
  }

  /* priDTO 불러오기 */
  @Override
  public FormsPriDTO getPriDTO(String formsSeq) {
    return formsPriDAO.getPriDTO(formsSeq);
  }
}

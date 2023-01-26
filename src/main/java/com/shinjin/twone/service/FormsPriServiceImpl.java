package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsPriDAO;
import com.shinjin.twone.dto.FormsPriDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsPriServiceImpl implements FormsPriService {

  @Autowired
  private FormsPriDAO formsPriDAO;

  /* 우선순위 이슈폼 생성 */
  @Override
  public String addFormsPri(FormsPriDTO priDTO) {
    String result = null;
    try {
      formsPriDAO.addFormsPri(priDTO);
      // selectkey 를 활용하여 인서트 한 pri_seq 바로 가져오기
      result = "pri-" + priDTO.getPriSeq();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
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

package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsParDAO;
import com.shinjin.twone.dto.FormsParDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsParServiceImpl implements FormsParService {

  @Autowired
  private FormsParDAO formsParDAO;

  /* 단락 이슈폼 생성 */
  @Override
  public String addFormsPar(FormsParDTO parDTO) {
    String result = null;
    try {
      formsParDAO.addFormsPar(parDTO);
      // selectkey 를 활용하여 인서트 한 par_seq 바로 가져오기
      result = "par-" + parDTO.getParSeq();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /* parTitle 변경 */
  @Override
  public int updateParTitle(FormsParDTO parDTO) {
    return formsParDAO.updateParTitle(parDTO);
  }

  /* parValue 변경 */
  @Override
  public int updateParValue(FormsParDTO parDTO) {
    return formsParDAO.updateParValue(parDTO);
  }

  /* FormsParDTO 불러오기 */
  @Override
  public FormsParDTO getParDTO(String parSeq) {
    return formsParDAO.getParDTO(parSeq);
  }
}

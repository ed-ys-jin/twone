package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsSimDAO;
import com.shinjin.twone.dto.FormsSimDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsSimServiceImpl implements FormsSimService{

  @Autowired
  private FormsSimDAO formsSimDAO;

  @Override
  public String createSimSeq() {
    String simSeq = "sim-" + formsSimDAO.createSimSeq();
    return simSeq;
  }

  /* 간단한 텍스트 이슈폼 생성 */
  @Override
  public int addFormsSim(FormsSimDTO simDTO) {
    return formsSimDAO.addFormsSim(simDTO);
  }

  /* simTitle 변경 */
  @Override
  public int updateSimTitle(FormsSimDTO simDTO) {
    return formsSimDAO.updateSimTitle(simDTO);
  }

  /* simValue 변경 */
  @Override
  public int updateSimValue(FormsSimDTO simDTO) {
    return formsSimDAO.updateSimValue(simDTO);
  }

  /* FormsSimDTO 불러오기 */
  @Override
  public FormsSimDTO getSimDTO(String simSeq) {
    return formsSimDAO.getSimDTO(simSeq);
  }
}

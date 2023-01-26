package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsSimDAO;
import com.shinjin.twone.dto.FormsSimDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsSimServiceImpl implements FormsSimService{

  @Autowired
  private FormsSimDAO formsSimDAO;

  /* 간단한 텍스트 이슈폼 생성 */
  @Override
  public String addFormsSim(FormsSimDTO simDTO) {
    String result = null;
    try {
      formsSimDAO.addFormsSim(simDTO);
      // selectkey 를 활용하여 인서트 한 sim_seq 바로 가져오기
      result = "sim-" + simDTO.getSimSeq();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
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

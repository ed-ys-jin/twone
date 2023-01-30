package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsDatDAO;
import com.shinjin.twone.dto.FormsDatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsDatServiceImpl implements FormsDatService {

  @Autowired
  private FormsDatDAO formsDatDAO;

  /* 날짜 이슈폼 생성 */
  @Override
  public String addFormsDat(FormsDatDTO datDTO) {
    String result = null;
    try {
      formsDatDAO.addFormsDat(datDTO);
      // selectkey 를 활용하여 인서트 한 dat_seq 바로 가져오기
      result = "dat-" + datDTO.getDatSeq();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
  }

  /* datTitle 변경 */
  @Override
  public int updateDatTitle(FormsDatDTO datDTO) {
    return formsDatDAO.updateDatTitle(datDTO);
  }

  /* datValue 변경 */
  @Override
  public int updateDatValue(FormsDatDTO datDTO) {
    return formsDatDAO.updateDatValue(datDTO);
  }

  /* FormsDatDTO 불러오기 */
  @Override
  public FormsDatDTO getDatDTO(String datSeq) {
    return formsDatDAO.getDatDTO(datSeq);
  }
}

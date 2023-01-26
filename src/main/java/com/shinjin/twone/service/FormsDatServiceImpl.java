package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsDatDAO;
import com.shinjin.twone.dto.FormsDatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsDatServiceImpl implements FormsDatService {

  @Autowired
  private FormsDatDAO formsDatDAO;

  /* datSeq 생성 */
  @Override
  public String createDatSeq() {
    String datSeq = "dat-" + formsDatDAO.createDatSeq();
    return datSeq;
  }

  /* 날짜 이슈폼 생성 */
  @Override
  public int addFormsDat(FormsDatDTO datDTO) {
    return formsDatDAO.addFormsDat(datDTO);
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

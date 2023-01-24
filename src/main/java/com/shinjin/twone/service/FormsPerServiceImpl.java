package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsPerDAO;
import com.shinjin.twone.dto.FormsPerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsPerServiceImpl implements FormsPerService {

  @Autowired
  private FormsPerDAO formsPerDAO;

  /* FormsPerDTO 불러오기 */
  @Override
  public FormsPerDTO getPerDTO(String perSeq) {
    return formsPerDAO.getPerDTO(perSeq);
  }
}

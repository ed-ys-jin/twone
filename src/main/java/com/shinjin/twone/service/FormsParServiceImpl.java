package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsParDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsParServiceImpl implements FormsParService {

  @Autowired
  private FormsParDAO formsParDAO;
}

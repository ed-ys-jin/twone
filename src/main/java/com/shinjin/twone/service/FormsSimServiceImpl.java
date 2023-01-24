package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsSimDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsSimServiceImpl implements FormsSimService{

  @Autowired
  private FormsSimDAO formsSimDAO;
}

package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsCheDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsCheServiceImpl implements FormsCheService {

  @Autowired
  private FormsCheDAO formsCheDAO;

}

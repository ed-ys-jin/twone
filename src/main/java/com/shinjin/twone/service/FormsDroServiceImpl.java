package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsDroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsDroServiceImpl implements FormsDroService {

  @Autowired
  private FormsDroDAO formsDroDAO;
}

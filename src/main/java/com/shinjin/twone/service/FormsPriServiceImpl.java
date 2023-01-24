package com.shinjin.twone.service;

import com.shinjin.twone.dao.FormsPriDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormsPriServiceImpl implements FormsPriService {

  @Autowired
  private FormsPriDAO formsPriDAO;
}

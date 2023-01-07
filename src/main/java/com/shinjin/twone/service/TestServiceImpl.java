package com.shinjin.twone.service;

import com.shinjin.twone.dao.TestDAO;
import com.shinjin.twone.dto.TestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private TestDAO testDao;

    @Override
    public TestDTO selectList(){
        return testDao.selectList();
    }

}

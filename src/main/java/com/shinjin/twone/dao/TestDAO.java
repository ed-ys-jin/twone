package com.shinjin.twone.dao;

import com.shinjin.twone.dto.TestDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestDAO {
    public TestDTO selectList();
}

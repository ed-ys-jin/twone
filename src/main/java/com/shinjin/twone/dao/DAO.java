package com.shinjin.twone.dao;

import com.shinjin.twone.dto.DTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DAO {
    public List<DTO> selectList();
}

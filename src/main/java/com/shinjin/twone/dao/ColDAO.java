package com.shinjin.twone.dao;

import com.shinjin.twone.dto.ColDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ColDAO {

    public List<ColDTO> getColList(int boardSeq); // 컬럼 리스트 불러오기
    public int addColumn(ColDTO colDTO); // 컬럼 생성
    public int deleteColumn(int colSeq); // 컬럼 삭제

}

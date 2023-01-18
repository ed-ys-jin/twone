package com.shinjin.twone.service;

import com.shinjin.twone.dto.ColDTO;

import java.util.List;

public interface ColService {

    public List<ColDTO> getColList(int boardSeq); // 컬럼 리스트 불러오기
    public int createSampleColumn(ColDTO colDTO); // 샘플 컬럼 생성
    public int addDoneColumn(ColDTO colDTO); // Done 컬럼 생성
    public int addColumn(ColDTO colDTO); // 컬럼 생성
    public int deleteColumn(int colSeq); // 컬럼 삭제

}

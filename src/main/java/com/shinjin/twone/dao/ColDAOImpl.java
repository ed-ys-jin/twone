package com.shinjin.twone.dao;

import com.shinjin.twone.dto.ColDTO;

import java.util.List;

public class ColDAOImpl implements ColDAO {

    /* 컬럼 리스트 불러오기 */
    @Override
    public List<ColDTO> getColList(int boardSeq) {
        return getColList(boardSeq);
    }

    /* 샘플 컬럼 생성 */
    @Override
    public int createSampleColumn(ColDTO colDTO) {
        return createSampleColumn(colDTO);
    }

    /* Done 컬럼 생성 */
    @Override
    public int addDoneColumn(ColDTO colDTO) {
        return addDoneColumn(colDTO);
    }

    /* 컬럼 생성 */
    @Override
    public int addColumn(ColDTO colDTO) {
        return addColumn(colDTO);
    }

    /* 컬럼 삭제 by colSeq */
    @Override
    public int deleteColumn(int colSeq) {
        return deleteColumn(colSeq);
    }

    /* 컬럼 삭제 by boardSeq */
    @Override
    public int deleteColumnByBoardSeq(int boardSeq) {
        return deleteColumnByBoardSeq(boardSeq);
    }

    @Override
    public int deleteColumnByProjectSeq(int projectSeq) {
        return deleteColumnByProjectSeq(projectSeq);
    }
}

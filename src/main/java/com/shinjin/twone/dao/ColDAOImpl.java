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
    public int createsamplecolumn(ColDTO colDTO) {
        return createsamplecolumn(colDTO);
    }

    /* 컬럼 생성 */
    @Override
    public int addColumn(ColDTO colDTO) {
        int result = -1;
        try {
            result = addColumn(colDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 컬럼 삭제 */
    @Override
    public int deleteColumn(int colSeq) {
        return deleteColumn(colSeq);
    }
}

package com.shinjin.twone.service;

import com.shinjin.twone.dao.ColDAO;
import com.shinjin.twone.dto.ColDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColServiceImpl implements ColService {

    @Autowired
    ColDAO colDAO;

    /* 컬럼 리스트 불러오기 */
    @Override
    public List<ColDTO> getColList(int boardSeq) {
        return colDAO.getColList(boardSeq);
    }

    /* 샘플 컬럼 생성 */
    @Override
    public int createsamplecolumn(ColDTO colDTO) {
        colDAO.createsamplecolumn(colDTO);
        // selectkey 를 활용하여 인서트 한 col_seq 바로 가져오기
        return colDTO.getColSeq();
    }

    /* 컬럼 생성 */
    @Override
    public int addColumn(ColDTO colDTO) {
        return colDAO.addColumn(colDTO);
    }

    /* 컬럼 삭제 */
    @Override
    public int deleteColumn(int colSeq) {
        return colDAO.deleteColumn(colSeq);
    }
}

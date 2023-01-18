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
    public int createSampleColumn(ColDTO colDTO) {
        colDAO.createSampleColumn(colDTO);
        // selectkey 를 활용하여 인서트 한 col_seq 바로 가져오기
        return colDTO.getColSeq();
    }

    @Override
    public int addDoneColumn(ColDTO colDTO) {
        return colDAO.addDoneColumn(colDTO);
    }

    /* 컬럼 생성 */
    @Override
    public int addColumn(ColDTO colDTO) {
        int result = -1;
        try {
            result = colDAO.addColumn(colDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 컬럼 삭제 */
    @Override
    public int deleteColumn(int colSeq) {
        return colDAO.deleteColumn(colSeq);
    }
}

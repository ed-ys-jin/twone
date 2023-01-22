package com.shinjin.twone.service;

import com.shinjin.twone.dao.LabelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    LabelDAO labelDAO;

    /* 레이블 삭제 by labSeq */
    @Override
    public int deleteLabel(int labSeq) {
        return labelDAO.deleteLabel(labSeq);
    }

    /* 레이블 삭제 by projectSeq */
    @Override
    public int deleteLabelByProjectSeq(int projectSeq) {
        return labelDAO.deleteLabelByProjectSeq(projectSeq);
    }
}

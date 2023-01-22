package com.shinjin.twone.dao;

public class LabelDAOImpl implements LabelDAO {

    /* 레이블 삭제 by labSeq */
    @Override
    public int deleteLabel(int labSeq) {
        return deleteLabel(labSeq);
    }

    /* 레이블 삭제 by projectSeq */
    @Override
    public int deleteLabelByProjectSeq(int projectSeq) {
        return deleteLabelByProjectSeq(projectSeq);
    }
}

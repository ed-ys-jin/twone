package com.shinjin.twone.service;

public interface LabelService {

    public int deleteLabel(int labSeq); // 레이블 삭제 by labSeq
    public int deleteLabelByProjectSeq(int projectSeq); // 레이블 삭제 by projectSeq

}

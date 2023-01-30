package com.shinjin.twone.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LabelDAO {

    public int deleteLabel(int labSeq); // 레이블 삭제 by labSeq
    public int deleteLabelByProjectSeq(int projectSeq); // 레이블 삭제 by projectSeq

}

package com.shinjin.twone.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LinkedIssueDAO {

    public int deleteLinkedIssue(int issueSeq); // 링크된이슈 삭제

}

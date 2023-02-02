package com.shinjin.twone.dao;

import com.shinjin.twone.dto.LinkedIssueDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LinkedIssueDAO {

  public int addIssueLink(LinkedIssueDTO linkedIssueDTO); // 이슈 링크하기
  public int deleteIssueLink(LinkedIssueDTO linkedIssueDTO); // 이슈 링크 해제

}

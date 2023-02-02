package com.shinjin.twone.service;

import com.shinjin.twone.dto.LinkedIssueDTO;

public interface LinkedIssueService {

  public int addIssueLink(LinkedIssueDTO linkedIssueDTO); // 이슈 링크하기
  public int deleteIssueLink(LinkedIssueDTO linkedIssueDTO); // 이슈 링크 해제

}

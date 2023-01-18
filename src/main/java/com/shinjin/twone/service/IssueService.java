package com.shinjin.twone.service;

import com.shinjin.twone.dto.IssueDTO;

import java.util.List;

public interface IssueService {

    public int addIssue(IssueDTO issueDTO); // 이슈 생성
    public List<IssueDTO> getIssueList(int colSeq); // IssueList 불러오기

}

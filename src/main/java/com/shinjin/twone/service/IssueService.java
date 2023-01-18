package com.shinjin.twone.service;

import com.shinjin.twone.dto.IssueDTO;

import java.util.List;

public interface IssueService {

    public List<IssueDTO> getIssueList(int colSeq); // IssueList 불러오기

}

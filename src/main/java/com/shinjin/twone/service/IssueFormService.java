package com.shinjin.twone.service;

import com.shinjin.twone.dto.IssueFormDTO;

import java.util.List;

public interface IssueFormService {

    public List<IssueFormDTO> getIssueFormList(int issueSeq); // 이슈폼 리스트 불러오기 by issueSeq
    public int deleteIssueForm(int issueFormSeq); // 이슈폼 삭제
    public int deleteFormsUnderIssue(IssueFormDTO issueFormDTO); // 이슈폼 자식 테이블 삭제

}

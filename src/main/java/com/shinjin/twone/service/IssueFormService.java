package com.shinjin.twone.service;

import com.shinjin.twone.dto.IssueFormDTO;

import java.util.List;

public interface IssueFormService {

    public int addIssueForm(IssueFormDTO issueFormDTO); // 이슈폼 생성
    public int getIssueFormSize(int issueSeq); // 이슈폼 사이즈 구하기
    public List<IssueFormDTO> getIssueFormList(int issueSeq); // 이슈폼 리스트 불러오기 by issueSeq
    public int deleteIssueForm(int issueFormSeq); // 이슈폼 삭제

}

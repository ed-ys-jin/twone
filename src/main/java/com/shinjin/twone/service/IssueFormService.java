package com.shinjin.twone.service;

import com.shinjin.twone.dto.IssueFormDTO;

import java.util.List;

public interface IssueFormService {

    public int addIssueForm(IssueFormDTO issueFormDTO); // 이슈폼 생성
    public int updateIssueFormOrder(IssueFormDTO issueFormDTO); // 배치순서 변경
    public int getIssueFormSize(int issueSeq); // 이슈폼 사이즈 구하기
    public List<IssueFormDTO> getIssueFormList(int issueSeq); // 이슈폼 리스트 불러오기 by issueSeq (기본/ASC)
    public List<IssueFormDTO> getIssueFormListDesc(int issueSeq); // 이슈폼 리스트 불러오기 by issueSeq (역순/DESC)
    public int deleteIssueForm(int issueFormSeq); // 이슈폼 삭제
    public int deleteIssueFormByFormsSeq(String formsSeq); // 이슈폼 삭제 by formsSeq

}

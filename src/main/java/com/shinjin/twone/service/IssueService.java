package com.shinjin.twone.service;

import com.shinjin.twone.dto.IssueDTO;

import java.util.List;

public interface IssueService {

    public int addIssue(IssueDTO issueDTO); // 이슈 생성
    public List<IssueDTO> getIssueListByColSeq(int colSeq); // IssueList 불러오기 by bolSeq
    public List<Integer> getIssueSeqListUnderBoard(int boardSeq); // issueSeqList 불러오기 by boardSeq
    public List<Integer> getIssueSeqListUnderProject(int projectSeq); // issueSeqList 불러오기 by projectSeq
    public int deleteIssue(int issueSeq); // 이슈 삭제 by issueSeq
    public int deleteIssueByColSeq(int colSeq); // 이슈 삭제 by colSeq
    public int deleteIssueByBoardSeq(int boardSeq); // 이슈 삭제 by boardSeq
    public int deleteIssueByProjectSeq(int projectSeq); // 이슈 삭제 by projectSeq
}

package com.shinjin.twone.dao;

import com.shinjin.twone.dto.IssueDTO;

import java.util.List;

public class IssueDAOImpl implements IssueDAO {

    /* 이슈 생성 */
    @Override
    public int addIssue(IssueDTO issueDTO) {
        return addIssue(issueDTO);
    }

    /* IssueDTO 변경 */
    @Override
    public int updateIssueDTO(IssueDTO issueDTO) {
        return updateIssueDTO(issueDTO);
    }

    /* 이슈 업데이트 일자 변경 */
    @Override
    public int updateIssueUpdate(int issueSeq) {
        return updateIssueUpdate(issueSeq);
    }

    /* IssueDTO 불러오기 */
    @Override
    public IssueDTO getIssueDTO(int issueSeq) {
        return getIssueDTO(issueSeq);
    }

    /* IssueList 불러오기 by colSeq */
    @Override
    public List<IssueDTO> getIssueListByColSeq(int colSeq) {
        return getIssueListByColSeq(colSeq);
    }

    /* issueSeqList 불러오기 by boardSeq */
    @Override
    public List<Integer> getIssueSeqListUnderBoard(int boardSeq) {
        return getIssueSeqListUnderBoard(boardSeq);
    }

    /* issueSeqList 불러오기 by projectSeq */
    @Override
    public List<Integer> getIssueSeqListUnderProject(int projectSeq) {
        return getIssueSeqListUnderProject(projectSeq);
    }

    /* 이슈 삭제 by issueSeq */
    @Override
    public int deleteIssue(int issueSeq) {
        return deleteIssue(issueSeq);
    }

    /* 이슈 삭제 by colSeq */
    @Override
    public int deleteIssueByColSeq(int colSeq) {
        return deleteIssueByBoardSeq(colSeq);
    }

    /* 이슈 삭제 by boardSeq */
    @Override
    public int deleteIssueByBoardSeq(int boardSeq) {
        return deleteIssueByBoardSeq(boardSeq);
    }

    /* 이슈 삭제 by projectSeq */
    @Override
    public int deleteIssueByProjectSeq(int projectSeq) {
        return deleteIssueByProjectSeq(projectSeq);
    }

}

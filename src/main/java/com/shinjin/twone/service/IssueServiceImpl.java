package com.shinjin.twone.service;

import com.shinjin.twone.dao.IssueDAO;
import com.shinjin.twone.dto.IssueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueDAO issueDAO;

    /* 이슈 생성 */
    @Override
    public int addIssue(IssueDTO issueDTO) {
        return issueDAO.addIssue(issueDTO);
    }

    /* IssueDTO 변경 */
    @Override
    public int updateIssueDTO(IssueDTO issueDTO) {
        return issueDAO.updateIssueDTO(issueDTO);
    }

    /* IssueDTO 불러오기 */
    @Override
    public IssueDTO getIssueDTO(int issueSeq) {
        return issueDAO.getIssueDTO(issueSeq);
    }

    /* IssueList 불러오기 by colSeq */
    @Override
    public List<IssueDTO> getIssueListByColSeq(int colSeq) {
        return issueDAO.getIssueListByColSeq(colSeq);
    }

    /* issueSeqList 불러오기 by boardSeq */
    @Override
    public List<Integer> getIssueSeqListUnderBoard(int boardSeq) {
        return issueDAO.getIssueSeqListUnderBoard(boardSeq);
    }

    /* issueSeqList 불러오기 by projectSeq */
    @Override
    public List<Integer> getIssueSeqListUnderProject(int projectSeq) {
        return issueDAO.getIssueSeqListUnderProject(projectSeq);
    }

    /* 이슈 삭제 by issueSeq */
    @Override
    public int deleteIssue(int issueSeq) {
        return issueDAO.deleteIssue(issueSeq);
    }

    /* 이슈 삭제 by colSeq */
    @Override
    public int deleteIssueByColSeq(int colSeq) {
        return issueDAO.deleteIssueByBoardSeq(colSeq);
    }

    /* 이슈 삭제 by boardSeq */
    @Override
    public int deleteIssueByBoardSeq(int boardSeq) {
        return issueDAO.deleteIssueByBoardSeq(boardSeq);
    }

    /* 이슈 삭제 by projectSeq */
    @Override
    public int deleteIssueByProjectSeq(int projectSeq) {
        return issueDAO.deleteIssueByProjectSeq(projectSeq);
    }

}

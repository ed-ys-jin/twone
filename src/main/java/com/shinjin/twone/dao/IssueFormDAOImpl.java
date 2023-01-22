package com.shinjin.twone.dao;

import com.shinjin.twone.dto.IssueFormDTO;

import java.util.List;

public class IssueFormDAOImpl implements IssueFormDAO {

    /* 이슈폼 리스트 불러오기 by issueSeq */
    @Override
    public List<IssueFormDTO> getIssueFormList(int issueSeq) {
        return getIssueFormList(issueSeq);
    }

    /* 이슈폼 삭제 */
    @Override
    public int deleteIssueForm(int issueFormSeq) {
        return deleteIssueForm(issueFormSeq);
    }

    /* 이슈폼 자식 테이블 삭제 */
    @Override
    public int deleteFormsUnderIssue(IssueFormDTO issueFormDTO) {
        return deleteFormsUnderIssue(issueFormDTO);
    }
}

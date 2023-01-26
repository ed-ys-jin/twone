package com.shinjin.twone.dao;

import com.shinjin.twone.dto.IssueFormDTO;

import java.util.List;

public class IssueFormDAOImpl implements IssueFormDAO {

    /* 이슈폼 생성 */
    @Override
    public int addIssueForm(IssueFormDTO issueFormDTO) {
        return addIssueForm(issueFormDTO);
    }

    /* 이슈폼 사이즈 구하기 */
    @Override
    public int getIssueFormSize(int issueSeq) {
        return getIssueFormSize(issueSeq);
    }

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

}

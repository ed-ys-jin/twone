package com.shinjin.twone.dao;

import com.shinjin.twone.dto.IssueDTO;

import java.util.List;

public class IssueDAOImpl implements IssueDAO {

    /* 이슈 생성 */
    @Override
    public int addIssue(IssueDTO issueDTO) {
        return addIssue(issueDTO);
    }

    /* IssueList 불러오기 */
    @Override
    public List<IssueDTO> getIssueList(int colSeq) {
        return getIssueList(colSeq);
    }
}

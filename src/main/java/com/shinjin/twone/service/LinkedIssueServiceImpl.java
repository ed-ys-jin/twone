package com.shinjin.twone.service;

import com.shinjin.twone.dao.LinkedIssueDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkedIssueServiceImpl implements LinkedIssueService {

    @Autowired
    LinkedIssueDAO linkedIssueDAO;

    /* 링크된이슈 삭제 */
    @Override
    public int deleteLinkedIssue(int issueSeq) {
        return linkedIssueDAO.deleteLinkedIssue(issueSeq);
    }
}

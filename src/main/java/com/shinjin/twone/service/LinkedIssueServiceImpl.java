package com.shinjin.twone.service;

import com.shinjin.twone.dao.LinkedIssueDAO;
import com.shinjin.twone.dto.LinkedIssueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkedIssueServiceImpl implements LinkedIssueService {

    @Autowired
    LinkedIssueDAO linkedIssueDAO;

    /* 이슈 링크하기 */
    @Override
    public int addIssueLink(LinkedIssueDTO linkedIssueDTO) {
        return linkedIssueDAO.addIssueLink(linkedIssueDTO);
    }

    /* 이슈 링크 해제 */
    @Override
    public int deleteIssueLink(LinkedIssueDTO linkedIssueDTO) {
        return linkedIssueDAO.deleteIssueLink(linkedIssueDTO);
    }
}

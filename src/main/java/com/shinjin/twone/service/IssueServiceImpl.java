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

    /* IssueList 불러오기 */
    @Override
    public List<IssueDTO> getIssueList(int colSeq) {
        return issueDAO.getIssueList(colSeq);
    }
}

package com.shinjin.twone.service;

import com.shinjin.twone.dao.IssueFormDAO;
import com.shinjin.twone.dto.IssueFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueFormServiceImpl implements IssueFormService {

    @Autowired
    IssueFormDAO issueFormDAO;

    /* 이슈폼 생성 */
    @Override
    public int addIssueForm(IssueFormDTO issueFormDTO) {
        issueFormDAO.addIssueForm(issueFormDTO);
        // selectkey 를 활용하여 인서트 한 issueform_seq 바로 가져오기
        return issueFormDTO.getIssueFormSeq();
    }

    /* 이슈폼 사이즈 구하기 */
    @Override
    public int getIssueFormSize(int issueSeq) {
        return issueFormDAO.getIssueFormSize(issueSeq);
    }

    /* 이슈폼 리스트 불러오기 by issueSeq */
    @Override
    public List<IssueFormDTO> getIssueFormList(int issueSeq) {
        return issueFormDAO.getIssueFormList(issueSeq);
    }

    /* 이슈폼 삭제 */
    @Override
    public int deleteIssueForm(int issueFormSeq) {
        return issueFormDAO.deleteIssueForm(issueFormSeq);
    }

}

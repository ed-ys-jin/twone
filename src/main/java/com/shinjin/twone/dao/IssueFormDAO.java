package com.shinjin.twone.dao;

import com.shinjin.twone.dto.IssueFormDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IssueFormDAO {

    public List<IssueFormDTO> getIssueFormList(int issueSeq); // 이슈폼 리스트 불러오기 by issueSeq
    public int deleteIssueForm(int issueFormSeq); // 이슈폼 삭제
    public int deleteFormsUnderIssue(IssueFormDTO issueFormDTO); // 이슈폼 자식 테이블 삭제

}

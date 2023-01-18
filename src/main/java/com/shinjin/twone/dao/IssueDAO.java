package com.shinjin.twone.dao;

import com.shinjin.twone.dto.IssueDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IssueDAO {

    public List<IssueDTO> getIssueList(int colSeq); // IssueList 불러오기

}

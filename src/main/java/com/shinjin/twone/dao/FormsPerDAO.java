package com.shinjin.twone.dao;

import com.shinjin.twone.dto.FormsPerDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FormsPerDAO {

  public FormsPerDTO getPerDTO(String perSeq); // FormsPerDTO 불러오기

}

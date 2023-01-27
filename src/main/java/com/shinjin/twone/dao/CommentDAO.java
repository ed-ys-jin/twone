package com.shinjin.twone.dao;

import com.shinjin.twone.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDAO {

  public int addComment(CommentDTO commentDTO); // 댓글 등록
  public List<CommentDTO> getCommentList(int commentSeq); // CommentList 불러오기

}

package com.shinjin.twone.service;

import com.shinjin.twone.dto.CommentDTO;

import java.util.Date;
import java.util.List;

public interface CommentService {

  public Date addComment(CommentDTO commentDTO); // 댓글 등록
  public List<CommentDTO> getCommentList(int commentSeq); // CommentList 불러오기

}

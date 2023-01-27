package com.shinjin.twone.service;

import com.shinjin.twone.dao.CommentDAO;
import com.shinjin.twone.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

  @Autowired
  CommentDAO commentDAO;

  /* 댓글 등록 */
  @Override
  public Date addComment(CommentDTO commentDTO) {
    commentDAO.addComment(commentDTO);
    // selectkey 를 활용하여 인서트 한 comment_date 바로 가져오기
    return commentDTO.getCommentDate();
  }

  /* CommentList 불러오기 */
  @Override
  public List<CommentDTO> getCommentList(int commentSeq) {
    return commentDAO.getCommentList(commentSeq);
  }
}

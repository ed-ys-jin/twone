package com.shinjin.twone.service;

import com.shinjin.twone.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    public int createsampleboard(BoardDTO boardDTO); // 샘플 보드 생성
    public int addBoard(BoardDTO boardDTO); // 보드 생성
    public List<BoardDTO> getBoardList(int projectSeq); // 보드 리스트 불러오기
    public BoardDTO getLatestBoardDTO(int projectSeq); // 최근 생성한 BoardDTO 불러오기
    public BoardDTO getBoardDTO(int boardSeq); // BoardDTO 불러오기
    public int deleteBoard(int boardSeq); // 보드 삭제 by boardSeq
    public int deleteBoardByProjectSeq(int projectSeq); // 보드 삭제 by projectSeq

}

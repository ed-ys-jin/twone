package com.shinjin.twone.service;

import com.shinjin.twone.dto.BoardDTO;

import java.util.List;

public interface BoardService {

    public int addBoard(BoardDTO boardDTO); // 보드 생성
    public List<BoardDTO> getBoardList(); // 보드 리스트 받기
    public BoardDTO getLatestBoardDTO(BoardDTO boardDTO); // 최근 생성한 boardDTO 받기

}

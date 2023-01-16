package com.shinjin.twone.dao;

import com.shinjin.twone.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDAO {

    public int addBoard(BoardDTO boardDTO); // 보드 생성
    public List<BoardDTO> getBoardList(); // 보드 리스트 받기
    public BoardDTO getLatestBoardDTO(BoardDTO boardDTO); // 최근 생성한 boardDTO 받기

}

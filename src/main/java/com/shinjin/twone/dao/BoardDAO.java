package com.shinjin.twone.dao;

import com.shinjin.twone.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDAO {

    public int addBoard(BoardDTO boardDTO); // 보드 생성
    public List<BoardDTO> getBoardList(); // 보드 리스트 불러오기
    public BoardDTO getLatestBoardDTO(int projectSeq); // 최근 생성한 BoardDTO 불러오기
    public BoardDTO getBoardDTO(int boardSeq); // BoardDTO 불러오기
    public int deleteBoard(int boardSeq); // 보드 삭제

}

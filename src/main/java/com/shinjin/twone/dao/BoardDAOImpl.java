package com.shinjin.twone.dao;

import com.shinjin.twone.dto.BoardDTO;

import java.util.List;

public class BoardDAOImpl implements BoardDAO {


    /* 보드 생성 */
    @Override
    public int addBoard(BoardDTO boardDTO) {
        int result = -1;
        try {
            result = addBoard(boardDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 보드 리스트 받기 */
    @Override
    public List<BoardDTO> getBoardList() {
        return getBoardList();
    }

    /* 최근 생성한 boardDTO 받기 */
    @Override
    public BoardDTO getLatestBoardDTO(BoardDTO boardDTO) {
        return getLatestBoardDTO(boardDTO);
    }
}

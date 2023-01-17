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

    /* 보드 리스트 불러오기 */
    @Override
    public List<BoardDTO> getBoardList() {
        return getBoardList();
    }

    /* 최근 생성한 BoardDTO 불러오기 */
    @Override
    public BoardDTO getLatestBoardDTO(int projectSeq) {
        return getLatestBoardDTO(projectSeq);
    }

    /* BoardDTO 불러오기 */
    @Override
    public BoardDTO getBoardDTO(int boardSeq) {
        return getBoardDTO(boardSeq);
    }

    /* 보드 삭제 */
    @Override
    public int deleteBoard(int boardSeq) {
        return deleteBoard(boardSeq);
    }
}

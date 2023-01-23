package com.shinjin.twone.dao;

import com.shinjin.twone.dto.BoardDTO;

import java.util.List;

public class BoardDAOImpl implements BoardDAO {


    /* 샘플 보드 생성 */
    @Override
    public int createSampleBoard(BoardDTO boardDTO) {
        return createSampleBoard(boardDTO);
    }

    /* 보드 생성 */
    @Override
    public int addBoard(BoardDTO boardDTO) {
        return addBoard(boardDTO);
    }

    /* 보드명 변경 */
    @Override
    public int updateBoardName(BoardDTO boardDTO) {
        return updateBoardName(boardDTO);
    }

    /* 보드 리스트 불러오기 */
    @Override
    public List<BoardDTO> getBoardList(int projectSeq) {
        return getBoardList(projectSeq);
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

    /* 보드 삭제 by boardSeq */
    @Override
    public int deleteBoard(int boardSeq) {
        return deleteBoard(boardSeq);
    }

    /* 보드 삭제 by projectSeq */
    @Override
    public int deleteBoardByProjectSeq(int projectSeq) {
        return deleteBoardByProjectSeq(projectSeq);
    }
}

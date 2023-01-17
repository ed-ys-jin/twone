package com.shinjin.twone.service;

import com.shinjin.twone.dao.BoardDAO;
import com.shinjin.twone.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO boardDAO;


    /* 보드 생성 */
    @Override
    public int addBoard(BoardDTO boardDTO) {
        return boardDAO.addBoard(boardDTO);
    }

    /* 보드 리스트 불러오기 */
    @Override
    public List<BoardDTO> getBoardList() {
        return boardDAO.getBoardList();
    }

    /* 최근 생성한 BoardDTO 불러오기 */
    @Override
    public BoardDTO getLatestBoardDTO(int projectSeq) {
        return boardDAO.getLatestBoardDTO(projectSeq);
    }

    /* BoardDTO 불러오기 */
    @Override
    public BoardDTO getBoardDTO(int boardSeq) {
        return boardDAO.getBoardDTO(boardSeq);
    }

    /* 보드 삭제 */
    @Override
    public int deleteBoard(int boardSeq) {
        return boardDAO.deleteBoard(boardSeq);
    }

}

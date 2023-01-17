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

    @Override
    public List<BoardDTO> getBoardList() {
        return boardDAO.getBoardList();
    }

    @Override
    public BoardDTO getLatestBoardDTO(BoardDTO boardDTO) {
        return boardDAO.getLatestBoardDTO(boardDTO);
    }
}
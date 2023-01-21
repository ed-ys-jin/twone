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


    /* 샘플 보드 생성 */
    @Override
    public int createSampleBoard(BoardDTO boardDTO) {
        boardDAO.createSampleBoard(boardDTO);
        // selectkey 를 활용하여 인서트 한 board_seq 바로 가져오기
        return boardDTO.getBoardSeq();
    }

    /* 보드 생성 */
    @Override
    public int addBoard(BoardDTO boardDTO) {
        boardDAO.addBoard(boardDTO);
        // selectkey 를 활용하여 인서트 한 board_seq 바로 가져오기
        return boardDTO.getBoardSeq();
    }

    /* 보드명 변경 */
    @Override
    public int updateBoardName(BoardDTO boardDTO) {
        int result = -1;
        try {
            result = boardDAO.updateBoardName(boardDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 보드 리스트 불러오기 */
    @Override
    public List<BoardDTO> getBoardList(int projectSeq) {
        return boardDAO.getBoardList(projectSeq);
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

    /* 보드 삭제 by boardSeq */
    @Override
    public int deleteBoard(int boardSeq) {
        return boardDAO.deleteBoard(boardSeq);
    }

    /* 보드 삭제 by projectSeq */
    @Override
    public int deleteBoardByProjectSeq(int projectSeq) {
        return boardDAO.deleteBoardByProjectSeq(projectSeq);
    }

}

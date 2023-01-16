package com.shinjin.twone.controller;

import com.shinjin.twone.common.commonMethod;
import com.shinjin.twone.dao.ProjectDAO;
import com.shinjin.twone.dto.BoardDTO;
import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.dto.ProjectDTO;
import com.shinjin.twone.service.BoardService;
import com.shinjin.twone.service.MemService;
import com.shinjin.twone.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private MemService memService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BoardService boardService;

    @RequestMapping("project/board")
    public String viewBoard(HttpServletRequest request, Model model){
        /* memDTO 넘기기 */
        int memSeq = (int) request.getSession().getAttribute("login");
        MemDTO memDTO = memService.getDto(memSeq);
        model.addAttribute("memDTO", memDTO);

        /* projectDTO 넘기기 */
        int projectSeq = Integer.parseInt(request.getParameter("projectSeq"));
        ProjectDTO pdto = projectService.selectOne(projectSeq);
        model.addAttribute("pdto", pdto);

        /* boardDTO 넘기기 : 최근 생성한 보드 */
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setProjectSeq(projectSeq);
        boardDTO = boardService.getLatestBoardDTO(boardDTO);
        model.addAttribute("bdto", boardDTO);

        /* 보드 리스트 넘기기 */
        List<BoardDTO> boardList = boardService.getBoardList();
        model.addAttribute("blist", boardList);

        // project 상세페이지 넘어감
        model.addAttribute("navType", "project");

        return "project/board";
    }

    /*** 보드 생성 ***/
    @GetMapping("/project/addboard")
    @ResponseBody
    public String createBoardProc(BoardDTO boardDTO, HttpServletRequest request) {

        // boardName 입력값, projectSeq 값 파라미터로 받기
        int projectSeq = Integer.parseInt(request.getParameter("pseq"));
        String boardName = request.getParameter("bname");

        // 생성할 boardDTO 만들기
        boardDTO.setProjectSeq(projectSeq);
        boardDTO.setBoardName(boardName);

        // 보드 생성 실패
        if(boardService.addBoard(boardDTO) == -1){
            commonMethod.setAttribute(request, "/project/board", "보드 생성에 실패하였습니다. 관리자에게 문의해 주세요.");
            return "/common/alert";
        }

        // 보드 리스트 문자열에 담기
        List<BoardDTO> boardList = boardService.getBoardList();

        String result = "";
        for(BoardDTO bdto : boardList){
            result += "<li>";
            result += "<a href=\"${twone}/project/board?boardSeq=" + bdto.getBoardSeq() + "\">";
            result += "</i><span>" + bdto.getBoardName() + "</span>";
            result += "</a>";
            result += "</li>";
        }

        return result;
    }

}

package com.shinjin.twone.controller;

import com.shinjin.twone.dto.*;
import com.shinjin.twone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BoardController {

  @Autowired
  private MemService memService;
  @Autowired
  private ProjectService projectService;
  @Autowired
  private BoardService boardService;
  @Autowired
  private ColService colService;
  @Autowired
  private IssueService issueService;
  @Autowired
  private TeamService teamService;

  /*** 보드 출력 ***/
  @RequestMapping("project/board")
  public String viewBoard(@RequestParam(defaultValue = "-1", required = false) int boardSeq, HttpServletRequest request) {

    // project 상세페이지 넘어감
    request.setAttribute("navType", "project");

    /* Attr : memDTO */
    int memSeq = (int) request.getSession().getAttribute("login");
    MemDTO memDTO = memService.getDto(memSeq);
    request.setAttribute("memDTO", memDTO);

    /* Attr : projectDTO */
    int projectSeq = Integer.parseInt(request.getParameter("projectSeq"));
    ProjectDTO pdto = projectService.selectOne(projectSeq);
    request.setAttribute("pdto", pdto);

    /* Attr : teamAllow */
    TeamDTO teamDTO = new TeamDTO();
    teamDTO.setProjectSeq(projectSeq);
    teamDTO.setMemSeq(memSeq);
    teamDTO = teamService.getTeamDTO(teamDTO);
    request.setAttribute("teamAllow", teamDTO.getTeamAllow());

    /* Attr : boardList(보드사이드바 출력용) */
    List<BoardDTO> boardList = boardService.getBoardList(projectSeq);
    request.setAttribute("blist", boardList);

    /* Attr : boardDTO */
    BoardDTO boardDTO;
    // 파라미터로 넘어온 boardSeq가 없음 : 프로젝트 리스트 또는 URL 접속 -> 최근 생성된 보드 넘기기
    if (boardSeq == -1) {
      boardDTO = boardService.getLatestBoardDTO(projectSeq);
      // 파라미터로 넘어온 boardSeq가 존재 : 보드 리스트 클릭으로 접속 -> 지정된 보드 넘기기
    } else {
      boardDTO = boardService.getBoardDTO(boardSeq);
    }
    // 프로젝트에 보드가 한개도 없음
    if (boardDTO == null) {
      return "project/board";
    }
    request.setAttribute("bdto", boardDTO);

    /* Attr : colList */
    List<ColDTO> colList = colService.getColList(boardDTO.getBoardSeq());
    request.setAttribute("clist", colList);

    /* Attr : issueMap */
    Map<Integer, List<IssueDTO>> issueMap = new HashMap<>();
    for (ColDTO cdto : colList) {
      List<IssueDTO> issueList = issueService.getIssueListByColSeq(cdto.getColSeq());
      if (!issueList.isEmpty()) {
        issueMap.put(cdto.getColSeq(), issueList);
      }
    }
    request.setAttribute("imap", issueMap);

    return "project/board";
  }

  /*** 보드 생성 ***/
  @GetMapping("/project/addboard")
  @ResponseBody
  public String createBoardProc(HttpServletRequest request) {

    // boardName 입력값, projectSeq 값 파라미터로 받기
    int projectSeq = Integer.parseInt(request.getParameter("projectSeq"));
    String boardName = request.getParameter("boardName");

    // boardDTO 만들기
    BoardDTO boardDTO = new BoardDTO();
    boardDTO.setProjectSeq(projectSeq);
    boardDTO.setBoardName(boardName);

    // 보드 생성
    int boardSeq = boardService.addBoard(boardDTO);

    // Done 컬럼 만들기
    ColDTO colDTO = new ColDTO();
    colDTO.setProjectSeq(projectSeq);
    colDTO.setBoardSeq(boardSeq);
    colService.addDoneColumn(colDTO);

    // 보드 리스트 문자열에 담기
    String result = boardListToHtmlCode(projectSeq);

    return result;
  }

  /*** 보드명 변경 ***/
  @GetMapping("/project/updateboardname")
  @ResponseBody
  public String updateBoardName(HttpServletRequest request) {

    // boardName 입력값, boardSeq 값 파라미터로 받기
    int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
    String boardName = request.getParameter("boardName");

    // boardDTO 만들기
    BoardDTO boardDTO = boardService.getBoardDTO(boardSeq);
    boardDTO.setBoardName(boardName);

    // 보드명 변경
    boardService.updateBoardName(boardDTO);

    // 보드 리스트 문자열에 담기
    int projectSeq = Integer.parseInt(request.getParameter("projectSeq"));
    String result = boardListToHtmlCode(projectSeq);

    return result;
  }

  /*** 보드 리스트 문자열에 담기 ***/
  public String boardListToHtmlCode(int projectSeq) {
    List<BoardDTO> boardList = boardService.getBoardList(projectSeq);

    String result = "";
    for (BoardDTO bdto : boardList) {
      result += "<li id=\"" + bdto.getBoardSeq() + "\">";
      result += "<div class=\"row\">";
      result += "<div class=\"col-sm-8\">";
      result += "<a href=\"/project/board?projectSeq=" + projectSeq + "&boardSeq=" + bdto.getBoardSeq() + "\">";
      result += "<span>" + bdto.getBoardName() + "</span>";
      result += "</a>";
      result += "</div>";
      result += "<div class=\"col-sm-2\">";
      result += "<div class=\"filter\">";
      result += "<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>";
      result += "<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">";
      result += "<li><a class=\"dropdown-item\" href=\"/project/deleteboard?projectSeq=" + projectSeq + "&boardSeq=" + bdto.getBoardSeq() + "\">보드 삭제</a></li>";
      result += "</ul>";
      result += "</div>";
      result += "</div>";
      result += "</div>";
      result += "</li>";
    }

    return result;
  }

  /*** 보드 삭제 ***/
  @RequestMapping("/project/deleteboard")
  public String deleteBoardProc(HttpServletRequest request) {
    int projectSeq = Integer.parseInt(request.getParameter("projectSeq"));
    int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));

    boardService.deleteBoard(boardSeq);

    // 보드 삭제 후 보드 페이지 재출력이 필요하여 '보드 출력' 메소드로 리다이렉트 시킨다.
    return "redirect:/project/board?projectSeq=" + projectSeq;
  }

}
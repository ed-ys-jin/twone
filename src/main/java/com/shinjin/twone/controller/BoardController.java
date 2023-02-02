package com.shinjin.twone.controller;

import com.shinjin.twone.dto.*;
import com.shinjin.twone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    String blist = boardListToHtmlCode(projectSeq);
    request.setAttribute("blist", blist);

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
    String clist = colListToHtmlCode(boardDTO.getBoardSeq());
    request.setAttribute("clist", clist);

    /* Attr : issueMap */
    List<ColDTO> colList = colService.getColList(boardDTO.getBoardSeq());
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

  /*** 보드 삭제 ***/
  @RequestMapping("/project/deleteboard")
  public String deleteBoardProc(HttpServletRequest request) {
    int projectSeq = Integer.parseInt(request.getParameter("projectSeq"));
    int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));

    boardService.deleteBoard(boardSeq);

    // 보드 삭제 후 보드 페이지 재출력이 필요하여 '보드 출력' 메소드로 리다이렉트 시킨다.
    return "redirect:/project/board?projectSeq=" + projectSeq;
  }

  /*** 컬럼 생성 ***/
  @GetMapping("/project/addcolumn")
  @ResponseBody
  public String createColumnProc(HttpServletRequest request) {

    // columnName 입력값, boardSeq 값 파라미터로 받기
    int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
    String columnName = request.getParameter("colName");

    // projectSeq 얻기
    int projectSeq = boardService.getBoardDTO(boardSeq).getProjectSeq();

    // columnDTO 만들기
    ColDTO colDTO = new ColDTO();
    colDTO.setProjectSeq(projectSeq);
    colDTO.setBoardSeq(boardSeq);
    colDTO.setColName(columnName);

    // 컬럼 생성
    colService.addColumn(colDTO);

    // 컬럼 리스트 문자열에 담기
    String result = colListToHtmlCode(boardSeq);

    return result;
  }

  /*** 컬럼명 변경 ***/
  @GetMapping("/project/updatecolname")
  @ResponseBody
  public String updateColNameProc(HttpServletRequest request){

    // colName 입력값, colSeq 값 파라미터로 받기
    int colSeq = Integer.parseInt(request.getParameter("colSeq"));
    String colName = request.getParameter("colName");

    // colDTO 만들기
    ColDTO colDTO = colService.getColDTO(colSeq);
    colDTO.setColName(colName);

    // 컬럼명 변경
    colService.updateColName(colDTO);

    // 컬럼 리스트 문자열에 담기
    String result = colListToHtmlCode(colDTO.getBoardSeq());

    return result;
  }

  /*** 컬럼 삭제 ***/
  @GetMapping("project/deletecolumn")
  public void deleteColumnProc(HttpServletRequest request) {
    int colSeq = Integer.parseInt(request.getParameter("colSeq"));
    ColDTO colDTO = colService.getColDTO(colSeq);
    colService.deleteColumn(colSeq);
  }

  /*** 이슈 추가 ***/
  @GetMapping("/project/addissue")
  @ResponseBody
  public String addIssueProc(HttpServletRequest request, HttpSession session) {

    // issueDTO 만들기 사전준비
    int memSeq = (int) session.getAttribute("login");
    int colSeq = Integer.parseInt(request.getParameter("colSeq"));
    ColDTO colDTO = colService.getColDTO(colSeq);
    int projectSeq = colDTO.getProjectSeq();
    ProjectDTO projectDTO = projectService.selectOne(projectSeq);
    String issueCode = projectDTO.getProjectKey() + "-";

    // issueDTO 만들기
    IssueDTO issueDTO = new IssueDTO();
    issueDTO.setProjectSeq(projectSeq); // projectSeq
    issueDTO.setBoardSeq(colDTO.getBoardSeq()); // boardSeq
    issueDTO.setColSeq(colSeq); // colSeq
    issueDTO.setMemSeq(memSeq); // memSeq
    issueDTO.setIssueCode(issueCode); // issueCode
    issueDTO.setIssueTitle("TODO");

    // 이슈 생성
    issueService.addIssue(issueDTO);

    // 컬럼 리스트 문자열에 담기
    String result = colListToHtmlCode(colDTO.getBoardSeq());

    return result;
  }

  /* 보드 리스트 문자열에 담기 */
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

  /* 컬럼 리스트 문자열에 담기 */
  public String colListToHtmlCode(int boardSeq) {

    // colList, issueMap 불러오기
    List<ColDTO> colList = colService.getColList(boardSeq);
    Map<Integer, List<IssueDTO>> issueMap = new HashMap<>();
    for (ColDTO cdto : colList) {
      List<IssueDTO> issueList = issueService.getIssueListByColSeq(cdto.getColSeq());
      if (issueList != null) {
        issueMap.put(cdto.getColSeq(), issueList);
      }
    }

    String result = "";
    // Column
    for (ColDTO cdto : colList) {
      result += "<div id=\"col-" + cdto.getColSeq() + "\" class=\"col-lg-2 col-md-6\" style=\"min-width: 300px\">";
      result += "<div class=\"card info-card sales-card\">";

      // Three Dots Dropdown Menu Icon
      result += "<div class=\"filter\">";
      if (cdto.getColType() == 1) {
        result += "<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><img src=\"../resources/bootstrap/img/checkmark.png\" width=\"20\"></a>";
      } else {
        result += "<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>";
        result += "<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">";
        result += "<li><a class=\"dropdown-item\" href=\"javascript:addIssue(" + cdto.getColSeq() + ")\">이슈 추가</a></li>";
        result += "<li><a class=\"dropdown-item\" href=\"javascript:deleteColumn(" + cdto.getColSeq() + ")\">컬럼 삭제</a></li>";
        result += "</ul>";
      }
      result += "</div>";

      // Column Title
      result += "<div class=\"card-body\">";
      result += "<h5 class=\"card-title\">";
      result += "<input id=\"col-update-box\" type=\"text\" value=\"" + cdto.getColName() + "\" onkeyup=\"updateColName(this, " + cdto.getColSeq() + ")\"";
      result += "style=\"border: none; font-family: 'Nunito', sans-serif; font-size: 18px; font-weight: 500; color: #012970\">";
      result += "</h5>";
      result += "</div>";

      // Issue Card
      result += "<div id=\"issue-list-card\">";
      if (issueMap.containsKey(cdto.getColSeq())) {
        for (IssueDTO idto : issueMap.get(cdto.getColSeq())) {
          result += "<div id=\"issue-" + idto.getIssueSeq() + "\" class=\"card-body\" style=\"font-size: 14px\">";
          result += "<div class=\"alert alert-secondary fade show\" role=\"alert\">";
          result += "<a href=\"/project/issue?issueSeq=" + idto.getIssueSeq() + "\" style=\"color: black\">";
          result += "<p>" + idto.getIssueTitle() + "</p>";
          result += "</a>";

          // Three Dots Dropdown Menu Icon
          result += "<div class=\"filter\">";
          result += "<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>";
          result += "<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">";
          result += "<li><a class=\"dropdown-item\" href=\"javascript:deleteIssue(" + idto.getIssueSeq() + ")\">이슈 삭제</a></li>";
          result += "</ul>";
          result += "</div>";

          result += "<hr>";
          result += "<p class=\"mb-0\">" + idto.getIssueCode() + "</p>";
          result += "</div>";
          result += "</div>";
        }
      }
      result += "</div>";
      result += "</div>";
      result += "</div>";
    }

    result += "<div class=\"col-lg-2\" style=\"min-width: 300px\">";

    // Column Creation Button
    result += "<div class=\"col-sm-1\">";
    result += "<button type=\"button\" class=\"btn btn-light\" onclick=\"toggleInput('col-input-box')\">";
    result += "<img src=\"../resources/bootstrap/img/button_plus.png\">";
    result += "</button>";
    result += "</div>";

    // Column Creation Input Box
    result += "<div class=\"col-sm-12\" style=\"margin-top: 12px\">";
    result += "<input type=\"text\" class=\"form-control\" id=\"col-input-box\" style=\"display: none\" placeholder=\"컬럼 제목 입력 후 엔터 (최대 30자)\" onkeyup=\"addColumn(this)\">";
    result += "</div>";

    result += "</div>";

    return result;
  }

}
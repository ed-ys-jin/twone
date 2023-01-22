package com.shinjin.twone.controller;

import com.shinjin.twone.dto.*;
import com.shinjin.twone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
  private IssueFormService issueFormService;
  @Autowired
  private LinkedIssueService linkedIssueService;

  /*** 보드 출력 ***/
  @RequestMapping("project/board")
  public String viewBoard(@RequestParam(defaultValue = "-1", required = false) int boardSeq, HttpServletRequest request, Model model) {

    // project 상세페이지 넘어감
    model.addAttribute("navType", "project");

    /* Attr : memDTO */
    int memSeq = (int) request.getSession().getAttribute("login");
    MemDTO memDTO = memService.getDto(memSeq);
    model.addAttribute("memDTO", memDTO);

    /* Attr : projectDTO */
    int projectSeq = Integer.parseInt(request.getParameter("projectSeq"));
    ProjectDTO pdto = projectService.selectOne(projectSeq);
    model.addAttribute("pdto", pdto);

    /* Attr : boardList(보드사이드바 출력용) */
    List<BoardDTO> boardList = boardService.getBoardList(projectSeq);
    model.addAttribute("blist", boardList);

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
    model.addAttribute("bdto", boardDTO);

    /* Attr : colList */
    List<ColDTO> colList = colService.getColList(boardDTO.getBoardSeq());
    model.addAttribute("clist", colList);

    /* Attr : issueMap */
    Map<Integer, List<IssueDTO>> issueMap = new HashMap<>();
    for (ColDTO cdto : colList) {
      List<IssueDTO> issueList = issueService.getIssueListByColSeq(cdto.getColSeq());
      if (!issueList.isEmpty()) {
        issueMap.put(cdto.getColSeq(), issueList);
      }
    }
    model.addAttribute("imap", issueMap);

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

    /* 이슈 및 하위 레코드 전체 삭제 */
    List<Integer> issueSeqList = issueService.getIssueSeqListUnderBoard(boardSeq);
    for (int issueSeq : issueSeqList) {
      // 이슈폼 및 이슈폼 자식 삭제
      List<IssueFormDTO> issueFormList = issueFormService.getIssueFormList(issueSeq);
      for (IssueFormDTO issueFormDTO : issueFormList) {
        String code = issueFormDTO.getFormsSeq().substring(0, 3);
        String formsTableName = "t_forms_" + code; // forms 테이블명 조합
        String formsColName = code + "_seq"; // forms 컬럼명 조합
        issueFormDTO.setFormsTableName(formsTableName);
        issueFormDTO.setFormsColName(formsColName);
        issueFormService.deleteFormsUnderIssue(issueFormDTO); // 이슈폼 자식 삭제
        issueFormService.deleteIssueForm(issueFormDTO.getIssueFormSeq()); // 이슈폼 삭제
      }
      // 이슈된 링크 삭제
      linkedIssueService.deleteLinkedIssue(issueSeq);
      // 이슈 삭제
      issueService.deleteIssue(issueSeq);
    }

    /* 컬럼 삭제 */
    colService.deleteColumnByBoardSeq(boardSeq);

    /* 보드 삭제 */
    boardService.deleteBoard(boardSeq);

    return "redirect:/project/board?projectSeq=" + projectSeq;
  }

}
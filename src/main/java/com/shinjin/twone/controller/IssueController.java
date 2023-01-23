package com.shinjin.twone.controller;

import com.shinjin.twone.dto.*;
import com.shinjin.twone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IssueController {

  @Autowired
  ProjectService projectService;
  @Autowired
  BoardService boardService;
  @Autowired
  ColService colService;
  @Autowired
  IssueService issueService;
  @Autowired
  IssueFormService issueFormService;
  @Autowired
  LinkedIssueService linkedIssueService;

  /*** 이슈 상세 출력 ***/
  @RequestMapping("/project/issue")
  public String viewIssue(HttpServletRequest request) {

    /* Attr : issueDTO */
    int issueSeq = Integer.parseInt(request.getParameter("issueSeq"));
    IssueDTO issueDTO = issueService.getIssueDTO(issueSeq);
    request.setAttribute("idto", issueDTO);

    /* Attr : projectDTO */
    int projectSeq = issueDTO.getProjectSeq();
    ProjectDTO pdto = projectService.selectOne(projectSeq);
    request.setAttribute("pdto", pdto);

    /* Attr : boardList(보드사이드바 출력용) */
    List<BoardDTO> boardList = boardService.getBoardList(projectSeq);
    request.setAttribute("blist", boardList);

    return "issue/issue";
  }

  /*
  * [이슈 추가] 메소드는 ColController에 작성되어 있음
  * -> 이유 : html 태그 전환하는 메소드를 컬럼 추가 메소드와 공유함
  */

  /*** 이슈 제목 변경 ***/
  @GetMapping("/project/updateissuedto")
  @ResponseBody
  public String updateIssueTitleProc(HttpServletRequest request){

    // 입력값, issueSeq, type 파라미터로 받기
    int issueSeq = Integer.parseInt(request.getParameter("issueSeq"));
    String inputValue = request.getParameter("inputValue");
    String type = request.getParameter("type");

    // issueDTO 만들기
    IssueDTO issueDTO = issueService.getIssueDTO(issueSeq);
    switch (type){
      case "title":
        issueDTO.setIssueTitle(inputValue);
        break;
      case "summary":
        issueDTO.setIssueSummary(inputValue);
        break;
    }

    // IssueDTO 변경
    issueService.updateIssueDTO(issueDTO);

    // 태그 만들기
    String result = "";

    switch (type) {
      case "title":
        result += "<input id=\"issue-update-box\" type=\"text\" value=\"" + issueDTO.getIssueTitle() + "\" onkeyup=\"updateIssueDTO(this, 'title')\"";
        result += "style=\"border: none; font-family: 'Nunito', sans-serif;";
        result += "font-size: 24px; font-weight: 600; color: #012970\">";
        break;
      case "summary":
        result += "<input type=\"text\" class=\"form-control\" value=\"" + issueDTO.getIssueSummary() + "\" onkeyup=\"updateIssueDTO(this, 'summary')\">";
        break;
    }

    return result;
  }

  /*** 이슈 삭제 ***/
  @GetMapping("/project/deleteissue")
  @ResponseBody
  public void deleteIssueProc(HttpServletRequest request) {
    int issueSeq = Integer.parseInt(request.getParameter("issueSeq"));
    issueService.deleteIssue(issueSeq);
  }

}

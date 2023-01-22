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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ColController {

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

    /* 이슈 및 하위 레코드 전체 삭제 */
    List<Integer> issueSeqList = issueService.getIssueSeqListUnderBoard(colDTO.getBoardSeq());
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

    // 컬럼 삭제
    colService.deleteColumn(colSeq); // 컬럼 삭제
  }

  /*** 이슈 추가 ***/
  @RequestMapping("/project/addissue")
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

  /*** 컬럼 리스트 문자열에 담기 ***/
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
          result += "<a href=\"/project/issue\" style=\"color: black\">";
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

package com.shinjin.twone.controller;

import com.shinjin.twone.dto.BoardDTO;
import com.shinjin.twone.dto.ColDTO;
import com.shinjin.twone.dto.IssueDTO;
import com.shinjin.twone.service.BoardService;
import com.shinjin.twone.service.ColService;
import com.shinjin.twone.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ColController {

  @Autowired
  BoardService boardService;
  @Autowired
  ColService colService;
  @Autowired
  IssueService issueService;

  /*** 컬럼 생성 ***/
  @GetMapping("/project/addcolumn")
  @ResponseBody
  public String createColumnProc(HttpServletRequest request) {

    // columnName 입력값, boardSeq 값 파라미터로 받기
    int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
    String columnName = request.getParameter("colName");

    // projectSeq 얻기
    int projectSeq = boardService.getBoardDTO(boardSeq).getProjectSeq();

    // 생성할 columnDTO 만들기
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
    for (ColDTO cdto : colList) {
      result += "<div id=col-" + cdto.getColSeq() + " class=\"col-lg-2 col-md-6\" style=\"min-width: 300px\">";
      result += "<div class=\"card info-card sales-card\">";
      result += "<div class=\"filter\">";
      if (cdto.getColType() == 1) {
        result += "<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><img src=\"../resources/bootstrap/img/checkmark.png\" width=\"20\"></a>";
      } else {
        result += "<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>";
        result += "<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">";
        result += "<li><a class=\"dropdown-item\" href=\"#\">이슈 추가</a></li>";
        result += "<li><a class=\"dropdown-item\" href=\"javascript:deleteColumn(" + cdto.getColSeq() + ")\">컬럼 삭제</a></li>";
        result += "</ul>";
      }
      result += "</div>";
      result += "<div class=\"card-body\">";
      result += "<h5 class=\"card-title\">";
      result += "<input id=\"col-update-box\" type=\"text\" value=\"" + cdto.getColName() + "\" onkeyup=\"updateColName(this, " + cdto.getColSeq() + ")\"";
      result += "style=\"border: none; font-family: 'Nunito', sans-serif; font-size: 18px; font-weight: 500; color: #012970\">";
      result += "</h5>";
      result += "</div>";
      if (issueMap.containsKey(cdto.getColSeq())) {
        for (IssueDTO idto : issueMap.get(cdto.getColSeq())) {
          result += "<div class=\"card-body\" style=\"font-size: 14px\">";
          result += "<a href=\"/project/issue\">";
          result += "<div class=\"alert alert-secondary fade show\" role=\"alert\">";
          result += "<p>" + idto.getIssueTitle() + "</p>";
          result += "<div class=\"filter\">";
          result += "<div class=\"icon\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></div>";
          result += "<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">";
          result += "<li><div class=\"dropdown-item\"><a href=\"/project/deleteissue?issueSeq=" + idto.getIssueSeq() + "\">이슈 삭제</a></div></li>";
          result += "</ul>";
          result += "</div>";
          result += "<hr>";
          result += "<p class=\"mb-0\">" + idto.getIssueCode() + "</p>";
          result += "</div>";
          result += "</a>";
          result += "</div>";
        }
      }
      result += "</div>";
      result += "</div>";
    }
    result += "<div class=\"col-lg-2\" style=\"min-width: 300px\">";
    result += "<div class=\"col-sm-1\">";
    result += "<button type=\"button\" class=\"btn btn-light\" onclick=\"togglecnameinput()\">";
    result += "<img src=\"../resources/bootstrap/img/button_plus.png\">";
    result += "</button>";
    result += "</div>";
    result += "<div class=\"col-sm-12\" style=\"margin-top: 12px\">";
    result += "<input type=\"text\" class=\"form-control\" id=\"cnameinput\" style=\"display: none\" placeholder=\"컬럼 제목 입력 후 엔터\" onkeyup=\"addcolumn(this)\">";
    result += "</div>";
    result += "</div>";

    return result;
  }

  /*** 컬럼 삭제 ***/
  @GetMapping("project/deletecolumn")
  public void deleteColumnProc(HttpServletRequest request) {
    int columnSeq = Integer.parseInt(request.getParameter("colSeq"));
    colService.deleteColumn(columnSeq); // 컬럼 삭제
  }


}

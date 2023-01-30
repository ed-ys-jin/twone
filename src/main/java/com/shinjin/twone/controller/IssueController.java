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
import java.text.SimpleDateFormat;
import java.util.Date;
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
  CommentService commentService;
  @Autowired
  IssueFormService issueFormService;
  @Autowired
  LinkedIssueService linkedIssueService;
  @Autowired
  FormsDatService formsDatService;
  @Autowired
  FormsPerService formsPerService;
  @Autowired
  FormsPriService formsPriService;
  @Autowired
  FormsSimService formsSimService;
  @Autowired
  MemService memService;

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

    /* Attr : commentDTO */
    List<CommentDTO> commentlist = commentService.getCommentList(issueSeq);
    MemDTO memDTO;
    for(CommentDTO cmtdto : commentlist){
      memDTO = memService.getDto(cmtdto.getMemSeq());
      cmtdto.setMemName(memDTO.getMemName());
      cmtdto.setMemImage(memDTO.getMemImage());
    }
    request.setAttribute("cmtlist", commentlist);

    /* Attr : 이슈 세부사항 */
    String result = issueFormListToHtmlCode(issueSeq);
    request.setAttribute("issueFormList", result);

    return "issue/issue";
  }

  /*
  * [이슈 추가] 메소드는 ColController에 작성되어 있음
  * -> 이유 : html 태그 작성하는 메소드를 컬럼 추가 메소드와 공유함
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
        result += "style=\"border: none; background-color: #f6f9ff; font-family: 'Nunito', sans-serif;";
        result += "font-size: 24px; font-weight: 600; color: #012970\">";
        break;
      case "summary":
        result += "<input type=\"text\" class=\"form-control\" style=\"font-size: 14px; font-family: 'Nunito', sans-serif;\" value=\"" + issueDTO.getIssueSummary() + "\" onkeyup=\"updateIssueDTO(this, 'summary')\">";
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

  /*** 댓글 등록 ***/
  @GetMapping("/project/addcomment")
  @ResponseBody
  public String addCommentProc(HttpServletRequest request, HttpSession session) {

    int issueSeq = Integer.parseInt(request.getParameter("issueSeq"));
    String inputValue = request.getParameter("inputValue");
    int memSeq = (int) session.getAttribute("login");

    // commentDTO 만들기
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setIssueSeq(issueSeq);
    commentDTO.setMemSeq(memSeq);
    commentDTO.setCommentValue(inputValue);
    // 댓글 등록
    commentService.addComment(commentDTO);

    // 댓글 문자열 만들기
    String result = commentListToHtmlCode(issueSeq, memSeq);

    return result;
  }

  /*** 댓글 수정 ***/
  @GetMapping("/project/updatecomment")
  @ResponseBody
  public String updateCommentProc(HttpServletRequest request, HttpSession session){
    int memSeq = (int) session.getAttribute("login");
    int issueSeq = Integer.parseInt(request.getParameter("issueSeq"));
    int commentSeq = Integer.parseInt(request.getParameter("commentSeq"));
    String inputValue = request.getParameter("inputValue");

    // CommentDTO 만들기
    CommentDTO commentDTO = new CommentDTO();
    commentDTO.setCommentSeq(commentSeq);
    commentDTO.setCommentValue(inputValue);
    // 댓글 수정
    commentService.updateCommentValue(commentDTO);

    // 댓글 문자열 만들기
    String result = commentListToHtmlCode(issueSeq, memSeq);

    return result;
  }

  /*** 댓글 문자열 만들기 ***/
  public String commentListToHtmlCode(int issueSeq, int memSeq){
    String result = "";
    List<CommentDTO> commentlist = commentService.getCommentList(issueSeq);
    MemDTO memDTO;
    for(CommentDTO cmtdto : commentlist){
      memDTO = memService.getDto(cmtdto.getMemSeq());
      cmtdto.setMemName(memDTO.getMemName());
      cmtdto.setMemImage(memDTO.getMemImage());
    }

    for(CommentDTO cmtdto : commentlist){
      result += "<div class=\"row col-sm-10\">";
      result += "<div class=\"col-sm-2\">";
      if(cmtdto.getMemImage() != null){
        result += "<img src=\"../" + cmtdto.getMemImage() + "\" class=\"rounded-circle\" alt=\"Profile\" width=\"50\">";
      } else {
        result += "<img src=\"../resources/bootstrap/img/profile-img.jpg\" class=\"rounded-circle\" alt=\"Profile\" width=\"50\">";
      }
      result += "</div>";

      result += "<div class=\"col-sm-10\">";
      result += "<p>";
      result += cmtdto.getMemName() + "&nbsp;&nbsp;&nbsp;";
      result += cmtdto.getCommentDate() + "<br>";
      result += cmtdto.getCommentValue();
      if(memSeq == cmtdto.getMemSeq()){
        result += "<br><a href=\"javascript:toggleCommentInput('comment-" + cmtdto.getCommentSeq() + "-update-box')\">수정</a>";
        result += "&nbsp;<a href=\"javascript:deleteComment(" + cmtdto.getCommentSeq() + ")\">삭제</a>";
      }
      result += "</p>";
      result += "</div>";
      result += "<div id=\"comment-" + cmtdto.getCommentSeq() + "-update-box\" class=\"col-sm-12\" style=\"display: none\">";
      result += "<input id=\"comment-" + cmtdto.getCommentSeq() + "-update\" type=\"text\" class=\"form-control\" value=\"" + cmtdto.getCommentValue() + "\" onkeyup=\"updateComment(this, " + cmtdto.getCommentSeq() + ")\">";
      result += "<h5 class=\"card-title\"></h5>";
      result += "</div>";
      result += "</div>";
    }

    return result;
  }

  /*** 댓글 삭제 ***/
  @GetMapping("/project/deletecomment")
  @ResponseBody
  public void deleteComment(HttpServletRequest request){
    int commentSeq = Integer.parseInt(request.getParameter("commentSeq"));
    commentService.deleteComment(commentSeq);
  }

  /*** 이슈폼 생성 ***/
  @GetMapping("/project/addissueform")
  @ResponseBody
  public String addIssueFormProc(HttpServletRequest request, HttpSession session){

    int issueSeq = Integer.parseInt(request.getParameter("issueSeq")); // issueSeq 받기
    String selectedValue = request.getParameter("selectedValue"); // 이슈타입 받기

    // issueFormDTO 만들기
    IssueFormDTO issueFormDTO = new IssueFormDTO();
    int issueFormOrder = issueFormService.getIssueFormSize(issueSeq) + 1; // 이슈폼 배치번호 설정 (기존 이슈폼 개수 + 1)
    issueFormDTO.setIssueSeq(issueSeq);
    issueFormDTO.setIssueFormOrder(issueFormOrder);
    int issueFormSeq;

    switch (selectedValue) {
      case "per":
        // 이슈폼 생성
        String perSeq = formsPerService.createPerSeq(); // perSeq 생성
        issueFormDTO.setFormsSeq(perSeq);
        issueFormSeq = issueFormService.addIssueForm(issueFormDTO);
        // perDTO 만들기
        int memSeq = (int) session.getAttribute("login");
        FormsPerDTO perDTO = new FormsPerDTO();
        perDTO.setPerSeq(perSeq);
        perDTO.setIssueFormSeq(issueFormSeq);
        perDTO.setMemSeq(memSeq);
        // 담당자 이슈폼 생성
        formsPerService.addFormsPer(perDTO);
        break;
      case "dat":
        // 이슈폼 생성
        String datSeq = formsDatService.createDatSeq(); // datSeq 생성
        issueFormDTO.setFormsSeq(datSeq);
        issueFormSeq = issueFormService.addIssueForm(issueFormDTO);
        // datDTO 만들기
        FormsDatDTO datDTO = new FormsDatDTO();
        datDTO.setDatSeq(datSeq);
        datDTO.setIssueFormSeq(issueFormSeq);
        // 날짜 이슈폼 생성
        formsDatService.addFormsDat(datDTO);
        break;
      case "pri":
        // 이슈폼 생성
        String priSeq = formsPriService.createPriSeq(); // priSeq 생성
        issueFormDTO.setFormsSeq(priSeq);
        issueFormSeq = issueFormService.addIssueForm(issueFormDTO);
        // priDTO 만들기
        FormsPriDTO priDTO = new FormsPriDTO();
        priDTO.setPriSeq(priSeq);
        priDTO.setIssueFormSeq(issueFormSeq);
        // 우선순위 이슈폼 생성
        formsPriService.addFormsPri(priDTO);
        break;
      case "sim":
        // 이슈폼 생성
        String simSeq = formsSimService.createSimSeq(); // simSeq 생성
        issueFormDTO.setFormsSeq(simSeq);
        issueFormSeq = issueFormService.addIssueForm(issueFormDTO);
        // simDTO 만들기
        FormsSimDTO simDTO = new FormsSimDTO();
        simDTO.setSimSeq(simSeq);
        simDTO.setIssueFormSeq(issueFormSeq);
        // 간단한 텍스트 이슈폼 생성
        formsSimService.addFormsSim(simDTO);
        break;
    }

    // 이슈폼 문자열 만들기
    String result = issueFormListToHtmlCode(issueSeq);

    return result;
  }

  /*** 이슈폼 위로 이동 ***/
  @GetMapping("/project/moveup")
  @ResponseBody
  public String moveUpProc(HttpServletRequest request){

    int issueSeq = Integer.parseInt(request.getParameter("issueSeq"));
    String formsSeq = request.getParameter("formsSeq");

    // 배치순서 내림차순 정렬된 이슈폼 리스트 불러오기 (배치순서가 1 작은 번호와 바꿔야하므로 큰 수부터 찾아 내려간다)
    List<IssueFormDTO> issueFormList = issueFormService.getIssueFormListDesc(issueSeq);
    // 다음 순서에서 배치순서를 변경해야 함을 알려주는 flag
    Boolean flag = false;

    for(IssueFormDTO ifdto : issueFormList){
      // flag가 올라가 있다면 [배치순서+1]
      if(flag == true){
        ifdto.setIssueFormOrder(ifdto.getIssueFormOrder() + 1);
        issueFormService.updateIssueFormOrder(ifdto);
        // for문 종료 (더이상 변경할 작업이 없음)
        break;
      }
      // 사용자가 변경 요청한 이슈폼이면 [배치순서-1]
      if(ifdto.getFormsSeq().equals(formsSeq)){
        // 배치순서가 1 이라면 for문 종료 (1보다 낮은수 없음. 변경불가)
        if(ifdto.getIssueFormOrder() == 1){
          break;
        }
        ifdto.setIssueFormOrder(ifdto.getIssueFormOrder() - 1);
        issueFormService.updateIssueFormOrder(ifdto);
        // 다음 순서에서 +1 해야하므로 flag 올림
        flag = true;
      }
    }

    // 이슈폼 문자열 만들기
    String result = issueFormListToHtmlCode(issueSeq);

    return result;
  }

  /*** 이슈폼 아래로 이동 ***/
  @GetMapping("/project/movedown")
  @ResponseBody
  public String moveDownProc(HttpServletRequest request){

    int issueSeq = Integer.parseInt(request.getParameter("issueSeq"));
    String formsSeq = request.getParameter("formsSeq");

    // 배치순서 오름차순 정렬된 이슈폼 리스트 불러오기 (배치순서가 1 큰 번호와 바꿔야하므로 작은 수부터 찾아 올라간다)
    List<IssueFormDTO> issueFormList = issueFormService.getIssueFormList(issueSeq);
    // 다음 순서에서 배치순서를 변경해야 함을 알려주는 flag
    Boolean flag = false;

    for(IssueFormDTO ifdto : issueFormList){
      // flag가 올라가 있다면 [배치순서-1]
      if(flag == true){
        ifdto.setIssueFormOrder(ifdto.getIssueFormOrder() - 1);
        issueFormService.updateIssueFormOrder(ifdto);
        // for문 종료 (더이상 변경할 작업이 없음)
        break;
      }
      // 사용자가 변경 요청한 이슈폼이면 [배치순서+1]
      if(ifdto.getFormsSeq().equals(formsSeq)){
        // 배치순서가 마지막 이라면 for문 종료 (더이상 높은수 없음. 변경불가)
        if(ifdto.getIssueFormOrder() == issueFormList.size()){
          break;
        }
        ifdto.setIssueFormOrder(ifdto.getIssueFormOrder() + 1);
        issueFormService.updateIssueFormOrder(ifdto);
        // 다음 순서에서 -1 해야하므로 flag 올림
        flag = true;
      }
    }

    // 이슈폼 문자열 만들기
    String result = issueFormListToHtmlCode(issueSeq);

    return result;
  }

  /* 이슈폼 문자열 만들기 */
  public String issueFormListToHtmlCode(int issueSeq){

    List<IssueFormDTO> issueFormList = issueFormService.getIssueFormList(issueSeq); // (배치순서 정렬된) 이슈폼 리스트 불러오기

    String result = "";

    for(IssueFormDTO ifdto : issueFormList){
      switch (ifdto.getFormsSeq().substring(0,3)){
        case "per":
          FormsPerDTO perDTO = formsPerService.getPerDTO(ifdto.getFormsSeq());
          result += perToHtmlCode(ifdto, perDTO);
          break;
        case "dat":
          FormsDatDTO datDTO = formsDatService.getDatDTO(ifdto.getFormsSeq());
          result += datToHtmlCode(datDTO);
          break;
        case "pri":
          FormsPriDTO priDTO = formsPriService.getPriDTO(ifdto.getFormsSeq());
          result += priToHtmlCode(priDTO);
          break;
        case "sim":
          FormsSimDTO simDTO = formsSimService.getSimDTO(ifdto.getFormsSeq());
          result += simToHtmlCode(simDTO);
          break;
      }
    }

    return result;
  }

  /* 담당자 이슈폼 문자열 만들기 */
  public String perToHtmlCode(IssueFormDTO issueFormDTO, FormsPerDTO perDTO){

    int projectSeq = issueService.getIssueDTO(issueFormDTO.getIssueSeq()).getProjectSeq(); // projectSeq 불러오기
    List<MemDTO> teamList = memService.getTeamMemberForIssueForm(projectSeq); // (권한 구성원 이상의) 팀 멤버 리스트 불러오기
    String perSeq = perDTO.getPerSeq();

    String result = "";

    result += "<div id=\"forms-" + perSeq + "\" class=\"row mb-3\">";

    result += "<label id=\"" + perSeq + "-label-box\" class=\"issue-label col-sm-2 col-form-label\">";
    result += "<input id=\"" + perSeq + "-label\" type=\"text\" value=\"" + perDTO.getPerTitle() + "\" onkeyup=\"updateLabel(this, '" + perSeq + "')\">";
    result += "</label>";

    result += "<div id=\"" + perSeq + "-value-box\" class=\"custom-font col-sm-8\">";
    result += "<select id=\"" + perSeq + "-value\" class=\"form-select\" aria-label=\"Default select example\" onchange=\"updateValue(this, '" + perSeq + "')\">";
    for(MemDTO mdto : teamList){
      if(perDTO.getMemSeq() == mdto.getMemSeq()){
        result += "<option value=\"" + mdto.getMemSeq() + "\" selected>" + mdto.getMemName() + "(" + mdto.getMemEmail() + ")</option>";
      } else {
        result += "<option value=\"" + mdto.getMemSeq() + "\">" + mdto.getMemName() + "(" + mdto.getMemEmail() + ")</option>";
      }
    }
    result += "</select>";
    result += "</div>";

    result += "<div class=\"custom-font col-sm-2\" align=\"right\">";
    result += "<button type=\"button\" class=\"btn btn-secondary\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-collection\"></i></button>";
    result += "<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">";
    result += "<li><a class=\"dropdown-item\" href=\"javascript:moveUpDown('up', '" + perSeq + "')\">위로 이동</a></li>";
    result += "<li><a class=\"dropdown-item\" href=\"javascript:moveUpDown('down', '" + perSeq + "')\">아래로 이동</a></li>";
    result += "<li><a class=\"dropdown-item\" href=\"javascript:deleteForms('" + perSeq + "')\">구성요소 삭제</a></li>";
    result += "</ul>";
    result += "</div>";

    result += "</div>";

    return result;
  }

  /* 날짜 이슈폼 문자열 만들기 */
  public String datToHtmlCode(FormsDatDTO datDTO){

    String datSeq = datDTO.getDatSeq();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String datValue = format.format(datDTO.getDatValue());
    String result = "";

    result += "<div id=\"forms-" + datSeq + "\" class=\"row mb-3\">";

    result += "<label id=\"" + datSeq + "-label-box\" class=\"issue-label col-sm-2 col-form-label\">";
    result += "<input id=\"" + datSeq + "-label\" type=\"text\" value=\"" + datDTO.getDatTitle() + "\" onkeyup=\"updateLabel(this, '" + datSeq + "')\">";
    result += "</label>";

    result += "<div id=\"" + datSeq + "-value-box\" class=\"custom-font col-sm-8\">";
    result += "<input id=\"" + datSeq + "-value\" type=\"date\" class=\"form-control\" value=\"" + datValue + "\" onchange=\"updateValue(this, '" + datSeq + "')\">";
    result += "</div>";

    result += "<div class=\"custom-font col-sm-2\" align=\"right\">";
    result += "<button type=\"button\" class=\"btn btn-secondary\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-collection\"></i></button>";
    result += "<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">";
    result += "<li><a class=\"dropdown-item\" href=\"javascript:moveUpDown('up', '" + datSeq + "')\">위로 이동</a></li>";
    result += "<li><a class=\"dropdown-item\" href=\"javascript:moveUpDown('down', '" + datSeq + "')\">아래로 이동</a></li>";
    result += "<li><a class=\"dropdown-item\" href=\"javascript:deleteForms('" + datSeq + "')\">구성요소 삭제</a></li>";
    result += "</ul>";
    result += "</div>";

    result += "</div>";

    return result;
  }

  /* 우선순위 이슈폼 문자열 만들기 */
  public String priToHtmlCode(FormsPriDTO priDTO){

    String priSeq = priDTO.getPriSeq();
    String priValue = priDTO.getPriValue();
    String[] primary = {"Highest", "High", "Medium", "Low", "Lowest"};
    String result = "";

    result += "<div id=\"forms-" + priSeq + "\" class=\"row mb-3\">";

    result += "<label id=\"" + priSeq + "-label-box\" class=\"issue-label col-sm-2 col-form-label\">";
    result += "<input id=\"" + priSeq + "-label\" type=\"text\" value=\"" + priDTO.getPriTitle() + "\" onkeyup=\"updateLabel(this, '" + priSeq + "')\">";
    result += "</label>";

    result += "<div id=\"" + priSeq + "-value-box\" class=\"custom-font col-sm-8\">";
    result += "<select id=\"" + priSeq + "-value\" class=\"form-select\" aria-label=\"Default select example\" onchange=\"updateValue(this, '" + priSeq + "')\">";
    // priValue 값과 일치하면 selected 삽입
    for(int i = 0; i < 5; i++){
      result += "<option value=\"";
      result += (primary[i].equals(priValue)) ? primary[i] + "\" selected" : primary[i] + "\"";
      result += ">" + primary[i] + "</option>";
    }
    result += "</select>";
    result += "</div>";

    result += "<div class=\"custom-font col-sm-2\" align=\"right\">";
    result += "<button type=\"button\" class=\"btn btn-secondary\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-collection\"></i></button>";
    result += "<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">";
    result += "<li><a class=\"dropdown-item\" href=\"javascript:moveUpDown('up', '" + priSeq + "')\">위로 이동</a></li>";
    result += "<li><a class=\"dropdown-item\" href=\"javascript:moveUpDown('down', '" + priSeq + "')\">아래로 이동</a></li>";
    result += "<li><a class=\"dropdown-item\" href=\"javascript:deleteForms('" + priSeq + "')\">구성요소 삭제</a></li>";
    result += "</ul>";
    result += "</div>";

    result += "</div>";

    return result;
  }

  /* 간단한 텍스트 문자열 만들기 */
  public String simToHtmlCode(FormsSimDTO simDTO){

    String simSeq = simDTO.getSimSeq();
    String result = "";

    result += "<div id=\"forms-" + simDTO.getSimSeq() + "\" class=\"row mb-3\">";

    result += "<label id=\"" + simSeq + "-label-box\" class=\"issue-label col-sm-2 col-form-label\">";
    result += "<input id=\"" + simSeq + "-label\" type=\"text\" value=\"" + simDTO.getSimTitle() + "\" onkeyup=\"updateLabel(this, '" + simSeq + "')\">";
    result += "</label>";

    result += "<div id=\"" + simSeq + "-value-box\" class=\"custom-font col-sm-8\">";
    result += "<input id=\"" + simSeq + "-value\" type=\"text\" class=\"form-control\" value=\"" + simDTO.getSimValue() + "\" onchange=\"updateValue(this, '" + simSeq + "')\">";
    result += "</div>";

    result += "<div class=\"custom-font col-sm-2\" align=\"right\">";
    result += "<button type=\"button\" class=\"btn btn-secondary\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-collection\"></i></button>";
    result += "<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">";
    result += "<li><a class=\"dropdown-item\" href=\"javascript:moveUpDown('up', '" + simSeq + "')\">위로 이동</a></li>";
    result += "<li><a class=\"dropdown-item\" href=\"javascript:moveUpDown('down', '" + simSeq + "')\">아래로 이동</a></li>";
    result += "<li><a class=\"dropdown-item\" href=\"javascript:deleteForms('" + simSeq + "')\">구성요소 삭제</a></li>";
    result += "</ul>";
    result += "</div>";

    result += "</div>";

    return result;
  }

}









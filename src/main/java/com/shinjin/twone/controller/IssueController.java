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
  @Autowired
  FormsCheService formsCheService;
  @Autowired
  FormsDatService formsDatService;
  @Autowired
  FormsDroService formsDroService;
  @Autowired
  FormsParService formsParService;
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
      case "che":
        break;
      case "dro":
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
      case "par":
        // 이슈폼 생성
        String parSeq = formsParService.createParSeq(); // parSeq 생성
        issueFormDTO.setFormsSeq(parSeq);
        issueFormSeq = issueFormService.addIssueForm(issueFormDTO);
        // parDTO 만들기
        FormsParDTO parDTO = new FormsParDTO();
        parDTO.setParSeq(parSeq);
        parDTO.setIssueFormSeq(issueFormSeq);
        // 단락 이슈폼 생성
        formsParService.addFormsPar(parDTO);
        break;
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
        case "che":
          break;
        case "dro":
          break;
        case "sim":
          FormsSimDTO simDTO = formsSimService.getSimDTO(ifdto.getFormsSeq());
          result += simToHtmlCode(simDTO);
          break;
        case "par":
          FormsParDTO parDTO = formsParService.getParDTO(ifdto.getFormsSeq());
          result += parToHtmlCode(parDTO);
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

    result += "<div class=\"row mb-3\">";
    result += "<label id=\"" + perSeq + "-label-box\" class=\"issue-label col-sm-2 col-form-label\">";
    result += "<input id=\"" + perSeq + "-label\" type=\"text\" value=\"" + perDTO.getPerTitle() + "\" onkeyup=\"updateLabel(this, '" + perSeq + "')\">";
    result += "</label>";
    result += "<div id=\"" + perSeq + "-value-box\" class=\"custom-font col-sm-10\">";
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
    result += "</div>";

    return result;
  }

  /* 날짜 이슈폼 문자열 만들기 */
  public String datToHtmlCode(FormsDatDTO datDTO){

    String datSeq = datDTO.getDatSeq();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    String datValue = format.format(datDTO.getDatValue());
    String result = "";

    result += "<div class=\"row mb-3\">";
    result += "<label id=\"" + datSeq + "-label-box\" class=\"issue-label col-sm-2 col-form-label\">";
    result += "<input id=\"" + datSeq + "-label\" type=\"text\" value=\"" + datDTO.getDatTitle() + "\" onkeyup=\"updateLabel(this, '" + datSeq + "')\">";
    result += "</label>";
    result += "<div id=\"" + datSeq + "-value-box\" class=\"custom-font col-sm-10\">";
    result += "<input id=\"" + datSeq + "-value\" type=\"date\" class=\"form-control\" value=\"" + datValue + "\" onchange=\"updateValue(this, '" + datSeq + "')\">";
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

    result += "<div class=\"row mb-3\">";
    result += "<label id=\"" + priSeq + "-label-box\" class=\"issue-label col-sm-2 col-form-label\">";
    result += "<input id=\"" + priSeq + "-label\" type=\"text\" value=\"" + priDTO.getPriTitle() + "\" onkeyup=\"updateLabel(this, '" + priSeq + "')\">";
    result += "</label>";
    result += "<div id=\"" + priSeq + "-value-box\" class=\"custom-font col-sm-10\">";
    result += "<select id=\"" + priSeq + "-value\" class=\"form-select\" aria-label=\"Default select example\" onchange=\"updateValue(this, '" + priSeq + "')\">";
    // priValue 값과 일치하면 selected 삽입
    for(int i = 0; i < 5; i++){
      result += "<option value=\"";
      result += (primary[i].equals(priValue)) ? primary[i] + "\" selected" : primary[i] + "\"";
      result += ">" + primary[i] + "</option>";
    }
    result += "</select>";
    result += "</div>";
    result += "</div>";

    return result;
  }

  /* 간단한 텍스트 문자열 만들기 */
  public String simToHtmlCode(FormsSimDTO simDTO){

    String simSeq = simDTO.getSimSeq();
    String result = "";

    result += "<div class=\"row mb-3\">";
    result += "<label id=\"" + simSeq + "-label-box\" class=\"issue-label col-sm-2 col-form-label\">";
    result += "<input id=\"" + simSeq + "-label\" type=\"text\" value=\"" + simDTO.getSimTitle() + "\" onkeyup=\"updateLabel(this, '" + simSeq + "')\">";
    result += "</label>";
    result += "<div id=\"" + simSeq + "-value-box\" class=\"custom-font col-sm-10\">";
    result += "<input id=\"" + simSeq + "-value\" type=\"text\" class=\"form-control\" value=\"" + simDTO.getSimValue() + "\" onchange=\"updateValue(this, '" + simSeq + "')\">";
    result += "</div>";
    result += "</div>";

    return result;
  }

  /* 단락 문자열 만들기 */
  public String parToHtmlCode(FormsParDTO parDTO){

    String parSeq = parDTO.getParSeq();
    String result = "";

    result += "<div class=\"row mb-3\">";
    result += "<label id=\"" + parSeq + "-label-box\" class=\"issue-label col-sm-2 col-form-label\">";
    result += "<input id=\"" + parSeq + "-label\" type=\"text\" value=\"" + parDTO.getParTitle() + "\" onkeyup=\"updateLabel(this, '" + parSeq + "')\">";
    result += "</label>";
    result += "<div id=\"" + parSeq + "-value-box\" class=\"custom-font col-sm-10\">";
    result += "<div id=\"" + parSeq + "-value\" class=\"quill-editor-default\" value=\"" + parDTO.getParValue() + "\" onchange=\"updateValue(this, '" + parSeq + "')\"></div>";
    result += "</div>";
    result += "</div>";

    return result;

  }

}









package com.shinjin.twone.controller;

import com.shinjin.twone.dto.ColDTO;
import com.shinjin.twone.dto.IssueDTO;
import com.shinjin.twone.dto.IssueFormDTO;
import com.shinjin.twone.dto.ProjectDTO;
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
  ColService colService;
  @Autowired
  IssueService issueService;
  @Autowired
  IssueFormService issueFormService;
  @Autowired
  LinkedIssueService linkedIssueService;

  /*** 이슈 상세 페이지로 이동 ***/
  @RequestMapping("/project/issue")
  public String viewIssue() {
    return "issue/issue";
  }

  /*
  * [이슈 추가] 메소드는 ColController에 작성되어 있음
  * -> 이유 : html 태그 전환하는 메소드를 컬럼 추가 메소드와 공유함
  */

  /*** 이슈 삭제 ***/
  @GetMapping("project/deleteissue")
  @ResponseBody
  public void deleteIssueProc(HttpServletRequest request) {
    int issueSeq = Integer.parseInt(request.getParameter("issueSeq"));

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

}

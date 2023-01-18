package com.shinjin.twone.controller;

import com.shinjin.twone.common.commonMethod;
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

    /*** 보드 출력 ***/
    @RequestMapping("project/board")
    public String viewBoard(@RequestParam(defaultValue="-1", required=false) int boardSeq, HttpServletRequest request, Model model) {

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
        if(boardSeq == -1) {
            boardDTO = boardService.getLatestBoardDTO(projectSeq);
        // 파라미터로 넘어온 boardSeq가 존재 : 보드 리스트 클릭으로 접속 -> 지정된 보드 넘기기
        } else {
            boardDTO = boardService.getBoardDTO(boardSeq);
        }
        // 프로젝트에 보드가 한개도 없음
        if(boardDTO == null){
            return "project/board";
        }
        model.addAttribute("bdto", boardDTO);

        /* Attr : colList */
        List<ColDTO> colList = colService.getColList(boardDTO.getBoardSeq());
        model.addAttribute("clist", colList);

        /* Attr : issueMap */
        Map<Integer, List<IssueDTO>> issueMap = new HashMap<>();
        for(ColDTO cdto : colList) {
            List<IssueDTO> issueList = issueService.getIssueList(cdto.getColSeq());
            if(!issueList.isEmpty()) {
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

        // 생성할 boardDTO 만들기
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setProjectSeq(projectSeq);
        boardDTO.setBoardName(boardName);

        // 보드 생성 실패
        if(boardService.addBoard(boardDTO) == -1) {
            commonMethod.setAttribute(request, "/project/board", "보드 생성에 실패하였습니다. 관리자에게 문의해 주세요.");
            return "/common/alert";
        }

        // 보드 리스트 문자열에 담기
        List<BoardDTO> boardList = boardService.getBoardList(projectSeq);

        String result = "";
        for(BoardDTO bdto : boardList) {
            result += "<li>";
            result += "<a href=\"${twone}/project/board?projectSeq=" + projectSeq + "&boardSeq=" + bdto.getBoardSeq() + "\">";
            result += "</i><span>" + bdto.getBoardName() + "</span>";
            result += "</a>";
            result += "</li>";
        }

        return result;
    }


    /*** 컬럼 생성 ***/
    @GetMapping("/project/addcolumn")
    @ResponseBody
    public String createColumnProc(HttpServletRequest request) {

        // columnName 입력값, boardSeq 값 파라미터로 받기
        int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));
        String columnName = request.getParameter("colName");

        // 생성할 columnDTO 만들기
        ColDTO colDTO = new ColDTO();
        colDTO.setBoardSeq(boardSeq);
        colDTO.setColName(columnName);

        // 컬럼 생성
        colService.addColumn(colDTO);

        // 컬럼 리스트 문자열에 담기
        String result = createHtmlCodeForBoardView(boardSeq);

        return result;
    }

    /*** 컬럼 삭제 ***/
    @GetMapping("project/deletecolumn")
    public String deleteColumnProc(HttpServletRequest request){

        int columnSeq = Integer.parseInt(request.getParameter("colSeq"));
//        int boardSeq = Integer.parseInt(request.getParameter("boardSeq"));

        // 컬럼 삭제
        colService.deleteColumn(columnSeq);

        // 컬럼 리스트 문자열에 담기
//        String result = createHtmlCodeForBoardView(boardSeq);

        return null;
    }

    /*** 컬럼 리스트 문자열에 담기 ***/
    public String createHtmlCodeForBoardView(int boardSeq) {

        // colList, issueMap 불러오기
        List<ColDTO> colList = colService.getColList(boardSeq);
        Map<Integer, List<IssueDTO>> issueMap = new HashMap<>();
        for (ColDTO cdto : colList) {
            List<IssueDTO> issueList = issueService.getIssueList(cdto.getColSeq());
            if (issueList != null) {
                issueMap.put(cdto.getColSeq(), issueList);
            }
        }

        String result = "";
        for (ColDTO cdto : colList) {
            result += "<div id=" + cdto.getColSeq() + " class=\"col-lg-2 col-md-6\" style=\"min-width: 300px\">";
            result += "<div class=\"card info-card sales-card\">";
            result += "<div class=\"filter\">";
            result += "<a class=\"icon\" href=\"#\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></a>";
            result += "<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">";
            result += "<li><a class=\"dropdown-item\" href=\"#\">이슈 추가</a></li>";
            result += "<li><a class=\"dropdown-item\" href=\"javascript:deletecolumn(" + cdto.getColSeq() + ")\">컬럼 삭제</a></li>";
            result += "</ul>";
            result += "</div>";
            result += "<div class=\"card-body\">";
            result += "<h5 class=\"card-title\">" + cdto.getColName() + "</h5>";
            result += "</div>";
            if (issueMap.containsKey(cdto.getColSeq())) {
                for (IssueDTO idto : issueMap.get(cdto.getColSeq())) {
                    result += "<div class=\"card-body\" style=\"font-size: 14px\">";
                    result += "<a href=\"${twone}/project/issue\">";
                    result += "<div class=\"alert alert-secondary fade show\" role=\"alert\">";
                    result += "<p>" + idto.getIssueTitle() + "</p>";
                    result += "<div class=\"filter\">";
                    result += "<div class=\"icon\" data-bs-toggle=\"dropdown\"><i class=\"bi bi-three-dots\"></i></div>";
                    result += "<ul class=\"dropdown-menu dropdown-menu-end dropdown-menu-arrow\">";
                    result += "<li><div class=\"dropdown-item\"><a href=\"${twone}/project/deleteissue?issueSeq=" + idto.getIssueSeq() + "\">이슈 삭제</a></div></li>";
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

}
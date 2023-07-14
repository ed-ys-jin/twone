package com.shinjin.twone.common;

import com.shinjin.twone.dto.BoardDTO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommonMethod {

    /*** URL/메세지 set/add ***/
    public static void setAttribute(Model model, String url) {
        model.addAttribute("url", url);
        return;
    }
    public static void setAttribute(Model model, String url, String msg) {
        model.addAttribute("url", url);
        model.addAttribute("msg", msg);
        return;
    }
    public static void setAttribute(HttpServletRequest request, String url) {
        request.setAttribute("url", url);
        return;
    }
    public static void setAttribute(HttpServletRequest request, String url, String msg) {
        request.setAttribute("url", url);
        request.setAttribute("msg", msg);
        return;
    }

    /*숫자인지 판별하는 메소드*/
    public static boolean isNumber(String str){
        try{
            Double.parseDouble(str);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    /*
    * 보드 리스트 문자열에 담기
    * : 프로젝트 사이드바에 출력되는 html 코드이기에 board, issue 페이지 등에서 공통 사용되므로 CommonMethod에 작성함
    */
    public static String boardListToHtmlCode(List<BoardDTO> boardList, int projectSeq, int teamAllow) {

        String result = "";
        for (BoardDTO bdto : boardList) {
            // 보드 리스트
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

        // 보드 추가 버튼, 보드명 입력창
        // 접근 권한이 관리자(1), 구성원(2)인 경우에만 출력
        if(teamAllow == 1 || teamAllow == 2){
            result += "<li>";
            result += "<div class=\"row addboard\">";
            result += "<button class=\"btn btn-primary btn-light\" type=\"button\" onclick=\"toggleInput('board-input-box')\">";
            result += "<img src=\"../resources/bootstrap/img/button_plus.png\" width=\"17\">";
            result += "</button>";
            result += "<br>";
            result += "<input type=\"text\" class=\"form-control\" id=\"board-input-box\" style=\"display: none\" placeholder=\"보드명 입력 후 엔터\" onkeyup=\"addBoard(this)\">";
            result += "</div>";
            result += "</li>";
        }

        return result;
    }

}

package com.shinjin.twone.common;

import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public class commonMethod {

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

}
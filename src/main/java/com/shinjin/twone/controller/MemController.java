package com.shinjin.twone.controller;

import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MemController {

    @Autowired
    private MemService memService;

    /* 로그인 */
    @RequestMapping("/login")
    public String loginView(){
        return "/member/login";
    }

    /* 회원가입 */
    @RequestMapping("/signup")
    public String signupView(){
        return "/member/signup";
    }

    @RequestMapping("/member/signupProc")
    public String signupProc(@Valid MemDTO memDto, Errors errors, Model model){

        /* 유효성 검사 */
        if(errors.hasErrors()){
            // 회원가입 실패시 입력 데이터 값을 유지
            model.addAttribute("memDto", memDto);

            Map<String, String> validatorResult = memService.validatorHandling(errors);
            for(String key : validatorResult.keySet()){
                model.addAttribute(key, validatorResult.get(key));
            }
            // 회원가입 페이지로 리턴
            return "/member/signup";
        }

//        memService.signupProc(memDto);
        return "/member/login";
    }

    /* 프로필 */
    @RequestMapping("/profile")
    public String profileView(){
        return "member/profile";
    }

}

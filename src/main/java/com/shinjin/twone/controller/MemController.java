package com.shinjin.twone.controller;

import com.shinjin.twone.common.commonMethod;
import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class MemController {

    @Autowired
    private MemService memService;

    /* 회원가입 */
    @RequestMapping("/signup")
    public String signupView(){
        return "/member/signup";
    }

    @PostMapping("/signup")
    public String signupProc(@Valid MemDTO memDto, Errors errors, Model model) throws Exception {

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

        /* 중복 이메일 확인 */
        if(memService.checkWithdraw(memDto.getMemEmail()) == 0){ // 기등록된 이메일이 존재하며, 가입중 상태인 경우
            commonMethod.setAttribute(model, "/signup", "이미 등록된 이메일 계정입니다.");
            return "/common/alert";
        }

        /* 회원 등록 */
        if(memService.signup(memDto) != 0) { // 회원 등록 성공
            commonMethod.setAttribute(model, "/login?email=" + memDto.getMemEmail(), "TWONE 회원이 되었습니다. 로그인을 진행해 주세요.");
        } else { // 회원 등록 실패
            commonMethod.setAttribute(model, "/signup", "회원가입에 실패하였습니다. 관리자에게 문의해 주세요.");
        }

        return "/common/alert";
    }


    /* 로그인 */
    @RequestMapping("/login")
    public String loginView(HttpServletRequest request){

        // email 값을 파라미터로 받는다.
        String email = request.getParameter("email");

        request.setAttribute("email", email);

        return "/member/login";
    }


    /* 프로필 */
    @RequestMapping("/profile")
    public String profileView(){
        return "member/profile";
    }

}

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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
public class MemController {

    @Autowired
    private MemService memService;

    /*** 회원가입 ***/
    @RequestMapping("/signup")
    public String signupView(){
        return "/member/signup";
    }

    @PostMapping("/signup")
    public String signupProc(@Valid MemDTO memDto, Errors errors, Model model) {

        /* 유효성 검사 */
        if(errors.hasErrors()){ // 유효성 검사 실패
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
        // 기등록된 이메일이 존재하며, 가입중 상태인 경우 (가입중 : 0, 탈퇴 : 1)
        if(memService.checkDupl(memDto.getMemEmail()) == 0){
            commonMethod.setAttribute(model, "/signup", "이미 등록된 이메일 계정입니다.");
            return "/common/alert";
        }

        /* 회원 등록 */
        // 회원 등록 성공
        if(memService.signup(memDto) != -1) {
            commonMethod.setAttribute(model, "/login?email=" + memDto.getMemEmail(), "TWONE 회원이 되었습니다. 로그인을 진행해 주세요.");
        // 회원 등록 실패
        } else {
            commonMethod.setAttribute(model, "/signup", "회원가입에 실패하였습니다. 관리자에게 문의해 주세요.");
        }

        return "/common/alert";
    }


    /*** 로그인 ***/
    @RequestMapping("/login")
    public String loginView(HttpServletRequest request) {

        String email = request.getParameter("email"); // 회원가입 시 입력받은 email 값을 파라미터로 받기

        boolean saveidFlag = false; // 아이디 저장 체크박스 상태값

        if(email == null){ // 회원가입이 아닌 다른 경로로 접속
            Cookie[] cookiebox = request.getCookies(); // 쿠키파일 불러오기

            if(cookiebox != null){ // 쿠키파일 값이 존재
                for(Cookie ck : cookiebox){
                    if(ck.getName().equals("saveid")){
                        email = ck.getValue();
                        saveidFlag = true;
                        break;
                    }
                }
            }
        }

        request.setAttribute("saveid", email);
        request.setAttribute("flag", saveidFlag);

        return "/member/login";
    }

    @PostMapping("/login")
    public String loginProc(MemDTO memDto, HttpServletRequest request, HttpServletResponse response) {

        MemDTO dto = memService.login(memDto); // DTO 불러오기

        /* 로그인 가능 여부 확인 */
        // 일치하는 ('가입중' 상태의) 이메일 계정이 없음
        if(dto == null){
            commonMethod.setAttribute(request, "/login", "존재하지 않는 이메일 계정입니다.");
            return "/common/alert";
        // 일치하는 이메일은 존재하나, 비밀번호 다름
        } else if(!dto.getMemPw().equals(memDto.getMemPw())) {
            commonMethod.setAttribute(request, "/login", "비밀번호가 일치하지 않습니다.");
            return "common/alert";
        }

        /* 로그인 성공 */
        request.getSession().setAttribute("login", dto.getMemSeq()); // memSeq를 login 세션 저장

        /* 아이디 저장 */
        boolean saveid = Boolean.parseBoolean(request.getParameter("saveid")); // 아이디 저장 flag 값 받기

        Cookie[] cookiebox = request.getCookies(); // 쿠키파일 불러오기

        Cookie cookie = null;

        // 쿠키파일 값이 존재
        if(cookiebox != null){
            for(Cookie ck : cookiebox){
                if(ck.getName().equals("savaid")){
                    cookie = ck;
                    break;
                }
            }
        }

        // 아이디 저장이 체크되어 있음
        if(saveid == true){
            // 기존 쿠키 값이 없음
            if(cookie == null){
                cookie = new Cookie("saveid", dto.getMemEmail()); // 쿠키 값 설정
                cookie.setPath("/"); // root 경로 설정
                cookie.setMaxAge(60 * 60 * 24 * 30);  // 30일동안 유효
                response.addCookie(cookie);
            // 기존 쿠키 값이 존재
            } else {
                // 기존 쿠키 값과 다름
                if(!cookie.getValue().equals(dto.getMemEmail())){
                    cookie.setValue(dto.getMemEmail());
                    response.addCookie(cookie);
                }
            }
        // 아이디 저장이 체크되어 있지 않음
        } else if (saveid == false) {
            cookie = new Cookie("saveid", null); // 쿠키 값 null로 설정
            cookie.setMaxAge(0);  // 남은 유효시간 0으로 설정
            response.addCookie(cookie);
        }

        commonMethod.setAttribute(request, "/project");

        return "/common/noalert";
    }


    /*** 로그아웃 ***/
    @RequestMapping("/logout")
    public String logoutProc(Model model, HttpSession session) {

        session.invalidate(); // 세션 해제

        commonMethod.setAttribute(model, "/login"); // 로그인 페이지로 이동

        return "/common/noalert";
    }


    /*** 회원탈퇴 ***/
    @RequestMapping("withdraw")
    public String withdrawView(){
        return "/member/withdraw";
    }

    @PostMapping("/withdraw")
    public String withdrawProc(MemDTO memDto, Model model, HttpSession session) {

        int memSeq = (int) session.getAttribute("login"); // 세션 정보 불러오기

        memDto.setMemSeq(memSeq); // memDto(memSeq, memPw)

        // 회원탈퇴 성공
        if(memService.withdraw(memDto) != 0){
            commonMethod.setAttribute(model, "/login", "회원탈퇴 처리가 완료되었습니다. 그동안 TWONE 서비스를 이용해 주셔서 감사합니다. 더욱더 노력하고 발전하는 TWONE이 되도록 노력하겠습니다.");
        // 회원탈퇴 실패
        } else {
            commonMethod.setAttribute(model, "/withdraw", "비밀번호 불일치 등의 이유로 회원탈퇴 처리에 실패했습니다. 담당자에게 문의해 주세요.");
        }

        return "/common/alert";
    }

    /*** 프로필 ***/
    @RequestMapping("/profile")
    public String profileView(Model model, HttpSession session) {

        int memSeq = (int) session.getAttribute("login"); // 세션 정보 불러오기

        MemDTO memDto = memService.getDto(memSeq); // memDTO 불러오기

        model.addAttribute("memDto", memDto);

        return "/member/profile";
    }

    @RequestMapping("/editprofile")
    public String editProfileProc(MemDTO memDto, Model model, HttpSession session) {

        int memSeq = (int) session.getAttribute("login"); // 세션 정보 불러오기

        memDto.setMemSeq(memSeq); // 수정할 정보를 담은 memDTO 만들기

        /* 회원정보 수정 */
        if(memService.updateMemInfo(memDto) == -1){ // 정보 수정 실패
            commonMethod.setAttribute(model, "/profile", "정보 수정에 실패하였습니다. 관리자에게 문의해 주세요.");
            return "/common/alert";
        }

        commonMethod.setAttribute(model, "/profile");

        return "/common/noalert";
    }

    @PostMapping("/changepassword")
    public String chengPasswordProc(@Valid MemDTO memDto, Errors errors, HttpServletRequest request, HttpSession session) {

        int memSeq = (int) session.getAttribute("login"); // 세션 정보 불러오기

        /* 현재 비밀번호 일치여부 확인 */
        String currentPw = request.getParameter("currentPw"); // 현재 비밀번호 받기
        if(!memService.getPw(memSeq).equals(currentPw)){ // 현재 비밀번호 불일치
            commonMethod.setAttribute(request, "/profile", "현재 비밀번호가 일치하지 않습니다.");
            return "common/alert";
        }

        /* 새 비밀번호 유효성 검사 */
        if(errors.hasErrors()){ // 유효성 검사 실패
            Map<String, String> validatorResult = memService.validatorHandling(errors); // 에러 내역 불러오기
            String pwError = validatorResult.get("valid_memPw");
            if(pwError != null){
                commonMethod.setAttribute(request, "/profile", pwError); // 비밀번호 에러 내역만 분류 후 Alert
                return "/common/alert";
            }
        }

        /* 비밀번호 변경 */
        memDto.setMemSeq(memSeq); // 수정할 정보를 담은 memDTO 만들기
        if(memService.changePw(memDto) != -1) { // 정보 수정 성공
            commonMethod.setAttribute(request, "/profile", "비밀번호가 변경되었습니다.");
        } else { // 정보 수정 실패
            commonMethod.setAttribute(request, "/profile", "비밀번호 변경에 실패하였습니다. 관리자에게 문의해 주세요.");
        }

        return "/common/alert";
    }

}

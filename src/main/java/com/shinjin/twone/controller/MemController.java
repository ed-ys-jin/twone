package com.shinjin.twone.controller;

import com.shinjin.twone.common.CommonMethod;
import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.service.EmailService;
import com.shinjin.twone.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    EmailService emailService;

    /*** 회원가입 ***/
    @RequestMapping("/signup")
    public String signupView(){
        return "/member/signup";
    }

    @PostMapping("/signup")
    public String signupProc(@Valid MemDTO memDTO, Errors errors, Model model)throws Exception {

        /* 유효성 검사 */
        if(errors.hasErrors()){ // 유효성 검사 실패
            // 회원가입 실패시 입력 데이터 값을 유지
            model.addAttribute("memDTO", memDTO);

            Map<String, String> validatorResult = memService.validatorHandling(errors);
            for(String key : validatorResult.keySet()){
                model.addAttribute(key, validatorResult.get(key));
            }
            // 회원가입 페이지로 리턴
            return "/member/signup";
        }

        /* 중복 이메일 확인 */
        // 기등록된 이메일이 존재하며, 가입중 상태인 경우 (가입중 : 0, 탈퇴 : 1)
        if(memService.checkDupl(memDTO.getMemEmail()) == 0){
            CommonMethod.setAttribute(model, "/signup", "이미 등록된 이메일 계정입니다.");
            return "/common/alert";
        }

        // 비밀번호 대문자 존재 시, 소문자로 변경
        memDTO.setMemPw(memDTO.getMemPw().toLowerCase());

        /* 회원 등록 */
        int memSeq = memService.signup(memDTO);
        // 회원 등록 성공
        if(memSeq != -1) {
            // 임의의 key 생성 & 이메일 발송
            String key = emailService.sendSimpleMessage(memDTO.getMemEmail());
            memDTO.setMemKey(key);
            memDTO.setMemSeq(memSeq);
            memService.updateMemKey(memDTO);
            CommonMethod.setAttribute(model, "/login?email=" + memDTO.getMemEmail(), "등록하신 이메일로 인증요청 메일이 발송되었습니다. 메일 인증 후 로그인을 진행해 주세요.");
        // 회원 등록 실패
        } else {
            CommonMethod.setAttribute(model, "/signup", "회원가입에 실패하였습니다. 관리자에게 문의해 주세요.");
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
    public String loginProc(MemDTO memDTO, HttpServletRequest request, HttpServletResponse response) {

        MemDTO dto = memService.login(memDTO); // DTO 불러오기

        // 비밀번호 대문자 존재 시, 소문자로 변경
        memDTO.setMemPw(memDTO.getMemPw().toLowerCase());

        /* 로그인 가능 여부 확인 */
        // 일치하는 ('가입중' 상태의) 이메일 계정이 없음
        if(dto == null){
            CommonMethod.setAttribute(request, "/login", "존재하지 않는 이메일 계정입니다.");
            return "/common/alert";
        // 일치하는 이메일은 존재하나, 비밀번호 다름
        } else if(!dto.getMemPw().equals(memDTO.getMemPw())) {
            CommonMethod.setAttribute(request, "/login", "비밀번호가 일치하지 않습니다.");
            return "common/alert";
        } else if (dto.getMemCert() == 0 || dto.getMemCert() == 2){
            CommonMethod.setAttribute(request, "/login", "메일 인증이 진행되지 않았습니다.");
            return "common/alert";
        }

        /* 로그인 성공 & 세션 업로드 */
        request.getSession().setAttribute("login", dto.getMemSeq()); // member seq
        request.getSession().setAttribute("username", dto.getMemName()); // member name
        request.getSession().setAttribute("userposition", dto.getMemPosition()); // member position
        request.getSession().setAttribute("userimage", dto.getMemImage()); // member image

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

        return "redirect:/project";
    }


    /*** 로그아웃 ***/
    @RequestMapping("/logout")
    public String logoutProc(HttpSession session) {

        session.invalidate(); // 세션 해제

        return "redirect:/login";
    }


    /*** 회원탈퇴 ***/
    @RequestMapping("withdraw")
    public String withdrawView(){
        return "/member/withdraw";
    }

    @PostMapping("/withdraw")
    public String withdrawProc(MemDTO memDTO, Model model, HttpSession session) {

        int memSeq = (int) session.getAttribute("login"); // 세션 정보 불러오기
        memDTO.setMemSeq(memSeq); // memDTO(memSeq, memPw)

        // 비밀번호 대문자 존재 시, 소문자로 변경
        memDTO.setMemPw(memDTO.getMemPw().toLowerCase());

        // 회원탈퇴 성공
        if(memService.withdraw(memDTO) != 0){
            CommonMethod.setAttribute(model, "/login", "회원탈퇴 처리가 완료되었습니다. 그동안 TWONE 서비스를 이용해 주셔서 감사합니다. 더욱더 노력하고 발전하는 TWONE이 되도록 노력하겠습니다.");
            session.invalidate(); // 세션 해제
        // 회원탈퇴 실패
        } else {
            CommonMethod.setAttribute(model, "/withdraw", "비밀번호 불일치 등의 이유로 회원탈퇴 처리에 실패했습니다. 담당자에게 문의해 주세요.");
        }

        return "/common/alert";
    }

    /*** 프로필 ***/
    @RequestMapping("/profile")
    public String profileView(Model model, HttpSession session) {

        int memSeq = (int) session.getAttribute("login"); // 세션 정보 불러오기

        MemDTO memDTO = memService.getDto(memSeq); // memDTO 불러오기

        model.addAttribute("memDTO", memDTO);

        return "/member/profile";
    }

    @RequestMapping("/editprofile")
    public String editProfileProc(MemDTO memDTO, HttpServletRequest request, HttpSession session) {

        int memSeq = (int) session.getAttribute("login"); // 세션 정보 불러오기

        memDTO.setMemSeq(memSeq); // 수정할 정보를 담은 memDTO 만들기

        int check = memService.updateMemInfo(memDTO);
        /* 회원정보 수정 */
        if(check == -1){ // 정보 수정 실패
            CommonMethod.setAttribute(request, "/profile", "정보 수정에 실패하였습니다. 관리자에게 문의해 주세요.");
            return "/common/alert";
        }
        /* 변경사항 세션에 업로드 */
        request.getSession().setAttribute("username", memDTO.getMemName()); // member name
        request.getSession().setAttribute("userposition", memDTO.getMemPosition()); // member position
        request.getSession().setAttribute("userimage", memDTO.getMemImage()); // member image
        return "redirect:/profile";
    }

    @PostMapping("/changepassword")
    public String chengPasswordProc(@Valid MemDTO memDTO, Errors errors, HttpServletRequest request, HttpSession session) {

        int memSeq = (int) session.getAttribute("login"); // 세션 정보 불러오기

        /* 현재 비밀번호 일치여부 확인 */
        String currentPw = request.getParameter("currentPw"); // 현재 비밀번호 받기
        if(!memService.getPw(memSeq).equals(currentPw)){ // 현재 비밀번호 불일치
            CommonMethod.setAttribute(request, "/profile", "현재 비밀번호가 일치하지 않습니다.");
            return "common/alert";
        }

        /* 새 비밀번호 유효성 검사 */
        if(errors.hasErrors()){ // 유효성 검사 실패
            Map<String, String> validatorResult = memService.validatorHandling(errors); // 에러 내역 불러오기
            String pwError = validatorResult.get("valid_memPw");
            if(pwError != null){
                CommonMethod.setAttribute(request, "/profile", pwError); // 비밀번호 에러 내역만 분류 후 Alert
                return "/common/alert";
            }
        }

        /* 비밀번호 변경 */
        memDTO.setMemSeq(memSeq); // 수정할 정보를 담은 memDTO 만들기
        if(memService.changePw(memDTO) != -1) { // 정보 수정 성공
            CommonMethod.setAttribute(request, "/profile", "비밀번호가 변경되었습니다.");
        } else { // 정보 수정 실패
            CommonMethod.setAttribute(request, "/profile", "비밀번호 변경에 실패하였습니다. 관리자에게 문의해 주세요.");
        }

        return "/common/alert";
    }

    /*메일 링크 인증시 인증 여부 변경*/
    @RequestMapping ("/signUpConfirm")
    public void changeMailCert(@RequestParam Map<String,String> map ){
        memService.changeMailCert(map);
    }

}

package com.shinjin.twone.service;

import com.shinjin.twone.dto.MemDTO;
import org.springframework.validation.Errors;

import java.util.Map;

public interface MemService {
    public Map<String, String> validatorHandling(Errors errors); // 유효성 검사
    public int checkDupl(String email); // 중복 이메일 확인
    public int signup(MemDTO memDTO); // 회원등록
    public MemDTO login(MemDTO memDTO); // 로그인
    public int withdraw(MemDTO memDTO); // 회원탈퇴
    public MemDTO getDto(int memSeq); // memDTO 불러오기
    public int updateMemInfo(MemDTO memDTO); // 회원정보 수정
    public String getPw(int memSeq); // 비밀번호 불러오기
    public int changePw(MemDTO memDTO); // 비밀번호 변경
}
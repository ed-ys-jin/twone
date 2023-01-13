package com.shinjin.twone.service;

import com.shinjin.twone.dto.MemDTO;
import org.springframework.validation.Errors;

import java.util.Map;

public interface MemService {
    public Map<String, String> validatorHandling(Errors errors); // 유효성 검사
    public int signup(MemDTO memDto) throws Exception; // 회원등록
    public int checkDupl(String email) throws Exception; // 중복 이메일 확인
    public MemDTO login(MemDTO memDto) throws Exception; // 로그인
    public int withdraw(MemDTO memDto) throws Exception; // 회원탈퇴
}

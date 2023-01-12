package com.shinjin.twone.service;

import com.shinjin.twone.dto.MemDTO;
import org.springframework.validation.Errors;

import java.util.Map;

public interface MemService {
    public Map<String, String> validatorHandling(Errors errors); // 유효성 검사
    public int signup(MemDTO memDto); // 회원등록
    public int checkWithdraw(String email) throws Exception; // 중복 이메일 확인
}

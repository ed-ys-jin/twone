package com.shinjin.twone.service;

import com.shinjin.twone.dao.MemDAO;
import com.shinjin.twone.dto.MemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemServiceImpl implements MemService{

    @Autowired
    private MemDAO memDao;

    /* 유효성 검사 */
    @Override
    public Map<String, String> validatorHandling(Errors errors){
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    /* 중복 이메일 확인 */
    @Override
    public int checkDupl(String email) {
        return memDao.checkDupl(email);
    }

    /* 회원등록 */
    @Override
    public int signup(MemDTO memDto) {
        return memDao.signup(memDto);
    }

    /* 로그인 */
    @Override
    public MemDTO login(MemDTO memDto) {
        return memDao.login(memDto);
    }

    /* 회원탈퇴 */
    @Override
    public int withdraw(MemDTO memDto) {
        return memDao.withdraw(memDto);
    }

    /* memDTO 불러오기 */
    @Override
    public MemDTO getDto(int memSeq){
        return memDao.getDto(memSeq);
    }

    /* 회원정보 수정 */
    @Override
    public int updateMemInfo(MemDTO memDto) {
        return memDao.updateMemInfo(memDto);
    }

    /* 비밀번호 불러오기 */
    @Override
    public String getPw(int memSeq) {
        return memDao.getPw(memSeq);
    }

    /* 비밀번호 변경 */
    @Override
    public int changePw(MemDTO memDto) {
        return memDao.changePw(memDto);
    }

}

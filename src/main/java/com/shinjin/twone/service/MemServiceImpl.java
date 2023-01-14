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
    public int checkDupl(String email) throws Exception {
        int result = -1;
        try {
            result = memDao.checkDupl(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 회원등록 */
    @Override
    public int signup(MemDTO memDto) throws Exception {
        int result = -1;
        try {
            result = memDao.signup(memDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 로그인 */
    @Override
    public MemDTO login(MemDTO memDto) throws Exception {
        MemDTO dto = null;
        try {
            dto = memDao.login(memDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    /* 회원탈퇴 */
    @Override
    public int withdraw(MemDTO memDto) throws Exception {
        int result = 0;
        try {
            result = memDao.withdraw(memDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* memDTO 불러오기 */
    @Override
    public MemDTO getDto(int memSeq){
        return memDao.getDto(memSeq);
    }

    @Override
    public int updateMemInfo(MemDTO memDto) throws Exception {
        int result = -1;
        try {
            result = memDao.updateMemInfo(memDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getPw(int memSeq) {
        return memDao.getPw(memSeq);
    }

    @Override
    public int changePw(MemDTO memDto) throws Exception {
        int result = -1;
        try {
            result = memDao.changePw(memDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

package com.shinjin.twone.service;

import com.shinjin.twone.dao.MemDAO;
import com.shinjin.twone.dto.MemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemServiceImpl implements MemService {

    @Autowired
    private MemDAO memDAO;

    /* 유효성 검사 */
    @Override
    public Map<String, String> validatorHandling(Errors errors) {
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
    public Integer checkDupl(String email) {
        int result = -1;
        try {
            result = memDAO.checkDupl(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 회원등록 */
    @Override
    public int signup(MemDTO memDTO) {
        int result = -1;
        try {
            memDAO.signup(memDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // selectkey 를 활용하여 인서트 한 mem_seq 바로 가져오기
        result = memDTO.getMemSeq();
        return result;
    }

    /* 로그인 */
    @Override
    public MemDTO login(MemDTO memDTO) {
        MemDTO dto = null;
        try {
            dto = memDAO.login(memDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    /* 회원탈퇴 */
    @Override
    public int withdraw(int memSeq) {
        int result = 0;
        try {
            result = memDAO.withdraw(memSeq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* memDTO 불러오기 */
    @Override
    public MemDTO getDto(int memSeq) {
        return memDAO.getDto(memSeq);
    }

    /* 회원정보 수정 */
    @Override
    public int updateMemInfo(MemDTO memDTO) {
        int result = -1;
        try {
            result = memDAO.updateMemInfo(memDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 비밀번호 불러오기 */
    @Override
    public String getPw(int memSeq) {
        return memDAO.getPw(memSeq);
    }

    /* 비밀번호 변경 */
    @Override
    public int changePw(MemDTO memDTO) {
        int result = -1;
        try {
            result = memDAO.changePw(memDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<MemDTO> getTeamMemberForIssueForm(int projectSeq) {
        return memDAO.getTeamMemberForIssueForm(projectSeq);
    }

    @Override

    public int updateMemKey(MemDTO memDTO) {
       return memDAO.updateMemKey(memDTO);
    }

    /* 사용자 메일인증 키값 저장 for 비밀번호 분실 멤버 */
    @Override
    public int updateMemKeyForLostPwMember(MemDTO memDTO) {
        return memDAO.updateMemKeyForLostPwMember(memDTO);
    }

    @Override
    public int changeMailCert(Map<String, String> map) {
        int result = -1;
        try {
            result = memDAO.changeMailCert(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 이메일 인증 - 비밀번호 재설정 */
    @Override
    public int checkMemKey(Map<String, String> map) {
        int result = -1;
        try {
            result = memDAO.checkMemKey(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int timeOut() {
        return memDAO.timeOut();
    }
    
    @Override
    public int updateMemImage(MemDTO memDTO) {
        return memDAO.updateMemImage(memDTO);
    }

    @Override
    public int deleteMemImage(int memSeq) {
        return memDAO.deleteMemImage(memSeq);
    }

}
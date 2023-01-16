package com.shinjin.twone.dao;

import com.shinjin.twone.dto.MemDTO;

public class MemDAOImpl implements MemDAO {

    /* 중복 이메일 확인 */
    @Override
    public int checkDupl(String email) {
        int result = -1;
        try {
            result = checkDupl(email);
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
            result = signup(memDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 로그인 */
    @Override
    public MemDTO login(MemDTO memDTO) {
        MemDTO dto = null;
        try {
            dto = login(memDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    /* 회원탈퇴 */
    @Override
    public int withdraw(MemDTO memDTO) {
        int result = 0;
        try {
            result = withdraw(memDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* memDTO 불러오기 */
    @Override
    public MemDTO getDto(int memSeq) {
        return getDto(memSeq);
    }

    /* 회원정보 수정 */
    @Override
    public int updateMemInfo(MemDTO memDTO) {
        int result = -1;
        try {
            result = updateMemInfo(memDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 비밀번호 불러오기 */
    @Override
    public String getPw(int memSeq) {
        return getPw(memSeq);
    }

    /* 비밀번호 변경 */
    @Override
    public int changePw(MemDTO memDTO) {
        int result = -1;
        try {
            result = changePw(memDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

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
    public int signup(MemDTO memDto) {
        int result = -1;
        try {
            result = signup(memDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /* 로그인 */
    @Override
    public MemDTO login(MemDTO memDto) {
        MemDTO dto = null;
        try {
            dto = login(memDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    /* 회원탈퇴 */
    @Override
    public int withdraw(MemDTO memDto) {
        int result = 0;
        try {
            result = withdraw(memDto);
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
    public int updateMemInfo(MemDTO memDto) {
        int result = -1;
        try {
            result = updateMemInfo(memDto);
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
    public int changePw(MemDTO memDto) {
        int result = -1;
        try {
            result = changePw(memDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

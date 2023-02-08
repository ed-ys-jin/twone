package com.shinjin.twone.dao;

import com.shinjin.twone.dto.MemDTO;

import java.util.List;
import java.util.Map;

public class MemDAOImpl implements MemDAO {

    /* 중복 이메일 확인 */
    @Override
    public Integer checkDupl(String email) {
        return checkDupl(email);
    }

    /* 회원등록 */
    @Override
    public int signup(MemDTO memDTO) {
        return signup(memDTO);
    }

    /* 로그인 */
    @Override
    public MemDTO login(MemDTO memDTO) {
        return login(memDTO);
    }

    /* 회원탈퇴 */
    @Override
    public int withdraw(MemDTO memDTO) {
        return withdraw(memDTO);
    }

    /* memDTO 불러오기 */
    @Override
    public MemDTO getDto(int memSeq) {
        return getDto(memSeq);
    }

    /* 회원정보 수정 */
    @Override
    public int updateMemInfo(MemDTO memDTO) {
        return updateMemInfo(memDTO);
    }

    /* 비밀번호 불러오기 */
    @Override
    public String getPw(int memSeq) {
        return getPw(memSeq);
    }

    /* 비밀번호 변경 */
    @Override
    public int changePw(MemDTO memDTO) {
        return changePw(memDTO);
    }

    /* 팀 멤버 불러오기 for 이슈폼 */
    @Override
    public List<MemDTO> getTeamMemberForIssueForm(int projectSeq) {
        return getTeamMemberForIssueForm(projectSeq);
    }

    
    @Override
    public int updateMemKey(MemDTO memDTO) {
        return updateMemKey(memDTO);
    }

    @Override
    public void changeMailCert(Map<String, String> map) {
        changeMailCert(map);
    }

    @Override
    public int timeOut() {
        return timeOut();
    }
    
    @Override
    public int updateMemImage(MemDTO memDTO) {
        return updateMemImage(memDTO);
    }

    @Override
    public int deleteMemImage(int memSeq) {
        return deleteMemImage(memSeq);
    }
}
package com.shinjin.twone.dao;

import com.shinjin.twone.dto.MemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemDAO {
    public int checkDupl(String email); // 중복 이메일 확인
    public int signup(MemDTO memDTO); // 회원 등록
    public MemDTO login(MemDTO memDTO); // 로그인
    public int withdraw(MemDTO memDTO); // 회원탈퇴
    public MemDTO getDto(int memSeq); // memDTO 불러오기
    public int updateMemInfo(MemDTO memDTO); // 회원정보 수정
    public String getPw(int memSeq); // 비밀번호 불러오기
    public int changePw(MemDTO memDTO); // 비밀번호 변경
    public List<MemDTO> getTeamMemberForIssueForm(int projectSeq); // 팀 멤버 불러오기 for 이슈폼

    public void updateMemKey(MemDTO memDTO); // 사용자 메일 키값 저장

    public void changeMailCert(Map<String, String> map); // 메일인증 여부 변경
}

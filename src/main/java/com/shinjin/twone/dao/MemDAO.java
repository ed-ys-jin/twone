package com.shinjin.twone.dao;

import com.shinjin.twone.dto.MemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
    public int updateMemImage(MemDTO memDTO); // 이미지 업로드
    public int deleteMemImage(int memSeq); // 기본 이미지로 변경
}

package com.shinjin.twone.service;

import com.shinjin.twone.dto.MemDTO;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

public interface MemService {
    public Map<String, String> validatorHandling(Errors errors); // 유효성 검사
    public Integer checkDupl(String email); // 중복 이메일 확인
    public int signup(MemDTO memDTO); // 회원등록
    public MemDTO login(MemDTO memDTO); // 로그인
    public int withdraw(int memSeq); // 회원탈퇴
    public MemDTO getDto(int memSeq); // memDTO 불러오기
    public int updateMemInfo(MemDTO memDTO); // 회원정보 수정
    public String getPw(int memSeq); // 비밀번호 불러오기
    public int changePw(MemDTO memDTO); // 비밀번호 변경
    public List<MemDTO> getTeamMemberForIssueForm(int projectSeq); // 팀 멤버 불러오기 for 이슈폼
    public int updateMemKey(MemDTO memDTO); // 키값 업로드
    public void changeMailCert(Map<String, String> map); // 이메일 인증여부 변경
    public int timeOut(); // 발급일 하루 지난 사용자의 발급일, 발급일자, 이메일 인증 변경
    public int updateMemImage(MemDTO memDTO); // 이미지 업로드
    public int deleteMemImage(int memSeq); // 기본 이미지로 변경

}

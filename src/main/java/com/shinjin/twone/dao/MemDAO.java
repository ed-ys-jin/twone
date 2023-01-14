package com.shinjin.twone.dao;

import com.shinjin.twone.dto.MemDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemDAO {
    public int checkDupl(String email); // 중복 이메일 확인
    public int signup(MemDTO memDto); // 회원 등록
    public MemDTO login(MemDTO memDto); // 로그인
    public int withdraw(MemDTO memDto); // 회원탈퇴
    public MemDTO getDto(int memSeq); // memDTO 불러오기
    public int updateMemInfo(MemDTO memDto); // 회원정보 수정
    public String getPw(int memSeq); // 비밀번호 불러오기
    public int changePw(MemDTO memDto); // 비밀번호 변경
}

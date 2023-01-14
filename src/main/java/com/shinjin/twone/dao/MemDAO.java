package com.shinjin.twone.dao;

import com.shinjin.twone.dto.MemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemDAO {
    public int signup(MemDTO memDto) throws Exception; // 회원 등록
    public int checkDupl(String email) throws Exception; // 중복 이메일 확인
    public MemDTO login(MemDTO memDto) throws Exception; // 로그인
    public int withdraw(MemDTO memDto) throws Exception; // 회원탈퇴
    public MemDTO getDto(int memSeq); // memDTO 불러오기
    public int updateMemInfo(MemDTO memDto) throws Exception; // 회원정보 수정
    public String getPw(int memSeq); // 비밀번호 불러오기
    public int changePw(MemDTO memDto) throws Exception; // 비밀번호 변경
}

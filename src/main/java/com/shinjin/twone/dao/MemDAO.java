package com.shinjin.twone.dao;

import com.shinjin.twone.dto.MemDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemDAO {
    public int signup(MemDTO memDto); // 회원 등록
    public int checkWithdraw(String email) throws Exception; // 중복 이메일 확인
}

package com.shinjin.twone.dto;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class MemDTO {

    private int memSeq;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"
            , message = "이메일 형식에 맞지 않습니다.")
    private String memEmail;

    private int memCert;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}"
            , message = "비밀번호는 8~16자 영문 소문자, 숫자, 특수문자를 사용하세요.")
    private String memPw;

    private Date memPwexpiry;

    private int memDelcheck;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String memName;

    private String memImage;

    private String memPosition;

    private String memDept;

    private String memCompany;


    /*팀 권한*/
    private int teamAllow;

    /*프로필 이미지*/
    private MultipartFile memPic;

    private String memKey;

}
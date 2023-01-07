package com.shinjin.twone.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MemDTO {
    private int memSeq;
    private String memEmail;
    private int memCert;
    private String memPw;
    private Date memPwexpiry;
    private int memDelcheck;
    private String memName;
    private String memNick;
    private String memImage;
    private String memPosition;
    private String memDept;
    private String memCompany;
}

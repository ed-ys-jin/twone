package com.shinjin.twone.dto;

import lombok.Data;

import java.util.Date;

@Data
public class IssueDTO {
    private int issueSeq;
    private int projectSeq;
    private int boardSeq;
    private int colSeq;
    private int memSeq;
    private String issueCode;
    private String issueTitle;
    private String issueLabel;
    private Date issueRegdate;
    private Date issueUpdate;
    private String issueSummary;
}

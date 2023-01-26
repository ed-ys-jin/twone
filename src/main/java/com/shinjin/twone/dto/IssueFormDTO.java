package com.shinjin.twone.dto;

import lombok.Data;

@Data
public class IssueFormDTO {
    private int issueFormSeq;
    private int issueSeq;
    private String formsSeq;
    private int issueFormOrder;

    private String formsTableName; // t_forms 테이블명
    private String formsColName; // t_forms 컬럼명
}

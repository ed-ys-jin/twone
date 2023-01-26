package com.shinjin.twone.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FormsDatDTO {
    private String datSeq;
    private int issueFormSeq;
    private String datTitle;
    private Date datValue;
}

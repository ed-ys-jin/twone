package com.shinjin.twone.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ColDTO {

    private int colSeq;

    private int projectSeq;

    private int boardSeq;

    @NotBlank(message = "컬럼명은 필수 입력 값입니다.")
    private String colName;

    private int colType;
}

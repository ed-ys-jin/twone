package com.shinjin.twone.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BoardDTO {

    private int boardSeq;

    private int projectSeq;

    @NotBlank(message = "보드명은 필수 입력 값입니다.")
    private String boardName;
}
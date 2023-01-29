package com.shinjin.twone.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {

  private int commentSeq;
  private int issueSeq;
  private int memSeq;
  private Date commentDate;
  private String commentValue;

  private String memName;
  private String memImage;

}

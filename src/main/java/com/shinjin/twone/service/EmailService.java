package com.shinjin.twone.service;

public interface EmailService {
  public String sendSimpleMessage(String to)throws Exception;
  public String sendEmailToLostPwMember(String to) throws Exception;
}

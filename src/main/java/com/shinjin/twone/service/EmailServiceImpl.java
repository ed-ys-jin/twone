package com.shinjin.twone.service;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

  @Autowired
  JavaMailSender emailSender;

  public String key = "";

  private MimeMessage createMessage(String to)throws Exception{
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String formatedNow = LocalDate.now().plusDays(1) + "";
    formatedNow += " " + LocalTime.now().format(formatter);

    key = createKey();

    MimeMessage  message = emailSender.createMimeMessage();


    message.addRecipients(RecipientType.TO, to);//보내는 대상
    message.setSubject("TWONE 이메일 인증");//제목

    String msgg="";
    msgg+= "<div style='margin:20px;'>";
    msgg+= "<h1> 안녕하세요 TWONE 입니다. </h1>";
    msgg+= "<br>";
    msgg+= "<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.<p>";
    msgg+= "<br>";
    msgg+= "<p>인증링크는 24시간동안 유효합니다. (인증 만료 일시 : "+formatedNow +")<p>";
    msgg+= "<br>";
    msgg+= "<p>감사합니다.<p>";
    msgg+= "<br>";
//    msgg+= "<a href='http://localhost:8080/signUpConfirm?email=" + to;
    msgg+= "<a href='http://www.twoneproject.com/signUpConfirm?email=" + to;
    msgg+= "&key=" + key;
    msgg+= "'target='blank'>이메일 인증 하러가기</a>";
    message.setText(msgg, "utf-8", "html");//내용
    message.setFrom(new InternetAddress("twone.shinjinbong@gmail.com","twone"));//보내는 사람

    return message;
  }

  // 인증코드 생성
  public static String createKey() {
    StringBuffer key = new StringBuffer();
    Random rnd = new Random();

    for (int i = 0; i < 8; i++) { // 인증코드 8자리
      int index = rnd.nextInt(3); // 0~2 까지 랜덤

      switch (index) {
        case 0:
          key.append((char) ((int) (rnd.nextInt(26)) + 97));
          //  a~z  (ex. 1+97=98 => (char)98 = 'b')
          break;
        case 1:
          key.append((char) ((int) (rnd.nextInt(26)) + 65));
          //  A~Z
          break;
        case 2:
          key.append((rnd.nextInt(10)));
          // 0~9
          break;
      }
    }
    return key.toString();
  }

  // 이메일 전송
  @Override
  public String sendSimpleMessage(String to)throws Exception {
    // TODO Auto-generated method stub
    MimeMessage message = createMessage(to);
    try{//예외처리
      emailSender.send(message);
    }catch(MailException es){
      es.printStackTrace();
      throw new IllegalArgumentException();
    }
    return key;
  }
}

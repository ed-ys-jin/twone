package com.shinjin.twone.scheduler;

import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/*2단계
*   스케줄러를 이용하기 위해서는 스프링 빈으로 등록해야 합니다.
* */
@Component
public class ScheduledService {

  @Autowired
  private MemService memService;
  

  // 출력형식 지정
  private static final DateTimeFormatter formatter
      = DateTimeFormatter.ofPattern("yy-MM-dd hh:mm:ss");

  @Scheduled (fixedDelay = 3600000) //이전 작업이 완료된 이후부터 시간 측정/ 1시간 단위
  public void fixedDelay() throws InterruptedException{
    // 발급일 하루 지난 사용자의 발급일, 발급일자, 이메일 인증 변경
     int delcheck =  memService.timeOut();

  }



}

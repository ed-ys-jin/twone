package com.shinjin.twone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//* 스케줄러 특징
//* 메서드의 리턴값은 void여야 한다. (그러나 리턴값이 있더라도 동작합니다.)
//* 메서드의 인자는 없어야 한다. (인자가 있으면 컴파일시 BeanCreationException 발생)
//* */
//
///*1단계 - 스케줄러 활성화
//*   스프링 스케줄러를 사용하기 위해서는 Spring Boot 실행파일에 @EnableScheduling를 선언해야 사용할 수 있습니다.
//* */

@SpringBootApplication
@EnableScheduling
public class TwoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwoneApplication.class, args);
    }

}

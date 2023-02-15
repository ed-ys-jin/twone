package com.shinjin.twone.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {

  // AWS 계정의 Access Key를 가져오는데 사용될 properties 값
  @Value("${cloud.aws.credentials.access-key}")
  private String accessKey;

  // AWS 계정의 Secret Key를 가져오는데 사용될 properties 값
  @Value("${cloud.aws.credentials.secret-key}")
  private String secretKey;

  // AWS S3 Bucket이 저장되는 지역(region)을 가져오는데 사용될 properties 값
  @Value("${cloud.aws.region.static}")
  private String region;

  @Bean
  public AmazonS3 amazonS3() {
    // AWS 계정의 인증 정보를 가진 credentials 객체 생성
    AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
    // AmazonS3 Client Builder 객체 생성
    AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
            // AWS 계정의 인증 정보를 client builder 객체에 설정
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            // S3 Bucket이 저장되는 region을 client builder 객체에 설정
            .withRegion(region)
            // AmazonS3 객체 생성 및 반환
            .build();
    return amazonS3;
  }
}

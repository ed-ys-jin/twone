package com.shinjin.twone.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

  private final AmazonS3 s3Client;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  public S3Service(AmazonS3 s3Client) {
    this.s3Client = s3Client;
  }

  // MultipartFile 타입의 이미지 파일을 업로드하고, 해당 이미지의 URL을 반환하는 메소드
  public String uploadImage(MultipartFile file) throws IOException {

    // UUID로 랜덤한 파일 이름 생성
    String fileName = UUID.randomUUID().toString();
    // S3에 업로드되는 파일 URL
    String fileUrl = "https://" + bucket + ".s3.amazonaws.com/" + fileName;

    ObjectMetadata objectMetadata = new ObjectMetadata();
    // 업로드한 파일의 MIME 타입을 설정
    objectMetadata.setContentType(file.getContentType());
    // 업로드한 파일의 크기를 설정
    objectMetadata.setContentLength(file.getSize());

    // PutObjectRequest 객체 생성 및 설정
    PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, file.getInputStream(), objectMetadata)
            // 접근 권한을 public으로 설정
            .withCannedAcl(CannedAccessControlList.PublicRead);

    // S3에 파일 업로드
    s3Client.putObject(putObjectRequest);
    // S3에 업로드된 이미지의 URL 반환
    return fileUrl;
  }

  // Amazon S3 버킷에서 파일을 삭제
  // fileUrl : 삭제할 파일의 S3 버킷 URL
  public void deleteImage(String fileUrl) {
    // fileUrl에서 파일 이름을 추출
    String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
    // 삭제할 파일의 위치를 나타내는 DeleteObjectRequest 객체를 생성하고, s3Client의 deleteObject() 메소드에 전달하여 파일 삭제
    s3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
  }

}

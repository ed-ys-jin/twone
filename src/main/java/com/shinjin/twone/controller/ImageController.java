package com.shinjin.twone.controller;

import com.shinjin.twone.common.CommonMethod;
import com.shinjin.twone.dto.MemDTO;
import com.shinjin.twone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
public class ImageController {

  @Autowired
  private MemService memService;
  @Autowired
  private ServletContext application;
  @Autowired
  private S3Service s3Service;


  // 사진 업로드 (본인 피씨(로컬) 저장 경로)
  // private final String savePath = "C:\\Users\\A\\IdeaProjects\\twone\\src\\main\\webapp\\resources\\upload\\"; //성주 - 윈도우
  // private final String savePath = "/Users/sj/Desktop/twone/src/main/webapp/resources/upload"; //성주 - 맥
//  private final String savePath = "/Users/jin/DevRoot/Workspace/Github/Twone/src/main/webapp/resources/upload/"; //윤석

  // 리눅스 서버 경로
//  private final String savePath = "/var/lib/tomcat9/file_repo/";
//  private final String imgPath = "resources/upload/";

  @RequestMapping("/image/profileImg")
  public String changeMemImage(MemDTO memDTO, HttpServletRequest request, HttpSession session) throws IOException {

    int memSeq = (int) session.getAttribute("login"); // 세션 정보 불러오기
    memDTO.setMemSeq(memSeq); // 수정할 정보를 담은 memDTO 만들기

    MultipartFile memPic = memDTO.getMemPic();
    String memImage = null;

//    아래 코드는 로컬 또는 스프링 프로젝트 환경에서 파일 업로드 시 사용했으나,
//    스프링부트 프로젝트 환경에서 EC2 서버 내 경로 설정 오류 원인을 찾을 수 없어서,
//    AWS S3 버킷에 이미지 파일을 저장하는 방식으로 변경함. 23/02/16 윤석
//
//    if(!memPic.isEmpty() || memPic==null) {
//      memImage = memPic.getOriginalFilename();
//      memImage = memSeq + "memberImage" + memImage.substring(memImage.lastIndexOf("."),memImage.length());
//      File saveFile = new File(savePath, memImage);
//
//      //업로드된 파일 저장할 때 코드
//      if(!saveFile.exists()) {//경로에 파일이 없다면
//        saveFile.mkdir();
//      }else {
//        long time = System.currentTimeMillis();
//        memImage = String.format("%s%d%s", memImage.substring(0,memImage.lastIndexOf(".")),time,memImage.substring(memImage.lastIndexOf(".")));
//        saveFile = new File(savePath,memImage);
//      }
//
//      try {
//        memPic.transferTo(saveFile);
//      } catch (IllegalStateException e) {
//        e.printStackTrace();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//      memImage = imgPath+memImage;
//      memDTO.setMemImage(memImage);
//    }

    // AWS S3 버킷에 이미지 업로드 후 URL 받기
    memImage = s3Service.uploadImage(memPic);

    // AWS S3 버킷 내 기존 memImage 파일 삭제
    MemDTO prevMemDTO = memService.getDto(memSeq);
    s3Service.deleteImage(prevMemDTO.getMemImage());

    // 이미지 URL memImage에 저장
    memDTO.setMemImage(memImage);

    // 회원정보 수정
    if(memService.updateMemImage(memDTO) == -1){ // 정보 수정 실패
      CommonMethod.setAttribute(request, "/profile", "정보 수정에 실패하였습니다. 관리자에게 문의해 주세요.");
      return "/common/alert";
    }

    // 수정된 프로필 이미지 세션에 업로드
    request.getSession().setAttribute("userimage", memDTO.getMemImage());

    return "redirect:/profile";
  }

  @RequestMapping("/image/deleteImg")
  public String deleteMemImage(HttpServletRequest request, HttpSession session){

    // 세션 정보 불러오기
    int memSeq = (int) session.getAttribute("login");
    // memDTO 불러오기
    MemDTO memDTO = memService.getDto(memSeq);

    // AWS S3 버킷 내 이미지 파일 삭제
    s3Service.deleteImage(memDTO.getMemImage());

    // memImage 값 null로 변경
    if(memService.deleteMemImage(memSeq) == -1){ // 정보 수정 실패
      CommonMethod.setAttribute(request, "/profile", "정보 수정에 실패하였습니다. 관리자에게 문의해 주세요.");
      return "/common/alert";
    }

    // 프로필 이미지 세션에서 삭제
    session.removeAttribute("userimage");

    return "redirect:/profile";

  }

}
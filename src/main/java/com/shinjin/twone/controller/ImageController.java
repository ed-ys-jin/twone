package com.shinjin.twone.controller;

import com.shinjin.twone.common.commonMethod;
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
  @Autowired // 자동 주입
  private ServletContext application;

  // 사진 업로드 (본인 피씨(로컬) 저장 경로)
  // private final String savePath = "C:\\Users\\A\\IdeaProjects\\twone\\src\\main\\webapp\\resources\\upload\\"; //성주 - 1

//  private final String savePath = "/Users/sj/Desktop/twone/src/main/webapp/resources/upload";

  // private final String savePath = "/Users/jin/DevRoot/Workspace/Github/Twone/src/main/webapp/resources/upload/"; //윤석

  // 리눅스 서버 경로
  private final String savePath = "/var/lib/tomcat9/file_repo/";

//  private final String imgPath = "resources/upload/";
  private final String imgPath = "/var/lib/tomcat9/file_repo/";

  @RequestMapping("/image/profileImg")
  public String changeMemImage(MemDTO memDTO, HttpServletRequest request, HttpSession session){

    int memSeq = (int) session.getAttribute("login"); // 세션 정보 불러오기
    memDTO.setMemSeq(memSeq); // 수정할 정보를 담은 memDTO 만들기

    MultipartFile memPic = memDTO.getMemPic();
    String memImage = null;

    if(!memPic.isEmpty() || memPic==null) {
      memImage = memPic.getOriginalFilename();
      memImage = memSeq + "memberImage" + memImage.substring(memImage.lastIndexOf("."),memImage.length());
      File saveFile = new File(savePath, memImage);

      //업로드된 파일 저장할 때 코드
      if(!saveFile.exists()) {//경로에 파일이 없다면
        saveFile.mkdir();
      }else {
        long time = System.currentTimeMillis();
        memImage = String.format("%s%d%s", memImage.substring(0,memImage.lastIndexOf(".")),time,memImage.substring(memImage.lastIndexOf(".")));
        saveFile = new File(savePath,memImage);
      }

      try {
        memPic.transferTo(saveFile);
      } catch (IllegalStateException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      memImage = imgPath+memImage;
      memDTO.setMemImage(memImage);
    }
    int check = memService.updateMemImage(memDTO);
    /* 회원정보 수정 */
    if(check == -1){ // 정보 수정 실패
      commonMethod.setAttribute(request, "/profile", "정보 수정에 실패하였습니다. 관리자에게 문의해 주세요.");
      return "/common/alert";
    }
    return "redirect:/profile";
  }

  @RequestMapping("/image/deleteImg")
  public String deleteMemImage(HttpServletRequest request, HttpSession session){

    int memSeq = (int) session.getAttribute("login"); // 세션 정보 불러오기

    int check = memService.deleteMemImage(memSeq);

    if(check == -1){ // 정보 수정 실패
      commonMethod.setAttribute(request, "/profile", "정보 수정에 실패하였습니다. 관리자에게 문의해 주세요.");
      return "/common/alert";
    }
    return "redirect:/profile";

  }

}
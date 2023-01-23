package com.shinjin.twone.controller;

import com.shinjin.twone.dto.*;
import com.shinjin.twone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ImageController {

  @Autowired
  private MemService memService;

  // 서버용
  // private String savePath = "/var/lib/tomcat9/file_repo/이미지명"

  // local 개발용
  private String savePath = "${contextPath }/resources/upload/";



}
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Twone Project</title>
  <!-- Favicons -->
  <link href="../resources/bootstrap/img/logo_sjb_withback.png" rel="icon">
  <link href="../resources/bootstrap/img/apple-touch-icon.png" rel="apple-touch-icon">
  <!-- Icon -->
  <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
</head>
<body>

  <div class="wrap">
    <h1>
      <a href="/project" rel="click"><img src="../resources/bootstrap/img/logo_sjb_withback.png" width="250px"></a>
    </h1><br>
    <p id="dynamic" class="lg-text"></p><br>
    <p class="sm-text">TWONE Project &nbsp; | &nbsp; Team SJB</p>
    <br><br>
    <p>본 서비스는 크롬(PC) 환경에 최적화되어 있습니다.</p>
  </div>

</body>
</html>

<style>
    *{
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    body{
        /*background-color: darkslateblue;*/
        background-color: #99D8FF;
    }

    .wrap{
        /* 화면의 정중앙에 배치 */
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);

        color: white;
        text-shadow: 2px 2px 4px steelblue;
        text-align: center;     /* 아이콘도 영향을 받아 중앙정렬 */
    }

    /* 아이콘 CSS */
    .material-symbols-outlined{
        font-size: 10rem;
    }

    .lg-text{
        font-size: 2rem;
        font-weight: bold;
        margin-bottom: 5px;
    }

    .sm-text{
        font-size: 1.5rem;
    }

    #dynamic{
        position: relative;
        display: inline-block;  /* inline 속성을 부여하여 커서가 텍스트에 붙도록 한다 */
    }

    /* 텍스트 오른쪽에 커서처럼 보이도록 함 */
    /* after 가상요소 부여 */
    #dynamic::after{
        content: "";
        display: block;

        /* 오른쪽에 배치 */
        position: absolute;
        top: 0;
        right: -10px;   /* dynamic 과 10px 떨어뜨리기 */

        width: 4px;
        height: 100%;
        background-color: white;
    }

    /* 만약에 active 속성을 가질 경우 적용되는 CSS */
    /* 커서 깜박임 효과 JS code와 연결 */
    #dynamic.active::after{
        display: none;
    }
</style>

<script>
  // dynamic 이라는 속성값을 가지고 있는 문서객체를 선택
  let target = document.querySelector("#dynamic");

  // 랜덤 문자열 생성
  function randomString(){
    // 랜덤 문자열 생성
    let stringArr = ["Java JDK 1.8", "Spring Framework 5.0.2", "Spring Boot 2.7.7", "Jsp/Servlet 3.1", "Mybatis 3.5.3", "MySQL", "IntelliJ", "AWS", "Git/GitHub"];
    // stringArr 배열에 있는 문자열 중 랜덤하게 하나를 선택
    let selectString = stringArr[Math.floor(Math.random() * stringArr.length)];
    // 랜덤하게 받은 문자열 1개의 문자를 개별 분리
    let selectStringArr = selectString.split("");

    return selectStringArr;   // 최종값 반환
  }

  // 타이핑 reset
  function resetTyping(){
    target.textContent = "";
    dynamic(randomString());
  }

  // 한글자씩 텍스트 출력 함수
  function dynamic(randomArr){
    if(randomArr.length > 0){   // randomArr 안에 문자가 전부 빠질때까지 수행
      target.textContent += randomArr.shift();   // shift 메소드로 앞문자부터 빼내어 출력

      setTimeout(function(){   // randomArr 이 0 이 될때까지 dynamic 함수를 재호출 (재귀함수)
        dynamic(randomArr);
      }, 80);
    } else {    // 더이상 출력할 문자가 없을 경우 reset
      setTimeout(resetTyping, 3000);
    }
  }

  // dynamic 함수 호출할때 randomString 함수를 호출해서 랜덤 문자열 생성
  dynamic(randomString());

  // 커서 깜박임 효과
  function blink(){
    target.classList.toggle("active");   // active class 가 추가/삭제 반복
  }

  // blink 라는 함수를 0.5초마다 실행
  setInterval(blink, 500);
</script>
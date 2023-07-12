<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="projectsidebar.jsp"%>

<main id="main" class="main">
  <h5 class="card-title"></h5>

  <!-- Page Title -->
  <div class="pagetitle">
    <h1>
      <c:choose>
        <c:when test="${teamAllow == 3}">
          <input id="board-update-box" type="text" value="${bdto.boardName}"
                 style="border: none; background-color: #f6f9ff; font-family: 'Nunito', sans-serif;
                 font-size: 24px; font-weight: 600; color: #012970" readonly>
        </c:when>
        <c:otherwise>
          <input id="board-update-box" type="text" value="${bdto.boardName}" onkeyup="updateBoardName(this)"
                 style="border: none; background-color: #f6f9ff; font-family: 'Nunito', sans-serif;
                 font-size: 24px; font-weight: 600; color: #012970">
        </c:otherwise>
      </c:choose>
    </h1>

    <nav style="--bs-breadcrumb-divider: '>';">
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/project">프로젝트</a></li>
        <li class="breadcrumb-item active">보드</li>
      </ol>
    </nav>
  </div><!-- End Page Title -->

  <!-- 컬럼/이슈 리스트 -->
  <section class="section dashboard">
    <div class="row" id="column-list-card">
      ${clist}
    </div>
  </section>

</main><!-- End #main -->

<%@ include file="../layouts/footer.jsp"%>

<script>

  /* 보드명 변경 */
  function updateBoardName(input){

    const inputBox = document.getElementById("board-update-box");
    let boardName = input.value;

    // 입력 글자수 제어
    if(boardName.length > 30) {
      alert("최대 30자까지만 작성할 수 있습니다.");
      inputBox.value = boardName.substring(0, 28); // 문자열 29자로 자르기
      return;
    }

    // focus 해제 시 실행
    inputBox.onblur = function (e) {
      // 입력값이 공백인 경우 Alert
      if(boardName.trim().length == 0){
        alert("보드명을 최소 1글자 이상 입력해 주세요.");
        return;
      }

      // URL(+ 파라미터) 만들기
      let url = "/project/updateboardname?projectSeq=" + ${bdto.projectSeq}
          + "&boardSeq=" + ${bdto.boardSeq}
          + "&boardName=" + encodeURIComponent(boardName);

      // 연결 작업
      const xhttp = new XMLHttpRequest();
      xhttp.open("GET", url, true);

      // 콜백 작업 지정
      xhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
          // boardSideBar의 보드 리스트 업데이트
          document.getElementById("components-nav").innerHTML = this.responseText;
          // inputBox focus 해제
          inputBox.blur();
        }
      };
      // 결과값 받음
      xhttp.send();
    }
  }

  /* 컬럼 생성 */
  function addColumn(input) {

    const inputBox = document.getElementById("col-input-box");
    let colName = input.value;

    // 입력 글자수 제어
    if(colName.length > 30) {
      alert("최대 30자까지만 작성할 수 있습니다.");
      inputBox.value = colName.substring(0, 28); // 문자열 29자로 자르기
      return;
    }

    // 엔터키 입력 시 IF문 실행
    if (window.event.keyCode == 13) {
      // 공백이 입력된 경우
      if(colName.trim() == ""){
        alert("컬럼명을 최소 1글자 이상 입력해 주세요.");
        return;
      }

      // URL 만들기
      let url = "/project/addcolumn?boardSeq=" + ${bdto.boardSeq}
              + "&colName=" + encodeURIComponent(colName);

      // 연결 작업
      const xhttp = new XMLHttpRequest();
      xhttp.open("GET", url, true);

      // 콜백 작업 지정
      xhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200) {
          document.getElementById("column-list-card").innerHTML = this.responseText;
        }
      };

      // input 클리어 & 숨기기
      inputBox.value = null;
      inputBox.style.display = "none";

      // 결과값 받음
      xhttp.send();
    }
  }

  /* 컬럼명 변경 */
  function updateColName(input, colSeq){
    const inputBox = document.getElementById("col-update-box");
    let colName = input.value;

    // 입력 글자수 제어
    if(colName.length > 30) {
      alert("최대 30자까지만 작성할 수 있습니다.");
      inputBox.value = colName.substring(0, 28); // 문자열 29자로 자르기
      return;
    }

    // focus 해제 시 실행
    inputBox.onblur = function (e) {
      // 입력값이 공백인 경우
      if(colName.trim() == ""){
        alert("컬럼명을 최소 1글자 이상 입력해 주세요.");
        return;
      }

      // URL(+ 파라미터) 만들기
      let url = "/project/updatecolname?colSeq=" + colSeq
          + "&colName=" + encodeURIComponent(colName);

      // 연결 작업
      const xhttp = new XMLHttpRequest();
      xhttp.open("GET", url, true);

      // 콜백 작업 지정
      xhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
          document.getElementById("column-list-card").innerHTML = this.responseText; // 컬럼 리스트 업데이트
          inputBox.blur(); // inputBox focus 해제
        }
      };
      // 결과값 받음
      xhttp.send();
    }
  }

  /* 컬럼 삭제 */
  function deleteColumn(colSeq) {

    if(!confirm("컬럼을 삭제하시겠습니까?")){
      return;
    }

    // URL(+ 파라미터) 만들기
    let url = "/project/deletecolumn?colSeq=" + colSeq;

    // 연결 작업
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);

    // 태그 삭제
    let colId = "col-" + colSeq;
    document.getElementById(colId).remove();

    // 결과값 받음
    xhttp.send();
  }

  /* 이슈 생성 */
  function addIssue(colSeq){
    // URL 만들기
    let url = "/project/addissue?colSeq=" + colSeq;

    // 연결 작업
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);

    // 콜백 작업 지정
    xhttp.onreadystatechange = function (){
      if(this.readyState == 4 && this.status == 200) {
        document.getElementById("column-list-card").innerHTML = this.responseText;
      }
    };

    // 결과값 받음
    xhttp.send();
  }

  /* 이슈 삭제 */
  function deleteIssue(issueSeq){

    if(!confirm("이슈를 삭제하시겠습니까?")){
      return;
    }

    // URL 만들기
    let url = "/project/deleteissue?issueSeq=" + issueSeq;

    // 연결 작업
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);

    // 태그 삭제
    let issueId = "issue-" + issueSeq;
    document.getElementById(issueId).remove();

    // 결과값 받음
    xhttp.send();
  }

  /* 이슈 완료 처리 */
  function moveToDone(issueSeq){
    // URL(+ 파라미터) 만들기
    let url = "/project/movetodone?issueSeq=" + issueSeq;

    // 연결 작업
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);

    // 콜백 작업 지정
    xhttp.onreadystatechange = function (){
      if(this.readyState == 4 && this.status == 200){
        document.getElementById("column-list-card").innerHTML = this.responseText; // 컬럼 리스트 업데이트
      }
    };
    // 결과값 받음
    xhttp.send();
  }
  정
</script>
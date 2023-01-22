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
      <input id="board-update-box" type="text" value="${bdto.boardName}" onkeyup="updateBoardName(this)"
             style="border: none; background-color: #f6f9ff; font-family: 'Nunito', sans-serif;
             font-size: 24px; font-weight: 600; color: #012970">
    </h1>

    <nav>
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/project">프로젝트</a></li>
        <li class="breadcrumb-item active">보드</li>
      </ol>
    </nav>
  </div><!-- End Page Title -->

  <section class="section dashboard">
    <div class="row" id="column-list-card">

      <!-- Column -->
      <c:forEach var="cdto" items="${clist}">
        <div id="col-${cdto.colSeq}" class="col-lg-2 col-md-6" style="min-width: 300px">
          <div class="card info-card sales-card">

            <!-- Three Dots Dropdown Menu Icon -->
            <div class="filter">
              <c:choose>
                <c:when test="${cdto.colType == 1}">
                  <a class="icon" href="#" data-bs-toggle="dropdown"><img src="../resources/bootstrap/img/checkmark.png" width="20"></a>
                </c:when>
                <c:otherwise>
                    <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                      <li><a class="dropdown-item" href="javascript:addIssue(${cdto.colSeq})">이슈 추가</a></li>
                      <li><a class="dropdown-item" href="javascript:deleteColumn(${cdto.colSeq})">컬럼 삭제</a></li>
                    </ul>
                </c:otherwise>
              </c:choose>
            </div><!-- End Three Dots Dropdown Menu Icon -->

            <!-- Column Title -->
            <div class="card-body">
              <h5 class="card-title">
                  <input id="col-update-box" type="text" value="${cdto.colName}" onkeyup="updateColName(this, ${cdto.colSeq})"
                         style="border: none; font-family: 'Nunito', sans-serif;
                         font-size: 18px; font-weight: 500; color: #012970">
              </h5>
            </div>

            <!-- Issue Card -->
            <div id="issue-list-card">
              <c:choose>
                <c:when test="${imap.containsKey(cdto.colSeq)}">

                  <c:forEach var="idto" items="${imap.get(cdto.colSeq)}">
                    <div id="issue-${idto.issueSeq}" class="card-body" style="font-size: 14px">

                      <div class="alert alert-secondary fade show" role="alert">
                        <a href="${twone}/project/issue" style="color: black">
                          <p>${idto.issueTitle}</p>
                        </a>
                        <!-- Three Dots Dropdown Menu Icon -->
                        <div class="filter">
                          <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
                          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                            <li><a class="dropdown-item" href="javascript:deleteIssue(${idto.issueSeq})">이슈 삭제</a></li>
                          </ul>
                        </div><!-- End Three Dots Dropdown Menu Icon -->

                        <hr>
                        <p class="mb-0">${idto.issueCode}</p>
                      </div>

                    </div>
                  </c:forEach>

                </c:when>
              </c:choose>
            </div>
          </div>
        </div>

      </c:forEach><!-- End Column -->

      <div class="col-lg-2" style="min-width: 300px">

        <!-- Column Creation Button -->
        <div class="col-sm-1">
          <button type="button" class="btn btn-light" onclick="toggleInput('col-input-box')">
            <img src="../resources/bootstrap/img/button_plus.png">
          </button>
        </div>

        <!-- Column Creation Input Box -->
        <div class="col-sm-12" style="margin-top: 12px">
          <input type="text" class="form-control" id="col-input-box" style="display: none" placeholder="컬럼 제목 입력 후 엔터 (최대 30자)" onkeyup="addColumn(this)">
        </div>

      </div>

    </div>
  </section>

</main><!-- End #main -->

<%@ include file="../layouts/footer.jsp"%>

<script>

  /* 보드명 변경 */
  function updateBoardName(inputText){

    const inputBox = document.getElementById("board-update-box");
    let boardName = inputText.value;

    // 입력 글자수 제어
    if(boardName.length > 30) {
      alert("최대 30자까지만 작성할 수 있습니다.");
      inputBox.value = boardName.substring(0, 28); // 문자열 29자로 자르기
      return;
    }

    // 엔터키 입력 시 IF문 실행
    if (window.event.keyCode == 13) {

      // 입력값이 공백인 경우
      if(boardName.trim() == ""){
        alert("컬럼명을 최소 1글자 이상 입력해 주세요.");
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
  function addColumn(inputText) {

    const inputBox = document.getElementById("col-input-box");
    let colName = inputText.value;

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

      // URL(+ 파라미터) 만들기
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
  function updateColName(inputText, colSeq){
    const inputBox = document.getElementById("col-update-box");
    let colName = inputText.value;

    // 입력 글자수 제어
    if(colName.length > 30) {
      alert("최대 30자까지만 작성할 수 있습니다.");
      inputBox.value = colName.substring(0, 28); // 문자열 29자로 자르기
      return;
    }

    // 엔터키 입력 시 IF문 실행
    if (window.event.keyCode == 13) {

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
          // 컬럼 리스트 업데이트
          document.getElementById("column-list-card").innerHTML = this.responseText;
          // inputBox focus 해제
          inputBox.blur();
        }
      };
      // 결과값 받음
      xhttp.send();
    }
  }

  /* 컬럼 삭제 */
  function deleteColumn(colSeq) {

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
    // URL(+ 파라미터) 만들기
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
    // URL(+ 파라미터) 만들기
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

</script>
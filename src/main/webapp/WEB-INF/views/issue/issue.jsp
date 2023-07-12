<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>

.main,
button span,
.custom-font input,
.custom-font select
{
    font-size: 15px;
    font-family: 'Nunito', sans-serif;
}

.pagetitle input {
    border: none;
    background-color: #f6f9ff;
    font-size: 24px;
    font-family: 'Nunito', sans-serif;
    font-weight: 600;
    color: #012970
}
.issue-label input {
    border: none;
    font-family: 'Nunito', sans-serif;
    font-size: 15px;
    width: 100px;
}
.date-info {
    text-align: end
}
.date-info p {
    font-size: small;
    font-family: 'Nunito', sans-serif;
    font-weight: 400;
    color: #012970
}

</style>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="../project/projectsidebar.jsp"%>

  <main id="main" class="main">
    <h5 class="card-title"></h5>
    <div class="row">

      <!-- Page Title -->
      <div class="col-lg-6">
        <div class="pagetitle">
          <h1 id="issue-title-box">
            <c:choose>
              <c:when test="${teamAllow == 3}">
                <input id="issue-title" type="text" value="${idto.issueTitle}" readonly>
              </c:when>
              <c:otherwise>
                <input id="issue-title" type="text" value="${idto.issueTitle}" onkeyup="updateIssueInfo(this, 'title')">
              </c:otherwise>
            </c:choose>
          </h1>
          <nav style="--bs-breadcrumb-divider: '>';">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="/project">프로젝트</a></li>
              <li class="breadcrumb-item"><a href="/project/board?projectSeq=${idto.projectSeq}&boardSeq=${idto.boardSeq}">보드</a></li>
              <li class="breadcrumb-item active">이슈</li><br>
            </ol>
          </nav>
        </div>
      </div><!-- End Page Title -->

      <%-- 생성일자, 업데이트일자 --%>
      <div id="issue-date-info" class="date-info col-lg-6">
        <p><br><br>
          최초작성 &nbsp;<fmt:formatDate value="${idto.issueRegdate}" pattern="yyyy.MM.dd HH:mm:ss" />
          &nbsp; 업데이트 &nbsp;<fmt:formatDate value="${idto.issueUpdate}" pattern="yyyy.MM.dd HH:mm:ss" />
        </p>
      </div>

    </div>

    <form>
      <section class="section">
        <div class="row">

          <!-- Summary Card -->
          <div class="col-lg-6">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title"></h5>

                <div class="row mb-3">
                  <div  class="col-sm-5">
<%--                    <!-- 파일 첨부 -->--%>
<%--                    <button type="button" class="btn btn-light">--%>
<%--                      <img src="../resources/bootstrap/img/attach-clip.png" width="20"/>--%>
<%--                      <span>첨부</span>--%>
<%--                    </button>--%>
<%--                    &nbsp;--%>
                    <!-- 이슈 연결 -->
                    <c:choose>
                      <c:when test="${teamAllow != 3}">
                        <button type="button" class="btn btn-light" onclick="toggleSelect('issue-link-select-box')">
                          <img src="../resources/bootstrap/img/attach-link.png" width="20"/>
                          <span>이슈 연결</span>
                        </button>
                      </c:when>
                    </c:choose>
                  </div>
                  <div class="custom-font col-sm-7">
                    <!-- Issue Link Dropdown -->
                    <select id="issue-link-select-box" class="form-select" style="display: none" aria-label="default select example" onchange="linkIssue(this)">
                      ${unlinkedIssueList}
                    </select>
                  </div>
                </div><br>

                <!-- 링크된 이슈 리스트 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">링크</label>
                  <ol id="issue-link-complete" class="custom-font col-sm-10 list-group" style="padding-top: 0; list-style: none">
                    ${linkedIssueList}
                  </ol>
                </div><br>

                <!-- 설명 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">설명</label>
                  <div id="issue-summary-box" class="custom-font col-sm-10">
                    <c:choose>
                      <c:when test="${teamAllow == 3}">
                        <input id="issue-summary" type="text" class="form-control" value="${idto.issueSummary}" readonly>
                      </c:when>
                      <c:otherwise>
                        <input id="issue-summary" type="text" class="form-control" value="${idto.issueSummary}" onkeyup="updateIssueInfo(this, 'summary')">
                      </c:otherwise>
                    </c:choose>
                  </div>
                </div><br>

                <!-- 레이블 -->
<%--                <div class="row mb-3">--%>
<%--                  <label class="col-sm-2 col-form-label">레이블</label>--%>
<%--                  <div class="custom-font col-sm-10">--%>
<%--                    <input type="text" class="form-control">--%>
<%--                  </div>--%>
<%--                </div><br>--%>

                <!-- 댓글 추가 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">활동</label>
                  <div class="custom-font col-sm-10">
                    <input id="comment-value" type="text" class="form-control" placeholder="댓글 작성 후 엔터키를 눌러 등록하세요." onkeyup="addComment(this)">
                  </div>
                </div><br>

                <!-- 댓글 -->
                <div id="comment-box" class="row mb-3">
                  ${commentList}
                </div>

              </div>
            </div><!-- End Comment Card -->
          </div><!-- End Summary Card -->

          <!-- Detail Card -->
          <div class="col-lg-6">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title"></h5>
                <div class="pagetitle col-sm-5">
                  <h5>세부 정보</h5>
                </div><!-- End Card Title -->

                <div class="row mb-3">

                  <!-- IssueForm Creation Button -->
                  <c:choose>
                    <c:when test="${teamAllow != 3}">
                      <button class="btn btn-primary btn-light col-sm-2" type="button" onclick="toggleSelect('issue-select-box')">
                        <img src="../resources/bootstrap/img/button_plus.png" width="17">
                      </button>
                    </c:when>
                  </c:choose>

                  <!-- Board Creation Input Box -->
                  <div class="custom-font col-sm-8">
                    <select class="form-select" id="issue-select-box" style="display: none" aria-label="default select example" onchange="addIssueForm(this)">
                      <option selected>구성요소 추가하기</option>
                      <option value="per">담당자</option>
                      <option value="dat">날짜</option>
                      <option value="pri">우선 순위</option>
                      <option value="sim">간단한 텍스트</option>
                    </select>
                  </div>
                </div>

                <div id="issue-form-list">
                  ${issueFormList}
                </div>

              </div>
            </div>
          </div><!-- End Detail Card -->

        </div>
      </section>
    </form><!-- End General Form Elements -->

  </main><!-- End #main -->

<%@ include file="../layouts/footer.jsp"%>

<script>

  /* 이슈 세부사항 수정 */
  function updateIssueInfo(input, type){
    let boxId = null; // 출력 위치 지정 엘리먼트 ID
    let valueId = null; // Value 엘리먼트 ID
    let maxLength = 0; // 입력 제한 길이
    const inputValue = input.value; // 입력값
    let url = "/project/updateissueinfo?issueSeq=" + ${idto.issueSeq}
            + "&inputValue=" + encodeURIComponent(inputValue);

    switch (type) {
      // 이슈 제목
      case "title":
        boxId = "issue-title-box";
        valueId = "issue-title";
        maxLength = 30;
        url += "&type=title";
        break;
      // 설명
      case "summary":
        boxId = "issue-summary-box";
        valueId = "issue-summary";
        maxLength = 300;
        url += "&type=summary";
        break;
    }

    const valueElement = document.getElementById(valueId); // 입력/선택값 가져올 엘리먼트

    // 입력 글자수 제어
    if(inputValue.length > maxLength) {
      alert("최대 " + maxLength + "자까지만 작성할 수 있습니다.");
      valueElement.value = inputValue.substring(0, maxLength-2); // 문자열 자르기(Max-2)
      return;
    }

    // focus 해제 시 실행
    valueElement.onblur = function (e) {
      // 이슈 제목의 입력값이 공백인 경우
      if(type == "title"){
        if(inputValue.trim() == ""){
          alert("이슈 제목을 최소 1글자 이상 입력해 주세요.");
          return;
        }
      }

      // 연결 작업
      const xhttp = new XMLHttpRequest();
      xhttp.open("GET", url, true);

      // 콜백 작업 지정
      xhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200){
          updateDateInfo(); // 이슈 업데이트 일시 수정
          document.getElementById(boxId).innerHTML = this.responseText; // 태그 업데이트
          // 이슈 제목인 경우 focus 해제
          if(type == "title"){
            valueElement.blur();
          }
        }
      };
      // 결과값 받음
      xhttp.send();
    }
  }

  /* 이슈 링크 걸기 */
  function linkIssue(selectedOption){
    const selectedValue = selectedOption.value; // 선택된 옵션명

    toggleSelect("issue-link-select-box"); // 드롭다운(이슈 링크) 숨기기

    // URL 만들기
    let url = "/project/linkissue?issueSeq=" + ${idto.issueSeq}
            + "&linkIssueSeq=" + selectedValue;

    // 연결 작업
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);

    // 콜백 작업 지정
    xhttp.onreadystatechange = function (){
      if(this.readyState == 4 && this.status == 200){
        updateDateInfo(); // 이슈 업데이트 일시 수정
        document.getElementById("issue-link-complete").innerHTML = this.responseText; // 태그 업데이트
        document.getElementById("issue-link-select-" + selectedValue).remove(); // 태그 삭제
      }
    }
    // 결과값 받음
    xhttp.send();
  }

  /* 이슈 링크 해제 */
  function unlinkIssue(linkedIssueSeq){
    // URL 만들기
    let url = "/project/unlinkissue?issueSeq=" + ${idto.issueSeq}
            + "&linkedIssueSeq=" + linkedIssueSeq;

    // 연결 작업
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);

    // 콜백 작업 지정
    xhttp.onreadystatechange = function (){
      if(this.readyState == 4 && this.status == 200){
        updateDateInfo(); // 이슈 업데이트 일시 수정
        document.getElementById("issue-link-" + linkedIssueSeq).remove(); // 태그 삭제
        console.log(document.getElementById("issue-link-select-box"));
        document.getElementById("issue-link-select-box").innerHTML = this.responseText; // 태그 업데이트
      }
    }

    // 결과값 받음
    xhttp.send();
  }

  /* 드롭다운(이슈폼 추가) 토글 */
  function toggleSelect(selectBoxId) {
    const selectBox = document.getElementById(selectBoxId); // 드롭다운 엘리먼트

    if(selectBox.style.display != "none") {
      selectBox.style.display = "none";
    } else {
      selectBox.options[0].selected = true; // 드롭다운 출력할 때 기본옵션("구성요소 출력하기") select
      selectBox.style.display = "block";
    }
  }

  /* 이슈폼 추가 */
  function addIssueForm(selectedOption){
    const selectedValue = selectedOption.value; // 선택된 옵션명

    toggleSelect("issue-select-box"); // 드롭다운(이슈폼 추가) 숨기기

    // URL 만들기
    let url = "/project/addissueform?issueSeq=" + ${idto.issueSeq}
            + "&selectedValue=" + selectedValue;

    // 연결 작업
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);

    // 콜백 작업 지정
    xhttp.onreadystatechange = function (){
      if(this.readyState == 4 && this.status == 200){
        updateDateInfo(); // 이슈 업데이트 일시 수정
        document.getElementById("issue-form-list").innerHTML = this.responseText; // 태그 업데이트
      }
    }
    // 결과값 받음
    xhttp.send();
  }

  /* 이슈폼 제목(Label) 변경 */
  function updateLabel(input, formsSeq){
    const labelValue = input.value; // 입력값
    const valueId = formsSeq + "-label";
    const boxId = valueId + "-box";
    const valueElement = document.getElementById(valueId); // Value 엘리먼트

    // 입력 글자수 제어
    if(labelValue.length > 8) {
      alert("최대 8자까지만 작성할 수 있습니다.");
      valueElement.value = labelValue.substring(0, 7); // 문자열 자르기(7자)
      return;
    }

    // focus 해제 시 실행
    valueElement.onblur = function (e) {
      // 이슈 제목의 입력값이 공백인 경우
      if (labelValue.trim() == "") {
        alert("구성요소 이름을 최소 1글자 이상 입력해 주세요.");
        return;
      }

      // URL 만들기
      let url = "/project/updatelabel?issueSeq=" + ${idto.issueSeq}
          + "&labelValue=" + labelValue
          + "&formsSeq=" + formsSeq;

      // 연결 작업
      const xhttp = new XMLHttpRequest();
      xhttp.open("GET", url, true);

      // 콜백 작업 지정
      xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
          updateDateInfo(); // 이슈 업데이트 일시 수정
          document.getElementById(boxId).innerHTML = this.responseText; // 태그 업데이트
          valueId.blur(); // focus 해제
        }
      };
      // 결과값 받음
      xhttp.send();
    }
  }

  /* 이슈폼 값(Value) 변경 */
  function updateValue(input, formsSeq){
    const inputValue = input.value;
    const boxId = formsSeq + "-value-box";

    // URL 만들기
    let url = "/project/updatevalue?issueSeq=" + ${idto.issueSeq}
        + "&inputValue=" + inputValue
        + "&formsSeq=" + formsSeq;

    // 연결 작업
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);

    // 콜백 작업 지정
    xhttp.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        updateDateInfo(); // 이슈 업데이트 일시 수정
        document.getElementById(boxId).innerHTML = this.responseText; // 태그 업데이트
      }
    }
    // 결과값 받음
    xhttp.send();
  }

  /* 댓글 등록 */
  function addComment(input){
    const inputValue = input.value;
    let valueElement = document.getElementById("comment-value");

    // 입력 글자수 제어
    if(inputValue.length > 100) {
      alert("최대 100자까지만 작성할 수 있습니다.");
      valueElement.value = inputValue.substring(0, 98); // 문자열 자르기
      return;
    }

    // 엔터키 입력 시 IF문 실행
    if (window.event.keyCode == 13) {
      // 댓글 입력값이 공백인 경우
      if (inputValue.trim() == "") {
        alert("댓글을 최소 1글자 이상 입력해 주세요.");
        return;
      }

      // url 만들기
      const url = "/project/addcomment?issueSeq=" + ${idto.issueSeq}
                + "&inputValue=" + encodeURIComponent(inputValue);

      // 연결 작업
      const xhttp = new XMLHttpRequest();
      xhttp.open("GET", url, true);

      // 콜백 작업 지정
      xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
          document.getElementById("comment-box").innerHTML = this.responseText; // 태그 업데이트
          valueElement.blur();
          valueElement.value = null;
        }
      };
    // 결과값 받음
    xhttp.send();
    }
  }

  /* 댓글 수정용 입력창 토글 */
  function toggleCommentInput(commentInputId){
    const commentInput = document.getElementById(commentInputId);

    if(commentInput.style.display != "none"){
      commentInput.style.display = "none";
    } else {
      commentInput.style.display = "block";
    }
  }

  /* 댓글 수정 */
  function updateComment(input, commentSeq){
    const inputValue = input.value;
    const commentInput = document.getElementById("comment-" + commentSeq + "-update");
    const commentInputBox = document.getElementById("comment-" + commentSeq + "-update-box");

    // 입력 글자수 제어
    if(inputValue.length > 100) {
      alert("최대 100자까지만 작성할 수 있습니다.");
      commentInput.value = inputValue.substring(0, 98); // 문자열 자르기
      return;
    }

    // 엔터키 입력 시 IF문 실행
    if (window.event.keyCode == 13) {
      // 댓글 입력값이 공백인 경우
      if (inputValue.trim() == "") {
        alert("댓글을 최소 1글자 이상 입력해 주세요.");
        return;
      }

      // URL 만들기
      let url = "/project/updatecomment?issueSeq=" + ${idto.issueSeq}
              + "&commentSeq=" + commentSeq
              + "&inputValue=" + inputValue;

      // 연결 작업
      const xhttp = new XMLHttpRequest();
      xhttp.open("GET", url, true);

      // 콜백 작업 지정
      xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
          document.getElementById("comment-box").innerHTML = this.responseText; // 태그 업데이트
          commentInputBox.style.display = "none"; // 댓글 수정용 입력창 숨기기
        }
      }
      // 결과값 받음
      xhttp.send();
    }
  }

  /* 댓글 삭제 */
  function deleteComment(commentSeq){
    // URL 만들기
    const url = "/project/deletecomment?commentSeq=" + commentSeq;

    // 연결 작업
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);

    // 태그 삭제
    document.getElementById("comment-" + commentSeq).remove();

    // 결과값 받음
    xhttp.send();
  }

  /* 이슈폼 위/아래 이동 */
  function moveUpDown(updown, formsSeq){
    let url;
    // URL 만들기
    if(updown == "up"){
      url = "/project/moveup?issueSeq=" + ${idto.issueSeq} + "&formsSeq=" + formsSeq;
    } else {
      url = "/project/movedown?issueSeq=" + ${idto.issueSeq} + "&formsSeq=" + formsSeq;
    }

    // 연결 작업
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);

    // 콜백 작업 지정
    xhttp.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        updateDateInfo(); // 이슈 업데이트 일시 수정
        document.getElementById("issue-form-list").innerHTML = this.responseText; // 태그 업데이트
      }
    }
    // 결과값 받음
    xhttp.send();
  }

  /* 이슈폼 삭제 */
  function deleteForms(formsSeq){
    // URL 만들기
    const url = "/project/deleteforms?issueSeq=" + ${idto.issueSeq}
              + "&formsSeq=" + formsSeq;

    // 연결 작업
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);

    // 태그 삭제
    document.getElementById("forms-" + formsSeq).remove();

    // 이슈 업데이트 일시 수정
    updateDateInfo();

    // 결과값 받음
    xhttp.send();
  }

  /* 이슈 업데이트 일자 변경 */
  function updateDateInfo(){
    // url 만들기
    let url = "/project/updatedateinfo?issueSeq=" + ${idto.issueSeq};

    // 연결 작업
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);

    // 콜백 작업 지정
    xhttp.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        // 태그 업데이트
        document.getElementById("issue-date-info").innerHTML = this.responseText;
      }
    }
    // 결과값 받음
    xhttp.send();
  }

</script>











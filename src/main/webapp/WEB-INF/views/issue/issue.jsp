<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="../project/projectsidebar.jsp"%>

  <main id="main" class="main" style="font-size: 14px; font-family: 'Nunito', sans-serif;">
    <h5 class="card-title"></h5>
    <div class="row">

      <div class="col-lg-6">
        <!-- Page Title -->
        <div class="pagetitle">
          <h1 id="issue-title">
            <input id="issue-title-box" type="text" value="${idto.issueTitle}" onkeyup="updateIssueDTO(this, 'title')"
                   style="border: none; background-color: #f6f9ff; font-family: 'Nunito', sans-serif;
                   font-size: 24px; font-weight: 600; color: #012970">
          </h1>
          <nav style="--bs-breadcrumb-divider: '>';">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="/project">프로젝트</a></li>
              <li class="breadcrumb-item"><a href="/project/board?projectSeq=${idto.projectSeq}&boardSeq=${idto.boardSeq}">보드</a></li>
              <li class="breadcrumb-item active">이슈</li><br>
            </ol>
          </nav>
        </div><!-- End Page Title -->
      </div>

      <%-- 생성일자, 업데이트일자 --%>
      <div class="col-lg-6" style="text-align: end">
        <p style="font-size: small; font-family: 'Nunito', sans-serif; font-weight: 400; color: #012970">
          최초작성 &nbsp; <fmt:formatDate value="${idto.issueRegdate}" pattern="y년 M월 d일 a h:mm" type="date"/><br>
          업데이트 &nbsp; <fmt:formatDate value="${idto.issueUpdate}" pattern="y년 M월 d일 a h:mm" type="date"/>
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
                  <div class="col-sm-12">

                    <!-- 파일 첨부 -->
                    <button type="button" class="btn btn-light">
                      <img src="../resources/bootstrap/img/attach-clip.png" width="20"/>
                      <span style="font-size: 14px; font-family: 'Nunito', sans-serif;">첨부</span>
                    </button>
                    &nbsp;
                    <!-- 이슈 연결 -->
                    <button type="button" class="btn btn-light">
                      <img src="../resources/bootstrap/img/attach-link.png" width="20"/>
                      <span style="font-size: 14px; font-family: 'Nunito', sans-serif;">이슈 연결</span>
                    </button>

                  </div>
                </div>

                <!-- 설명 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">설명</label>
                  <div id="issue-summary" class="col-sm-10">
                    <input type="text" class="form-control" style="font-size: 14px; font-family: 'Nunito', sans-serif;" value="${idto.issueSummary}" onkeyup="updateIssueDTO(this, 'summary')">
                  </div>
                </div>

                <!-- 레이블 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">레이블</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" style="font-size: 14px; font-family: 'Nunito', sans-serif;">
                  </div>
                </div>

              </div>
            </div><!-- End Summary Card -->

            <!-- Comment Card -->
            <div class="card">
              <div class="card-body">
                <h5 class="card-title"></h5>

                <!-- 댓글 추가 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">활동</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" style="font-size: 14px; font-family: 'Nunito', sans-serif;" placeholder="댓글 추가...">
                  </div>
                </div>

                <!-- 댓글 -->
                <div class="row mb-3">
                  <div class="col-sm-10">
                    <img src="../resources/bootstrap/img/profile-img.jpg" alt="Profile" width="50">
                    <p>작성자 | 작성일시<br>내용</p>
                  </div>
                </div>

              </div>
            </div>
          </div><!-- End Comment Card -->

          <!-- Detail Card -->
          <div class="col-lg-6">
            <div class="card">
              <div class="card-body">
                <h5 class="card-title"></h5>
                <div class="pagetitle col-sm-5">
                  <h5>세부 정보</h5>
                </div><!-- End Card Title -->

                <!-- Board Creation Button -->
                <div class="d-grid gap-2 mt-3">
                  <button class="btn btn-primary btn-light" type="button" onclick="toggleSelect('issue-detail-input-box')">
                    <img src="../resources/bootstrap/img/button_plus.png" width="17">
                  </button>
                </div>
                <br>
                <div class="row mb-3">
                  <div class="col-sm-12">
                    <!-- Board Creation Input Box -->
                    <select class="form-select" id="issue-detail-input-box" style="display: none; font-size: 14px; font-family: 'Nunito', sans-serif;" aria-label="default select example" onchange="addIssueForm(this)">
                      <option selected>구성요소 추가하기</option>
                      <option value="per">담당자</option>
                      <option value="dat">날짜</option>
                      <option value="pri">우선 순위</option>
                      <option value="che">체크박스</option>
                      <option value="dro">드롭다운</option>
                      <option value="sim">간단한 텍스트</option>
                      <option value="par">단락</option>
                    </select>
                  </div>
                </div>

                <!-- 담당자 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">담당자</label>
                  <div class="col-sm-10">
                    <select class="form-select" style="font-size: 14px; font-family: 'Nunito', sans-serif;" aria-label="Default select example">
                      <option value="진윤석" selected>진윤석</option>
                      <option value="신성주">신성주</option>
                      <option value="봉지원">봉지원</option>
                    </select>
                  </div>
                </div>

                <%--
                <!-- 날짜 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">날짜</label>
                  <div class="col-sm-10">
                    <input type="date" class="form-control" style="font-size: 14px; font-family: 'Nunito', sans-serif;">
                  </div>
                </div>
                --%>

                <%--
                <!-- 우선 순위 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">우선 순위</label>
                  <div class="col-sm-10">
                    <select class="form-select" style="font-size: 14px; font-family: 'Nunito', sans-serif;" aria-label="Default select example">
                      <option value="highest">Highest</option>
                      <option value="high">High</option>
                      <option value="medium" selected>Medium</option>
                      <option value="low">Low</option>
                      <option value="lowest">Lowest</option>
                    </select>
                  </div>
                </div>
                --%>

                <%--
                <!-- 체크박스 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">체크박스</label>
                  <div class="col-sm-10">

                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="gridCheck1">
                      <label class="form-check-label" for="gridCheck1">
                        Example checkbox 1
                      </label>
                    </div>

                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="gridCheck2">
                      <label class="form-check-label" for="gridCheck2">
                        Example checkbox 2
                      </label>
                    </div>

                  </div>
                </div>
                --%>

                <%--
                <!-- 드롭다운 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">드롭다운</label>
                  <div class="col-sm-10">
                    <select class="form-select" style="font-size: 14px; font-family: 'Nunito', sans-serif;" aria-label="Default select example">
                      <option selected>없음</option>
                      <option value="1">Example dropbox 1</option>
                      <option value="2">Example dropbox 2</option>
                    </select>
                  </div>
                </div>
                --%>

                <%--
                <!-- 간단한 텍스트 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">간단한 텍스트</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" style="font-size: 14px; font-family: 'Nunito', sans-serif;">
                  </div>
                </div>
                --%>

                <%--
                <!-- 단락 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">단락</label>
                  <div class="col-sm-10">
                    <div class="quill-editor-default" style="font-size: 14px; font-family: 'Nunito', sans-serif;"></div>
                  </div>
                </div>
                --%>
              <br><br>
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
  function updateIssueDTO(inputText, type){

    let eBoxId = null;
    let eTagId = null;
    let maxLength = 0;
    const inputValue = inputText.value;
    let url = "/project/updateissuedto?issueSeq=" + ${idto.issueSeq}
            + "&inputValue=" + encodeURIComponent(inputValue);

    switch (type) {
      case "title":
        eBoxId = "issue-title-box";
        eTagId = "issue-title";
        maxLength = 30;
        url += "&type=title";
        break;
      case "summary":
        eBoxId = "issue-summary-box";
        eTagId = "issue-summary";
        maxLength = 300;
        url += "&type=summary";
        break;
    }

    const inputBox = document.getElementById(eBoxId);

    // 입력 글자수 제어
    if(inputValue.length > maxLength) {
      alert("최대 " + maxLength + "자까지만 작성할 수 있습니다.");
      inputBox.value = inputValue.substring(0, maxLength-2); // 문자열 자르기(Max-2)
      return;
    }

    // 엔터키 입력 시 IF문 실행
    if (window.event.keyCode == 13) {
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
          // 태그 업데이트
          document.getElementById(eTagId).innerHTML = this.responseText;
          // 이슈 제목인 경우 focus 해제
          if(type == "title"){
            inputBox.blur();
          }
        }
      };
      // 결과값 받음
      xhttp.send();
    }
  }

  /* 드롭다운 토글 */
  function toggleSelect(selectBoxId) {

    const id = selectBoxId;
    const selectBox = document.getElementById(id);

    // input toggle
    if(selectBox.style.display != "none") {
      selectBox.style.display = "none";
    } else {
      // 드롭다운 출력할 때 기본옵션 select
      selectBox.options[0].selected = true;
      selectBox.style.display = "block";
    }
  }

  function addIssueForm(selectedObj){
    alert(selectedObj.value);

    // 드롭다운 숨기기
    toggleSelect("issue-detail-input-box");

  }

</script>
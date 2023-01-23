<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="../project/projectsidebar.jsp"%>

  <main id="main" class="main">

    <div class="pagetitle">
      <h1></h1>
    </div><!-- End Page Title -->

    <form>
      <section class="section">
        <div class="row">

          <!-- Summary Card -->
          <div class="col-lg-6">
            <div class="card">
              <div class="card-body">
                <div class="pagetitle">
                  <h5 class="card-title"></h5>
                  <h1 id="issue-title">
                    <input id="issue-title-box" type="text" value="${idto.issueTitle}" onkeyup="updateIssueDTO(this, 'title')"
                           style="border: none; font-family: 'Nunito', sans-serif;
                           font-size: 24px; font-weight: 600; color: #012970">
                  </h1>
                </div><!-- End Summary Card Title -->

                <div class="row mb-3">
                  <div class="col-sm-10">

                    <!-- 파일 첨부 -->
                    <button type="button" class="btn btn-light">
                      <img src="../resources/bootstrap/img/attach-clip.png" width="25"/>
                      <span>첨부</span>
                    </button>
                    &nbsp;
                    <!-- 이슈 연결 -->
                    <button type="button" class="btn btn-light">
                      <img src="../resources/bootstrap/img/attach-link.png" width="25"/>
                      <span>이슈 연결</span>
                    </button>

                  </div>
                </div>

                <!-- 설명 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">설명</label>
                  <div id="issue-summary" class="col-sm-10">
                    <input type="text" class="form-control" value="${idto.issueSummary}" onkeyup="updateIssueDTO(this, 'summary')">
                  </div>
                </div>

                <!-- 레이블 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">레이블</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control">
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
                    <input type="text" class="form-control" placeholder="댓글 추가...">
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
                <div class="pagetitle">
                  <h5 class="card-title"></h5>
                  <h5>세부 정보</h5>
                </div><!-- End Card Title -->

                <!-- 담당자 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">담당자</label>
                  <div class="col-sm-10">
                    <select class="form-select" aria-label="Default select example">
                      <option value="진윤석" selected>진윤석</option>
                      <option value="신성주">신성주</option>
                      <option value="봉지원">봉지원</option>
                    </select>
                  </div>
                </div>

                <!-- 날짜 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">날짜</label>
                  <div class="col-sm-10">
                    <input type="date" class="form-control">
                  </div>
                </div>

                <!-- 우선 순위 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">우선 순위</label>
                  <div class="col-sm-10">
                    <select class="form-select" aria-label="Default select example">
                      <option value="highest">Highest</option>
                      <option value="high">High</option>
                      <option value="medium" selected>Medium</option>
                      <option value="low">Low</option>
                      <option value="lowest">Lowest</option>
                    </select>
                  </div>
                </div>

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

                <!-- 드롭다운 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">드롭다운</label>
                  <div class="col-sm-10">
                    <select class="form-select" aria-label="Default select example">
                      <option selected>없음</option>
                      <option value="1">Example dropbox 1</option>
                      <option value="2">Example dropbox 2</option>
                    </select>
                  </div>
                </div>

                <!-- 간단한 텍스트 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">간단한 텍스트</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control">
                  </div>
                </div>

                <!-- 단락 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">단락</label>
                  <div class="col-sm-10">
                    <div class="quill-editor-default"></div>
                  </div>
                </div><br><br>

              </div>
            </div>
          </div><!-- End Detail Card -->

        </div>
      </section>
    </form><!-- End General Form Elements -->

  </main><!-- End #main -->

<%@ include file="../layouts/footer.jsp"%>

<script>

  function updateIssueDTO(inputText, type){

    let eBoxId = null;
    let eTagId = null;
    let maxLength = 0;
    let inputValue = inputText.value;
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

</script>
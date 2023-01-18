<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="projectsidebar.jsp"%>

<main id="main" class="main">

  <h5 class="card-title"></h5>

  <!-- Page Title -->
  <div class="pagetitle">
    <h1>${bdto.boardName}</h1>
    <nav>
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/project">프로젝트</a></li>
        <li class="breadcrumb-item active">보드</li>
      </ol>
    </nav>
  </div><!-- End Page Title -->

  <section class="section dashboard">
    <div class="row" id="columnlist-card">

      <!-- Column -->
      <c:forEach var="cdto" items="${clist}">
        <div id="${cdto.colSeq}" class="col-lg-2 col-md-6" style="min-width: 300px">
          <div class="card info-card sales-card">

            <!-- Three Dots Dropdown Menu Icon -->
            <div class="filter">
              <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
              <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                <li><a class="dropdown-item" href="#">이슈 추가</a></li>
                <li><a class="dropdown-item" href="javascript:deletecolumn(${cdto.colSeq})">컬럼 삭제</a></li>
              </ul>
            </div><!-- End Three Dots Dropdown Menu Icon -->

            <!-- Column Title -->
            <div class="card-body">
              <h5 class="card-title">${cdto.colName}</h5>
            </div>

            <!-- Issue Card -->
            <c:choose>
              <c:when test="${imap.containsKey(cdto.colSeq)}">

                <c:forEach var="ilist" items="${imap.get(cdto.colSeq)}">
                  <c:forEach var="idto" items="${ilist}">
                    <div class="card-body" style="font-size: 14px">

                      <a href="${twone}/project/issue">
                        <div class="alert alert-secondary fade show" role="alert">
                          <p>${idto.issueTitle}</p>
                          <!-- Three Dots Dropdown Menu Icon -->
                          <div class="filter">
                            <div class="icon" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></div>
                            <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                              <li><div class="dropdown-item">이슈 삭제</div></li>
                            </ul>
                          </div><!-- End Three Dots Dropdown Menu Icon -->
                          <hr>
                          <p class="mb-0">${idto.issueCode}</p>
                        </div>
                      </a>

                    </div>
                  </c:forEach>
                </c:forEach>

              </c:when>
            </c:choose>
          </div>
        </div>

      </c:forEach><!-- End Column -->

      <div class="col-lg-2" style="min-width: 300px">
        <!-- Column Creation Button -->
        <div class="col-sm-1">
          <button type="button" class="btn btn-light" onclick="togglecnameinput()">
            <img src="../resources/bootstrap/img/button_plus.png">
          </button>
        </div>

        <!-- Column Creation Input Box -->
        <div class="col-sm-12" style="margin-top: 12px">
          <input type="text" class="form-control" id="cnameinput" style="display: none" placeholder="컬럼 제목 입력 후 엔터 (최대 30자)" onkeyup="addcolumn(this)">
        </div>
      </div>

    </div>
  </section>

</main><!-- End #main -->

<%@ include file="../layouts/footer.jsp"%>

<script>

  <%-- 보드 생성 --%>
  function addcolumn(obj) {

    // 입력 글자수 제어
    if(obj.value.length > 30) {
      alert("최대 30자까지만 작성할 수 있습니다.");
      const inputbox = document.getElementById("cnameinput");
      inputbox.value = obj.value.substring(0, 28); // 문자열 29자로 자르기
      return;
    }

    // 엔터키 입력 시 IF문 실행
    if (window.event.keyCode == 13) {

      let cname = obj.value;
      if(cname.trim() == ""){
        alert("컬럼명을 최소 1글자 이상 입력해 주세요.");
        return;
      }

      // URL(+ 파라미터) 만들기
      let url = "/project/addcolumn?colName=" + encodeURIComponent(obj.value) + "&boardSeq=" + ${bdto.boardSeq};

      // 연결 작업
      const xhttp = new XMLHttpRequest();
      xhttp.open("GET", url, true);

      // 콜백 작업 지정
      xhttp.onreadystatechange = function (){
        if(this.readyState == 4 && this.status == 200) {
          document.getElementById("columnlist-card").innerHTML = this.responseText;
        }
      };

      // input 클리어 & 숨기기
      const inputbox = document.getElementById("cnameinput");
      inputbox.value = null;
      inputbox.style.display = "none";

      // 결과값 받음
      xhttp.send();
    }
  }

  <!-- 컬럼 삭제 -->
  function deletecolumn(colSeq) {

    // URL(+ 파라미터) 만들기
    let url = "/project/deletecolumn?boardSeq=" + ${bdto.boardSeq} + "&colSeq=" + colSeq;

    // 연결 작업
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, true);

    // 태그 삭제
    document.getElementById(colSeq).remove();

    // 콜백 작업 지정
    // xhttp.onreadystatechange = function () {
    //   if(this.readyState == 4 && this.status == 200) {
    //     document.getElementById("columnlist-card").innerHTML = this.responseText;
    //   }
    // }

    // 결과값 받음
    xhttp.send();
  }

    <!-- 컬럼 이름 입력창 보이기 / 숨기기 -->
  function togglecnameinput() {

    const inputbox = document.getElementById("cnameinput");

    // input toggle
    if(inputbox.style.display != "none") {
      // 입력창 숨길때 입력값 삭제
      const inputbox = document.getElementById("cnameinput");
      inputbox.value = null;
      inputbox.style.display = "none";
    } else {
      inputbox.style.display = "block";
    }
  }

</script>
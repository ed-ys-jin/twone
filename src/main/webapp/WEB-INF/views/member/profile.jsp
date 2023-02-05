<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="twone" value="${pageContext.request.contextPath }"/>
<%@ include file="../layouts/header.jsp"%>

<script>
  function deleteAction(j) {
    let check = confirm("기본 이미지로 변경 하시겠습니까?");

    if(!check) return;

    location.href='${twone}/image/deleteImg';
  }
</script>

  <main id="main" class="main">

    <h5 class="card-title"></h5>

    <!-- Page Title -->
    <div class="pagetitle">
      <h1>프로필</h1>
    </div>
    <nav>
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="/project">메인</a></li>
        <li class="breadcrumb-item active">보드</li>
      </ol>
    </nav><!-- End Page Title -->

    <section class="section profile">
      <div class="row">
        <div class="col-xl-4">

          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">

              <!-- *** Profile Summary *** -->
              <!-- Profile Image -->
              <c:choose>
                <c:when test="${!empty memDTO.memImage}">
                  <img src="${memDTO.memImage}" alt="Profile" class="rounded-circle">
                </c:when>
                <c:otherwise>
                  <img src="../resources/bootstrap/img/no_image.png" alt="Profile" class="rounded-circle">
                </c:otherwise>
              </c:choose><br>

              <div class="pt-2">
                <!-- 사진 업로드 -->
                <button type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#memImageUpload" title="사진 업로드">
                  <i class="bi bi-upload"></i>
                </button>

                <div class="modal fade" id="memImageUpload" tabindex="-1">
                  <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title">프로필 이미지 변경</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <form method="post" action="${twone}/image/profileImg" enctype="multipart/form-data">
                        <div class="modal-body">
                            <input type="file" name="memPic" value="${memDTO.memImage}">
                        </div>
                        <div class="modal-footer">
                          <button type="submit" class="btn btn-primary">저장</button>
                          <button type="button " class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
                        </div>
                      </form>
                    </div>
                  </div>
                </div><!-- End Vertically centered Modal-->

                <!-- 기본 이미지로 변경 -->
                <button type="button" class="btn btn-primary btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#memImageDelete" title="사진 삭제" onclick="deleteAction()">
                  <i class="bi bi-trash"></i>
                </button>
              </div>

              <!-- Member Name -->
              <h2>${memDTO.memName}</h2><br>

              <!-- Member Position -->
              <c:choose>
                <c:when test="${!empty memDTO.memPosition}">
                  <h3>${memDTO.memPosition}</h3>
                </c:when>
              </c:choose>

            </div>
          </div>

        </div><!-- End Profile Summary -->

        <div class="col-xl-6">

          <div class="card">
            <div class="card-body pt-3">
              <!-- *** Bordered Tabs *** -->
              <ul class="nav nav-tabs nav-tabs-bordered">

                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">프로필</button>
                </li>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">정보 수정</button>
                </li>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-change-password">비밀번호 변경</button>
                </li>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-withdraw">회원 탈퇴</button>
                </li>

              </ul><!-- End Bordered Tabs -->

              <div class="tab-content pt-2">

                <div class="tab-pane fade show active profile-overview" id="profile-overview">
                  <h5 class="card-title"></h5>

                  <!-- *** Profile Info *** -->
                  <!-- Member Name -->
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">이름</div>
                    <div class="col-lg-9 col-md-8">${memDTO.memName}</div>
                  </div>

                  <!-- Member Position -->
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">직위</div>
                    <c:choose>
                      <c:when test="${!empty memDTO.memPosition}">
                        <div class="col-lg-9 col-md-8">${memDTO.memPosition}</div>
                      </c:when>
                      <c:otherwise>
                        <div class="col-lg-9 col-md-8">없음</div>
                      </c:otherwise>
                    </c:choose>
                  </div>

                  <!-- Member Department -->
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">부서</div>
                    <c:choose>
                      <c:when test="${!empty memDTO.memDept}">
                        <div class="col-lg-9 col-md-8">${memDTO.memDept}</div>
                      </c:when>
                      <c:otherwise>
                        <div class="col-lg-9 col-md-8">없음</div>
                      </c:otherwise>
                    </c:choose>
                  </div>

                  <!-- Member Company -->
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">조직</div>
                    <c:choose>
                      <c:when test="${!empty memDTO.memCompany}">
                        <div class="col-lg-9 col-md-8">${memDTO.memCompany}</div>
                      </c:when>
                      <c:otherwise>
                        <div class="col-lg-9 col-md-8">없음</div>
                      </c:otherwise>
                    </c:choose>
                  </div>

                  <!-- Member Email -->
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">이메일</div>
                    <div class="col-lg-9 col-md-8">${memDTO.memEmail}</div>
                  </div>

                </div><!-- End Profile Info -->

                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">
                  <h5 class="card-title"></h5>

                  <!-- *** Profile Edit Form *** -->
                  <form method="post" action="${twone}/editprofile">
                    <div class="row mb-3">
                      <label for="name" class="col-md-4 col-lg-3 col-form-label">이름</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="memName" type="text" class="form-control" id="name" value="${memDTO.memName}">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="position" class="col-md-4 col-lg-3 col-form-label">직위</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="memPosition" type="text" class="form-control" id="position" placeholder="직위" value="${memDTO.memPosition}">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="dept" class="col-md-4 col-lg-3 col-form-label">부서</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="memDept" type="text" class="form-control" id="dept" placeholder="부서" value="${memDTO.memDept}">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="country" class="col-md-4 col-lg-3 col-form-label">조직</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="memCompany" type="text" class="form-control" id="country" placeholder="조직" value="${memDTO.memCompany}">
                      </div>
                    </div>

                    <h5 class="card-title"></h5>

                    <div class="text-center">
                      <button type="submit" class="btn btn-primary">변경사항 저장</button>
                    </div>
                  </form><!-- End Profile Edit Form -->

                </div>



                <div class="tab-pane fade pt-3" id="profile-change-password">
                  <h5 class="card-title"></h5>

                  <!-- *** Change Password Form *** -->
                  <form method="post" action="${twone}/changepassword">

                    <div class="row mb-3">
                      <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">현재 비밀번호</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="currentPw" type="password" class="form-control" id="currentPassword">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="newPassword" class="col-md-4 col-lg-3 col-form-label">새 비밀번호</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="memPw" type="password" class="form-control" id="newPassword">
                      </div>
                    </div>

                    <h5 class="card-title"></h5>

                    <div class="text-center">
                      <button type="submit" class="btn btn-primary">비밀번호 변경</button>
                    </div>
                  </form><!-- End Change Password Form -->

                </div>

                <div class="tab-pane fade pt-3" id="profile-withdraw">
                  <h5 class="card-title"></h5>

                  <div class="text-center">
                    <a href="${twone}/withdraw">
                      <button type="button" class="btn btn-primary">회원 탈퇴</button>
                    </a>
                  </div><br><br>

                </div>

              </div><!-- End Bordered Tabs -->

            </div>
          </div>

        </div>
      </div>
    </section>
  </main><!-- End #main -->


<%@ include file="../layouts/footer.jsp"%>

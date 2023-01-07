<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="../layouts/sidebar.jsp"%>

  <main id="main" class="main">

    <div class="pagetitle">
      <h1>프로필</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="/">홈</a></li>
          <li class="breadcrumb-item active">프로필</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section profile">
      <div class="row">
        <div class="col-xl-4">

          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">

              <img src="../resources/bootstrap/img/profile-img.jpg" alt="Profile" class="rounded-circle">
              <h2>Kevin Anderson</h2>
              <h3>Web Designer</h3>

            </div>
          </div>

        </div>

        <div class="col-xl-8">

          <div class="card">
            <div class="card-body pt-3">
              <!-- Bordered Tabs -->
              <ul class="nav nav-tabs nav-tabs-bordered">

                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">프로필</button>
                </li>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">정보 수정</button>
                </li>

<%--                <li class="nav-item">--%>
<%--                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-settings">Settings</button>--%>
<%--                </li>--%>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-change-password">비밀번호 변경</button>
                </li>

              </ul>

              <div class="tab-content pt-2">

                <div class="tab-pane fade show active profile-overview" id="profile-overview">
                  <h5 class="card-title"></h5>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">이름</div>
                    <div class="col-lg-9 col-md-8">Kevin Anderson</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">직위</div>
                    <div class="col-lg-9 col-md-8">Lueilwitz, Wisoky and Leuschke</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">부서</div>
                    <div class="col-lg-9 col-md-8">Web Designer</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">조직</div>
                    <div class="col-lg-9 col-md-8">USA</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">이메일</div>
                    <div class="col-lg-9 col-md-8">k.anderson@example.com</div>
                  </div>

                </div>

                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">
                  <h5 class="card-title"></h5>

                  <!-- Profile Edit Form -->
                  <form>
                    <div class="row mb-3">
                      <label for="profileImage" class="col-md-4 col-lg-3 col-form-label">프로필 사진</label>
                      <div class="col-md-8 col-lg-9">
                        <img src="../resources/bootstrap/img/profile-img.jpg" alt="Profile">
                        <div class="pt-2">
                          <a href="#" class="btn btn-primary btn-sm" title="사진 변경"><i class="bi bi-upload"></i></a>
                          <a href="#" class="btn btn-danger btn-sm" title="사진 삭제"><i class="bi bi-trash"></i></a>
                        </div>
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="fullName" class="col-md-4 col-lg-3 col-form-label">이름</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="fullName" type="text" class="form-control" id="fullName" value="Kevin Anderson">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="company" class="col-md-4 col-lg-3 col-form-label">직위</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="company" type="text" class="form-control" id="company" value="Lueilwitz, Wisoky and Leuschke">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="Job" class="col-md-4 col-lg-3 col-form-label">부서</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="job" type="text" class="form-control" id="Job" value="Web Designer">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="Country" class="col-md-4 col-lg-3 col-form-label">조직</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="country" type="text" class="form-control" id="Country" value="USA">
                      </div>
                    </div>

                    <h5 class="card-title"></h5>

                    <div class="text-center">
                      <button type="submit" class="btn btn-primary">변경사항 저장</button>
                    </div>
                  </form><!-- End Profile Edit Form -->

                </div>

                <div class="tab-pane fade pt-3" id="profile-settings">
                  <h5 class="card-title"></h5>
                  <!-- Settings Form -->
<%--                  <form>--%>

<%--                    <div class="row mb-3">--%>
<%--                      <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Email Notifications</label>--%>
<%--                      <div class="col-md-8 col-lg-9">--%>
<%--                        <div class="form-check">--%>
<%--                          <input class="form-check-input" type="checkbox" id="changesMade" checked>--%>
<%--                          <label class="form-check-label" for="changesMade">--%>
<%--                            Changes made to your account--%>
<%--                          </label>--%>
<%--                        </div>--%>
<%--                        <div class="form-check">--%>
<%--                          <input class="form-check-input" type="checkbox" id="newProducts" checked>--%>
<%--                          <label class="form-check-label" for="newProducts">--%>
<%--                            Information on new products and services--%>
<%--                          </label>--%>
<%--                        </div>--%>
<%--                        <div class="form-check">--%>
<%--                          <input class="form-check-input" type="checkbox" id="proOffers">--%>
<%--                          <label class="form-check-label" for="proOffers">--%>
<%--                            Marketing and promo offers--%>
<%--                          </label>--%>
<%--                        </div>--%>
<%--                        <div class="form-check">--%>
<%--                          <input class="form-check-input" type="checkbox" id="securityNotify" checked disabled>--%>
<%--                          <label class="form-check-label" for="securityNotify">--%>
<%--                            Security alerts--%>
<%--                          </label>--%>
<%--                        </div>--%>
<%--                      </div>--%>
<%--                    </div>--%>

<%--                    <div class="text-center">--%>
<%--                      <button type="submit" class="btn btn-primary">Save Changes</button>--%>
<%--                    </div>--%>
                  </form><!-- End settings Form -->

                </div>

                <div class="tab-pane fade pt-3" id="profile-change-password">
                  <h5 class="card-title"></h5>

                  <!-- Change Password Form -->
                  <form>

                    <div class="row mb-3">
                      <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">현재 비밀번호</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="password" type="password" class="form-control" id="currentPassword">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="newPassword" class="col-md-4 col-lg-3 col-form-label">새 비밀번호</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="newpassword" type="password" class="form-control" id="newPassword">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="renewPassword" class="col-md-4 col-lg-3 col-form-label">새 비밀번호 확인</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="renewpassword" type="password" class="form-control" id="renewPassword">
                      </div>
                    </div>

                    <h5 class="card-title"></h5>

                    <div class="text-center">
                      <button type="submit" class="btn btn-primary">비밀번호 변경</button>
                    </div>
                  </form><!-- End Change Password Form -->

                </div>

              </div><!-- End Bordered Tabs -->

            </div>
          </div>

        </div>
      </div>
    </section>

  </main><!-- End #main -->

<%@ include file="../layouts/footer.jsp"%>
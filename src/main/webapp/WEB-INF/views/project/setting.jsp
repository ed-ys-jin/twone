<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="projectsidebar.jsp"%>

  <main id="main" class="main">

    <h5 class="card-title"></h5>

    <div class="pagetitle">
      <h1>프로젝트 설정</h1>
      <nav>
          <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="/project">프로젝트</a></li>
              <li class="breadcrumb-item active">설정</li>
          </ol>
      </nav>
    </div><!-- End Page Title -->

    <div class="container">

      <section class="section register d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

              <div class="card mb-3">
                <div class="card-body">

                  <form class="row g-3 needs-validation" novalidate>

                    <h5 class="card-title"></h5>

                    <div class="col-12">
                      <div class="d-flex flex-column align-items-center">
                          <img src="../resources/bootstrap/img/product-5.jpg" alt="Profile" width="200">
                          <div class="pt-2">
                              <a href="#" class="btn btn-primary btn-sm" title="아이콘 변경"><i class="bi bi-upload"></i></a>
                          </div>
                      </div>
                    </div>

                    <div class="col-12">
                      <label class="form-label">이름</label>
                      <input type="text" name="name" class="form-control" id="projectName" required>
                    </div>

                    <div class="col-12">
                      <label class="form-label">키</label>
                      <input type="text" name="name" class="form-control" id="yourName" required>
                    </div>

                    <!-- 담당자 -->
                    <div class="col-12">
                      <label class="form-label">프로젝트 리더</label>
                      <select class="form-select" aria-label="Default select example">
                          <option value="진윤석" selected>진윤석</option>
                          <option value="신성주">신성주</option>
                          <option value="봉지원">봉지원</option>
                      </select>
                    </div>

                    <h5 class="card-title"></h5>

                    <div class="col-12">
                      <button class="btn btn-primary w-100" type="submit">변경사항 저장</button>
                    </div>

                  </form>

                </div>
              </div>

            </div>
          </div>
        </div>

      </section>

    </div>
  </main><!-- End #main -->

<%@ include file="../layouts/footer.jsp"%>
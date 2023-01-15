<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="projectsidebar.jsp"%>

  <main id="main" class="main">

    <h5 class="card-title"></h5>

    <!-- Page Title -->
    <div class="pagetitle">
      <h1>보드</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="/project">프로젝트</a></li>
          <li class="breadcrumb-item active">보드</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section dashboard">
      <div class="row">

        <!-- Column Card -->
        <div class="col-lg-2 col-md-6">
          <div class="card info-card sales-card">

            <!-- Three Dots Dropdown Menu Icon -->
            <div class="filter">
              <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
              <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                <li><a class="dropdown-item" href="#">이슈 추가</a></li>
                <li><a class="dropdown-item" href="#">컬럼 삭제</a></li>
              </ul>
            </div><!-- End Three Dots Dropdown Menu Icon -->

            <!-- Column Title -->
            <div class="card-body">
              <h5 class="card-title">기능 고도화 <span>| 기능 고도화</span></h5>
            </div>

            <!-- Issue Card -->
            <div class="card-body">

              <a href="#">
                <div class="alert alert-secondary fade show" role="alert">
                  <p>개발 일정 플랜 수립</p>
                  <!-- Three Dots Dropdown Menu Icon -->
                  <div class="filter">
                    <div class="icon" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></div>
                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                      <li><div class="dropdown-item">이슈 삭제</div></li>
                    </ul>
                  </div><!-- End Three Dots Dropdown Menu Icon -->
                  <hr>
                  <p class="mb-0">SHINJIN-2</p>
                </div>
              </a>

              <a href="#">
                <div class="alert alert-secondary fade show" role="alert">
                  <p>개발 주제 선정</p>
                  <!-- Three Dots Dropdown Menu Icon -->
                  <div class="filter">
                    <div class="icon" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></div>
                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                      <li><div class="dropdown-item">이슈 삭제</div></li>
                    </ul>
                  </div><!-- End Three Dots Dropdown Menu Icon -->
                  <hr>
                  <p class="mb-0">SHINJIN-1</p>
                </div>
              </a>

            </div>

          </div>
        </div><!-- End Column -->

        <!-- Column Card -->
        <div class="col-lg-2 col-md-6">
          <div class="card info-card sales-card">

            <!-- Three Dots Dropdown Menu Icon -->
            <div class="filter">
              <a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a>
              <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                <li><a class="dropdown-item" href="#">이슈 추가</a></li>
                <li><a class="dropdown-item" href="#">컬럼 삭제</a></li>
              </ul>
            </div><!-- End Three Dots Dropdown Menu Icon -->

            <!-- Column Title -->
            <div class="card-body">
              <h5 class="card-title">기능 고도화 <span>| 기능 고도화</span></h5>
            </div>

            <!-- Issue Card -->
            <div class="card-body">

              <a href="#">
                <div class="alert alert-secondary fade show">
                  <p>개발 일정 플랜 수립</p>
                  <!-- Three Dots Dropdown Menu Icon -->
                  <div class="filter">
                    <div class="icon" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></div>
                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                      <li><div class="dropdown-item">이슈 삭제</div></li>
                    </ul>
                  </div><!-- End Three Dots Dropdown Menu Icon -->
                  <hr>
                  <p class="mb-0">SHINJIN-2</p>
                </div>
              </a>

              <a href="#">
                <div class="alert alert-secondary fade show" role="alert">
                  <p>개발 주제 선정</p>
                  <!-- Three Dots Dropdown Menu Icon -->
                  <div class="filter">
                    <div class="icon" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></div>
                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                      <li><div class="dropdown-item">이슈 삭제</div></li>
                    </ul>
                  </div><!-- End Three Dots Dropdown Menu Icon -->
                  <hr>
                  <p class="mb-0">SHINJIN-1</p>
                </div>
              </a>

            </div>

          </div>
        </div><!-- End Column -->

        <div class="col-lg-2">
          <button type="button" class="btn btn-light"><img src="../resources/bootstrap/img/button_plus.png"></button>
        </div>


      </div>
    </section>

  </main><!-- End #main -->

<%@ include file="../layouts/footer.jsp"%>
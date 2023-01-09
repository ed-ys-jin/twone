<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="projectsidebar.jsp"%>

  <main id="main" class="main">

    <h5 class="card-title"></h5>

    <div class="pagetitle">
      <h1>보드</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="/project">프로젝트</a></li>
          <li class="breadcrumb-item active">보드</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">

        <!-- Begin Column -->
        <div class="col-lg-2">
          <div class="card">
            <div class="card-body">

              <!-- Column Title -->
              <h5 class="card-title">컬럼 1</h5>

              <!-- Issue -->
              <div class="card">
                <div class="card-body">
                  <p>이슈 1</p>
                  <p>This is an examle page with no contrnt. You can use it as a starter for your custom pages.</p>
                </div>
              </div><!-- End Issue -->

              <div class="card">
                <div class="card-body">
                  <p>이슈 2</p>
                  <p>This is an examle page with no contrnt. You can use it as a starter for your custom pages.</p>
                </div>
              </div>

              <div class="card">
                <div class="card-body">
                  <p>이슈 3</p>
                  <p>This is an examle page with no contrnt. You can use it as a starter for your custom pages.</p>
                </div>
              </div>

            </div>
          </div>
        </div><%-- End Column --%>

        <div class="col-lg-2">
          <div class="card">
            <div class="card-body">

              <h5 class="card-title">컬럼 2</h5>

              <div class="card">
                <div class="card-body">
                  <p>이슈 1</p>
                  <p>This is an examle page with no contrnt. You can use it as a starter for your custom pages.</p>
                </div>
              </div>

              <div class="card">
                <div class="card-body">
                  <p>이슈 2</p>
                  <p>This is an examle page with no contrnt. You can use it as a starter for your custom pages.</p>
                </div>
              </div>

            </div>
          </div>
        </div>

      </div>
    </section>

  </main><!-- End #main -->

<%@ include file="../layouts/footer.jsp"%>
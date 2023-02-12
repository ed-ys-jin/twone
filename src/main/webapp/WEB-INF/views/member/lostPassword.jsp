<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="twone" value="${pageContext.request.contextPath }"/>

<%@ include file="../layouts/headForSinglePage.jsp"%>

  <main>
    <div class="container">

      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

              <div class="card mb-3">
                <div class="card-body">

                    <div class="d-flex justify-content-center py-4">
                        <a href="/" class="logo d-flex align-items-center w-auto">
                            <img src="../resources/bootstrap/img/logo_sjb_withback.png" alt="">
                            <span class="d-none d-lg-block">Twone</span>
                        </a>
                    </div><!-- End Logo -->

                  <form class="row g-3" method="post" action="${twone}/sendemailforresetpassword" novalidate>

                    <div class="col-12">
                      <label for="email" class="form-label">이메일</label>
                      <input type="email" name="memEmail" class="form-control" id="email">
                    </div>

                    <div class="col-12">
                      <button class="btn btn-primary w-100" type="submit">비밀번호 재설정</button>
                    </div>

                    <div class="col-12">
                      <p class="small mb-0">비밀번호가 기억나셨나요? <a href="${twone}/login">로그인</a></p>
                    </div>

                  </form>

                </div>
              </div>

              <div class="credits">
                <!-- All the links in the footer should remain intact. -->
                <!-- You can delete the links only if you purchased the pro version. -->
                <!-- Licensing information: https://bootstrapmade.com/license/ -->
                <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
                Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
              </div>

            </div>
          </div>
        </div>

      </section>

    </div>
  </main><!-- End #main -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="../resources/bootstrap/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="../resources/bootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="../resources/bootstrap/vendor/chart.js/chart.umd.js"></script>
  <script src="../resources/bootstrap/vendor/echarts/echarts.min.js"></script>
  <script src="../resources/bootstrap/vendor/quill/quill.min.js"></script>
  <script src="../resources/bootstrap/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="../resources/bootstrap/vendor/tinymce/tinymce.min.js"></script>
  <script src="../resources/bootstrap/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="../resources/bootstrap/js/main.js"></script>

</body>

</html>
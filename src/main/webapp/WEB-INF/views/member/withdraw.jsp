<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="twone" value="${ pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Login - Twone</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="../resources/bootstrap/img/logo_sjb_withback.png" rel="icon">
  <link href="../resources/bootstrap/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="../resources/bootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="../resources/bootstrap/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="../resources/bootstrap/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="../resources/bootstrap/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="../resources/bootstrap/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="../resources/bootstrap/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="../resources/bootstrap/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="../resources/bootstrap/css/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: NiceAdmin - v2.5.0
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

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

                <form class="row g-3" method="post" action="${twone}/withdraw" novalidate>

                  <div class="col-12">
                    <label for="password" class="form-label">비밀번호</label>
                    <input type="password" name="memPw" class="form-control" id="password">
                  </div>

                  <div class="col-12">
                    <button class="btn btn-primary w-100" type="submit">회원탈퇴</button>
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
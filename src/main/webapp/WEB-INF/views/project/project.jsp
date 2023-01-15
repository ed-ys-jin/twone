<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layouts/header.jsp"%>
<c:set var="idx" value="1"/>
  <main id="main" class="main">

    <h5 class="card-title"></h5>

    <div class="pagetitle">
      <h1>프로젝트
        <button type="button" class="btn btn-light" style="float: right;" onclick="location.href='${twone}/project/createForm'">
          <img src="../resources/bootstrap/img/attach-link.png" width="25"/>
          <span>프로젝트 생성</span>
        </button>
      </h1>


    </div><br><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title"></h5>

              <!-- Table with hoverable rows -->
              <table class="table table-hover">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">프로젝트 명</th>
                    <th scope="col">프로젝트 키</th>
                    <th scope="col">프로젝트 리더</th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="plist" items="${plist}">
                    <tr onclick="location.href='${twone}/project/board?projectSeq=${plist.projectSeq}'">
                      <th scope="row">${idx}</th>
                      <td>${plist.projectName}</td>
                      <td>${plist.projectKey}</td>
                      <td>${plist.memName}</td>
                    </tr>
                    <c:set var="idx" value="${idx + 1}"/>
                  </c:forEach>
                </tbody>
              </table>
              <!-- End Table with hoverable rows -->

            </div>
          </div>

        </div>
      </div>
    </section>

  </main><!-- End #main -->

<%@ include file="../layouts/footer.jsp"%>
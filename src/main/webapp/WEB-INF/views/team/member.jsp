<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layouts/header.jsp" %>
<%@ include file="../project/projectsidebar.jsp" %>

<main id="main" class="main">

    <div class="pagetitle">
        <h1>사용자</h1>
        <nav>
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/project">프로젝트</a></li>
                <li class="breadcrumb-item active">사용자</li>
            </ol>
        </nav>
    </div><!-- End Page Title -->

    <div class="search-bar">
        <form class="search-form d-flex align-items-center" method="POST" action="#">
            <input type="text" name="query" placeholder="이메일로 사용자 검색" title="Enter search keyword">
            <button type="submit" title="Search"><i class="bi bi-search member-search"></i></button>
        </form>
    </div><!-- End Search Bar -->

    <section class="section profile">
        <div class="row">
            <c:forEach var="member" items="${teamList}">
                <div class="col-2">

                    <div class="card">
                        <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">

                            <div class="member-info">
                                <img src="${twone}/resources/bootstrap/img/news-3.jpg" alt="Profile"
                                     class="rounded-circle member-img">
                            </div>
                            <div class="name">${member.memName}</div>
                            <div class="position">${member.memPosition}</div>
                            <p>${login}!</p>
                            <c:if test="${login == leader}">
                                <button type="submit" class="btn btn-primary">권한 변경</button>
                            </c:if>
                            <div class="social-links mt-2">
                                <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
                                <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
                                <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
                                <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>

</main>
<!-- End #main -->

<%@ include file="../layouts/footer.jsp" %>
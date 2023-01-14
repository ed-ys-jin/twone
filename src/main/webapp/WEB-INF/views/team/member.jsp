<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../layouts/header.jsp" %>
<%@ include file="../project/projectsidebar.jsp" %>

<script>
    function change_allow(name){
        // confirm(name+"님의 권한을 변경하시겠습니까?");
        let select = confirm(name+"님의 권한을 변경하시겠습니까?");
        console.log(select);
        if(select){
            document.input.submit();
        }else{
            return false;
        }
    }

</script>
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
                        <form name="input" method="post" action="/project/changeAllow">
                            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">

                                <div class="member-info">
                                    <img src="${twone}/resources/bootstrap/img/news-3.jpg" alt="Profile"
                                         class="rounded-circle member-img">
                                </div>
                                <div class="name">${member.memName}</div>
                                <div class="position">${member.memPosition}</div>

    <%--                            <c:if test="${login == leader}">--%>
    <%--                                <div class="allow">--%>
    <%--                                    <c:choose>--%>
    <%--                                        <c:when test="${member.teamAllow == 1}">관리자</c:when>--%>
    <%--                                        <c:when test="${member.teamAllow == 2}">구성원</c:when>--%>
    <%--                                        <c:when test="${member.teamAllow == 3}">조회자</c:when>--%>
    <%--                                    </c:choose>--%>
    <%--                                </div>--%>
    <%--                                <button type="submit" class="btn btn-primary">권한 변경</button>--%>
    <%--                            </c:if>--%>

                                    <div class="form-floating mb-3">
                                        <select class="form-select" id="floatingSelect" name="teamAllow"
                                                                            aria-label="Floating label select example" onchange="change_allow('${member.memName}')">
                                            <option value="1" ${member.teamAllow == 1? 'selected':''}>관리자</option>
                                            <option value="2" ${member.teamAllow == 2? 'selected':''}>구성원</option>
                                            <option value="3" ${member.teamAllow == 3? 'selected':''}>조회자</option>
                                        </select> <label for="floatingSelect">권한</label>
                                    </div>

    <%--                            <button type="submit" class="btn btn-primary" onclick="change_allow">권한 변경</button>--%>
                            </div>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </section>

</main>
<!-- End #main -->

<%@ include file="../layouts/footer.jsp" %>
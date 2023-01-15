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
        <form class="search-form d-flex align-items-center" method="POST" action="/project/memberAdd" onsubmit="return check(this)">
<%--            <input type="hidden" name="projectSeq" value="">--%>
            <input type="text" name="email" placeholder="이메일로 사용자 검색" title="Enter search keyword">
            <button type="submit" title="Search"><i class="bi bi-search member-search"></i></button>
        </form>
    </div><!-- End Search Bar -->

    <section class="section profile">
        <div class="row">
            <c:set var = "idx" value="0"/>
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

                        <div>${member.memSeq}</div>
                        <c:if test="${login == leader}">
                        <c:set var="idx" value="${idx+1}"/>
                        <form id="input${idx}" method="post" action="/project/changeAllow">
                            <input type="hidden" value="${member.memSeq}" name="memSeq">
                            <div class="form-floating mb-3">
                                <select class="form-select" id="floatingSelect" name="allow"
                                                                    aria-label="Floating label select example" onchange="change_allow('${member.memName}',${idx})">
                                    <option value="1" ${member.teamAllow == 1? 'selected':''}>관리자</option>
                                    <option value="2" ${member.teamAllow == 2? 'selected':''}>구성원</option>
                                    <option value="3" ${member.teamAllow == 3? 'selected':''}>조회자</option>
                                </select> <label for="floatingSelect">권한</label>
                            </div>
                        </form>
                        </c:if>

                    </div>
                </div>

            </div>
            </c:forEach>
        </div>
    </section>

</main>
<!-- End #main -->

<script>
    <%-- 권한 변경 --%>
    function change_allow(name,idx){
        let select = confirm(name+"님의 권한을 변경하시겠습니까?");

        if(select){
            document.getElementById("input"+idx).submit();
        }else{
            return location.href= "/project/team";
        }
    }

    function check(f){
        if(f.email.value == ""){
            alert("추가할 사용자의 이메일 적어주세요");
            f.email.focus();
            return false;
        }
        return true;
    }
</script>
<%@ include file="../layouts/footer.jsp" %>
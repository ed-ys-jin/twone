<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="twone" value="${pageContext.request.contextPath }"/>

<style>
/*
#사용자 input 추가
*/
    .search-form input {
        border: 0;
        font-size: 14px;
        color: #012970;
        border: 1px solid rgba(1, 41, 112, 0.2);
        padding: 7px 38px 7px 8px;
        border-radius: 3px;
        transition: 0.3s;
        width: 25%;
    }

    .bi::before, [class^="bi-"]::before, [class*=" bi-"]::before {
        display: inline-block;
        font-family: bootstrap-icons !important;
        font-style: normal;
        font-weight: normal !important;
        font-variant: normal;
        text-transform: none;
        line-height: 1;
        vertical-align: -.125em;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        color: #012970;

    }
    .search-form button {
        border: 0;
        padding: 0;
        margin-left: -30px;
        background: none;
    }

    .bi-search::before {
        content: "\f52a";
    }

    .search-bar{
        padding-bottom: 30px;
    }

    .member-info{
        padding-bottom: 10px;
    }

    .teamtitle{
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: space-between;
    }

    .delete-bnt{
        height: 38px;
    }

    .x{
        padding-left: 150px;
    }

    .form-select{
        height: 42px !important;
        margin: 0px !important;
        padding: 7px 0 7px 7px !important;
        width: 86px !important;
    }

    #manager, #member, #reder{
        display: flex;
        flex-wrap: wrap;
        justify-content: flex-start;
    }
    .line{
        padding: 18px;
        border-bottom: 1px solid #cddfff;
    }
    .line-end{
        padding: 10px;
    }

    .allowTitle{
        padding-bottom: 10px;
    }
    .position{
        padding-bottom: 5px;
    }

    .team-card{
        max-width: 200px;
        min-width: 200px;
    }
    .card-wrrap{
        width: 240px;
        min-width: 240px;
    }

    .test-card-body{
        padding-top: 12px;
    }

    .team-search{
        padding-bottom: 0;
    }

    .team-filter{
        padding-left: 139px !important;
    }
</style>

<%@ include file="../layouts/header.jsp" %>
<%@ include file="../project/projectsidebar.jsp" %>


<main id="main" class="main">
    <h5 class="card-title"></h5>

    <div class="pagetitle teamtitle">
        <div>
            <h1>사용자</h1>
            <nav style="--bs-breadcrumb-divider: '>';">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/project">프로젝트</a></li>
                    <li class="breadcrumb-item active">사용자</li>
                </ol>
            </nav>
        </div>
    </div><!-- End Page Title -->

    <div class="search-bar team-search">
        <form class="search-form d-flex align-items-center" method="POST" action="/project/memberAdd"
              onsubmit="return check(this)">
            <input type="hidden" name="projectSeq" value="${dto.project_seq}">
            <input type="text" name="email" placeholder="email" title="Enter search keyword">
            <button type="submit" title="Search"><i class="bi bi-person-plus-fill member-search"></i></button>
        </form>
    </div><!-- End Search Bar -->

    <section class="section profile">
        <div class="row">
            <div class="line">
                <div class="allowTitle">관리자</div>
                <div id="manager"></div>
            </div>
            <div class="line">
                <div class="allowTitle">구성원</div>
                <div id="member"></div>
            </div>
            <div class="line-end">
                <div class="allowTitle">조회자</div>
                <div id="reader"></div>
            </div>
        </div>
    </section>

</main>
<!-- End #main -->
<script>
    // 검색폼 유효성 검사
    function check(f) {
        const emailCheck = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
        let email = f.email;

        if (email.value == "") {
            alert("추가할 사용자의 이메일 적어주세요.");
            email.focus();
            return false;
        } else if (!emailCheck.test(email.value)) {
            alert("이메일을 형식을 확인해주세요.");
            email.focus();
            return false;
        }
        return true;
    }

    // 다른 사용자 삭제
    function deleteTeam(mSeq, pSeq) {
        let check = confirm("사용자를 삭제하시겠습니까?");
        if (!check) { return; }
        location.href = "/project/deleteMember?projectSeq=" + pSeq + "&memberSeq=" + mSeq;
    }

    // 로그인세션 팀 탈퇴
    function Withdrawal(mSeq, pSeq) {
        let check = confirm("팀을 탈퇴하시겠습니까?");
        if (check) {
            const allowList = ${allowList};
            const result = allowList.filter(allow => allow == 1);
            if (result.length == 1) {
                alert("최소 한명이상이 관리자 권한이어야 합니다.");
                return;
            }
        }else{
            return;
        }
        location.href = "/project/Withdrawal?projectSeq=" + pSeq + "&memberSeq=" + mSeq;
    }

    <%-- 권한 변경 --%>
    function change_allow(name, idx, memSeq, pSeq) {
        const allowList = ${allowList};
        // 본인 권한 수정일 때
        if (memSeq == ${login}) {
            const result = allowList.filter(allow => allow == 1);
            if (result.length == 1) {
                alert("최소 한명이상이 관리자 권한이어야 합니다.");
                return location.href = "/project/team?projectSeq=" + pSeq;
                alert("end");
            }
        }
        let select = confirm(name + "님의 권한을 변경하시겠습니까?");
        if (select) {
            document.getElementById("input" + idx).submit();
        } else {
            return location.href = "/project/team?projectSeq=" + pSeq;
        }
    }
</script>
<script >
    // view 정렬을 위해 페이지가 로드되자마자 실행
    window.onload = function () {
        let teamList = ${teamList};
        const leader = ${leader};
        const dto = ${dto};
        const login = ${login};

        const manager = document.getElementById("manager");
        const memberd = document.getElementById("member");
        const reader = document.getElementById("reader");
        let idx = 0;


        teamList.forEach(member => {

            let tag = '<div class="col-2 card-wrrap"> <div class="card team-card" style="';
            if (leader == member.mem_seq) {
                tag += 'border: 3px solid #0d6efd3b;';
            }
            tag +='"><div class="card-body test-card-body profile-card pt-4 d-flex flex-column align-items-center">';
            if(dto.team_allow == 1 || login == member.mem_seq){ // 로그인세션의 팀 권한이 조회자가 아니면 보이게끔
                tag += ' <div class="filter team-filter"><a class="icon" href="#" data-bs-toggle="dropdown"><i class="bi bi-three-dots"></i></a> <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">';

                if(login == member.mem_seq){
                    tag += '<li><a class="dropdown-item" onclick="Withdrawal(' +login+','+ dto.project_seq + ')">팀 탈퇴</a></li>';
                }else{
                    tag += '<li><a class="dropdown-item" onclick="deleteTeam(' + member.mem_seq + "," + dto.project_seq + ')">사용자 삭제</a></li>';
                }
                tag += '</ul></div>';
            }
            tag += ' <div class="member-info"> <img alt="Profile" class="rounded-circle member-img" src="';
            if (member.mem_image == null) {
                tag += '../resources/bootstrap/img/no_image.png">';
            } else {
                tag += member.mem_image + '\">';
            }
            tag += '</div> <div class="name">' + member.mem_name + '</div><div class="position">';
            if (!member.mem_position =="") {
                tag += member.mem_position;
            }
            tag += '</div>';
            if (dto.team_allow == 1) { // 권한 변경
                idx++;
                tag += '<form  class="team-from" id="input' + idx + '" method="post" action="/project/changeAllow">'
                    + '<input type="hidden" value="' + member.mem_seq + '" name="memSeq">'
                    + '<input type="hidden" name="projectSeq" value="' + dto.project_seq + '"> <div class="form-floating mb-3" >' +
                    '<select  class="form-select" id="floatingSelect" name="allow" aria-label="Floating label select example" onchange="change_allow(' +
                    "'" + member.mem_name + '\',' + idx + ',' + member.mem_seq + ','+ dto.project_seq +')"> <option value="1"';

                tag += member.team_allow == 1 ? "selected" : "";
                tag += '>관리자</option> <option value="2"';
                tag += member.team_allow == 2 ? "selected" : "";
                tag += '>구성원</option> <option value="3"';
                tag += member.team_allow == 3 ? "selected" : "";
                tag += '>조회자</option></select></div></form>';
            }
            tag += '</div></div></div>';
            if (member.team_allow == 1) {
                manager.insertAdjacentHTML('beforeend', tag);
            } else if (member.team_allow == 2) {
                memberd.insertAdjacentHTML('beforeend', tag);
            } else if (member.team_allow == 3) {
                reader.insertAdjacentHTML('beforeend', tag);
            }
        })
    }
</script>
<%@ include file="../layouts/footer.jsp" %>
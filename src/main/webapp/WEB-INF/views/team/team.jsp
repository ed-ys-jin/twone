<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ include file="../layouts/header.jsp" %>
<%@ include file="../project/projectsidebar.jsp" %>


<main id="main" class="main">

    <div class="pagetitle teamtitle">
        <div>
            <h1>사용자</h1>
            <nav>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/project">프로젝트</a></li>
                    <li class="breadcrumb-item active">사용자</li>
                </ol>
            </nav>
        </div>
        <button type="button" class="btn btn-primary delete-bnt" onclick="Withdrawal(${login},${dto.projectSeq})">팀 탈퇴
        </button>
    </div><!-- End Page Title -->

    <div class="search-bar">
        <form class="search-form d-flex align-items-center" method="POST" action="/project/memberAdd"
              onsubmit="return check(this)">
            <input type="hidden" name="projectSeq" value="${dto.project_seq}">
            <input type="text" name="email" placeholder="이메일로 사용자 추가" title="Enter search keyword">
            <button type="submit" title="Search"><i class="bi bi-person-plus-fill member-search"></i></button>
        </form>
    </div><!-- End Search Bar -->

    <section class="section profile">
        <div class="row">
            <div class="line">
                <div class="allowTitle">관리자</div>
                <div id="manager"></div>
            </div>
<%--            <hr style="border: 1px solid #8eb3f5f5;">--%>
            <div class="line">
                <div class="allowTitle">구성원</div>
                <div id="member"></div>
            </div>
<%--            <hr style="border: 1px solid #8eb3f5f5;">--%>
            <div class="line-end">
                <div class="allowTitle">조회자</div>
                <div id="reader"></div>
            </div>
            <%--            <c:set var = "idx" value="0"/>--%>
            <%--            <c:forEach var="member" items="${teamList}">--%>
            <%--            <div class="col-2" >--%>
            <%--                <div class="card" style="${leader == member.memSeq? 'border: 3px solid #0d6efd3b;':''}">--%>

            <%--                    <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">--%>
            <%--                        <c:if test="${(dto.teamAllow ==1) && login != member.memSeq}">--%>
            <%--                            <span class="x" onclick="deleteTeam(${member.memSeq},${dto.projectSeq})"><i class="bi bi-person-dash-fill"></i></span>--%>
            <%--                            <span class="clean"></span>--%>
            <%--                        </c:if>--%>
            <%--                        <div class="member-info">--%>
            <%--                            <c:choose>--%>
            <%--                                <c:when test="${member.memImage == null}">--%>
            <%--                                    <img src="${twone}/resources/bootstrap/img/no_image.png" alt="Profile" class="rounded-circle member-img">--%>
            <%--                                </c:when>--%>
            <%--                                <c:otherwise>--%>
            <%--                                         <img src="${twone}/resources/bootstrap/img/news-3.jpg" alt="Profile"--%>
            <%--                                         class="rounded-circle member-img">--%>

            <%--                                </c:otherwise>--%>
            <%--                            </c:choose>--%>
            <%--&lt;%&ndash;                            <img src="${member.memImage == ""? [twone]+'/resources/bootstrap/img/no_image.png' : [twone]+'/resources/bootstrap/img/news-3.jpg'}" alt="Profile"&ndash;%&gt;--%>
            <%--&lt;%&ndash;                                 class="rounded-circle member-img">&ndash;%&gt;--%>
            <%--                        </div>--%>
            <%--                        <div class="name">${member.memName}</div>--%>
            <%--                        <div class="position">${member.memPosition}</div>--%>

            <%--                        <c:if test="${dto.teamAllow == 1}">--%>
            <%--                            <c:set var="idx" value="${idx+1}"/>--%>
            <%--                            <form class="team-from" id="input${idx}" method="post" action="/project/changeAllow">--%>
            <%--                                <input type="hidden" value="${member.memSeq}" name="memSeq">--%>
            <%--                                <input type="hidden" name="projectSeq" value="${dto.projectSeq}">--%>
            <%--                                <div class="form-floating mb-3">--%>
            <%--                                    <select class="form-select" id="floatingSelect" name="allow"--%>
            <%--                                                                        aria-label="Floating label select example" onchange="change_allow('${member.memName}',${idx},${member.memSeq})">--%>
            <%--                                        <option value="1" ${member.teamAllow == 1? 'selected':''}>관리자</option>--%>
            <%--                                        <option value="2" ${member.teamAllow == 2? 'selected':''}>구성원</option>--%>
            <%--                                        <option value="3" ${member.teamAllow == 3? 'selected':''}>조회자</option>--%>
            <%--                                    </select> <label for="floatingSelect">권한</label>--%>
            <%--                                </div>--%>
            <%--                            </form>--%>
            <%--                        </c:if>--%>

            <%--                    </div>--%>
            <%--                </div>--%>

            <%--            </div>--%>
            <%--            </c:forEach>--%>
        </div>
    </section>

</main>
<!-- End #main -->
<script>
    <%-- 권한 변경 --%>

    function change_allow(name, idx, memSeq) {
        const allowList = ${allowList};
        // 본인 권한 수정일 때
        if (memSeq == ${login}) {
            const result = allowList.filter(allow => allow == 1);
            if (result.length == 1) {
                alert("최소 한명이상이 관리자 권한이어야 합니다.");
                return location.href = "/project/team";
            }
        }
        let select = confirm(name + "님의 권한을 변경하시겠습니까?");
        if (select) {
            document.getElementById("input" + idx).submit();
        } else {
            return location.href = "/project/team";
        }
    }

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
        if (!check) {
            return;
        }
        location.href = "/project/deleteMember?projectSeq=" + pSeq + "&memberSeq=" + mSeq;
    }

    // 로그인세션 팀 탈퇴
    function Withdrawal(mSeq, pSeq) {
        let check = confirm("팀을 탈퇴하시겠습니까?");
        if (!check) {
            return;
        }
        location.href = "/project/Withdrawal?projectSeq=" + pSeq + "&memberSeq=" + mSeq;
    }
</script>
<script language="JavaScript">
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
            console.log(member.mem_position);

            let tag = '<div class="col-2 card-wrrap"> <div class="card team-card" style="';
            console.log(${leder == dto.memSeq});
            if (leader == member.mem_seq) {
                tag += 'border: 3px solid #0d6efd3b;';
            }
            tag += '"> <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">';

            if (dto.team_allow == 1 && login != member.mem_seq) {
                tag += '<span class="x" onclick="deleteTeam(' + member.mem_seq + "," + dto.project_seq + ')"><i class="bi bi-person-dash-fill"></i></span>';
            }
            tag += '<div class="member-info"> <img alt="Profile" class="rounded-circle member-img" src="';
            if (member.mem_image == null) {
                tag += 'pageContext.request.contextPath/resources/bootstrap/img/no_image.png">';
            } else {
                tag += 'pageContext.request.contextPath/resources/bootstrap/img/' + member.mem_image + '">';
            }
            tag += '</div> <div class="name">' + member.mem_name + '</div><div class="position">';
            if (!member.mem_position =="") {
                tag += member.mem_position;
            }
            tag += '</div>';
            if (dto.team_allow == 1) {
                idx++;
                tag += '<form  class="team-from" id="input' + idx + '" method="post" action="/project/changeAllow">'
                    + '<input type="hidden" value="' + member.mem_seq + '" name="memSeq">'
                    + '<input type="hidden" name="projectSeq" value="' + dto.project_seq + '"> <div class="form-floating mb-3" >' +
                    '<select  class="form-select" id="floatingSelect" name="allow" aria-label="Floating label select example" onchange="change_allow(' +
                    "'" + member.mem_name + '\',' + idx + ',' + member.mem_seq + ')"> <option value="1"';

                tag += member.team_allow == 1 ? "selected" : "";
                tag += '>관리자</option> <option value="2"';
                tag += member.team_allow == 2 ? "selected" : "";
                tag += '>구성원</option> <option value="3"';
                tag += member.team_allow == 3 ? "selected" : "";
                tag += '>조회자</option></select></div></form></div></div></div>';

            }
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
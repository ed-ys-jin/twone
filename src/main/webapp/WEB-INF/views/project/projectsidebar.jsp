<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ======= Sidebar ======= -->
<aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

        <li class="nav-heading">프로젝트</li>

        <li class="nav-item">
            <c:choose>
                <c:when test="${navtype == project}">
                    <a class="nav-link" href="index.html">
                        <i class="bi bi-grid"></i>
                        <span>${pdto.projectName}</span>
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="nav-link collapse" href="index.html">
                        <i class="bi bi-grid"></i>
                        <span>${pdto.projectName}</span>
                    </a>
                </c:otherwise>
            </c:choose>

        </li><!-- End Dashboard Nav -->

        <h5 class="card-title"></h5>
        <li class="nav-heading">보드</li>

        <li class="nav-item">
            <a class="nav-link collapsed" data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
                <i class="bi bi-menu-button-wide"></i><span>보드</span><i class="bi bi-chevron-down ms-auto"></i>
            </a>
            <ul id="components-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
                <li>
                    <a href="../../../../../../../../../Downloads/NiceAdmin/components-alerts.html">
                        <i class="bi bi-circle"></i><span>보드 1</span>
                    </a>
                </li>
                <li>
                    <a href="../../../../../../../../../Downloads/NiceAdmin/components-accordion.html">
                        <i class="bi bi-circle"></i><span>보드 2</span>
                    </a>
                </li>
                <li>
                    <a href="../../../../../../../../../Downloads/NiceAdmin/components-badges.html">
                        <i class="bi bi-circle"></i><span>보드 3</span>
                    </a>
                </li>
                <li>
                    <a href="../../../../../../../../../Downloads/NiceAdmin/components-breadcrumbs.html">
                        <i class="bi bi-circle"></i><span>보드 4</span>
                    </a>
                </li>
                <li>
                    <a href="../../../../../../../../../Downloads/NiceAdmin/components-buttons.html">
                        <i class="bi bi-circle"></i><span>보드 5</span>
                    </a>
                </li>
                <li>
                    <a href="../../../../../../../../../Downloads/NiceAdmin/components-cards.html">
                        <i class="bi bi-circle"></i><span>보드 6</span>
                    </a>
                </li>
            </ul>
        </li><!-- End Components Nav -->

        <h5 class="card-title"></h5>
        <li class="nav-heading">사용자</li>
<%--        <c:if test=""></c:if>--%>
        <li class="nav-item">
            <c:choose>
                <c:when test="${navType == team}">
                    <a class="nav-link" href="${twone}/project/team">
                        <i class="bi bi-person"></i>
                        <span>사용자 관리</span>
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="nav-link collapse" href="${twone}/project/team">
                        <i class="bi bi-person"></i>
                        <span>사용자 관리</span>
                    </a>
                </c:otherwise>
            </c:choose>

        </li>




        <h5 class="card-title"></h5>
        <li class="nav-heading">설정</li>


        <li class="nav-item">
            <c:choose>
                <c:when test="${navType == setting}">
                    <a class="nav-link" href="${twone}/project/setting?projectSeq=${pdto.projectSeq}">
                        <i class="bi bi-person"></i>
                        <span>프로젝트 설정</span>
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="nav-link collapsed" href="${twone}/project/setting?projectSeq=${pdto.projectSeq}">
                        <i class="bi bi-person"></i>
                        <span>프로젝트 설정</span>
                    </a>
                </c:otherwise>
            </c:choose>

        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="../../../../../../../../../Downloads/NiceAdmin/users-profile.html">
                <i class="bi bi-person"></i>
                <span>엑세스</span>
            </a>
        </li>
        <!-- End Profile Page Nav -->


    </ul>

</aside><!-- End Sidebar-->
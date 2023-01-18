<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../layouts/header.jsp"%>
<%@ include file="../project/projectsidebar.jsp"%>

  <main id="main" class="main">

    <div class="pagetitle">
      <h1></h1>
    </div><!-- End Page Title -->

    <form>
      <section class="section">
        <div class="row">

          <!-- Summary Card -->
          <div class="col-lg-6">
            <div class="card">
              <div class="card-body">
                <div class="pagetitle">
                  <h5 class="card-title"></h5>
                  <h1>Issue Title</h1>
                </div><!-- End Summary Card Title -->

                <div class="row mb-3">
                  <div class="col-sm-10">

                    <!-- 파일 첨부 -->
                    <button type="button" class="btn btn-light">
                      <img src="../resources/bootstrap/img/attach-clip.png" width="25"/>
                      <span>첨부</span>
                    </button>
                    &nbsp;
                    <!-- 이슈 연결 -->
                    <button type="button" class="btn btn-light">
                      <img src="../resources/bootstrap/img/attach-link.png" width="25"/>
                      <span>이슈 연결</span>
                    </button>

                  </div>
                </div>

                <!-- 설명 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">설명</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control">
                  </div>
                </div>

                <!-- 레이블 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">레이블</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control">
                  </div>
                </div>

              </div>
            </div><!-- End Summary Card -->

            <!-- Comment Card -->
            <div class="card">
              <div class="card-body">
                <h5 class="card-title"></h5>

                <!-- 댓글 추가 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">활동</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="댓글 추가...">
                  </div>
                </div>

                <!-- 댓글 -->
                <div class="row mb-3">
                  <div class="col-sm-10">
                    <img src="../resources/bootstrap/img/profile-img.jpg" alt="Profile" width="50">
                    <p>작성자 | 작성일시<br>내용</p>
                  </div>
                </div>

              </div>
            </div>
          </div><!-- End Comment Card -->

          <!-- Detail Card -->
          <div class="col-lg-6">
            <div class="card">
              <div class="card-body">
                <div class="pagetitle">
                  <h5 class="card-title"></h5>
                  <h5>세부 정보</h5>
                </div><!-- End Card Title -->

                <!-- 담당자 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">담당자</label>
                  <div class="col-sm-10">
                    <select class="form-select" aria-label="Default select example">
                      <option value="진윤석" selected>진윤석</option>
                      <option value="신성주">신성주</option>
                      <option value="봉지원">봉지원</option>
                    </select>
                  </div>
                </div>

                <!-- 날짜 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">날짜</label>
                  <div class="col-sm-10">
                    <input type="date" class="form-control">
                  </div>
                </div>

                <!-- 우선 순위 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">우선 순위</label>
                  <div class="col-sm-10">
                    <select class="form-select" aria-label="Default select example">
                      <option value="highest">Highest</option>
                      <option value="high">High</option>
                      <option value="medium" selected>Medium</option>
                      <option value="low">Low</option>
                      <option value="lowest">Lowest</option>
                    </select>
                  </div>
                </div>

                <!-- 체크박스 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">체크박스</label>
                  <div class="col-sm-10">

                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="gridCheck1">
                      <label class="form-check-label" for="gridCheck1">
                        Example checkbox 1
                      </label>
                    </div>

                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="gridCheck2">
                      <label class="form-check-label" for="gridCheck2">
                        Example checkbox 2
                      </label>
                    </div>

                  </div>
                </div>

                <!-- 드롭다운 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">드롭다운</label>
                  <div class="col-sm-10">
                    <select class="form-select" aria-label="Default select example">
                      <option selected>없음</option>
                      <option value="1">Example dropbox 1</option>
                      <option value="2">Example dropbox 2</option>
                    </select>
                  </div>
                </div>

                <!-- 간단한 텍스트 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">간단한 텍스트</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control">
                  </div>
                </div>

                <!-- 단락 -->
                <div class="row mb-3">
                  <label class="col-sm-2 col-form-label">단락</label>
                  <div class="col-sm-10">
                    <div class="quill-editor-default"></div>
                  </div>
                </div><br><br>

              </div>
            </div>
          </div><!-- End Detail Card -->

        </div>
      </section>
    </form><!-- End General Form Elements -->

  </main><!-- End #main -->

<%@ include file="../layouts/footer.jsp"%>
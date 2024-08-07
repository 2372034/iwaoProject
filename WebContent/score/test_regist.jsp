<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<%@ include file="../header.jsp" %>
<div class="container">
    <%@ include file="menu.jsp" %>
    <div class="subject_content">
        <h2>成績管理</h2>

        <form method="post" action="TestRegist.action">
            <div class="student_list_search" id="filter">
                <!-- 入学年度のセレクトボックス -->
                <div class="col-4">
                    <label>入学年度</label>
                    <select class="form-select" id="test_regist-entYearSet-select" name="f1">
                        <option value="0">--------</option>
                        <c:forEach var="year" items="${ent_year_set}">
                            <option value="${year}" <c:if test="${year == param.f1}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- クラスのセレクトボックス -->
                <div class="col-4">
                    <label class="form-label" for="test_regist-classes-select">クラス</label>
                    <select class="form-select" id="test_regist-classes-select" name="f2">
                        <option value="0">--------</option>
                        <c:forEach var="num" items="${class_num_set}">
                            <option value="${num}" <c:if test="${num == param.f2}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- 科目のセレクトボックス -->
                <div class="col-4">
                    <label class="form-label" for="test_regist-subjects-select">科目</label>
                    <select class="form-select" id="test_regist-subjects-select" name="f3">
                        <option value="0">--------</option>
                        <c:forEach var="subject" items="${subject_set}">
                            <option value="${subject.cd}" <c:if test="${subject.cd == param.f3}">selected</c:if>>${subject.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- 回数のセレクトボックス -->
                <div class="col-4">
                    <label class="form-label" for="test_regist-testNumSet-select">回数</label>
                    <select class="form-select" id="test_regist-testNumSet-select" name="f4">
                        <option value="0">--------</option>
                        <option value="1" <c:if test="${1 == param.f4}">selected</c:if>>1</option>
                        <option value="2" <c:if test="${2 == param.f4}">selected</c:if>>2</option>
                        <c:forEach var="num" items="${test_num_set}">
                            <option value="${num}" <c:if test="${num == param.f4}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- 検索ボタン -->
                <div class="col-2_button_TestList">
                    <button class="btn-grades" id="filter-button">検索</button>
                </div>
            </div>
        </form>

        <c:choose>
            <c:when test="${not empty students}">
                <form method="post" action="TestRegistExecute.action">
                    <input type="hidden" name="f1" value="${param.f1}">
                    <input type="hidden" name="f2" value="${param.f2}">
                    <input type="hidden" name="f3" value="${param.f3}">
                    <input type="hidden" name="f4" value="${param.f4}">
                    <c:set var="subjectName" value="${students[0].subject.name}" />
                    <p>科目：${subjectName}（${f4}回）</p>
                    <c:if test="${not empty error}">
						<div class="error-message">${error}</div>
					</c:if>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>点数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="test" items="${students}">
                                <tr>
                                    <td>${test.student.entYear}</td>
                                    <td>${test.classNum}</td>
                                    <td>${test.student.no}</td>
                                    <td>${test.student.name}</td>
                                    <td>
                                    <input type="text" name="score_${test.student.no}" value="${test.point}" />
                                    </td>
                                    <c:if test="${not empty error}">
                                        <td class="error-message">${error}</td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="submit-section">
                        <button type="submit" class="btn btn-primary">登録して終了</button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <p>該当する成績データがありません。</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@ include file="../footer.jsp" %>

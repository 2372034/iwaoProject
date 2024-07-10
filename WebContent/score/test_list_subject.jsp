<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<%@include file="../header.jsp" %>
<div class="container">
<%@include file="menu.jsp" %>
<div class="test_list_content">
    <h2>成績参照</h2>
    <div class="test_search_border">
        <form method="post" action="TestListSubjectExecute.action">
            <div class="test_list_search" id="filter">
                <div class="col-2_test_list">科目情報</div>
                <div class="col-4_entYearSet">
                    <label>入学年度</label>
                    <select class="form-select" id="test_list-entYearSet-select" name="entYearSet">
                        <option value="0">--------</option>
                        <c:forEach var="year" items="${entYearSet}">
                            <option value="${year}" <c:if test="${year == selectedYear}">selected</c:if>>${year}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-4_classes">
                    <label>クラス</label>
                    <select class="form-select" id="test_list-classes-select" name="classes">
                        <option value="0">--------</option>
                        <c:forEach var="num" items="${classes}">
                            <option value="${num}" <c:if test="${num == selectedClass}">selected</c:if>>${num}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-4_subjects">
                    <label>科目</label>
                    <select class="form-select" id="test_list-subjects-select" name="subjects">
                        <option value="0">--------</option>
                        <c:forEach var="subject" items="${subjects}">
                            <option value="${subject.cd}" <c:if test="${subject.cd == selectedSubject}">selected</c:if>>${subject.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-2_button_TestList text-center">
                    <button class="btn btn-secondary" id="filter-button-1">検索</button>
                </div>
                <div class="mt-2 text-warning">${errors.get("entYearSet")}</div>
            </div>
        </form>

        <form method="get" action="TestListStudentExecute.action">
            <div class="test_list_student">
                <div class="col-2_test_list">学生番号</div>
                <div class="col-4_studentNum">
                    <label>学生番号</label>
                    <input type="text" name="studentNum" placeholder="学生番号を入力してください" value="${studentNum}">
                </div>
                <div class="col-2_button_TestList text-center">
                    <button class="btn btn-secondary" id="filter-button-2">検索</button>
                </div>
            </div>
        </form>
    </div>
    <c:choose>
        <c:when test="${not empty selectedYear and not empty selectedClass and not empty selectedSubject}">
            <!-- クラスと科目が入力されている場合の処理 -->
            <c:set var="selectedSubjectName" value="" />
            <c:forEach var="subject" items="${subjects}">
                <c:if test="${subject.cd == selectedSubject}">
                    <c:set var="selectedSubjectName" value="${subject.name}" />
                </c:if>
            </c:forEach>
            <c:choose>
                <c:when test="${empty testListSubjects}">
                    <div class="alert alert-warning">学生情報が存在しませんでした</div>
                </c:when>
                <c:otherwise>
                    <div>科目：${selectedSubjectName}</div>
                    <table class="subject_list">
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>1回</th>
                            <th>2回</th>
                        </tr>
                        <c:forEach var="TestListSubject" items="${testListSubjects}">
                            <tr>
                                <td>${TestListSubject.entYear}</td>
                                <td>${TestListSubject.classNum}</td>
                                <td>${TestListSubject.studentNo}</td>
                                <td>${TestListSubject.studentName}</td>
                                <c:forEach var="entry" items="${TestListSubject.points}">
                                    <c:choose>
                                        <c:when test="${entry.value == -1 || entry.value == null}">
                                            <td>-</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>${entry.value}</td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <!-- 入学年度、クラスまたは科目が入力されていない場合の処理 -->
            <div class="test_list_search">
                <p>入学年度とクラスと科目を入力してください</p>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</div>
<%@include file="../footer.jsp" %>
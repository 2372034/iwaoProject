<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<%@ include file="../header.jsp"%>
<div class="container">
    <%@ include file="menu.jsp"%>
    <div class="test_list_content">
        <h2>成績参照</h2>
        <div class="test_search_border">
            <form method="get" action="TestListStudentExecute.action">
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
                        <button class="btn btn-secondary" id="filter-button">検索</button>
                    </div>
                    <div class="mt-2 text-warning">${errors.get("entYearSet")}</div>
                </div>

                <div class="test_list_student">
                    <div class="col-2_test_list">学生番号</div>
                    <div class="col-4_studentNum">
                        <label>学生番号</label>
                        <input type="text" name="studentNum" placeholder="学生番号を入力してください" value="${not empty param.studentNum ? param.studentNum : ''}">
                    </div>
                    <div class="col-2_button_TestList text-center">
                        <button class="btn btn-secondary" id="filter-button">検索</button>
                    </div>
                </div>
            </form>
        </div>
        <c:choose>
            <c:when test="${not empty error}">
                <div>${error}</div>
            </c:when>
            <c:when test="${not empty error2}">
				<div>${error2}</div>
				<div>成績情報が存在しませんでした</div>
            </c:when>
            <c:when test="${not empty students}">
                <div>検索結果: ${students.size()}件</div>
                <table class="table table-hover">
                    <thead>
  	            <thead>
	                <tr>
	                    <th>科目名</th>
	                    <th>科目コード</th>
	                    <th>回数</th>
	                    <th>点数</th>
	                </tr>
	            </thead>
	            <tbody>
	                <c:forEach var="student" items="${students}">
	                    <tr>
	                        <td>${student.subjectName}</td>
	                        <td>${student.subjectCd}</td>
	                        <td>${student.num}</td>
	                        <td>${student.point}</td>
	                    </tr>
	                </c:forEach>
                    </tbody>
                </table>
            </c:when>
        </c:choose>
    </div>
</div>
<%@ include file="../footer.jsp"%>

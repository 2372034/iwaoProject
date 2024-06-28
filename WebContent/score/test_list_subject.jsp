<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--  原因 -->
<link rel="stylesheet" type="text/css" href="../css/style.css">
<%@include file="../header.jsp" %><!--  原因 -->
<div class="container">
<%@include file="menu.jsp" %><!--  原因 -->
<div class="test_list_content">
	<h2>成績参照</h2>
	<div class="test_search_border">
		<form method="get">
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
					<input type="text" name="studentNum" placeholder="学生番号を入力してください" value="${studentNum}">
				</div>
				<div class="col-2_button_TestList text-center">
					<button class="btn btn-secondary" id="filter-button">検索</button>
				</div>
			</div>
		</form>
	</div>
    <c:choose>
		<c:when test="${not empty students}">
        <div>検索結果:${students.size()}件</div>
        <table class="table table-hover">
            <tr>
                <th>入学年度</th>
                <th>クラス</th>
                <th>科目</th>
                <th>氏名</th>
                <th>1回</th>
                <th>2回</th>
            </tr>
            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.entYear}</td>
                    <td>${student.classNum}</td>
                    <td>${student.subject}</td>
                    <td>${student.name}</td>
                    <td>${student.score1}</td>
                    <td>${student.score2}</td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
		<c:otherwise>
			<div>学生情報が存在しませんでした</div>
		</c:otherwise>
	</c:choose>
</div>
</div>
<%@include file="../footer.jsp" %>
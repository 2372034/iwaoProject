<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<%@include file="../header.jsp" %>
<div class="container">
<%@include file="menu.jsp" %>
<div class="subject_content">
	<h2>成績管理</h2>
	<c:choose>
		<c:when test="${students.size() > 0}">
		<table class="table table-hover">
			<tr>
				<th>入学年度</th>
				<th>クラス</th>
				<th>科目</th>
				<th>回数</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="student" items="${students}">
				<tr>
					<td>${student.entYear}</td>
					<td>${student.no}</td>
					<td>${student.name}</td>
					<td>${student.classNum}</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
</c:choose>
	<form method="get">
		<div class="test_list_search" id="filter">
			<div class="col-4">
				<label>入学年度</label>
				<select class="form-select" id="test_list-entYearSet-select" name="entYearSet">
					<option value="0">--------</option>
					<c:forEach var="year" items="${entYearSet}">
						<option value="${year}" <c:if test="${year == entYearSet}">selected</c:if>>${year}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-4">
				<label class="form-label" for="test_list-classes-select">クラス</label>
				<select class="form-select" id="test_list-classes-select" name="classes">
					<option value="0">--------</option>
					<c:forEach var="num" items="${classes}">
						<option value="${num}" <c:if test="${num == classes}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-4">
				<label class="form-label" for="test_list-subjects-select">科目</label>
				<select class="form-select" id="test_list-subjects-select" name="subjects">
					<option value="0">--------</option>
					<c:forEach var="num" items="${subjects}">
						<option value="${subject.cd}" <c:if test="${subject.cd == subjects}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-4">
				<label class="form-label" for="test_list-testNumSet-select">回数</label>
				<select class="form-select" id="test_list-etestNumSet-select" name="testNumSet">
					<option value="0">--------</option>
					<c:forEach var="num" items="${testNumSet}">
						<option value="${num}" <c:if test="${num == testNumSet}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-2 text-center">
				<button class="btn btn-secondary" id="filter-button">検索</button>
			</div>
			<div class="mt-2 text-warning">${errors.get("f1")}</div>
		</div>
	</form>
			
</div>
</div>
<%@include file="../footer.jsp" %>

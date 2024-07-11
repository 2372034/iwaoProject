<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<%@include file="../header.jsp" %>
<div class="container">
<%@include file="menu.jsp" %>
<div class="student_list_content">
	<h2>学生管理</h2>
	<div class="new_create">
		<a href="StudentCreate.action">新規登録</a>
	</div>
	<form method="get">
	<p>${errors.get("f1")}</p>
		<div class="student_list_search" id="filter">
			<div class="col-4">
				<label>入学年度</label>
				<select class="form-select" id="student-f1-select" name="f1">
					<option value="0">--------</option>
					<c:forEach var="year" items="${ent_year_set}">
						<option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-4">
				<label class="form-label" for="student-f2-select">クラス</label>
				<select class="form-select" id="student-f2-select" name="f2">
					<option value="0">--------</option>
					<c:forEach var="num" items="${class_num_set}">
						<option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-2 form-check text-center">
				<label class="form-check-label" for="student-f3-check">
					<input class="form-check-input" type="checkbox"
					id="student-f3-check" name="f3" value="t"
					<c:if test="${!empty f3}">checked</c:if> />
					在学中
				</label>
			</div>
			<div class="col-2_button_TestList">
				<button class="btn btn-secondary" id="filter-button">絞込み</button>
			</div>
		</div>
	</form>
	<c:choose>
		<c:when test="${students.size() > 0}">
			<div>検索結果:${students.size()}件</div>
			<table class="table table-hover">
				<tr>
					<th>入学年度</th>
					<th>学生番号</th>
					<th>氏名</th>
					<th>クラス</th>
					<th class="text-center">在学中</th>
					<th></th>
				</tr>
				<c:forEach var="student" items="${students}">
					<tr>
						<td>${student.entYear}</td>
						<td>${student.no}</td>
						<td>${student.name}</td>
						<td>${student.classNum}</td>
						<td class="text-center">
							<c:choose>
								<c:when test="${student.isAttend}">
									〇
								</c:when>
								<c:otherwise>
									✕
								</c:otherwise>
							</c:choose>
						</td>
						<td><a href="StudentUpdate.action?no=${student.no}">変更</a></td>
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

<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<%@include file="../header.jsp" %>
<div class="container">
<%@include file="menu.jsp" %>
<div class="student_list_content">
	<h2>科目管理</h2>
	<div class="new_create">
		<a href="StudentCreate.action">新規登録</a>
	</div>
	<table class="table table-hover">
		<tr>
			<th>科目コード</th>
			<th>科目名</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="subject" items="${subjectList}">
			<tr>
				<td>${subject.cd}</td>
				<td>${subject.name}</td>
				<td><a href="SubjectUpdate.action?no=${subject.cd}">変更</a></td>
				<td><a href="SubjectDelete.action?no=${subject.cd}">削除</a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
</div>
<%@include file="../footer.jsp" %>

<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<%@include file="../header.jsp" %>
<div class="container">
<%@include file="menu.jsp" %>
<div class="subject_content">
	<h2>成績管理</h2>

	<form method="post" action="">
		<div class="student_list_search" id="filter">
			<!-- 入学年度のセレクトボックス -->
			<div class="col-4">
				<label>入学年度</label>
				<select class="form-select" id="test_regist-entYearSet-select" name="entYearSet">
					<option value="0">--------</option>
					<c:forEach var="year" items="${ent_year_set}">
						<option value="${year}" <c:if test="${year == param.entYearSet}">selected</c:if>>${year}</option>
					</c:forEach>
				</select>
			</div>
			<!-- クラスのセレクトボックス -->
			<div class="col-4">
				<label class="form-label" for="test_regist-classes-select">クラス</label>
				<select class="form-select" id="test_regist-classes-select" name="classes">
					<option value="0">--------</option>
					<c:forEach var="num" items="${class_num_set}">
						<option value="${num}" <c:if test="${num == param.classes}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>
			</div>
			<!-- 科目のセレクトボックス -->
			<div class="col-4">
				<label class="form-label" for="test_regist-subjects-select">科目</label>
				<select class="form-select" id="test_regist-subjects-select" name="subjects">
					<option value="0">--------</option>
					<c:forEach var="subject" items="${subject_set}">
						<option value="${subject.cd}" <c:if test="${subject.cd == param.subjects}">selected</c:if>>${subject.name}</option>
					</c:forEach>
				</select>
			</div>
			<!-- 回数のセレクトボックス -->
			<div class="col-4">
				<label class="form-label" for="test_regist-testNumSet-select">回数</label>
				<select class="form-select" id="test_regist-testNumSet-select" name="testNumSet">
					<option value="0">--------</option>
					<option value="1" <c:if test="${1 == param.testNumSet}">selected</c:if>>1</option>
					<option value="2" <c:if test="${2 == param.testNumSet}">selected</c:if>>2</option>
					<c:forEach var="num" items="${test_num_set}">
						<option value="${num}" <c:if test="${num == param.testNumSet}">selected</c:if>>${num}</option>
					</c:forEach>
				</select>
			</div>
			<!-- 検索ボタン -->
			<div class="col-2 text-center">
				<button class="btn btn-secondary" id="filter-button">検索</button>
			</div>
			<!-- エラーメッセージの表示 -->
			<div class="mt-2 text-warning">${errors.get("f1")}</div>
		</div>
	</form>

	<c:choose>
		<c:when test="${not empty students}">
			<form method="post" action="registerScores">
				<p>科目：${selectedSubjectName}（${selectedTestNum}回）</p>
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
						<c:forEach var="student" items="${students}">
							<tr>
								<td>${student.entYear}</td>
								<td>${student.classNum}</td>
								<td>${student.studentId}</td>
								<td>${student.name}</td>
								<td>
									<input type="text" name="score_${student.studentId}" value="${student.score}" />
								</td>
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
<%@include file="../footer.jsp" %>

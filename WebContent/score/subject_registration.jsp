<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<div class="container">
<%@include file="menu.jsp" %>
<div class="subject_content">
<h2>科目情報登録</h2>
<form action="SubjectCreateExecute.action" method="post">
<label for="subjectCode">科目コード</label>
<input type="text" id="subjectCode" name="cd" required>
<ul id="errorMessages" class="error"></ul>
<label for="subjectName">科目名</label>
<input type="text" id="subjectName" name="name" required>
<ul id="errorMessages" class="error"></ul>
<input type="submit" value="登録">
<a href="/iwaoProject/score/SubjectList.action">戻る</a>
</form>
</div>
</div>
</html><%@include file="../footer.jsp" %>

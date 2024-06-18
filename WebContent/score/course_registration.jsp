<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
 <%@include file="menu.jsp" %>
<h2>科目情報登録</h2>
<form id="subjectForm">
<label for="subjectCode">科目コード</label>
<input type="text" id="subjectCode" name="subjectCode" required><br><br>
<ul id="errorMessages" class="error"></ul>
<label for="subjectName">科目名</label>
<input type="text" id="subjectName" name="subjectName" required><br><br>
<ul id="errorMessages" class="error"></ul>
<button type="submit">登録</button>
<a href="/iwaoProject/score/ToMainMenu.action">戻る</a>
</form>

</html><%@include file="../footer.jsp" %>

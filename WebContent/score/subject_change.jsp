<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
 <%@include file="menu.jsp" %>
    <h2>科目情報変更</h2>
    <form action="updateSubject" method="post">
    	<div>
        <label for="subjectCode">科目コード</label>
        <input type="text" id="subjectCode" name="subjectCode" value="F02" style="border: none;">
        <ul id="errorMessages" class="error"></ul>
     	</div>
        <label for="subjectName">科目名</label>
        <input type="text" id="subjectName" name="subjectName" value="">
         <ul id="errorMessages" class="error"></ul>
        <button type="submit">変更</button>
        <a href="/iwaoProject/score/ToMainMenu.action">戻る</a>
    </form>
<%@include file="../footer.jsp" %>

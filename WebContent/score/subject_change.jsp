<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="../header.jsp" %>
<div class="container">
 <%@include file="menu.jsp" %>
   <div class="subject_content">
    <h2>科目情報変更</h2>
    <form action="updateSubject" method="post">	
        <label for="subjectCode">科目コード</label>
        <div>
        <input type="text" id="subjectCode" name="subjectCode" value="F02" style="border: none;">
        </div>
        <ul id="errorMessages" class="error"></ul>
        <label for="subjectName">科目名</label>
        <div>
        <input type="text" id="subjectName" name="subjectName" value="">
        </div>
         <ul id="errorMessages" class="error"></ul>
         <div class="btn-container">
        <input type="submit" value="変更">
        </div>
        <a href="/iwaoProject/score/ToMainMenu.action" id="subjectreturn">戻る</a>
    </form>
	</div>
</div>
<%@include file="../footer.jsp" %>

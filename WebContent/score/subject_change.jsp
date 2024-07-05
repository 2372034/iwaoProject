<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<div class="container">
    <%@ include file="menu.jsp" %>
    <div class="subject_content">
        <h2>科目情報変更</h2>
        <form action="SubjectUpdateExecute.action" method="post"required>
            <label for="subjectCode">科目コード</label>
            <div>
                <input type="text" id="subjectCode" name="cd" value="${subject.cd}" style="border: none;" readonly>
            </div>
            <ul id="errorMessages" class="error"required></ul>
            <label for="subjectName">科目名</label>
            <div>
                <input type="text" id="subjectName" name="name" value="${subject.name}"required>
            </div>
            <ul id="errorMessages" class="error"></ul>
            <input type="submit" value="変更">
            <a href="/iwaoProject/score/SubjectList.action" id="subjectreturn">戻る</a>
        </form>
    </div>
</div>
<%@ include file="../footer.jsp" %>

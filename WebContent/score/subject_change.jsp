<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<div class="container">
    <%@ include file="menu.jsp" %>
    <div class="subject_content">
        <h2>科目情報変更</h2>
        <form action="SubjectUpdateExecute.action" method="post">
            <label for="subjectCode">科目コード</label>
            <div>
            <c:if test="${not empty subject}">
                <input type="text" id="subjectCode" name="cd" value="${subject.cd}" style="border: none;" readonly>
            </c:if>
                <input type="text" id="subjectCode" name="cd" value="${subject_cd}" style="border: none;" readonly>
            </div>
            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>
            <ul id="errorMessages" class="error"></ul>
            <label for="subjectName">科目名</label>
            <div>
                <input type="text" id="subjectName" name="name" id="sub-form" value="${subject.name}" placeholder="科目名を入力してください" required>
            <c:if test="${not empty error1}">
                <div class="error-message">${error1}</div>
            </c:if>
            </div>
            <ul id="errorMessages" class="error"></ul>
            <input type="submit" value="変更">
            <a href="/iwaoProject/score/SubjectList.action" id="subjectreturn">戻る</a>
        </form>
    </div>
</div>
<%@ include file="../footer.jsp" %>

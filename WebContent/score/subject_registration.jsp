<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<div class="container">
    <%@include file="menu.jsp" %>
    <div class="subject_content">
        <h2>科目情報登録</h2>
        <form action="SubjectCreateExecute.action" method="post">
            <label for="subjectCode">科目コード</label>
            <input type="text" id="subjectCode" name="cd" required value="${cd}">
            <!-- エラーメッセージの表示 -->
            <c:if test="${not empty error}">
			<div class="error-message">科目コードは3文字で入力してください</div>
			</c:if>
            <c:if test="${not empty error2}">
                <div class="error-message">科目コードが重複しています</div>
            </c:if>
            <label for="subjectName">科目名</label>
            <input type="text" id="subjectName" name="name" required value="${name}">
            <input type="submit" value="登録">
            <a href="/iwaoProject/score/SubjectList.action">戻る</a>
        </form>
    </div>
</div>
<%@include file="../footer.jsp" %>

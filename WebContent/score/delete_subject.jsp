<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<div class="container">
<%@include file="menu.jsp" %>
<div class="subject_content">
    <h2>科目情報削除</h2>
    <p>「Javaプログラミング基礎(F02)」を削除してもよろしいですか？</p>
    <form action="deleteSubject" method="post">
        <button type="submit">削除</button>
         <a href="/iwaoProject/score/ToMainMenu.action">戻る</a>
    </form>
	</div>
</div>
<%@include file="../footer.jsp" %>

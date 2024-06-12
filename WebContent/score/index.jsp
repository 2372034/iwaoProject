<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<!-- このページはhttp://localhost:8080/kouka2にアクセスしたさいに
index.javaが実行され、フォワードされて表示する。 -->
<!-- display:flexでmenu.jspとcontentを横並びにする -->
<div class="container">
    <!-- flexアイテム左側としてmenu.jspを読み込む -->
    <%@include file="menu.jsp" %>

    <!-- flexアイテム右側としてdiv class="main-content" -->
    <div class="main-content">
        <div class="main-header">
            <h2>メニュー</h2>
        </div>
        <div class="main-flex-items-container">
            <div class="main-flex-item">
                <a href="/iwaoProject/score/ToStudentList.action">学生管理</a>
            </div>
            <div class="main-flex-item">
                <div style="margin-bottom: 5px;">成績管理</div>
                <a href="/iwaoProject/score/ToStudentList.action">成績登録</a><br>
                <a href="/iwaoProject/score/ToStudentList.action">成績参照</a>
            </div>
            <div class="main-flex-item">
                <a href="/iwaoProject/score/ToStudentList.action">科目管理</a>
            </div>
        </div>
    </div>
</div>

<%@include file="../footer.jsp" %>

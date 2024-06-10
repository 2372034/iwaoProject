<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<!-- このページはhttp://localhost:8080/kouka2にアクセスしたさいに
index.javaが実行され、フォワードされて表示する。 -->
<!-- display:flexでmenu.jspとcontentを横並びにする -->
<div class="container" style="display:flex; height:100vh;">
	<!-- flexアイテム左側としてmuenu.jspを読み込む -->
	<%@include file="menu.jsp" %>

	<!-- flexアイテム右側としてdiv class="content" -->
    <div style="display: flex; flex-direction: column; align-items: center; padding: 20px; width:100%">
        <div style="margin-bottom: 20px; width: 100%; text-align: left; background-color: #f8f8f8; padding: 10px; border: 1px solid #ddd;">
            <h1 style="margin: 0;">メニュー</h1>
        </div>
        <div style="display: flex; justify-content: space-around; width: 100%;">
            <div style="width:25%; background-color: #f8d7da; padding: 20px; text-align: center; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);display: flex;flex-direction: column; justify-content: center;">
                <a href="/iwaoProject/score/ToStudentList.action" style="color: blue; text-decoration: none;">学生管理</a>
            </div>
            <div style="width:25%; background-color: #d4edda; padding: 20px; text-align: center; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);display: flex;flex-direction: column; justify-content: center;">
                <h2>成績管理</h2>
                <a href="/iwaoProject/score/ToStudentList.action" style="color: blue; text-decoration: none;">成績登録</a><br>
                <a href="/iwaoProject/score/ToStudentList.action" style="color: blue; text-decoration: none;">成績参照</a>
            </div>
            <div style="width:25%; background-color: #d1ecf1; padding: 20px; text-align: center; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);display: flex;flex-direction: column; justify-content: center;">
                <a href="/iwaoProject/score/ToStudentList.action" style="color: blue; text-decoration: none;">科目管理</a>
            </div>
        </div>
    </div>
</div>

<%@include file="../footer.jsp" %>

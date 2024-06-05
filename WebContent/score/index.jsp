<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.html" %>
<!-- このページはhttp://localhost:8080/kouka2にアクセスしたさいに
index.javaが実行され、フォワードされて表示する。 -->

<!-- display:flexでmenu.jspとcontentを横並びにする -->
<div class="container" style="display:flex; height:100vh;">
	<!-- flexアイテム左側としてmuenu.jspを読み込む -->
	<%@include file="menu.jsp" %>

	<!-- flexアイテム右側としてdiv class="content" -->
	<div class="content" style="flex:0 0 85%; padding-left:20px">
		<h2>トップページ</h2>
		<p>学生とコースの管理をするシステムです。<br>
			左のメニューから、各機能を選択してください。
		</p>
	</div>
</div>

<%@include file="../footer.html" %>

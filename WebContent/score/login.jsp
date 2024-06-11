<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../header.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <div class="login">
        <h2>ログイン</h2>
        <form>
            <input type="text" menu.jsp="ID" value="admin"><br>
            <input type="password" placeholder="パスワード" value="*****"><br>
            <input type="checkbox" id="show-password">
            <label for="show-password">パスワードを表示</label><br>
            <button type="submit">ログイン</button>
            <ul id="errorMessages" class="error"></ul>
        </form>
   
</body>
</html>
<%@include file="../footer.jsp" %>
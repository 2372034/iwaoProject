<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <div class="login">
        <h2>ログイン</h2>
        <form>
            <input type="text" placeholder="ID" value="admin"><br>
            <input type="password" placeholder="パスワード" value="*****"><br>
            <input type="checkbox" id="show-password">
            <label for="show-password">パスワードを表示</label><br>
            <button type="submit">ログイン</button>
        </form>
        
 
   
</body>
</html>
<%@include file="../footer.jsp" %>
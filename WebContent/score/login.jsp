<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<%@ include file="../header.jsp" %>
<form action="LoginExecute.action" method="post" class="login-form">
    <h2>ログイン</h2>
    <c:if test="${not empty error}">
        <ul>
            <li>ログインに失敗しました。IDまたはパスワードが正しくありません。</li>
        </ul>
    </c:if>
    <input type="text" name="id" placeholder="ID" maxlength="20" required value="${id != null ? id : ''}">
    <input type="password" id="password" name="password" placeholder="パスワード" maxlength="20" required>
    <label for="chk_d_ps">
        <input type="checkbox" id="chk_d_ps" onclick="togglePassword()"> パスワードを表示
    </label>
    <input type="submit" value="ログイン">
</form>
<%@ include file="../footer.jsp" %>
<script>
    function togglePassword() {
        var passwordField = document.getElementById("password");
        if (passwordField.type == "password") {
            passwordField.type = "text";
        } else {
            passwordField.type = "password";
        }
    }
</script>

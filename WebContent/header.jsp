<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html xmlns:mso="urn:schemas-microsoft-com:office:office" xmlns:msdt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta charset="UTF-8">
<title>Servlet/JSP Samples</title>
</head>
<body>
    <h1>得点管理システム
    <c:if test="${not empty sessionScope.user}">
        <span class="header_content1">${sessionScope.user.name}様</span>
        <a class="header_content2" href="/iwaoProject/score/Logout.action">ログアウト</a>
    </c:if>
</h1>
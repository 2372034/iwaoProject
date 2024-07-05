<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="../css/style.css">

<div class="global-navi" style="border-right: 2px solid black; flex: 0 0 15%;">
    <nav>
        <ul>
            <li><a href="/iwaoProject/score/index.jsp">メニュー</a></li>
            <li><a href="/iwaoProject/score/StudentList.action">学生管理</a></li>
            <li>
                <label>成績管理</label>
                <ul>
                    <li><a href="TestRegist.action">成績登録</a></li>
                    <li><a href="/iwaoProject/score/TestList.action">成績参照</a></li>
                </ul>
            </li>
            <li><a href="/iwaoProject/score/SubjectList.action">科目管理</a></li>
        </ul>
    </nav>
</div>


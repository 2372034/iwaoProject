<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<link rel="stylesheet" type="text/css" href="../css/style.css">

<div class="global-navi" style="border-right: 2px solid black; flex: 0 0 15%;">
    <nav>
        <ul>
            <li><a href="/iwaoProject/score/ToMainMenu.action">メニュー</a></li>
            <li><a href="/iwaoProject/score/ToStudentList.action">学生管理</a></li>
            <li>
                <label>成績管理</label>
                <ul>
                    <li><a href="/iwaoProject/score/ToScoreRegister.action">成績登録</a></li>
                    <li><a href="/iwaoProject/score/ToScoreView.action">成績参照</a></li>
                </ul>
            </li>
            <li><a href="/iwaoProject/score/ToSubjectList.action">科目管理</a></li>
        </ul>
    </nav>
</div>
<html>
<head>
 <meta charset="UTF-8">
<title>学生情報登録</title>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>成績参照</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>科目登録</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<h2>科目情報登録</h2>
<form id="subjectForm">
<label for="subjectCode">科目コード</label>
<input type="text" id="subjectCode" name="subjectCode" required><br><br>
<ul id="errorMessages" class="error"></ul>
<label for="subjectName">科目名</label>
<input type="text" id="subjectName" name="subjectName" required><br><br>
<ul id="errorMessages" class="error"></ul>
<button type="submit">登録</button>
<li><a href="/iwaoProject/score/ToMainMenu.action">戻る</a></li>
</form>
 
    <script src="scripts.js"></script>
</body>
</html>
<script src="script.js"></script>
</body>
</html><%@include file="../footer.jsp" %>

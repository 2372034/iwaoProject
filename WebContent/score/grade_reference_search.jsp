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
<div id="header">
<h1>得点管理システム</h1>
<a href="logout">ログアウト</a>
</div>
<div id="main">
<h2>成績参照</h2>
<label for="grade">科目情報</label>
<form id="search-form">
<label for="year">入学年度:</label>
<select id="year" name="year">
<!-- 年度のオプション -->
</select>
 
            <label for="semester">学期:</label>
<select id="semester" name="semester">
<!-- 学期のオプション -->
</select>
 
            <label for="class">クラス:</label>
<select id="class" name="class">
<!-- クラスのオプション -->
</select>
 
            <label for="subject">科目:</label>
<select id="subject" name="subject">
<!-- 科目のオプション -->
</select>
 
            <button type="submit">検索</button>
</form>
<label for="grade">学生情報</label>
<form id="search-form">
 <label for="admissionYear">学生番号</label>
 
         <button type="submit">検索</button>
</form>
                <input type="text" id="admissionYear" name="admissionYear">
 
        <div id="results">
<!-- 検索結果がここに表示される -->
</div>
</div>
<script src="script.js"></script>
</body>
</html><%@include file="../footer.jsp" %>

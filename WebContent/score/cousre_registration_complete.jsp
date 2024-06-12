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
<title>科目登録完了</title>
</head>
<body>
<div class="main">
           <h2>科目情報登録</h2>
<div class="form-group">
                     <label for="admissionYear">登録が完了しました</label>
                     <li><a href="/iwaoProject/score/ToMainMenu.action">戻る</a></li>
                     <li><a href="/iwaoProject/score/ToStudentList.action">科目一覧</a></li>
                </div>
            
</body>
</html>
<%@include file="../footer.jsp" %>

<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
 
<head>
    <meta charset="UTF-8">
    <title>学生情報登録</title>
</head>
<body>

    <div class="container">
        <h2>学生情報登録</h2>
        <form action="/path/to/submit" method="POST">
   
                <label for="grade">入学年度</label>
                <ul id="errorMessages" class="error"></ul>
                <select id="grade" name="grade">
                    <option value="1">1年生</option>
                    <option value="2">2年生</option>
                    <option value="3">3年生</option>
                    <option value="4">4年生</option>
                </select>
            <div class="form-group">
                <label for="admissionYear">学生番号</label>
                <input type="text" id="admissionYear" name="admissionYear">
       			<ul id="errorMessages" class="error"></ul>
            </div>
                <label for="firstName">氏名</label>
                <input type="text" id="firstName" name="firstName">
  
            <div class="form-group">
                <label for="class">クラス</label>
                <select id="class" name="class">
                    <option value="101">101</option>
                    <option value="102">102</option>
                    <option value="103">103</option>
                </select>
            </div>
            <div class="form-group">
                <button type="submit">登録して完了</button>
            </div>
            <li><a href="/iwaoProject/score/ToMainMenu.action">戻る</a></li>
        </form>
    </div>
</body>
</html>
<%@include file="../footer.jsp" %>

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
 <label class="error-message">エラーが発生しました</label>
<%@include file="../footer.jsp" %>
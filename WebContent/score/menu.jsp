<%@page pageEncoding="UTF-8" %>
<div class="global-navi" style="border-right: 2px solid black; flex:0 0 15%">
    <nav>
        <ul>
            <li><a href="/iwaoProject/score/ToStudentList.action">メニュー</a></li>
            <li><a href="/iwaoProject/score/ToStudentList.action">学生管理</a></li>
            <div>成績管理</div>
            <li><a href="/iwaoProject/score/ToStudentList.action">成績登録</a></li>
            <li><a href="/iwaoProject/score/ToStudentList.action">成績参照</a></li>
            <li><a href="/iwaoProject/score/ToStudentList.action">科目管理</a></li>
        </ul>
    </nav>
    <% String message = (String) request.getAttribute("message"); %>
    	
</div>
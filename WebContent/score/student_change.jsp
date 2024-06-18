<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<div class="container">
	<%@include file="menu.jsp" %>
		<div class="subject_content">
		<h2>学生情報変更</h2>
		<form action="StudentUpdateExecute.action" method="post">
			<label>入学年度</label>
            <input type="text" name="ent_year">

            <label>学生番号</label>
            <input type="text" name="no">

            <label>氏名</label>
            <input type="text" name="name">

            <label>クラス</label>
            <select>
            	<option value="101">101</option>
            	<option value="102">102</option>
            	<option value="103">103</option>
            </select>
            <label for="class">在学中</label>
            <input type="checkbox" name="si_attend" value="true">
                <button type="submit">変更</button>
            <a href="/iwaoProject/score/ToMainMenu.action">戻る</a>
		</form>
	</div>
</div>
<%@include file="../footer.jsp" %>

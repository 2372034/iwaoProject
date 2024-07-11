<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<div class="container">
	<%@include file="menu.jsp" %>
		<div class="subject_content">
		<h2>学生情報変更</h2>
		<form action="StudentUpdateExecute.action" method="post"required>
			<label for="subjectCode">入学年度</label>
			<div>
            <input type="number" name="ent_year" value="${student.entYear}" readonly>
			</div>
            <label>学生番号</label>
            <div>
            <input type="number" name="no" value="${student.no}" readonly>
			</div>
            <label>氏名</label>
            <input type="text" name="name" value="${student.name}" required>
            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>


            <label>クラス</label>
                <select name="class_num">
                    <option value="101">101</option>
                    <option value="102">102</option>
                    <option value="103">103</option>
                </select>
            <label for="si_attend">在学中</label>
            <input type="checkbox" name="si_attend" value="true" ${student.isAttend ? 'checked' : ''}>
                <button type="submit">変更</button>
            <a href="/iwaoProject/score/StudentList.action">戻る</a>
		</form>
		</div>
	</div>
<%@include file="../footer.jsp" %>

<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../header.jsp" %>
<div class="container">
	<%@include file="menu.jsp" %>
		<div class="subject_content">
		<h2>学生情報変更</h2>
		<form action="StudentUpdateExecute.action" method="post">
			<label for="studentCode">入学年度</label>
			<div class="stu-change-label">
            	  <input type="number" name="ent_year" value="${student.entYear}" readonly style="border:none;padding-left:15px;">
			</div>
            <label>学生番号</label>
            <div class="stu-change-label">
            	  <input type="number" name="no" value="${student.no}" readonly  style="border:none;padding-left:15px;">
			</div>
            <label>氏名</label>
            <div class="stu-change-label">
            	  <input type="text" name="name" value="${student.name}" required style="width:98%;padding:10px;border-radius:5px;border:solid 1px #ccc;">
            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>
            </div>
            
            <label>クラス</label>
                <select name="class_num"style="border-radius:5px;border:solid 1px #ccc;">
                    <option value="101">101</option>
                    <option value="102">102</option>
                    <option value="103">103</option>
                </select>
             <div class="stu-change-isattend">
            	<label for="is_attend">在学中</label>
            	<input id="is_attend" type="checkbox" name="si_attend" value="true" ${student.isAttend ? 'checked' : ''}>
           	</div>
                <ul id="errorMessages" class="error"></ul>
            	   <input type="submit" value="変更">
            <a href="/iwaoProject/score/StudentList.action" id="studentreturn">戻る</a>
		</form>
		</div>
	</div>
<%@include file="../footer.jsp" %>

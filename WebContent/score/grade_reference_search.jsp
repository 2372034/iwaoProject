<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../header.jsp" %>
<div class="container">
<%@include file="menu.jsp" %>
<div class="subject_content">
<h2>成績参照</h2>
<div class="iwao">
<div>科目情報</div>
<form class="search-form">
	<table>
		<tr>	
			<th>入学年度:</th>
			<th>クラス</th>
			<th>科目</th>
		</tr>		
		<tr>	
			<td><select id="year" name="year"></select></td>
			<td><select id="class" name="class"></select></td>
			<td><select id="subject" name="subject"></select></td>
		</tr>
	</table>
	<div class="btn-container">
		<button type="submit">検索</button>
	</div>
</form>
</div>
<p>学生情報</p>
<form class="search-form">
<div>学生番号</div>
<input type="text" id="admissionYear" name="admissionYear">
<button type="submit">検索</button>
<p>科目情報を選択または学生情報入力して検索ボタンをクリックしてください</p>
  <input type="hidden" id="userid" name="nameid" value="科目">
  <input type="hidden" id="userid" name="nameid" value="学生">
</form>
 
</div>
</div>
<%@include file="../footer.jsp" %>

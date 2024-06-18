<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@include file="../header.jsp" %>
 <div class="container">
 <%@include file="menu.jsp" %>
 <div class="subject_content">
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
            <a href="/iwaoProject/score/ToMainMenu.action">戻る</a>
        </form>
     </div>
</div>
<%@include file="../footer.jsp" %>

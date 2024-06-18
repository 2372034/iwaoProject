<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@include file="../header.jsp" %>
 <div class="container">
 <%@include file="menu.jsp" %>
 <div class="subject_content">
     <h2>学生情報登録</h2>
        <form action="StudentCreateExecute.action" method="post">

                <label for="grade">入学年度</label>
                <select name="ent_year">
                    <option value="2023">2023</option>
                    <option value="2024">2024</option>
                </select>
                <label for="admissionYear">学生番号</label>
                <input type="text" name="no">
                <label for="firstName">氏名</label>
                <input type="text" name="name">
                <label for="class">クラス</label>
                <select name="class_num">
                    <option value="101">101</option>
                    <option value="102">102</option>
                    <option value="103">103</option>
                </select>
                <button type="submit">登録して完了</button>
            <a href="/iwaoProject/score/StudentList.action">戻る</a>
        </form>
     </div>
</div>
<%@include file="../footer.jsp" %>

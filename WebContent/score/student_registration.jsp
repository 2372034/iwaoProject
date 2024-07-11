<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<div class="container">
    <%@ include file="menu.jsp" %>
    <div class="subject_content">
        <h2>学生情報登録</h2>

        <form action="StudentCreateExecute.action" method="post" class="student-form">
            <label for="ent_year" class="stu-regist-label">入学年度</label>
            <select name="ent_year" id="ent_year" class="stu-regist-input">
                <option value="">--------</option>
                <option value="2023" ${param.ent_year == '2023' ? 'selected' : ''}>2023</option>
                <option value="2024" ${param.ent_year == '2024' ? 'selected' : ''}>2024</option>
            </select>
            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>

            <label for="no" class="stu-regist-label">学生番号</label>
            <input type="text" name="no" id="no" class="stu-regist-input" placeholder="学生番号を入力してください"
                value="${not empty param.no ? param.no : ''}" required>
            <c:if test="${not empty error2}">
                <div class="error-message">${error2}</div>
            </c:if>

            <label for="name" class="stu-regist-label">氏名</label>
            <input type="text" name="name" id="name" class="stu-regist-input" placeholder="氏名を入力してください"
                value="${not empty param.name ? param.name : ''}" required>
            <c:if test="${not empty error3}">
                <div class="error-message">${error3}</div>
            </c:if>

            <label for="class_num" class="stu-regist-label">クラス</label>
            <select name="class_num" id="class_num" class="stu-regist-input">
                <option value="101" ${param.class_num == '101' ? 'selected' : ''}>101</option>
                <option value="102" ${param.class_num == '102' ? 'selected' : ''}>102</option>
                <option value="103" ${param.class_num == '103' ? 'selected' : ''}>103</option>
            </select>

            <button type="submit" class="stu-regist-submit">登録して終了</button>
            <a href="/iwaoProject/score/StudentList.action">戻る</a>
        </form>

        <!-- CSVファイルアップロードフォームを追加 -->
        <h2>CSVファイルから学生情報登録</h2>
        <form id="" action="StudentCsvUpload.action" method="post" enctype="multipart/form-data">
            <label for="csvFile">CSVファイルを選択してください</label>
            <div class="tr">
            	<label for="csv" class="th">csv:</label>
            	<div class="td">
            		<input type="file" id="csv" name="csv" required>
            	</div>
			</div>
            <button type="submit">アップロード</button>
        </form>
    </div>
</div>
<%@ include file="../footer.jsp" %>

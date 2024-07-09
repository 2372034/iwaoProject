package score;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action{
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        // 学生番号を取得
        String studentNum = request.getParameter("studentNum");
        System.out.println("Received studentNum: " + studentNum);

        // Daoインスタンスの作成
        StudentDao studentDao = new StudentDao();
        TestListStudentDao testListStudentDao = new TestListStudentDao();

        // 学生情報の取得
        Student stu = studentDao.get(studentNum);
        HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("user");
        String expectedSchoolCode =teacher.getSchool().getCd();
        if (stu == null || !expectedSchoolCode.equals(stu.getSchool().getCd())) {
            System.out.println("Student not found with studentNum: " + studentNum);
            request.setAttribute("error", "学生情報が存在しませんでした");
            request.getRequestDispatcher("/score/test_list_student.jsp").forward(request, response);
            return;
        }

        // テストリストの取得
        List<TestListStudent> tests = testListStudentDao.filter(stu);
        if (tests == null || tests.isEmpty()) {
            System.out.println("No test records found for studentNum: " + studentNum);
            request.setAttribute("error2", "氏名：" + stu.getName() + " (" + studentNum + ")");
            request.setAttribute("student", stu); // 学生情報をリクエストにセット
            request.getRequestDispatcher("/score/test_list_student.jsp").forward(request, response);
            return;
        }
        // リクエストにテストリストをセット
        request.setAttribute("students", tests);

        // フォワードするページを返す
        request.getRequestDispatcher("/score/test_list_student.jsp").forward(request, response);
    }
}

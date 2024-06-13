package score;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.Action;

public class StudentCreateAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        // 学生情報を取得
        StudentDAO dao = new StudentDAO();
        List<Student> studentList = dao.studentAll();
        request.setAttribute("studentList", studentList);

        // JSPへのフォワード
        request.getRequestDispatcher("student_registration.jsp").forward(request, response);
    }
}

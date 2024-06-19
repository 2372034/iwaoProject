package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
    	String studentNo = request.getParameter("no");
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(studentNo);

        request.setAttribute("student", student);

        // JSPへのフォワード
        request.getRequestDispatcher("/score/student_change.jsp").forward(request, response);
    }
}

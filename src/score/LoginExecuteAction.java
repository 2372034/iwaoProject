package score;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action {
    private TeacherDao teacherDao = new TeacherDao();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        Teacher teacher = teacherDao.login(id, password);
        if (teacher != null) {
            request.getSession().setAttribute("teacher", teacher);
            response.sendRedirect("menu");
        } else {
            request.setAttribute("error", "Invalid ID or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}

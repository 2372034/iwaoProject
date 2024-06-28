package score;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action {
    private TeacherDao teacherDao;

    public LoginExecuteAction() {
        this.teacherDao = new TeacherDao(); // TeacherDao のインスタンスをコンストラクタで初期化
    }

     @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        try {
            Teacher teacher = teacherDao.login(id, password);
            if (teacher != null) {
                request.getSession().setAttribute("user", teacher);
                response.sendRedirect(request.getContextPath() + "/score/index.jsp");
            } else {
                request.setAttribute("error", "Invalid ID or password");
                request.getRequestDispatcher("/score/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // 例外処理が必要な場合はここに記述する
            e.printStackTrace();
            request.getRequestDispatcher("/score/error.jsp").forward(request, response);
        }
    }
}

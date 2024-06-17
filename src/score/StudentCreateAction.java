package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentCreateAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

//        // 学生情報を取得
//        StudentDao dao = new StudentDao();
//        List<Student> studentList = dao.filter(new School(), true);
//
//        // 学生リストをリクエストに設定
//        request.setAttribute("studentList", studentList);

        // JSPへのフォワード
        request.getRequestDispatcher("/score/student_registration.jsp").forward(request, response);
    }
}

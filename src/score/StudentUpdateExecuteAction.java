package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session=request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        School school = teacher.getSchool();

        // リクエストパラメータから学生情報を取得
        int entYear = Integer.parseInt(request.getParameter("ent_year")); // 入学年度
        String no = request.getParameter("no"); // 学生番号
        String name = request.getParameter("name"); // 氏名
        String classNum = request.getParameter("class_num"); // クラス

        // Studentインスタンスを作成し、取得したパラメータをセット
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setIsAttend(true);
        student.setSchool(school); // Schoolを設定

        // StudentDAOを使用してデータベースを更新
        StudentDao dao = new StudentDao();
        boolean count = dao.save(student);

        // 更新の成否に応じて適切なメッセージを設定
        if (count) {
            request.setAttribute("message", "学生情報の更新に成功しました。");
        } else {
            request.setAttribute("message", "学生情報の更新に失敗しました。");
        }
        request.setAttribute("student", student);
        // フォワード先のJSPを設定
        request.getRequestDispatcher("/score/student_completion_change.jsp").forward(request, response);
    }
}

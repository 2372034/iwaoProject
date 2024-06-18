package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
    	HttpSession session=request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

     // TeacherオブジェクトからSchoolオブジェクトを取得
        School school = teacher.getSchool();

        // ユーザーからの入力値を受け取る
        int entYear = Integer.parseInt(request.getParameter("ent_year")); // 入学年度
        String no = request.getParameter("no"); // 学生番号
        String name = request.getParameter("name"); // 氏名
        String classNum = request.getParameter("class_num"); // クラス

        // Studentビーンに設定
        Student student = new Student();
        student.setEntYear(entYear);
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setIsAttend(true);
        student.setSchool(school); // Schoolを設定

		System.out.println(entYear);
		System.out.println(no);
		System.out.println(name);
		System.out.println(classNum);

        // StudentDAOインスタンスを生成
        StudentDao dao = new StudentDao();
        // StudentDAOのsaveメソッドを実行してデータベースに登録
        boolean count = dao.save(student);

        // 登録成功かどうかでメッセージを設定
        if (count) {
            request.setAttribute("message", "登録しました");
        } else {
            request.setAttribute("message", "登録に失敗しました");
        }

        // フォワード先のJSPに遷移
        request.getRequestDispatcher("/score/student_completion_of_registration.jsp").forward(request, response);
    }
}

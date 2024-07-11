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
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // TeacherオブジェクトからSchoolオブジェクトを取得
        School school = teacher.getSchool();

        // ユーザーからの入力値を受け取る
        int entYear;
        try {
            entYear = Integer.parseInt(request.getParameter("ent_year")); // 入学年度
        } catch (NumberFormatException e) {
            // エラーメッセージを設定し、登録画面にリダイレクト
            request.setAttribute("error", "入学年度を選択してください");
            request.getRequestDispatcher("/score/student_registration.jsp").forward(request, response);
            return; // エラーがあった場合はここで処理を終了する
        }

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

        // StudentDAOインスタンスを生成
        StudentDao dao = new StudentDao();

     // 学生番号の検証
        if (no == null || !no.matches("^\\d{1,10}$")) {
            request.setAttribute("error2", "学生番号は数字のみ、10文字以内で入力してください。");
            request.getRequestDispatcher("/score/StudentCreate.action").forward(request, response);
            return;
        }

        // 氏名の検証
        if (name == null || name.length() > 10) {
            request.setAttribute("error3", "氏名は10文字以内で入力してください。");
            request.getRequestDispatcher("/score/StudentCreate.action").forward(request, response);
            return;
        }

     // 学生番号の重複チェック
        Student existingStudent = dao.get(no);
        if (existingStudent != null) {
            // 学生番号が既に存在する場合のエラーメッセージを設定し、登録画面にリダイレクト
            request.setAttribute("error2", "学生番号が重複しています");
            request.getRequestDispatcher("/score/student_registration.jsp").forward(request, response);
            return; // エラーがあった場合はここで処理を終了する
        }

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

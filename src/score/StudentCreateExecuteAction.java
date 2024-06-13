package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        // ユーザーからの入力値を受け取る
        int entYear = Integer.parseInt(request.getParameter("entYear"));
        int no = Integer.parseInt(request.getParameter("no"));
        String name = request.getParameter("name");
        String classNum = request.getParameter("classNum");

        // Studentビーンに設定
        Student student = new Student();
        student.setEntYear(entYear);
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);

        // StudentDAOインスタンスを生成
        StudentDAO dao = new StudentDAO();
        // StudentDAOのinsertメソッドを実行してデータベースに登録
        boolean isSuccess = dao.save(student);

        // 登録成功かどうかでメッセージを設定
        if (isSuccess) {
            request.setAttribute("message", "登録しました");
        } else {
            request.setAttribute("message", "登録に失敗しました");
        }

        // フォワード先のJSPに遷移
        request.getRequestDispatcher("student_completion_of_registration.jsp").forward(request, response);
    }
}

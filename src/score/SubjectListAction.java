package score;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
    public void execute(
    		HttpServletRequest request, HttpServletResponse response
    		) throws Exception {
        // セッションを取得する
        HttpSession session = request.getSession();

        // セッションから学校オブジェクトを取得する
        Teacher teacher = (Teacher) session.getAttribute("user");

        // SubjectDaoのインスタンスを生成する
        SubjectDao subjectDao = new SubjectDao();

        try {
            // 学校に所属する科目のリストを取得する
            List<Subject> subjectList = subjectDao.filter(teacher.getSchool());

            // 取得した科目リストをリクエスト属性にセットする
            request.setAttribute("subjectList", subjectList);

            // 科目一覧ページにフォワードする
            request.getRequestDispatcher("/score/subject_list.jsp").forward(request, response);

        } catch (Exception e) {
            // 例外処理
            e.printStackTrace();
            // エラーページにリダイレクトするなどの処理を記述する
            response.sendRedirect("/error.jsp");
        }
    }
}
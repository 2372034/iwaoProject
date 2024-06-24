package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションを取得する
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 教員が所属している学校情報を取得
        School school = teacher.getSchool();

        // リクエストから科目コードを取得
        String subjectNo = request.getParameter("cd");

		System.out.println(subjectNo);
        // 科目DAOをインスタンス化
        SubjectDao subjectDao = new SubjectDao();

        // 科目コードと学校情報を使用して科目情報を取得
        Subject subject = subjectDao.get(subjectNo, school);

        // 取得した科目情報をリクエスト属性に設定
        request.setAttribute("subject", subject);

        // JSPへのフォワード
        request.getRequestDispatcher("/score/subject_change.jsp").forward(request, response);
    }
}

package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションを取得する
        HttpSession session = request.getSession();
        // セッションから教師オブジェクトを取得する
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストから科目コードと科目名を取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        SubjectDao dao = new SubjectDao();
        Subject existingSubject = dao.get(cd, teacher.getSchool());

        // 氏名の検証
        if (existingSubject == null) {
            request.setAttribute("error", "科目が存在していません");
            request.setAttribute("subject_cd", cd);
            request.getRequestDispatcher("/score/subject_change.jsp").forward(request, response);
            return;
        }

        if (name.length() > 20) {
        request.setAttribute("error1", "科目名は20文字以内で入力してください");
        request.getRequestDispatcher("/score/SubjectUpdate.action").forward(request, response);
        return; // ここで処理を終了
        }

        // 新しい科目インスタンスを作成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        boolean count = dao.save(subject);

        // 結果に応じてメッセージを設定
        if (count) {
            request.setAttribute("message", "科目が正常に登録されました。");
        } else {
            request.setAttribute("message", "科目の登録に失敗しました。");
        }

        // JSPへのフォワード
        request.getRequestDispatcher("/score/subject_change_completion.jsp").forward(request, response);
    }
}

package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        // セッションを取得する
        HttpSession session = request.getSession();

        // リクエストから科目コードと科目名を取得
        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // セッションから教師オブジェクトを取得する
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 新しい科目インスタンスを作成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        // 科目をデータベースに保存
        SubjectDao dao = new SubjectDao();
        Subject existingSubject = dao.get(cd, teacher.getSchool());
        // バリデーションチェック

           if (cd.length() != 3) {
           request.setAttribute("error", "科目コードは3文字で入力してください");
           request.setAttribute("cd", cd); // 入力された科目コードをリクエストスコープに設定
           request.setAttribute("name", name); // 入力された科目名をリクエストスコープに設定
           request.getRequestDispatcher("/score/SubjectCreate.action").forward(request, response);
           return; // ここで処理を終了

           }
        if (existingSubject != null) {
            // 科目コードが既に存在する場合のエラーメッセージを設定し、登録画面にリダイレクト
            request.setAttribute("error2", "科目コードが重複しています");
            request.setAttribute("cd", cd); // 科目コードを再設定
            request.setAttribute("name", name); // 科目名を再設定
            request.getRequestDispatcher("/score/SubjectCreate.action").forward(request, response);
            return; // エラーがあった場合はここで処理を終了する
        }
        if (name.length() > 20) {
        request.setAttribute("error3", "科目名は20文字以内で入力してください");
        request.setAttribute("cd", cd); // 入力された科目コードをリクエストスコープに設定
        request.setAttribute("name", name); // 入力された科目名をリクエストスコープに設定
        request.getRequestDispatcher("/score/SubjectCreate.action").forward(request, response);
        return; // ここで処理を終了
        }
        boolean count = dao.save(subject);

        // 結果に応じてメッセージを設定
        if (count) {
            request.setAttribute("message", "科目が正常に登録されました。");
        } else {
            request.setAttribute("message", "科目の登録に失敗しました。");
        }

        // JSPへのフォワード
        request.getRequestDispatcher("/score/subject_registration_complete.jsp").forward(request, response);
    }
}

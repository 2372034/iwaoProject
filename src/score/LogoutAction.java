package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // セッションを取得して、ログアウト処理を行う
        HttpSession session = request.getSession();
        session.invalidate(); // セッションを無効にする（セッションからユーザー情報を削除）

        // ログアウト後の画面にフォワードする
        String forwardJsp = "/score/logout.jsp"; // ログアウト後に表示するJSPのパスを指定する

        // フォワード先のJSPにリクエストを転送する
        try {
            request.getRequestDispatcher(forwardJsp).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

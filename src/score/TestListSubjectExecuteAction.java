package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class TestListAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        // セッションのユーザーデータを取得

        // ただのページ遷移のため、フォワードするのみ
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}

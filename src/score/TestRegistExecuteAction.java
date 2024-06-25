package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class TestRegistExecuteAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        // JSPへのフォワード
        request.getRequestDispatcher("/score/grade_registration_completed.jsp").forward(request, response);
    }
}

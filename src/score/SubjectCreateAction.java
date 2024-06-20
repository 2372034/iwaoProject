package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectCreateAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        // JSPへのフォワード
        request.getRequestDispatcher("/score/subject_registration.jsp").forward(request, response);
    }
}

package score;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentUpdateAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {


        // JSPへのフォワード
        request.getRequestDispatcher("/score/student_change.jsp").forward(request, response);
    }
}

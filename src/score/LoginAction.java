package score;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class LoginAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		try {
			request.getRequestDispatcher("/score/login.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

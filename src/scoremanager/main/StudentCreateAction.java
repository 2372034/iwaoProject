package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.Action;

public class StudentCreateAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {

		// コース情報を取得
		StudentDAO dao=new StudentDAO();
		List<Student> courseList=dao.studentAll();
		request.setAttribute("studentList", studentList);
		// FrontControllerを使用しているためreturn文でフォワードできる
		return "insert_form.jsp";
	}
}

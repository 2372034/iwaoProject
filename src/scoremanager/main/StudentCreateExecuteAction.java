package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		// ユーザーからの入力値を受け取る
		int ent_Year=Integer.parseInt(request.getParameter("entYear"));
		int no=Integer.parseInt(request.getParameter("no"));
		String name=request.getParameter("name");
		String classNum=request.getParameter(request.getParameter("classNum"));

		// Studentビーンに設定
		Student student=new Student();
		student.setEntYear(ent_Year);
		student.setName(name);
		student.setClassNum(classNum);
		// StudentDAOインスタンスを生成
		StudentDAO dao=new StudentDAO();
		// StudentDAOのstudentInsertメソッドを実行してデータベースに登録
		int line = dao.postFilter(student);

		// lineが0でなければ登録成功
		if (line != 0) {
			request.setAttribute("message", "登録しました");
		} else {
			request.setAttribute("message", "登録に失敗しました");
		}
		return "result.jsp";
	}
}


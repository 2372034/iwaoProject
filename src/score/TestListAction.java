package score;
//
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {
	//セッションからユーザーデータを取得
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		LocalDate todaysDate=LocalDate.now();
		int Year=todaysDate.getYear();
		Teacher teacher=(Teacher)session.getAttribute("user");

		// ClassNumDaoを呼び出す
		ClassNumDao dao1 = new ClassNumDao();
		// ClassNumDaoのfilterメソッドで所属学校のクラス一覧を取得する
		List<String> classes = dao1.filter(teacher.getSchool());

		// SubjectDaoを呼び出す
		SubjectDao dao2 = new SubjectDao();
		// SubjectDaoのfilterメソッドで所属学校の科目一覧を取得する
		List<Subject> subjects = dao2.filter(teacher.getSchool());


		// リストを初期化 (入学年度セレクトボックス用)
		List<Integer> entYearSet = new ArrayList<>();

		// 10年前から1年後まで年をリストに追加
		for (int i = Year - 10; i < Year + 1; i++) {
			entYearSet.add(i);
		}

		 // リクエストパラメータから選択された年度、クラス、科目を取得
		String selectedYear = request.getParameter("entYearSet");
		String selectedClass = request.getParameter("classes");
		String selectedSubject = request.getParameter("subjects");
		String studentNum = request.getParameter("studentNum");

		request.setAttribute("entYearSet", entYearSet); //f1
		request.setAttribute("classes", classes); //f2
		// リクエストにクラス番号をセット
		request.setAttribute("subjects", subjects);
		request.setAttribute("selectedYear", selectedYear); // 選択された年度を保持
		request.setAttribute("selectedClass", selectedClass); // 選択されたクラスを保持
		request.setAttribute("selectedSubject", selectedSubject); // 選択された科目を保持
		request.setAttribute("studentNum", studentNum); // 入力された学生番号を保持

		request.getRequestDispatcher("/score/test_list.jsp").forward(request,response);
	}

}
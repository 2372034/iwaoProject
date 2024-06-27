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

		request.setAttribute("entYearSet", entYearSet);
		request.setAttribute("classes", classes);
		// リクエストにクラス番号をセット
		request.setAttribute("subjects", subjects);
		request.getRequestDispatcher("/score/test_list.jsp").forward(request,response);
	}

}
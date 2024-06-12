package score;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentListAction extends Action {
	public void execute(
		HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("user");


		String entYearStr="";
		String classNum="";
		String isAttendStr="";
		int entYear=0;
		boolean isAttend=false;
		List<Student> Students=null;
		LocalDate todaysDate=LocalDate.now();
		int Year=todaysDate.getYear();
		StudentDao sDao=new StudentDao();
		ClassNumDao cNumDao=new ClassNumDao();
		Map<String,String> errors=new HashMap<>();


		entYearStr=request.getParameter("f1");
		classNum=request.getParameter("f2");
		isAttendStr=request.getParameter("f3");

		List<String> list = cNumDao.filter(teacher.getSchool());

		if (entYear != 0 && !classNum.equals("0")) {
		    // 入学年度とクラス番号を指定
		    students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear != 0 && classNum.equals("0")) {
		    // 入学年度のみ指定
		    students = sDao.filter(teacher.getSchool(), entYear, isAttend);
		} else if (entYear == 0 && (classNum == null || entYear == 0 && classNum.equals("0"))) {
		    // 指定なしの場合
		    // 全学生情報を取得
		    students = sDao.filter(teacher.getSchool(), isAttend);
		} else {
		    errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
		    request.setAttribute("errors", errors);
		    // 全学生情報を取得
		    students = sDao.filter(teacher.getSchool(), isAttend);
		}

		if (entYearStr != null) {
		    // 数値に変換
		    entYear = Integer.parseInt(entYearStr);
		}

		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();

		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
		    entYearSet.add(i);
		}

		request.setAttribute("f1", entYear);

		// リクエストにクラス番号をセット
		request.setAttribute("f2", classNum);

		// 在学フラグが送信されていた場合
		if (isAttendStr != null) {
		    // 在学フラグを立てる
		    isAttend = true;
		    // リクエストに在学フラグをセット
		    request.setAttribute("f3", isAttendStr);
		}

		// リクエストに学生リストをセット
		request.setAttribute("students", students);

		// リクエストにデータをセット
		request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year_set", entYearSet);
		request.getRequestDispatcher( "student_list.jsp");
	}
}

package score;
//
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {
	//セッションからユーザーデータを取得
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("user");
		

		//セッションのユーザーデータから、ユーザーが所属している学校のクラスデータを取得
		String entYearStr=""; //入力された入学年度
		String classNum=""; //入力されたクラス番号
		String subjectStr="";//入力された科目
		String NoStr="";//入力された回数
		int entYear = 0; //入学年度
		int No = 0;
		List<Test> Tests=null; //成績リスト
		LocalDate todaysDate=LocalDate.now(); //LocalDaoインスタンスを取得
		int Year=todaysDate.getYear(); //現在の年を取得
		StudentDao sDao=new StudentDao();
		ClassNumDao cNumDao=new ClassNumDao(); //クラス番号Daoの初期化
		SubjectDao subDao=new SubjectDao();
		TestDao testDao=new TestDao(); //テストDao
		Map<String,String> errors=new HashMap<>(); //エラーメッセージ


		//リクエストパラメータの取得
		entYearStr=request.getParameter("f1");
		classNum=request.getParameter("f2");
		subjectStr=request.getParameter("f3");
		NoStr=request.getParameter("f4");

		System.out.println(entYearStr);
		System.out.println(classNum);
		System.out.println(subjectStr);
		System.out.println(NoStr);

		//DBからデータ取得
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> cNumlist = cNumDao.filter(teacher.getSchool());
		List<String> sublist = subDao.filter(teacher.getSchool());
		

		

		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();

		// 10年前から1年後まで年をリストに追加
		for (int i = Year - 10; i < Year + 1; i++) {
		    entYearSet.add(i);
		}

		request.setAttribute("f1", entYear);

		// リクエストにクラス番号をセット
		request.setAttribute("f2", classNum);

		request.setAttribute("f3", subjectName);

		request.setAttribute("f4", subjectNo);

		// リクエストに学生リストをセット
		request.setAttribute("students", Tests);

		// リクエストにデータをセット
		request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year_set", entYearSet);
		request.getRequestDispatcher( "student_list.jsp").forward(request,response);
	}

}
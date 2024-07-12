package score;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action{
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
    	HttpSession session=request.getSession();
		Teacher teacher=(Teacher)session.getAttribute("user");

        LocalDate todaysDate = LocalDate.now(); // Date型を生成
        int Year = todaysDate.getYear(); // 今日の年度を取得

     // ClassNumDaoを呼び出す
        ClassNumDao classNumDao = new ClassNumDao();
        // ClassNumDaoのfilterメソッドで所属学校のクラス一覧を取得する
        List<String> classes = classNumDao.filter(teacher.getSchool());

        // SubjectDaoを呼び出す
        SubjectDao subjectdao = new SubjectDao();
        // SubjectDaoのfilterメソッドで所属学校の科目一覧を取得する
        List<Subject> subjects = subjectdao.filter(teacher.getSchool());

     // リストを初期化 (入学年度セレクトボックス用)
        List<Integer> entYearSet = new ArrayList<>();

        // 10年前から1年後まで年をリストに追加
        for (int i = Year - 10; i < Year + 1; i++) {
            entYearSet.add(i);
        }

        // entYearを初期化
        int entYear = 0;

        String selectedYearStr = request.getParameter("entYearSet");
        if (selectedYearStr != null && !selectedYearStr.isEmpty()) {
            try {
                entYear = Integer.parseInt(selectedYearStr); // 既存の entYear 変数に数値型で代入
            } catch (NumberFormatException e) {
                // エラーハンドリング
                e.printStackTrace();
                // または適切なエラーメッセージを設定
            }
        }
        request.setAttribute("entYearSet", entYearSet); // f1 次のjspのセレクトボックス用
        request.setAttribute("classes", classes); // f2 次のjspのセレクトボックス用
        // リクエストにクラス番号をセット
        request.setAttribute("subjects", subjects);// 次のjspのセレクトボックス用
        // 学生番号を取得
        String studentNum = request.getParameter("studentNum");
        System.out.println("Received studentNum: " + studentNum);

        if (studentNum == null || studentNum.trim().isEmpty()) {
            request.setAttribute("error", "このフィールドを入力してください");
            request.getRequestDispatcher("/score/TestList.action").forward(request, response);
            return;
        }

        // Daoインスタンスの作成
        StudentDao studentDao = new StudentDao();
        TestListStudentDao testListStudentDao = new TestListStudentDao();

        // 学生情報の取得
        Student stu = studentDao.get(studentNum);
        request.setAttribute("studentNum", studentNum);


        String expectedSchoolCode =teacher.getSchool().getCd();
        if (stu == null || !expectedSchoolCode.equals(stu.getSchool().getCd())) {
            System.out.println("Student not found with studentNum: " + studentNum);
            request.setAttribute("error2", "学生情報が存在しませんでした");
            request.getRequestDispatcher("/score/TestList.action").forward(request, response);
            return;
        }

        // テストリストの取得
        List<TestListStudent> tests = testListStudentDao.filter(stu);
        if (tests == null || tests.isEmpty()) {
            System.out.println("No test records found for studentNum: " + studentNum);
            request.setAttribute("error3", "氏名：" + stu.getName() + " (" + studentNum + ")");
            request.setAttribute("student", stu); // 学生情報をリクエストにセット
            request.getRequestDispatcher("/score/TestList.action").forward(request, response);
            return;
        }
        // リクエストにテストリストをセット
        request.setAttribute("students", tests);

        // フォワードするページを返す
        request.getRequestDispatcher("/score/test_list_student.jsp").forward(request, response);
    }
}

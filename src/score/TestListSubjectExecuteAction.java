package score;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject; // 追加
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(); // セッションスタート
        LocalDate todaysDate = LocalDate.now(); // Date型を生成
        int Year = todaysDate.getYear(); // 今日の年度を取得
        Teacher teacher = (Teacher) session.getAttribute("user"); // ログインユーザー取得
        // セッションのユーザーデータを取得
        System.out.println("名前：" + teacher.getName());
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
        String selectedClass = request.getParameter("classes"); // ユーザー指定のクラス
        String selectedSubjectCd = request.getParameter("subjects"); // ユーザー指定の科目
        String studentNum = request.getParameter("studentNum"); // ユーザー指定の学生番号

        // 出力してチェック
		System.out.println(selectedYearStr);
		System.out.println(selectedClass);
		System.out.println(selectedSubjectCd);
		System.out.println(studentNum);

        // ユーザー指定のsubjectsを取得
        Subject subject = null;
        for (Subject s : subjects) {
            if (s.getCd().equals(selectedSubjectCd)) {
                subject = s;
                break;
            }
        }

        School school = teacher.getSchool(); // ここは適切なSchoolオブジェクトを取得する必要があります

        TestListSubjectDao testListSubjectsDao = new TestListSubjectDao();
        List<TestListSubject> testListSubjects = testListSubjectsDao.filter(entYear, selectedClass, subject, school);

        request.setAttribute("entYearSet", entYearSet); // f1 次のjspのセレクトボックス用
        request.setAttribute("classes", classes); // f2 次のjspのセレクトボックス用
        // リクエストにクラス番号をセット
        request.setAttribute("subjects", subjects);// 次のjspのセレクトボックス用
        request.setAttribute("selectedYear", selectedYearStr); // 選択された年度を保持
        request.setAttribute("selectedClass", selectedClass); // 選択されたクラスを保持
        request.setAttribute("selectedSubject", selectedSubjectCd); // 選択された科目を保持
        request.setAttribute("studentNum", studentNum); // 入力された学生番号を保持
        request.setAttribute("testListSubjects", testListSubjects); // テストリストをセット
		

        // ただのページ遷移のため、フォワードするのみ
        request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
    }
}
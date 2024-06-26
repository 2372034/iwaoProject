package score;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {
    // セッションからユーザーデータを取得
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // セッションのユーザーデータから、ユーザーが所属している学校のクラスデータを取得
        String entYearStr = request.getParameter("f1"); // 入力された入学年度
        String classNum = request.getParameter("f2"); // 入力されたクラス番号
        String subjectStr = request.getParameter("f3"); // 入力された科目
        String NoStr = request.getParameter("f4"); // 入力された回数

        System.out.println(entYearStr);
		System.out.println(classNum);
		System.out.println(subjectStr);
		System.out.println(NoStr);

        int entYear = 0; // 入学年度
        int No = 0; // 回数
        LocalDate todaysDate = LocalDate.now(); // LocalDateインスタンスを取得
        int year = todaysDate.getYear(); // 現在の年を取得
        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Daoの初期化
        SubjectDao subDao = new SubjectDao();
        TestDao testDao = new TestDao(); // テストDao
        Map<String, String> errors = new HashMap<>(); // エラーメッセージ

        // 入力のバリデーションと変換
        try {
            if (entYearStr != null && !entYearStr.isEmpty()) {
                entYear = Integer.parseInt(entYearStr);
            }
        } catch (NumberFormatException e) {
            errors.put("f1", "入学年度は整数で入力してください。");
        }

        try {
            if (NoStr != null && !NoStr.isEmpty()) {
                No = Integer.parseInt(NoStr);
            }
        } catch (NumberFormatException e) {
            errors.put("f4", "回数は整数で入力してください。");
        }

        List<String> cNumList = new ArrayList<>();
        List<Subject> subList = new ArrayList<>();
        List<Test> tests = new ArrayList<>();

        if (errors.isEmpty()) {
            // DBからデータ取得
            // ログインユーザーの学校コードをもとにクラス番号の一覧を取得
            cNumList = cNumDao.filter(teacher.getSchool());
            subList = subDao.filter(teacher.getSchool());

            // 入力値がすべて揃っている場合にのみ成績リストを取得
            if (entYearStr != null && !entYearStr.isEmpty() &&
                classNum != null && !classNum.isEmpty() &&
                subjectStr != null && !subjectStr.isEmpty() &&
                NoStr != null && !NoStr.isEmpty()) {

                tests = testDao.filter(entYear, classNum, subjectStr, No, teacher.getSchool());

                // リクエストに学生リストをセット
                request.setAttribute("students", tests);
            }
        } else {
            // エラーメッセージをリクエストにセット
            request.setAttribute("errors", errors);
        }

        // リストを初期化
        List<Integer> entYearSet = new ArrayList<>();
        // 10年前から現在までの年をリストに追加
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // リクエストにデータをセット
        request.setAttribute("f1", entYearStr);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectStr);
        request.setAttribute("f4", NoStr);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", cNumList);
        request.setAttribute("subject_set", subList);

        // JSPにフォワード
        request.getRequestDispatcher("/score/test_regist.jsp").forward(request, response);
    }
}

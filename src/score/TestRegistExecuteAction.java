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
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectStr = request.getParameter("f3");
        String NoStr = request.getParameter("f4");

        int entYear = 0;
        int No = 0;
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subDao = new SubjectDao();
        TestDao testDao = new TestDao();
        Map<String, String> errors = new HashMap<>();

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
            cNumList = cNumDao.filter(teacher.getSchool());
            subList = subDao.filter(teacher.getSchool());

            if (entYearStr != null && !entYearStr.isEmpty() &&
                classNum != null && !classNum.isEmpty() &&
                subjectStr != null && !subjectStr.isEmpty() &&
                NoStr != null && !NoStr.isEmpty()) {

                tests = testDao.filter(entYear, classNum, subjectStr, No, teacher.getSchool());
                request.setAttribute("students", tests);
            }
        } else {
            request.setAttribute("errors", errors);
        }

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        request.setAttribute("f1", entYearStr);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectStr);
        request.setAttribute("f4", NoStr);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", cNumList);
        request.setAttribute("subject_set", subList);

        // 成績データの更新処理
        boolean hasErrors = false;
        for (Test test : tests) {
            String pointStr = request.getParameter("score_" + test.getStudent().getNo());
            if (pointStr != null) {
                if (pointStr.isEmpty()) {
                    test.setPoint(null);
                } else {
                    try {
                        int point = Integer.parseInt(pointStr);
                        test.setPoint(point);
                    } catch (NumberFormatException e) {
                        errors.put("score_" + test.getStudent().getNo(), "点数は整数で入力してください。");
                        hasErrors = true;
                    }
                }
            }
        }

        if (!hasErrors) {
            try {
                boolean success = testDao.save(tests);
                if (success) {
                    request.setAttribute("message", "テストデータの保存が成功しました。");
                } else {
                    request.setAttribute("errorMessage", "テストデータの保存に失敗しました。");
                }
            } catch (Exception e) {
                request.setAttribute("errorMessage", "テストデータの保存中にエラーが発生しました。詳細：" + e.getMessage());
                e.printStackTrace();
            }
        } else {
            request.setAttribute("errors", errors);
        }

        request.getRequestDispatcher("/score/grade_registration_completed.jsp").forward(request, response);
    }
}

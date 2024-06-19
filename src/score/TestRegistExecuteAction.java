package score;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class TestRegistExecuteAction {
    public String execute(
		HttpServletRequest request, HttpServletResponse response
	)throws Exception {
        //セッションのユーザーデータを取得
        

        // ただのページ遷移のため、フォワードするのみ
		// FrontControllerを使用しているためreturn文でフォワードできる
		return "test_regist.jsp";
    }
}

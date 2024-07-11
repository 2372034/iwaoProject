package score;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCsvUploadAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new ServletException("Content type is not multipart/form-data");
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));

        List<Student> students = new ArrayList<>();
        for (FileItem item : items) {
            if (!item.isFormField()) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(item.getInputStream(), "UTF-8"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(",");
                        if (data.length == 4) {
                            Student student = new Student();
                            student.setEntYear(Integer.parseInt(data[0]));
                            student.setNo(data[1]);
                            student.setName(data[2]);
                            student.setClassNum(data[3]);
                            student.setSchool(school);
                            student.setIsAttend(true);
                            students.add(student);
                        }
                    }
                }
            }
        }

        DataSource ds = getDataSource();
        try (Connection connection = ds.getConnection()) {
            StudentDao dao = new StudentDao();
            for (Student student : students) {
                dao.save(student);
            }
        }

        request.setAttribute("message", "CSVファイルからの学生登録が完了しました。");
        request.getRequestDispatcher("/score/student_completion_of_registration.jsp").forward(request, response);
    }

    private DataSource getDataSource() throws NamingException {
        InitialContext context = new InitialContext();
        return (DataSource) context.lookup("java:comp/env/jdbc/iwaoProject");
    }
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {
    private String baseSql = "SELECT subject_cd, SUBJECT.name, no, point FROM TEST" +
                             " JOIN SUBJECT ON TEST.SUBJECT_CD = SUBJECT.CD" +
                             " WHERE STUDENT_NO = ? AND TEST.SCHOOL_CD = ?";

    private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        List<TestListStudent> results = new ArrayList<>();
        while (rSet.next()) {
            TestListStudent testListStudent = new TestListStudent();
            testListStudent.setSubjectCd(rSet.getString("subject_cd"));
            testListStudent.setSubjectName(rSet.getString("name"));
            testListStudent.setNum(rSet.getInt("no"));
            testListStudent.setPoint(rSet.getInt("point"));
            results.add(testListStudent);

            System.out.println(rSet.getString("subject_cd"));
            System.out.println(rSet.getString("name"));
            System.out.println(rSet.getInt("no"));
            System.out.println(rSet.getInt("point"));
        }
        return results;
    }

    public List<TestListStudent> filter(Student student) throws Exception {
        if (student == null || student.getNo() == null || student.getSchool() == null || student.getSchool().getCd() == null) {
            throw new IllegalArgumentException("Student or its properties are null.");
        }

        List<TestListStudent> results = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rSet = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(baseSql);
            stmt.setString(1, student.getNo());
            stmt.setString(2, student.getSchool().getCd());
            rSet = stmt.executeQuery();

            // Process ResultSet only if there are results
            if (rSet.next()) {
                results = postFilter(rSet); // Process ResultSet
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Database error occurred while filtering TestListStudent.");
        } finally {
            // Close resources in reverse order of creation
            if (rSet != null) {
                try {
                    rSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return results;
    }


}

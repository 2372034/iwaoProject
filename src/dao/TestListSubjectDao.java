package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
    private String baseSql = "SELECT STUDENT.ENT_YEAR, STUDENT.NO AS student_no, STUDENT.NAME AS student_name, " +
                             "STUDENT.CLASS_NUM, TEST.POINT " +
                             "FROM TEST " +
                             "JOIN STUDENT ON TEST.STUDENT_NO = STUDENT.NO " +
                             "WHERE STUDENT.ENT_YEAR = ? AND STUDENT.CLASS_NUM = ? AND TEST.SUBJECT_CD = ? AND TEST.SCHOOL_CD = ?";

    private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
        List<TestListSubject> results = new ArrayList<>();
        while (rSet.next()) {
            TestListSubject testListSubject = new TestListSubject();
            testListSubject.setEntYear(rSet.getInt("ent_year"));
            testListSubject.setStudentNo(rSet.getString("student_no"));
            testListSubject.setStudentName(rSet.getString("student_name"));
            testListSubject.setClassNum(rSet.getString("class_num"));
            testListSubject.putPoint(rSet.getInt("subject_cd"), rSet.getInt("point"));
            results.add(testListSubject);
        }
        return results;
    }

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> results = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rSet = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(baseSql);
            stmt.setInt(1, entYear);
            stmt.setString(2, classNum);
            stmt.setString(3, subject.getCd());
            stmt.setString(4, school.getCd());
            rSet = stmt.executeQuery();
            results = postFilter(rSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Database error occurred while filtering TestListSubject.");
        } finally {
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

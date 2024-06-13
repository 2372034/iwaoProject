package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;
import bean.Teacher;

public class TeacherDao extends Dao {

    // 引数に教員IDを与えると教員情報を返すgetメソッド
    public Teacher get(String id) throws Exception {
        Teacher teacher = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM teacher WHERE id=?");
            statement.setString(1, id);
            rSet = statement.executeQuery();

            if (rSet.next()) {
                teacher = new Teacher();
                teacher.setId(rSet.getString("id"));
                teacher.setPassword(rSet.getString("cd"));
                teacher.setName(rSet.getString("name"));

                // Schoolのインスタンスを作成または取得
                School school = new School();
                teacher.setSchool(school);
            }
        } catch (SQLException e) {
            throw new Exception("Error fetching teacher data", e);
        } finally {
            if (rSet != null) {
                try {
                    rSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return teacher;
    }

    public Teacher login(String id, String password) throws Exception {
        Teacher teacher = null;

        // データベース接続情報
        String url = "jdbc:h2:tcp://localhost/~/iwaoProject";
        String user = "sa";
        String dbPassword = "";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rSet = null;

        try {
            conn = DriverManager.getConnection(url, user, dbPassword);
            String sql = "SELECT * FROM teachers WHERE id = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);

            rSet = pstmt.executeQuery();

            if (rSet.next()) {
                teacher = new Teacher();
                teacher.setId(rSet.getString("id"));
                teacher.setPassword(rSet.getString("password"));
                teacher.setName(rSet.getString("name"));

                // Schoolのインスタンスを作成または取得
                School school = new School();
                teacher.setSchool(school);
            }
        } catch (SQLException e) {
            throw new Exception("Error logging in", e);
        } finally {
            if (rSet != null) {
                try {
                    rSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
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

        return teacher;
    }
}
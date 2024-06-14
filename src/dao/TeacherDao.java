package dao;

import java.sql.Connection;
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
                teacher.setPassword(rSet.getString("password"));
                teacher.setName(rSet.getString("name"));

                // Schoolのインスタンスを作成または取得
                School school = new School();
                school.setCd(rSet.getString("school_cd"));
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

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            // ログインユーザーをteacherテーブルから探す
            String sql = "SELECT * FROM teacher WHERE id = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, password);

            rSet = statement.executeQuery();

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
}

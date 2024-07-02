package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    // 特定の条件でテストを取得するための基本SQLクエリ
    private String baseSql = "SELECT * FROM test WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?";

    // 特定のTestオブジェクトをデータベースから取得するメソッド
    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Test test = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            // SQLステートメントを準備して実行
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, student.getNo());
            statement.setString(2, subject.getCd());
            statement.setString(3, school.getCd());
            statement.setInt(4, no);
            rSet = statement.executeQuery();

            // 結果が見つかった場合、Testオブジェクトを作成してデータを設定
            if (rSet.next()) {
                test = new Test();
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));
                test.setClassNum(rSet.getString("class_num"));
            }
        } catch (SQLException e) {
            throw new Exception("テストデータの取得中にエラーが発生しました", e);
        } finally {
            // リソースを閉じる
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

        return test;
    }

    // 結果セットからテストデータをリストに変換するヘルパーメソッド
    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Test test = new Test();
                // 必要なDAOのインスタンスを取得
                StudentDao studentDao = new StudentDao();
                SubjectDao subjectDao = new SubjectDao();
                // 学生情報を取得して設定
                Student student = studentDao.get(rSet.getString("student_no"));
                test.setStudent(student);
                // 科目情報を取得して設定
                Subject subject = subjectDao.get(rSet.getString("subject_cd"), school);
                test.setSubject(subject);

                test.setSchool(school);
                test.setNo(rSet.getInt("no"));
                int point = rSet.getInt("point");
                if (rSet.wasNull()) {
                    test.setPoint(null); // POINTがnullなら、テストのポイントもnullに設定
                } else {
                    test.setPoint(point);
                }
                test.setClassNum(rSet.getString("class_num"));

                list.add(test);
            }
        } catch (SQLException e) {
            throw new Exception("テストデータの処理中にエラーが発生しました", e);
        }
        return list;
    }

    // フィルタリングされたテストデータを取得するメソッド
    public List<Test> filter(int entYear, String classNum, String subject, int no, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            // SQLクエリを準備して実行
            String sql = "SELECT student.NO AS student_no, student.NAME, student.ENT_YEAR, student.CLASS_NUM, student.SCHOOL_CD, test.SUBJECT_CD, test.NO, test.POINT"
                + " FROM student"
                + " LEFT JOIN ("
                + " SELECT student_no, SUBJECT_CD, NO, POINT"
                + " FROM test"
                + " WHERE no = ? AND subject_cd = ?"
                + " ) AS test"
                + " ON student.no = test.student_no"
                + " WHERE student.school_cd = ? AND student.ent_year = ? AND student.class_num = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, no);
            statement.setString(2, subject);
            statement.setString(3, school.getCd());
            statement.setInt(4, entYear);
            statement.setString(5, classNum);
            rSet = statement.executeQuery();

            // 結果をリストに変換
            list = postFilter(rSet, school);
        } catch (SQLException e) {
            throw new Exception("テストデータのフィルタリング中にエラーが発生しました", e);
        } finally {
            // リソースを閉じる
            try {
                if (rSet != null) {
                    rSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    // リスト内のすべてのテストデータを保存するメソッド
    public boolean save(List<Test> list) throws Exception {
        boolean result = true;
        Connection connection = getConnection();

        try {
            connection.setAutoCommit(false);
            for (Test test : list) {
                if (!save(test, connection)) {
                    result = false;
                }
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new Exception("テストデータの保存中にエラーが発生しました", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    // 単一のテストデータを保存するヘルパーメソッド
 // 単一のテストデータを保存するヘルパーメソッド
    private boolean save(Test test, Connection connection) throws Exception {
        boolean result = false;
        PreparedStatement checkStatement = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            // データが存在するかどうかを確認するためのクエリ
            String checkSql = "SELECT point FROM test WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?";
            checkStatement = connection.prepareStatement(checkSql);
            checkStatement.setString(1, test.getStudent().getNo());
            checkStatement.setString(2, test.getSubject().getCd());
            checkStatement.setString(3, test.getSchool().getCd());
            checkStatement.setInt(4, test.getNo());
            rSet = checkStatement.executeQuery();

            if (rSet.next()) {
                // データが存在する場合はUPDATE文を実行
                String updateSql = "UPDATE test SET point=? WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?";
                statement = connection.prepareStatement(updateSql);
                statement.setInt(1, test.getPoint());
                statement.setString(2, test.getStudent().getNo());
                statement.setString(3, test.getSubject().getCd());
                statement.setString(4, test.getSchool().getCd());
                statement.setInt(5, test.getNo());
            } else {
                // データが存在しない場合はINSERT文を実行
                String insertSql = "INSERT INTO test (student_no, subject_cd, school_cd, no, point, class_num) VALUES (?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(insertSql);
                statement.setString(1, test.getStudent().getNo());
                statement.setString(2, test.getSubject().getCd());
                statement.setString(3, test.getSchool().getCd());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
                statement.setString(6, test.getClassNum()); // クラス番号も保存する
            }
            System.out.println(test.getSubject());
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new Exception("テストの保存中にエラーが発生しました", e);
        } finally {
            if (rSet != null) {
                try {
                    rSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (checkStatement != null) {
                try {
                    checkStatement.close();
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
        }

        return result;
    }

    // リスト内のすべてのテストデータを削除するメソッド
    public boolean delete(List<Test> list) throws Exception {
        boolean result = true;
        Connection connection = getConnection();

        try {
            connection.setAutoCommit(false);
            for (Test test : list) {
                if (!delete(test, connection)) {
                    result = false;
                }
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw new Exception("テストデータの削除中にエラーが発生しました", e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    // 単一のテストデータを削除するヘルパーメソッド
    private boolean delete(Test test, Connection connection) throws Exception {
        boolean result = false;
        PreparedStatement statement = null;

        try {
            String sql = "DELETE FROM test WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, test.getStudent().getNo());
            statement.setString(2, test.getSubject().getCd());
            statement.setString(3, test.getSchool().getCd());
            statement.setInt(4, test.getNo());

            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new Exception("テストの削除中にエラーが発生しました", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}

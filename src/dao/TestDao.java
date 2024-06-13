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
    private String baseSql = "SELECT * FROM test WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?";

    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Test test = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(baseSql);
            statement.setString(1, student.getNo());
            statement.setString(2, subject.getCd());
            statement.setString(3, school.getCd());
            statement.setInt(4, no);
            rSet = statement.executeQuery();

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

    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Test test = new Test();
                Student student = new Student();
                student.setNo(rSet.getString("student_no"));
                test.setStudent(student);

                Subject subject = new Subject();
                subject.setCd(rSet.getString("subject_cd"));
                test.setSubject(subject);

                test.setSchool(school);
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));
                test.setClassNum(rSet.getString("class_num"));

                list.add(test);
            }
        } catch (SQLException e) {
            throw new Exception("テストデータの処理中にエラーが発生しました", e);
        }
        return list;
    }

    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            String sql = "SELECT * FROM test WHERE ent_year=? AND class_num=? AND subject_cd=? AND school_cd=? AND no=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject.getCd());
            statement.setString(4, school.getCd());
            statement.setInt(5, num);
            rSet = statement.executeQuery();

            list = postFilter(rSet, school);
        } catch (SQLException e) {
            throw new Exception("テストデータのフィルタリング中にエラーが発生しました", e);
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

        return list;
    }

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

    private boolean save(Test test, Connection connection) throws Exception {
        boolean result = false;
        PreparedStatement statement = null;

        try {
            String sql = "INSERT INTO test (student_no, subject_cd, school_cd, no, point, class_num) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, test.getStudent().getNo());
            statement.setString(2, test.getSubject().getCd());
            statement.setString(3, test.getSchool().getCd());
            statement.setInt(4, test.getNo());
            statement.setInt(5, test.getPoint());
            statement.setString(6, test.getClassNum());

            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new Exception("テストの保存中にエラーが発生しました", e);
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


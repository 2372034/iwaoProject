package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {

    // 引数に科目コード(cd)と教員が所属している学校(school)を与えると科目を返すgetメソッド
    public Subject get(String cd, School school) throws Exception {
        Subject subject = new Subject();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            // SQLクエリを準備
            statement = connection.prepareStatement("SELECT * FROM subject WHERE cd=? AND school_cd=?");
            statement.setString(1, cd); // 科目コードを設定
            statement.setString(2, school.getCd()); // 学校コードを設定
            ResultSet rSet = statement.executeQuery(); // クエリを実行

            // 結果セットからデータを取得
            if (rSet.next()) {
                subject.setCd(rSet.getString("cd")); // 科目コードを設定
                subject.setName(rSet.getString("name")); // 科目名を設定
                subject.setSchool(school); // 学校オブジェクトを設定
            } else {
                subject = null; // 科目が見つからない場合はnullを返す
            }
        } catch (Exception e) {
            throw e; // 例外をスロー
        } finally {
            // リソースをクローズ
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return subject; // 科目オブジェクトを返す
    }

    // 学校を指定して科目の一覧を取得するメソッド
    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String order = "ORDER BY cd ASC"; // 科目コードで昇順にソート

        try {
            // SQLクエリを準備
            statement = connection.prepareStatement("SELECT * FROM subject WHERE school_cd=?" + order);
            statement.setString(1, school.getCd()); // 学校コードを設定
            rSet = statement.executeQuery(); // クエリを実行

            // 結果セットからデータを取得
            while (rSet.next()) {
                Subject subject = new Subject();
                subject.setCd(rSet.getString("cd")); // 科目コードを設定
                subject.setName(rSet.getString("name")); // 科目名を設定
                subject.setSchool(school); // 学校オブジェクトを設定
                list.add(subject); // リストに追加
            }
        } catch (Exception e) {
            throw e; // 例外をスロー
        } finally {
            // リソースをクローズ
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return list; // 科目リストを返す
    }

    // 科目を保存するメソッド
    public boolean save(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // 既存の科目を取得
            Subject old = get(subject.getCd(), subject.getschool());
            if (old == null) {
                // 新規科目を挿入
                statement = connection.prepareStatement(
                        "INSERT INTO subject (cd, name, school_cd) VALUES (?, ?, ?)");
                statement.setString(1, subject.getCd()); // 科目コードを設定
                statement.setString(2, subject.getName()); // 科目名を設定
                statement.setString(3, subject.getschool().getCd()); // 学校コードを設定
            } else {
                // 既存の科目を更新
                statement = connection.prepareStatement(
                        "UPDATE subject SET name=?, school_cd=? WHERE cd=?");
                statement.setString(1, subject.getName()); // 科目名を設定
                statement.setString(2, subject.getschool().getCd()); // 学校コードを設定
                statement.setString(3, subject.getCd()); // 科目コードを設定
            }
            count = statement.executeUpdate(); // クエリを実行
        } catch (Exception e) {
            throw e; // 例外をスロー
        } finally {
            // リソースをクローズ
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return count > 0; // 更新が成功したかどうかを返す
    }

    // 科目を削除するメソッド
    public boolean delete(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            // SQLクエリを準備
            statement = connection.prepareStatement("DELETE FROM subject WHERE cd=? AND school_cd=?");
            statement.setString(1, subject.getCd()); // 科目コードを設定
            statement.setString(2, subject.getschool().getCd()); // 学校コードを設定
            count = statement.executeUpdate(); // クエリを実行
        } catch (Exception e) {
            throw e; // 例外をスロー
        } finally {
            // リソースをクローズ
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }
        return count > 0; // 削除が成功したかどうかを返す
    }
}

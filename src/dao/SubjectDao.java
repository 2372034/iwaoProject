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
        //科目インスタンスを初期化
        Subject subject = new Subject();
        //データベースへのコネクションを確立
        Connection connection = getConnection();
        //プリペアドステートメント
        PreparedStatement statement = null;

        try {
            //プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement("SELECT * FROM subject WHERE cd=?");
            //プリペアードステートメントに学生番号をバインド
            statement.setString(1, cd);
            //プリペアードステートメントを実行
            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(school);
            } else {
                subject = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
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
        return subject;
    }

    //学校を指定して科目の一覧を取得するメソッド
    @SuppressWarnings("unused")
	private List<Subject> filter(School school) throws Exception {
        //リストの初期化
        List<Subject> list = new ArrayList<>();
        //コネクションを確立
        Connection connection = getConnection();
        //プリペアドステートメント
        PreparedStatement statement = null;
        //リザルトセット
        ResultSet rSet = null;
        //SQL文のソート
        String order = "ORDER BY cd ASC";


        try {
            //プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement("SELECT * FROM subject WHERE school_cd=?" + order);
            //プリペアードステートメントに学校コードをバインド
            statement.setString(1, school.getCd());
            rSet = statement.executeQuery();
            //リストへの格納処理を実行
            while (rSet.next()) {
                Subject subject = new Subject();
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(school);
                list.add(subject);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            //プリペアードステートメントを閉じる
            if (statement != null) {
                try{
                    statement.close();
                }catch (SQLException sqle){
                    throw sqle;
                }
            }
            //コネクションを閉じる
            if (connection != null) {
                try{
                    connection.close();
                }catch (SQLException sqle){
                    throw sqle;
                }
            }
        }
        return list;
    }

    //saveメソッド
    //科目インスタンスをデータベースに保存するメソッド。科目登録、または科目変更、削除の処理で利用する。
    //データが存在する場合は更新、存在しない場合は登録し、戻り値として登録した情報が1件以上あればtrueを、なければfalseを戻す
    public boolean save(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            Subject old = get(subject.getCd(), subject.getschool());
            if (old == null) {
                statement = connection.prepareStatement(
                        "INSERT INTO subject (cd, name, school_cd) VALUES (?, ?, ?)");
                statement.setString(1, subject.getCd());
                statement.setString(2, subject.getName());
                statement.setString(3, subject.getschool().getCd());
            } else {
                statement = connection.prepareStatement(
                        "UPDATE subject SET name=?, school_cd=? WHERE cd=?");
                statement.setString(1, subject.getCd());
                statement.setString(2, subject.getName());
                statement.setString(3, subject.getschool().getCd());
            }
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
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
        return count > 0;
    }

    public boolean delete(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            statement = connection.prepareStatement(
                    "DELETE FROM subject WHERE cd=?");
            statement.setString(1, subject.getCd());
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
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
        return count > 0;
    }
}

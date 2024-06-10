package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;

import bean.Subject;

public class SubjectDao extends Dao{
    public Subject get(String cd, School school) throws Exception{
        //学生インスタンスを初期化
        Subject subject = new Subject();
        //データベースへのコネクションを確立
        Connection connection = getConnection();
        //プリペアドステートメント
        PreparedStatement statement =  null;

        try{
            //プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement("SELECT * FROM subject WHERE cd=?");
            //プリペアードステートメントに学生番号をバインド
            statement.setString(1,cd);
            //プリペアードステートメントを実行
            ResultSet rSet = statement.executeQuery();

            //学校DAOを初期化
            SchoolDao schoolDao = new SchoolDao();

            if (rSet.next()){
                //リザルトセットが存在する場合
                //学生インスタンスに検索結果をセット
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(rSet.getString("school"));
                //学校フィールドには学校コードで検索した学校インスタンスをセット
                //subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
            }else{
                //リザルトセットが存在しない場合
                //学生インスタンスにnullをセット
                subject = null;
            }
        }catch (Exception e){
            throw e;
        }finally{
            //プリペアードステートメントを閉じる
            if (subject != null) {
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
        return subject;
    }
    private List<Subject> filter(School school) throws Exception{
        //リストを初期化
        List<Subject> list = new ArrayList<>();
        //データベースへのコネクションを確立
        Connection connection = getConnection();
        //プリペアドステートメント
        PreparedStatement statement =  null;
        //リザルトセット
        ResultSet rSet = null;
        //SQL文の条件
        //String order = "ORDER BY no ASC";

        try{
            statement = connection.prepareStatement("SELECT * FROM subject WHERE cd=?");
            //プリペアードステートメントに学校コードをバインド
            statement.setString(1,school.getCd());
            statement.setString(2,school.getName());
            //プリペアードステートメントを実行
            rSet = statement.executeQuery();
            //リストへの格納処理を実行
            list = postFilter(rSet, school);
        }catch(Exception e){
            throw e;
        }finally{
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
    public boolean save (Subject subject) throws Exception{
        //コネクションを確立
        Connection connection = getConnection();
        //プリペアードステートメント
        PreparedStatement statement = null;
        //実行件数
        int count = 0;

        try{
            //データベースから学生を取得
            Student old = get(subject.getCd());
            if(old == null){
                //学生が存在しなかった場合
                //プリペアードステートメントにINSERT文をセット
                statement = connection.prepareStatement(
                    "insert into student (cd, name, school) values (?, ?, ?)");
                    //プリペアードステートメントに値をバインド
                    statement.setString(1,subject.getCd());
                    statement.setString(2,subject.getName());
                    statement.setInt(3,subject.getSchool().getCd());
            }else{
                //学生が存在した場合
                //プリペアードステートメントにUPDATE文をセット
                statement = connection.prepareStatement(
                    "UPDATE subject SET cd=?, name=?, school=?,is_attend=? WHERE cd=?"
                );
                //プリペアードステートメントに値をバインド
                statement.setString(1,subject.getCd());
                statement.setString(2,subject.getName());
                statement.setInt(3,subject.getSchool().getCd());
            }
            //プリペアードステートメントを実行
            count = statement.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
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
        //実行件数が1件以上ある場合
        if (count > 0){
            return true;
        }else{
            //実行件数が0件の場合
            return false;
        }
    }
}

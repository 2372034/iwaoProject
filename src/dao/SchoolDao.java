package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;

public class SchoolDao extends Dao{
    public School get(String cd) throws Exception{
    //学生インスタンスを初期化
    School school = new School();
    //データベースへのコネクションを確立
    Connection connection = getConnection();
    //プリペアドステートメント
    PreparedStatement statement =  null;

    try{
        //プリペアードステートメントにSQL文をセット
        statement = connection.prepareStatement("SELECT * FROM school WHERE cd=?");
        //プリペアードステートメントに学生番号をバインド
        statement.setString(1,cd);
        //プリペアードステートメントを実行
        ResultSet rSet = statement.executeQuery();

        if (rSet.next()){
            //リザルトセットが存在する場合
            //学生インスタンスに検索結果をセット
            school.setCd(rSet.getString("cd"));
            school.setName(rSet.getString("name"));
        }else{
            //リザルトセットが存在しない場合
            //学生インスタンスにnullをセット
            school = null;
        }
    }catch (Exception e){
        throw e;
    }finally{
        //プリペアードステートメントを閉じる
        if (school != null) {
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
    return school;
    }
}

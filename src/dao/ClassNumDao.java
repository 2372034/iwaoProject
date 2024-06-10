package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Test;

public class ClassNumDao extends Dao{
    public List<String> filter(School school) throws Exception{
        //リストを初期化
        List<String> list = new ArrayList<>();
        //データベースへのコネクションを確立
        Connection connection = getConnection();
        //プリペアドステートメント
        PreparedStatement statement =  null;
        //リザルトセット
        ResultSet rSet = null;
        //SQL文の条件
        String order = "ORDER BY cd ASC";

        try{
            statement = connection.prepareStatement(baseSql + connection + order);
            //プリペアードステートメントに学校コードをバインド
            statement.setString(1,school.getCd());
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
}

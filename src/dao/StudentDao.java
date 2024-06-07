package dao;

import java.sql.ResultSet;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import bean.Student;

public class StudentDao extends Dao{
    private String baseSql = "SELECT * FROM student WHERE school_cd = ?";
    public Student get(String no) throws Exception{
        //学生インスタンスを初期化
        Student student = new Student();
        //データベースへのコネクションを確立
        Connection connection = getConnection();
        //プリペアドステートメント
        PreparedStatement statement =  null;

        try{
            //プリペアードステートメントにSQL文をセット
            statement = connection.preparedStatement("SELECT * FROM student WHERE no=?");
            //プリペアードステートメントに学生番号をバインド
            statement.setString(1,no);
            //プリペアードステートメントを実行
            ResultSet rSet = statement.executeQuery();

            //学校DAOを初期化
            SchoolDao schoolDao = new SchoolDao();

            if (rSet.next()){
                //リザルトセットが存在する場合
                //学生インスタンスに検索結果をセット
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                student.setAttend(rSet.getBoolean("is_attend"));
                //学校フィールドには学校コードで検索した学校インスタンスをセット
                student.setSchool(schoolDao.get(rSet.getString("school_cd")));
            }else{
                //リザルトセットが存在しない場合
                //学生インスタンスにnullをセット
                student = null;
            }
        }catch (Exception e){
            throw e;
        }finally{
            //プリペアードステートメントを閉じる
            if (student != null) {
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
        return student;
    }

    private List<Student> postFilter(ResultSet rSet, School school) throws Exception{
         //リストの初期化
        List<Student> List = new ArrayList<>();
        try{
             //リザルトセットを全権走査
            while (rSet.next()) {
                 //学生インスタンスを初期化
                Student student = new Student();
                //学生インスタンスに検索結果をセット
                student.setNo(rSet.getString("no"));
                student.setName(rSet.getString("name"));
                student.setEntYear(rSet.getInt("ent_year"));
                student.setClassNum(rSet.getString("class_num"));
                student.setAttend(rSet.getBoolean("is_attend"));
                student.setSchool(school);
                 //リストに追加
                list.add(student);
            }
        }catch (SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return list;
    }

    private List<Student> filter(School school,int entYear, String classNum, boolean isAttend) throws Exception{
        //リストの初期化
        List<Student> list = new ArrayList<>();
        //コネクションを確立
        Connection connection = getConnection();
        //プリペアドステートメント
        PreparedStatement statement =  null;
        //リザルトセット
        ResultSet rSet = null;
        //SQL文の条件
        String condition = "AND ent_year=? AND class_num=?";
        //SQL文のソート
        String order = "ORDER BY no ASC";

        //SQL文の在学フラグ条件
        String conditionIsAttend = "";
        //在学フラグがtrueの場合
        if(isAttend){
            conditionIsAttend = "AND is_attend=ture";
        }

        try{
            //プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + conditionIsAttend + order);
            //プリペアードステートメントに学校コードをバインド
            statement.setString(1,school.getCd());
            //プリペアードステートメントに入学年度をバインド
            statement.steInt(2,entYear);
            //プリペアードステートメントにクラス番号をバインド
            statement.steInt(3,classNum);
            //プライベートステートメントを実行
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

    private List<Student> filter(School school,int entYear, boolean isAttend) throws Exception{
        //リストの初期化
        List<Student> list = new ArrayList<>();
        //コネクションを確立
        Connection connection = getConnection();
        //プリペアドステートメント
        PreparedStatement statement =  null;
        //リザルトセット
        ResultSet rSet = null;
        //SQL文の条件
        String condition = "AND ent_year=?";
        //SQL文のソート
        String order = "ORDER BY no ASC";

        //SQL文の在学フラグ条件
        String conditionIsAttend = "";
        //在学フラグがtrueの場合
        if(isAttend){
            conditionIsAttend = "AND is_attend=ture";
        }

        try{
            //プリペアードステートメントにSQL文をセット
            statement = connection.prepareStatement(baseSql + conditionIsAttend + order);
            //プリペアードステートメントに学校コードをバインド
            statement.setString(1,school.getCd());
            //プリペアードステートメントに入学年度をバインド
            statement.steInt(2,entYear);
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

    private List<Student> filter(School school,boolean isAttend) throws Exception{
        //リストを初期化
        List<Student> list = new ArrayList<>();
        //データベースへのコネクションを確立
        Connection connection = getConnection();
        //プリペアドステートメント
        PreparedStatement statement =  null;
        //リザルトセット
        ResultSet rSet = null;
        //SQL文の条件
        String order = "ORDER BY no ASC";

        //SQL文の在学フラグ
        String conditionIsAttend = "";
        //在学フラグがtrueだった場合
        if (isAttend){
            conditionIsAttend = "and is_attend=true";
        }
        try{
            statement = connection.prepareStatement(baseSql + conditionIsAttend + order);
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

    public boolean save (Student student) throws Exception{
        //コネクションを確立
        Connection connection = getConnection();
        //プリペアードステートメント
        PrepareStatement statement = null;
        //実行件数
        int count = 0;

        try{
            //データベースから学生を取得
            Student old = get(student.getNo());
            if(old == null){
                //学生が存在しなかった場合
                //プリペアードステートメントにINSERT文をセット
                statement = connection.prepareStatement(
                    "insert into student (no, name, ent_year, class_num, is_attend, school_cd) values (?, ?, ?, ?, ?, ?)");
                    //プリペアードステートメントに値をバインド
                    statement.setString(1,student.getNo());
                    statement.setStringa(2,student.getName());
                    statement.setInt(3,student.getEntYear());
                    statement.setString(4,student.getClassNum());
                    statement.setBoolean(5,student.getIsAttend());
                    statement.setString(6,student.getSchool().getCd());
            }else{
                //学生が存在した場合
                //プリペアードステートメントにUPDATE文をセット
                statement = connection.prepareStatement(
                    "UPDATE student SET name=?, ent_tear=?, class_num=?,is_attend=? WHERE no=?"
                );
                //プリペアードステートメントに値をバインド
                statement.setString(1,student.getNo());
                statement.setStringa(2,student.getName());
                statement.setInt(3,student.getEntYear());
                statement.setString(4,student.getClassNum());
                statement.setBoolean(5,student.getIsAttend());
                statement.setString(6,student.getSchool().getCd());
            }
            //プリペアードステートメントを実行
            count statement.executeQuery();
        }catch(Exception e){
            throw e;
        }finally{
            //プリペアードステートメントを閉じる
            if (statement != null) {
                try{
                    statement.close();
                }catch (SQLException sqle){
                    throw sqle
                }
            }
            //コネクションを閉じる
            if (connection != null) {
                try{
                    connection.close();
                }catch (SQLException sqle){
                    throw sqle
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

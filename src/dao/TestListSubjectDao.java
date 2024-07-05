package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
    private String baseSql = "SELECT * FROM student,subject WHERE ";

    public List<TestListSubject> postFilter(ResultSet set) {
        // データベースから結果セットを受け取り、科目リストを返す
        List<TestListSubject> subjectList = new ArrayList<>();
        try {
            while (set.next()) {
                int entYear = set.getInt("STUDENT.ENT_YEAR");
                String studentName = set.getString("STUDENT.NAME");
                String studentNo = set.getString("STUDENT.NO");
                String classNum = set.getString("STUDENT.CLASS_NUM");
                int testNo = set.getInt("TEST.NO");
                int testPoint = set.getInt("TEST.POINT");
                String subjectName = set.getString("SUBJECT.NAME");

                // 同じ学生をリストから探す
                TestListSubject subject = null;
                for (TestListSubject s : subjectList) {
                    if (s.getStudentNo().equals(studentNo)) {
                        subject = s;
                        break;
                    }
                }

                // 見つからなければ新しいオブジェクトを作成してリストに追加
                if (subject == null) {
                    subject = new TestListSubject();
                    subject.setEntYear(entYear);
                    subject.setStudentName(studentName);
                    subject.setStudentNo(studentNo);
                    subject.setClassNum(classNum);
                    subject.setPoints(new HashMap<>()); // 初期化

                    subjectList.add(subject);

                }

                // ポイントを追加
                subject.putPoint(testNo, testPoint);

                // 2回目の値がなければデフォルト値 -1 を格納
                if (!subject.getPoints().containsKey(2)) {
                    subject.putPoint(2, -1);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectList;
    }

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) {
        // 年度、クラス番号、科目、学校に基づいてリストを取得する
        List<TestListSubject> subjectList = new ArrayList<>();
        try (
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT STUDENT.* ,SUBJECT.* ,TEST.* FROM TEST JOIN SUBJECT ON TEST.SUBJECT_CD = SUBJECT.CD JOIN STUDENT ON STUDENT.NO = TEST.STUDENT_NO WHERE SUBJECT.NAME = ? AND STUDENT.ENT_YEAR = ? AND STUDENT.CLASS_NUM = ?"
            )) {
            stmt.setString(1, subject.getName());

            stmt.setInt(2, entYear);

            stmt.setString(3, classNum);

            ResultSet rs = stmt.executeQuery();
            subjectList = postFilter(rs);

            // デバッグ用に取得したリストを出力
            for (TestListSubject subjects : subjectList) {

            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectList;
    }
}
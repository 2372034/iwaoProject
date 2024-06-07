package bean;

import java.io.Serializable;
public class Student implements Serializable {

	private String no;
	private String name;
	private int entYear;
	private String classNum;
    private boolean isAttend;
    private School school;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	public int getEntYear() {
		return  entYear;
	}
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
    public String getClassNum() {
        return classNum;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
<<<<<<< HEAD
	public boolean getIsAttend(boolean isAttend) {
		this.isAttend = isAttend;
	}
    public void setIsAttend(boolean isAttend){
        this.isAttend = isAttend
=======
	public boolean isAttend() {
		return isAttend;
	}
    public void setAttend(boolean isAttend){
        this.isAttend = isAttend;
>>>>>>> dd6c1dd7a048df18a44afdaf8c8d5bd36dfb523d
    }
	public School getSchool() {
		return school;
	}
    public void setSchool(School school) {
        this.school = school;
    }
}



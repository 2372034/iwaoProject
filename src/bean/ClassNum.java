package bean;

import java.io.Serializable;
public class ClassNum implements Serializable {

	private School school;
	private String num;


	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	public String getNum() {
		return num;
    }
    public void setNum(String num) {
        this.num = num;
    }

}


package main.java.pers.jiangyinzuo.rollcall.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public class Student implements Serializable {

	private Long studentId;
	private Byte gender;
	private String studentName;
	private String password;
	private String major;
	private List<TeachingClass> teachingClassList;
	private List<RollCall> rollCallList;

	public Student(Long studentId, Byte gender, String studentName, String password, String major) {
		this.studentId = studentId;
		this.gender = gender;
		this.studentName = studentName;
		this.password = password;
		this.major = major;
	}

	public Student(Long studentId, Byte gender, String studentName, String password, String major,
				   List<TeachingClass> teachingClassList, List<RollCall> rollCallList) {
		this(studentId, gender, studentName, password, major);
		this.teachingClassList = teachingClassList;
		this.rollCallList = rollCallList;
	}

	/**
	 * 用于验证学生登录账号密码
	 * 
	 * @param studentId
	 * @param password
	 */
	public Student(Long studentId, String password) {
		this.studentId = studentId;
		this.password = password;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Byte getGender() {
		return gender;
	}

	public String getGenderStr() {
		if (gender == 1) {
			return "男";
		} else {
			return "女";
		}
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getPwd() {
		return password;
	}

	public void setPwd(String pwd) {
		this.password = pwd;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public List<TeachingClass> getTeachingClassList() {
		return teachingClassList;
	}

	public void setTeachingClassList(List<TeachingClass> teachingClassList) {
		this.teachingClassList = teachingClassList;
	}

	public List<RollCall> getRollCallList() {
		return rollCallList;
	}

	public void setRollCallList(List<RollCall> rollCallList) {
		this.rollCallList = rollCallList;
	}

	public String welcome() {
		return "你好, " + this.studentId + " " + this.studentName;
	}
}

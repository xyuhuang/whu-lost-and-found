package db;
//学生类
public class Student implements java.io.Serializable
{
	//学号
	public String ID;
	//姓名
	public String name;
	//密码
	public String password;
	//联系方式
	public String tel;
	public String qq;
	
	public Student() {}
	
	public Student(String ID, String name, String password, String tel, String qq)
	{
		this.ID = ID;
		this.name = name;
		this.password = password;
		this.tel = tel;
		this.qq = qq;
	}
}

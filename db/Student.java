package db;
//ѧ����
public class Student implements java.io.Serializable
{
	//ѧ��
	public String ID;
	//����
	public String name;
	//����
	public String password;
	//��ϵ��ʽ
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

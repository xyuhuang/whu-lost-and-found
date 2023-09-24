package frames;

import java.awt. * ;
import javax.swing. * ;
import java.awt.event. * ;

public class EditInfo extends JFrame implements ActionListener
{
	//定义域
	public db.Student student;
	public StudentInfo studentInfo;
	
	private JLabel l1 = new JLabel("姓名:");
	private JLabel l2 = new JLabel("密码:");
	private JLabel l3 = new JLabel("再输入:");
	private JLabel l4 = new JLabel("手机号：");
	private JLabel l5 = new JLabel("QQ：");
	
	private JTextField t1 = new JTextField(10);
	private JTextField t2 = new JTextField(20);
	private JTextField t3 = new JTextField(20);
	private JTextField t4 = new JTextField(11);
	private JTextField t5 = new JTextField(11);
	
	private JButton b1 = new JButton("取消");
	private JButton b2 = new JButton("确定");
	
	//构造函数
	public EditInfo(db.Student _student, StudentInfo _studentInfo)
	{
		super("用户信息更改");
		student=_student;
		studentInfo=_studentInfo;
		init();
		this.setSize(300, 300);
		setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	private void init()
	{
		setLayout(null);
		add(l1);
		l1.setBounds(30, 30, 50, 25);
		add(t1);
		t1.setBounds(100, 30, 150, 25);
		add(l2);
		l2.setBounds(30, 60, 50, 25);
		add(t2);
		t2.setBounds(100, 60, 150, 25);
		add(l3);
		l3.setBounds(30, 90, 100, 25);
		add(t3);
		t3.setBounds(100, 90, 150, 25);
		add(l4);
		l4.setBounds(30, 120, 100, 25);
		add(t4);
		t4.setBounds(100, 120, 150, 25);
		add(l5);
		l5.setBounds(30, 150, 100, 25);
		add(t5);
		t5.setBounds(100, 150, 150, 25);
		add(b1);
		b1.setBounds(25,200,100,40);
		b1.addActionListener(this);
		add(b2);
		b2.setBounds(150,200,100,40);
		b2.addActionListener(this);
		
		t1.setText(student.name);
		t2.setText("");
		t3.setText("");
		t4.setText(student.tel);
		t5.setText(student.qq);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == b1)
			b1();
		else if (ae.getSource() == b2)
			b2();
	}
	public void b1()
	{
		this.dispose();
	}
	public void b2()
	{
		String n = t1.getText();
		String p = t2.getText();
		String p1 = t3.getText();
		String ph = t4.getText();
		String q = t5.getText();
		
		if (p.equals(p1))
		{
			db.Student a = new db.Student(student.ID, n, p, ph, q);
			db.Save.saveStudent(a);
			System.out.println("更新成功！");
			studentInfo.student=a;
			studentInfo.refresh();
			this.dispose();
		}
		else
		{
			System.out.println("Password didn't consistent!");
		}
	}
}

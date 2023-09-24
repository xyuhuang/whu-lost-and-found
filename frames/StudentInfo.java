package frames;

import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;

public class StudentInfo extends JFrame implements ActionListener
{
	public db.Student student;
	public Home home;
	
	public JLabel[]o = new JLabel[10];
	public JLabel o1;
	public JLabel o2;
	public JLabel o3;
	public JLabel o4;
	public JButton o5;
	public JButton o6;
	public JTextField Name;
	public JTextField StudentID;
	public JTextField Phone;
	public JTextField QQno;
	
	public StudentInfo(db.Student _student, Home _home)
	{
		super("用户信息");
		student=_student;
		home=_home;
		init();
		//pack();
		this.setSize(300, 300);
		setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	private void init()
	{
		for (int i = 0; i < 7; i++)
		{
			o[i] = new JLabel("    "); //为了实现显示格式而添加的控件
			this.add(o[i]);
		}
		o1 = new JLabel("姓名：");
		Name = new JTextField(10);
		o2 = new JLabel("学号：");
		StudentID = new JTextField(10);
		o3 = new JLabel("手机：");
		Phone = new JTextField(10);
		o4 = new JLabel("Q  Q：");
		QQno = new JTextField(10);
		o5 = new JButton("返        回");
		o6 = new JButton("更        改");
		
		setLayout(null);
		
		add(o1);
		o1.setBounds(30,25,50,30);
		
		add(Name);
		Name.setBounds(100,25,150,30);
		
		add(o2);
		o2.setBounds(30,60,50,30);
		
		add(StudentID);
		StudentID.setBounds(100,60,150,30);
		
		add(o3);
		o3.setBounds(30,95,50,30);
		
		add(Phone);
		Phone.setBounds(100,95,150,30);
		
		add(o4);
		o4.setBounds(30,130,50,30);
		
		add(QQno);
		QQno.setBounds(100,130,150,30);
		
		add(o5);
		o5.setBounds(25,180,100,40);
		o5.addActionListener(this);
		
		add(o6);
		o6.setBounds(150,180,100,40);
		o6.addActionListener(this);
		
		refresh();
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == o5)
			o5();
		else if (ae.getSource() == o6)
			o6();
	}
	private void o5()
	{
		this.dispose();
	}
	private void o6()
	{
		EditInfo editinfo = new EditInfo(student, this);
	}
	
	public void refresh()
	{
		Name.setText(student.name);
		StudentID.setText(student.ID);
		Phone.setText(student.tel);
		QQno.setText(student.qq);
		
		home.student=student;
	}
}

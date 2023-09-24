package frames;

import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;

public class LogIn extends JFrame implements ActionListener
{
	JLabel[]o = new JLabel[4];
	JLabel o1;
	JLabel o2;
	TextField o3;
	TextField o4;
	JButton o5;
	JLabel o6;
	BackgroundPanel bg;

	public LogIn()
	{
		super("用户登录");
		init();
		this.setSize(505,395);
		setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	private void init()
	{
		o1 = new JLabel("学号：");
		o2 = new JLabel("密码：");
o1.setForeground(Color.WHITE);
o2.setForeground(Color.WHITE);
		o3 = new TextField();
		o4 = new TextField();
		o4.setEchoChar('*');
		o5 = new JButton("登录");
		o6 = new JLabel("<html><body style=\"color:#00FFFF\"><br><br>遇到问题请联系管理员<br>邮箱：123@whu.edu.cn</body></html>");
		bg=new BackgroundPanel((new ImageIcon("frames/pic/logInBg.jpg")).getImage());
		
		this.setLayout(null);
		
		this.add(o1);
		o1.setBounds(45, 265, 50, 25);
		this.add(o2);
		o2.setBounds(45, 310, 50, 25);
		this.add(o3);
		o3.setBounds(110, 265, 120, 25);
		o3.addActionListener(this);
		this.add(o4);
		o4.setBounds(110, 310, 120, 25);
		this.add(o5);
		o5.setBounds(290, 265, 80, 30);
		o5.addActionListener(this);
		this.add(o6);
		o6.setBounds(280, 255, 800, 100);
		this.add(bg);
		bg.setBounds(0,0,497,364);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == o5)
			o5();
		else if (ae.getSource() == o3)
			o3();
		
	}
	
	public void o3()
	{
		KeyEvent e = null;
		int key = e.getKeyChar();
		if (key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9)
		{}
		else
		{
			e.consume();
		}
		
	}
	
	public void o5()
	{
		String zh = o3.getText();
		String ma = o4.getText();
		
		db.Student S0 = db.Read.readStudent(zh);
		if (ma.equals(S0.password))
		{
			System.out.println("登录成功");
			Home home = new Home(S0);
			home.setVisible(true);
			this.dispose();
		}
		else
		{
			System.out.println("账号或密码输入错误");
			o3.setText("");
			o4.setText("");
		}
	}

class BackgroundPanel extends JPanel
{
	Image im;
	public BackgroundPanel(Image im)
	{
		this.im=im;
		this.setOpaque(true);
	}
	//Draw the back ground.
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
		
	}
}
	
}



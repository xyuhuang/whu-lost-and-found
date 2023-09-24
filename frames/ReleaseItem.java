package frames;

import java.awt. * ;
import java.awt.event. * ;
import java.awt.Point;
import javax.swing. * ;
import java.text.SimpleDateFormat;
import javax.swing.text.StyledDocument;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReleaseItem extends JFrame implements ActionListener
{
	public Home home;
	public db.MyPoint myPoint = new db.MyPoint(1, 2);
	public StyledDocument styledDocument=null;
	public long time;
	
	public JLabel[]o = new JLabel[10];
	public JLabel l1 = new JLabel("名称：");
	public JLabel l2 = new JLabel("时间：");
	public JLabel l3 = new JLabel("描述：");
	//public JLabel l4 = new JLabel("照片：");
	public JLabel l5 = new JLabel("位置：");
	public JTextField Name = new JTextField(10);
	public JTextField Time = new JTextField(10);
	public JTextField Describe = new JTextField(10);
	public JTextField PhotoName = new JTextField(10);
	public JButton gettime = new JButton("刷新时间");
	//public JLabel o1 = new JLabel("<html><body style=\"color:#FF0000\"><br><br>自动获取1971年至今的毫秒数</body></html>");
	public JButton edit = new JButton("编辑描述");
	ImageIcon img = new ImageIcon("text.jpg"); //设置图片路径，尽量设置为相对路径
	private JTextField SpotPosition = new JTextField(10);
	private JButton spot = new JButton("选择点位");
	private JButton rel = new JButton("发布");
	
	public ReleaseItem(Home _home)
	{
		super("发布招领信息");
		home=_home;
		init();
		this.setSize(500, 400);
		setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	private void init()
	{		
		setLayout(null);
		
		add(l1);
		l1.setBounds(50, 40, 50, 30);
		add(Name);
		Name.setBounds(120, 40, 200, 30);
		
		add(l2);
		l2.setBounds(50, 90, 50, 30);
		add(Time);
		Time.setBounds(120, 90, 200, 30);
		add(gettime);
		gettime.setBounds(330, 90, 100, 30);
		gettime.addActionListener(this);
		
		add(l3);
		l3.setBounds(50, 140, 50, 30);
		add(Describe);
		Describe.setBounds(120, 140, 200, 30);
		
		add(edit);
		edit.setBounds(330, 140, 100, 30);
		edit.addActionListener(this);
		
		add(l5);
		l5.setBounds(50, 190, 50, 30);
		add(SpotPosition);
		SpotPosition.setBounds(120, 190, 200, 30);
		add(spot);
		spot.setBounds(330, 190, 100, 30);
		spot.addActionListener(this);
		
		add(rel);
		rel.setBounds(190, 270, 100, 40);
		rel.addActionListener(this);
		
		
		time = System.currentTimeMillis();
		Time.setText(transferLongToDate("yyyy-MM-dd HH:mm:ss", time));
		Describe.setText(time + "");
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == gettime)
			gettime();
		else if (ae.getSource() == spot)
			spot();
		else if (ae.getSource() == edit)
			edit();
		else if (ae.getSource() == rel)
			rel();
	}
	private void gettime()
	{
		time = System.currentTimeMillis();
		Time.setText(transferLongToDate("yyyy-MM-dd HH:mm:ss", time));
		Describe.setText(time + "");
	}
	private void spot()
	{
		map.ChoosePoint obj = new map.ChoosePoint(home.p0);
		map.MyPoint p = obj.getResult();
		myPoint = new db.MyPoint(p.x,p.y);
		SpotPosition.setText("x=" + p.x + ",y=" + p.y);
	}
	private void edit()
	{
		frames.infoText.InfoText infoText=new frames.infoText.InfoText(this);
	}
	private void rel()
	{
		String pickup = home.student.ID;
		String name = Name.getText();
		String ID = time+"";
		String describe = ID;

		boolean claim = false;
		
		db.Item _item = new db.Item(ID, name, time, myPoint, describe, null, claim, pickup, null);
		db.Save.saveItem(_item);
		
		db.Save.saveDocument(styledDocument, _item.ID);
		System.out.println("发布成功");
		home.loadItem();
		this.dispose();
	}
	
	private String transferLongToDate(String dateFormat, Long millSec)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}
}

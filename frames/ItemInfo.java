package frames;

import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;
import java.text.SimpleDateFormat;

public class ItemInfo extends JFrame implements ActionListener
{
	public db.Item item;
	//定义域
	private JLabel l1 = new JLabel("ID:");
	private JLabel l2 = new JLabel("物品名称:");
	private JLabel l3 = new JLabel("发布时间:");
	private JLabel l4 = new JLabel("物品描述:");
	private JLabel l5 = new JLabel("发布者:");
	
	private JTextField id = new JTextField();
	private JTextField name = new JTextField();
	private JTextField time = new JTextField();
	private JButton describe = new JButton("");
	private JButton person = new JButton("");
	
	public ItemInfo(db.Item _item)
	{
		super("失物信息");
		item = _item;
		init();
		this.setSize(300, 300);
		setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void init()
	{
		setLayout(null);
		
		add(l1);
		add(id);
		add(l2);
		add(name);
		add(l3);
		add(time);
		add(l4);
		//add(jsp);
		//add(describe);
		add(l5);
		add(person);
		
		l1.setBounds(30, 30, 70, 20);
		id.setBounds(100, 30, 150, 20);
		l2.setBounds(30, 65, 70, 20);
		name.setBounds(100, 65, 150, 20);
		l3.setBounds(30, 100, 70, 20);
		time.setBounds(100, 100, 150, 20);
		
		l4.setBounds(30, 145, 70, 20);
		add(describe);
		describe.setBounds(100, 140, 150, 30);
		describe.addActionListener(this);
		
		l5.setBounds(30, 190, 70, 30);
		person.setBounds(100, 190, 150, 30);
		person.addActionListener(this);
		
		id.setText(item.ID);
		name.setText(item.name);
		java.util.Date date = new java.util.Date(item.time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		time.setText(sdf.format(date));
		describe.setText(item.describe);
		person.setText(item.IDPickUp);
		describe.setText(item.describe);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == person)
			person();
		else if (ae.getSource() == describe)
			describe();
	}
	
	private void person()
	{
		db.Student pickup = db.Read.readStudent(item.IDPickUp);
		PostByInfo p = new PostByInfo(pickup);
		p.setVisible(true);
	}
	private void describe()
	{
		System.out.println("show infoText");
		javax.swing.text.StyledDocument doc = db.Read.readDocument(item.describe);
		if(doc!=null)
		{
			frames.infoText.CheckInfoText checkInfoText = new frames.infoText.CheckInfoText(doc);
		}
		else
			System.out.println("信息加载失败");
	}
}

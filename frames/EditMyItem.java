package frames;

import java.awt. * ;
import java.awt.event. * ;
import java.awt.Point;
import javax.swing. * ;
import java.text.SimpleDateFormat;
import javax.swing.text.StyledDocument;

public class EditMyItem extends JFrame implements ActionListener
{
	public MyItem his;
	public Home home;
	public db.Item item;
	public StyledDocument styledDocument = null;
	
	public JLabel[]o = new JLabel[10];
	public JLabel l1 = new JLabel("���ƣ�");
	//public JLabel l2 = new JLabel("ʱ�䣺");
	public JLabel l3 = new JLabel("������");
	public JLabel l5 = new JLabel("λ�ã�");
	public JLabel l7 = new JLabel("����ѧ�ţ�");
	public JTextField Name = new JTextField(10);
	public JTextField Time = new JTextField(10);
	public JTextField Describe = new JTextField(10);
	public JTextField PhotoName = new JTextField(10);
	//public JButton gettime = new JButton("��ȡʱ��");
	//public JLabel o1 = new JLabel("<html><body style=\"color:#FF0000\"><br><br>�Զ���ȡ1971������ĺ�����</body></html>");
	public JButton edit = new JButton("�༭");
	ImageIcon img = new ImageIcon("text.jpg"); //����ͼƬ·������������Ϊ���·��
	private JTextField SpotPosition = new JTextField(10);
	private JButton spot = new JButton("ѡ���λ");
	private JTextField claimID = new JTextField(""); //������
	private JButton rel = new JButton("�޸�");
	
	public EditMyItem(MyItem _his, Home _home, db.Item _item)
	{
		super("�޸�������Ϣ");
		his = _his;
		item = _item;
		home = _home;
		init();
		this.setSize(500, 400);
		setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	private void init()
	{
		javax.swing.text.StyledDocument doc = db.Read.readDocument(item.describe);
		Name.setText(item.name);
		Describe.setText(item.describe);
		SpotPosition.setText("x=" + item.location.X + ",y=" + item.location.Y);
		claimID.setText(item.IDGetBack);
		
		if (doc != null)
		{
			styledDocument = doc;
		}
		else
			System.out.println("��Ϣ����ʧ��");
		
		setLayout(null);
		
		add(l1);
		l1.setBounds(50, 40, 50, 30);
		add(Name);
		Name.setBounds(120, 40, 200, 30);
		
		add(l3);
		l3.setBounds(50, 90, 50, 30);
		add(Describe);
		Describe.setBounds(120, 90, 200, 30);
		
		add(edit);
		edit.setBounds(330, 90, 100, 30);
		edit.addActionListener(this);
		
		add(l5);
		l5.setBounds(50, 140, 50, 30);
		add(SpotPosition);
		SpotPosition.setBounds(120, 140, 200, 30);
		add(spot);
		spot.setBounds(330, 140, 100, 30);
		spot.addActionListener(this);
		
		add(l7);
		l7.setBounds(50, 190, 70, 30);
		add(claimID);
		claimID.setBounds(120, 190, 200, 30);
		
		add(rel);
		rel.setBounds(190, 270, 100, 40);
		rel.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == spot)
			spot();
		else if (ae.getSource() == edit)
			edit();
		else if (ae.getSource() == rel)
			rel();
	}
	
	private void spot()
	{
		map.ChoosePoint obj = new map.ChoosePoint(home.p0);
		map.MyPoint p = obj.getResult();
		item.location = new db.MyPoint(p.x, p.y);
		SpotPosition.setText("x=" + p.x + ",y=" + p.y);
	}
	private void edit()
	{
		frames.infoText.ChangeInfoText changeInfoText = new frames.infoText.ChangeInfoText(this);
	}
	private void rel() //�޸İ�ť
	{
		item.name=Name.getText();
		item.IDGetBack = claimID.getText();
		if (item.IDGetBack.equals(""))
		{
			item.isClaim = false;
		}
		else
		{
			item.isClaim = true;
		}
		db.Save.saveItem(item);
		
		db.Save.saveDocument(styledDocument, item.ID);
		System.out.println("�޸ĳɹ�");
		home.loadItem();
		this.dispose();
	}
}

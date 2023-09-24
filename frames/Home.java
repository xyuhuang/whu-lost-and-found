package frames;

import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;
import java.util.Date;


public class Home extends JFrame implements ActionListener
{
	public db.Student student;
       public db.Item item;
	public int[] timeRange = {0,30};
	public boolean checkIsClaim=false;
	
	public map.MapPanel p0 = new map.MapPanel(); //�����ͼ���
	
	private JButton EditInfo = new JButton("��������");
	private JButton ReleaseI = new JButton("������Ϣ");
	private JButton chooseTime = new JButton("ɸѡʱ��");
	private JButton History = new JButton("�ҵķ���");

	public Home(db.Student _student)
	{
		super("�人��ѧʧ������ϵͳ");
		student=_student;
		add(p0);
		p0.setBounds(10, 10, 800, 600); //���ô�С
		
		long[] time = new long[2];
		time[0]=System.currentTimeMillis()-(long)(1000)*3600*24*timeRange[0];
		time[1]=System.currentTimeMillis()-(long)(1000)*3600*24*timeRange[1];
		db.Item[] itemList=db.Read.readItemList(time, checkIsClaim);
		
		p0.setItemList(itemList); //����Ʒ�б����ͼ����
		p0.refresh(); //ˢ�½��棨�ߴ硢������Ʒˢ�£�
		
		
		setLayout(null);
		//pack();
		setSize(930+20, 620+40);
		setVisible(true);
		this.getContentPane().setBackground(Color.darkGray);
		this.setLocationRelativeTo(null);
		
		add(EditInfo);
		add(ReleaseI);
		add(chooseTime);
		add(History);

		EditInfo.setBounds(820, 20, 100, 30);
		EditInfo.addActionListener(this);
		ReleaseI.setBounds(820, 60, 100, 30);
		ReleaseI.addActionListener(this);
		chooseTime.setBounds(820, 100, 100, 30);
		chooseTime.addActionListener(this);
             History.setBounds(820, 140, 100, 30);
		History.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		if (ae.getSource() == EditInfo)
			EditInfo();
		if (ae.getSource() == ReleaseI)
			ReleaseI();
		if (ae.getSource() == chooseTime)
			chooseTime();
		if (ae.getSource() == History)
			History();
		
	}
	
	public void EditInfo()
	{
		JFrame frame2 = new StudentInfo(student, this);
		frame2.setVisible(true);
	}
	
	public void ReleaseI()
	{
		JFrame frame3 = new ReleaseItem(this);
		frame3.setVisible(true);
	}
	
	public void chooseTime()
	{
		SortItem sortItem = new SortItem(this);
	}
	
	public void History()
	{
		JFrame frame4 = new MyItem(student, this);
	}

	public void loadItem()
	{
		long[] time = new long[2];
		time[0]=System.currentTimeMillis()-(long)(1000)*3600*24*timeRange[0];
		time[1]=System.currentTimeMillis()-(long)(1000)*3600*24*timeRange[1];
		db.Item[] itemList=db.Read.readItemList(time, checkIsClaim);
		System.out.println("ɸѡ��"+new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date(time[0]))+"    ��    "+new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date(time[1])));
		
		p0.setItemList(itemList); //����Ʒ�б����ͼ����
		p0.refresh(); //ˢ�½��棨�ߴ硢������Ʒˢ�£�
	}
}

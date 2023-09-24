package frames;

import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;
import javax.swing.table.DefaultTableCellRenderer;

import java.text.SimpleDateFormat;

import java.util.Date;

public class MyItem extends JFrame
{
	public db.Student student;
	public db.Item item;
	public Home home;
	
	public JButton oA;
	public JButton oB;
	
	public MyItem(db.Student _student, Home _home)
	{
		super("�ҵķ���");
		student = _student;
		//item = _item;
		home = _home;
		init();
		this.setSize(580, 150);
		setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public String transferLongToDate(String dateFormat, Long millSec)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(millSec);
		return sdf.format(date);
	}
	
	private void init()
	{
		db.Item[]itemStudent = db.Read.readItemStudent(student.ID);
		String[]Names =
		{
			"����",
			"ʱ��",
			"�鿴",
			"�༭"
		};
		String[][]data = new String[itemStudent.length][Names.length];
		
		for (int i = 0; i < itemStudent.length; i++)
		{
			data[i][0] = itemStudent[i].name;
			data[i][1] = transferLongToDate("yyyy-MM-dd HH:mm", itemStudent[i].time);
			String text1 = "����鿴";
			String text2 = "����༭";
			data[i][2] = text1;
			data[i][3] = text2;
			
		}
		
		JTable table = new JTable(data, Names);
		
		table.setFont(new Font("������", Font.PLAIN, 15));
		table.setRowHeight(25);
		
		// ���ô˱���ͼ����ѡ��С
		table.setPreferredScrollableViewportSize(new Dimension(600, 300));
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		tcr.setVerticalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		
		// �������뵽�����������
		JScrollPane scrollPane = new JScrollPane(table);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		// �ٽ������������ӵ��м�������
		this.pack();
		//this.setSize(400,300);
		this.setLocationRelativeTo(null);//����
		this.setVisible(true);
		
		
		table.addMouseListener(new java.awt.event.MouseAdapter()
		{
			 @ Override
			public void mouseClicked(java.awt.event.MouseEvent evt)
			{
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());
				String itemID = itemStudent[row].ID;
				if (col == 2)
				{
					checkItem(itemID);
				}
				else if (col == 3)
				{
					editItem(itemID);
				}
			}
		}
		);
	}
	private void checkItem(String _ID)
	{
		db.Item item = db.Read.readItem(_ID);
		System.out.println("�鿴��ʷ����" + _ID);
		ItemInfo window = new frames.ItemInfo(item);
	}
	private void editItem(String _ID)
	{
		db.Item item = db.Read.readItem(_ID);
		System.out.println("�༭��ʷ����" + _ID);
		EditMyItem editMyItem = new EditMyItem(this, home, item);
	}
}

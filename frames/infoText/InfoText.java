package frames.infoText;

import java.awt. * ;
import java.awt.event. * ;
import java.io. * ;
import javax.swing. * ;
import javax.swing.text. * ;

public class InfoText extends JFrame implements ActionListener
{
	public frames.ReleaseItem releaseItem;
	
	private JTextPane text = new JTextPane();
	private JScrollPane scrollPane = new JScrollPane(text); // ����
	private Box box; // ���������������
	private JButton b_insert = new JButton("ȷ��"); ;
	private JButton b_icon = new JButton("����ͼƬ"); ; // ���밴ť;�����ť;����ͼƬ��ť
	private JTextField addText; // ���������
	//     ��������;�ֺŴ�С;������ʽ;������ɫ;���ֱ�����ɫ
	private JComboBox < String > fontName;
	private JComboBox < String > fontSize;
	private JComboBox < String > fontStyle;
	private JComboBox < String > fontColor;
	private JComboBox < String > fontBackColor;
	private JLabel l1 = new JLabel("���壺");
	private JLabel l2 = new JLabel("��ʽ��");
	private JLabel l3 = new JLabel("�ֺţ�");
	private JLabel l4 = new JLabel("��ɫ��");
	private JLabel l5 = new JLabel("������");
	
	private StyledDocument doc; // �ǳ���Ҫ����������ʽ�Ϳ�����
	
	public InfoText(frames.ReleaseItem _releaseItem)
	{
		super("��Ϣ�༭");
		
		releaseItem=_releaseItem;
		if(_releaseItem.styledDocument!=null)
			text.setDocument(_releaseItem.styledDocument);
		init();
	}
	
	private void init()
	{
		doc = text.getStyledDocument(); // ���JTextPane��Document
		
		text.setEditable(true);
		String[]str_name =
		{
			"����",
			"����",
			"Dialog",
			"Gulim"
		};
		String[]str_Size =
		{
			"12",
			"14",
			"18",
			"22",
			"30",
			"40"
		};
		String[]str_Style =
		{
			"����",
			"б��",
			"����",
			"��б"
		};
		String[]str_Color =
		{
			"<html><body><div style=\"color:#000000\">��ɫ</div></body></html>",
			"<html><body><div style=\"color:#FF0000\">��ɫ</div></body></html>",
			"<html><body><div style=\"color:#0000FF\">��ɫ</div></body></html>",
			"<html><body><div style=\"color:#FFFF00\">��ɫ</div></body></html>",
			"<html><body><div style=\"color:#00FF00\">��ɫ</div></body></html>"
		};
		String[]str_BackColor =
		{
			"<html><body><div>��ɫ</div></body></html>",
			"<html><body><div style=\"background-color:#C8C8C8\">��ɫ</div></body></html>",
			"<html><body><div style=\"background-color:#FFC8C8\">����</div></body></html>",
			"<html><body><div style=\"background-color:#C8C8FF\">����</div></body></html>",
			"<html><body><div style=\"background-color:#FFFFC8\">����</div></body></html>",
			"<html><body><div style=\"background-color:#C8FFC8\">����</div></body></html>"
		};
		fontName = new JComboBox < String > (str_name); // ��������
		fontSize = new JComboBox < String > (str_Size); // �ֺ�
		fontStyle = new JComboBox < String > (str_Style); // ��ʽ
		fontColor = new JComboBox < String > (str_Color); // ��ɫ
		fontBackColor = new JComboBox < String > (str_BackColor); // ������ɫ
		// ����
		
		
		this.setLayout(null);
		setSize(800, 600);
		
		add(l1);
		l1.setBounds(10, 5, 40, 25);
		add(fontName);
		fontName.setBounds(50, 5, 60, 25);
		fontName.addActionListener(this);
		add(l2);
		l2.setBounds(120, 5, 40, 25);
		add(fontStyle);
		fontStyle.setBounds(160, 5, 60, 25);
		fontStyle.addActionListener(this);
		add(l3);
		l3.setBounds(230, 5, 40, 25);
		add(fontSize);
		fontSize.setBounds(270, 5, 60, 25);
		fontSize.addActionListener(this);
		add(l4);
		l4.setBounds(340, 5, 40, 25);
		add(fontColor);
		fontColor.setBounds(380, 5, 60, 25);
		fontColor.addActionListener(this);
		add(l5);
		l5.setBounds(450, 5, 40, 25);
		add(fontBackColor);
		fontBackColor.setBounds(490, 5, 60, 25);
		fontBackColor.addActionListener(this);
		add(b_icon);
		b_icon.setBounds(570, 5, 90, 25);
		b_icon.addActionListener(this);
		add(b_insert);
		b_insert.setBounds(700, 5, 60, 25);
		b_insert.addActionListener(this);
		add(scrollPane);
		scrollPane.setBounds(10, 40, 800 - 36, 560 - 48);
		
		text.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				doStyle();
			}
		}
		);
		text.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				doStyle();
			}
		}
		);
		
		this.setLocationRelativeTo(null);//����
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == b_icon)
			b_icon();
		else if (ae.getSource() == b_insert)
			b_insert();
		else if (ae.getSource() == fontName || ae.getSource() == fontStyle || ae.getSource() == fontSize || ae.getSource() == fontColor || ae.getSource() == fontBackColor)
			doStyle();
		
	}
	
	private void b_icon()
	{
		try
		{
			JFileChooser f = new JFileChooser(); // �����ļ�
			f.showOpenDialog(null);
			insertIcon(f.getSelectedFile()); // ����ͼƬ
		}
		catch (Exception e)
		{};
	}
	private void b_insert()
	{
		releaseItem.styledDocument=text.getStyledDocument();
		this.dispose();
	}
	
	private void doStyle()
	{
		System.out.println("changed");
		text.setCharacterAttributes(getFontAttrib().getAttrSet(), true);
	}
	
	/**
	 * ����ͼƬ
	 *
	 * @param icon
	 */
	private void insertIcon(File file)
	{
		text.setCaretPosition(doc.getLength()); // ���ò���λ��
		text.insertIcon(new ImageIcon(file.getPath())); // ����ͼƬ
	}
	
	private FontAttrib getFontAttrib()
	{
		FontAttrib att = new FontAttrib();
		//att.setText(addText.getText());
		att.setText("1");
		att.setName((String)fontName.getSelectedItem());
		att.setSize(Integer.parseInt((String)fontSize.getSelectedItem()));
		String temp_style = (String)fontStyle.getSelectedItem();
		if (temp_style.equals("����"))
		{
			att.setStyle(FontAttrib.GENERAL);
		}
		else if (temp_style.equals("����"))
		{
			att.setStyle(FontAttrib.BOLD);
		}
		else if (temp_style.equals("б��"))
		{
			att.setStyle(FontAttrib.ITALIC);
		}
		else if (temp_style.equals("��б"))
		{
			att.setStyle(FontAttrib.BOLD_ITALIC);
		}
		String temp_color = (String)fontColor.getSelectedItem();
		if (temp_color.equals("<html><body><div style=\"color:#000000\">��ɫ</div></body></html>"))
		{
			att.setColor(new Color(0, 0, 0));
		}
		else if (temp_color.equals("<html><body><div style=\"color:#FF0000\">��ɫ</div></body></html>"))
		{
			att.setColor(new Color(255, 0, 0));
		}
		else if (temp_color.equals("<html><body><div style=\"color:#0000FF\">��ɫ</div></body></html>"))
		{
			att.setColor(new Color(0, 0, 255));
		}
		else if (temp_color.equals("<html><body><div style=\"color:#FFFF00\">��ɫ</div></body></html>"))
		{
			att.setColor(new Color(255, 255, 0));
		}
		else if (temp_color.equals("<html><body><div style=\"color:#00FF00\">��ɫ</div></body></html>"))
		{
			att.setColor(new Color(0, 255, 0));
		}
		String temp_backColor = (String)fontBackColor.getSelectedItem();
		if (!temp_backColor.equals("<html><body><div>��ɫ</div></body></html>"))
		{
			if (temp_backColor.equals("<html><body><div style=\"background-color:#C8C8C8\">��ɫ</div></body></html>"))
			{
				att.setBackColor(new Color(200, 200, 200));
			}
			else if (temp_backColor.equals("<html><body><div style=\"background-color:#FFC8C8\">����</div></body></html>"))
			{
				att.setBackColor(new Color(255, 200, 200));
			}
			else if (temp_backColor.equals("<html><body><div style=\"background-color:#C8C8FF\">����</div></body></html>"))
			{
				att.setBackColor(new Color(200, 200, 255));
			}
			else if (temp_backColor.equals("<html><body><div style=\"background-color:#FFFFC8\">����</div></body></html>"))
			{
				att.setBackColor(new Color(255, 255, 200));
			}
			else if (temp_backColor.equals("<html><body><div style=\"background-color:#C8FFC8\">����</div></body></html>"))
			{
				att.setBackColor(new Color(200, 255, 200));
			}
		}
		return att;
	}
	/**
	 * �����������
	 */
	private class FontAttrib
	{
		public static final int GENERAL = 0; // ����
		
		public static final int BOLD = 1; // ����
		
		public static final int ITALIC = 2; // б��
		
		public static final int BOLD_ITALIC = 3; // ��б��
		
		private SimpleAttributeSet attrSet; // ���Լ�
		
		private String text,
		name; // Ҫ������ı�����������
		
		private int style = 0,
		size = 0; // ��ʽ���ֺ�
		
		private Color color,
		backColor; // ������ɫ�ͱ�����ɫ
		
		/**
		 * һ���յĹ��죨�ɵ�������ʹ�ã�
		 */
		public FontAttrib() {}
		
		/**
		 * �������Լ�
		 *
		 * @return
		 */
		public SimpleAttributeSet getAttrSet()
		{
			attrSet = new SimpleAttributeSet();
			if (name != null)
				StyleConstants.setFontFamily(attrSet, name);
			if (style == FontAttrib.GENERAL)
			{
				StyleConstants.setBold(attrSet, false);
				StyleConstants.setItalic(attrSet, false);
			}
			else if (style == FontAttrib.BOLD)
			{
				StyleConstants.setBold(attrSet, true);
				StyleConstants.setItalic(attrSet, false);
			}
			else if (style == FontAttrib.ITALIC)
			{
				StyleConstants.setBold(attrSet, false);
				StyleConstants.setItalic(attrSet, true);
			}
			else if (style == FontAttrib.BOLD_ITALIC)
			{
				StyleConstants.setBold(attrSet, true);
				StyleConstants.setItalic(attrSet, true);
			}
			StyleConstants.setFontSize(attrSet, size);
			if (color != null)
				StyleConstants.setForeground(attrSet, color);
			if (backColor != null)
				StyleConstants.setBackground(attrSet, backColor);
			return attrSet;
		}
		
		/**
		 * �������Լ�
		 *
		 * @param attrSet
		 */
		public void setAttrSet(SimpleAttributeSet attrSet)
		{
			this.attrSet = attrSet;
		}
		
		/* �����ע�;Ͳ�д�ˣ�һ�������� */
		
		public String getText()
		{
			return text;
		}
		
		public void setText(String text)
		{
			this.text = text;
		}
		
		public Color getColor()
		{
			return color;
		}
		
		public void setColor(Color color)
		{
			this.color = color;
		}
		
		public Color getBackColor()
		{
			return backColor;
		}
		
		public void setBackColor(Color backColor)
		{
			this.backColor = backColor;
		}
		
		public String getName()
		{
			return name;
		}
		
		public void setName(String name)
		{
			this.name = name;
		}
		
		public int getSize()
		{
			return size;
		}
		
		public void setSize(int size)
		{
			this.size = size;
		}
		
		public int getStyle()
		{
			return style;
		}
		
		public void setStyle(int style)
		{
			this.style = style;
		}
	}
	
	/*public static void main(String args[])
	{
		try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("aaa"));
				StyledDocument _doc = (StyledDocument)ois.readObject();
				ois.close();
				
				new InfoText(_doc, true);
			}
			catch (Exception e)
			{
				new InfoText(true);
			}
		
	}
	*/
}

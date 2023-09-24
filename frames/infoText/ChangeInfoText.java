package frames.infoText;

import java.awt. * ;
import java.awt.event. * ;
import java.io. * ;
import javax.swing. * ;
import javax.swing.text. * ;

public class ChangeInfoText extends JFrame implements ActionListener
{
	public frames.EditMyItem editmyitem;
	
	private JTextPane text = new JTextPane();
	private JScrollPane scrollPane = new JScrollPane(text); // 滚动
	private Box box; // 放输入组件的容器
	private JButton b_insert = new JButton("确定"); ;
	private JButton b_icon = new JButton("插入图片"); ; // 插入按钮;清除按钮;插入图片按钮
	private JTextField addText; // 文字输入框
	//     字体名称;字号大小;文字样式;文字颜色;文字背景颜色
	private JComboBox < String > fontName;
	private JComboBox < String > fontSize;
	private JComboBox < String > fontStyle;
	private JComboBox < String > fontColor;
	private JComboBox < String > fontBackColor;
	private JLabel l1 = new JLabel("字体：");
	private JLabel l2 = new JLabel("样式：");
	private JLabel l3 = new JLabel("字号：");
	private JLabel l4 = new JLabel("颜色：");
	private JLabel l5 = new JLabel("背景：");
	
	private StyledDocument doc; // 非常重要插入文字样式就靠它了
	
	public ChangeInfoText(frames.EditMyItem _EditMyItem)
	{
		super("信息编辑");
		
		editmyitem=_EditMyItem;
		if(_EditMyItem.styledDocument!=null)
			text.setDocument(_EditMyItem.styledDocument);
		init();
	}
	
	private void init()
	{
		doc = text.getStyledDocument(); // 获得JTextPane的Document
		
		text.setEditable(true);
		String[]str_name =
		{
			"宋体",
			"黑体",
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
			"常规",
			"斜体",
			"粗体",
			"粗斜"
		};
		String[]str_Color =
		{
			"<html><body><div style=\"color:#000000\">黑色</div></body></html>",
			"<html><body><div style=\"color:#FF0000\">红色</div></body></html>",
			"<html><body><div style=\"color:#0000FF\">蓝色</div></body></html>",
			"<html><body><div style=\"color:#FFFF00\">黄色</div></body></html>",
			"<html><body><div style=\"color:#00FF00\">绿色</div></body></html>"
		};
		String[]str_BackColor =
		{
			"<html><body><div>无色</div></body></html>",
			"<html><body><div style=\"background-color:#C8C8C8\">灰色</div></body></html>",
			"<html><body><div style=\"background-color:#FFC8C8\">淡红</div></body></html>",
			"<html><body><div style=\"background-color:#C8C8FF\">淡蓝</div></body></html>",
			"<html><body><div style=\"background-color:#FFFFC8\">淡黄</div></body></html>",
			"<html><body><div style=\"background-color:#C8FFC8\">淡绿</div></body></html>"
		};
		fontName = new JComboBox < String > (str_name); // 字体名称
		fontSize = new JComboBox < String > (str_Size); // 字号
		fontStyle = new JComboBox < String > (str_Style); // 样式
		fontColor = new JComboBox < String > (str_Color); // 颜色
		fontBackColor = new JComboBox < String > (str_BackColor); // 背景颜色
		// 插入
		
		
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
		
		this.setLocationRelativeTo(null);
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
			JFileChooser f = new JFileChooser(); // 查找文件
			f.showOpenDialog(null);
			insertIcon(f.getSelectedFile()); // 插入图片
		}
		catch (Exception e)
		{};
	}
	private void b_insert()
	{
		editmyitem.styledDocument=text.getStyledDocument();
		this.dispose();
	}
	
	private void doStyle()
	{
		System.out.println("changed");
		text.setCharacterAttributes(getFontAttrib().getAttrSet(), true);
	}
	
	/**
	 * 插入图片
	 *
	 * @param icon
	 */
	private void insertIcon(File file)
	{
		text.setCaretPosition(doc.getLength()); // 设置插入位置
		text.insertIcon(new ImageIcon(file.getPath())); // 插入图片
	}
	
	private FontAttrib getFontAttrib()
	{
		FontAttrib att = new FontAttrib();
		//att.setText(addText.getText());
		att.setText("1");
		att.setName((String)fontName.getSelectedItem());
		att.setSize(Integer.parseInt((String)fontSize.getSelectedItem()));
		String temp_style = (String)fontStyle.getSelectedItem();
		if (temp_style.equals("常规"))
		{
			att.setStyle(FontAttrib.GENERAL);
		}
		else if (temp_style.equals("粗体"))
		{
			att.setStyle(FontAttrib.BOLD);
		}
		else if (temp_style.equals("斜体"))
		{
			att.setStyle(FontAttrib.ITALIC);
		}
		else if (temp_style.equals("粗斜"))
		{
			att.setStyle(FontAttrib.BOLD_ITALIC);
		}
		String temp_color = (String)fontColor.getSelectedItem();
		if (temp_color.equals("<html><body><div style=\"color:#000000\">黑色</div></body></html>"))
		{
			att.setColor(new Color(0, 0, 0));
		}
		else if (temp_color.equals("<html><body><div style=\"color:#FF0000\">红色</div></body></html>"))
		{
			att.setColor(new Color(255, 0, 0));
		}
		else if (temp_color.equals("<html><body><div style=\"color:#0000FF\">蓝色</div></body></html>"))
		{
			att.setColor(new Color(0, 0, 255));
		}
		else if (temp_color.equals("<html><body><div style=\"color:#FFFF00\">黄色</div></body></html>"))
		{
			att.setColor(new Color(255, 255, 0));
		}
		else if (temp_color.equals("<html><body><div style=\"color:#00FF00\">绿色</div></body></html>"))
		{
			att.setColor(new Color(0, 255, 0));
		}
		String temp_backColor = (String)fontBackColor.getSelectedItem();
		if (!temp_backColor.equals("<html><body><div>无色</div></body></html>"))
		{
			if (temp_backColor.equals("<html><body><div style=\"background-color:#C8C8C8\">灰色</div></body></html>"))
			{
				att.setBackColor(new Color(200, 200, 200));
			}
			else if (temp_backColor.equals("<html><body><div style=\"background-color:#FFC8C8\">淡红</div></body></html>"))
			{
				att.setBackColor(new Color(255, 200, 200));
			}
			else if (temp_backColor.equals("<html><body><div style=\"background-color:#C8C8FF\">淡蓝</div></body></html>"))
			{
				att.setBackColor(new Color(200, 200, 255));
			}
			else if (temp_backColor.equals("<html><body><div style=\"background-color:#FFFFC8\">淡黄</div></body></html>"))
			{
				att.setBackColor(new Color(255, 255, 200));
			}
			else if (temp_backColor.equals("<html><body><div style=\"background-color:#C8FFC8\">淡绿</div></body></html>"))
			{
				att.setBackColor(new Color(200, 255, 200));
			}
		}
		return att;
	}
	/**
	 * 字体的属性类
	 */
	private class FontAttrib
	{
		public static final int GENERAL = 0; // 常规
		
		public static final int BOLD = 1; // 粗体
		
		public static final int ITALIC = 2; // 斜体
		
		public static final int BOLD_ITALIC = 3; // 粗斜体
		
		private SimpleAttributeSet attrSet; // 属性集
		
		private String text,
		name; // 要输入的文本和字体名称
		
		private int style = 0,
		size = 0; // 样式和字号
		
		private Color color,
		backColor; // 文字颜色和背景颜色
		
		/**
		 * 一个空的构造（可当做换行使用）
		 */
		public FontAttrib() {}
		
		/**
		 * 返回属性集
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
		 * 设置属性集
		 *
		 * @param attrSet
		 */
		public void setAttrSet(SimpleAttributeSet attrSet)
		{
			this.attrSet = attrSet;
		}
		
		/* 后面的注释就不写了，一看就明白 */
		
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

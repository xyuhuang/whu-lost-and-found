package frames.infoText;

import java.awt. * ;
import java.awt.event. * ;
import java.io. * ;
import javax.swing. * ;
import javax.swing.text. * ;

public class CheckInfoText extends JFrame
{
	private JTextPane text = new JTextPane();
	private JScrollPane scrollPane = new JScrollPane(text); // ����
	
	
	public CheckInfoText(StyledDocument _doc)
	{
		super("��Ϣ�鿴");
		
		text.setDocument(_doc);
		init();
	}
	
	private void init()
	{
		text.setEditable(false);
		
		this.setLayout(null);
		setSize(800, 600);
		
		
		add(scrollPane);
		scrollPane.setBounds(10, 10, 800 - 36, 590 - 48);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}

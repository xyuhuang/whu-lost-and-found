package map;

import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;

public class MapPanelPos extends JLayeredPane implements ActionListener
{
	public JDialog jd;
	public MapPanel mp;
	public BackgroundPos bg = new BackgroundPos(this);
	private JButton zoomUp = new JButton("放大");
	private JButton zoomDown = new JButton("缩小");
	
	private JLabel warning = new JLabel("<html><body style=\"color:#FF0000\"><H1><super>请在屏幕点击选择地点</super><H1></body></html>");
	
	private JButton ensure = new JButton("<html><body><H2><super>确    定</super><H2></body></html>");
	
	public MapPanelPos(JDialog _jd)
	{
		jd=_jd;
		init();
	}
	public MapPanelPos(JDialog _jd, MapPanel _map)
	{
		jd=_jd;
		bg.coords=_map.bg.coords;
		bg.zoom=_map.bg.zoom;
		mp=_map;
		transLayer();
		init();
	}
	private void init()
	{
		setLayout(null);
		
		add(bg);
		
		add(zoomUp);
		moveToFront(zoomUp);
		zoomUp.setBounds(25,25,100,30);
		zoomUp.addActionListener(this);
		
		add(zoomDown);
		moveToFront(zoomDown);
		zoomDown.setBounds(125,25,100,30);
		zoomDown.addActionListener(this);
		
		add(warning);
		moveToFront(warning);
		warning.setBounds(100,25,400,25);
		
		add(ensure);
		moveToFront(ensure);
		ensure.setBounds(25,100,200,50);
		ensure.addActionListener(this);
	}
	public void refresh()
	{
		bg.setBounds(0,0,getSize().width,getSize().height);
		zoomUp.setBounds(getSize().width-200-25,25,100,30);
		zoomDown.setBounds(getSize().width-200+75,25,100,30);
		ensure.setBounds(getSize().width-200-25,80,200,40);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == zoomUp)
			zoomUp();
		else if (ae.getSource() == zoomDown)
			zoomDown();
		else if (ae.getSource() == ensure)
			ensure();
	}
	public void zoomUp()
	{
		if(bg.zoom<12)
		{
			bg.zoom*=1.3;
			transLayer();
			bg.repaint();
		}
	}
	public void zoomDown()
	{
		if(bg.zoom>1)
		{
			bg.zoom/=1.3;
			transLayer();
			bg.repaint();
		}
	}
	private void transLayer()
	{
		if(bg.zoom<2)
			bg.layer=0;
		else if(bg.zoom<4)
			bg.layer=1;
		else if(bg.zoom<8)
			bg.layer=2;
		else
			bg.layer=3;
	}
	private void ensure()
	{
		mp.refresh();
		jd.dispose();
	}
}
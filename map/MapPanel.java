package map;

import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;

public class MapPanel extends JLayeredPane implements ActionListener
{
	public db.Item[] itemList = new db.Item[0];
	private boolean[] itemListState;
	
	public Background bg = new Background(this);
	private JButton zoomUp = new JButton("放大");
	private JButton zoomDown = new JButton("缩小");
	private JLabel warning = new JLabel("<html><body style=\"color:#FF0000\"><H1>物品过多，加载不完全！<H1></body></html>");
	
	private MyLabel[] itemLabel = new MyLabel[100];
	private int itemLabelCount = 0;
	
	public MapPanel()
	{
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
		
		//add(warning);
		moveToFront(warning);
		warning.setBounds(100,25,400,25);
		
		for(int i=0;i<100;i++)
		{
			itemLabel[i] = new MyLabel();
		}
	}
	public void refresh()
	{
		transLayer();
		bg.setBounds(0,0,getSize().width,getSize().height);
		zoomUp.setBounds(getSize().width-200-25,25,100,30);
		zoomDown.setBounds(getSize().width-200+75,25,100,30);
		
		itemListState = new boolean[itemList.length];
		itemLabelCount = 0;
		for(int i=0;i<100;i++)
		{
			itemLabel[i].show=false;
			try
			{
				remove(itemLabel[i]);
			}
			catch(Exception e){}
		}
		bg.repaint();
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == zoomUp)
			zoomUp();
		else if (ae.getSource() == zoomDown)
			zoomDown();
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
	public void transLayer()
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
	public void setItemList(db.Item[] _itemList)
	{
		itemList=_itemList;
	}
	public void showItem()
	{
		int i;
		i=0;
		while(i<100)
		{
			if(itemLabel[i].show==true && !inFrame(itemList[itemLabel[i].itemIndex]))
			{
				itemListState[itemLabel[i].itemIndex]=false;
				itemLabel[i].show=false;
				remove(itemLabel[i]);
				return;
			}
			else if(itemLabel[i].show==true && inFrame(itemList[itemLabel[i].itemIndex]))
			{
				setAppearance(itemLabel[i]);
			}
			i++;
		}
		i=0;
		int count=0;
		while(i<itemList.length)
		{
			if(!itemListState[i] && inFrame(itemList[i]))
			{
				int j;
				for(j=count;j<100;j++)
				{
					if(itemLabel[j].show==false)
					{
						count=j;
						itemLabel[j].itemIndex=i;
						itemLabel[j].item=itemList[i];
						add(itemLabel[j]);
						itemLabel[j].setText("<html><body style=\"color:#0000FF\"><H2>"+itemList[itemLabel[j].itemIndex].name+"<H2></body></html>");
						moveToFront(itemLabel[j]);
						setAppearance(itemLabel[j]);
						itemLabel[j].show=true;
						itemListState[i]=true;
						return;
					}
				}
				if(j>=100)
					add(warning);
			}
			i++;
		}
	}
	private boolean inFrame(db.Item _item)
	{
		
		if(Math.abs(bg.zoom*(_item.location.X-bg.coords.x))<getSize().width/2+50 && Math.abs(bg.zoom*(_item.location.Y-bg.coords.y))<getSize().height/2+12)
			return true;
		else
			return false;
	}
	private void setAppearance(MyLabel obj)
	{
		obj.setBounds((int)(bg.zoom*(itemList[obj.itemIndex].location.X-bg.coords.x)+getSize().width/2-50), (int)(bg.zoom*(itemList[obj.itemIndex].location.Y-bg.coords.y)+getSize().height/2-12), 100, 24);
	}
}
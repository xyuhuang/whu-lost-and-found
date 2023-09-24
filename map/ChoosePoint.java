package map;

import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;

public class ChoosePoint extends JDialog
{
	public MapPanelPos p0;//导入地图组件
	
	public ChoosePoint()
	{
		p0 = new MapPanelPos(this);
		setModalityType(DEFAULT_MODALITY_TYPE);
		add(p0);
		p0.setBounds(10,10,800,600);//设置大小
		p0.refresh();//刷新界面（尺寸、加载物品刷新）
		
		setLayout(null);
		setSize(820+20,610+40);
		this.setLocationRelativeTo(null);
		setTitle("选择地点");
	}
	public ChoosePoint(MapPanel mp)
	{
		p0 = new MapPanelPos(this, mp);
		setModalityType(DEFAULT_MODALITY_TYPE);
		add(p0);
		p0.setBounds(10,10,800,600);//设置大小
		p0.refresh();//刷新界面（尺寸、加载物品刷新）
		
		setLayout(null);
		setSize(820+20,620+40);
		setTitle("选择地点");
		this.setLocationRelativeTo(null);
	}
	public MyPoint getResult()
	{
		setVisible(true);
		return new MyPoint(p0.bg.choosePoint);
	}
}
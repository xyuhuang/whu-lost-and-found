package map;

import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;

public class ChoosePoint extends JDialog
{
	public MapPanelPos p0;//�����ͼ���
	
	public ChoosePoint()
	{
		p0 = new MapPanelPos(this);
		setModalityType(DEFAULT_MODALITY_TYPE);
		add(p0);
		p0.setBounds(10,10,800,600);//���ô�С
		p0.refresh();//ˢ�½��棨�ߴ硢������Ʒˢ�£�
		
		setLayout(null);
		setSize(820+20,610+40);
		this.setLocationRelativeTo(null);
		setTitle("ѡ��ص�");
	}
	public ChoosePoint(MapPanel mp)
	{
		p0 = new MapPanelPos(this, mp);
		setModalityType(DEFAULT_MODALITY_TYPE);
		add(p0);
		p0.setBounds(10,10,800,600);//���ô�С
		p0.refresh();//ˢ�½��棨�ߴ硢������Ʒˢ�£�
		
		setLayout(null);
		setSize(820+20,620+40);
		setTitle("ѡ��ص�");
		this.setLocationRelativeTo(null);
	}
	public MyPoint getResult()
	{
		setVisible(true);
		return new MyPoint(p0.bg.choosePoint);
	}
}
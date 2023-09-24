package frames;

import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;

class SortItem extends JFrame implements ActionListener
{
	public Home home;
	
	
	public JLabel[]o = new JLabel[10];
	public JLabel o1;
	public JTextField o2;
	public JLabel o3;
	public JTextField o4;
	public JLabel o5;
	public JCheckBox o6;
	public JButton o7;
	
	public SortItem(Home _home)
	{
		super("物品筛选");
		home=_home;
		init();
		//pack();
		this.setSize(300, 300);
		setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	private void init()
	{
		o1 = new JLabel("距今  ");
		o2 = new JTextField("00");
		o3 = new JLabel("  --  ");
		o4 = new JTextField("30");
		o5 = new JLabel("  天  ");
		o6 = new JCheckBox("查看已注销物品");
		o7 = new JButton("筛选");
		
		setLayout(null);
		
		add(o1);
		o1.setBounds(50, 50, 40, 30);
		
		add(o2);
		o2.setBounds(80, 50, 60, 30);
		
		add(o3);
		o3.setBounds(140, 50, 30, 30);
		
		add(o4);
		o4.setBounds(160, 50, 60, 30);
		
		add(o5);
		o5.setBounds(220, 50, 30, 30);
		
		add(o6);
		o6.setBounds(50, 120, 130, 30);
		
		add(o7);
		o7.setBounds(90, 170, 100, 40);
		o7.addActionListener(this);
		
		o6.setSelected(home.checkIsClaim);
		o2.setText(String.valueOf(home.timeRange[0]));
		o4.setText(String.valueOf(home.timeRange[1]));
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == o7)
			o7();
	}
	
	private void o7()
	{
		home.timeRange[0]=Integer.parseInt(o2.getText());
		home.timeRange[1]=Integer.parseInt(o4.getText());
		home.checkIsClaim=o6.isSelected();
		
		home.loadItem();
		this.dispose();
	}
}

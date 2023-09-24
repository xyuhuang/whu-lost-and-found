package map;

import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;

class MyLabel extends JLabel
{
	public int itemIndex=-1;
	public boolean show = false;
	public db.Item item;
	
	public MyLabel()
	{
		setHorizontalAlignment(JLabel.CENTER);
		Cursor hourglassCursor = new Cursor(Cursor.HAND_CURSOR);
		setCursor(hourglassCursor);
		
		addMouseListener(new MouseAdp());
	}
	public db.Item getItem()
	{
		return item;
	}
	
	class MouseAdp implements MouseListener
	{
		public void mouseClicked(MouseEvent me)
		{
			clicked();
		}
		public void mouseEntered(MouseEvent me) {}
		public void mouseExited(MouseEvent me) {}
		public void mousePressed(MouseEvent me) {}
		public void mouseReleased(MouseEvent me) {}
	}
	private void clicked()
	{
		frames.ItemInfo window = new frames.ItemInfo(item);
	}
}

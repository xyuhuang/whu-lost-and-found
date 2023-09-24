package map;

import java.awt. * ;
import java.awt.event. * ;
import javax.swing. * ;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

class BackgroundPos extends JPanel
{
	public int layer = 0;
	public BufferedImage[][][] image = new BufferedImage[4][40][24];
	public boolean[][][] exploredImage = new boolean[4][40][24];
	public MyPoint coords = new MyPoint(400, 700);
	public float zoom = 1;
	public final int grid = 256;
	public MapPanelPos mp;
	
	private long pressTime=0;
	public MyPoint choosePoint = new MyPoint(-100000,-100000);
	public BufferedImage crosshair;
	
	public BackgroundPos(MapPanelPos mp)
	{
		init();
		this.mp=mp;
		
		MouseHandler mh = new MouseHandler();
		addMouseListener(mh);
		addMouseMotionListener(mh);
		
		Cursor normalCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		setCursor(normalCursor);
	}
	private void init()
	{
		try
		{crosshair = ImageIO.read(new FileInputStream("map/pic/crosshair220x220_1.png"));}
		catch(Exception e){}
	}
	public void paint(Graphics g)
	{
		int[]size = {getSize().width, getSize().height};
		
		int[] start = {(int)(coords.x*transZoom(zoom)*Math.pow(2,layer))-size[0]/2, (int)(coords.y*transZoom(zoom)*Math.pow(2,layer))-size[1]/2};
		int[] end = {(int)(coords.x*transZoom(zoom)*Math.pow(2,layer))+size[0]/2, (int)(coords.y*transZoom(zoom)*Math.pow(2,layer))+size[1]/2};
		start[0]=(start[0]>0)?start[0]/(int)(grid*transZoom(zoom)):start[0]/(int)(grid*transZoom(zoom))-1;
		start[1]=(start[1]>0)?start[1]/(int)(grid*transZoom(zoom)):start[1]/(int)(grid*transZoom(zoom))-1;
		end[0]=(end[0]>0)?end[0]/(int)(grid*transZoom(zoom)):end[0]/(int)(grid*transZoom(zoom))-1;
		end[1]=(end[1]>0)?end[1]/(int)(grid*transZoom(zoom)):end[1]/(int)(grid*transZoom(zoom))-1;
		
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0,0,size[0],size[1]);
		for(int i=start[1];i<end[1]+1;i++)
		{
			for(int j=start[0];j<end[0]+1;j++)
			{
				if(i>=0 && j>=0 && i<40 && j<24 && !exploredImage[layer][i][j])
				{
					exploredImage[layer][i][j]=true;
					try
					{
						String url = String.format("map/pic/L%02d/R%02d/C%02d.png", layer,i,j);
						image[layer][i][j] = ImageIO.read(new FileInputStream(url));
					}
					catch(Exception e){};
				}
				if(i>=0 && j>=0 && i<40 && j<24 && image[layer][i][j]!=null)
					myPaint(image[layer][i][j], (int)((j*grid-coords.x*Math.pow(2,layer))*transZoom(zoom))+size[0]/2, (int)((i*grid-coords.y*Math.pow(2,layer))*transZoom(zoom))+size[1]/2, (int)(((j+1)*grid-coords.x*Math.pow(2,layer))*transZoom(zoom))+size[0]/2, (int)(((i+1)*grid-coords.y*Math.pow(2,layer))*transZoom(zoom))+size[1]/2, g);
				else
					myPaint((int)((j*grid-coords.x*Math.pow(2,layer))*transZoom(zoom))+size[0]/2, (int)((i*grid-coords.y*Math.pow(2,layer))*transZoom(zoom))+size[1]/2, (int)(((j+1)*grid-coords.x*Math.pow(2,layer))*transZoom(zoom))+size[0]/2, (int)(((i+1)*grid-coords.y*Math.pow(2,layer))*transZoom(zoom))+size[1]/2, g);
			}
		}
		g.setColor(new Color(0, 0, 0));
		g.drawRect(0,0,size[0]-1,size[1]-1);
		
		g.drawImage(crosshair, (int)(zoom*(choosePoint.x-coords.x)+getSize().width/2-30), (int)(zoom*(choosePoint.y-coords.y)+getSize().height/2-30), 60, 60, this);
		
		mp.mp.bg.zoom=mp.bg.zoom;
	}
	private float transZoom(float _zoom)
	{
		if(_zoom<2)
			return _zoom;
		else if(_zoom<4)
			return _zoom/2;
		else if(_zoom<8)
			return _zoom/4;
		else
			return _zoom/8;
	}
	private void myPaint(BufferedImage _image, int x, int y, int x1, int y1, Graphics _g)
	{
		_g.drawImage(_image, x, y, x1-x, y1-y, this);
	}
	private void myPaint(int x, int y, int x1, int y1, Graphics _g)
	{
		_g.setColor(new Color(230, 230, 230));
		_g.drawRect(x, y, x1-x-1, y1-y-1);
	}
	
	public class MouseHandler extends MouseAdapter
	{
		private MyPoint offset;
		private MyPoint start;
		@Override
		public void mousePressed(MouseEvent me)
		{
			offset = new MyPoint(me.getPoint());
			start = new MyPoint(coords);
			pressTime=System.currentTimeMillis();
		}
		@Override
		public void mouseDragged(MouseEvent me)
		{
			float x = me.getX() - offset.x;
			float y = me.getY() - offset.y;
			coords.setLocation((start.x-x/zoom), (start.y-y/zoom));
			repaint();
		}
		@Override
		public void mouseReleased(MouseEvent me)
		{
			if(System.currentTimeMillis()-pressTime<200)
			{
				choosePoint.setLocation(coords.x+(me.getX()-getSize().width/2)/zoom, coords.y+(me.getY()-getSize().height/2)/zoom);
				repaint();
				try
				{
					Thread.sleep(5);
				}
				catch(Exception e)
				{}
			}
		}
	}
}
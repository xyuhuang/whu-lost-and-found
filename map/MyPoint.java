package map;

import java.awt. * ;

public class MyPoint
{
	public float x = 0;
	public float y = 0;
	
	public MyPoint(){}
	public MyPoint(float _x, float _y)
	{
		setLocation(_x, _y);
	}
	public MyPoint(MyPoint p)
	{
		x=p.x; y=p.y;
	}
	public MyPoint(Point p)
	{
		x=p.x; y=p.y;
	}
	
	public void setLocation(float _x, float _y)
	{
		x=_x; y=_y;
	}
}
package db;

public class MyPoint implements java.io.Serializable
{
	public float X;
	public float Y;
	
	public MyPoint() {}
	
	public MyPoint(float x, float y)
	{
		X = x;
		Y = y;
	}
}

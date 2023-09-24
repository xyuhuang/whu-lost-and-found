package db;
//物品类
public class Item implements java.io.Serializable
{
	//物品编号
	public String ID;
	//物品名称
	public String name;
	//创建日期
	public long time;
	//拾取地点
	public MyPoint location;
	//文字描述
	public String describe;
	//图片描述（文件地址）
	public String[] path;
	//认领状态（=true则对象已注销）
	public boolean isClaim;
	//拾取者id
	public String IDPickUp;
	//领回者id
	public String IDGetBack;
	
	public Item() {}
	
	public Item(String ID, String name, long time, MyPoint location, String describe, String[]path, boolean isClaim, String idPickUp, String idGetBack)
	{
		this.ID = ID;
		this.name = name;
		this.time = time;
		this.location = location;
		this.describe = describe;
		this.path = path;
		this.isClaim = isClaim;
		this.IDPickUp = idPickUp;
		this.IDGetBack=idGetBack;
	}
}

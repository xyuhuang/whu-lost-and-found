package db;
//��Ʒ��
public class Item implements java.io.Serializable
{
	//��Ʒ���
	public String ID;
	//��Ʒ����
	public String name;
	//��������
	public long time;
	//ʰȡ�ص�
	public MyPoint location;
	//��������
	public String describe;
	//ͼƬ�������ļ���ַ��
	public String[] path;
	//����״̬��=true�������ע����
	public boolean isClaim;
	//ʰȡ��id
	public String IDPickUp;
	//�����id
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

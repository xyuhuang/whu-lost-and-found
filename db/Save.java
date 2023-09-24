package db;
import java.io. * ;
import javax.swing.text.StyledDocument;

//保存、修改信息
public class Save
{
	//保存、修改学生信息的方法
	public static void saveStudent(Student s)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("db/database/student/" + s.ID));
			oos.writeObject(s);
			oos.close();
		}
		catch (Exception e)
		{System.out.println("save student failed!");}
	}
	
	//保存、修改物品信息的方法
	public static void saveItem(Item it)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("db/database/item/" + it.ID));
			oos.writeObject(it);
			oos.close();
		}
		catch (Exception e)
		{System.out.println("save item failed!");}
	}
	
	public static void saveDocument(StyledDocument doc, String ID)
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("db/database/styledDocument/" + ID));
			oos.writeObject(doc);
			oos.close();
		}
		catch (Exception e)
		{System.out.println("save Document failed!");}
	}
}

package db;

import java.util.ArrayList;
import java.io. * ;
import java.util.Date;
import javax.swing.text.StyledDocument;

public class Read
{
	//查看学生的方法
	public static Student readStudent(String id)
	{
		//    在此目录中找文件
		String baseDIR = "db/database/student";
		//    找对应学号的文件
		String fileName = id;
		
		File file = findFile(baseDIR, fileName);
		
		//返回对应学生信息
		if (file != null)
		{
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("db/database/student/" + id));
				Student s = (Student)ois.readObject();
				ois.close();
				
				return s;
			}
			catch (Exception e)
			{
				return new Student(id, null, "123456", null, null);
			}
		}
		else
		{
			Student s = new Student(id, null, "123456", null, null);
			Save.saveStudent(s);
			return s;
		}
	}
	
	//查看物品的方法
	public static Item readItem(String ID)
	{
		//    在此目录中找文件
		String baseDIR = "db/database/item";
		//    找对应编号的txt文件
		String fileName = ID;
		
		File file = findFile(baseDIR, fileName);
		
		//返回对应物品信息
		if (file != null)
		{
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("db/database/item/" + ID));
				Item item = (Item)ois.readObject();
				ois.close();
				
				return item;
			}
			catch (Exception e)
			{
				return new Item("出错", null, -1, null, null, null, false, null, null);
			}
		}
		else
		{
			Item item = new Item("出错", null, -1, null, null, null, false, null, null);
			return item;
		}
	}
	
	//筛查物品的方法   (起始时间time_search[0]，截至时间time_search[1])
	public static Item[] readItemList(long[] time_search, boolean claim)
	{
		if(time_search[0]>time_search[1])//顺序
		{
			long l=time_search[0];
			time_search[0]=time_search[1];
			time_search[1]=l;
		}
		//在此目录中找文件
		String baseDIR = "db/database/item";
		File[]files = getFiles(baseDIR);
		
		//遍历文件，返回对应物品信息
		Item item = new Item();
		ArrayList < Item > array = new ArrayList < Item > ();
		
		for (File file: files)
		{
			if (file != null)
			{
				try
				{
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					item = (Item)ois.readObject(); //将file文件内容存入item
					ois.close();
					
					//对时间进行判断，满足条件则将item存入数组array
					
					if ((item.time >= time_search[0]) && (item.time <= time_search[1]))
					{
						//System.out.println(new Date(System.currentTimeMillis())+"    "+new Date(time_search[0])+"    "+new Date(item.time)+"    "+new Date(time_search[1]));
						if (claim == false)
						{ //claim==false则不返回注销对象
							if (item.isClaim == false)
							{
								array.add(item);
							}
						}
						else
						{
							array.add(item);
						}
					}
				}
				catch (Exception e)
				{}
			}
		}
		
		Item[]items = new Item[array.size()];
		for (int i = 0; i < array.size(); i++)
		{
			items[i] = array.get(i);
		}
		return items;
	}
	

	//按发布者筛查物品的方法
	public static Item[] readItemStudent(String studentID)
	{
		//在此目录中找文件
		String baseDIR = "db/database/item";
		File[]files = getFiles(baseDIR);
		
		//遍历文件，返回对应物品信息
		Item item = new Item();
		ArrayList < Item > array = new ArrayList < Item > ();
		
		for (File file: files)
		{
			if (file != null)
			{
				try
				{
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					item = (Item)ois.readObject(); //将file文件内容存入item
					ois.close();
					
					//对发布id进行判断，满足条件则将item存入数组array
					
					if (item.IDPickUp.equals(studentID))
						array.add(item);
				}
				catch (Exception e)
				{}
			}
		}
		
		Item[]items = new Item[array.size()];
		for (int i = 0; i < array.size(); i++)
		{
			items[i] = array.get(i);
		}
		return items;
	}


	//查找指定文件夹下的文件
	public static File findFile(String baseDirName, String targetFileName)
	{
		File baseDir = new File(baseDirName); // 创建一个File对象
		
		String tempName;
		
		File tempFile;
		File[]files = baseDir.listFiles();
		if (files.length == 0)
		{ //该文件夹下没有文件，为空文件夹
			return null;
		}
		for (int i = 0; i < files.length; i++)
		{
			tempFile = files[i];
			tempName = tempFile.getName();
			if (tempName.equals(targetFileName))
			{
				return tempFile.getAbsoluteFile();
			}
		}
		return null;
	}
	
	//获取目录中所有文件的名称，把获取的多个名称存入File数组返回
	public static File[] getFiles(String pathName)
	{
		File file = new File(pathName);
		File[] files = file.listFiles();
		
		return files;
	}
	
	public static StyledDocument readDocument(String ID)
	{
		String baseDIR = "db/database/styledDocument";
		String fileName = ID;
		File file = findFile(baseDIR, fileName);
		
		if (file != null)
		{
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("db/database/styledDocument/" + ID));
				StyledDocument s = (StyledDocument)ois.readObject();
				ois.close();
				
				return s;
			}
			catch (Exception e)
			{
				return null;
			}
		}
		else
		{
			return null;
		}
	}
}

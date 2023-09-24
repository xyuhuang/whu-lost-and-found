package db;

import java.util.ArrayList;
import java.io. * ;
import java.util.Date;
import javax.swing.text.StyledDocument;

public class Read
{
	//�鿴ѧ���ķ���
	public static Student readStudent(String id)
	{
		//    �ڴ�Ŀ¼�����ļ�
		String baseDIR = "db/database/student";
		//    �Ҷ�Ӧѧ�ŵ��ļ�
		String fileName = id;
		
		File file = findFile(baseDIR, fileName);
		
		//���ض�Ӧѧ����Ϣ
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
	
	//�鿴��Ʒ�ķ���
	public static Item readItem(String ID)
	{
		//    �ڴ�Ŀ¼�����ļ�
		String baseDIR = "db/database/item";
		//    �Ҷ�Ӧ��ŵ�txt�ļ�
		String fileName = ID;
		
		File file = findFile(baseDIR, fileName);
		
		//���ض�Ӧ��Ʒ��Ϣ
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
				return new Item("����", null, -1, null, null, null, false, null, null);
			}
		}
		else
		{
			Item item = new Item("����", null, -1, null, null, null, false, null, null);
			return item;
		}
	}
	
	//ɸ����Ʒ�ķ���   (��ʼʱ��time_search[0]������ʱ��time_search[1])
	public static Item[] readItemList(long[] time_search, boolean claim)
	{
		if(time_search[0]>time_search[1])//˳��
		{
			long l=time_search[0];
			time_search[0]=time_search[1];
			time_search[1]=l;
		}
		//�ڴ�Ŀ¼�����ļ�
		String baseDIR = "db/database/item";
		File[]files = getFiles(baseDIR);
		
		//�����ļ������ض�Ӧ��Ʒ��Ϣ
		Item item = new Item();
		ArrayList < Item > array = new ArrayList < Item > ();
		
		for (File file: files)
		{
			if (file != null)
			{
				try
				{
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					item = (Item)ois.readObject(); //��file�ļ����ݴ���item
					ois.close();
					
					//��ʱ������жϣ�����������item��������array
					
					if ((item.time >= time_search[0]) && (item.time <= time_search[1]))
					{
						//System.out.println(new Date(System.currentTimeMillis())+"    "+new Date(time_search[0])+"    "+new Date(item.time)+"    "+new Date(time_search[1]));
						if (claim == false)
						{ //claim==false�򲻷���ע������
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
	

	//��������ɸ����Ʒ�ķ���
	public static Item[] readItemStudent(String studentID)
	{
		//�ڴ�Ŀ¼�����ļ�
		String baseDIR = "db/database/item";
		File[]files = getFiles(baseDIR);
		
		//�����ļ������ض�Ӧ��Ʒ��Ϣ
		Item item = new Item();
		ArrayList < Item > array = new ArrayList < Item > ();
		
		for (File file: files)
		{
			if (file != null)
			{
				try
				{
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
					item = (Item)ois.readObject(); //��file�ļ����ݴ���item
					ois.close();
					
					//�Է���id�����жϣ�����������item��������array
					
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


	//����ָ���ļ����µ��ļ�
	public static File findFile(String baseDirName, String targetFileName)
	{
		File baseDir = new File(baseDirName); // ����һ��File����
		
		String tempName;
		
		File tempFile;
		File[]files = baseDir.listFiles();
		if (files.length == 0)
		{ //���ļ�����û���ļ���Ϊ���ļ���
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
	
	//��ȡĿ¼�������ļ������ƣ��ѻ�ȡ�Ķ�����ƴ���File���鷵��
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

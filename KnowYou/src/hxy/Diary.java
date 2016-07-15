package hxy;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Diary 
{
	public static void addDiary(String pathname, String title, String txt) 
	{
		File dirfile = new File(pathname);
		BufferedWriter bufw = null;
		dirfile.mkdirs();
		File file = new File(dirfile, title + ".ky");
		try 
		{
			bufw = new BufferedWriter(new FileWriter(file, true));
			bufw.write(txt);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (bufw != null) 
			{
				try 
				{
					bufw.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}

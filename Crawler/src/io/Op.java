package io;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import bean.CityWeather;

public class Op 
{
	public static void List2File(ArrayList<CityWeather> list, String file) throws IOException
	{
	  	Writer w = new FileWriter(file, true);
		BufferedWriter buffWriter = new BufferedWriter(w);
		for(CityWeather cw : list)
		{
			buffWriter.write(cw.toString());
		}
		buffWriter.close();
		w.close();
	}
}

package test;
import java.io.IOException;
import java.util.ArrayList;
import service.Crawler;
import bean.CityWeather;
import bean.War;
import io.Op;

public class Th extends Thread
{
	static String url = "http://datacenter.mep.gov.cn/report/air_daily/air_dairy.jsp";
	static String[] params = { "city", "startdate", "enddate", "page" };
	String[] values = { "", "2016-01-01", "2016-05-01", "1" };
	static int requestMethod = War.GET;
	static String selector = "td.report1_4";
	War war;

    public Th(int a) throws Exception
    {
    	values[3] = String.valueOf(a);
    	war = new War(url, params, values, requestMethod, selector);
    }
    
	public void run()
	{
		ArrayList<CityWeather> list = null;
		try 
		{
			while(true)
			{
				list = Crawler.getResults(war);
				if(list.size() > 0) break;
				else System.out.println("fail");
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			Op.List2File(list, "data/file((" + Integer.parseInt(values[3]) + ")).csv");
		} 
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}

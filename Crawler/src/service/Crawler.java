package service;
import bean.CityWeather;
import bean.War;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection;
import java.io.IOException;
import java.util.ArrayList;

public class Crawler 
{
	public static ArrayList<CityWeather> getResults(War war) throws IOException  
	{
		Connection conn = Jsoup.connect(war.getUrl());
		if(war.getParams() != null)
		{
			for(int i = 0; i < war.getParams().length; i++)
			{
				conn.data(war.getParams()[i], war.getValues()[i]);
			}
		}
		Document doc = null;
		if(war.getRequestMethod() == War.GET)
		{
			doc = conn.timeout(10000).get();
		}
		else
		{
			doc = conn.timeout(10000).post();
		}
		Elements results = doc.select(war.getSelector());
		ArrayList<CityWeather> list = new ArrayList<CityWeather>();
		int i = 0;
		CityWeather cw = null;
		for(Element e: results)
		{
			switch(i % CityWeather.ITEM_NUMBER)
			{
			case 0:
				cw = new CityWeather();
				cw.setId(e.text());
				break;
			case 1:
				cw.setCity(e.text());
				break;
			case 2:
				cw.setDate(e.text());
				break;
			case 3:
				cw.setAqi(e.text());
				break;
			case 4:
				cw.setAql(e.text());
				break;
			default:
				cw.setPp(e.text().replace(',', '|'));
				list.add(cw);
			}
			i++;
		}
		return list;
	}
}
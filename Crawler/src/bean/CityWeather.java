package bean;

public class CityWeather 
{
	public final static int ITEM_NUMBER = 6;
	private String id;
	private String city;
	private String date;
	private String aqi;
	private String aql;
	private String pp;
	
	public CityWeather(){}
	
	public CityWeather(String id, String city, String date, 
			String aqi, String aql, String pp)
	{
		this.id = id;
		this.city = city;
		this.date = date;
		this.aqi = aqi;
		this.aql = aql;
		this.pp = pp;
	}
	
	public String getId() 
	{
		return id;
	}
	
	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getCity() 
	{
		return city;
	}
	
	public void setCity(String city) 
	{
		this.city = city;
	}
	
	public String getDate() 
	{
		return date;
	}
	
	public void setDate(String date) 
	{
		this.date = date;
	}
	
	public String getAqi() 
	{
		return aqi;
	}
	
	public void setAqi(String aqi) 
	{
		this.aqi = aqi;
	}
	
	public String getAql() 
	{
		return aql;
	}
	
	public void setAql(String aql) 
	{
		this.aql = aql;
	}
	
	public String getPp() 
	{
		return pp;
	}
	
	public void setPp(String pp) 
	{
		this.pp = pp;
	}
	
	@Override
	public String toString()
	{
		return id + ", " + city + ", " + date + ", " + aqi +
				", " + aql + ", " + pp + "\n";
	}
}

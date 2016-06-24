package bean;

public class War 
{
	//定义常量
	public final static int GET = 0;
	public final static int POST = 1;
	//待访问网址的url
	private String url;
	//参数列表
	private String[] params;
	//对应参数值
	private String[] values;
	//请求类型，默认为GET
	private int requestMethod;
	//待获取元素的选择器语法表示
	private String selector;
	
	public War(String url, String[] params, String[] values, 
			int requestMethod, String selector)
	{
		this.url = url;
		this.params = params;
		this.values = values;
		this.requestMethod = requestMethod;
		this.setSelector(selector);
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public String[] getParams()
	{
		return params;
	}
	
	public void setParams(String[] params)
	{
		this.params = params;
	}
	
	public String[] getValues()
	{
		return values;
	}
	
	public void setValues(String[] values)
	{
		this.values = values;
	}
	
	public int getRequestMethod()
	{
		return requestMethod;
	}
	
	public void setRequestMethod(int requestMethod)
	{
		this.requestMethod = requestMethod;
	}

	public String getSelector() 
	{
		return selector;
	}

	public void setSelector(String selector) 
	{
		this.selector = selector;
	}
}

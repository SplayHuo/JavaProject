package hxy;

public class Register 
{
	public static String checkName(String name) 
	{
		return null;
	}

	public static String checkId(String id) 
	{
		if (id.matches("\\d{1,8}")) 
		{
			return null;
		} 
		else
		{
			return "ID not conform to the rules";
		}
	}

	public static String checkPassword(String password) 
	{
		if (password.matches("\\d{6,15}")) 
		{
			return null;
		} 
		else
		{
			return "Password not conform to the rules";
		}
	}

	public static String register(String name, String password, String id)
	{
		return (JDOM.write(name, password, id));
	}
}
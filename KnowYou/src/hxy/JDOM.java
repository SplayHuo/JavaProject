package hxy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class JDOM 
{
	public static String write(String n, String p, String id)
	{
		String path = ".\\UserInfo.xml";
		File file = new File(path);
		SAXBuilder saxBuilder = new SAXBuilder();
		Document doc;
		try 
		{
			doc = saxBuilder.build(file);
			Element root = doc.getRootElement();
			Element user = new Element("user");
			Element name = new Element("name");
			Element password = new Element("password");
			if (checkId(id, root)) 
			{
				user.setAttribute(new Attribute("id", id));
				name.setText(n);
				password.setText(p);
				user.addContent(name);
				user.addContent(password);
				root.addContent(user);
				XMLOutputter out = new XMLOutputter();
				out.output(doc, new FileOutputStream(file));
				return "Successful registration";
			} 
			else
			{
				return "ID already exists, please input again";
			}
		}
		catch (JDOMException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return "ERROR";
	}

	public static boolean checkId(String id, Element root) 
	{
		@SuppressWarnings("unchecked")
		List<Element> list = root.getChildren("user");
		Iterator<Element> it = list.iterator();
		while (it.hasNext()) 
		{
			Element e = (Element) it.next();
			if (e.getAttributeValue("id").equals(id)) 
			{
				return false;
			}
		}
		return true;
	}

	public static String read(String id, String password)
	{
		String path = ".\\UserInfo.xml";
		File file = new File(path);
		SAXBuilder saxBuilder = new SAXBuilder();
		try 
		{
			Document doc = saxBuilder.build(file);
			Element root = doc.getRootElement();
			String info = getPassword(root).get(id);
			if (info == null) 
			{
				return "User does not exist!!";
			}
			String[] buf = info.split("/");
			if (buf[0].equals(password)) 
			{
				return "Successful landing/" + buf[1];
			}
		}
		catch (JDOMException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return "Wrong password!!";
	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> getPassword(Element root) 
	{
		Map<String, String> map = new TreeMap<String, String>();
		List<Element> list = new ArrayList<Element>();
		list = root.getChildren("user");
		Iterator<Element> it = list.iterator();
		while (it.hasNext()) 
		{
			Element e = it.next();
			String id = e.getAttributeValue("id");
			String password = e.getChildText("password");
			String name = e.getChildText("name");
			map.put(id, getInfo(password, name));
		}
		return map;
	}

	private static String getInfo(String password, String name) 
	{
		return password + "/" + name;
	}
}

package test;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ExtractLinks 
{
    public static void main(String[] args) throws IOException   
    {
    	String url = "http://www.cnblogs.com/huoxiayu/";
    	Document doc = Jsoup.connect(url).get();
    	Elements results = doc.select("a[href]");
        System.out.println("Total " + results.size() + " links");
        for(Element e : results)
        {
        	String name = e.text();
        	String href = e.attr("abs:href");
        	System.out.println(name + " : " + href);
        }
        System.out.println("‘À––Ω· ¯");
	}
}

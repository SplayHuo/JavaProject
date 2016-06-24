package test;
import java.io.*;
import java.net.URL;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DownloadImages 
{
    public static void main(String[] args) throws IOException   
    {
    	//目标网站
    	String url = "http://www.zgfjqw.roboo.com/";
    	//获取到的图片的存储位置，这里采用的是相对路径，存储到项目中的img文件夹下
    	String path = "img";
    	Document doc = Jsoup.connect(url).timeout(100000).get();
    	//获取所有图像元素
        Elements results = doc.getElementsByTag("img");
        for(Element e : results)
        {
        	//获取图像的url
        	String src = e.absUrl("src");
        	//存储到本地的函数
            storeImg(src, path);
        }
        System.out.println("运行结束");
    }
    
    //从文件的url获得其文件名，比如http://www.zgfjqw.roboo.com/a/b/c.gif转化为c.gif
    private static String getName(String src)
    {
    	int index = src.lastIndexOf('/');
    	return src.substring(index);
    }

    //将src存储到path中
    private static void storeImg(String src, String path) throws IOException 
    {        
        String name = getName(src);
        URL url = new URL(src);
        InputStream in = url.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(path + name));
        for(int r;(r = in.read()) != -1; )
        {
        	out.write(r);
        }
        in.close();
        out.close();
    }
}

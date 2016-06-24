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
    	//Ŀ����վ
    	String url = "http://www.zgfjqw.roboo.com/";
    	//��ȡ����ͼƬ�Ĵ洢λ�ã�������õ������·�����洢����Ŀ�е�img�ļ�����
    	String path = "img";
    	Document doc = Jsoup.connect(url).timeout(100000).get();
    	//��ȡ����ͼ��Ԫ��
        Elements results = doc.getElementsByTag("img");
        for(Element e : results)
        {
        	//��ȡͼ���url
        	String src = e.absUrl("src");
        	//�洢�����صĺ���
            storeImg(src, path);
        }
        System.out.println("���н���");
    }
    
    //���ļ���url������ļ���������http://www.zgfjqw.roboo.com/a/b/c.gifת��Ϊc.gif
    private static String getName(String src)
    {
    	int index = src.lastIndexOf('/');
    	return src.substring(index);
    }

    //��src�洢��path��
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

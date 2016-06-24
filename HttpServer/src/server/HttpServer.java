package server;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer
{
	public static void main(String[] args) throws IOException 
	{
		int port = 80;
		new HttpServer().start(port);
	}
	
	public void start(int port) throws IOException 
	{
		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(port);
		System.out.println("Server has started at port: " + port);
		while(true)
		{
			Socket client = server.accept();
			Thread thread = new Thread(new Services(client));
			thread.start();
		}
	}
	
	class Services implements Runnable
	{
		Socket client;
		
		public Services(Socket client)
		{
			this.client = client;
		}
		
		private byte[] getResource(String resource) throws IOException
		{
			File file = new File(resource);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1000];
			int read;
			while((read = fis.read(buffer)) != -1)
			{
				baos.write(buffer, 0, read);
			}
			fis.close();
			baos.close();
			return baos.toByteArray();
		}
		
		private String getHead(String url)
		{
			//使用\\对.进行正则表达式中的转义
			String[] arr = url.split("\\.");
			String fileType = arr[arr.length - 1];
			if(fileType.equals("html"))
			{
				return "HTTP/1.0200OK\n" + "Content-Type:text/html\n" + "Server:myserver\n\n";
			}
			else if(fileType.equals("jpg") || fileType.equals("gif") || fileType.equals("png"))
			{
				return "HTTP/1.0200OK\n" + "Content-Type:image/jpeg\n" + "Server:myserver\n\n";
			}
			else
			{
				return null;
			}
		}
		
		@Override
		public void run()
		{
			try
			{
				InputStream is = client.getInputStream();
				OutputStream os = client.getOutputStream();
				client.setSoTimeout(500);
				String url = "";
				int state = 0;
				while(true)
				{
					int readInt = is.read();
					char c = (char)readInt;
					boolean space = Character.isWhitespace(c);
					switch (state)
					{
					case 0:
						if(space) continue;
						else state = 1;
					case 1:
						if(space)
						{
							state = 2;
							continue;
						}
						else
						{
							continue;
						}
					case 2:
						if(space) continue;
						else state = 3;
					case 3:
						if(space) break;
						else 
						{
							url += c;
							continue;
						}
					}
					break;
				}
				while(true)
				{
					try
					{
						@SuppressWarnings("unused")
						int readInt = is.read();
					} 
					catch(InterruptedIOException e)
					{
						byte[] data = getResource("webapp" + url);
						os.write(getHead(url).getBytes("utf-8"));
						os.write(data);
						os.close();
						break;
					}
				}
			} 
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}

package test;

public class Test 
{
	private static final int N = 50;
	
	public static void main(String[] args) throws Exception
	{
        for(int i = 1; i <= N; i++)
        {
        	Th tt = new Th(i);
        	tt.start();
        }
	}
}

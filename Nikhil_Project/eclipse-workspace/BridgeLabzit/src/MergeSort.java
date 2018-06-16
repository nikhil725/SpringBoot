import java.io.*;
import java.util.*;
public class MergeSort {
	public static void main(String args[]) throws IOException
	{
		Scanner sc=new Scanner(System.in);
		String source="/home/bridgeit/gangadhar/Java/algorithm_pgm/file.txt";
		LinkedList al=new LinkedList(); 
		
		FileReader fr=new FileReader(source);
		BufferedReader br=new BufferedReader(fr);
		String temp;
		while((temp=br.readLine())!=null)
		{
			System.out.println(temp);
			StringTokenizer str=new StringTokenizer(temp,",");
			while(str.hasMoreTokens())
			{
				String token=str.nextToken();
				al.add(token);
			}
		}
		System.out.println("enter the string to search");
		String key=sc.next();
		int i,cmp,p=-1;
		for(i=0;i<temp.length();i++)
		{
			cmp=key.compareTo(al);
		}
		
	}
}
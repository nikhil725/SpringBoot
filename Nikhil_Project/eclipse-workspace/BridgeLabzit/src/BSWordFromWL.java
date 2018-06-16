import java.io.*;
import java.util.*;
public class BSWordFromWL {
	public static void main(String[] args) throws Exception {		
		Scanner sc=new Scanner(System.in);	
		String source="/home/bridgeit/gangadhar/Java/algorithm_pgm/file.txt";		
		BSWordFromWL bs=new BSWordFromWL();	
		String temp="";
		try
		{
			FileReader fr=new FileReader(source);
			BufferedReader br=new BufferedReader(fr);
			while((temp=br.readLine())!=null)
			{
				System.out.println(temp);				
				int ans=bs.WordLength(temp);				
				System.out.println("enter the string to search");
				String key=sc.nextLine();				
				int result=bs.arrayConv(temp, ans, key);				
				if(result==-1)
				{
					System.out.println("String not found ");
				}
				else
				{
					System.out.println("String found at "+result);
				}				
			}
		}
		catch(Exception e)
		{
			System.out.println("File not found ");
		}
	}	
	public int WordLength(String s)
	{
		int n=0;
		StringTokenizer str=new StringTokenizer(s,",");
		while(str.hasMoreTokens())
		{
			String token=str.nextToken();
			n++;
		}
		return n;
	}	
	public int arrayConv(String s,int n,String key)
	{
		String arr[]=new String[n];
		int i=0;
		StringTokenizer str=new StringTokenizer(s,",");
		while(str.hasMoreTokens())
		{
			String token=str.nextToken();
			arr[i]=token;
			i++;
		}		
		for(i=0; i<n; i++)
		{
			for (int j=i+1; j<n; j++)
			{
				if(arr[i].compareToIgnoreCase(arr[j])>0)
				{
					String dummy=arr[i];
					arr[i]=arr[j];
					arr[j]=dummy;
				}
			}
		}		
		System.out.println("sorting of words are ");		
		for(i=0; i<n; i++)
		{
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		int low=0,high=n-1;
		int mid;
		while(low<=high)
		{
			mid=(high+low)/2;
			
			if(key.compareToIgnoreCase(arr[mid])==0)
			{
				return mid;
			}
			else if(key.compareToIgnoreCase(arr[mid])>0)
			{
				low=mid+1;
				high=high;
			}
			else if(key.compareToIgnoreCase(arr[mid])<0)
			{
				low=low;
				high=mid-1;
			}
		}
		return -1;
	}
}
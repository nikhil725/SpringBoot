import java.util.Scanner;
class StringSort{
	public void insert_sort(String[] word,int n)
	{
		for(int i=1; i<n; i++)
		{
			String temp=word[i];
			int j=i-1;
			while(j>=0 && word[j].compareToIgnoreCase(temp)>0)
			{
				word[j+1]=word[j];
				j--;	
			}
			word[j+1]=temp;
		}
	}
}
public class StringWordInstSort {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("entar the number ");
		int n=sc.nextInt();
		String word[]=new String[n];
		
		System.out.println("enter the words "); 
		for (int i = 0; i <n; i++)
		   {	      
		       word[i] = sc.next();
		   }	
		StringSort ss=new StringSort();
		ss.insert_sort(word, n);	
		System.out.println("string after sorting ");
		for(int i=0; i<n; i++)
		{
			System.out.print(word[i]+" ");
		}
	}
	

}
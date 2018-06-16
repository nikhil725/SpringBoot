import java.util.*;
public class BubbleSort {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the size");
		int n=sc.nextInt();
		int arr[]=new int[n];
		int i;
		System.out.println("enter the numbers");
		for(i=0;i<=arr.length-1;i++)
		{
			arr[i]=sc.nextInt();
		}
		int m=n-1;
		while(m>0)
		{
			for(i=0;i<m;i++)
			{
				if(arr[i]>arr[i+1])
				{
					int temp;
					temp=arr[i];
					arr[i]=arr[i+1];
					arr[i+1]=temp;
				}
			}
			m--;
		}
		System.out.println("the sorted numbers are");
		for(i=0;i<=arr.length-1;i++)
		{
			System.out.print(arr[i]+" ");
		}
	}

}

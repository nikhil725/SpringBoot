import java.util.*;
class PrimePalindrome
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the size");
		int size;
		size=sc.nextInt();
		int i,j;
		for(i=1;i<size;i++)
		{
			boolean flag=true;
			for(j=2;j<i;j++)
			{
				if(i%j==0)
				{
					flag=false;
					break;
				}
			}
			if(flag==true)
			{
				int rem,num=i,sum=0;
				while(num>0)
				{
					rem = num % 10;
					sum = (sum*10)+rem;
					num=num/10;
				}
				if(sum==i)
				{
					System.out.println(i);
				}
			}
		}
	}
}
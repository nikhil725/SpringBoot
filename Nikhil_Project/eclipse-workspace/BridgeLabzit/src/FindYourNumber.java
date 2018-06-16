import java.util.*;
public class FindYourNumber {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		//int n=Integer.parseInt(args[0]);
		System.out.println("enter the size");
		int n=sc.nextInt();
		int low=0,high=n-1;
		int mid,a;
		while(true)
		{
			mid=(high+low)/2;
			System.out.println("the number between "+low+" and "+mid+" pess 1 else 0");
			a=sc.nextInt();
			if(a==1)
			{
				low=low;
				high=mid;	
			}
			else {
				low=mid+1;
				high=high;
			}
			if(low==high)
			{
				System.out.println("the number is"+low);
				System.exit(0);
			}
		}
		
	}

}

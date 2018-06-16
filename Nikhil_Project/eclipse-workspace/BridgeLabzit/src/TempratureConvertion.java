import java.util.*;
class TempratureConvertion
{
	static double temperaturConversion(int n,float value)
	{
		double ans;
		if(n==0)
		{
			value=value+32;
			ans=1.8*value;
			return ans;
		}
		if(n==1)
		{
			value=value-32;
			ans=value*0.5556;
			return ans;
		}
		return 0;
	}
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		int n;
		float value;
		double ans;
		System.out.println("0 to convert celsius to fahrenheit ");
		System.out.println("1 to convert fahrenheit to celsius");
		n=sc.nextInt();
		switch(n)
		{
			case 0: System.out.println("enter the celsius value ");
					value=sc.nextInt();
					ans=temperaturConversion(n,value);
					System.out.println("the fahrenheit value is "+ans+" .");
					break;
			case 1: System.out.println("enter the fahrenheit value ");
					value=sc.nextInt();
					ans=temperaturConversion(n,value);
					System.out.println("the Celsius value is "+ans+" .");
					break;
			default : System.out.println("you entered a invalid number");
				break;
		}
		
	}
}
import java.util.*;
public class Payment {
	
	public static void main(String args[])
	{
		//int y=Integer.parseInt(args[0]);
		//int p=Integer.parseInt(args[1]);
		//float r=Float.parseFloat(args[2]);
		Scanner sc = new Scanner(System.in);
	    System.out.println("enter the principal amount ");
	    int p = sc.nextInt();
	    System.out.println("interest rate ");
	    Float r = sc.nextFloat();
	    System.out.println("How many years");
	    int y = sc.nextInt();
		payment(y,p,r);
	}
	public static void payment(int y,int p,float r)
	{
			int n=12*y;
			float i=r/(12*100);
			double pow=Math.pow((1+i),(-n));
			double payment=p*i/(1-pow);
			
			//System.out.println("My monthly payments will be " + payment);
		   // System.out.println("Total Interest Paid is " + intetotal);
		    System.out.println("Total amount paid is " + payment);
	}
}

import java.util.Scanner;
public class SwapBinaryNibbles {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the input");
		int num=sc.nextInt();
		String temp=(String.format("%8s",Integer.toBinaryString(num)).replace(" ", "0"));
		//char arr[]=temp.toCharArray();
		char[] m=new char[8];
		m=temp.toCharArray();
		char[] n=new char[8];
		int i,j=0;
		for (i =4 ; i<8 ; i++) {
			n[j]=m[i];
			j++;
		}
		for(i=0;i<4;i++)
		{
			n[j]=m[i];
			j++;
		}
	
		for (i =0 ; i<8 ; i++) {
			System.out.print(m[i]+" ");
		}
		System.out.println();
		System.out.println();
		System.out.println("swaped nibbles are");		
		for(i=0;i<8;i++)
		{
			System.out.print(n[i]+" ");
		}
		
		System.out.println();
		int pow=0;
		j=7;
		for (i =0 ; i<8 ; i++) {
			if(n[i]=='1')
			{
				pow +=(int)Math.pow(2, j);
				
			}  
			
			j--;
		}
		System.out.print(pow+" ");
		i=1;
		while(i<pow)
		{
			i=i*2;
		}
		if(i==pow)
		{
			System.out.println("number is power of two");
		}
		else {
			System.out.println("number is not a power of two");
		}
	}
}

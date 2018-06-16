import java.util.*;
public class ToBinary {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the input");
		int num=sc.nextInt();
		String temp=(String.format("%16s",Integer.toBinaryString(num)).replace(" ", "0"));
		char arr[]=temp.toCharArray();
		int i;
		int j=15;
		for (i =0 ; i<arr.length ; i++) {
			if(arr[i]=='1')
			{
				int pow =(int)Math.pow(2, j);
				System.out.print(pow+" ");
			}  
			j--;
		}
	}

}

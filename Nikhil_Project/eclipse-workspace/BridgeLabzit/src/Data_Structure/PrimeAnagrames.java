package Data_Structure;
import java.util.*;
public class PrimeAnagrames {
 
	    public static boolean isPrime(int n) 
	    {
	        int c = 0;
	        for(int i = 1; i<=n; i++)
	        {
	            if(n%i == 0)
	                c++;
	        }
	        if(c == 2)
	            return true;
	        else
	            return false;
	    }
	   public static void main(String args[])
	   {
	      Scanner sc=new Scanner(System.in);
	      System.out.println("enter the row size");
	      int m=sc.nextInt();
	      System.out.println("enter the col size");
	      int n=sc.nextInt();
	      int A[][]=new int[m][n];
	      int B[] = new int [m*n];       
	      int i = 0, j;
	      int k = 1;
	      int temp = 0;
	      while(i < m*n)
	      {
	          if(isPrime(k)==true)
	          {
	                B[i] = k;
	                i++;
	          }
	          k++;
	          temp=B[i];
	      }
	      int rev[]=new int [m*n];
	      int rem=0;
	      int sum=0;
	      while(temp>0)
	      {
	    	  rem=temp/10;
	    	  sum=sum*10+rem;
	    	  temp=temp%10;
	      }
	      if(sum==temp)
	      {
	    	  System.out.println(sum);
	      }
	      
	      int x = 0;
	      for(i=0; i<m; i++)
	      {
	         for(j=0; j<n; j++)
	         {
	            A[i][j] = B[x];
	            x++;
	         }
	      }
	      System.out.println("The Filled Array is :");
	      for(i=0; i<m; i++)
	      {
	           for(j=0; j<n; j++)
	           {
	               System.out.print(A[i][j]+"\t");
	           }
	           System.out.println();
	      }
	   }
	}
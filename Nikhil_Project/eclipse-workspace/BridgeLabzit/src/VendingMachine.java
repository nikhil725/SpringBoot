import java.util.*;
class VendingMachine
{
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		System.out.println("enter the amount");
		int N=scan.nextInt();	
		int Notes[]={1000,500,100,50,10,5,2,1};
		int i=0;
		int TotalNotes=0;
		count_notes(N,Notes,i,TotalNotes);
	}

	public static void count_notes(int N,int Notes[],int i,int TotalNotes)
	{
		int count=0;
		if(N==0)
		{
			System.out.println("Total number of notes :"+ TotalNotes);
			System.exit(0);
		}
		else
		{
			count=N/Notes[i];
			TotalNotes=TotalNotes+count;
			System.out.println(Notes[i]+"Rs notes are  "+count);
			N=N%Notes[i];
			i++;
		}
		count_notes(N,Notes,i,TotalNotes);
	}
}
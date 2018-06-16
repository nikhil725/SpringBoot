import java.util.*;
public class Anagrames {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the string one");
		String s1=sc.nextLine();
		System.out.println("enter the string two");
		String s2=sc.nextLine();
		s1=s1.replaceAll("\\s", " ");
		s2=s2.replaceAll("\\s", " ");
		if(s1.length()!=s2.length())
		{
			System.out.println("two strings are not anagrames");
		}
		else {
			s1=s1.toLowerCase();
			s2=s2.toLowerCase();
			char arr1[] =s1.toCharArray();
			char arr2[] =s2.toCharArray();
			Arrays.sort(arr1);
			Arrays.sort(arr2);
			boolean status=Arrays.equals(arr1, arr2);
			if(status==true)
			{
				System.out.println("two strings are anagrames");
			}
			else {

				System.out.println("two strings are not anagrames");	
			}
		}
	}

}

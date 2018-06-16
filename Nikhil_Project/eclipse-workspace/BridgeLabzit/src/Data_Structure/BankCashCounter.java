package Data_Structure;

import java.util.*;
public class BankCashCounter
{
    public static void main(String args[] )
    { 
        int balance = 5000, withdraw, deposit;
        Scanner s = new Scanner(System.in);
        while(true)
        {
            System.out.println("Welcome to SBI Bank");
            System.out.println("choice the option");
            System.out.println("1 to Withdraw");
            System.out.println("2 to Deposit");
            System.out.println("3 to Check Balance");
            System.out.println("4 to EXIT");
            System.out.print("Choose the operation you want to perform:");
            int n = s.nextInt();
            switch(n)
            {
                case 1:
                System.out.print("Enter money to be withdrawn:");
                withdraw = s.nextInt();
                if(balance >= withdraw)
                {
                    balance = balance - withdraw;
                    System.out.println("Please collect your money");
                }
                else
                {
                    System.out.println("Insufficient Balance");
                }
                System.out.println("");
                break;
 
                case 2:
                System.out.print("Enter money to be deposited:");
                deposit = s.nextInt();
                balance = balance + deposit;
                System.out.println("Your Money has been successfully depsited");
                System.out.println("");
                break;
 
                case 3:
                System.out.println("Balance : "+balance);
                System.out.println("");
                break;
 
                case 4:
                System.exit(0);
            }
        }
        
        
    }
}
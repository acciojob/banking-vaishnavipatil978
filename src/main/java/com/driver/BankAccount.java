package com.driver;
import java.util.*;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    private String accountNumber;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception

        if(sum>0 && digits>0 && digits*9>=sum){
            int[] accNo = new int[digits];

            if(sum<=9){
                accNo[0]=sum;
            }
            else if(sum%digits==0){
                Arrays.fill(accNo,sum/digits);
            }
            else{
                int num = sum/digits+1;
                Arrays.fill(accNo,num);
                accNo[digits-1] = sum - (num*(digits-1));
            }

            String result = new String();

            for(int x:accNo) result+= String.valueOf(x);

            this.accountNumber = result;

            return result;
        }
        else{
            throw new Exception("Account Number can not be generated");
        }
    }

    public void deposit(double amount) {
        //add amount to balance
        balance += amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(amount<balance) balance-=amount;

        if(balance<minBalance) throw new Exception("Insufficient Balance");

    }


    public double getBalance() {
        return balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

}
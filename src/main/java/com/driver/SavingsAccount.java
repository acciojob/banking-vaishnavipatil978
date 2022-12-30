package com.driver;
import java.util.*;

public class SavingsAccount extends BankAccount{
    double rate;
    double maxWithdrawalLimit;

    public double getRate() {
        return rate;
    }

    public double getMaxWithdrawalLimit() {
        return maxWithdrawalLimit;
    }

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        // minimum balance is 0 by default
        super(name,balance,0);
        this.maxWithdrawalLimit = maxWithdrawalLimit;
        this.rate = rate;
    }
    public void withdraw(double amount) throws Exception {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance

        if(amount>maxWithdrawalLimit) throw new Exception("Maximum Withdraw Limit Exceed");
        if(amount>getBalance()) throw new Exception("Insufficient Balance");

        super.withdraw(amount);
    }

    public double getSimpleInterest(int years){
        // Return the final amount considering that bank gives simple interest on current amount
         return getBalance()+((getBalance()*rate*years)/100);
    }

    public double getCompoundInterest(int times, int years){
        // Return the final amount considering that bank gives compound interest on current amount given times per year
        return getBalance() * (Math.pow( (100+(rate/times))/100, times*years));
    }

}

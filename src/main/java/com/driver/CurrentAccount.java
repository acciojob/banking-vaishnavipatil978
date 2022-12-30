package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        boolean valid = this.isValid();

        if (valid==true) {
            return;
        }

        int[] charArr = new int[26];
        int n = tradeLicenseId.length();

        int max = 0;
        char maxChar = 'A';

        String result = "";

        for(int i = 0 ; i < n ; i++){
            int num = (tradeLicenseId.charAt(i)-'A');
            charArr[num]++;

            if(max<charArr[num]){
                max = charArr[num];
                maxChar = tradeLicenseId.charAt(i);
            }

            result += ' ';
        }

        if(max>(n+1)/2) throw new Exception("Valid License can not be generated");

        int index = 0;

        while(max>0){
            result = result.substring(0,index)+maxChar+result.substring(index+1);
            max--;
            index+=2;
        }

        charArr[maxChar-'A']=0;

        for(int i = 0 ; i < 26 ; i++){
            while(charArr[i]>0){
                index = (index>=n) ? 1:index;
                result = result.substring(0,index) + (char)('A'+i) + result.substring(index+1);
                charArr[i]--;
                index+=2;
            }
        }

        this.tradeLicenseId = result;

    }

    public boolean isValid(){
        int n = tradeLicenseId.length();

        for(int i = 1 ; i < n ; i++){
            if(tradeLicenseId.charAt(i)==tradeLicenseId.charAt(i-1)) return false;
        }

        return true;
    }

}

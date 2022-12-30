package com.driver;
import java.util.*;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        if(balance<5000){
            throw new Exception("Insufficient Balance");
        }

        this.tradeLicenseId=tradeLicenseId;



    }
    public String getTradeLicenseId(){
        return tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        String s=tradeLicenseId;
        boolean flag=false;
        for(int i=1;i<tradeLicenseId.length();i++){
            if(s.charAt(i-1)==s.charAt(i)){
                flag=true;
                break;
            }
        }
        if(flag==true){
            String ans=rearrangeString(s);
            if(ans.length()!=s.length()){
                throw new Exception("Valid License can not be generated");
            }
            else {
                this.tradeLicenseId = ans;
            }

        }

    }
    class KeyComparator implements Comparator<Key> {

        // Overriding compare()method of Comparator
        public int compare(Key k1, Key k2)
        {
            if (k1.freq < k2.freq)
                return 1;
            else if (k1.freq > k2.freq)
                return -1;
            return 0;
        }
    }
    class Key {

        int freq; // store frequency of character
        char ch;
        Key(int val, char c)
        {
            freq = val;
            ch = c;
        }
    }
    public String rearrangeString(String str) {
        int MAX_CHAR = 26;
        int n = str.length();


        int[] count = new int[MAX_CHAR];

        for (int i = 0; i < n; i++)
            count[str.charAt(i) - '0']++;


        PriorityQueue<Key> pq
                = new PriorityQueue<>(new KeyComparator());
        for (char c = '0'; c <= '9'; c++) {
            int val = c - '0';
            if (count[val] > 0)
                pq.add(new Key(count[val], c));
        }


        str = "";

        Key prev = new Key(-1, '#');

        // traverse queue
        while (pq.size() != 0) {


            Key k = pq.peek();
            pq.poll();
            str = str + k.ch;


            if (prev.freq > 0)
                pq.add(prev);


            (k.freq)--;
            prev = k;
        }


        return str;
    }


}

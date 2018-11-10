package org.pursuit;

import java.util.HashMap;

public class Customer {
    private String name;
    private HashMap<Integer, Double> accounts = new HashMap<>();

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public HashMap<Integer, Double> getAccounts(){
        return accounts;
    }

    public void setAccounts(HashMap<Integer, Double> accounts) {
        this.accounts = accounts;
    }

    public void deleteAccount(int accountNum){
        accounts.remove(accountNum);
    }

    public void addAccount(int accountNumber, double balance ){
        accounts.put(accountNumber, balance);
    }

    public double withdrawl(double amount, int accountNumber){
        if(accounts.containsKey(accountNumber)){
           double value = accounts.get(accountNumber);  //this gives the value
            return value - amount;
        }else{
            return 0;  //put if statement checking for 0
        }
    }

    public double deposit(double amount, int accountNumber){
        if(accounts.containsKey(accountNumber)){
            double value = accounts.get(accountNumber);  //this gives the value
            return value + amount;
        }else{
            return 0;  //put if statement checking for 0
        }
    }
}


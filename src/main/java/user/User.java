package user;
import account.*;
import consoleMenu.Console;

import java.util.ArrayList;



public class User {
    private String userName;
    private ArrayList<Account> accounts;

    public User()
    {
        this.userName = "" ; //Entered by user during creating new user
        this.accounts= new ArrayList<Account>();
    }

    public User(String userName, ArrayList<Account> accounts) {
        this.userName = userName;
        this.accounts = accounts;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userId) {
        this.userName = userId;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    public ArrayList<Account> getAccountsByUserName(String userName) {
        if(this.userName.equals(userName))
          return accounts;
        else
            return new ArrayList<Account>();
    }
    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(String accountId, Double balance,String accountType){
        this.accounts.add(new Account(accountId, balance, accountType));
    }

    public void removeAccount(String accountId){
        for (Account account : accounts){
            if (account.getAccountId().equalsIgnoreCase(accountId)){
                this.accounts.remove(account);
                return;
            }
        }
    }

    public static void transfer(Account currentAccount, Account destinationAccount, Double transferAmount){
        Double newTransferAmount = transferAmount;
        while (newTransferAmount > currentAccount.getBalance()) {
            System.out.println("I know you owe them money but money doesn't grow on trees your current balance is too low\n" +
                    "to transfer that much. You have " + currentAccount.getBalance() + " available try again.");
            newTransferAmount = Console.getDoubleInput("Please enter the amount :  ");
        }
        currentAccount.withdraw(transferAmount);
        destinationAccount.deposit(transferAmount);
    }

 public String getUserNameByAccount(String accountId)
 {
     for (int i = 0; i < accounts.size() ; i++) {
         if(accounts.get(i).getAccountId().equals(accountId))
             return userName;

     }
     return "";
 }

}

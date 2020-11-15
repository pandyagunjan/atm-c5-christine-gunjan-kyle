package consoleMenu;


import account.Account;
import account.AccountManager;
import user.UserManager;
import consoleMenu.Console;
import user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Menu {
    AccountManager accountManager;
    UserManager manager;
    private int choice;
    private String accountId = "";
    User currentUser;
    Account currentAccount;

    public Menu(UserManager manager, AccountManager accountManager) {
        this.manager = manager;
        this.accountManager = accountManager;
    }

    public Menu() {

    }

    public void accountMenu(Account account) throws IOException {
        this.currentAccount = account;
        int transactionChoice = 0;
        this.accountId = accountId;
        System.out.println("****************************");
        System.out.println("1.  Add account");
        System.out.println("2.  Get account balance");
        System.out.println("3.  Make withdrawal");
        System.out.println("4.  Make deposit");
        System.out.println("5.  Transfer");
        System.out.println("6.  Close Account");
        System.out.println("7.  Go to Previous Menu");
        System.out.println("****************************");
        transactionChoice = Console.getIntInput("Please select from these options: :  ");
        matchTransactionMethod(transactionChoice);
    }

    public void matchTransactionMethod(int choice) throws IOException {
        Random random = new Random();
        Double balcanceNewAccount = 0d;
        String accountType = "";
        Integer accountId = 010;

        switch (choice) {
            case 1:
                accountId += random.nextInt(998) + 1;
                balcanceNewAccount = Console.getDoubleInput("Please enter the amount :  ");
                accountType = Console.getStringInput("Enter account type CHECKING/SAVINGS/INVESTMENT:  ");
                currentUser.addAccount(String.valueOf(accountId), balcanceNewAccount, accountType);
                ArrayList<Account> modifiedAccounts = currentUser.getAccounts();
                for (int i = 0; i < modifiedAccounts.size(); i++) {
                    if (modifiedAccounts.get(i).getAccountId().equalsIgnoreCase(String.valueOf(accountId))) {
                        currentAccount = modifiedAccounts.get(i);
                    }

                }
                accountMenu(currentAccount);
                // userOptionsMenu(currentUser);

                break;
            case 2:
                System.out.println(currentAccount.getBalance());
                accountMenu(currentAccount);
                break;
            case 3:
                Double withdrawAmount = 0.0;
                withdrawAmount = Console.getDoubleInput("Please enter the amount :  ");
                currentAccount.withdraw(withdrawAmount);
                System.out.println(currentAccount.getBalance());
                accountMenu(currentAccount);
                break;
            case 4:
                Double depositAmount = 0.0;
                depositAmount = Console.getDoubleInput("Please enter the amount :  ");
                currentAccount.deposit(depositAmount);
                accountMenu(currentAccount);
                break;
            case 5:
                Double transferAmount = 0.0;
                transferAmount = Console.getDoubleInput("Please enter the amount : ");
                currentAccount.withdraw(transferAmount);
                // this needs to be a new menu just with their current accounts.
//                switch (currentAccount) {
//                    Integer choiceOfAccount = 0;
//                    case 1:
//                        return currentAccount.deposit(transferAmount);
//                    case 2:
//                        return currentAccount.deposit(transferAmount);
//                    case 3:
//                        return currentAccount.deposit(transferAmount);
//                        break;
                break;
            case 6:
                if (currentAccount.getBalance() > 0.0) {
                    currentAccount.withdraw(currentAccount.getBalance());
                }
                currentUser.removeAccount(currentAccount.getAccountId());
                try {
                    userOptionsMenu(currentUser);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 7:
                userOptionsMenu(currentUser);
                break;
            default:
                System.out.println("Invalid entry");
                accountMenu(currentAccount);
                //will that work?^
                break;
        }
    }

    public void userOptionsMenu(User currentUser) throws IOException {
        this.currentUser = currentUser;
        int newPin = -1;
        Integer choiceOfAccount = 0;
        Account accountSelected = new Account();
        String accountTypeSelected="";
        System.out.println("**** ACCOUNT OPTIONS *****  ");
        System.out.println("1.  Checking");
        System.out.println("2.  Savings");
        System.out.println("3.  Investment");
        System.out.println("4.  Add new account");
        System.out.println("5.  Change pin");
        System.out.println("6.  Log out of user");
        choiceOfAccount = Console.getIntInput("Please select from these options:  ");

        switch (choiceOfAccount) {

            case 1:
                 accountTypeSelected="CHECKING";
                //Account accountSelected = new Account();

                for(int i=0;i<currentUser.getAccounts().size();i++) {
                    if(currentUser.getAccounts().get(i).getAccountType().equals(accountTypeSelected))
                        accountSelected=currentUser.getAccounts().get(i);
                }
                //For the selected account , display menu to do transaction
                this.accountMenu(accountSelected);
                //return "CHECKING";

            case 2:
                 accountTypeSelected="SAVINGS";
                 accountSelected = new Account();

                for(int i=0;i<currentUser.getAccounts().size();i++) {
                    if(currentUser.getAccounts().get(i).getAccountType().equals(accountTypeSelected))
                        accountSelected=currentUser.getAccounts().get(i);
                }
                //For the selected account , display menu to do transaction
                this.accountMenu(accountSelected);

               // return "SAVINGS";
            case 3:
                accountTypeSelected="INVESTMENT";
                accountSelected = new Account();

                for(int i=0;i<currentUser.getAccounts().size();i++) {
                    if(currentUser.getAccounts().get(i).getAccountType().equals(accountTypeSelected))
                        accountSelected=currentUser.getAccounts().get(i);
                }
                //For the selected account , display menu to do transaction
                this.accountMenu(accountSelected);

                //return "INVESTMENT";
            case 4:
                this.matchTransactionMethod(1);
               // return "";
            case 5:

                newPin = Console.getIntInput("Please enter the new pin:  ");
                manager.changePin(currentUser.getUserName(), newPin);
               // return "";
            case 6:

                 manager.printOnFile();
                //log out method
                //prompt to log in again or quit atm


                break;

            default:
                System.out.println("Invalid entry");
        }
      //  return "";
    }
}


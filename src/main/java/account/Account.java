package account;

import java.util.List;
import java.util.Map;

public class Account {
    private Double balance;
    private String accountId;

    public Account(String accountId, Double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }
    public String getAccountId() { return  accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) { this.balance = balance; }

}
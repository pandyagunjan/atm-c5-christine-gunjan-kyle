package account;

public class Checking extends Account {

    public Checking(String accountId, Double balance ,String accountType) {
        super(accountId, balance,accountType);
    }

    public Checking() {

    }

    public Double getBalance() {
        return super.getBalance();
    }



}

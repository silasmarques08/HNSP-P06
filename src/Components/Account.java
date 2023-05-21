package Components;

import java.util.Map;

public abstract class Account implements Comparable<Account> {                                //1.2.1 Creation of the account class
    protected String label;
    protected double balance;
    protected int accountNumber;
    protected static int count = 10000;

    protected Client client;

protected Account(String _label, Client _client){
    label = _label;
    client = _client;
    accountNumber = ++count;
    balance = 0;
}


    public String getLabel() {return label;}

    public void setLabel(String label) {this.label = label;}

    public double getBalance() {return balance;}

    public void setBalance(Flow flow) {
        if(flow instanceof Credit) {
            this.balance += flow.getAmount();
        }
        else if (flow instanceof Debit){
            this.balance -= flow.getAmount();
        }
        else if (flow instanceof Transfer){
            if(flow.getTarget() == this.accountNumber){
                this.balance += flow.getAmount();
            }
            else if (((Transfer) flow).getSource() == this.accountNumber){
                this.balance -= flow.getAmount();
            }
        }
}

    public int getAccountNumber() {return accountNumber;}

    public void setAccountNumber(int accountNumber) {this.accountNumber = accountNumber;}

    @Override
    public int compareTo(Account otherAccount) {
        double x = this.getBalance() - otherAccount.getBalance(); 
        return  (int) Math.round(x);
    }
    @Override
    public String toString() {
        return "Account_ID# " + accountNumber + ", Type: " + label+
                ", Balance: " + balance +
                "€, Client: [" + client +
                "]";
    }
}

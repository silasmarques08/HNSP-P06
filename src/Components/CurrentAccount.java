package Components;

public class CurrentAccount extends Account{        //1.2.2 Creation of the CurrentAccount and SavingsAccount
    public CurrentAccount(Client _client) {
        super("Current", _client);
    }
}

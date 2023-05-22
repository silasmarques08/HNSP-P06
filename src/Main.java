import java.nio.file.Paths;
import java.util.*;
import Components.*;
import static java.lang.System.*;
import java.util.function.Predicate;
import java.nio.file.Path;

public class Main {                                     // 1.1.2 Creation of main class for tests

public static Collection<Client> testClientCreate(int slots){

    Collection<Client> testClients = new ArrayList<Client>();

    for(int i = 0; i<slots; i++){
        Client tempClient = new Client("firstname"+(i+1),"lastname"+(i+1));
        testClients.add(tempClient);
    }

    return testClients;
    }

public static void testClientDisplay(Collection<Client> allClients){
    out.println("\nList of clients:");
    allClients.stream().forEach(out::println);
}

public static Collection<Account> testAccountCreate(Collection<Client> allClients){ //1.2.3 Creation of the account tables

    Collection<Account> testAccounts = new ArrayList<Account>();

    for (Client client : allClients){
        CurrentAccount tempCA = new CurrentAccount(client);
        SavingsAccount tempSA = new SavingsAccount(client);
        testAccounts.add(tempCA);
        testAccounts.add(tempSA);
    }
    return testAccounts;
}

public static void testAccountDisplay(Collection<Account> allAccounts){
        out.println("\nStarting list of all accounts:");
        allAccounts.stream().forEach(out::println);
    }

    //1.3.1 Adaptation of the table of accounts
public static Hashtable<Integer, Account> testEntryCreate(Collection<Account> allAccounts){ // 1.3.1 Adaptation of the table of accounts

    Hashtable<Integer, Account> testEntries = new Hashtable<>();

    for(Account account : allAccounts){
        testEntries.put(account.getAccountNumber(), account);
    }
    return testEntries;
}

public static void testEntryDisplay(Hashtable<Integer, Account> allEntries){

    TreeMap<Integer,Account> treemap = new TreeMap<>(allEntries);
    out.println("\nUpdated list of all accounts:");
    treemap.values().stream().sorted(Comparator.comparing(Account::getBalance)).forEach(out::println);

}


public static ArrayList<Flow> testFlowCreate(Hashtable<Integer, Account> allEntries){  //1.3.4 Creation of the flow array
    ArrayList<Flow> testFlows = new ArrayList<>();

    Debit test01 = new Debit(50, 10001);
    testFlows.add(test01);

    for(Map.Entry<Integer, Account> Entry : allEntries.entrySet()){
        if(Objects.equals(Entry.getValue().getLabel(), "Current")){
            Credit test02 = new Credit(100.5, Entry.getKey());
            testFlows.add(test02);
        }
    }

    for(Map.Entry<Integer, Account> Entry : allEntries.entrySet()){
        if(Objects.equals(Entry.getValue().getLabel(), "Savings")){
            Credit test03 = new Credit(1500, Entry.getKey());
            testFlows.add(test03);
        }
    }

    Transfer test04 = new Transfer(50,10002,10001);
    testFlows.add(test04);

    return testFlows;
}

public static void processAll (ArrayList<Flow> allTransactions, Hashtable<Integer, Account> allEntries){ //1.3.5 Updating accounts
    for(Flow transaction : allTransactions){
        allEntries.get(transaction.getTarget()).setBalance(transaction);
        if(transaction instanceof Transfer){
            allEntries.get(((Transfer) transaction).getSource()).setBalance(transaction);
        }
    }

    testEntryDisplay(allEntries);

    List<Account> negativeBalances = checkBalances(allEntries, i -> i < 0);
    if (!negativeBalances.isEmpty()){
        out.println("\nWARNING! ACCOUNT(S) WITH NEGATIVE BALANCE. THEY ARE THE FOLLOWING:");
        negativeBalances.stream().forEach(out::println);
    }

}
    public static List<Account> checkBalances(Hashtable<Integer, Account> allEntries, Predicate<Double> predicate) {
        List<Account> result = new ArrayList<>();

        for (Account account: allEntries.values()){
            if(predicate.test(account.getBalance())){
                result.add(account);
            }
        }
        return result;
    }



    public static void main(String[] args) {
        Collection<Client> allClients;                  // 1.1.2 Creation of main class for tests
        allClients = testClientCreate(5);
        testClientDisplay(allClients);

        Collection<Account> allAccounts;                //1.2.3 Creation of the account tables
        allAccounts = testAccountCreate(allClients);
        testAccountDisplay(allAccounts);

        Hashtable<Integer, Account> allEntries;         // 1.3.1 Adaptation of the table of accounts
        allEntries = testEntryCreate(allAccounts);

        ArrayList<Flow> allTransactions;                //1.3.4 Creation of the flow array
        allTransactions = testFlowCreate(allEntries);

        processAll(allTransactions,allEntries);         //1.3.5 Updating accounts
    }
}
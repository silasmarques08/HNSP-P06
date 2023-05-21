package Components;

public class Client {                                       // 1.1.1 Creation of the client class
    private static int count = 0;
    private int clientNumber;
    private String firstName;
    private String lastName;

    public Client(String first, String last){
        firstName = first;
        lastName = last;
        clientNumber = ++count;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    @Override
    public String toString() {
        return "Client_ID# " + clientNumber +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'';
    }
}

package Components;

public class Transfer extends Flow{                     //1.3.3 Creation of the Transfer, Credit, Debit classes
    private int source;
    public Transfer(double _amount, int _target, int _source){
        super(_amount, _target, "Transfer");
        source = _source;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

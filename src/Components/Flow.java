package Components;

import java.time.*;

public abstract class Flow {
    private String comment;
    private int identifier;
    private double amount;
    private int target;
    private boolean effect;
    private LocalDate date;

    private static int count = 55000;
    protected Flow(double _amount, int _target){
    identifier = ++count;
    amount = _amount;
    target = _target;
    comment = " No comment. ";
    effect = false;
    date = LocalDate.now().plusDays(2);
    }

    public int getIdentifier() {
        return identifier;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean getEffect(){
        return effect;
    }
    public void setEffect(boolean effect) {
        this.effect = effect;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "{Transaction #" + identifier+ " - "+
                "Comment='" + comment + '\'' +
                ", Amount=" + amount +
                "€, Target Account ID=" + target +
                ", Effect=" + effect +
                ", Date=" + date +
                "}";
    }
}
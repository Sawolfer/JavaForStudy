import javax.swing.*;

public class Passport extends Document implements Expirable{

    private int age;
    private boolean reissued;

    public Passport(String name, int age, int number) {
        super(DocType.PASSPORT, number, name);
        this.age = age;
    }

    @Override
    public boolean hasExpired(){
        if (age > 44) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void reissue(){
        this.reissued = true;
    }

    @Override
    public void print(){
        System.out.println(type() + " #" + number + " of " + ownerName);
        System.out.println(" Status: " + (hasExpired()?"expired":"valid"));
    }
}

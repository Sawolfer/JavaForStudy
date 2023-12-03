public class Snils extends Document{


    public Snils(String name, int number) {
        super(DocType.SNISL, number, name);
    }

    @Override
    public void print(){
        System.out.println(type() + " #" + number + " of " + ownerName);
    }
}

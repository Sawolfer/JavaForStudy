public class Human extends Creature{

//    protected String name = "Joe";

    @Override
    public void bear() {
        this.setName("Joe");
        System.out.printf("The " + this.getClass().getName() + getName() + " was born");
    }

    @Override
    public void die() {
        System.out.printf("The " + this.getClass().getName() + getName() + "has died");
    }
}

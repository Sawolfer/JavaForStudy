public class Dog extends Creature{


    @Override
    public void bear() {
        setName("Max10");
        System.out.printf("The " + this.getClass().getName() + getName() + " was born");
    }

    @Override
    public void die() {
        System.out.printf("The " + this.getClass().getName() + getName() + "has died");
    }

}

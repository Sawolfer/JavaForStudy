public class Alien extends Creature{

    @Override
    public void bear() {
        this.setName("Alubadu");
        System.out.printf("The " + this.getClass().getName() + getName() + " was born");
    }

    @Override
    public void die() {
        System.out.printf("The " + this.getClass().getName() + getName() + "has died");
    }

}

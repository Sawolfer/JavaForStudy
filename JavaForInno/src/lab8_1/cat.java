package lab8_1;

public class cat extends Animal{


    protected cat(String _name, int _height, int _weght, String _color, String _sound) {
        super("cat", 40, 9, "grey", "meeows");
    }

    @Override
    protected void ToSleep() {
        super.ToSleep();
    }

    @Override
    protected void ToEat() {
        super.ToEat();
    }
    @Override
    protected void ToMakeSound() {
        super.ToMakeSound();
    }
}

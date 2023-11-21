public class Cat extends Animal{

    String purLoudness;

    @Override
    public void Voice() {
        System.out.printf(purLoudness);
    }
}

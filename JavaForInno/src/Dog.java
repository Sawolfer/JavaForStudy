public class Dog extends Animal{

    String barkingLoudness;

    @Override
    public void Voice() {
        System.out.printf(barkingLoudness);
    }
}

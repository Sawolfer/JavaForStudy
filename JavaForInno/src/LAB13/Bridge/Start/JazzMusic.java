package LAB13.Bridge.Start;

public class JazzMusic extends MusicStreamingApp {
    
    @Override
    public void play(String songName) {
        System.out.println("Playing: Jazz" + songName);
    }

    @Override
    public void decode(String fileName) {
        super.decode(fileName);
    }
}

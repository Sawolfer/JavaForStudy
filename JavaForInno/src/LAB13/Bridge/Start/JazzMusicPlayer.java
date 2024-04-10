package LAB13.Bridge.Start;

public class JazzMusicPlayer implements MusicPlayer {
Codec codec;
    JazzMusicPlayer(Codec codec){
        this.codec = codec;
        System.out.println("JazzMusicPlayer object created");
    }
    public void play(String fileName) {
        System.out.println("Playing Jazz music...");
        codec.decode(fileName);
    }

    
}
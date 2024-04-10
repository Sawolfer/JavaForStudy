package LAB13.Bridge.Start;

// Client
public class MusicStreamingApp implements MusicPlayer {

    // Bridge
    @Override
    public void decode(String fileName) {
        MusicPlayer.super.decode(fileName);
    }
    
    @Override
    public void play(String songName) {
        // Add your implementation for playing the song here
        System.out.println("Playing: " + songName);
    }
    // Client
    public static void main(String[] args) {

        MusicPlayer popMusicPlayer1 = new PopMusicPlayerMP3Codec();
        MusicPlayer jazzMusicPlayer = new JazzMusicPlayerMP3Codec();
        MusicPlayer popMusicPlayer2 = new PopMusicPlayerWAVCodec();

        popMusicPlayer1.play("pop_song_1.mp3");
        jazzMusicPlayer.play("jazz_song.mp3");
        popMusicPlayer2.play("pop_song_2.wav");
    }
}
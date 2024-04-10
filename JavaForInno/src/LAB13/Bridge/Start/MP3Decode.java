package LAB13.Bridge.Start;

public class MP3Decode implements Codec {
    
    public  void decode(String fileName){
        System.out.println("Playing " + fileName + " using MP3 codec.");
    };
}

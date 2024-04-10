package LAB13.Bridge.Start;

public class WAVDecode  implements Codec {

    public void decode(String fileName){
        System.out.println("Playing " + fileName + " using WAV codec.");
    };
}

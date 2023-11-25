
import java.io.*;

public class Main {

    private static InvalidBoardSizeException invalidBSE = new InvalidBoardSizeException();
    private static InvalidNumberOfInsectsException invalidNOIE = new InvalidNumberOfInsectsException();
    private static InvalidNumberOfFoodPointsException invalidNOFPE = new InvalidNumberOfFoodPointsException();
    private static Board gameBoard;
    private static BufferedWriter bw;
    private static BufferedReader br;
    private static int d;
    private static int n;
    private static int m;

    public static void main(String[] args) {
        try {
            bw = new BufferedWriter(new FileWriter("src/output.txt"));
            br = new BufferedReader(new FileReader("src/input.txt"));
            String currentString;
            String strD = br.readLine();
            String strN = br.readLine();
            String strM = br.readLine();
            try {
                d = Integer.parseInt(strD);
                n = Integer.parseInt(strN);
                m = Integer.parseInt(strM);
            } catch (NumberFormatException e) {
                finish();
            }
            if (d < 4 || d > 1000) {
                bw.write(invalidBSE.getMessage());
                finish();
            }
            if (n < 1 || n > 16) {
                bw.write(invalidNOIE.getMessage());
                finish();
            }
            if (m < 1 || m > 200) {
                bw.write(invalidNOFPE.getMessage());
                finish();
            }
            while ((currentString = br.readLine()) != null) {
                bw.write(currentString);
                bw.newLine();
            }
            finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void finish() {
        try {
            bw.close();
            br.close();
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }
}


import com.sun.org.apache.xpath.internal.objects.XNull;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static InvalidBoardSizeException invalidBSE = new InvalidBoardSizeException();
    private static InvalidNumberOfInsectsException invalidNOIE = new InvalidNumberOfInsectsException();
    private static InvalidNumberOfFoodPointsException invalidNOFPE = new InvalidNumberOfFoodPointsException();
    public static InvalidInsectColorException invalidICE = new InvalidInsectColorException();
    private static InvalidEntityPositionException invalidEPE = new InvalidEntityPositionException();
    private static Board gameBoard;
    public static BufferedWriter bw;
    public static BufferedReader br;
    private static int d;
    private static int n;
    private static int m;

    List<Insect> insects = new ArrayList<>();

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
                finish("Wrong inputs");
            }
            if (d < 4 || d > 1000) {
                finish(invalidBSE.getMessage());
            }
            gameBoard = new Board(d);
            if (n < 1 || n > 16) {
                finish(invalidNOIE.getMessage());
            }
            if (m < 1 || m > 200) {
                finish(invalidNOFPE.getMessage());
            }
            for (int i = 0; i < n; i++) {
                currentString = br.readLine();
                int tmpX, tmpY;
                EntityPosition position;
                String[] words = currentString.split(" ");
                InsectColor color = InsectColor.toColor(words[0]);
                words[1] = words[1].toLowerCase();
                switch (words[1]){
                    case ("ant"):
                        try {
                            tmpX = Integer.parseInt(words[2]);
                            tmpY = Integer.parseInt(words[3]);
                            
                            position = new EntityPosition(tmpX, tmpY);
                            Ant newAnt = new Ant(position, color);
                        } catch (NumberFormatException e){
                            finish(invalidEPE.getMessage());
                        }

                }
                System.out.println(color);
            }
            finish("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void finish(String exception) {
        try {
            bw.write(exception);
            bw.close();
            br.close();
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(0);
    }
}

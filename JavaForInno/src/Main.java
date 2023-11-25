import sun.lwawt.macosx.CSystemTray;

import java.io.*;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static Board gameBoard;
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
            String currentString;
            while ((currentString = br.readLine()) != null ){
                System.out.println(currentString);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
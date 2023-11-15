import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

enum LevelOf{
    Hard,
    easy;
}

public class test {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
//        String word = sc.next();
//        LevelOf level;
//        level = new LevelOf();
//        if (word == "easy"){
//            level = LevelOf.easy;
//        }
//        System.out.printf(String.valueOf(level));
//        String a  = "aaa";
//        String b = "AAA";
//
//        System.out.print(a.compareTo(b));
        List<String> wok = new ArrayList<String>();
        wok.add("123");
        wok.add("ads");
        wok.add("papa");
        wok.add("solo");

        wok.remove("ads");

        for (String item: wok) {
            for (int i=0; i<item.length(); i++) {
                if ('a'<= item.charAt(i) && item.charAt(i)<='z'){
                    System.out.printf("true");
                }

            }
            item = item.toUpperCase();
            System.out.printf(item);
            System.out.printf("\n");
        }
    }

}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;

enum LevelOf{
    Hard,
    easy;
}

public class test {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String bebe = sc.nextLine();
        System.out.print(courseNameChecker(bebe));

    }
    private static void bebeb(String toReturn){
        System.out.println(toReturn);
    }

    public static boolean courseNameChecker(String input) { //checking the course name(only English letters, under
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            if (!(Character.isLetter(c) && ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')))) {
                if (c != '_') {
                    return false;
                }
            }
        }
        if (input.contains("___") || input.compareTo("")==0 || input.equalsIgnoreCase("student") || input.equalsIgnoreCase("course") || input.equalsIgnoreCase("professor") || input.equalsIgnoreCase("master") || input.equalsIgnoreCase("bachelor")) {
            return false;
        }
        return (input.charAt(length - 1) != '_' && input.charAt(0) != '_');
    }
}

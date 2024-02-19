import java.util.Scanner;

class RestoringSpaces{

    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);
        int countWords = sc.nextInt();
        int countLetters = sc.nextInt();
        int[] dp = new int[countLetters+1];

        String[] inputWords = new String[countWords];
        for( int i = 0; i < countWords; i++){
            inputWords[i] = sc.next();
        }

        

    }
}


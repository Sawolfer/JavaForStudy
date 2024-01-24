// Savva Ponomarev

import java.util.Scanner;
public class TopRanking{

    private static Scanner sc;
    public static Player[] players;
    static int countTopPlayers;
    static int countPlayers;
    public static void main(String args[]) {
        sc = new Scanner(System.in);
        countPlayers = sc.nextInt();
        countTopPlayers = sc.nextInt();
        players = new Player[countPlayers];
        for (int i = 0; i < countPlayers; i++) {
            String currentString = sc.next();
            int currentScore = sc.nextInt();
            Player newPlayer = new Player(currentString, currentScore);
            players[i] = newPlayer;
        }
        printNSort();
    }

    public static void printNSort(){
        int count = countPlayers;
        while(count > 0 && countTopPlayers > 0){
            Player tmpMax = players[0];
            int pos=0;
            for (int i =0; i< players.length; i++){
                if (players[i].score > tmpMax.score){
                    tmpMax = players[i];
                    pos = i;
                }
            }
            System.out.println(tmpMax.name + " " + tmpMax.score);
            players[pos].score = -1;
            count --;
            countTopPlayers--;
        }
    }

}
class Player{
    String name;
    int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

/**
 * while writing this program my mate Kirill Efimovich gave me idea not to sort all massive
 */

//// Savva Ponomarev
//
//
//import java.util.Scanner;
//import java.util.logging.Level;
//
//class Player{
//    String name;
//    int score;
//
//    public Player(String name, int score) {
//        this.name = name;
//        this.score = score;
//    }
//}
//class TopRanking {
//
//    private static Scanner sc;
//    public static Player[] players;
//    public static void main(String args[]){
//        sc = new Scanner(System.in);
//        int countPlayers = sc.nextInt();
//        int countTopPlayers = sc.nextInt();
//        players = new Player[countPlayers];
//        for (int i = 0; i < countPlayers; i++) {
//            String currentString = sc.next();
//            int currentScore = sc.nextInt();
////            String[] parts = currentString.split(" ");
//            Player newPlayer = new Player(currentString, currentScore);
//            players[i] = newPlayer;
//        }
//        players = sortArray(players);
//        boolean t = true;
//        int i = players.length;
//        while (t) {
//
//            System.out.println(players[i].name + " " + players[i].score);
//            countTopPlayers--;
//            i--;
//            if ( i ==0 || countTopPlayers == 0) {
//                t = false;
//            }
//
//        }
//
//    }
//
//
//    public static Player[] sortArray(Player[] players){
//
//        if (players == null) return null;
//
//        if (players.length < 2) return players;
//
//        Player[] leftArr = new Player[players.length / 2 ];
//        System.arraycopy(players, 0, leftArr, 0, players.length / 2);
//
//        Player[] rightArr = new Player[players.length - players.length/2];
//        if ((players.length)%2 == 0){
//            System.arraycopy(players, (players.length/2), rightArr, 0, (players.length - (players.length/2)));
//        } else {
//            int l = players.length /2;
//            int b = players.length - players.length/2;
//            System.arraycopy(players, (players.length/2), rightArr, 0, players.length - players.length/2);
//        }
//
//        leftArr = sortArray(leftArr);
//        rightArr = sortArray(rightArr);
//
//
//        return mergeArray(leftArr, rightArr);
//    }
//
//    public static Player[] mergeArray(Player[] leftArr, Player[] rightArr) {
//        Player[] mergedArr = new Player[leftArr.length + rightArr.length];
//        int posL = 0, posR = 0;
//
//        for (int i = 0; i < mergedArr.length; i++){
//            if (posL == leftArr.length) {
//                mergedArr[i] = rightArr[i - posR];
//                posR++;
//            } else if (posR == rightArr.length) {
//                mergedArr[i] = leftArr[i - posL];
//                posL++;
//            } else if (leftArr[i - posL].score < rightArr[i - posR].score) {
//                mergedArr[i] = leftArr[i - posL];
//                posL++;
//            } else {
//                mergedArr[i] = rightArr[i - posR];
//                posR++;
//            }
//        }
//        return mergedArr;
//    }
//
//}
//
//
//
